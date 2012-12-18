package com.questy.services.email;

import com.questy.dao.EmailConfirmationDao;
import com.questy.dao.UserDao;
import com.questy.domain.EmailConfirmation;
import com.questy.domain.User;
import com.questy.helpers.UIException;

import java.sql.Connection;
import java.sql.SQLException;

public class EmailConfirmationServices {


    public static void beginEmailConfirmation (Integer userId) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Create email confirmation object
        EmailConfirmationDao.insert(conn, userId);

        // Send first confirmation email twice
        sendConfirmationEmail(userId, 1);

    }

    public static void sendConfirmationEmail (Integer userId, Integer noOfEmails) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Send email
        for (int i = 0; i < noOfEmails; i++)
            EmailServices.confirmationEmail(userId);

        // Update confirmation details
        EmailConfirmationDao.updateSentEmailDetails(conn, userId);

    }

    public static void confirmEmail (Integer userId, String checksum) throws SQLException {

        // Validating
        if (userId == null) throw new UIException("User id not provided");
        if (checksum == null) throw new UIException("Email confirmation checksum not provided");

        // Currently non-transactional
        Connection conn = null;

        // Retrieve email confirmation
        EmailConfirmation ec = EmailConfirmationDao.getByUserIdAndChecksum(conn, userId, checksum);

        // Was the checksum correct?
        if (ec == null) {

            // No, send error
            throw new UIException("Confirmation checksum failed");

        } else {

            // Checking if email has already been confirmed
            if (ec.isConfirmed())
                return;

            // Yes, update confirmation details
            EmailConfirmationDao.updateConfirmedDetails(conn, userId, checksum);

        }

    }

}
