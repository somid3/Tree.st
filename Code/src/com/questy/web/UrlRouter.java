package com.questy.web;

import com.questy.dao.NetworkAlphaSettingDao;
import com.questy.dao.NetworkDao;
import com.questy.domain.Network;
import com.questy.domain.NetworkAlphaSetting;
import com.questy.enums.NetworkAlphaSettingEnum;
import com.questy.utils.StringUtils;
import com.questy.utils.Vars;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UrlRouter {

    /**
     * Will log all route checks
     */
    private static boolean debug = true;

    private static Map<String, Integer> registeredDomains = new HashMap<String, Integer>();

    /**
     * Generating in-memory index to quickly identify which domain and path
     * belongs to which network
     */
    static {

        try {

            // Loading domains
            List<NetworkAlphaSetting> domainSettings = NetworkAlphaSettingDao.getBySettingEnum(null, NetworkAlphaSettingEnum.URL_DOMAIN);
            for (NetworkAlphaSetting domainSetting : domainSettings)
                registeredDomains.put(domainSetting.getValue(), domainSetting.getNetworkId());

        } catch (SQLException e) {

            e.printStackTrace();
        }

    }

    public static Network getNetworkByRootDomain(WebUtils webUtils) throws Exception  {

        String domain = webUtils.getRootDomain();

        // Ensuring domain is not that of the server's
        Network network = null;
        Integer networkId = registeredDomains.get( domain );

        if (networkId == null)
           network = null;
        else
            network = NetworkDao.getById(null, networkId);

        return network;
    }

    private static void debug (String message) {

        if (debug)
            System.out.println(UrlRouter.class.getName() + ": " +message);
    }




}
