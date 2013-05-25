package com.questy.enums;

import com.questy.dao.NetworkIntegerSettingDao;
import com.questy.domain.NetworkIntegerSetting;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum NetworkIntegerSettingEnum {

    // Number of points a user gains by viewing others
    NETWORK_JOIN_POINTS_PER             (0, 5),

    // If value is 1, network will display all user emails as text
    NETWORK_DISPLAY_TEXT_EMAILS         (1, 0),

    SHARED_ITEM_POINTS_PER              (200, 10),
    SHARED_ITEM_DISPLAY_CREATED_ON      (201, 1),
    SHARED_ITEMS_PER_FIVE_MINUTES       (202, 2),
    SHARED_ITEMS_PER_HOUR               (203, 6),
    SHARED_ITEMS_PER_DAY                (204, 10),

    SHARED_COMMENT_POINTS_PER           (300, 5),
    SHARED_COMMENT_DISPLAY_CREATED_ON   (301, 1),
    SHARED_COMMENTS_PER_MINUTE          (302, 1),
    SHARED_COMMENTS_PER_HOUR            (303, 10),
    SHARED_COMMENTS_PER_DAY             (304, 20),

    SHARED_ITEM_DISPLAY_UP_VOTES        (305, 1),
    SHARED_ITEM_DISPLAY_DOWN_VOTES      (306, 1),
    SHARED_COMMENTS_DISPLAY_UP_VOTES    (307, 1),
    SHARED_COMMENTS_DISPLAY_DOWN_VOTES  (308, 1),

    USER_LINK_POINTS_PER                (400, 10),
    USER_LINKS_PER_MINUTE               (401, 3),
    USER_LINKS_PER_HOUR                 (402, 60),
    USER_LINKS_PER_DAY                  (403, 120),

    SMART_RESULTS_DISPLAY_SINCE         (500, 1),

    /**
     * Regular members of a community can not create "shared" smart groups
     * that contain more than X number of members within them
     */
    SMART_GROUP_RESULTS_LIMIT           (600, 300),

    /**
     * If set to a value other than zero, then collect mode will be enabled, in this mode
     * the only functionality of the community is to gather user details. User's can not
     * search for one another or create smart groups
     */
    MODE_COLLECT_ONLY                   (9000, 0),

    /**
     * Allows new users who join this community to not need to confirm their emails
     * the first time they join this network
     */
    MODE_NO_CONFIRM                     (9001, 0),

    UI_HAS_BACKGROUND                   (10000, 0),
    UI_HAS_ICON                         (10001, 0),
    UI_HAS_LOGO                         (10002, 0)

    ;

    private int id;
    private Integer ifNull;

    private NetworkIntegerSettingEnum(int id, Integer ifNull) {
        this.id = id;
        this.ifNull = ifNull;
    }

    public int getId() {
        return id;
    }

    public Integer getIfNullValue(NetworkIntegerSetting setting) {

        // If the setting is not set for the network, return the code's default
        if (setting == null)
            return this.ifNull;
        else
            return  setting.getValue();
    }

    public Integer getValueByNetworkId(Integer networkId) throws SQLException {

        NetworkIntegerSetting setting = NetworkIntegerSettingDao.getByNetworkIdAndSettingEnum(null, networkId, this);

        return getIfNullValue(setting);
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

    /**
     * Returns a map of all the network integer settings
     */
    public static Map<NetworkIntegerSettingEnum, Integer> getMapByNetworkId(Integer networkId) throws SQLException {

        // Retrieving all defined network settings and placing on map
        List<NetworkIntegerSetting> settings = NetworkIntegerSettingDao.getByNetworkId(null, networkId);
        Map<NetworkIntegerSettingEnum, NetworkIntegerSetting> settingsMap = new HashMap<NetworkIntegerSettingEnum, NetworkIntegerSetting>();
        for (NetworkIntegerSetting setting : settings)
            settingsMap.put(setting.getSettingEnum(), setting);

        // For each possible setting, place the set value or the default code value
        Map<NetworkIntegerSettingEnum, Integer> out = new HashMap<NetworkIntegerSettingEnum, Integer>();
        for (NetworkIntegerSettingEnum value : values()) {

            // Adding setting value
            out.put(

                // Adding setting enum into map as key
                value,

                // Adding setting value with default as backup
                value.getIfNullValue(
                    settingsMap.get(value)
                )

            );

        }

        return out;
    }


    public Boolean getBooleanByNetworkId (Integer networkId) throws SQLException {

        Integer value = getValueByNetworkId(networkId);

        return !(value == null || value == 0);
    }

    public void setValueByNetworkId (Integer networkId, Integer value) throws SQLException {

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
