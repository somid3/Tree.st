package com.questy.services;

import com.questy.dao.PasswordResetDao;
import com.questy.dao.UserDao;
import com.questy.domain.PasswordReset;
import com.questy.domain.User;
import com.questy.helpers.UIException;
import com.questy.services.email.EmailServices;
import com.questy.utils.StringUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class PasswordResetServices extends ParentService {

    public static void begin(String email) throws SQLException {

        // Validate email
        if (!StringUtils.isEmail(email))
            throw new UIException ("Email is not valid");

        // Currently non-transactional
        Connection conn = null;

        // Attempt to retrieve user
        User user = UserDao.getByEmail(conn,  email);

        // Does such a email exist?
        if (user == null) {

            // No, send user not found email
            EmailServices.passwordResetNotFound(email);

        } else {

            // Create new password reset
            Integer id = PasswordResetDao.insert(conn, user.getId());

            // Retrieve new password rest object
            PasswordReset reset = PasswordResetDao.getById(conn, id);

            // Send password reset email
            EmailServices.passwordReset(user.getId(), reset.getChecksum());
        }

    }

}
