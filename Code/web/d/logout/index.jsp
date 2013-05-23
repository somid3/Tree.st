<%@ include file="./all.jsp" %>
<%
    // Removing cookie with user id
    webUtils.deleteCookieByName("uid");

    // Removing cookie with user checksum
    webUtils.deleteCookieByName("scs");

    // Removing cookie with user's email
    webUtils.deleteCookieByName("ue");

    // Removing cookie with user session checksum
    webUtils.deleteCookieByName("uscs");

    // Sending user to login page
    webUtils.redirect("/");
%>
