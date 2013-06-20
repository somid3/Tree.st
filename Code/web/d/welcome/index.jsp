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
<%@ include file="../includes/browser_check.jsp"%>
<a name="top"/>

<% String header_path = "welcome"; %>
<%@ include file="./includes/header.jsp"%>

<div class="main-block square">
    <div class="w700 center">
        <div class="block">
            <div class="main sp_header white">Allow your members to find each other via qualities that are unique to your organization</div>
            <div class="sub vl_text white">The fastest, easiest way to connect groups</div>

        </div>
    </div>
</div>


<div class="attention shadow w800 center">
    <div id="playing-video" class="left shadow">
        <iframe width="500" height="281" src="https://www.youtube.com/embed/pGOVuZ83x0A?rel=0&autoplay=1" frameborder="0" allowfullscreen></iframe>
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
        <div class="main sp_header">Let's Get Started!</div>
        <div class="sub smd_text dim">Fill out the form below and one of our engineers will contact you in no time.</div>
    </div>
</div>

<div class="w800 center contact shadow">
    <a href="#top"><img style="float: right" src="/d/assets/logo.png"></a>
    <div id="wufoo-z7x3p9">
    <a href="https://treest.wufoo.com/forms/z7x3p9">Loading form...</a>.
    </div>
    <script type="text/javascript">var z7x3p9;(function(d, t) {
    var s = d.createElement(t), options = {
    'userName':'treest',
    'formHash':'z7x3p9',
    'autoResize':true,
    'height':'828',
    'async':true,
    'header':'show'};
    s.src = ('https:' == d.location.protocol ? 'https://' : 'http://') + 'wufoo.com/scripts/embed/form.js';
    s.onload = s.onreadystatechange = function() {
    var rs = this.readyState; if (rs) if (rs != 'complete') if (rs != 'loaded') return;
    try { z7x3p9 = new WufooForm();z7x3p9.initialize(options);z7x3p9.display(); } catch (e) {}};
    var scr = d.getElementsByTagName(t)[0], par = scr.parentNode; par.insertBefore(s, scr);
    })(document, 'script');</script>
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