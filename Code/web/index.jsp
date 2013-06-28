<%@ include file="all.jsp" %>
<%
    // Is the user logged in an should be sent to the application?
    boolean wasAuthGood = UserWebServices.authenticateViaCookies(webUtils);
    if (wasAuthGood) {

        // Send user to app
        webUtils.redirect("/d/app");
    }

    // Is the requested site the parent company?
    if (webUtils.getRequestedDomain().equals(Vars.domain)) {

        // Yes, send user to parent company details
        webUtils.redirect("/d/welcome");
    }

    // Send user to the requested network
    Network network = UrlRouter.getNetworkByDomain(webUtils);







%>

show header<br/>
show menu<br/>
iitems<br/>
