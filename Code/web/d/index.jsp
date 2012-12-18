<%@ include file="./all.jsp" %>
<%
    WebUtils wu = new WebUtils(request, response);

    // Ensuring browser is not denied
    BrowserAcceptanceEnum.Status status = BrowserAcceptanceEnum.Browser.getStatus(wu.getUserAgent());
    if (status == BrowserAcceptanceEnum.Status.DENIED)
        wu.redirect("/d/browser");

    // Authenticate user session by cookies
    boolean wasAuthGood = UserWebServices.authenticateViaCookies(wu);

    // Was the authentication good?
    if (wasAuthGood) {

        // Send user to app
        wu.redirect("/d/app");

    } else {

        // Send user to login
        wu.redirect("/d/welcome");

    }
%>
