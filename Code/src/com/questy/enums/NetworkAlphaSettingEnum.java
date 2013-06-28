package com.questy.enums;

import com.questy.dao.NetworkAlphaSettingDao;
import com.questy.domain.NetworkAlphaSetting;
import com.questy.web.HtmlDesign;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum NetworkAlphaSettingEnum {

    /**
     * Path name used to identify a network from the root domain name
     */
    @Deprecated
    URL_PATH                            (3, null),

    /**
     * Message that appears on main page of community, above the manifesto
     */
    START_MESSAGE                       (4, ""),

    /**
     * Layout that appears below the manifesto but is the main page of the community
     */
    START_BODY                          (5, ""),

    /**
     * Domain used to identify a particular network
     */
    URL_DOMAIN                          (6, null),

    /**
     * Jsp file name of the custom landing page of the network
     */
    CUSTOM_LANDING                      (7, null),

    /**
     * Word used to describe a singular user
     */
    VOCAB_USER_SINGULAR                 (1000, "Member"),

    /**
     * Word used to describe a plural user
     */
    VOCAB_USER_PLURAL                   (1001, "Members"),

    /**
     * Vocabulary used to display up vote something
     */
    SHARED_VOTE_UP_VOCAB                (2000, "Like"),

    /**
     * Vocabulary used to display down vote something
     */
    SHARED_VOTE_DOWN_VOCAB              (2001, "Dislike"),

    /**
     * Top header color
     */
    UI_HEADER_BACKGROUND_COLOR          (10001, HtmlDesign.headerColor),

    ;



    private int id;
    private String ifNull;


    private NetworkAlphaSettingEnum(int id, String ifNull) {
        this.id = id;
        this.ifNull = ifNull;
    }

    public int getId() {
        return id;
    }


    public String getIfNullValue(NetworkAlphaSetting setting) {

        // If the setting is not set for the network, return the code's default
        if (setting == null)
            return this.ifNull;
        else
            return  setting.getValue();
    }

    public String getValueByNetworkId(Integer networkId) throws SQLException {

        NetworkAlphaSetting setting = NetworkAlphaSettingDao.getByNetworkIdAndSettingEnum(null, networkId, this);

        return getIfNullValue(setting);
    }

    public Integer getNetworkIdByValue (String value) throws SQLException {

        NetworkAlphaSetting setting = NetworkAlphaSettingDao.getByValueAndSettingEnum(null, value, this);

        if (setting == null)
            return null;

        return setting.getNetworkId();
    }

    public static NetworkAlphaSettingEnum getById (Integer id) {

       if (id == null)
           return null;

       for (NetworkAlphaSettingEnum enu : values()) {
           if (enu.getId() == id)
               return enu;
       }

       return null;
    }


    public void setValueByNetworkId(Integer networkId, String value) throws SQLException {

        // Attempt to get value
        NetworkAlphaSetting setting = NetworkAlphaSettingDao.getByNetworkIdAndSettingEnum(null, networkId, this);

        // Does the value already exist?
        if (setting != null) {

            // Yes, update the value
            NetworkAlphaSettingDao.updateByNetworkIdAndSettingEnum(null, networkId, this, value);

        } else {

            // No, insert the value
            NetworkAlphaSettingDao.insert(null, networkId, this, value);

        }

    }

    /**
    * Returns a map of all the network integer settings
    */
    public static Map<NetworkAlphaSettingEnum, String> getMapByNetworkId(Integer networkId) throws SQLException {

        // Retrieving all defined network settings and placing on map
        List<NetworkAlphaSetting> settings = NetworkAlphaSettingDao.getByNetworkId(null, networkId);
        Map<NetworkAlphaSettingEnum, NetworkAlphaSetting> settingsMap = new HashMap<NetworkAlphaSettingEnum, NetworkAlphaSetting>();
        for (NetworkAlphaSetting setting : settings)
        settingsMap.put(setting.getSettingEnum(), setting);

        // For each possible setting, place the set value or the default code value
        Map<NetworkAlphaSettingEnum, String> out = new HashMap<NetworkAlphaSettingEnum, String>();
        for (NetworkAlphaSettingEnum value : values()) {

        // Adding setting value
        out.put(

            // Adding setting enum into map as key
            value,

            // Adding setting value with default as backup
            value.getIfNullValue(
              settingsMap.get(value))
            );

        }

        return out;
    }
}
