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
            <div class="main gg_header white">Find anyone.</div>
            <div class="sub vl_text white">Discover & group people by their qualities<br/>&mdash; and create amazing connections.</div>
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
                        <img id="join" class="today" src="./img/today.png">
                        <div><span class="lg_header white">Start Your Community Today!</span></div>
                        <div><span class="smd_text white">Our engineers will help you every step of the way. In less than 48 hours &mdash; your solution delivered.</span></div>
                    </div>
                </a>

                <br/>

                <a href="/colors" target="_new">
                    <div class="action">
                        <img class="demo" src="./img/muffin.png">
                        <div><span class="lg_header white">Experience a Demo</span></div>
                        <div><span class="smd_text white">Connect with others around the world via their favorite colors and foods!</span></div>
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
    <div class="solutions">
        <div class="solution shadow">
            <div class="title lg_header dim">Associations</div>
            <div class="title sm_text highlight2"><img src="./img/pdf.png"> Summary Brochure</div>
            <div class="desc sm_text dim">
                <div class="img"><img src="./img/associations.png"></div>
                <p>Save thousands of dollars every year and move your membership paper-based directory
                online.</p>

                <p>Offer a feature-rich searchable directory to your members.
                Attract more members, increase donations, and reduce costs.</p>
            </div>
        </div>
        <div class="solution shadow">
            <div class="title md_header dim">Educational Institutions</div>
            <div class="title sm_text highlight2"><img src="./img/pdf.png"> Summary Brochure</div>
            <div class="desc sm_text dim">
                <div class="img"><img src="./img/education.png"></div>
                <p>Allow your students to find one another and communicate based on their interests, goals, and knowledge.</p>
                <p>Build a stronger alumni community, streamline research efforts, and create mentorship opportunities.</p>
            </div>
        </div>
        <div class="solution shadow">
            <div class="title md_header dim">Conferences & Trade Shows</div>
            <div class="desc sm_text dim">
                <div class="img">
                    <img src="./img/conferences.png">
                </div>
                <p>Participants can seamlessly discover and connect with specific individuals before they even arrive on site.</p>
                <p>Empower your participants to have purposeful conversations by uniting groups with shared qualities.</p>
            </div>
        </div>
    </div>
    <div class="solutions">
        <div class="solution shadow">
            <div class="title lg_header dim">Mentoring Groups</div>
            <div class="desc sm_text dim">
                <div class="img"><img src="./img/mentoring.png"></div>
                <p>Automatically find the best mentor for a mentee.</p>

                <p>Easily discover the needs of all your mentees and group them together so that they may also help one another.</p>
            </div>
        </div>
        <div class="solution shadow">
            <div class="title md_header dim">Healthcare & Biotech</div>
            <div class="title sm_text highlight2"><img src="./img/pdf.png"> Summary Brochure</div>
            <div class="desc sm_text dim">
                <div class="img"><img src="./img/health.png"></div>
                <p>Create patient, practitioner, and research communities that improve healthcare outcomes.</p>
                <p>Gain valuable insights and improve care by allowing patients, care-givers, and practitioners to focus on subject-specific conversations within private communities.</p>
            </div>
        </div>
        <div class="solution shadow">
            <div class="title md_header dim">Business Enterprises</div>
            <div class="title sm_text highlight2"><img src="./img/pdf.png"> Summary Brochure</div>
            <div class="desc sm_text dim">
                <div class="img">
                    <img src="./img/business.png">
                </div>
                <p>Break business silos and allow different functional groups to innovate together.</p>
                <p>Drive business-value by leveraging your human capital to find solutions to multi-disciplinary problems.</p>
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
        </a>
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