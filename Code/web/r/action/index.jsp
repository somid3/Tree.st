<%@ include file="../all.jsp"%>
<%
    Integer userId = StringUtils.parseInt(request.getParameter("uid"));
    String saltChecksum = StringUtils.parseString(request.getParameter("scs"));
    EmailActionEnum action = EmailActionEnum.getById(StringUtils.parseInt(request.getParameter("aid")));

    // Retrieving and validating user
    User user = UserDao.getByIdAndSaltChecksum(null, userId, saltChecksum);
    if (user == null)
        webUtils.redirect("/d/signout");



    /*********************
     * Global user actions
     *********************/

     if (action == EmailActionEnum.UNSUBSCRIBE_FROM_FIRST_PHOTO_UPLOAD_EMAILS) {

        UserIntegerSettingEnum.IS_UNSUBSCRIBED_FROM_FIRST_PHOTO_UPLOAD_EMAILS.setValueByUserId(userId, 1);

     }



    /*************************
     * User to network actions
     *************************/

    else if (action == EmailActionEnum.UNSUBSCRIBE_FROM_NEW_USER_LINK_NOTIFICATIONS) {

        Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
        UserToNetworkIntegerSettingEnum.IS_UNSUBSCRIBED_FROM_NEW_USER_LINK_EMAIL_NOTIFICATIONS.setValueByUserIdAndNetworkId(userId, networkId, 1);

    } else if (action ==  EmailActionEnum.UNSUBSCRIBE_FROM_NEW_SMART_GROUP_MAPPINGS_NOTIFICATIONS) {

        Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
        UserToNetworkIntegerSettingEnum.IS_UNSUBSCRIBED_FROM_NEW_SMART_GROUP_MAPPINGS_EMAIL_NOTIFICATIONS.setValueByUserIdAndNetworkId(userId, networkId, 1);

    } else if (action == EmailActionEnum.FLAG_SMART_GROUP) {

         Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
         Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
         UserToSmartGroupServices.changeState(UserToSmartGroupStateEnum.FLAGGED, networkId, smartGroupRef, userId);

    } else if (action ==  EmailActionEnum.UNSUBSCRIBE_FROM_NEW_SHARED_ITEM_NOTIFICATIONS) {

         Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
         UserToNetworkIntegerSettingEnum.NEW_SHARED_ITEM_DIGEST_EMAIL_RATE.setValueByUserIdAndNetworkId(userId, networkId, EmailNotificationRateEnum.NEVER.getId());

    } else if (action == EmailActionEnum.CHANGE_ACTIVE_SMART_GROUP_DIGEST_RATE) {

         Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
         EmailNotificationRateEnum rate = EmailNotificationRateEnum.getById(
                 StringUtils.parseInt(request.getParameter("rate"))
         );
         UserToNetworkIntegerSettingEnum.NEW_SHARED_ITEM_DIGEST_EMAIL_RATE.setValueByUserIdAndNetworkId(userId, networkId, rate.getId());

    } else if (action == EmailActionEnum.UNSUBSCRIBE_FROM_NEW_USER_MESSAGE_NOTIFICATIONS) {

        Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
        UserToNetworkIntegerSettingEnum.IS_UNSUBSCRIBED_FROM_USER_MESSAGE_EMAIL_NOTIFICATIONS.setValueByUserIdAndNetworkId(userId, networkId, 1);

    }



    Boolean persistent = false;

    // Login user persistently
    UserSession userSession = UserWebServices.authenticateAndCreateSession(webUtils, user.getEmail(), user.getPasswordHash(), persistent);

    // Install login cookies at client
    UserWebServices.installCookies(webUtils, user.getId(), userSession.getChecksum(), persistent);

    // Sending user to application
    webUtils.redirect("/d/updated/");
%>
