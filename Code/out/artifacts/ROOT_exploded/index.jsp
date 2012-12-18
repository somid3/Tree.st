<%@ include file="all.jsp" %>
<%
    // Determine device, currently only desktop is supported
    WebUtils wu = new WebUtils(request,  response);
    wu.redirect("/d");
%>
