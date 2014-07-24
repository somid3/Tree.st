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
%>

<!DOCTYPE HTML>
<html>
<head>
    <title><%= Vars.name %></title>
    <%@ include file="../includes/google_analytics.jsp"%>
</head>
<script type="text/javascript" src="../js/jquery-1.9.0.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/jquery-ui-1.9.2.custom.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/global.js?<%= Vars.rev %>"></script>

<link rel=stylesheet type="text/css" href="../css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./css/basic.css?<%= Vars.rev %>">

<body>

<div id="logo">
    <a href="/"><img src="/d/assets/logo.png"></a>
</div>

<a href="/" class="no_deco">
    <div class="w300 center">
        <div class="signout">

            <div class="center"><img src="./img/done.png"></div>

            <br/>

            <div class="message vl_header dim">
                You have signed out successfully.</span>
            </div>

        </div>
   </div>
</a>

</body>
</html>