package com.questy.services;

import com.questy.dao.*;
import com.questy.domain.*;
import com.questy.enums.RoleEnum;
import com.questy.enums.UserIntegerSettingEnum;
import com.questy.helpers.SqlLimit;
import com.questy.helpers.Tuple;
import com.questy.helpers.UIException;
import com.questy.services.email.EmailConfirmationServices;
import com.questy.services.email.EmailServices;
import com.questy.utils.StringUtils;
import com.questy.web.HashRouting;
import com.questy.web.WebUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

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
            String emailToConfirm,
            String passwordText,
            String first,
            String last) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        StringBuilder buf = new StringBuilder();

        // Retrieve network
        Network network = NetworkDao.getByIdAndChecksum(conn, networkId, networkChecksum);

        // Validating email
        if (!StringUtils.isEmail(emailToConfirm))
            throw new UIException("Email address is not valid");

        // Validating network
        if (network == null)
            throw new UIException("Network could not be found");

        // Validating if email ends correctly
        Tuple<Boolean, String> emailEndingTest = NetworkServices.isEmailEndingGood(networkId, emailToConfirm);

        if (!emailEndingTest.getX())
            throw new UIException("Please use an email that ends with " + emailEndingTest.getY());

        // Validate password length, complexity, etc
        if (passwordText.length() < 6)
            throw new UIException("Your password needs to be greater than five characters in length");

        // Check if the email already exists as an account
        User user = UserDao.getByEmail(null, emailToConfirm);

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
            NetworkServices.addUserToNetworkWithDependencies(network.getId(), user.getId(), RoleEnum.MEMBER);

            // Has the user been confirmed by email?
            Boolean isEmailConfirmed = UserIntegerSettingEnum.IS_ACCOUNT_CONFIRMED.getBooleanByUserId(user.getId());

            if (!isEmailConfirmed) {

                // Send confirmation email
                EmailConfirmationServices.sendEmailConfirmation(user.getId());

                // Add email confirmation action to response
                buf.append("<confirm/>");

            } else {

                // Install login cookies at client
                UserWebServices.installCookies(webUtils, user.getId(), userSession.getChecksum(), persistent);

                // Add send to application action in response
                buf.append("<app go='" + HashRouting.questions(network.getId()) + "' />");

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
            Integer userId = UserDao.insert(null, emailToConfirm, passwordText, first, last);

            // Retrieve new user
            user = UserDao.getById(null, userId);

            // Adding user to network, and all its dependencies
            NetworkServices.addUserToNetworkWithDependencies(network.getId(), user.getId(), RoleEnum.MEMBER);

            // Sending first email confirmation
            EmailConfirmationServices.beginEmailConfirmation(user.getId(), emailToConfirm);

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
        Boolean isEmailConfirmed = UserIntegerSettingEnum.IS_ACCOUNT_CONFIRMED.getBooleanByUserId(user.getId());

        // Has the user been confirmed by email?
        if (!isEmailConfirmed) {

            // No, sending another pair of email confirmations
            EmailConfirmationServices.sendEmailConfirmation(user.getId());

            // Add email confirmation action to response
            buf.append("<confirm/>");

        } else {

            // Install login cookies at client
            UserWebServices.installCookies(webUtils, user.getId(), userSession.getChecksum(), keep);

            // Send user to application
            buf.append("<app/>");

        }

        return buf.toString();
    }

    public static String forgotSetPassword (
        WebUtils webUtils,
        Integer userId,
        String passwordChecksum,
        String passwordText,
        String passwordTextAgain) throws SQLException {

        StringBuilder buf = new StringBuilder();

        // Validating
        if (userId == null)
            throw new UIException ("Incorrect password reset link");

        // Retrieving password reset
        PasswordReset reset = PasswordResetDao.getByUserIdAndChecksum(null, userId, passwordChecksum);

        // Validating reset
        if (reset == null)
            throw new UIException ("Incorrect password reset, please start over");

        if ((new Date().getTime() - reset.getCreatedOn().getTime() > 3600000))
            throw new UIException ("Password reset link has exceed one hour, please start over");

        User user = UserDao.getById(null, userId);
        if (user == null)
            throw new UIException ("Incorrect password reset link");

        // Validating password
        if (StringUtils.emptyIfNull(passwordText).length() < 6)
            throw new UIException("Password must be greater than five characters");

        if (StringUtils.emptyIfNull(passwordTextAgain).length() < 6)
            throw new UIException("Password must be greater than five characters");

        if (!passwordText.equals(passwordTextAgain))
            throw new UIException("Both passwords do not match");

        /* Yay! We are all good to update the password */

        // Updating the password
        UserDao.updatePasswordByUserId(null, userId, passwordText);

        // Retrieving updated user
        user = UserDao.getById(null, userId);

        // Notify user by email that password was updated
        EmailServices.passwordResetDone(userId);

        // Delete reset password
        PasswordResetDao.deleteByUserIdAndChecksum(null, userId, passwordChecksum);

        // Creating hashed password for login
        String providedPasswordHash = UserDao.hashPassword(passwordText, user.getPasswordSalt());

        // Ensure user gets logged in persistently
        Boolean persistent = true;

        // Logging user in, creating a new user session
        UserSession userSession = UserWebServices.authenticateAndCreateSession(webUtils, user.getEmail(), providedPasswordHash, persistent);

        // Install login cookies at client
        UserWebServices.installCookies(webUtils, user.getId(), userSession.getChecksum(), persistent);

        // Send user to application
        buf.append("<app/>");

        return buf.toString();
    }

    /**
     * Provides the initial location that the user should be sent
     * once it enters the application
     */
    public static String getInitialHash (Integer userId) throws SQLException {

        // Retrieving list of user networks
        List<Network> networks = NetworkServices.getByUserId(userId, RoleEnum.VISITOR, SqlLimit.FIRST);
        Network firstNetwork = networks.get(0);

        // Retrieving user to network in case user is blocked
        UserToNetwork userToNetwork = UserToNetworkDao.getByUserIdAndNetworkId(null, userId, firstNetwork.getId());
        if (userToNetwork.getBlockedOn() != null)
            return HashRouting.blocked(firstNetwork.getId());

        // Retrieving first available question, if any..
        Integer nextQuestionRef = FlowRuleServices.getNextQuestionRef(userId, firstNetwork.getId());

        // Determining where to send initially...
        if (nextQuestionRef != null)
            return HashRouting.questions(firstNetwork.getId());
        else
            return HashRouting.smartGroups(firstNetwork.getId());
    }

}
