package com.questy.services;

import com.questy.dao.*;
import com.questy.domain.SharedComment;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SharedCommentServices extends ParentService  {

    public static Tuple<Integer, Integer> add (
            Integer networkId,
            Integer userId,
            Integer smartGroupRef,
            Integer sharedItemRef,
            String text) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Getting network settings
        Map<NetworkIntegerSettingEnum, Integer> networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(networkId);

        // Validating minimum length
        if (text.length() < networkIntegerSettings.get(NetworkIntegerSettingEnum.SHARED_COMMENT_MIN_LENGTH))
            throw new UIException("Comment is too short");

        // Validating max length
        if (text.length() > networkIntegerSettings.get(NetworkIntegerSettingEnum.SHARED_COMMENT_MAX_LENGTH))
            throw new UIException("Comment is too long");


        // Validating for timing attacks
        if (Vars.enableTimelocks) {

            // Validating for minute attack
            Integer perMinute = networkIntegerSettings.get(NetworkIntegerSettingEnum.SHARED_COMMENTS_PER_MINUTE);
            Integer lastMinute = SharedCommentDao.countByNetworkIdAndUserIdAndCreatedAfter(conn, networkId, userId, DateUtils.minutesAgo(1));
            if (lastMinute >= perMinute) throw new UIException("Limit: " + perMinute + " comments per minute");

            // Validating for hour attack
            Integer perHour = networkIntegerSettings.get(NetworkIntegerSettingEnum.SHARED_COMMENTS_PER_HOUR);
            Integer lastHour = SharedCommentDao.countByNetworkIdAndUserIdAndCreatedAfter(conn, networkId, userId, DateUtils.hoursAgo(1));
            if (lastHour >= perHour) throw new UIException("Limit: " + perHour + " comments per hour");

            // Validating for day attack
            Integer perDay = networkIntegerSettings.get(NetworkIntegerSettingEnum.SHARED_COMMENTS_PER_DAY);
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
        SharedComment sharedComment = SharedCommentDao.getByNetworkIdAndSmartGroupRefAndSharedItemRefAndRef(conn, networkId, smartGroupRef, sharedItemRef, ref);

        // Retrieving user to network relationship
        UserToNetwork userToNetwork = UserToNetworkDao.getByUserIdAndNetworkId(null, userId, networkId);

        Boolean deletedByAuthor = false;
        Boolean deletedByModerator = false;

        if (sharedComment.getUserId().equals(userId))
            deletedByAuthor = true;

        if (userToNetwork.getRole().isHigherThan(RoleEnum.MEMBER))
            deletedByModerator = true;

        // Validating authority to delete
        if (!(deletedByAuthor || deletedByModerator))
            throw new UIException("Can not hide comment");

        // Hide shared comment
        SharedCommentDao.updateHiddenByNetworkIdAndSmartGroupRefAndSharedItemIdAndRef(conn, networkId, smartGroupRef, sharedItemRef, ref, true);

        // Decrease shared comments count on shared item
        SharedItemDao.incrementTotalCommentsByNetworkIdAndSmartGroupRefAndRef(conn, networkId, smartGroupRef, sharedItemRef, -1);
               
        // Decrease total point value of shared item
        Integer pointsPer = 0;
        if (deletedByAuthor) {

            // Retrieving points per shared item
            pointsPer = NetworkIntegerSettingEnum.SHARED_COMMENT_POINTS_PER.getValueByNetworkId(networkId);
            pointsPer = pointsPer * -1;
            UserToNetworkDao.incrementPointsByUserIdAndNetworkId(conn, userId, networkId, pointsPer);
        }

        Tuple<Integer, Integer> out = new Tuple<Integer, Integer>(ref, pointsPer);

        return out;
    }

}
