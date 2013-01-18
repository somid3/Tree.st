package com.questy.services.cron;


import com.questy.dao.NetworkDao;
import com.questy.dao.SmartGroupDao;
import com.questy.dao.UserSessionDao;
import com.questy.dao.UserToSmartGroupDao;
import com.questy.domain.Network;
import com.questy.domain.SmartGroup;
import com.questy.domain.UserToSmartGroup;
import com.questy.enums.EmailNotificationRateEnum;
import com.questy.enums.SmartGroupVisibilityEnum;
import com.questy.services.QueryServices;
import com.questy.services.email.EmailServices;
import com.questy.utils.DateUtils;
import com.questy.xml.query.QueryXml;
import com.questy.xml.query.QueryXmlReader;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CronServices {

    public static void calledHourlyPopulateSmartGroups() throws Exception {

        QueryXml queryXml = null;

        // Retrieving all networks
        List<Network> networks = NetworkDao.getAll(null);

        // Looping over each network
        for (Network network : networks) {

            // Retrieving all smart groups
            List<SmartGroup> groups = SmartGroupDao.getNonHiddenByNetworkIdAndLowestVisibility(null, network.getId(), SmartGroupVisibilityEnum.SHARED);

            // Looping over smart groups
            for (SmartGroup group : groups) {

                // Running search to create scores and add new mappings
                queryXml = QueryXmlReader.parseAndLoad(group.getQuery());
                QueryServices.createScoresAndMappings(network.getId(), group.getSmartGroupRef(), queryXml);

            }

            // Delete all inactive user to smart group mappings
            UserToSmartGroupDao.deleteInactiveByNetworkId(null, network.getId());

        }

    }

    /**
     * Called at a variable calling rate, notifies users of each network the new smart
     * groups to which they have been auto-magically added
     *
     * @param callingRate
     * @throws Exception
     */
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
                EmailServices.newSmartGroupMappings(network.getId(), userId, callingRate);

            }
        }
    }

    /**
     * Called at a variable calling rate, creates a unique digest message for each user
     * in a network based on the shared items of the user's active smart groups
     *
     * @throws SQLException
     */
    public static void sharedItemDigest (EmailNotificationRateEnum callingRate) throws SQLException {

        // Retrieving all networks
        List<Network> networks = NetworkDao.getAll(null);

        // Looping over each network
        for (Network network : networks) {

            EmailServices.sharedItemDigest(network.getId(), callingRate);

        }
    }

    /**
     * On a daily basis removes user sessions that are not active
     *
     * @throws SQLException
     */
    public static void calledDailyUserSessionCleanUp() throws SQLException {

        // Removing non-persistent user sessions that have not been updated in the last 7 days
        UserSessionDao.deleteByPersistentAndUpdatedBefore(null, false, DateUtils.daysAgo(7));

        // Removing persistent user sessions that have not been updated in the last 60 days
        UserSessionDao.deleteByPersistentAndUpdatedBefore(null, true, DateUtils.daysAgo(60));
    }
}
