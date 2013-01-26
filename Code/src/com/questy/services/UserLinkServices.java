package com.questy.services;

import com.questy.dao.NetworkDao;
import com.questy.dao.UserLinkDao;
import com.questy.dao.UserToNetworkDao;
import com.questy.domain.Network;
import com.questy.domain.UserLink;
import com.questy.domain.UserToNetwork;
import com.questy.enums.NetworkIntegerSettingEnum;
import com.questy.enums.UserLinkDirectionEnum;
import com.questy.helpers.Tuple;
import com.questy.helpers.UIException;
import com.questy.services.email.EmailServices;
import com.questy.utils.DateUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class UserLinkServices extends ParentService  {

    /**
     * Creates a #UserLink and returns the #UserLinkDirectionEnum of
     * the latest link created and the number of points that were used.
     * If a bi-directional link already exists, then no database actions are taken
     *
     */
    public static Tuple<UserLinkDirectionEnum, Integer> linkUsers(Integer networkId, Integer fromUserId, Integer toUserId) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving network
        Network network = NetworkDao.getById(conn, networkId);

        // Validating
        if (fromUserId.equals(toUserId))
            throw new UIException("Go to 'My profile'");

        // Validating for timing attacks
        {
            // Validating for minute attack
            Integer perMinute = NetworkIntegerSettingEnum.USER_LINKS_PER_MINUTE.getValueByNetworkId(networkId);
            Integer lastMinute = UserLinkDao.countByNetworkIdAndFromUserIdAndCreatedAfterAndDirection(conn, networkId, fromUserId, DateUtils.minutesAgo(1), UserLinkDirectionEnum.ME_TO_TARGET);
            if (lastMinute >= perMinute) throw new UIException("Limit: " + perMinute + " views per minute");

            // Validating for hour attack
            Integer perHour = NetworkIntegerSettingEnum.USER_LINKS_PER_HOUR.getValueByNetworkId(networkId);
            Integer lastHour = UserLinkDao.countByNetworkIdAndFromUserIdAndCreatedAfterAndDirection(conn, networkId, fromUserId, DateUtils.hoursAgo(1), UserLinkDirectionEnum.ME_TO_TARGET);
            if (lastHour >= perHour) throw new UIException("Limit: " + perHour + " views per hour");

            // Validating for day attack
            Integer perDay = NetworkIntegerSettingEnum.USER_LINKS_PER_DAY.getValueByNetworkId(networkId);
            Integer lastDay = UserLinkDao.countByNetworkIdAndFromUserIdAndCreatedAfterAndDirection(conn, networkId, fromUserId, DateUtils.daysAgo(1), UserLinkDirectionEnum.ME_TO_TARGET);
            if (lastDay >= perDay) throw new UIException("Limit: " + perDay + " views per day");
        }

        // Calculate the points required for a link to the user being connected to
        Integer pointsForLink = getPointsPerLink(networkId, toUserId);

        // Check that the from user has the required number of points
        UserToNetwork fromUserToNetwork = UserToNetworkDao.getByUserIdAndNetworkId(conn, fromUserId, networkId);

        /*
         * Here we multiply the points for link by negative one because we wish to make sure
         * we only check for points when the "to user" will actually reduce points from the
         * "from user"
         */
        if (fromUserToNetwork.getCurrentPoints() < (pointsForLink * -1)) {

            // User does not have enough points, return null
            throw new UIException("Not enough points");

        }

        // Decide whether this link is one-way or both ways
        {
            // Does the current user already have a connection from the target?
            UserLink fromLink = UserLinkDao.getByNetworkIdAndFromUserIdAndToUserId(conn, networkId, fromUserId, toUserId);

            // Will contain the final direction of the link
            UserLinkDirectionEnum finalDirection = null;

            // Does no link exist?
            if (fromLink == null) {

                // Create my user link
                UserLinkDao.insert(conn, networkId, fromUserId, toUserId, UserLinkDirectionEnum.ME_TO_TARGET);

                // Create target user link
                UserLinkDao.insert(conn, networkId, toUserId, fromUserId, UserLinkDirectionEnum.TARGET_TO_ME);

                // Update points from me
                UserToNetworkDao.incrementPointsByUserIdAndNetworkId(conn, fromUserId, networkId, pointsForLink);

                // Send notification email
                EmailServices.userLinkCreated(fromUserId, toUserId, networkId);

                finalDirection = UserLinkDirectionEnum.ME_TO_TARGET;
            }

            // Is there already a link from the target to me?
            else if (fromLink.getDirection() == UserLinkDirectionEnum.TARGET_TO_ME) {

                // Update my user link's direction to both-ways
                UserLinkDao.updateDirectionByNetworkIdAndFromUserIdAndToUserId (
                        conn,
                        UserLinkDirectionEnum.BOTH,
                        networkId,
                        fromUserId,
                        toUserId);

                // Update target user link's direction to both-ways
                UserLinkDao.updateDirectionByNetworkIdAndFromUserIdAndToUserId (
                        conn,
                        UserLinkDirectionEnum.BOTH,
                        networkId,
                        toUserId,
                        fromUserId);

                // Update points from me
                UserToNetworkDao.incrementPointsByUserIdAndNetworkId(conn, fromUserId, networkId, pointsForLink);

                // Send notification email
                EmailServices.userLinkCreated(fromUserId, toUserId, networkId);

                finalDirection = UserLinkDirectionEnum.BOTH;
            }

            // Does the user already have a user link to the target
            else if (fromLink.getDirection() == UserLinkDirectionEnum.ME_TO_TARGET)
                finalDirection = UserLinkDirectionEnum.ME_TO_TARGET;

            // Do the users already have a bi-directional link?
            else if (fromLink.getDirection() == UserLinkDirectionEnum.BOTH)
                finalDirection = UserLinkDirectionEnum.BOTH;

            return new Tuple<UserLinkDirectionEnum, Integer>(finalDirection, pointsForLink);
        }

    }

    public static Integer getPointsPerLink(Integer networkId, Integer userId) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving user to network
        UserToNetwork userToNetwork = UserToNetworkDao.getByUserIdAndNetworkId(conn, userId, networkId);

        if (!networkId.equals(userToNetwork.getNetworkId()))
            throw new RuntimeException("Network ids need to match");

        // Giving preference to user set value if not null
        if (userToNetwork.getPointsPerLink() != null)
            return userToNetwork.getPointsPerLink();
        else
            return NetworkIntegerSettingEnum.USER_LINK_POINTS_PER.getValueByNetworkId(networkId);
    }


    /**
     * Identifies whether if two user are the same, if not, then the method
     * requires that the "from user" be linked to the "to user"
     *
     */
    public static Boolean viewMyselfOrValidateUsersLinked (Integer networkId, Integer fromUserId, Integer toUserId) throws SQLException {

        Boolean viewMyself = false;
        if (fromUserId.equals(toUserId))
           viewMyself = true;
        else {

            // If not myself, determining if user has a link with the viewed user
            UserLink link = UserLinkDao.getByNetworkIdAndFromUserIdAndToUserId(null, networkId, fromUserId, toUserId);
            if (link == null || link.getDirection() == UserLinkDirectionEnum.TARGET_TO_ME)
                throw new RuntimeException("User link required");
        }

        return viewMyself;
    }
}
