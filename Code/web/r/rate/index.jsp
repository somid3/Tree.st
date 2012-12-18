<%@ page import="java.sql.SQLException" %>
<%@ include file="../all.jsp"%>
<%
    WebUtils wu = new WebUtils(request, response);

    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));

    Integer userId = StringUtils.parseInt(request.getParameter("uid"));
    String saltChecksum = StringUtils.parseString(request.getParameter("scs"));

    NetworkEventEnum event = NetworkEventEnum.getByValue(StringUtils.parseInt(request.getParameter("ne")));
    EmailNotificationRateEnum newRate = EmailNotificationRateEnum.getByValue(StringUtils.parseInt(request.getParameter("ra")));

    // Retrieving user
    User user = UserDao.getByIdAndSaltChecksum(null, userId, saltChecksum);

    if (user == null) {

        wu.redirect("/");

    } else {

        // Update or set the notification rate for the event
        EmailNotificationServices.setRate(
            networkId,
            smartGroupRef,
            userId,
            event,
            newRate);

        Boolean persistent = false;

        // Login user persistently
        UserSession userSession = UserWebServices.authenticateAndCreateSession(wu, user.getEmail(), user.getPasswordHash(), persistent);

        // Install login cookies at client
        UserWebServices.installCookies(wu, user.getId(), userSession.getChecksum(), persistent);

        // Sending user to application
        wu.redirect("/d/app?rate");

    }

%>
