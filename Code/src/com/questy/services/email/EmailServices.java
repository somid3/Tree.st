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
        AmazonSmtpSender ass = new AmazonSmtpSender();
        ass.setMessageMine(EmailMimeEnum.HTML_UTF8);
        ass.setFromName(Vars.supportEmailName);
        ass.setFromEmail(Vars.supportEmail);
        ass.addRecipient(emailToConfirm);
        ass.setSubject("Confirm your email");
        ass.setMessageText(EmailServices.customizeMessage(message, user));

        // Queueing the email
        AmazonMailQueue.queueEmail(ass);
    }



    public static void passwordResetNotFound (String email) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Creating message
        String message = UrlUtils.getUrlContents(GLOBAL_CREATOR_URL + "/password_not_found.jsp");

        // Creating runnable to send email on new thread
        AmazonSmtpSender aes = new AmazonSmtpSender();
        aes.setMessageMine(EmailMimeEnum.HTML_UTF8);
        aes.setFromName(Vars.supportEmailName);
        aes.setFromEmail(Vars.supportEmail);
        aes.addRecipient(email);
        aes.setSubject("Password reset for " + Vars.name);
        aes.setMessageText(message);

        // Queueing the email
        AmazonMailQueue.queueEmail(aes);
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
        AmazonSmtpSender aes = new AmazonSmtpSender();
        aes.setMessageMine(EmailMimeEnum.HTML_UTF8);
        aes.setFromName(Vars.supportEmailName);
        aes.setFromEmail(Vars.supportEmail);
        aes.addRecipient(user.getEmail());
        aes.setSubject("Password reset for " + Vars.name);
        aes.setMessageText(EmailServices.customizeMessage(message, user));

        // Queueing the email
        AmazonMailQueue.queueEmail(aes);
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
        AmazonSmtpSender ams = new AmazonSmtpSender();
        ams.setMessageMine(EmailMimeEnum.HTML_UTF8);
        ams.setFromName(Vars.supportEmailName);
        ams.setFromEmail(Vars.supportEmail);
        ams.addRecipient(user.getEmail());
        ams.setSubject("Password updated on " + Vars.name);
        ams.setMessageText(EmailServices.customizeMessage(message, user));

        // Queueing the email
        AmazonMailQueue.queueEmail(ams);
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
        AmazonSmtpSender aes = new AmazonSmtpSender();
        aes.setMessageMine(EmailMimeEnum.HTML_UTF8);
        aes.setFromName(Vars.supportEmailName);
        aes.setFromEmail(Vars.supportEmail);
        aes.addRecipient(user.getEmail());
        aes.setSubject("Please upload your photo on " + Vars.name);
        aes.setMessageText(EmailServices.customizeMessage(message, user));

        // Queueing the email
        AmazonMailQueue.queueEmail(aes);
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

        // Retrieving user       �
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
        AmazonSmtpSender aes = new AmazonSmtpSender();
        aes.setMessageMine(EmailMimeEnum.HTML_UTF8);
        aes.setFromName(network.getName());
        aes.setFromEmail(Vars.supportEmail);
        aes.addRecipient(toUser.getEmail());
        aes.setSubject(fromUser.getFirstName() + " from \"" + network.getName() + "\" just viewed you!");
        aes.setMessageText(EmailServices.customizeMessage(message, toUser));

        // Queueing the email
        AmazonMailQueue.queueEmail(aes);
    }

    public static void userMessage (
        Integer fromUserId,
        Integer toUserId,
        Integer networkId) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Does the message receiver wish to receive these messages instantaneously?
        Boolean unsubscribed = UserToNetworkIntegerSettingEnum.IS_UNSUBSCRIBED_FROM_USER_MESSAGE_EMAIL_NOTIFICATIONS.getBooleanByUserIdAndNetworkId(toUserId, networkId);
        if(unsubscribed)
            return;

        // Retrieving user
        User fromUser = UserDao.getById(conn, fromUserId);
        User toUser = UserDao.getById(conn, toUserId);

        // Retrieving network
        Network network = NetworkDao.getById(conn, networkId);

        // Creating message
        UrlQuery query = new UrlQuery();
        query.add("fuid", fromUserId);
        query.add("tuid", toUserId);
        query.add("nid", networkId);
        String message = UrlUtils.getUrlContents(NETWORK_CREATOR_URL + "/new_user_message.jsp?" + query);

        // Creating runnable to send email on new thread
        AmazonSmtpSender aes = new AmazonSmtpSender();
        aes.setMessageMine(EmailMimeEnum.HTML_UTF8);
        aes.setFromName(network.getName());
        aes.setFromEmail(Vars.supportEmail);
        aes.addRecipient(toUser.getEmail());
        aes.setSubject(fromUser.getFirstName() + " from \"" + network.getName() + "\" just messaged you!");
        aes.setMessageText(EmailServices.customizeMessage(message, toUser));

        // Queueing the email
        AmazonMailQueue.queueEmail(aes);
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

        // Retrieving user
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
        AmazonSmtpSender aes = new AmazonSmtpSender();
        aes.setMessageMine(EmailMimeEnum.HTML_UTF8);
        aes.setFromName(network.getName());
        aes.setFromEmail(Vars.supportEmail);
        aes.addRecipient(user.getEmail());
        aes.setSubject("Say hello to " + network.getName() + "!");
        aes.setMessageText(EmailServices.customizeMessage(message, user));

        // Queueing the email
        AmazonMailQueue.queueEmail(aes);
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

        // Retrieving shared item author
        User author = UserDao.getById(null, sharedItem.getUserId());

        // Creating runnable to send email on new thread
        AmazonSmtpSender aes = new AmazonSmtpSender();
        aes.setMessageMine(EmailMimeEnum.HTML_UTF8);
        aes.setFromName(network.getName());
        aes.setFromEmail(Vars.supportEmail);
        aes.addRecipient(author.getEmail());
        aes.setSubject("Someone commented on your message!");
        aes.setMessageText(EmailServices.customizeMessage(message, author));

        // Queueing the email
        AmazonMailQueue.queueEmail(aes);
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
        User author = null;
        EmailNotificationRateEnum rate = null;
        List<Integer> sentUsers = new ArrayList<Integer>();
        for (SharedComment sharedComment : sharedComments) {

            // Is the author of the comment in the ignore list?
            if (ignoreUsers.contains(sharedComment.getUserId()))
                continue;

            // Do not send messages to users that have posted more than one comment
            if (sentUsers.contains(sharedComment.getUserId()))
                continue;

             // Retrieving user to receive email       �
            author = UserDao.getById(null, sharedComment.getUserId());

            // Creating runnable to send email on new thread
            AmazonSmtpSender aes = new AmazonSmtpSender();
            aes.setMessageMine(EmailMimeEnum.HTML_UTF8);
            aes.setFromName(network.getName());
            aes.setFromEmail(Vars.supportEmail);
            aes.addRecipient(author.getEmail());
            aes.setSubject("Someone commented after you did!");
            aes.setMessageText(EmailServices.customizeMessage(message, author));

            // Queueing the email
            AmazonMailQueue.queueEmail(aes);

            // Add user to list of users who received a notification
            sentUsers.add(sharedComment.getUserId());
        }
    }

    public static void newSharedItemForActiveUserToSmartGroups (
            Integer networkId,
            Integer smartGroupRef,
            Integer sharedItemRef) throws SQLException {

        // Validating
        if (smartGroupRef.equals(SmartGroup.ANY_SMART_GROUP_REF))
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
        String messageOnSubject = null;
        String subtitleOnSubject = null;
        String customizeMessage = null;
        for (UserToSmartGroup userToSmartGroup : activeUsersToSmartGroup) {

            // Does the message receiver wish to receive these messages instantaneously?
            rate = EmailNotificationRateEnum.getById(UserToNetworkIntegerSettingEnum.NEW_SHARED_ITEM_DIGEST_EMAIL_RATE.getValueByUserIdAndNetworkId(userToSmartGroup.getUserId(), userToSmartGroup.getNetworkId()));
            if (rate != EmailNotificationRateEnum.INSTANTLY)
                continue;

            // Retrieving user to receive email       �
            toUser = UserDao.getById(null, userToSmartGroup.getUserId());

            // Creating runnable to send email on new thread
            AmazonSmtpSender aes = new AmazonSmtpSender();
            aes.setMessageMine(EmailMimeEnum.HTML_UTF8);
            aes.setFromName(network.getName());
            aes.setFromEmail(Vars.supportEmail);
            aes.addRecipient(toUser.getEmail());

            // Creating subject
            subtitleOnSubject = smartGroup.getName();
            messageOnSubject = StringUtils.concat(sharedItem.getText(), 70, "...").replaceAll("\n", " ");
            aes.setSubject(subtitleOnSubject + ": " + messageOnSubject);

            // Customizing the message with receiver information
            customizeMessage = EmailServices.customizeMessage(message, toUser);
            aes.setMessageText(customizeMessage);

            // Queueing the email
            AmazonMailQueue.queueEmail(aes);
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
        List<UserToNetwork> userToNetworks = UserToNetworkDao.getByNetworkIdAndLowestRoleOrderedBy(null, networkId, RoleEnum.VISITOR, AllMembersViewEnum.BY_POINTS, SqlLimit.ALL);
        for (UserToNetwork userToNetwork : userToNetworks) {

            // Does the message receiver wish to receive a digest at the same rate as the calling rate?
            userRate = EmailNotificationRateEnum.getById(UserToNetworkIntegerSettingEnum.NEW_SHARED_ITEM_DIGEST_EMAIL_RATE.getValueByUserIdAndNetworkId(userToNetwork.getUserId(), userToNetwork.getNetworkId()));
            if (userRate != callingRate)
                continue;

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
                    digestTotalSmartGroupCount = digestTotalSmartGroupCount + 1;
                    digestTotalSharedItemCount = digestTotalSharedItemCount + digestSharedItemCount;
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

            // Retrieving user to receive email       �
            toUser = UserDao.getById(null, userToNetwork.getUserId());

            // Creating runnable to send email on new thread
            AmazonSmtpSender ams = new AmazonSmtpSender();
            ams.setMessageMine(EmailMimeEnum.HTML_UTF8);
            ams.setFromName(network.getName());
            ams.setFromEmail(Vars.supportEmail);
            ams.addRecipient(toUser.getEmail());

            // Creating subject
            ams.setSubject(
                    network.getName() + " digest with " +
                            digestTotalSharedItemCount + " messages in " +
                            digestTotalSmartGroupCount + " smart groups"
            );

            // Customizing the message with receiver information
            customizeMessage = EmailServices.customizeMessage(message, toUser);
            ams.setMessageText(customizeMessage);

            // Queueing the email
            AmazonMailQueue.queueEmail(ams);
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
