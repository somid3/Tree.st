<%@ include file="../all.jsp" %>
<%
    WebUtils wu = new WebUtils(request, response);
%>

<!DOCTYPE HTML>
<html>
<head>
    <title><%= Vars.name %></title>
    <%@ include file="../../includes/google_analytics.jsp"%>
</head>
<script type="text/javascript" src="../../js/jquery-1.7.1.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../../js/jquery-ui-1.8.18.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../../js/global.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/welcome.js?<%= Vars.rev %>"></script>

<link rel=stylesheet type="text/css" href="../../css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./../css/basic.css?<%= Vars.rev %>">
<body>

<% String header_path = "features"; %>
<%@ include file="../includes/header.jsp"%>

<div class="main-block">
    <div class="w800 center">
        <div class="block">
            <div class="main gg_header white">Patent-pending Awesomeness!</div>
            <div class="sub vl_text white">All our features will make your community thrive!</div>
        </div>
    </div>
</div>

<div class="w800 center">
    <div class="statement">
        <div class="main sp_header">Features</div>
        <div class="sub md_text dim">An arsenal of unique features</div>
    </div>
</div>

<div class="w800 center">
    <div class="features">
        <div class="columns w800">
            <div class="column">
                <div class="feature-title shadow">
                    <div class="title md_header dim">Multi-layered Searching</div>
                    <div class="desc sm_text dim">Members can search for one another within an organization based on any combination of organization-specific criteria.</div>
                    <div class="img"><img src="./img/combi-searching.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">Social Analytics</div>
                    <div class="desc sm_text dim">Organization administrators can analyze and uncover member demographics, relationships, and activity like never before.</div>
                    <div class="img"><img src="./img/analytics.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">Email Digests</div>
                    <div class="desc sm_text dim">Enable your members to receive and digest data at a pace that fits their specific needs.</div>
                    <div class="img"><img src="./img/email-digest.png"></div>
                </div>
                <div class="feature-title shadow ">
                    <div class="title md_header dim">Mobile Friendly</div>
                    <div class="desc sm_text dim">All Tree.st services follow common practices enabling our technology to work on most mobile devices and tables.</div>
                    <div class="img"><img src="./img/mobile-friendly.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">24/7 On The Cloud</div>
                    <div class="desc sm_text dim">With a 99.9% up-time, Tree.st delivers the versatility and robustness comparable to enterprise-solution standards.</div>
                    <div class="img"><img src="./img/cloud.png"></div>
                </div>
            </div>
            <div class="column">
                <div class="feature-title shadow">
                    <div class="title md_header dim">Nested Communities</div>
                    <div class="desc sm_text dim">Administrators can create private sub-communities nested within an organization's larger Tree.st community based on factors such as department, team, location, etc.</div>
                    <div class="img"><img src="./img/nested-communities.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">Unique Profiles</div>
                    <div class="desc sm_text dim">Members can create social profiles with organization-specific criteria that you determine -- such as interests, research, expertise, department, skills, affiliations, etc.</div>
                    <div class="img"><img src="./img/unique-profiles.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">Email Notifications</div>
                    <div class="desc sm_text dim">Stop individually adding members to groups. Group them automatically based on qualities.</div>
                    <div class="img"><img src="./img/email-notifications.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">"Offline" Networking</div>
                    <div class="desc sm_text dim">Allow your members to discover one another online, and connect offline. Create a collaborative culture by connecting members with similar interests and goal.</div>
                    <div class="img"><img src="./img/social-networking.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">Anonymous Help</div>
                    <div class="desc sm_text dim">Members can answer sensitive questions annonymously, allowing anyone in the community to discover and message them without knowing their identity.</div>
                    <div class="img"><img src="./img/anonymous.png"></div>
                </div>
            </div>
            <div class="column">
                <div class="feature-title shadow">
                    <div class="title md_header dim">"Smart" Groups</div>
                    <div class="desc sm_text dim">This patented software tool will automatically enable your members to organize others within the organization based on criteria that is specific to the individual.</div>
                    <div class="img"><img src="./img/smart-groups.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">Gamification</div>
                    <div class="desc sm_text dim">Incentize participation in your community by keeping track of member actions via points. Later, reward members who are effective collaborators.</div>
                    <div class="img"><img src="./img/gamification.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">Decision Tree</div>
                    <div class="desc sm_text dim">Minimize the number of questions each member has to answer by creating a decision tree that guides your members and only gathers the most important details.</div>
                    <div class="img"><img src="./img/decision-tree.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">Message Voting</div>
                    <div class="desc sm_text dim">Recognize your most impactful members by empowering others to vote on the information that is the most helpful to the entire community.</div>
                    <div class="img"><img src="./img/message-voting.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">100% Private</div>
                    <div class="desc sm_text dim">Each Tree.st is completely private. Control who has access to your community and the information each member can see.</div>
                    <div class="img"><img src="./img/private.png"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../includes/footer.jsp"%>
</body>
</html>