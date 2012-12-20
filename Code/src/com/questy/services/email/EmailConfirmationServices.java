package com.questy.services.email;

import com.questy.enums.UserAlphaSettingEnum;
import com.questy.enums.UserIntegerSettingEnum;
import com.questy.helpers.UIException;
import com.questy.utils.StringUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class EmailConfirmationServices {


    public static void beginEmailConfirmation (Integer userId) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Save email confirmation checksum for user
        String checksum = StringUtils.random();
        UserAlphaSettingEnum.EMAIL_CONFIRMATION_CHECKSUM.setValueByUserId(userId, checksum);

        /*
         * Set that the user has not confirmed its email address, this value is important
         * as the daily function will only email individuals who have not confirmed their
         * email yet
         */
        UserIntegerSettingEnum.IS_EMAIL_CONFIRMED.setValueByUserId(userId, 0);

        // Send first confirmation email twice
        sendConfirmationEmail(userId);

    }

    public static void sendConfirmationEmail (Integer userId) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Send email
        EmailServices.confirmationEmail(userId);

        // Update confirmation email count
        UserIntegerSettingEnum.NUMBER_OF_EMAIL_CONFIRMATION_EMAILS_SENT.incrementValueByUserId(userId, 1);

    }

    public static void confirmEmail (Integer userId, String providedChecksum) throws SQLException {

        // Validating
        if (userId == null) throw new UIException("User id not provided");
        if (providedChecksum == null) throw new UIException("Email confirmation checksum not provided");

        // Currently non-transactional
        Connection conn = null;

        // Retrieve email confirmation checksum
        String validChecksum = UserAlphaSettingEnum.EMAIL_CONFIRMATION_CHECKSUM.getValueByUserId(userId);

        // Check checksum
        if (!validChecksum.equals(providedChecksum))
            throw new UIException("Confirmation checksum failed");

        // Confirm user
        UserIntegerSettingEnum.IS_EMAIL_CONFIRMED.setValueByUserId(userId, 1);

    }

}
