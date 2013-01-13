package com.questy.services.email;

import com.questy.dao.*;
import com.questy.domain.*;
import com.questy.enums.*;
import com.questy.helpers.SqlLimit;
import com.questy.services.ParentService;
import com.questy.utils.*;
import com.questy.web.HtmlUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmailServices extends ParentService {

    private static String GLOBAL_CREATOR_URL = "http://" + Vars.emailTemplateDomain + "/e/global";
    private static String NETWORK_CREATOR_URL = "http://" + Vars.emailTemplateDomain + "/e/network";

    public static String TO_USER_ID = "--user_id--";
    public static String TO_USER_SALT_CHECKSUM = "--user_salt_checksum--";
    public static String TO_USER_FIRST_NAME = "--user_first_name--";

    public static void confirmationEmail(Integer userId) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving user
        User user = UserDao.getById(conn, userId);

        // Retrieve email that needs to be confirmed
        String emailToConfirm = UserAlphaSettingEnum.EMAIL_TO_BE_CONFIRMED.getValueByUserId(user.getId());

        // Creating message
        UrlQuery query = new UrlQuery();
        query.add("uid", userId);
        String message = UrlUtils.getUrlContents(GLOBAL_CREATOR_URL + "/email_confirmation.jsp?" + query);

        // Creating runnable to send email on new thread
        AmazonMailSender ser = new AmazonMailSender();
        ser.setMessageMine(EmailMimeEnum.HTML_UTF8);
        ser.setFromName(Vars.supportEmailName);
        ser.setFromEmail(Vars.supportEmail);
        ser.addRecipient(emailToConfirm);
        ser.setSubject("Confirm your email at Tree.st");
        ser.setMessageText(EmailServices.customizeMessage(message, user));

        // Sending the email
        Thread thread = new Thread(ser);
        thread.start();

    }



    public static void passwordResetNotFound (String email) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Creating message
        String message = UrlUtils.getUrlContents(GLOBAL_CREATOR_URL + "/password_not_found.jsp");

        // Creating runnable to send email on new thread
        AmazonMailSender ser = new AmazonMailSender();
        ser.setMessageMine(EmailMimeEnum.HTML_UTF8);
        ser.setFromName(Vars.name);
        ser.setFromEmail(Vars.supportEmail);
        ser.addRecipient(email);
        ser.setSubject("Password reset for " + Vars.name);
        ser.setMessageText(message);

        // Sending the email
        Thread thread = new Thread(ser);
        thread.start();

    }

    public static void passwordReset (Integer userId, String checksum) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving user
        User user = UserDao.getById(conn, userId);

        // Creating message
        UrlQuery query = new UrlQuery();
        query.add("xcs", checksum);
        String message = UrlUtils.getUrlContents(GLOBAL_CREATOR_URL + "/password_reset.jsp?" + query);

        // Creating runnable to send email on new thread
        AmazonMailSender ser = new AmazonMailSender();
        ser.setMessageMine(EmailMimeEnum.HTML_UTF8);
        ser.setFromName(Vars.supportEmailName);
        ser.setFromEmail(Vars.supportEmail);
        ser.addRecipient(user.getEmail());
        ser.setSubject("Password reset for Tree.st");
        ser.setMessageText(EmailServices.customizeMessage(message, user));

        // Sending the email
        Thread thread = new Thread(ser);
        thread.start();

    }

    public static void passwordResetDone (Integer userId) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving user
        User user = UserDao.getById(conn, userId);

        // Creating message
        UrlQuery query = new UrlQuery();
        String message = UrlUtils.getUrlContents(GLOBAL_CREATOR_URL + "/password_done.jsp?" + query);

        // Creating runnable to send email on new thread
        AmazonMailSender ser = new AmazonMailSender();
        ser.setMessageMine(EmailMimeEnum.HTML_UTF8);
        ser.setFromName(Vars.supportEmailName);
        ser.setFromEmail(Vars.supportEmail);
        ser.addRecipient(user.getEmail());
        ser.setSubject("Password updated on " + Vars.name);
        ser.setMessageText(EmailServices.customizeMessage(message, user));

        // Sending the email
        Thread thread = new Thread(ser);
        thread.start();

    }

    public static void firstPhotoUpload(Integer userId) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Should this email be stopped?
        Boolean stop = UserIntegerSettingEnum.IS_UNSUBSCRIBED_FROM_FIRST_PHOTO_UPLOAD_EMAILS.getBooleanByUserId(userId);
        if (stop) return;

        // Retrieving user
        User user = UserDao.getById(conn, userId);

        // Creating message
        UrlQuery query = new UrlQuery();
        String message = UrlUtils.getUrlContents(GLOBAL_CREATOR_URL + "/first_photo_upload.jsp?" + query);

        // Creating runnable to send email on new thread
        AmazonMailSender ser = new AmazonMailSender();
        ser.setMessageMine(EmailMimeEnum.HTML_UTF8);
        ser.setFromName(Vars.supportEmailName);
        ser.setFromEmail(Vars.supportEmail);
        ser.addRecipient(user.getEmail());
        ser.setSubject("Please upload your photo on " + Vars.name);
        ser.setMessageText(EmailServices.customizeMessage(message, user));

        // Sending the email
        Thread thread = new Thread(ser);
        thread.start();

    }

    public static void userLinkCreated (
        Integer fromUserId,
        Integer toUserId,
        Integer networkId) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Does the message receiver wish to receive these messages instantaneously?
        Boolean unsubscribed = UserToNetworkIntegerSettingEnum.IS_UNSUBSCRIBED_FROM_NEW_USER_LINK_EMAIL_NOTIFICATIONS.getBooleanByUserIdAndNetworkId(toUserId, networkId);
        if(unsubscribed)
            return;

        // Retrieving user       Ê
        User fromUser = UserDao.getById(conn, fromUserId);
        User toUser = UserDao.getById(conn, toUserId);

        // Retrieving network
        Network network = NetworkDao.getById(conn, networkId);

         // Creating message
        UrlQuery query = new UrlQuery();
        query.add("fuid", fromUserId);
        query.add("nid", networkId);
        String message = UrlUtils.getUrlContents(NETWORK_CREATOR_URL + "/new_user_link.jsp?" + query);

        // Creating runnable to send email on new thread
        AmazonMailSender ser = new AmazonMailSender();
        ser.setMessageMine(EmailMimeEnum.HTML_UTF8);
        ser.setFromName(network.getName() + " @ " + Vars.supportEmailName);
        ser.setFromEmail(Vars.supportEmail);
        ser.addRecipient(toUser.getEmail());
        ser.setSubject(fromUser.getFirstName() + " from \"" + network.getName() + "\" just viewed you!");
        ser.setMessageText(EmailServices.customizeMessage(message, toUser));

        // Sending the email
        Thread thread = new Thread(ser);
        thread.start();
    }

    /**
     *
     * @param callingRate the rate at which this method is being called
     */
    public static void newSmartGroupMappings (
        Integer networkId,
        Integer userId,
        EmailNotificationRateEnum callingRate) throws SQLException {


        // Currently non-transactional
        Connection conn = null;

        // Does the message receiver wish to receive these messages instantaneously?
        Boolean unsubscribed = UserToNetworkIntegerSettingEnum.IS_UNSUBSCRIBED_FROM_NEW_SMART_GROUP_MAPPINGS_EMAIL_NOTIFICATIONS.getBooleanByUserIdAndNetworkId(userId, networkId);
        if(unsubscribed)
            return;


        // Retrieving user       Ê
        User user = UserDao.getById(conn, userId);

        // Retrieving network
        Network network = NetworkDao.getById(conn, networkId);

         // Creating message
        UrlQuery query = new UrlQuery();
        query.add("cr", callingRate.getId());
        query.add("nid", networkId);
        query.add("uid", userId);
        String message = UrlUtils.getUrlContents(NETWORK_CREATOR_URL + "/new_smart_group_mappings.jsp?" + query);

        // Creating runnable to send email on new thread
        AmazonMailSender ser = new AmazonMailSender();
        ser.setMessageMine(EmailMimeEnum.HTML_UTF8);
        ser.setFromName(network.getName() + " @ " + Vars.supportEmailName);
        ser.setFromEmail(Vars.supportEmail);
        ser.addRecipient(user.getEmail());
        ser.setSubject("Say hello!");
        ser.setMessageText(EmailServices.customizeMessage(message, user));

        // Sending the email
        Thread thread = new Thread(ser);
        thread.start();
    }


    public static void newSharedCommentForAuthor (
        Integer networkId,
        Integer smartGroupRef,
        Integer sharedItemRef,
        List<Integer> ignoreUsers) throws SQLException {

        // Creating email message
        UrlQuery query = new UrlQuery();
        query.add("nid", networkId);
        query.add("sgr", smartGroupRef);
        query.add("sir", sharedItemRef);
        String message = UrlUtils.getUrlContents(NETWORK_CREATOR_URL + "/new_shared_item.jsp?" + query);

        // Retrieving network
        Network network = NetworkDao.getById(null, networkId);

        // Retrieving shared item
        SharedItem sharedItem = SharedItemDao.getByNetworkIdAndSmartGroupRefAndRef(null, networkId, smartGroupRef, sharedItemRef);

        // Is the author of the shared item in the ignore list?
        if (ignoreUsers.contains(sharedItem.getUserId()))
            return;

        // Retrieving shared item author       Ê
        User toUser = UserDao.getById(null, sharedItem.getUserId());

        // Creating runnable to send email on new thread
        AmazonMailSender ser = new AmazonMailSender();
        ser.setMessageMine(EmailMimeEnum.HTML_UTF8);
        ser.setFromName(network.getName() + " @ " + Vars.supportEmailName);
        ser.setFromEmail(Vars.supportEmail);
        ser.addRecipient(toUser.getEmail());

        // Creating subject
        ser.setSubject("Someone commented on your message!");

        // Sending the email
        ser.setMessageText(EmailServices.customizeMessage(message, toUser));
        Thread thread = new Thread(ser);
        thread.start();

    }

    public static void newSharedCommentForCommentators(
        Integer networkId,
        Integer smartGroupRef,
        Integer sharedItemRef,
        List<Integer> ignoreUsers) throws SQLException {

        // Validating
        if (ignoreUsers == null) ignoreUsers = new ArrayList<Integer>();

        // Creating email message
        UrlQuery query = new UrlQuery();
        query.add("nid", networkId);
        query.add("sgr", smartGroupRef);
        query.add("sir", sharedItemRef);
        String message = UrlUtils.getUrlContents(NETWORK_CREATOR_URL + "/new_shared_item.jsp?" + query);

        // Retrieve all shared comments of the shared item
        List<SharedComment> sharedComments = SharedCommentDao.getByNetworkIdAndSmartGroupRefAndSharedItemRef(null, networkId, smartGroupRef, sharedItemRef, SqlLimit.ALL);

        // Retrieving network
        Network network = NetworkDao.getById(null, networkId);

        // Looping through all smart group members
        User toUser = null;
        EmailNotificationRateEnum rate = null;
        List<Integer> sentUsers = new ArrayList<Integer>();
        for (SharedComment sharedComment : sharedComments) {

            // Is the author of the comment in the ignore list?
            if (ignoreUsers.contains(sharedComment.getUserId()))
                continue;

            // Do not send messages to users that have posted more than one comment
            if (sentUsers.contains(sharedComment.getUserId()))
                continue;

             // Retrieving user to receive email       Ê
            toUser = UserDao.getById(null, sharedComment.getUserId());

            // Creating runnable to send email on new thread
            AmazonMailSender ser = new AmazonMailSender();
            ser.setMessageMine(EmailMimeEnum.HTML_UTF8);
            ser.setFromName(network.getName() + " @ " + Vars.supportEmailName);
            ser.setFromEmail(Vars.supportEmail);
            ser.addRecipient(toUser.getEmail());

            // Creating subject
            ser.setSubject("Someone commented after you did!");

            // Sending the email
            ser.setMessageText(EmailServices.customizeMessage(message, toUser));
            Thread thread = new Thread(ser);
            thread.start();

            // Add user to list of users who received a notification
            sentUsers.add(sharedComment.getUserId());
        }
    }

    public static void newSharedItemForActiveUserToSmartGroups (
            Integer networkId,
            Integer smartGroupRef,
            Integer sharedItemRef) throws SQLException {

        // Validating
        if (SmartGroupDao.isNetworkRef(smartGroupRef))
            throw new RuntimeException("A non-network smart group reference is required");

        // Creating email message
        UrlQuery query = new UrlQuery();
        query.add("nid", networkId);
        query.add("sgr", smartGroupRef);
        query.add("sir", sharedItemRef);
        query.add("digest", true);
        String message = UrlUtils.getUrlContents(NETWORK_CREATOR_URL + "/new_shared_item.jsp?" + query);

        // Retrieve all members of the smart group
        List<UserToSmartGroup> activeUsersToSmartGroup = UserToSmartGroupDao.getActiveByNetworkIdAndSmartGroupRef(null, networkId, smartGroupRef, SqlLimit.ALL);

        if (activeUsersToSmartGroup.isEmpty())
            return;

        // Retrieving network
        Network network = NetworkDao.getById(null, networkId);

        // Retrieving smart group
        SmartGroup smartGroup = SmartGroupDao.getByNetworkIdAndRef(null, networkId, smartGroupRef);

        // Retrieving shared item
        SharedItem sharedItem = SharedItemDao.getByNetworkIdAndSmartGroupRefAndRef(null, networkId, smartGroupRef, sharedItemRef);

        // Looping through all smart group members
        User toUser = null;
        EmailNotificationRateEnum rate = null;
        Boolean unsubscribed = null;
        String messageOnSubject = null;
        String subtitleOnSubject = null;
        String customizeMessage = null;
        for (UserToSmartGroup userToSmartGroup : activeUsersToSmartGroup) {

            // Does the message receiver wish to receive these messages instantaneously?
            rate = EmailNotificationRateEnum.getById(UserToNetworkIntegerSettingEnum.NEW_SHARED_ITEM_DIGEST_EMAIL_RATE.getValueByUserIdAndNetworkId(userToSmartGroup.getUserId(), userToSmartGroup.getNetworkId()));
            if (rate != EmailNotificationRateEnum.INSTANTLY)
                continue;

            // Retrieving user to receive email       Ê
            toUser = UserDao.getById(null, userToSmartGroup.getUserId());

            // Creating runnable to send email on new thread
            AmazonMailSender ser = new AmazonMailSender();
            ser.setMessageMine(EmailMimeEnum.HTML_UTF8);
            ser.setFromName(network.getName() + " @ " + Vars.supportEmailName);
            ser.setFromEmail(Vars.supportEmail);
            ser.addRecipient(toUser.getEmail());

            // Creating subject
            subtitleOnSubject = smartGroup.getName();
            messageOnSubject = StringUtils.concat(sharedItem.getText(), 70, "...").replaceAll("\n", " ");
            ser.setSubject(subtitleOnSubject + ": " + messageOnSubject);

            // Customizing the message with receiver information
            customizeMessage = EmailServices.customizeMessage(message, toUser);

            // Sending the email
            ser.setMessageText(customizeMessage);
            Thread thread = new Thread(ser);
            thread.start();

        }

    }

    public static void sharedItemDigest (
            Integer networkId,
            EmailNotificationRateEnum callingRate) throws SQLException {

        // Retrieving network
        Network network = NetworkDao.getById(null, networkId);

        // Looping through all network users
        User toUser = null;
        EmailNotificationRateEnum userRate = null;
        String customizeMessage = null;
        List<UserToNetwork> userToNetworks = UserToNetworkDao.getByNetworkIdAndLowestRoleOrderedByPoints(null, networkId, RoleEnum.VISITOR, AllMembersViewEnum.BY_POINTS, SqlLimit.ALL);
        for (UserToNetwork userToNetwork : userToNetworks) {

            // Does the message receiver wish to receive a digest at the same rate as the calling rate?
            userRate = EmailNotificationRateEnum.getById(UserToNetworkIntegerSettingEnum.NEW_SHARED_ITEM_DIGEST_EMAIL_RATE.getValueByUserIdAndNetworkId(userToNetwork.getUserId(), userToNetwork.getNetworkId()));
            if (userRate != callingRate)
                continue;

            System.out.println(
                userToNetwork.getUserId() + " " + userToNetwork.getNetworkId()
            );

            // Used to count the total number of shared items to be included in this digest
            Integer digestTotalSmartGroupCount = 0;
            Integer digestTotalSharedItemCount = 0;

            // Retrieving all active smart groups for user
            List<UserToSmartGroup> activeUserToSmartGroups = UserToSmartGroupDao.getActiveByNetworkIdAndUserId(null, networkId, userToNetwork.getUserId(), SqlLimit.ALL);

            // Counting the number of messages in the digest
            for (UserToSmartGroup activeUserToSmartGroup : activeUserToSmartGroups) {

                // Retrieving the number of shared items that should be included in the digest
                Integer digestSharedItemCount = SharedItemDao.countByNetworkIdAndSmartGroupRefAndCreatedAfter(null, networkId, activeUserToSmartGroup.getSmartGroupRef(), userRate.getBoundaryDate());

                // Rallying up the total count of smart groups and messages in the digest
                if (digestSharedItemCount > 0) {
                    digestTotalSmartGroupCount =+ 1;
                    digestTotalSharedItemCount =+ digestSharedItemCount;
                }

            }

            // Should a digest be sent for this user?
            if (digestTotalSharedItemCount == 0)
                continue;

            // Creating email message
            UrlQuery query = new UrlQuery();
            query.add("nid", networkId);
            query.add("uid", userToNetwork.getUserId());
            query.add("rate", userRate.getId());
            String message = UrlUtils.getUrlContents(NETWORK_CREATOR_URL + "/shared_item_digest.jsp?" + query);

            // Retrieving user to receive email       Ê
            toUser = UserDao.getById(null, userToNetwork.getUserId());

            // Creating runnable to send email on new thread
            AmazonMailSender ser = new AmazonMailSender();
            ser.setMessageMine(EmailMimeEnum.HTML_UTF8);
            ser.setFromName(network.getName() + " @ " + Vars.supportEmailName);
            ser.setFromEmail(Vars.supportEmail);
            ser.addRecipient(toUser.getEmail());

            // Creating subject
            ser.setSubject(
                network.getName() + " digest with " +
                digestTotalSharedItemCount + " messages in " +
                digestTotalSmartGroupCount + " smart groups"
            );

            // Customizing the message with receiver information
            customizeMessage = EmailServices.customizeMessage(message, toUser);

            // Sending the email
            ser.setMessageText(customizeMessage);
            Thread thread = new Thread(ser);
            thread.start();
        }

    }









    public static String helperCreateActionUrl(EmailActionEnum actionEnum, UrlQuery parameters) {

        UrlQuery query = new UrlQuery();
        query.add("uid", EmailServices.TO_USER_ID);
        query.add("scs", EmailServices.TO_USER_SALT_CHECKSUM);
        query.add("aid", actionEnum.getId());
        query.addAll(parameters);
        return "http://" + Vars.domain + "/r/action/?" + query;

    }

    public static String helperCreateActionRateUrls(EmailActionEnum action, Integer networkId) {

        StringBuilder buf = new StringBuilder();
        {
            UrlQuery parameters = new UrlQuery();
            parameters.add("nid", networkId);
            parameters.add("rate", EmailNotificationRateEnum.INSTANTLY.getId());
            String instantLink = HtmlUtils.createHref("Instant", EmailServices.helperCreateActionUrl(action, parameters));
            buf.append(instantLink);
            buf.append(", or every ");
        }

        {
            UrlQuery parameters = new UrlQuery();
            parameters.add("nid", networkId);
            parameters.add("rate", EmailNotificationRateEnum.EVERY_HOUR.getId());
            String instantLink = HtmlUtils.createHref("1 hr", EmailServices.helperCreateActionUrl(action, parameters));
            buf.append(instantLink);
            buf.append(", ");
        }

        {
            UrlQuery parameters = new UrlQuery();
            parameters.add("nid", networkId);
            parameters.add("rate", EmailNotificationRateEnum.EVERY_FOUR_HOURS.getId());
            String instantLink = HtmlUtils.createHref("4 hrs", EmailServices.helperCreateActionUrl(action, parameters));
            buf.append(instantLink);
            buf.append(", ");
        }

        {
            UrlQuery parameters = new UrlQuery();
            parameters.add("nid", networkId);
            parameters.add("rate", EmailNotificationRateEnum.EVERY_EIGHT_HOURS.getId());
            String instantLink = HtmlUtils.createHref("8 hrs", EmailServices.helperCreateActionUrl(action, parameters));
            buf.append(instantLink);
            buf.append(", ");
        }

        {
            UrlQuery parameters = new UrlQuery();
            parameters.add("nid", networkId);
            parameters.add("rate", EmailNotificationRateEnum.EVERY_TWELVE_HOURS.getId());
            String instantLink = HtmlUtils.createHref("12 hrs", EmailServices.helperCreateActionUrl(action, parameters));
            buf.append(instantLink);
            buf.append(", ");
        }

        {
            UrlQuery parameters = new UrlQuery();
            parameters.add("nid", networkId);
            parameters.add("rate", EmailNotificationRateEnum.EVERY_DAY.getId());
            String instantLink = HtmlUtils.createHref("day", EmailServices.helperCreateActionUrl(action, parameters));
            buf.append(instantLink);
            buf.append(", ");
        }

        {
            UrlQuery parameters = new UrlQuery();
            parameters.add("nid", networkId);
            parameters.add("rate", EmailNotificationRateEnum.EVERY_OTHER_DAY.getId());
            String instantLink = HtmlUtils.createHref("2 days", EmailServices.helperCreateActionUrl(action, parameters));
            buf.append(instantLink);
            buf.append(", ");
        }

        {
            UrlQuery parameters = new UrlQuery();
            parameters.add("nid", networkId);
            parameters.add("rate", EmailNotificationRateEnum.EVERY_THREE_DAYS.getId());
            String instantLink = HtmlUtils.createHref("3 days", EmailServices.helperCreateActionUrl(action, parameters));
            buf.append(instantLink);
            buf.append(", ");
        }

        {
            UrlQuery parameters = new UrlQuery();
            parameters.add("nid", networkId);
            parameters.add("rate", EmailNotificationRateEnum.EVERY_WEEK.getId());
            String instantLink = HtmlUtils.createHref("week", EmailServices.helperCreateActionUrl(action, parameters));
            buf.append(instantLink);
            buf.append(", or ");
        }


        {
            UrlQuery parameters = new UrlQuery();
            parameters.add("nid", networkId);
            parameters.add("rate", EmailNotificationRateEnum.EVERY_OTHER_WEEK.getId());
            String instantLink = HtmlUtils.createHref("2 weeks", EmailServices.helperCreateActionUrl(action, parameters));
            buf.append(instantLink);
            buf.append(".");
        }

        return buf.toString();
    }

    /**
     * All emails will contain keywords that are placeholders of the user id, user checksum,
     * user first name, among other variables
     *
     * This method updates the placeholders with the actual values that belong to a user
     */
    public static String customizeMessage (String message, User user) {

        message = message.replaceAll(EmailServices.TO_USER_ID, user.getId().toString());
        message = message.replaceAll(EmailServices.TO_USER_SALT_CHECKSUM, user.getSaltChecksum());
        message = message.replaceAll(EmailServices.TO_USER_FIRST_NAME, user.getFirstName());

        return message;
    }

}
