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
<%@ include file="../includes/browser_check.jsp"%>

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
                    <div class="action">
                        <div><span class="lg_header white">Start Your Community</span></div>
                        <div><span class="md_text white">30-day free trial.</span></div>
                    </div>
                </a>
                <a href="/d/hello">
                    <div class="action">
                        <div><span class="lg_header white">Experience a Demo</span></div>
                        <div><span class="md_text white">Discover others around you</span></div>
                    </div>
                </a>
            </div>
        </div>
    </div>
</div>

<div class="w800 center">
    <div class="statement">
        <div class="main sp_header">Our Solutions</div>
        <div class="sub smd_text dim">Tree.st can adapt to many needs, here are few examples</div>
    </div>
</div>

<div class="w800 center">
    <div class="solutions">
        <div class="solution shadow">
            <div class="title lg_header dim">Searchable Directories</div>
            <div class="desc sm_text dim">Create a feature-rich directory for you organization and enable anyone to find others based on their knowledge and qualities.</div>
            <div class="img"><img src="./img/solution-search2.jpg"></div>
        </div>
        <div class="solution shadow">
            <div class="title md_header dim">Coaching & Mentoring</div>
            <div class="desc sm_text dim">Develop communities where you members can organize themselves by their needs and goals, and allow them to help one another.</div>
            <div class="img"><img src="./img/solution-mentoring2.jpg"></div>
        </div>
        <div class="solution shadow">
            <div class="title md_header dim">"Smart" Communities</div>
            <div class="desc sm_text dim">Empower your members to have purposeful, subject-specific, conversations by creating groups that join together individuals with common qualities.</div>
            <div class="img"><img src="./img/solution-smart.jpg"></div>
        </div>
</div>







<div class="w800 center">
    <div class="statement">
        <div class="main sp_header">Benefits & Demo Videos</div>
        <div class="sub smd_text dim">Click on any image to view our 1-minute demos</div>
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
                <div class="desc sm_text dim">Your members want to discover one another, our combinatorial search allows all your members to find who they are looking for.</div>
                <div class="img">
                    <img src="./img/finder.png">
                </div>
            </div>
            <div class="step shadow">
                <div class="title md_header dim">"Smart" Groups</div>
                <div class="desc sm_text dim">Stop individually adding members to groups. Group them automatically based on qualities.</div>
                <div class="img">
                    <img src="./img/groups.png">
                </div>
            </div>
        </a>
            <div class="step shadow">
                <div class="title md_header dim">Communicate</div>
                <div class="desc sm_text dim">Foster communication within "Smart" groups with challenges and awards, and see what's trending in each group!</div>
                <div class="img">
                    <img src="./img/communicate.png">
                </div>
            </div>
    </div>




    <a name="request"></a>
    <div class="w600 center contact shadow">
        <br/>
        <br/>
        <br/>
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

</div>

<%@ include file="./includes/footer.jsp"%>
</body>
</html>