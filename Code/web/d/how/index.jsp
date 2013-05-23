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
<%@ include file="../includes/browser_check.jsp"%>
<div id="main">
    <div id="header">
        <div id="logo">
            <span class="sp_header highlight"><img src="/d/assets/logo.png"></span>
         </div>
    </div>

    <div id="container">

        <a name="finder"></a>
        <div class="video lg_shadow">
            <div class="desc">
                <span class="md_header dim">People Finder:</span>
                <span class="smd_text dim2">Discover how to create really complex searches on Tree</span>
            </div>
            <iframe src="http://player.vimeo.com/video/52804267?badge=0" width="500" height="281" frameborder="0" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe>
        </div>

        <a name="groups"></a>
        <div class="video lg_shadow">
            <div class="desc">
                <span class="md_header dim">Smart Groups:</span>
                <span class="smd_text dim2">Find out how to create dynamic 24/7 updating groups</span>
            </div>
            <iframe src="http://player.vimeo.com/video/52804264?badge=0" width="500" height="281" frameborder="0" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe>
        </div>

        <a name="share"></a>
        <div class="video lg_shadow">
            <div class="desc">
                <span class="md_header dim">Share:</span>
                <span class="smd_text dim2">Broadcast messages to all the members of a smart group</span>
            </div>
            <iframe src="http://player.vimeo.com/video/52804266?badge=0" width="500" height="281" frameborder="0" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe>
        </div>

        <a name="communities"></a>
        <div class="video lg_shadow">
            <div class="desc">
                <span class="md_header dim">Communities:</span>
                <span class="smd_text dim2">Learn more about how communities work on Tree</span>
            </div>
            <iframe src="http://player.vimeo.com/video/52805307?badge=0" width="500" height="281" frameborder="0" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe>
        </div>

        <a name="profile"></a>
        <div class="video lg_shadow">
            <div class="desc">
                <span class="md_header dim">Profile:</span>
                <span class="smd_text dim2">Learn how to update your profile</span>
            </div>
            <iframe src="http://player.vimeo.com/video/52804265?badge=0" width="500" height="281" frameborder="0" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe>
        </div>

    </div>

</div>

<%@ include file="../includes/footer.jsp"%>
</body>
</html>