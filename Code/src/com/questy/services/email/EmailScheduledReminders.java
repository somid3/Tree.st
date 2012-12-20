package com.questy.services.email;


import com.questy.dao.UserDao;
import com.questy.dao.UserIntegerSettingDao;
import com.questy.domain.User;
import com.questy.domain.UserIntegerSetting;
import com.questy.enums.UserIntegerSettingEnum;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class EmailScheduledReminders {

    public static void calledDailyConfirmationEmail() throws SQLException {

        // Retrieve all non-confirmed email confirmations
        List<UserIntegerSetting> nonConfirmed = UserIntegerSettingDao.getBySettingEnumAndValue(null, UserIntegerSettingEnum.IS_EMAIL_CONFIRMED, 0);

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        Integer dayNow = calendar.get(Calendar.DAY_OF_YEAR);

        // Sending message every 2 days
        if (dayNow % 2 != 0)
            return;

        Integer emailCount = null;
        for (UserIntegerSetting non : nonConfirmed) {

            // Ensure user has not received too many messages
            emailCount = UserIntegerSettingEnum.NUMBER_OF_EMAIL_CONFIRMATION_EMAILS_SENT.getValueByUserId(non.getUserId());

            if (emailCount < 5) {

                EmailConfirmationServices.sendConfirmationEmail(non.getUserId());

            }

        }

    }

    public static void calledDailyPhotoUploadReminder() throws SQLException {

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(new Date());
        Integer dayNow = calendar.get(Calendar.DAY_OF_YEAR);

        // Sending message every 5 days
        if (dayNow % 5 != 0)
            return;

        // Retrieve all faceless users
        List<User> facelessUsers = UserDao.getAllFaceless(null);
        Boolean isEmailConfirmed = null;
        Integer remindersSent = null;

        for (User facelessUser : facelessUsers) {

            // Do not sent a message to users who have not confirmed their email
            isEmailConfirmed = UserIntegerSettingEnum.IS_EMAIL_CONFIRMED.getBooleanByUserId(facelessUser.getId());
            if (!isEmailConfirmed)
                continue;

            // Has the user received too many reminders?
            remindersSent = UserIntegerSettingEnum.NUMBER_OF_FIRST_PHOTO_UPLOAD_EMAILS_SENT.getValueByUserId(facelessUser.getId());
            if (remindersSent >= 10)
                continue;

            // Sending email
            EmailServices.firstPhotoUpload(facelessUser.getId());

            // Update count of reminders sent
            UserIntegerSettingEnum.NUMBER_OF_FIRST_PHOTO_UPLOAD_EMAILS_SENT.incrementValueByUserId(facelessUser.getId(), 1);

        }
    }
}
