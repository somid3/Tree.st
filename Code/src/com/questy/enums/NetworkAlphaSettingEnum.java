package com.questy.enums;

import com.questy.dao.NetworkAlphaSettingDao;
import com.questy.dao.NetworkIntegerSettingDao;
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

    /**
     * Message that appears on main page of community, above the manifesto
     */
    START_MESSAGE(4, ""),

    /**
     * Layout that appears below the manifesto but is the main page of the community
     */
    START_BODY(5, ""),

    /**
     * System message in case there are outages to be expected for the community
     */
    SYSTEM_MESSAGE(6, ""),

    /**
     * Word used to describe a singular user
     */
    VOCAB_USER_SINGULAR (1000, "User"),

    /**
     * Word used to describe a plural user
     */
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

        NetworkAlphaSetting setting = NetworkAlphaSettingDao.getByNetworkIdAndSettingEnum(null, networkId, this);

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
}
