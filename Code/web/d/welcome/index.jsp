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
            <div class="main gg_header white">Find anyone in your organization</div>
            <div class="sub vl_text white">Discover & group your members by their qualities<br/> &mdash; and create amazing connections</div>
        </div>
    </div>
</div>

<div class="attention square glow4">

    <div class="w800 center">
        <div id="playing-video" class="left shadow">
            <iframe src="http://player.vimeo.com/video/56698814?autoplay=true" width="500" height="281" frameborder="0" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe>
        </div>
        <div class="right">
            <div class="actions">
                <a href="#request">
                    <div class="action glow">
                        <img class="today" src="./img/target.png">
                        <div><span class="lg_header white">Request a free demo!</span></div>
                        <div><span class="smd_text white">One of our engineers will walk you through a personalized demo</span></div>
                    </div>
                </a>

                <br/>

                <a href="/foods" target="_new">
                    <div class="action">
                        <img class="demo" src="./img/tree.png">
                        <div><span class="lg_header white">Click here to test us out:</span></div>
                        <div><span class="smd_text white">Find others around the world by your food preferences!</span></div>
                    </div>
                </a>
            </div>
        </div>
    </div>
</div>

<div class="w800 center">
    <div class="statement">
        <div class="main sp_header">Specific To Your Organization</div>
        <div class="sub smd_text dim">Tree.st can adapt to any industry, here are a few examples:</div>
    </div>
</div>

<div class="w800 center">
    <div class="four_steps">
        <div class="step shadow">
            <a href="./assets/associations.pdf" target="_new">
                <img class="persona" src="./img/lawyer.png">
            </a>
            <div class="title lg_header dim">Associations</div>
            <a href="./assets/associations.pdf" target="_new">
                <div class="title sm_text highlight2"><img src="./img/pdf.png"> Uses & Benefits</div>
            </a>

            <div class="desc sm_text dim">
                <p>Save thousands of dollars every year and move your membership paper-based directory online.</p>
            </div>
        </div>
        <div class="step shadow">
            <a href="./assets/educational_institutions.pdf" target="_new">
                <img class="persona" src="./img/teacher.png">
            </a>
            <div class="title md_header dim">Educational Institutions</div>
            <a href="./assets/educational_institutions.pdf" target="_new">
                <div class="title sm_text highlight2"><img src="./img/pdf.png"> Uses & Benefits</div>
            </a>
            <div class="desc sm_text dim">
                <p>Allow your students to find one another and communicate based on their interests, goals, and knowledge.</p>
            </div>
        </div>

        <div class="step shadow">
            <a href="./assets/medical_professionals.pdf" target="_new">
                <img class="persona" src="./img/nurse.png">
            </a>
            <div class="title md_header dim">Medical Professionals</div>
            <a href="./assets/medical_professionals.pdf" target="_new">
                <div class="title sm_text highlight2"><img src="./img/pdf.png"> Uses & Benefits</div>
            </a>
            <div class="desc sm_text dim">
                <p>Create patient, practitioner, and research communities that improve healthcare outcomes.</p>
            </div>
        </div>
        <div class="step shadow">
            <a href="./assets/businesses.pdf" target="_new">
                <img class="persona" src="./img/manager.png">
            </a>
            <div class="title md_header dim">Business Enterprises</div>
            <a href="./assets/businesses.pdf" target="_new">
                <div class="title sm_text highlight2"><img src="./img/pdf.png"> Uses & Benefits</div>
            </a>
            <div class="desc sm_text dim">
                <p>Break business silos and allow different functional groups to innovate together.</p>
            </div>
        </div>
    </div>
</div>

<div class="w800 center">
    <div class="statement">
        <div class="main sp_header">Our Four-Step Approach</div>
        <div class="sub smd_text dim">These simple steps will allow you to build a dynamic community.</div>
    </div>
</div>

<div class="w800 center">
    <div class="four_steps">
        <div class="step shadow">
            <div class="title md_header dim">Gather The Details</div>
            <div class="desc sm_text dim">Ask the members of your community any question, and create a unique profile based on a tree of questions.</div>
            <div class="img">
                <img src="./img/profile.png">
            </div>
        </div>
        <div class="step shadow">
            <div class="title md_header dim">Enable Searching</div>
            <div class="desc sm_text dim">Your members want to discover one another, our combinatorial search engine will enable your members to find exactly who they are looking for.</div>
            <div class="img">
                <img src="./img/finder.png">
            </div>
        </div>
        <div class="step shadow">
            <div class="title md_header dim">Build "Smart" Groups</div>
            <div class="desc sm_text dim">Stop individually adding members to groups. Automatically group all your members based on their qualities.</div>
            <div class="img">
                <img src="./img/groups.png">
            </div>
        </div>
        <div class="step shadow">
            <div class="title md_header dim">Collaborate</div>
            <div class="desc sm_text dim">Empower meaningful conversations within your Smart Groups via challenges and rewards, and watch as your members communicate like never before.</div>
            <div class="img">
                <img src="./img/communicate.png">
            </div>
        </div>
    </div>
</div>

<a name="request"></a>
<div class="w800 center">
    <div class="statement">
        <div class="main sp_header">So, Let's Get Started...</div>
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