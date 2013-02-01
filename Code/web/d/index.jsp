<%@ include file="./all.jsp" %>
<%
    // Ensuring browser is not denied
    BrowserAcceptanceEnum.Status status = BrowserAcceptanceEnum.Browser.getStatus(webUtils.getUserAgent());
    if (status == BrowserAcceptanceEnum.Status.DENIED)
        webUtils.redirect("/d/browser");

    // Authenticate user session by cookies
    boolean wasAuthGood = UserWebServices.authenticateViaCookies(webUtils);

    // Was the authentication good?
    if (wasAuthGood) {

        // Send user to app
        webUtils.redirect("/d/app");

    } else {

        // Send user to login
        webUtils.redirect("/d/welcome");
    }
%>
