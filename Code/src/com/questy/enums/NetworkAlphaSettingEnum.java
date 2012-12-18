package com.questy.enums;

import com.questy.dao.NetworkAlphaSettingDao;
import com.questy.domain.NetworkAlphaSetting;

import java.sql.SQLException;

public enum NetworkAlphaSettingEnum {

    MANIFESTO_TITLE    (0, ""),
    MANIFESTO_CONTENT    (1, ""),

    /**
     * Description that appears under the network dashboard that displays an overall
     * community award of points
     */
    AWARD_DESCRIPTION (2, ""),

    /**
     * Path name used to identify a network from the root domain name
     */
    URL_PATH(3, null),



    VOCAB_USER_SINGULAR (1000, "User"),
    VOCAB_USER_PLURAL (1001, "People");




    private int id;
    private String ifNull;


    private NetworkAlphaSettingEnum(int id, String ifNull) {
        this.id = id;
        this.ifNull = ifNull;
    }

    public int getId() {
        return id;
    }

    public String getIfNull() {
        return ifNull;
    }

    public String getValueByNetwork (Integer networkId) throws SQLException {

        NetworkAlphaSetting setting = NetworkAlphaSettingDao.getByNetworkIdAndSetting(null, networkId, this);

        // If the setting is not set for the network, return the code's default
        if (setting == null)
            return this.getIfNull();

        return setting.getValue();
    }

    public Integer getNetworkIdByValue (String value) throws SQLException {

        NetworkAlphaSetting setting = NetworkAlphaSettingDao.getByValueAndSetting(null, value, this);

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

    public static void setValueByNetwork (Integer networkId) {

        // Retrieve setting

        // Does the setting exist?

            // Yes, update it

            // No, insert it


    }
}
