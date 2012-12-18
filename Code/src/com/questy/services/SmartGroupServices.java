package com.questy.services;

import com.questy.dao.*;
import com.questy.domain.Network;
import com.questy.domain.SmartGroup;
import com.questy.enums.SmartGroupVisibilityEnum;
import com.questy.helpers.UIException;

import java.sql.Connection;
import java.sql.SQLException;


public class SmartGroupServices extends ParentService  {

    public static Integer createSmartGroup (Integer networkId, Integer userId, String name, String query) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving network
        Network network = NetworkDao.getById(conn, networkId);

        // Validating
        if (network.isGlobal())
            throw new UIException("Smart groups not enabled for global networks");

        // Retrieving max ref
        Integer maxRef = SmartGroupDao.getMaxRefByNetworkId(conn, networkId);

        /*
         * Creating next ref, starting with 1 and not 0, is left as the
         * reference for random connections, from the faces displays, etc.
         */
        Integer nextRef = 1;
        if (maxRef != null)
            nextRef = maxRef + 1;

        // Inserting smart group
        SmartGroupDao.insert(conn, networkId, userId, nextRef, name, query);

        return nextRef;
    }

    public static SmartGroup getOrCreateSearchSmartGroup (Integer networkId, Integer userId) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        SmartGroup out = SmartGroupDao.getSearchByNetworkIdAndUserId(conn, networkId, userId);

        // If user has no search smart group, then create one.
        if (out == null) {

            // Creating smart search
            SmartGroupServices.createSmartGroup(networkId, userId, SmartGroupDao.SEARCH_NAME, null);

            // Retrieving smart search
            out = SmartGroupDao.getSearchByNetworkIdAndUserId(conn, networkId, userId);
        }

        return out;
    }


    public static void hideSmartGroup (Integer networkId, Integer smartGroupRef, Integer userId) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving smart group to be hidden
        SmartGroup group = SmartGroupDao.getByNetworkIdAndRef(conn,  networkId, smartGroupRef);

        // Checking that user has rights to hide smart group
        if (group.getUserId().equals(userId)) {

            // Hiding smart group
            SmartGroupDao.updateHiddenByNetworkIdAndRef(conn, networkId, smartGroupRef, true);

        }


    }

    public static void convertSearchToSmartGroup (
        Integer networkId,
        Integer smartGroupRef,
        String name,
        String description,
        SmartGroupVisibilityEnum visibility) throws SQLException {

        // Updating the smart group's name and details...
        SmartGroupDao.updateDetailsByNetworkIdAndRef(null, networkId, smartGroupRef, name, description, visibility);

        // Create the smart group mappings
        UserToSmartGroupServices.createSmartGroupMappings(networkId, smartGroupRef);

    }


}
