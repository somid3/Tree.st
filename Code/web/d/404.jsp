<%@ include file="./all.jsp" %>
<%
    // Log 404
    String path = webUtils.getRequestedPath();
    if (!Vars.isInProduction())
        System.out.println("*** 404 NOT FOUND *** " + path);

    // Sending user to real 404 page
    webUtils.redirect("/d/welcome?404");
%>