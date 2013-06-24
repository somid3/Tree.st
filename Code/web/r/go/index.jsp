<%@ include file="../all.jsp"%>
<%
    Integer userId = StringUtils.parseInt(request.getParameter("uid"));
    String saltChecksum = StringUtils.parseString(request.getParameter("scs"));
    String goHash = StringUtils.parseString(request.getParameter("gh"));

    // Retrieving user by salt checksum
    User user = UserDao.getByIdAndSaltChecksum(null, userId, saltChecksum);

    if (user == null) {

        webUtils.redirect("/");

    } else {

        Boolean persistent = false;

        // Login user persistently
        UserSession userSession = UserWebServices.authenticateAndCreateSession(webUtils, user.getEmail(), user.getPasswordHash(), persistent);

        // Was a session created?
        if (userSession == null)
            webUtils.redirect("/");

        // Install login cookies at client
        UserWebServices.installCookies(webUtils, userId, userSession.getChecksum(), persistent);

        // Sending user to the appropriate in-app hash location
        webUtils.redirect("/d/app/" + goHash);
    }
%>
