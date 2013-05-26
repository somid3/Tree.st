package com.questy.services;

import com.questy.dao.NetworkDao;
import com.questy.dao.UserLinkDao;
import com.questy.dao.UserMessageDao;
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
import com.questy.utils.Vars;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class UserMessageServices extends ParentService  {

    public static Integer sendMessage (
            Integer networkId,
            Integer fromUserId,
            Integer toUserId,
            String quote) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Getting network settings
        Map<NetworkIntegerSettingEnum, Integer> networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(networkId);

        // Validating minimum length
        if (quote.length() < networkIntegerSettings.get(NetworkIntegerSettingEnum.USER_MESSAGE_MIN_LENGTH))
            throw new UIException("Message is too short");

        // Validating max length
        if (quote.length() > networkIntegerSettings.get(NetworkIntegerSettingEnum.USER_MESSAGE_MAX_LENGTH))
            throw new UIException("Message is too long");

        // Validating for timing attacks
        if (Vars.enableTimelocks) {

            // Validating for minute attack
            Integer perMinute = networkIntegerSettings.get(NetworkIntegerSettingEnum.USER_MESSAGES_PER_MINUTE);
            Integer lastMinute = UserMessageDao.countByNetworkIdAndFromUserIdAndCreatedAfter(conn, networkId, fromUserId, DateUtils.minutesAgo(1));
            if (lastMinute >= perMinute) throw new UIException("Limit: " + perMinute + " messages per minute");

            // Validating for hour attack
            Integer perHour = networkIntegerSettings.get(NetworkIntegerSettingEnum.USER_MESSAGES_PER_HOUR);
            Integer lastHour = UserMessageDao.countByNetworkIdAndFromUserIdAndCreatedAfter(conn, networkId, fromUserId, DateUtils.hoursAgo(1));
            if (lastHour >= perHour) throw new UIException("Limit: " + perHour + " messages per hour");

            // Validating for day attack
            Integer perDay = networkIntegerSettings.get(NetworkIntegerSettingEnum.USER_MESSAGES_PER_DAY);
            Integer lastDay = UserMessageDao.countByNetworkIdAndFromUserIdAndCreatedAfter(conn, networkId, fromUserId, DateUtils.daysAgo(1));
            if (lastDay >= perDay) throw new UIException("Limit: " + perDay + " messages per day");
        }

        // Calculate the points required to send a message
        Integer pointsPerMessage = networkIntegerSettings.get(NetworkIntegerSettingEnum.USER_MESSAGE_POINTS_PER);

        // Check that the from user has the required number of points
        UserToNetwork fromUserToNetwork = UserToNetworkDao.getByUserIdAndNetworkId(conn, fromUserId, networkId);
        if (fromUserToNetwork.getCurrentPoints() < (pointsPerMessage * -1)) {

            // User does not have enough points, return null
            throw new UIException("Not enough points");
        }

        // Update points from me
        UserToNetworkDao.incrementPointsByUserIdAndNetworkId(conn, fromUserId, networkId, pointsPerMessage);

        // Save message
        UserMessageDao.insert(conn, networkId, fromUserId, toUserId, quote);

        // Send notification email
        EmailServices.userMessage(fromUserId, toUserId, networkId, quote);

        return pointsPerMessage;

    }

}
