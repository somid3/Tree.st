package com.questy.services.email;


import com.questy.dao.EmailConfirmationDao;
import com.questy.dao.UserDao;
import com.questy.domain.EmailConfirmation;
import com.questy.domain.User;
import com.questy.domain.UserSession;
import com.questy.services.UserServices;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class EmailScheduledReminders {

    public static void calledDailyConfirmationEmail() throws SQLException {

        // Retrieve all non-confirmed email confirmations
        List<EmailConfirmation> nons = EmailConfirmationDao.getAllNonConfirmed(null);

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        Integer dayNow = calendar.get(Calendar.DAY_OF_YEAR);

        // Sending message every 2 days
        if (dayNow % 2 != 0)
            return;

        for (EmailConfirmation non : nons) {

            if (non.getMessageCount() < 3) {

                // Send a message every 5 hours
                EmailConfirmationServices.sendConfirmationEmail(non.getUserId(), 1);

            }

        }

    }

    public static void calledDailyPhotoUploadReminder() throws SQLException {

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        Integer dayNow = calendar.get(Calendar.DAY_OF_YEAR);

        // Sending message every 3 days
        if (dayNow % 5 != 0)
            return;

        // Retrieve all faceless users
        List<User> facelessUsers = UserDao.getAllFaceless(null);
        EmailConfirmation ec = null;

        for (User facelessUser : facelessUsers) {

            // Making sure user has confirmed his/her email
            ec = EmailConfirmationDao.getByUserId(null, facelessUser.getId());

            // Do not sent a message to users who have not confirmed their email
            if (ec == null || !ec.isConfirmed())
                continue;

            // Sending email
            // TODO: fix this, do send the photo upload reminders
            // TODO: fix this, do send the photo upload reminders
            // TODO: fix this, do send the photo upload reminders
            // TODO: fix this, do send the photo upload reminders
//            EmailServices.photoUpload(facelessUser.getId());

        }
    }
}
