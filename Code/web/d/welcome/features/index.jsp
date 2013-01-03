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
<%@ include file="../../includes/browser_check.jsp"%>

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
                    <div class="title md_header dim">Unique Profiles</div>
                    <div class="desc sm_text dim">Ask the members of your community any question, and create a  of your community any question, and create a unique profile based on a tree of questions.</div>
                    <div class="img"><img src="./img/unique-profiles.png"></div>
                </div>
                <div class="feature-title shadow ">
                    <div class="title md_header dim">Mobile Friendly</div>
                    <div class="desc sm_text dim">Your members want to discover one another, our combinational search allows all ey are looking for.</div>
                    <div class="img"><img src="./img/mobile-friendly.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">24/7 On The Cloud</div>
                    <div class="desc sm_text dim">Stop individually adding members to gndividually adding members to groups. Group them automatically based on qto groups. Group them automatically based on qualities.</div>
                    <div class="img"><img src="./img/cloud.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">Email Digests</div>
                    <div class="desc sm_text dim">Foster communicatio within "Smart" groups with challenges and awards, and see what's trending in each grouptrending in each group!</div>
                    <div class="img"><img src="./img/email-digest.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">Anonymous Help</div>
                    <div class="desc sm_text dim">Foster communicatio within "Smart" groups with challenges and awards, and see what's trending in each grouptrending in each group!</div>
                    <div class="img"><img src="./img/anonymous.png"></div>
                </div>
            </div>
            <div class="column">
                <div class="feature-title shadow">
                    <div class="title md_header dim">Combinatorial Searching</div>
                    <div class="desc sm_text dim">Ask the members of your community any question, and create a unique profile based on a tree of questionsyour community any question, and create a unique profile based on a tree of questions.</div>
                    <div class="img"><img src="./img/combi-searching.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">Gamification</div>
                    <div class="desc sm_text dim">Your members want to discover your members to find who they are looking for.</div>
                    <div class="img"><img src="./img/gamification.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">Email Notifications</div>
                    <div class="desc sm_text dim">Stop individually adding members to groups. Group them automatically based on qualities.</div>
                    <div class="img"><img src="./img/email-notifications.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">Social Networking</div>
                    <div class="desc sm_text dim">Foster communicatio within "Smart" groups with challenges and awards, and see what's trending in each art" groups with challenges and awards, and see what's trending in each group!</div>
                    <div class="img"><img src="./img/social-networking.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">Nested Communities</div>
                    <div class="desc sm_text dim">Foster communicatio within "Smart" groups with challenges and awards, and see what's trending in each art" groups with challenges and awards, and see what's trending in each group!</div>
                    <div class="img"><img src="./img/nested-communities.png"></div>
                </div>
            </div>
            <div class="column">
                <div class="feature-title shadow">
                    <div class="title md_header dim">Decision Tree</div>
                    <div class="desc sm_text dim">Ask the members of your community any question, and create a unique profile based on a tree of questions.</div>
                    <div class="img"><img src="./img/decision-tree.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">Social Graphs & Analytics</div>
                    <div class="desc sm_text dim">Your members want to discover one another, our combinational search allows all your members to find who they are lookinrch allows all your members to find who they are lookinrch allows all your members to find who they are looking for.</div>
                    <div class="img"><img src="./img/analytics.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">"Smart" Groups</div>
                    <div class="desc sm_text dim">Stop individually adding members to groups. Group them automatically based on qualities.</div>
                    <div class="img"><img src="./img/smart-groups.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">Message Voting</div>
                    <div class="desc sm_text dim">Foster communicatio within "Smart" groups with challenges and awards, and see what's trending in each group!</div>
                    <div class="img"><img src="./img/message-voting.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">Customization</div>
                    <div class="desc sm_text dim">Foster communicatio within "Smart" groups with challenges and awards, and see what's trending in each group!</div>
                    <div class="img"><img src="./img/customization.png"></div>
                </div>
                <div class="feature-title shadow">
                    <div class="title md_header dim">100% Private</div>
                    <div class="desc sm_text dim">Foster communicatio within "Smart" groups with challenges and awards, and see what's trending in each group!</div>
                    <div class="img"><img src="./img/private.png"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../includes/footer.jsp"%>
</body>
</html>