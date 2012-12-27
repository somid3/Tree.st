package com.questy.enums;


import com.questy.dao.UserAlphaSettingDao;
import com.questy.domain.UserAlphaSetting;

import java.sql.SQLException;

/**
 * Integer based settings for each user. For each type of
 * setting the user can have at most only one value. If a user
 * does not have any value set for the setting, then the setting's
 * default value is returned
 *
 */
public enum UserAlphaSettingEnum {

    // Email confirmation settings

    /*
     * Checksum used for the user to confirm its email
     */
    EMAIL_CONFIRMATION_CHECKSUM (100, ""),

    /*
     * Temporarily stores the email address the user wishes to update
     * as its main email contact. This is a temporary variable that
     * will remain until the user confirms the email address
     */
    CHANGE_EMAIL_TO (101, ""),

    ;

    private int id;
    private String ifNull;

    private UserAlphaSettingEnum(int id, String ifNull) {
        this.id = id;
        this.ifNull = ifNull;
    }

    public int getId() {
        return id;
    }

    public String getIfNull() {
        return ifNull;
    }

    public String getValueByUserId (Integer userId) throws SQLException {

        UserAlphaSetting setting = UserAlphaSettingDao.getByUserIdAndSettingEnum(null, userId, this);

        // If the setting is not set for the network, return the code's default
        if (setting == null)
            return this.getIfNull();

        return setting.getValue();
    }

    public void setValueByUserId (Integer userId, String value) throws SQLException {

        // Attempt to get value
        UserAlphaSetting setting = UserAlphaSettingDao.getByUserIdAndSettingEnum(null, userId, this);

        // Does the value already exist?
        if (setting != null) {

            // Yes, update the value
            UserAlphaSettingDao.updateByUserIdAndSettingEnum(null, userId, this, value);

        } else {

            // No, insert the value
            UserAlphaSettingDao.insert(null, userId, this, value);

        }

    }

    public void deleteByUserId (Integer userId) throws SQLException  {

        UserAlphaSettingDao.deleteByUserIdAndSettingEnum(null, userId, this);

    }

    public static UserAlphaSettingEnum getById (Integer id) {

       if (id == null)
           return null;

       for (UserAlphaSettingEnum enu : values()) {
           if (enu.getId() == id)
               return enu;
       }

       return null;
   }
}
