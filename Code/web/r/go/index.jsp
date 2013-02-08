<%@ include file="../all.jsp"%>
<%
    Integer userId = StringUtils.parseInt(request.getParameter("uid"));
    String saltChecksum = StringUtils.parseString(request.getParameter("scs"));

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

        /* Filtering for parameters that go-feature accepts */
        String goHash = null;

        // Sending user to a shared item
        if (!StringUtils.isEmpty(request.getParameter("nid")) &&
            !StringUtils.isEmpty(request.getParameter("sgr")) &&
            !StringUtils.isEmpty(request.getParameter("sir")) )

            goHash = HashRouting.sharedItem(
                StringUtils.parseInt(request.getParameter("nid")),
                StringUtils.parseInt(request.getParameter("sgr")),
                StringUtils.parseInt(request.getParameter("sir")));

        // Sending user to a smart group
        else if (!StringUtils.isEmpty(request.getParameter("nid")) &&
                 !StringUtils.isEmpty(request.getParameter("sgr")))

            goHash = HashRouting.smartGroup(
                StringUtils.parseInt(request.getParameter("nid")),
                StringUtils.parseInt(request.getParameter("sgr")));

        // Sending user to view another user
        else if (!StringUtils.isEmpty(request.getParameter("nid")) &&
                 !StringUtils.isEmpty(request.getParameter("vuid")))

            goHash = HashRouting.member(
                StringUtils.parseInt(request.getParameter("nid")),
                StringUtils.parseInt(request.getParameter("vuid")),
                userId);

        // Sending user to network only
        else if (!StringUtils.isEmpty(request.getParameter("nid")))

            goHash = HashRouting.network(
                StringUtils.parseInt(request.getParameter("nid")));

        webUtils.redirect("/d/app/" + goHash);
    }
%>
