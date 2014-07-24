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


<div class="attention shadow w800 center">
    <div id="playing-video" class="left shadow">
        <iframe width="500" height="281" src="https://www.youtube.com/embed/pGOVuZ83x0A?rel=0&modestbranding=1" frameborder="0" allowfullscreen></iframe>
    </div>
    <div class="right">
        <div class="actions">

            <a href="#request" class="no_deco">
                <div id="request-demo" class="action shadow">
                    <img class="free" src="./img/free.png">
                    <div><span class="md_header white underline">Request a Demo</span></div>
                    <div><span class="smd_text white">Learn how we can add value to your organization</span></div>
                </div>
            </a>

            <div class="or shadow">
                <span class="sm_header white">Or</span>
            </div>

            <a href="./start" class="no_deco">
                <div id="start-today" class="action shadow">
                    <div><span class="md_header white underline">Start Today:</span></div>
                    <div><span class="smd_text white">Create your own community in under a minute</span></div>
                </div>
            </a>

        </div>
    </div>
</div>

<a name="request"></a>
<div class="w600 center">
    <div class="statement">
        <div class="main sp_header">Let's Get Started</div>
        <div class="sub smd_text dim">Fill out the form below and one of our engineers will contact you in no time.</div>
    </div>
</div>

<div class="w800 center contact shadow">
    <iframe height="500" allowTransparency="true" frameborder="0" scrolling="no" style="width:100%; border:none;" src="https://treest.wufoo.com/embed/z7x3p9/"></iframe>
</div>

<script type="text/javascript">

    $("#request-demo").mouseenter( function() {
        Animations.bounce("#request-demo");
    })

    $("#start-today").mouseenter( function() {
        Animations.bounce("#start-today");
    })

</script>

<%@ include file="../includes/footer.jsp"%>
</body>
</html>