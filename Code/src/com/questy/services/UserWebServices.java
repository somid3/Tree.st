package com.questy.services;

import com.questy.dao.NetworkDao;
import com.questy.dao.UserDao;
import com.questy.dao.UserSessionDao;
import com.questy.domain.Network;
import com.questy.domain.User;
import com.questy.domain.UserSession;
import com.questy.enums.RoleEnum;
import com.questy.enums.UserIntegerSettingEnum;
import com.questy.helpers.Tuple;
import com.questy.helpers.UIException;
import com.questy.services.email.EmailConfirmationServices;
import com.questy.utils.StringUtils;
import com.questy.web.WebUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class UserWebServices extends ParentService {

    public static UserSession authenticateAndCreateSession(
        WebUtils webUtils,
        String email,
        String passwordHash,
        Boolean persistent) throws SQLException {

        String httpAgent = webUtils.getUserAgent();

        // Logging user in, creating a new user session
        return UserServices.authenticateAndCreateSession(email, passwordHash, httpAgent, persistent);
    }

    public static UserSession installCookies(
        WebUtils webUtils,
        Integer userId,
        String sessionChecksum,
        Boolean persistent) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving user
        User user = UserDao.getById(conn, userId);

        // Retrieving
        UserSession userSession = UserSessionDao.getByUserIdAndChecksum(conn, user.getId(), sessionChecksum);

        // Should the user be kept logged in?
        Integer maxAge = -1;
        if (persistent)
            maxAge = WebUtils.SECONDS_IN_YEAR;

        // Setting cookie with user id
        webUtils.createCookie("uid", user.getId().toString(), maxAge, "/");

        // Setting cookie with user checksum
        webUtils.createCookie("scs", user.getSaltChecksum(), maxAge, "/");

        // Persistently setting cookie with user's email for the next time the login screen is used */
        webUtils.createCookie("ue", user.getEmail(), WebUtils.SECONDS_IN_YEAR, "/");

        // Setting cookie with user session checksum
        webUtils.createCookie("uscs", userSession.getChecksum(), maxAge, "/");

        return userSession;
    }

    public static boolean authenticateViaCookies(WebUtils webUtils) throws SQLException {

        // Retrieving user id cookie
        Integer userId = webUtils.getCookieValueAsInteger("uid");

        // Retrieving user session checksum cookie
        String sessionChecksum = webUtils.getCookieValue("uscs");

        // Authenticating user session checksum
        return UserServices.checkAuthenticity(userId, sessionChecksum, webUtils.getUserAgent());
    }

    public static String addUser (
            WebUtils webUtils,
            Integer networkId,
            String networkChecksum,
            String email,
            String passwordText,
            String first,
            String last) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        StringBuilder buf = new StringBuilder();

        // Retrieve network
        Network network = NetworkDao.getByIdAndChecksum(conn, networkId, networkChecksum);

        // Validating email
        if (!StringUtils.isEmail(email))
            throw new UIException("Email address is not valid");

        // Validating network
        if (network == null)
            throw new UIException("Network could not be found");

        // Validate email length
        if (email.isEmpty())
            throw new UIException("Please provide a valid email address");

        // Validating if email ends correctly
        Tuple<Boolean, String> emailEndingTest = NetworkServices.isEmailEndingGood(networkId, email);

        if (!emailEndingTest.getX())
            throw new UIException("Please use an email that ends with " + emailEndingTest.getY());

        // Validate password length, complexity, etc
        if (passwordText.length() < 6)
            throw new UIException("Your password needs to be greater than five characters in length");

        // Check if the email already exists as an account
        User user = UserDao.getByEmail(null, email);

        // Does the user already exist?
        if (user != null) {

            // Yes, the user already exists...

            Boolean persistent = true;

            // Create password hash
            String providedPasswordHash = UserDao.hashPassword(passwordText, user.getPasswordSalt());

            // Yes, but was the provided password correct?
            UserSession userSession = UserWebServices.authenticateAndCreateSession(webUtils, user.getEmail(), providedPasswordHash, persistent);
            if (userSession == null)
                throw new UIException("Provide the correct password for this email address");

            // Add user to network and its dependants
            NetworkServices.addUserToNonGlobalNetworkWithDependencies(network.getId(), user.getId(), RoleEnum.MEMBER);

            // Has the user been confirmed by email?
            Boolean isEmailConfirmed = UserIntegerSettingEnum.IS_EMAIL_CONFIRMED.getBooleanByUserId(user.getId());

            if (!isEmailConfirmed) {

                // Send confirmation email
                EmailConfirmationServices.sendConfirmationEmail(user.getId());

                // Add email confirmation action to response
                buf.append("<confirm/>");

            } else {

                // Install login cookies at client
                UserWebServices.installCookies(webUtils, user.getId(), userSession.getChecksum(), persistent);

                // Add send to application action in response
                buf.append("<app/>");

            }

        // Email does not already exist
        } else {

            // Validate first name
            if (first.isEmpty())
                throw new UIException("Please provide your first name");

            // Validate last name
            if (last.isEmpty())
                throw new UIException("Please provide your last name");

            // Create user account
            Integer userId = UserDao.insert(null, email, passwordText, first, last);

            // Retrieve new user
            user = UserDao.getById(null, userId);

            // Add all global networks for user
            NetworkServices.addGlobals(user.getId(), RoleEnum.MEMBER);

            // Adding user to network, and all its dependencies
            NetworkServices.addUserToNonGlobalNetworkWithDependencies(network.getId(), user.getId(), RoleEnum.MEMBER);

            // Sending first email confirmation
            EmailConfirmationServices.beginEmailConfirmation(user.getId());

            // Add email confirmation action to response
            buf.append("<confirm/>");

        }

        return buf.toString();
    }

    public static String login (
            WebUtils webUtils,
            String email,
            String passwordText,
            Boolean keep) throws SQLException {

        StringBuilder buf = new StringBuilder();

           // Validating email
        if (!StringUtils.isEmail(email))
            throw new UIException("Email is not valid");

        // Retrieving user to get salt
        User user = UserDao.getByEmail(null, email);

        // Validating password
        if (StringUtils.emptyIfNull(passwordText).isEmpty())
            throw new UIException("Please provide a password");

        // Validating credentials
        if (user == null)
            throw new UIException("Incorrect email address or password");

        // Creating hashed password for login
        String providedPasswordHash = UserDao.hashPassword(passwordText, user.getPasswordSalt());

        // Logging user in, creating a new user session
        UserSession userSession = UserWebServices.authenticateAndCreateSession(webUtils, email, providedPasswordHash, keep);
        if (userSession == null)
            throw new UIException("Incorrect email address or password");

        /* Yay! Credentials are correct */

        // Has the user been confirmed by email?
        Boolean isEmailConfirmed = UserIntegerSettingEnum.IS_EMAIL_CONFIRMED.getBooleanByUserId(user.getId());

        // Has the user been confirmed by email?
        if (!isEmailConfirmed) {

            // No, sending another pair of email confirmations
            EmailConfirmationServices.sendConfirmationEmail(user.getId());

            // Add email confirmation action to response
            buf.append("<confirm/>");

        } else {

            // Install login cookies at client
            UserWebServices.installCookies(webUtils, user.getId(), userSession.getChecksum(), keep);

            // Add send to application action in response
            buf.append("<app/>");

        }


        return buf.toString();
    }

}
