<%@ include file="../all.jsp"%>
<%
    WebUtils wu = new WebUtils(request, response);

    Integer userId = StringUtils.parseInt(request.getParameter("uid"));
    String saltChecksum = StringUtils.parseString(request.getParameter("scs"));

    // Retrieving user by salt checksum
    User user = UserDao.getByIdAndSaltChecksum(null, userId, saltChecksum);

    if (user == null) {

        wu.redirect("/");

    } else {

        Boolean persistent = false;

        // Login user persistently
        UserSession userSession = UserWebServices.authenticateAndCreateSession(wu, user.getEmail(), user.getPasswordHash(), persistent);

        // Install login cookies at client
        UserWebServices.installCookies(wu, userId, userSession.getChecksum(), persistent);

        // Sending user to application
        wu.redirect("/d/app?go_photo_upload=1");

    }
%>
