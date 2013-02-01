<% { %>
       <% BrowserAcceptanceEnum.Status status = BrowserAcceptanceEnum.Browser.getStatus(webUtils.getUserAgent());

       if (status == BrowserAcceptanceEnum.Status.DENIED)

           webUtils.redirect("/d/browser");

       else if (status == BrowserAcceptanceEnum.Status.BUGGY ||
                status == BrowserAcceptanceEnum.Status.UNKNOWN) { %>

           <%@ include file="m_browser_warning.jsp"%>

       <% } %>
<% } %>