<%
    // Authenticate user session
    boolean wasAuthGood = UserWebServices.authenticateViaCookies(webUtils);

    // Was the authentication good?
    if (!wasAuthGood) {

        /* No -- that means user did not authenticate successfully */

        // Check sum authentication failed, send to log out page
        webUtils.redirect("/d/signout");
    }

    // Setting environment variables for the app
    Integer meId = webUtils.getCookieValueAsInteger("uid");
    String myChecksum = webUtils.getCookieValue("scs");

    // Retrieving logged user
    User me = UserDao.getByIdAndSaltChecksum(null, meId, myChecksum);
    if (me == null)
        webUtils.redirect("/d/signout");

    // Is there a current network?
    Integer homeId = StringUtils.parseInt(request.getParameter("nid"));
    Network home = null;
    if (homeId != null)
        home = NetworkDao.getById(null, homeId);

    // Me to home mapping?
    UserToNetwork meToHome = null;
    if (home != null)
        meToHome = UserToNetworkDao.getByUserIdAndNetworkId(null, me.getId(), home.getId());

    // Ensuring the mapping has to exist
    if (home != null && meToHome == null)
        webUtils.redirect("/d/signout");

    // Ensuring user has not been blocked from a network
    if (meToHome != null &&
        meToHome.getBlockedOn() != null &&
        appDisableBlocked)
            webUtils.redirect("/d/signout");
%>
