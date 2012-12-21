package com.questy.services.email;

import com.questy.dao.EmailNotificationRateDao;
import com.questy.dao.SmartGroupDao;
import com.questy.dao.UserToSmartGroupDao;
import com.questy.domain.EmailNotificationRate;
import com.questy.domain.UserToSmartGroup;
import com.questy.enums.EmailNotificationRateEnum;
import com.questy.enums.NetworkEventEnum;
import com.questy.enums.UserToSmartGroupStateEnum;

import java.sql.Connection;
import java.sql.SQLException;

@Deprecated
public class EmailNotificationServices {

    /**
     * This method is the final word that defines the rate of at which message or digest for any
     * network event should be delivered to a user. This method checks if a user has flagged a
     * smart group, or does not wish to receive notifications or digests of a particular event-type etc.
     * This method only deals with network-level events
     *
     * The sequence of rate searches are defined below:
     *
     * A. If the smart group reference of the network has been provided:
     *  1. Check if the user has defined a notification rate at the network level, if so, return that rate
     *  2. Return the rate defined by the code
     *
     * B. If the smart group reference provided is that of a physical smart group:
     *  1. Does the user have a smart group mapping
     *   a. If yes
     *    I. If the mapping has a flagged state, then the notification rate is "never"
     *    II. If the user is a top member of the smart group, or has made it a favorite
     *     (a). Check if the user has defined a notification rate for that event for that particular smart group, if yes, return that rate
     *     (b). Check if the user has defined a notification rate for all smart groups of which it is a member or, or has made a favorite, if yes, return that rate
     *     (c). Return the default rate defined by the code
     *   b. If no, then the notification rate is "never"
     */
    public static EmailNotificationRateEnum getRate (
            Integer networkId,
            Integer smartGroupRef,
            Integer userId,
            NetworkEventEnum event
            ) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        EmailNotificationRateEnum out = null;

        // Is the event rate stored at the network level or smart group level?
        if (event.isForNetworkOnly()) {

            // At the network level, retrieve network level rate
            return getRateFromNetworkLevelThenCode(networkId, userId, event);

        } else {

            // Provided event is only for the smart group level, validate
            if (SmartGroupDao.isNetworkRef(smartGroupRef))
                throw  new RuntimeException("Smart reference is that of a network");

            // Start retrieving the rate starting at the smart group level
            return getRateFromSmartGroupLevel(networkId, smartGroupRef, userId, event);

        }

    }



    private static EmailNotificationRateEnum getRateFromSmartGroupLevel(
            Integer networkId,
            Integer smartGroupRef,
            Integer userId,
            NetworkEventEnum event) throws SQLException {

        // Retrieving user mapping to smart group
        UserToSmartGroup mapping = UserToSmartGroupDao.getByNetworkIdAndSmartGroupRefAndUserId(null, networkId, smartGroupRef, userId);

        // Does a mapping exist?
        if (mapping == null)
            return EmailNotificationRateEnum.NEVER;

        // Has the user marked the mapping as flagged?
        if (mapping.getState() == UserToSmartGroupStateEnum.FLAGGED)
            return EmailNotificationRateEnum.NEVER;

        // Is the user a member of the smart group or has marked it as a favorite?
        if (!mapping.isFavoriteOrMember())
            return EmailNotificationRateEnum.NEVER;

        /* User is a member of the smart group or has marked it as a favorite, and has not flagged it */

        // Retrieving rate from the smart group
        EmailNotificationRate rate = EmailNotificationRateDao.getByNetworkIdAndSmartGroupRefAndUserIdAndEvent(null, networkId, smartGroupRef, userId, event);

        // Was a rate defined for the user given the event?
        if (rate != null) {

            // Yes, return the rate that was retrieved
            return rate.getRate();

        } else {

            // No, return the rate defined by the code
            return getRateFromNetworkLevelThenCode(networkId, userId, event);

        }
    }

    private static EmailNotificationRateEnum getRateFromNetworkLevelThenCode(
            Integer networkId,
            Integer userId,
            NetworkEventEnum event) throws SQLException {

        // Retrieving rate from the network level
        EmailNotificationRate rate = EmailNotificationRateDao.getByNetworkIdAndSmartGroupRefAndUserIdAndEvent(null, networkId, SmartGroupDao.ANY_SMART_GROUP_REF, userId, event);

        // Was a rate defined for the user given the event?
        if (rate != null) {

            // Yes, return the rate that was retrieved
            return rate.getRate();

        } else {

            // No, return the rate defined by the code
            return event.getDefaultRate();

        }
    }

    public static void setRate (
        Integer networkId,
        Integer smartGroupRef,
        Integer userId,
        NetworkEventEnum event,
        EmailNotificationRateEnum newRate) throws SQLException {

        EmailNotificationRate rate = EmailNotificationRateDao.getByNetworkIdAndSmartGroupRefAndUserIdAndEvent(null, networkId, smartGroupRef, userId, event);

        if (rate == null) {

            // Create the new rate object
            EmailNotificationRateDao.insert(null, networkId, smartGroupRef, userId, event, newRate);

        } else {

            // Update the rate object
            EmailNotificationRateDao.updateRate(null, networkId, smartGroupRef, userId, event, newRate);

        }


    }

}
