package com.questy.services.email;


import com.questy.dao.UserAlphaSettingDao;
import com.questy.dao.UserDao;
import com.questy.domain.User;
import com.questy.domain.UserAlphaSetting;
import com.questy.enums.UserAlphaSettingEnum;
import com.questy.enums.UserIntegerSettingEnum;
import com.questy.helpers.SqlLimit;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class EmailScheduledReminders {

    public static void calledDailyConfirmationEmail() throws SQLException {

        // Retrieve all non-confirmed email confirmations
        List<UserAlphaSetting> emailsToConfirm = UserAlphaSettingDao.getBySettingEnum(null, UserAlphaSettingEnum.EMAIL_TO_BE_CONFIRMED, SqlLimit.ALL);

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        Integer dayNow = calendar.get(Calendar.DAY_OF_YEAR);

        // Sending message every 2 days
        if (dayNow % 2 != 0)
            return;

        Integer emailCount = null;
        for (UserAlphaSetting emailToConfirm : emailsToConfirm) {

            // Ensure user has not received too many messages
            emailCount = UserIntegerSettingEnum.NUMBER_OF_EMAIL_CONFIRMATION_EMAILS_SENT.getValueByUserId(emailToConfirm.getUserId());

            if (emailCount < 5) {

                EmailConfirmationServices.sendEmailConfirmation(emailToConfirm.getUserId());

            }

        }

    }

    public static void calledDailyPhotoUploadReminder() throws SQLException {

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        Integer dayNow = calendar.get(Calendar.DAY_OF_YEAR);

        // Sending message every few days...
        if (dayNow % 3 != 0)
            return;

        // Retrieve all faceless users
        List<User> facelessUsers = UserDao.getAllFaceless(null);
        Boolean isAccountConfirmed = null;
        Integer remindersSent = null;

        for (User facelessUser : facelessUsers) {

            // Do not sent a message to users who have not confirmed their email
            isAccountConfirmed = UserIntegerSettingEnum.IS_ACCOUNT_CONFIRMED.getBooleanByUserId(facelessUser.getId());
            if (!isAccountConfirmed)
                continue;

            // Has the user received too many reminders?
            remindersSent = UserIntegerSettingEnum.NUMBER_OF_FIRST_PHOTO_UPLOAD_EMAILS_SENT.getValueByUserId(facelessUser.getId());
            if (remindersSent >= 5)
                continue;

            // Sending email
            EmailServices.firstPhotoUpload(facelessUser.getId());

            // Update count of reminders sent
            UserIntegerSettingEnum.NUMBER_OF_FIRST_PHOTO_UPLOAD_EMAILS_SENT.incrementValueByUserId(facelessUser.getId(), 1);

        }
    }
}
