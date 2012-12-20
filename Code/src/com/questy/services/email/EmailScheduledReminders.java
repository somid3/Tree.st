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

        // Sending message every 3 days
        if (dayNow % 5 != 0)
            return;

        // Retrieve all faceless users
        List<User> facelessUsers = UserDao.getAllFaceless(null);
        Boolean isEmailConfirmed = null;

        for (User facelessUser : facelessUsers) {

            // Making sure user has confirmed his/her email
            isEmailConfirmed = UserIntegerSettingEnum.IS_EMAIL_CONFIRMED.getBooleanByUserId(facelessUser.getId());

            // Do not sent a message to users who have not confirmed their email
            if (!isEmailConfirmed)
                continue;

            // Sending email
            EmailServices.photoUpload(facelessUser.getId());

        }
    }
}
