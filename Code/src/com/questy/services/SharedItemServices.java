package com.questy.services;

import com.questy.dao.NetworkDao;
import com.questy.dao.SharedItemDao;
import com.questy.dao.UserToNetworkDao;
import com.questy.domain.Network;
import com.questy.domain.SharedItem;
import com.questy.enums.NetworkIntegerSettingEnum;
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
            Integer perMinute = NetworkIntegerSettingEnum.SHARED_ITEMS_PER_FIVE_MINUTES.getValueByNetwork(networkId);
            Integer lastMinute = SharedItemDao.countByNetworkIdAndUserIdAndCreatedAfter(conn, networkId, userId, DateUtils.minutesAgo(5));
            if (lastMinute >= perMinute) throw new UIException("Limit: " + perMinute + " messages every 5 minutes");

            // Validating for hour attack
            Integer perHour = NetworkIntegerSettingEnum.SHARED_ITEMS_PER_HOUR.getValueByNetwork(networkId);
            Integer lastHour = SharedItemDao.countByNetworkIdAndUserIdAndCreatedAfter(conn, networkId, userId, DateUtils.hoursAgo(1));
            if (lastHour >= perHour) throw new UIException("Limit: " + perHour + " messages per hour");

            // Validating for day attack
            Integer perDay = NetworkIntegerSettingEnum.SHARED_ITEMS_PER_DAY.getValueByNetwork(networkId);
            Integer lastDay = SharedItemDao.countByNetworkIdAndUserIdAndCreatedAfter(conn, networkId, userId, DateUtils.daysAgo(1));
            if (lastDay >= perDay) throw new UIException("Limit: " + perDay + " messages per day");
        }

        // Retrieving network
        Network network = NetworkDao.getById(conn, networkId);

        // Retrieving points per shared item
        Integer pointsPerSharedItem = NetworkIntegerSettingEnum.SHARED_ITEM_POINTS_PER.getValueByNetwork(networkId);

        // Validating
        if (network.isGlobal())
            throw new UIException("Shared items not enabled for global networks");

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
        SharedItem si = SharedItemDao.getByNetworkIdAndSmartGroupRefAndRef(conn, networkId, smartGroupRef, sharedItemRef);

        // Validating authority to delete
        if (!si.getUserId().equals(userId))
            throw new UIException("User is not the author of the shared item to be hidden");

        // Retrieving points per shared item
        Integer pointsPerSharedItem = NetworkIntegerSettingEnum.SHARED_ITEM_POINTS_PER.getValueByNetwork(networkId);
        pointsPerSharedItem = pointsPerSharedItem * -1;

        // Hide shared item
        SharedItemDao.updateHiddenByNetworkIdAndSmartGroupRefAndRef(conn, networkId, smartGroupRef, sharedItemRef, true);

        // Decrease points
        UserToNetworkDao.incrementPointsByUserIdAndNetworkId(conn, userId, networkId, pointsPerSharedItem);

        // TODO: hide all shared comments of this shared item!
        // TODO: hide all shared comments of this shared item!
        // TODO: hide all shared comments of this shared item!

        Tuple<Integer, Integer> out = new Tuple<Integer, Integer>(sharedItemRef, pointsPerSharedItem);

        return out;
    }

}
