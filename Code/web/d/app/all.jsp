<%@ include file="../all.jsp" %>
<%

    WebUtils wu = new WebUtils(request, response);

    // Authenticate user session
    boolean wasAuthGood = UserWebServices.authenticateViaCookies(wu);

    // Was the authentication good?
    if (!wasAuthGood) {

        /* No -- that means user did not authenticate successfully */

        // Check sum authentication failed, send to log out page
        wu.redirect("/d/logout");
    }

    // Setting environment variables for the app
    Integer userId = wu.getCookieValueAsInteger("uid");
    String userChecksum = wu.getCookieValue("scs");
%>
