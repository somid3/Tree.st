<%@ include file="../all.jsp"%>
<%
    WebUtils wu = new WebUtils(request, response);

    Integer userId = StringUtils.parseInt(request.getParameter("uid"));

    String checksum = StringUtils.parseString(request.getParameter("xcs"));

    // Attempting to confirm email
    try {

        // Attempting to confirm user email
        EmailConfirmationServices.confirmEmail(userId, checksum);

        // Email confirmation was successful, retrieve user
        User user = UserDao.getById(null, userId);

        Boolean persistent = false;

        // Login user persistently
        UserSession userSession = UserWebServices.authenticateAndCreateSession(wu, user.getEmail(), user.getPasswordHash(), persistent);

        // Install login cookies at client
        UserWebServices.installCookies(wu, user.getId(), userSession.getChecksum(), persistent);

        // Sending user to application
        wu.redirect("/d/app?confirmed");

    } catch (UIException e) {

        // Confirmation failed, checksum is incorrect
        wu.redirect("/d/logout");
    }

%>
