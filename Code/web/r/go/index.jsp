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

        // Was a session created?
        if (userSession == null)
            wu.redirect("/");

        // Install login cookies at client
        UserWebServices.installCookies(wu, userId, userSession.getChecksum(), persistent);

        /* Filtering for parameters that go-feature accepts */
        String goHash = null;

        if (!StringUtils.isEmpty(request.getParameter("nid")) &&
            !StringUtils.isEmpty(request.getParameter("sgr")) &&
            !StringUtils.isEmpty(request.getParameter("sir")) )

            goHash = HashRouting.sharedItem(
                StringUtils.parseInt(request.getParameter("nid")),
                StringUtils.parseInt(request.getParameter("sgr")),
                StringUtils.parseInt(request.getParameter("sir")));

        else if (!StringUtils.isEmpty(request.getParameter("nid")) &&
                 !StringUtils.isEmpty(request.getParameter("sgr")))

            goHash = HashRouting.smartGroup(
                StringUtils.parseInt(request.getParameter("nid")),
                StringUtils.parseInt(request.getParameter("sgr")));

        else if (!StringUtils.isEmpty(request.getParameter("nid")) &&
                 !StringUtils.isEmpty(request.getParameter("vuid")))

            goHash = HashRouting.member(
                StringUtils.parseInt(request.getParameter("nid")),
                StringUtils.parseInt(request.getParameter("vuid")),
                userId);

        else if (!StringUtils.isEmpty(request.getParameter("nid")))

            goHash = HashRouting.smartGroups(
                StringUtils.parseInt(request.getParameter("nid")));

        wu.redirect("/d/app/" + goHash);
    }
%>
