<%@ include file="./all.jsp" %>
<%
    WebUtils wu = new WebUtils(request, response);
    
    // Removing cookie with user id
    wu.deleteCookieByName("uid");

    // Removing cookie with user checksum
    wu.deleteCookieByName("scs");

    // Removing cookie with user's email
    wu.deleteCookieByName("ue");

    // Removing cookie with user session checksum
    wu.deleteCookieByName("uscs");

    // Sending user to root
    wu.redirect("/");
%>
