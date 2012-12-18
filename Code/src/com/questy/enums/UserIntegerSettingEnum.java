package com.questy.enums;


import com.questy.dao.UserIntegerSettingDao;
import com.questy.domain.UserIntegerSetting;

import java.sql.SQLException;

public enum UserIntegerSettingEnum {

    // Email confirmation settings

    /*
     * Whether the user has confirmed the main email address point of
     * contact
     */
    IS_EMAIL_CONFIRMED (1, 0),

    /*
     * Total number of email confirmation requests that the user has
     * received
     */
    NUMBER_OF_EMAIL_CONFIRMATION_EMAILS_SENT (2, 0),



    // Face photo settings

    /*
     * Number photo upload reminder emails that the user has received so far.
     * After a certain number of requests the user will stop receiving
     * photo upload requests
     */
    NUMBER_OF_FIRST_PHOTO_UPLOAD_EMAILS_SENT (2, 0),

    /*
     * Total number of photos the user has uploaded to the profile
     */
    NUMBER_OF_PHOTOS_UPLOADED (3, 0),

    /*
     * Whether the user has requested to not receive any more reminders
     * to upload his or her first photo
     */
    IS_UNSUBSCRIBED_FROM_FIRST_PHOTO_UPLOAD_EMAILS (4, 0);



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

        UserIntegerSetting setting = UserIntegerSettingDao.getByUserIdAndSetting(null, userId, this);

        // If the setting is not set for the network, return the code's default
        if (setting == null)
            return this.getIfNull();

        return setting.getValue();
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
