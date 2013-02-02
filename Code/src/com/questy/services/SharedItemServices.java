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

import java.sql.Connection;
import java.sql.SQLException;

public class SharedItemServices extends ParentService  {

    public static Tuple<Integer, Integer> add (Integer networkId, Integer userId, Integer smartGroupRef, String text) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Validating
        if (text == null || text.isEmpty())
            throw new UIException("Shared item text can not be empty");

        // Validating for timing attacks
        {
            // Validating for minute attack
            Integer perMinute = NetworkIntegerSettingEnum.SHARED_ITEMS_PER_FIVE_MINUTES.getValueByNetworkId(networkId);
            Integer lastMinute = SharedItemDao.countByNetworkIdAndUserIdAndCreatedAfter(conn, networkId, userId, DateUtils.minutesAgo(5));
            if (lastMinute >= perMinute) throw new UIException("Limit: " + perMinute + " messages every 5 minutes");

            // Validating for hour attack
            Integer perHour = NetworkIntegerSettingEnum.SHARED_ITEMS_PER_HOUR.getValueByNetworkId(networkId);
            Integer lastHour = SharedItemDao.countByNetworkIdAndUserIdAndCreatedAfter(conn, networkId, userId, DateUtils.hoursAgo(1));
            if (lastHour >= perHour) throw new UIException("Limit: " + perHour + " messages per hour");

            // Validating for day attack
            Integer perDay = NetworkIntegerSettingEnum.SHARED_ITEMS_PER_DAY.getValueByNetworkId(networkId);
            Integer lastDay = SharedItemDao.countByNetworkIdAndUserIdAndCreatedAfter(conn, networkId, userId, DateUtils.daysAgo(1));
            if (lastDay >= perDay) throw new UIException("Limit: " + perDay + " messages per day");
        }

        // Retrieving network
        Network network = NetworkDao.getById(conn, networkId);

        // Retrieving points per shared item
        Integer pointsPerSharedItem = NetworkIntegerSettingEnum.SHARED_ITEM_POINTS_PER.getValueByNetworkId(networkId);

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
