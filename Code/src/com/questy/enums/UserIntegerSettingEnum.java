package com.questy.enums;


import com.questy.dao.UserIntegerSettingDao;
import com.questy.domain.UserIntegerSetting;

import java.sql.SQLException;

/**
 * Integer based settings for each user. For each type of
 * setting the user can have at most only one value. If a user
 * does not have any value set for the setting, then the setting's
 * default value is returned
 *
 */
public enum UserIntegerSettingEnum {

    TEST (0, 0),

    // Email confirmation settings

    /*
     * Whether the user has confirmed the main email address point of
     * contact
     */
    IS_ACCOUNT_CONFIRMED                                        (100, 0),

    /*
     * Total number of email confirmation requests that the user has
     * received
     */
    NUMBER_OF_EMAIL_CONFIRMATION_EMAILS_SENT                    (101, 0),



    // Face photo settings

    /*
     * Number photo upload reminder emails that the user has received so far.
     * After a certain number of requests the user will stop receiving
     * photo upload requests
     */
    NUMBER_OF_FIRST_PHOTO_UPLOAD_EMAILS_SENT                    (200, 0),

    /*
     * Whether the user has requested to not receive any more reminders
     * to upload his or her first photo
     */
    IS_UNSUBSCRIBED_FROM_FIRST_PHOTO_UPLOAD_EMAILS              (201, 0),

    /*
     * Total number of photos the user has uploaded to the profile
     */
    NUMBER_OF_PHOTOS_UPLOADED                                   (202, 0),



    /**
     * Tip that appears on top of the shared feed area informing the user that
     * shared items can be created only from within a smart group
     */
    TIP_SHARED_ITEMS_HOW                                        (1000, 1),

    /**
     * Tip on top of all the shared groups that informs a user that the search
     * tool needs to be used to create a smart group
     */
    TIP_SMART_GROUPS_CREATE                                     (1001, 1),

    /**
     * Tip on top of all the questions that informs the user how to go about
     * creating his or her profile
     */
    TIP_QUESTIONS_HOW                                           (1002, 1),

    /**
     * Tip on top of the messages dialog that informs users how to use
     * the messaging system
     */
    TIP_MESSAGES_HOW                                            (1003, 1),

    ;


    private int id;
    private Integer ifNull;

    private UserIntegerSettingEnum(int id, Integer ifNull) {
        this.id = id;
        this.ifNull = ifNull;
    }

    public int getId() {
        return id;
    }

    public Integer getIfNull() {
        return ifNull;
    }

    public Integer getValueByUserId (Integer userId) throws SQLException {

        UserIntegerSetting setting = UserIntegerSettingDao.getByUserIdAndSettingEnum(null, userId, this);

        // If the setting is not set for the network, return the code's default
        if (setting == null)
            return this.getIfNull();

        return setting.getValue();
    }

    public Boolean getBooleanByUserId (Integer userId) throws SQLException {

        Integer value = getValueByUserId(userId);

        return !(value == null || value == 0);
    }

    public void setValueByUserId (Integer userId, Integer value) throws SQLException {

        // Attempt to get value
        UserIntegerSetting setting = UserIntegerSettingDao.getByUserIdAndSettingEnum(null, userId, this);

        // Does the value already exist?
        if (setting != null) {

            // Yes, update the value
            UserIntegerSettingDao.updateByUserIdAndSettingEnum(null, userId, this, value);

        } else {

            // No, insert the value
            UserIntegerSettingDao.insert(null, userId, this, value);

        }

    }

    public void incrementValueByUserId (Integer userId, Integer incrementBy) throws SQLException {

        // Get setting's value
        Integer value = this.getValueByUserId(userId);

        // Set value
        this.setValueByUserId(userId, value + incrementBy);

    }

    public void deleteByUserId (Integer userId) throws SQLException  {

        UserIntegerSettingDao.deleteByUserIdAndSettingEnum(null, userId, this);

    }


    public static UserIntegerSettingEnum getById (Integer id) {

       if (id == null)
           return null;

       for (UserIntegerSettingEnum enu : values()) {
           if (enu.getId() == id)
               return enu;
       }

       return null;
   }
}
