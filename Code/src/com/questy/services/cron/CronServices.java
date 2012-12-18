package com.questy.services.cron;


import com.questy.dao.NetworkDao;
import com.questy.dao.SmartGroupDao;
import com.questy.dao.UserToSmartGroupDao;
import com.questy.domain.Network;
import com.questy.domain.SmartGroup;
import com.questy.domain.UserToSmartGroup;
import com.questy.enums.EmailNotificationRateEnum;
import com.questy.enums.SmartGroupVisibilityEnum;
import com.questy.helpers.UserScores;
import com.questy.services.QueryServices;
import com.questy.services.email.EmailServices;
import com.questy.xml.query.QueryXml;
import com.questy.xml.query.QueryXmlReader;

import java.util.*;

public class CronServices {

    public static void calledHourlyPopulateSmartGroups() throws Exception {

        QueryXml queryXml = null;

        // Retrieving all networks
        List<Network> networks = NetworkDao.getAll(null);

        UserScores scores = null;

        // Looping over each network
        for (Network network : networks) {

            // Retrieving all smart groups
            List<SmartGroup> groups = SmartGroupDao.getNonHiddenByNetworkIdAndLowestVisibility(null, network.getId(), SmartGroupVisibilityEnum.SHARED);

            // Looping over smart groups
            for (SmartGroup group : groups) {

                // Running search
                queryXml = QueryXmlReader.parseAndLoad(group.getQuery());
                QueryServices.createScores(network.getId(), group.getRef(), queryXml);

            }

        }

    }

    public static void notifyNewNonPrivateSmartGroupMembers (EmailNotificationRateEnum callingRate)  throws Exception {

        // Get all private networks
        List<Network> networks = NetworkDao.getAllByGlobal(null, false);

        // Loop through private networks
        for (Network network : networks) {

            // Select users to smart group mappings where the membership has been set since after the calling rate
            List<UserToSmartGroup> newMappings = UserToSmartGroupDao.getMembersByNetworkIdAndCreatedAfter (
                    null,
                    network.getId(),
                    callingRate.getBoundaryDate());

            // Remove duplicate users
            SmartGroup smartGroup = null;
            Set<Integer> userIds = new HashSet<Integer>();
            for (UserToSmartGroup newMapping : newMappings) {

                // Retrieving smart group while ensuring visibility is greater than private
                smartGroup = SmartGroupDao.getNonHiddenByNetworkIdAndRefAndLowestVisibility(null, network.getId(), newMapping.getSmartGroupRef(), SmartGroupVisibilityEnum.SHARED);

                // Ensure that only users of non-private smart groups are notified
                if (smartGroup != null)
                    userIds.add(newMapping.getUserId());
            }

            // Looping through each new mapped user
            for (Integer userId : userIds) {

                // Trigger the new smart group mapping email notification
                EmailServices.newSmartGroupMappings(callingRate, network.getId(), userId);

            }
        }
    }
}
