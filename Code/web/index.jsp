<%@ include file="all.jsp" %>
<%
    // Retrieve network by domain
    Network network = UrlRouter.getNetworkByDomain(webUtils);

    // Is the user logged in an should be sent to the application?
    boolean wasAuthGood = UserWebServices.authenticateViaCookies(webUtils);
    if (wasAuthGood) {

        Integer userId = webUtils.getCookieValueAsInteger("uid");

        // Is the user part of this network?
        UserToNetwork utn = UserToNetworkDao.getByUserIdAndNetworkId(null, userId, network.getId());
        if (utn != null) {

            // Yes, send user to application with network initial hash
            webUtils.redirect("/d/app" + NetworkServices.getInitialHash(userId, network.getId()));
        }
    }

    if (network != null)
        webUtils.redirect("/p/about/");
    else
        webUtils.redirect("/d/welcome/");
%>