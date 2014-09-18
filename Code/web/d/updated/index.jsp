<%@ include file="./all.jsp" %>

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
    <a href="/"><img style="border-radius: 5px;" src="/d/assets/logo.png"></a>
</div>

<a href="/" class="no_deco">
    <div class="center" style="width: 340px;">
        <div class="updated">

            <div class="center"><img src="/d/assets/done.png"></div>

            <br/>

            <div class="message vl_header dim">
                Your account has been updated. <span class="highlight2">Click to continue.</span>
            </div>

        </div>
   </div>
</a>

</body>
</html>