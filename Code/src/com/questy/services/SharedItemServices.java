package com.questy.services;

import com.questy.dao.NetworkDao;
import com.questy.dao.SharedItemDao;
import com.questy.dao.UserToNetworkDao;
import com.questy.domain.Network;
import com.questy.domain.SharedItem;
import com.questy.domain.UserToNetwork;
import com.questy.enums.NetworkIntegerSettingEnum;
import com.questy.enums.RoleEnum;
import com.questy.helpers.Tuple;
import com.questy.helpers.UIException;
import com.questy.services.email.EmailServices;
import com.questy.utils.DateUtils;
import com.questy.utils.Vars;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class SharedItemServices extends ParentService  {

    public static Tuple<Integer, Integer> add (
            Integer networkId,
            Integer userId,
            Integer smartGroupRef,
            String text) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Getting network settings
        Map<NetworkIntegerSettingEnum, Integer> networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(networkId);

        // Validating minimum length
        if (text.length() < networkIntegerSettings.get(NetworkIntegerSettingEnum.SHARED_ITEM_MIN_LENGTH))
            throw new UIException("Message is too short");

        // Validating max length
        if (text.length() > networkIntegerSettings.get(NetworkIntegerSettingEnum.SHARED_ITEM_MAX_LENGTH))
            throw new UIException("Message is too long");

        // Validating for timing attacks
        if (Vars.enableTimelocks) {

            // Validating for minute attack
            Integer per5Minutes = networkIntegerSettings.get(NetworkIntegerSettingEnum.SHARED_ITEMS_PER_FIVE_MINUTES);
            Integer last5Minutes = SharedItemDao.countByNetworkIdAndUserIdAndCreatedAfter(conn, networkId, userId, DateUtils.minutesAgo(5));
            if (last5Minutes >= per5Minutes) throw new UIException("Limit: " + per5Minutes + " messages every 5 minutes");

            // Validating for hour attack
            Integer perHour = networkIntegerSettings.get(NetworkIntegerSettingEnum.SHARED_ITEMS_PER_HOUR);
            Integer lastHour = SharedItemDao.countByNetworkIdAndUserIdAndCreatedAfter(conn, networkId, userId, DateUtils.hoursAgo(1));
            if (lastHour >= perHour) throw new UIException("Limit: " + perHour + " messages per hour");

            // Validating for day attack
            Integer perDay = networkIntegerSettings.get(NetworkIntegerSettingEnum.SHARED_ITEMS_PER_DAY);
            Integer lastDay = SharedItemDao.countByNetworkIdAndUserIdAndCreatedAfter(conn, networkId, userId, DateUtils.daysAgo(1));
            if (lastDay >= perDay) throw new UIException("Limit: " + perDay + " messages per day");
        }

        // Retrieving points per shared item
        Integer pointsPerSharedItem = networkIntegerSettings.get(NetworkIntegerSettingEnum.SHARED_ITEM_POINTS_PER);

        // Check that the from user has the required number of points
        UserToNetwork fromUserToNetwork = UserToNetworkDao.getByUserIdAndNetworkId(conn, userId, networkId);
        if (fromUserToNetwork.getCurrentPoints() < (pointsPerSharedItem * -1)) {

            // User does not have enough points, return null
            throw new UIException("Not enough points");
        }

        // Retrieving max ref
        Integer maxRef = SharedItemDao.getMaxRefByNetworkIdAndSmartGroupRef(conn, networkId, smartGroupRef);

        /* Creating next ref, starting with 1 and not 0 */
        Integer nextRef = 1;
        if (maxRef != null)
            nextRef = maxRef + 1;

        // Insert shared item
        SharedItemDao.insert(conn, networkId, userId, smartGroupRef, nextRef, text);

        // Increment one point for author
        UserToNetworkDao.incrementPointsByUserIdAndNetworkId(conn, userId, networkId, pointsPerSharedItem);

        // Sending notification emails
        EmailServices.newSharedItemForActiveUserToSmartGroups(networkId, smartGroupRef, nextRef);

        Tuple<Integer, Integer> out = new Tuple<Integer, Integer>(nextRef, pointsPerSharedItem);

        return out;
    }

    public static Tuple<Integer, Integer> hide(Integer networkId, Integer userId, Integer smartGroupRef, Integer sharedItemRef) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving shared item to be deleted
        SharedItem sharedItem = SharedItemDao.getByNetworkIdAndSmartGroupRefAndRef(conn, networkId, smartGroupRef, sharedItemRef);

        // Retrieving user to network relationship
        UserToNetwork userToNetwork = UserToNetworkDao.getByUserIdAndNetworkId(null, userId, networkId);

        Boolean deletedByAuthor = false;
        Boolean deletedByModerator = false;

        if (sharedItem.getUserId().equals(userId))
            deletedByAuthor = true;

        if (userToNetwork.getRole().isHigherThan(RoleEnum.MEMBER))
            deletedByModerator = true;

        // Validating authority to delete
        if (!(deletedByAuthor || deletedByModerator))
            throw new UIException("User can not hide shared item");


        // Hide shared item
        SharedItemDao.updateHiddenByNetworkIdAndSmartGroupRefAndRef(conn, networkId, smartGroupRef, sharedItemRef, true);

        // Decrease points
        Integer pointsPer = 0;
        if (deletedByAuthor) {

            // Retrieving points per shared item
            pointsPer = NetworkIntegerSettingEnum.SHARED_ITEM_POINTS_PER.getValueByNetworkId(networkId);
            pointsPer = pointsPer * -1;
            UserToNetworkDao.incrementPointsByUserIdAndNetworkId(conn, userId, networkId, pointsPer);
        }

        Tuple<Integer, Integer> out = new Tuple<Integer, Integer>(sharedItemRef, pointsPer);

        return out;
    }

}
