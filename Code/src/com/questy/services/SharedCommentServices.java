package com.questy.services;

import com.questy.dao.*;
import com.questy.domain.SharedComment;
import com.questy.enums.NetworkIntegerSettingEnum;
import com.questy.helpers.Tuple;
import com.questy.helpers.UIException;
import com.questy.services.email.EmailServices;
import com.questy.utils.DateUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SharedCommentServices extends ParentService  {

    public static Tuple<Integer, Integer> add (Integer networkId, Integer userId, Integer smartGroupRef, Integer sharedItemRef, String text) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Validating
        if (text == null || text.isEmpty())
            throw new UIException("Shared comment text can not be empty");

        // Validating for timing attacks
        {
            // Validating for minute attack
            Integer perMinute = NetworkIntegerSettingEnum.SHARED_COMMENTS_PER_MINUTE.getValueByNetworkId(networkId);
            Integer lastMinute = SharedCommentDao.countByNetworkIdAndUserIdAndCreatedAfter(conn, networkId, userId, DateUtils.minutesAgo(1));
            if (lastMinute >= perMinute) throw new UIException("Limit: " + perMinute + " comments per minute");

            // Validating for hour attack
            Integer perHour = NetworkIntegerSettingEnum.SHARED_COMMENTS_PER_HOUR.getValueByNetworkId(networkId);
            Integer lastHour = SharedCommentDao.countByNetworkIdAndUserIdAndCreatedAfter(conn, networkId, userId, DateUtils.hoursAgo(1));
            if (lastHour >= perHour) throw new UIException("Limit: " + perHour + " comments per hour");

            // Validating for day attack
            Integer perDay = NetworkIntegerSettingEnum.SHARED_COMMENTS_PER_DAY.getValueByNetworkId(networkId);
            Integer lastDay = SharedCommentDao.countByNetworkIdAndUserIdAndCreatedAfter(conn, networkId, userId, DateUtils.daysAgo(1));
            if (lastDay >= perDay) throw new UIException("Limit: " + perDay + " comments per day");
        }


        // Retrieving points per shared item
        Integer pointsPerSharedComment = NetworkIntegerSettingEnum.SHARED_COMMENT_POINTS_PER.getValueByNetworkId(networkId);

        // Retrieving max ref
        Integer maxRef = SharedCommentDao.getMaxRefByNetworkIdAndSmartGroupRef(conn, networkId, smartGroupRef);

        /* Creating next ref, starting with 1 and not 0 */
        Integer nextRef = 1;
        if (maxRef != null)
            nextRef = maxRef + 1;

        // Inserting shared comment
        SharedCommentDao.insert(conn, networkId, userId, smartGroupRef, sharedItemRef, nextRef, text);

        // Increment comment count on shared item
        SharedItemDao.incrementTotalCommentsByNetworkIdAndSmartGroupRefAndRef(conn, networkId, smartGroupRef, sharedItemRef, 1);

        // Increase total point value of shared comment
        UserToNetworkDao.incrementPointsByUserIdAndNetworkId(conn, userId, networkId, pointsPerSharedComment);

        // Sending notifications
        {
            List<Integer> ignoreUsers = new ArrayList<Integer>();

            // Ignoring the author of the comment
            ignoreUsers.add(userId);

            // Send notification to author of shared item
            EmailServices.newSharedCommentForAuthor(networkId, smartGroupRef, sharedItemRef, ignoreUsers);

            // Send notification to previous commentators
            EmailServices.newSharedCommentForCommentators(networkId, smartGroupRef, sharedItemRef, ignoreUsers);
        }

        Tuple<Integer, Integer> out = new Tuple<Integer, Integer>(nextRef, pointsPerSharedComment);

        return out;
    }

    public static Tuple<Integer, Integer> hide (Integer networkId, Integer userId, Integer smartGroupRef, Integer sharedItemRef, Integer ref) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving shared comment to be hidden
        SharedComment sc = SharedCommentDao.getByNetworkIdAndSmartGroupRefAndSharedItemRefAndRef(conn, networkId, smartGroupRef, sharedItemRef, ref);

        // Validating authority to delete
        if (!sc.getUserId().equals(userId))
            throw new UIException("User is not the author of the shared comment to be hidden");

        // Retrieving points per shared item
        Integer pointsPerSharedComment = NetworkIntegerSettingEnum.SHARED_COMMENT_POINTS_PER.getValueByNetworkId(networkId);
        pointsPerSharedComment = pointsPerSharedComment * -1;

        // Hide shared comment
        SharedCommentDao.updateHiddenByNetworkIdAndSmartGroupRefAndSharedItemIdAndRef(conn, networkId, smartGroupRef, sharedItemRef, ref, true);

        // Decrease shared comments count on shared item
        SharedItemDao.incrementTotalCommentsByNetworkIdAndSmartGroupRefAndRef(conn, networkId, smartGroupRef, sharedItemRef, -1);
               
        // Decrease total point value of shared item
        UserToNetworkDao.incrementPointsByUserIdAndNetworkId(conn, userId, networkId, pointsPerSharedComment);

        Tuple<Integer, Integer> out = new Tuple<Integer, Integer>(ref, pointsPerSharedComment);

        return out;
    }

}
