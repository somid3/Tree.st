package com.questy.enums;

import com.questy.dao.NetworkIntegerSettingDao;
import com.questy.dao.UserIntegerSettingDao;
import com.questy.domain.NetworkIntegerSetting;

import java.sql.SQLException;

public enum NetworkIntegerSettingEnum {

    NETWORK_JOIN_POINTS_PER             (0, 5),

    SHARED_ITEM_POINTS_PER              (200, 10),
    SHARED_ITEM_DISPLAY_CREATED_ON      (201, 1),
    SHARED_ITEMS_PER_FIVE_MINUTES       (202, 1),
    SHARED_ITEMS_PER_HOUR               (203, 5),

    SHARED_ITEMS_PER_DAY                (204, 10),

    SHARED_COMMENT_POINTS_PER           (300, 5),
    SHARED_COMMENT_DISPLAY_CREATED_ON   (301, 1),
    SHARED_COMMENTS_PER_MINUTE          (302, 1),
    SHARED_COMMENTS_PER_HOUR            (303, 5),
    SHARED_COMMENTS_PER_DAY             (304, 10),

    USER_LINK_POINTS_PER                (400, 10),
    USER_LINKS_PER_MINUTE               (401, 3),
    USER_LINKS_PER_HOUR                 (402, 60),
    USER_LINKS_PER_DAY                  (403, 120),

    SMART_RESULTS_DISPLAY_SINCE         (500, 1);


    private int id;
    private Integer ifNull;

    private NetworkIntegerSettingEnum(int id, Integer ifNull) {
        this.id = id;
        this.ifNull = ifNull;
    }

    public int getId() {
        return id;
    }

    public Integer getIfNull() {
        return ifNull;
    }

    public Integer getValueByNetwork (Integer networkId) throws SQLException {

        NetworkIntegerSetting setting = NetworkIntegerSettingDao.getByNetworkIdAndSettingEnum(null, networkId, this);

        // If the setting is not set for the network, return the code's default
        if (setting == null)
            return this.getIfNull();

        return setting.getValue();
    }

    public static NetworkIntegerSettingEnum getById (Integer id) {

       if (id == null)
           return null;

       for (NetworkIntegerSettingEnum enu : values()) {
           if (enu.getId() == id)
               return enu;
       }

       return null;
    }

    public void setValueByUserId (Integer networkId, Integer value) throws SQLException {

        // Attempt to get value
        NetworkIntegerSetting setting = NetworkIntegerSettingDao.getByNetworkIdAndSettingEnum(null, networkId, this);

        // Does the value already exist?
        if (setting != null) {

            // Yes, update the value
            NetworkIntegerSettingDao.updateByNetworkIdAndSettingEnum(null, networkId, this, value);

        } else {

            // No, insert the value
            NetworkIntegerSettingDao.insert(null, networkId, this, value);

        }

    }
}
