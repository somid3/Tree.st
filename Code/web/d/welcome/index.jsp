<%@ include file="./all.jsp" %>
<%
    WebUtils wu = new WebUtils(request, response);
%>

<!DOCTYPE HTML>
<html>
<head>
    <title><%= Vars.name %></title>
    <%@ include file="../includes/google_analytics.jsp"%>
</head>
<script type="text/javascript" src="../js/jquery-1.7.1.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/jquery-ui-1.8.18.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/global.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="./js/welcome.js?<%= Vars.rev %>"></script>

<link rel=stylesheet type="text/css" href="../css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./css/basic.css?<%= Vars.rev %>">

<body>

<% String header_path = "welcome"; %>
<%@ include file="./includes/header.jsp"%>

<div class="main-block">
    <div class="w800 center">
        <div class="block">
            <div class="main gg_header white">Get to Know Everyone.</div>
            <div class="sub vl_text white">Find & group people by their qualities &mdash; and create amazing bonds.</div>
        </div>
    </div>
</div>

<div class="attention square glow4">

    <div class="w800 center">
        <div id="playing-video" class="left shadow">
            <iframe src="http://player.vimeo.com/video/46590201?autoplay=true" width="500" height="281" frameborder="0" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe>
        </div>
        <div class="right">
            <div class="actions">
                <a href="#request">
                    <div class="action glow">
                        <img class="today" src="./img/today.png">
                        <div><span class="lg_header white">Start Your Community Today!</span></div>
                        <div><span class="smd_text white">Our engineers will help you at every step of the way. In less than 48 hours your solution &mdash; delivered.</span></div>
                    </div>
                </a>
                <br/>
                <a href="/d/hello" target="_new">
                    <div class="action">
                        <img class="tree" src="./img/tree.png">
                        <div><span class="lg_header white">Experience a Demo</span></div>
                        <div><span class="smd_text white">Find others around the world!</span></div>
                    </div>
                </a>
            </div>
        </div>
    </div>
</div>

<div class="w800 center">
    <div class="statement">
        <div class="main sp_header">Case Studies By Industry</div>
        <div class="sub smd_text dim">Tree.st can adapt to many needs, here are few examples</div>
    </div>
</div>

<div class="w800 center">
    <div class="solutions">
        <div class="solution shadow">
            <div class="title lg_header dim">Associations</div>
            <div class="title sm_text highlight2"><img src="./img/pdf.png"> Summary Brochure</div>
            <div class="desc sm_text dim">
                <div class="img"><img src="./img/associations.png"></div>
                <p>Save thousands of dollars every year and move your membership paper-based directory
                online.</p>

                <p>Offer a feature-rich searchable directory to your members.
                Attract more members, increase donations and reduce costs.</p>
            </div>
        </div>
        <div class="solution shadow">
            <div class="title md_header dim">Educational Institutions</div>
            <div class="title sm_text highlight2"><img src="./img/pdf.png"> Summary Brochure</div>
            <div class="desc sm_text dim">
                <div class="img"><img src="./img/education.png"></div>
                <p>Allow your students to find one another based on their interests, goals, and knowledge.</p>
                <p>Build a stronger alumni community, mentoring and advisorship programs.</p>
            </div>
        </div>
        <div class="solution shadow">
            <div class="title md_header dim">Conferences & Trade Shows</div>
            <div class="title sm_text highlight2"><img src="./img/pdf.png"> Summary Brochure</div>
            <div class="desc sm_text dim">
                <div class="img">
                    <img src="./img/conferences.png">
                </div>
                <p>Empower your participants to have purposeful, subject-specific, conversations by creating groups that join together individuals with common qualities.</p>
                <p>Participants specifically who they would like to meet and do business with.</p>
            </div>
    </div>
</div>

<div class="w800 center">
    <div class="statement">
        <div class="main sp_header">Our Four-Step Approach</div>
        <div class="sub smd_text dim">These simple steps will allow you to build a thriving community online.</div>
    </div>
</div>

<div class="w800 center">
    <div class="benefits">
            <div class="step shadow">
                <div class="title md_header dim">Gather The Details</div>
                <div class="desc sm_text dim">Ask the members of your community any question, and create a unique profile based on a tree of questions.</div>
                <div class="img">
                    <img src="./img/profile.png">
                </div>
            </div>
            <div class="step shadow">
                <div class="title md_header dim">Enable Searching</div>
                <div class="desc sm_text dim">Your members want to discover one another, our combinatorial search engine will allow your members to find exactly who they are looking for.</div>
                <div class="img">
                    <img src="./img/finder.png">
                </div>
            </div>
            <div class="step shadow">
                <div class="title md_header dim">"Smart" Groups</div>
                <div class="desc sm_text dim">Stop individually adding members to groups. Automatically group all your members based on their qualities.</div>
                <div class="img">
                    <img src="./img/groups.png">
                </div>
            </div>
        </a>
            <div class="step shadow">
                <div class="title md_header dim">Communicate</div>
                <div class="desc sm_text dim">Empower thematic conversations withing your Smart Groups via challenges and awards, and watch as your members share their interests.</div>
                <div class="img">
                    <img src="./img/communicate.png">
                </div>
            </div>
    </div>
</div>

<a name="request"></a>
<div class="w800 center">
    <div class="statement">
        <div class="main sp_header">So Let's Begin...</div>
        <div class="sub smd_text dim">Fill out the form below and one of our engineers will contact you in no time.</div>
    </div>
</div>

<div class="w800 center contact shadow">
    <img style="float: right" src="/d/assets/logo.png">
    <div id="wufoo-z7x3p9">
    <a href="http://treest.wufoo.com/forms/z7x3p9">Loading form...</a>.
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
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>


<%@ include file="./includes/footer.jsp"%>
</body>
</html>