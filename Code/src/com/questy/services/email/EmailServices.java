package com.questy.services.email;

import com.questy.dao.*;
import com.questy.domain.*;
import com.questy.enums.*;
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

    public static String TO_USER_ID = "--to_user_id--";
    public static String TO_USER_SALT_CHECKSUM = "--to_user_salt_checksum--";
    public static String TO_USER_FIRST_NAME = "--to_user_first_name--";


    public static void confirmationEmail(Integer userId) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving user
        User user = UserDao.getById(conn, userId);

        // Creating message
        UrlQuery query = new UrlQuery();
        query.add("uid", userId);
        String message = UrlUtils.getUrlContents(GLOBAL_CREATOR_URL + "/email_confirmation.jsp?" + query);

        // Creating runnable to send email on new thread
        AmazonMailSender ser = new AmazonMailSender();
        ser.setMessageMine(EmailMimeEnum.HTML_UTF8);
        ser.setFromName(Vars.supportEmailName);
        ser.setFromEmail(Vars.supportEmail);
        ser.addRecipient(user.getEmail());
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

        final NetworkEventEnum event = NetworkEventEnum.USER_LINK_CREATION;

        // Currently non-transactional
        Connection conn = null;

        // Does the message receiver wish to receive these messages instantaneously?
        EmailNotificationRateEnum rate = EmailNotificationServices.getRate(networkId, SmartGroupDao.ANY_SMART_GROUP_REF, toUserId, event);
        if (rate != EmailNotificationRateEnum.INSTANTLY) return;

        // Retrieving user       Ê
        User fromUser = UserDao.getById(conn, fromUserId);
        User toUser = UserDao.getById(conn, toUserId);

        // Retrieving network
        Network network = NetworkDao.getById(conn, networkId);

         // Creating message
        UrlQuery query = new UrlQuery();
        query.add("ne", event.getId());
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
        EmailNotificationRateEnum callingRate,
        Integer networkId,
        Integer userId) throws SQLException {

        final NetworkEventEnum event = NetworkEventEnum.NEW_SMART_GROUP_MAPPINGS;

        // Currently non-transactional
        Connection conn = null;

        // Does the message receiver wish to receive these messages instantaneously?
        EmailNotificationRateEnum userPreferredRate = EmailNotificationServices.getRate(networkId, SmartGroupDao.ANY_SMART_GROUP_REF, userId, event);
        if (userPreferredRate != callingRate) return;

        // Retrieving user       Ê
        User user = UserDao.getById(conn, userId);

        // Retrieving network
        Network network = NetworkDao.getById(conn, networkId);

         // Creating message
        UrlQuery query = new UrlQuery();
        query.add("ne", event.getId());
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

        // Setting the correct event
        NetworkEventEnum event = NetworkEventEnum.AUTHOR_OF_SHARED_ITEM_NEW_COMMENT;

        // Creating email message
        UrlQuery query = new UrlQuery();
        query.add("ne", event.getId());
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

        // Does the message receiver wish to receive these messages instantaneously?
        EmailNotificationRateEnum rate = EmailNotificationServices.getRate(networkId, smartGroupRef, sharedItem.getUserId(), event);
        if (rate != EmailNotificationRateEnum.INSTANTLY)
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
        ser.setSubject("Someone commented on your shared item!");

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

        // Setting the correct event
        NetworkEventEnum event = NetworkEventEnum.AUTHOR_OF_COMMENT_FOLLOW_UP_COMMENT;

        // Creating email message
        UrlQuery query = new UrlQuery();
        query.add("ne", event.getId());
        query.add("nid", networkId);
        query.add("sgr", smartGroupRef);
        query.add("sir", sharedItemRef);
        String message = UrlUtils.getUrlContents(NETWORK_CREATOR_URL + "/new_shared_item.jsp?" + query);

        // Retrieve all shared comments of the shared item
        List<SharedComment> sharedComments = SharedCommentDao.getByNetworkIdAndSmartGroupRefAndSharedItemRef(null, networkId, smartGroupRef, sharedItemRef);

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

            // Does the message receiver wish to receive these messages instantaneously?
            rate = EmailNotificationServices.getRate(networkId, smartGroupRef, sharedComment.getUserId(), event);
            if (rate != EmailNotificationRateEnum.INSTANTLY)
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
            ser.setSubject("Someone added a commented after you did!");

            // Sending the email
            ser.setMessageText(EmailServices.customizeMessage(message, toUser));
            Thread thread = new Thread(ser);
            thread.start();

            // Add user to list of users who received a notification
            sentUsers.add(sharedComment.getUserId());
        }
    }

    public static void newSharedItemForSmartGroup(
            Integer networkId,
            Integer smartGroupRef,
            Integer sharedItemRef) throws SQLException {

        // Validating
        if (SmartGroupDao.isNetworkRef(smartGroupRef))
            throw new RuntimeException("A non-network smart group reference is required");

        // Setting the correct event
        NetworkEventEnum event = NetworkEventEnum.SMART_GROUP_NEW_SHARED_ITEM;

        // Creating email message
        UrlQuery query = new UrlQuery();
        query.add("ne", event.getId());
        query.add("nid", networkId);
        query.add("sgr", smartGroupRef);
        query.add("sir", sharedItemRef);
        String message = UrlUtils.getUrlContents(NETWORK_CREATOR_URL + "/new_shared_item.jsp?" + query);

        // Retrieve all members of the smart group
        List<UserToSmartGroup> usersToSmartGroup = UserToSmartGroupDao.getByNetworkIdAndSmartGroupRef(null, networkId, smartGroupRef);

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
        for (UserToSmartGroup userToSmartGroup : usersToSmartGroup) {

            // Is the user a member or has made the smart group a favorite
            if (userToSmartGroup.isFavoriteOrMember()) {

                // Does the message receiver wish to receive these messages instantaneously?
                rate = EmailNotificationServices.getRate(networkId, smartGroupRef, userToSmartGroup.getUserId(), event);
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

    }



    public static String createActionUrl(EmailActionEnum actionEnum, UrlQuery parameters) {

        UrlQuery query = new UrlQuery();
        query.add("uid", EmailServices.TO_USER_ID);
        query.add("scs", EmailServices.TO_USER_SALT_CHECKSUM);
        query.add("aid", actionEnum.getId());
        query.addAll(parameters);
        return "http://" + Vars.domain + "/r/action/?" + query;

    }

    @Deprecated
    public static String networkEventRateUrl (Integer networkId, Integer smartGroupRef, NetworkEventEnum event, EmailNotificationRateEnum rate) {

        UrlQuery query = new UrlQuery();
        query.add("uid", EmailServices.TO_USER_ID);
        query.add("scs", EmailServices.TO_USER_SALT_CHECKSUM);
        query.add("ne", event.getId());
        query.add("ra", rate.getId());
        query.add("nid", networkId);
        query.add("sgr", smartGroupRef);
        return "http://" + Vars.domain + "/r/rate/?" + query;

    }

    @Deprecated
    public static String networkEventRateLinks(Integer networkId, Integer smartGroupRef, NetworkEventEnum event) {

        StringBuilder builder = new StringBuilder();
        String hRateLink = null;
        for (EmailNotificationRateEnum rate : event.getAcceptedRates()) {

            hRateLink = HtmlUtils.createHref(rate.getName(), EmailServices.networkEventRateUrl (networkId, smartGroupRef, event, rate));
            builder.append(hRateLink).append(", ");

        }

        String out = null;
        if (builder.length() > 2)
            out = builder.substring(0, builder.length() - 2);
        else
            out = "";

        return out;
    }

    /**
     * All emails will contain keywords that are placeholders of the
     * user id, user checksum, user first name, among other variables
     *
     * This method updates the placeholders with the actual values
     * that belong to a user
     */
    public static String customizeMessage (String message, User user) {

        message = message.replaceAll(EmailServices.TO_USER_ID, user.getId().toString());
        message = message.replaceAll(EmailServices.TO_USER_SALT_CHECKSUM, user.getSaltChecksum());
        message = message.replaceAll(EmailServices.TO_USER_FIRST_NAME, user.getFirstName());

        return message;
    }

}
