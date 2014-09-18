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
<script type="text/javascript" src="./js/welcome.js?<%= Vars.rev %>"></script>

<link rel=stylesheet type="text/css" href="../css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./css/basic.css?<%= Vars.rev %>">

<body>
<a name="top"/>

<% String header_path = "welcome"; %>
<%@ include file="./includes/header.jsp"%>

<div class="main-block square">
    <div class="w700 center">
        <div class="block">
            <div class="main sp_header white">Allow your members to find each other via qualities that are unique to your organization</div>
            <div class="sub vl_text white">The fastest and easiest way to connect your members</div>
        </div>
    </div>
</div>


<div class="attention w800 center">
    <div id="playing-video" class="left">
        <iframe src="//player.vimeo.com/video/67458399" width="500" height="281" frameborder="0" webkitallowfullscreen mozallowfullscreen allowfullscreen></iframe>
    </div>
    <div class="right md_text dim">
        <br/>
        The most customizable community platform.<br/>
        <br/>
        With Treelift automatically cluster your members in a private and safe network, and enables them to
        easily find each other.<br/>
        <br/>
        View some more <a href="/d/how" class="md_text highlight2">demo videos</a>. For personalized demo, contact us.<br/>
    </div>
</div>

<a name="request"></a>
<div class="w600 center">
    <div class="statement">
        <div class="main sp_header">Let's connect your members today</div>
        <div class="sub lg_text dim">Call us at <span class="md_text">1-510-499-5497</span> or email us at <span class="md_text highlight2">hello@treelift.com</span></div>
    </div>
</div>



<%@ include file="../includes/footer.jsp"%>
</body>
</html>