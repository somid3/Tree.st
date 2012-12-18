<% { %>
       <% BrowserAcceptanceEnum.Status status = BrowserAcceptanceEnum.Browser.getStatus(wu.getUserAgent());

       if (status == BrowserAcceptanceEnum.Status.DENIED)

           wu.redirect("/d/browser");

       else if (status == BrowserAcceptanceEnum.Status.BUGGY ||
                status == BrowserAcceptanceEnum.Status.UNKNOWN) { %>

           <%@ include file="m_browser_warning.jsp"%>

       <% } %>
<% } %>