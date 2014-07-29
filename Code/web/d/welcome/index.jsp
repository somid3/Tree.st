<%@ include file="./all.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><%= Vars.name %></title>
    <%--<%@ include file="../includes/google_analytics.jsp"%>--%>
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
            <div class="sub vl_text white">The fastest, easiest, and safest way to connect your human resources</div>
        </div>
    </div>
</div>


<div class="attention w800 center">
    <div id="playing-video" class="left">
        <iframe width="500" height="281" src="https://www.youtube.com/embed/pGOVuZ83x0A?rel=0&modestbranding=1" frameborder="0" allowfullscreen></iframe>
    </div>

</div>

<a name="request"></a>
<div class="w600 center">
    <div class="statement">
        <div class="main sp_header">Contact Us Today</div>
        <div class="sub md_text dim">Call us at 1-510-499-5497 or email at <a href="mailto:hello@treelift.com">hello@treelift.com</a></div>
    </div>
</div>

<%@ include file="../includes/footer.jsp"%>
</body>
</html>