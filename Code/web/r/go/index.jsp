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
        UrlQuery query = new UrlQuery();

        if (request.getParameter("nid") != null)
            query.add("go_nid", request.getParameter("nid"));

        if (request.getParameter("sgr") != null)
            query.add("go_sgr", request.getParameter("sgr"));

        if (request.getParameter("sir") != null)
            query.add("go_sir", request.getParameter("sir"));

        if (request.getParameter("vuid") != null)
            query.add("go_vuid", request.getParameter("vuid"));


        wu.redirect("/d/app/?" + query);
    }
%>
