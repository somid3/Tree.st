package com.questy.services;

import com.questy.dao.*;
import com.questy.domain.*;
import com.questy.enums.NetworkIntegerSettingEnum;
import com.questy.enums.RoleEnum;
import com.questy.enums.UserIntegerSettingEnum;
import com.questy.helpers.SqlLimit;
import com.questy.helpers.Tuple;
import com.questy.helpers.UIException;
import com.questy.services.email.EmailConfirmationServices;
import com.questy.services.email.EmailServices;
import com.questy.utils.StringUtils;
import com.questy.utils.StripeServices;
import com.questy.web.HashRouting;
import com.questy.web.WebUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    public static String signup (
        WebUtils webUtils,
        Integer networkId,
        String networkChecksum,
        String emailToConfirm,
        String passwordText,
        String fullname,
        String cardToken) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

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
        if (StringUtils.isEmpty(passwordText) || passwordText.length() < 6)
            throw new UIException("Password needs to be greater than five characters in length");

        // Check if the email already exists as an account
        User user = UserDao.getByEmail(null, emailToConfirm);

        // Do we need to sign up a new user, or an existing one?
        String out = null;
        if (user == null)
            out = signupNewUser(
                    webUtils,
                    network,
                    emailToConfirm,
                    passwordText,
                    fullname,
                    cardToken);
        else
            out = signupExistingUser(
                    webUtils,
                    network,
                    user,
                    passwordText,
                    cardToken);

        return out;
    }


    private static String signupNewUser (
        WebUtils webUtils,
        Network network,
        String emailToConfirm,
        String passwordText,
        String fullname,
        String cardToken) throws SQLException {

        // Retrieve network settings
        Map<NetworkIntegerSettingEnum, Integer> networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(network.getId());

        // Extracting important networks settings
        Boolean networkAllowNonConfirmed = networkIntegerSettings.get(NetworkIntegerSettingEnum.IS_MODE_NO_CONFIRM) == 1;
        Boolean networkRequiresPayment = networkIntegerSettings.get(NetworkIntegerSettingEnum.IS_PAYMENT_REQUIRED) == 1;

        // Validate full name
        if (StringUtils.isEmpty(fullname))
            throw new UIException("Please provide your first name");

        // Validating last name
        if (fullname.split(" ").length < 2)
            throw new UIException("Please provide your last name");

        // Send user to provide payment details?
        if (networkRequiresPayment && StringUtils.isEmpty(cardToken))
            return "<payment/>";

        // If needed, create stripe customer
        String stripeId = createStripeCustomer(networkRequiresPayment, cardToken);

        // Create user
        User user  = null;
        {
            String[] splitName = fullname.split(" ");
            String first = splitName[0];
            String last = splitName[1];

            // Create user account
            Integer userId = UserDao.insert(null, emailToConfirm, passwordText, first, last);

            // Retrieve new user
            user = UserDao.getById(null, userId);
        }

        // Update stripe id
        if (!StringUtils.isEmpty(stripeId))
            UserDao.updateStripeId(null, user.getId(), stripeId);

        // Adding user to network and its dependants, if needed
        NetworkServices.addUserToNetworkWithDependencies(network.getId(), user.getId(), RoleEnum.MEMBER);

        // Begin confirmation email reminders?
        if (!networkAllowNonConfirmed) {
            EmailConfirmationServices.beginEmailConfirmation(user.getId(), emailToConfirm);
            return "<confirm/>";
        }

        // We are all good, send user to application
        return beginApplication(webUtils, network, user, passwordText);
    }

    private static String signupExistingUser (
        WebUtils webUtils,
        Network network,
        User user,
        String passwordText,
        String cardToken) throws SQLException {

        // Retrieve network settings
        Map<NetworkIntegerSettingEnum, Integer> networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(network.getId());

        // Extracting important networks settings
        Boolean networkAllowNonConfirmed = networkIntegerSettings.get(NetworkIntegerSettingEnum.IS_MODE_NO_CONFIRM) == 1;
        Boolean networkRequiresPayment = networkIntegerSettings.get(NetworkIntegerSettingEnum.IS_PAYMENT_REQUIRED) == 1;

        // Checking for duplicate email
        String passwordHash = UserDao.hashPassword(passwordText, user.getPasswordSalt());
        User authUser =  UserDao.getByEmailAndPasswordHash(null, user.getEmail(), passwordHash);
        if (authUser == null)
            throw new UIException("Email already exists, password does not match");

        // Send user to provide payment details?
        if (networkRequiresPayment &&
            StringUtils.isEmpty(user.getStripeId()) &&
            StringUtils.isEmpty(cardToken))
            return "<payment/>";

        // If needed, create stripe customer
        String stripeId = null;
        if (StringUtils.isEmpty(user.getStripeId()))
            stripeId = createStripeCustomer(networkRequiresPayment, cardToken);

        // Update stripe id
        if (!StringUtils.isEmpty(stripeId))
            UserDao.updateStripeId(null, user.getId(), stripeId);

        // Adding user to network and its dependants, if needed
        NetworkServices.addUserToNetworkWithDependencies(network.getId(), user.getId(), RoleEnum.MEMBER);

        // Send email confirmation email again?
        {
            // Has the user previously confirmed by email?
            Boolean isEmailConfirmed = UserIntegerSettingEnum.IS_ACCOUNT_CONFIRMED.getBooleanByUserId(user.getId());

            if (!networkAllowNonConfirmed && !isEmailConfirmed) {
                EmailConfirmationServices.sendEmailConfirmation(user.getId());
                return "<confirm/>";
            }
        }

        // We are all good, send user to application
        return beginApplication(webUtils, network, user, passwordText);
    }

    private static String beginApplication (
            WebUtils webUtils,
            Network network,
            User user,
            String passwordText
            ) throws SQLException {

        String passwordHash = UserDao.hashPassword(passwordText, user.getPasswordSalt());
        UserSession userSession = UserWebServices.authenticateAndCreateSession(webUtils, user.getEmail(), passwordHash, true);

        // Install login cookies at client
        UserWebServices.installCookies(webUtils, user.getId(), userSession.getChecksum(), true);

        // Add send to application action in response
        return "<app go='" + NetworkServices.getInitialHash(user.getId(), network.getId()) + "' />";

    }

    private static String createStripeCustomer (Boolean networkRequiresPayment, String cardToken) {

        String stripeId = null;
        if (networkRequiresPayment) {
            try { stripeId = StripeServices.createCustomer(cardToken); }
            catch (Exception e) {
                e.printStackTrace();
                throw new UIException(e.getMessage());
            }
        }

        return stripeId;
    }




    public static String signin (
            WebUtils webUtils,
            Integer networkId,
            String email,
            String passwordProvided,
            Boolean keep) throws SQLException {

        StringBuilder buf = new StringBuilder();

           // Validating email
        if (!StringUtils.isEmail(email))
            throw new UIException("Email is not valid");

        // Retrieving user to get salt
        User user = UserDao.getByEmail(null, email);

        // Validating password
        if (StringUtils.emptyIfNull(passwordProvided).isEmpty())
            throw new UIException("Please provide a password");

        // Validating credentials
        if (user == null)
            throw new UIException("Incorrect email address or password");

        // Creating hashed password for login
        String providedPasswordHash = UserDao.hashPassword(passwordProvided, user.getPasswordSalt());

        // Logging user in, creating a new user session
        UserSession userSession = UserWebServices.authenticateAndCreateSession(webUtils, email, providedPasswordHash, keep);
        if (userSession == null)
            throw new UIException("Incorrect email address or password");

        // Verify user is member of this network
        UserToNetwork userToNetwork = UserToNetworkDao.getByUserIdAndNetworkId(null, user.getId(), networkId);
        if (userToNetwork == null)
            throw new UIException("Please sign up first");

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

            // Add send to application action in response
            buf.append("<app go='" + NetworkServices.getInitialHash(user.getId(), networkId) + "' />");

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
            return HashRouting.profileQuestions(firstNetwork.getId());
        else
            return HashRouting.sharedItems(firstNetwork.getId());
    }

}
