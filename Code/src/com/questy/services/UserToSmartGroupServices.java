package com.questy.services;

import com.questy.dao.SmartGroupDao;
import com.questy.dao.SmartGroupResultDao;
import com.questy.dao.UserToSmartGroupDao;
import com.questy.domain.SmartGroup;
import com.questy.domain.SmartGroupResult;
import com.questy.domain.UserToSmartGroup;
import com.questy.enums.SmartGroupVisibilityEnum;
import com.questy.enums.UserToSmartGroupStateEnum;
import com.questy.helpers.SqlLimit;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserToSmartGroupServices extends ParentService {

    public static void changeState(UserToSmartGroupStateEnum applyState, Integer networkId, Integer smartGroupRef, Integer userId) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving user to smart group mapping
        UserToSmartGroup utsg = getOrCreateMapping(networkId, smartGroupRef, userId);

        // Is the user to smart group state already the state being toggled?
        if (utsg.getState() == applyState) {

            // Yes, then the state of the mapping to none
            UserToSmartGroupDao.updateStateByNetworkIdAndSmartGroupRefAndUserId(conn, networkId, smartGroupRef, userId, UserToSmartGroupStateEnum.NONE);

        } else {

            // No, then convert the mapping into that state
            UserToSmartGroupDao.updateStateByNetworkIdAndSmartGroupRefAndUserId(conn, networkId, smartGroupRef, userId, applyState);

        }

    }

    private static UserToSmartGroup getOrCreateMapping(Integer networkId, Integer smartGroupRef, Integer userId) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        UserToSmartGroup out = UserToSmartGroupDao.getByNetworkIdAndSmartGroupRefAndUserId(conn, networkId, smartGroupRef, userId);

        // If user has no mapping, then create one.
        if (out == null) {

            UserToSmartGroupDao.insert(conn, networkId, smartGroupRef, userId);

            out = UserToSmartGroupDao.getByNetworkIdAndSmartGroupRefAndUserId(conn, networkId, smartGroupRef, userId);
        }

        return out;
    }

    private static void mapUserAsMember(Integer networkId, Integer smartGroupRef, Integer userId) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Attempt to update the user as a member of the smart group
        Integer updates = UserToSmartGroupDao.updateMemberByNetworkIdAndSmartGroupRefAndUserId(conn, networkId, smartGroupRef, userId, true);

        // Was the update successful, that is, did a mapping already exist?
        if (updates == 0) {

            // No, create the new mapping
            UserToSmartGroupDao.insert(conn, networkId, smartGroupRef, userId);

            // Update that the user is a member of the smart group
            UserToSmartGroupDao.updateMemberByNetworkIdAndSmartGroupRefAndUserId(conn, networkId, smartGroupRef, userId, true);
        }
    }

    public static void createSmartGroupMappings (Integer networkId, Integer smartGroupRef) throws SQLException {

        // Retrieve smart group
        SmartGroup smartGroup = SmartGroupDao.getByNetworkIdAndRef(null, networkId, smartGroupRef);

        // Is this a private smart group? If so, skip it
        if (smartGroup.getVisibility() == SmartGroupVisibilityEnum.PRIVATE)
            return;

        // Retrieve smart group results
        List<SmartGroupResult> results = SmartGroupResultDao.getByNetworkIdAndRef(null, networkId, smartGroupRef, SqlLimit.ALL);

        // Update all user of the smart group mappings as non-members
        UserToSmartGroupDao.updateMemberByNetworkIdAndSmartGroupRef(null, networkId, smartGroupRef, false);

        // Create new mappings or update mappings as members
        for (SmartGroupResult result: results)
            UserToSmartGroupServices.mapUserAsMember(networkId, smartGroupRef, result.getUserId());

    }

    public static List<SmartGroup> getSmartGroups (List<UserToSmartGroup> userToSmartGroups) throws SQLException {

        List<SmartGroup> out = new ArrayList<SmartGroup>();

        for (UserToSmartGroup userToSmartGroup : userToSmartGroups)
            out.add(getSmartGroup(userToSmartGroup));

        return out;
    }

    public static SmartGroup getSmartGroup (UserToSmartGroup userToSmartGroup) throws SQLException {

        return SmartGroupDao.getByNetworkIdAndRef(null, userToSmartGroup.getNetworkId(), userToSmartGroup.getSmartGroupRef());

    }
}
