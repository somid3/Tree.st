<%@ include file="../all.jsp"%>
<%
    WebUtils wu = new WebUtils(request, response);

    Integer userId = StringUtils.parseInt(request.getParameter("uid"));
    String saltChecksum = StringUtils.parseString(request.getParameter("scs"));
    GlobalEventEnum globalEvent = GlobalEventEnum.getByValue(StringUtils.parseInt(request.getParameter("ge")));

    // Retrieving user
    User user = UserDao.getByIdAndSaltChecksum(null, userId, saltChecksum);

    if (user == null) {

        wu.redirect("/");

    } else {

        // Does the user already contain a stop for this email type?
        EmailStop es = EmailStopDao.getByUserIdAndEmailEvent(null, user.getId(), globalEvent);
        if (es == null) {

            // Adding the email stop request
            EmailStopDao.insert(null, user.getId(), globalEvent);
        }

        Boolean persistent = false;

        // Login user persistently
        UserSession userSession = UserWebServices.authenticateAndCreateSession(wu, user.getEmail(), user.getPasswordHash(), persistent);

        // Install login cookies at client
        UserWebServices.installCookies(wu, user.getId(), userSession.getChecksum(), persistent);

        // Sending user to application
        wu.redirect("/d/app?stop");

    }

%>
