<%@ include file="../all.jsp"%>
<%
    WebUtils wu = new WebUtils(request, response);

    Integer userId = StringUtils.parseInt(request.getParameter("uid"));
    String saltChecksum = StringUtils.parseString(request.getParameter("scs"));
    EmailActionEnum action = EmailActionEnum.getById(StringUtils.parseInt(request.getParameter("aid")));

    // Retrieving and validating user
    User user = UserDao.getByIdAndSaltChecksum(null, userId, saltChecksum);
    if (user == null)
        wu.redirect("/d/logout");



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

    } else if (action ==  EmailActionEnum.UNSUBSCRIBE_FROM_NEW_SMART_GROUP_MAPPINGS) {

        Integer networkId = StringUtils.parseInt(request.getParameter("nid"));

    } else if (action == EmailActionEnum.FLAG_SMART_GROUP) {

         Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
         Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));

    } else if (action == EmailActionEnum.CHANGE_MEMBER_OR_FAVORITE_SMART_GROUP_DIGEST_RATE) {

         Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
         EmailNotificationRateEnum rate = EmailNotificationRateEnum.EVERY_THREE_DAYS.

    };



    Boolean persistent = false;

    // Login user persistently
    UserSession userSession = UserWebServices.authenticateAndCreateSession(wu, user.getEmail(), user.getPasswordHash(), persistent);

    // Install login cookies at client
    UserWebServices.installCookies(wu, user.getId(), userSession.getChecksum(), persistent);

    // Sending user to application
    wu.redirect("/d/app?updated");



%>
