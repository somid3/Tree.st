package com.questy.services.email;

import com.questy.dao.UserDao;
import com.questy.domain.Network;
import com.questy.domain.User;
import com.questy.domain.UserSession;
import com.questy.enums.RoleEnum;
import com.questy.enums.UserAlphaSettingEnum;
import com.questy.enums.UserIntegerSettingEnum;
import com.questy.helpers.SqlLimit;
import com.questy.helpers.UIException;
import com.questy.services.FlowRuleServices;
import com.questy.services.NetworkServices;
import com.questy.services.UserWebServices;
import com.questy.utils.StringUtils;
import com.questy.web.HashRouting;
import com.questy.web.WebUtils;

import javax.servlet.jsp.SkipPageException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * This class deals with confirming the mail email address a user defines. This is
 * the main point of contact for a user. This class also helps update the main
 * email address of a user
 *
 */
public class EmailConfirmationServices {

    /**
     * Used to confirm the email address a user supplied when joining
     * a network. This is used for new users, not for the process involved
     * in changing or updating a user's email address
     *
     * @throws SQLException
     */
    public static void beginEmailConfirmation (Integer userId, String emailToConfirm) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Save email confirmation checksum for user
        UserAlphaSettingEnum.EMAIL_CONFIRMATION_CHECKSUM.setValueByUserId(userId, StringUtils.random());

        // Saving email that needs to be confirmed
        UserAlphaSettingEnum.EMAIL_TO_BE_CONFIRMED.setValueByUserId(userId,  emailToConfirm);

        /**
         * Reset the number of email confirmations the user has received. This is reset for
         * situations where the user wishes to update its email address
         */
        UserIntegerSettingEnum.NUMBER_OF_EMAIL_CONFIRMATION_EMAILS_SENT.setValueByUserId(userId, 0);

        // Send first confirmation email
        sendEmailConfirmation(userId);

    }

    public static void sendEmailConfirmation(Integer userId) throws SQLException {

        // Check email checksum is defined
        String emailChecksum = UserAlphaSettingEnum.EMAIL_CONFIRMATION_CHECKSUM.getValueByUserId(userId);
        if (!emailChecksum.isEmpty()) {

            // Save email confirmation checksum for user
            UserAlphaSettingEnum.EMAIL_CONFIRMATION_CHECKSUM.setValueByUserId(userId, StringUtils.random());
        }

        // Update confirmation email count
        UserIntegerSettingEnum.NUMBER_OF_EMAIL_CONFIRMATION_EMAILS_SENT.incrementValueByUserId(userId, 1);

        // Send email
        EmailServices.confirmationEmail(userId);
    }

    public static void confirmEmail(WebUtils webUtils, Integer userId, String confirmationChecksum) throws SQLException, IOException, SkipPageException {

        // Validating
        if (userId == null) throw new UIException("User id not provided");
        if (confirmationChecksum == null) throw new UIException("Email confirmation checksum not provided");

        // Currently non-transactional
        Connection conn = null;

        // Retrieve email confirmation checksum
        String validChecksum = UserAlphaSettingEnum.EMAIL_CONFIRMATION_CHECKSUM.getValueByUserId(userId);

        // Check checksum
        if (!validChecksum.equals(confirmationChecksum))
            throw new UIException("Confirmation checksum failed");

        // Retrieve email that needs to be updated to user's email attribute
        String newEmail = UserAlphaSettingEnum.EMAIL_TO_BE_CONFIRMED.getValueByUserId(userId);
        if (StringUtils.isEmpty(newEmail))
            throw new UIException("No email needs to be confirmed");

        // Updating user's email address
        UserDao.updateEmailByUserId(conn, userId, newEmail);

        // Clearing the email to be confirmed setting
        UserAlphaSettingEnum.EMAIL_TO_BE_CONFIRMED.deleteByUserId(userId);

        // Clearing the email checksum setting
        UserAlphaSettingEnum.EMAIL_CONFIRMATION_CHECKSUM.deleteByUserId(userId);

        // Confirm account
        UserIntegerSettingEnum.IS_ACCOUNT_CONFIRMED.setValueByUserId(userId, 1);

        // Email confirmation was successful, retrieve user
        User user = UserDao.getById(null, userId);

        Boolean persistent = false;

        // Login user persistently
        UserSession userSession = UserWebServices.authenticateAndCreateSession(webUtils, user.getEmail(), user.getPasswordHash(), persistent);

        // Install login cookies at client
        UserWebServices.installCookies(webUtils, user.getId(), userSession.getChecksum(), persistent);

        // Retrieving user's initial hash
        String goHash = UserWebServices.getInitialHash(user.getId());

        // Sending user to application
        webUtils.redirect("/d/app/" + goHash);
    }

}
