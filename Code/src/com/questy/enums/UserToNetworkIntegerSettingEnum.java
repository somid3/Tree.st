package com.questy.enums;


import com.questy.dao.UserToNetworkIntegerSettingDao;
import com.questy.domain.UserToNetworkIntegerSetting;

import java.sql.SQLException;

/**
 * Integer based settings for each user within a network. For each type of
 * setting the user can have at most only one value for each network. If a user
 * within a network does not have any value set for the setting, then the setting's
 * default value is returned
 *
 */
public enum UserToNetworkIntegerSettingEnum {

    /*
     * Used for testing
     */
    TEST (0, 0),

    /*
     * Whether the user has requested to not receive any more email
     * notifications when a new user link is created
     */
    IS_UNSUBSCRIBED_FROM_NEW_USER_LINK_EMAIL_NOTIFICATIONS (101, 0),

    /*
     * Whether the user has requested to not receive any more email
     * notifications when it is auto-magically called to a new smart group
     */
    IS_UNSUBSCRIBED_FROM_NEW_SMART_GROUP_MAPPINGS_EMAIL_NOTIFICATIONS(102, 0),

    /**
     * Rate at which a member of a smart group will receive new shared item
     * notifications by email. The value of setting is defined by:
     *
     * {@link EmailNotificationRateEnum}
     *
     * To completely unsubscribe from all shared item
     * notifications the user can use the {@link EmailNotificationRateEnum#NEVER} rate.
     */
    NEW_SHARED_ITEM_DIGEST_EMAIL_RATE (200, EmailNotificationRateEnum.INSTANTLY.getId())



    ;



    private int id;
    private Integer ifNull;

    private UserToNetworkIntegerSettingEnum(int id, Integer ifNull) {
        this.id = id;
        this.ifNull = ifNull;
    }

    public int getId() {
        return id;
    }

    public Integer getIfNull() {
        return ifNull;
    }

    public Integer getValueByUserIdAndNetworkId (Integer userId, Integer networkId) throws SQLException {

        UserToNetworkIntegerSetting setting = UserToNetworkIntegerSettingDao.getByUserIdAndNetworkIdAndSettingEnum(null, userId, networkId, this);

        // If the setting is not set for the network, return the code's default
        if (setting == null)
            return this.getIfNull();

        return setting.getValue();
    }

    public Boolean getBooleanByUserIdAndNetworkId (Integer userId, Integer networkId) throws SQLException {

        Integer value = getValueByUserIdAndNetworkId(userId, networkId);

        return !(value == null || value == 0);
    }

    public void setValueByUserIdAndNetworkId (Integer userId, Integer networkId, Integer value) throws SQLException {

        // Attempt to get value
        UserToNetworkIntegerSetting setting = UserToNetworkIntegerSettingDao.getByUserIdAndNetworkIdAndSettingEnum(null, userId, networkId, this);

        // Does the value already exist?
        if (setting != null) {

            // Yes, update the value
            UserToNetworkIntegerSettingDao.updateByUserIdAndNetworkIdAndSetting(null, userId, networkId, this, value);

        } else {

            // No, insert the value
            UserToNetworkIntegerSettingDao.insert(null, userId, networkId, this, value);

        }

    }

    public void incrementValueByUserIdAndNetworkId (Integer userId, Integer networkId, Integer incrementBy) throws SQLException {

        // Get setting's value
        Integer value = this.getValueByUserIdAndNetworkId(userId, networkId);

        // Set value
        this.setValueByUserIdAndNetworkId(userId, networkId, value + incrementBy);

    }

    public void deleteByUserIdAndNetworkId (Integer userId, Integer networkId) throws SQLException  {

        UserToNetworkIntegerSettingDao.deleteByUserIdAndNetworkIdAndSettingEnum(null, userId, networkId, this);

    }


    public static UserToNetworkIntegerSettingEnum getById (Integer id) {

       if (id == null)
           return null;

       for (UserToNetworkIntegerSettingEnum enu : values()) {
           if (enu.getId() == id)
               return enu;
       }

       return null;
   }
}
