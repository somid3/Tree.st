<%@ include file="./all.jsp" %>
<%
    WebUtils wu = new WebUtils(request, response);

    // Retrieving log in details
    String defaultEmail = wu.getCookieValue("ue");
    if (defaultEmail == null) defaultEmail = "";
%>

<!DOCTYPE HTML>
<html>
<head>
    <title><%= Vars.name %></title>
    <%@ include file="../includes/google_analytics.jsp"%>
</head>
<script type="text/javascript" src="../js/jquery-1.8.3.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/jquery-ui-1.9.2.custom.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/global.js?<%= Vars.rev %>"></script>

<link rel=stylesheet type="text/css" href="../css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./css/basic.css?<%= Vars.rev %>">

<body>
<%@ include file="../includes/browser_check.jsp"%>

<div id="logo">
    <a href="/"><img src="/d/assets/logo.png"></a>
</div>

<a href="/">
    <div class="canvas_container w300 center">
        <div class="updated">
            <div class="center"><img src="./img/done.png"></div>
            <br/>
            <div class="vl_header dim">Your account has been updated</div>
        </div>
   </div>
</a>

<script>

    // Bringing down the logo
    $(document).ready(function() {
        Animations.inTopAndBounce("#logo", 30);
    })

</script>

</body>
</html>