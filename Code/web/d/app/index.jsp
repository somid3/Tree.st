<%@ include file="./all.jsp" %>
<%
    // If in development mode, make sure all scripts refresh
    Vars.setDevelopmentRev();

    // Retrieving logged user
    User user = UserDao.getById(null, userId);

    // Retrieving user's tooltip situation
    TooltipEnum tooltip = TooltipServices.getNextTooltipByUserId(userId);

    // Tooltip include variables
    TooltipEnum tt_l_tooltip = null;
    Integer tt_l_width = null;
    Integer tt_l_left = null;
    Integer tt_l_top = null;
    String tt_l_title = null;
    String tt_l_message = null;
    String tt_l_gotit = null;
    String tt_l_direction = null;
    String tt_l_style = null;
    String tt_l_callback = null;

%>
<!DOCTYPE HTML>
<html>
<head>
    <title><%= Vars.name %></title>
    <%@ include file="../includes/google_analytics.jsp"%>
</head>

<script type="text/javascript" src="js/routie-0.3.0.min.js?<%= Vars.rev %>"></script>

<script type="text/javascript" src="../js/jquery-1.9.0.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/jquery-ui-1.9.2.custom.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/global.js?<%= Vars.rev %>"></script>

<script type="text/javascript" src="./modules/networks/js/menus.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="./modules/networks/js/dashboard.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="./modules/networks/js/points.js?<%= Vars.rev %>"></script>
<link rel=stylesheet type="text/css" href="./modules/networks/css/basic.css?<%= Vars.rev %>">

<script type="text/javascript" src="./modules/finder/js/finder.js?<%= Vars.rev %>"></script>
<link rel=stylesheet type="text/css" href="./modules/finder/css/basic.css?<%= Vars.rev %>">

<script type="text/javascript" src="./user_panel/js/user_panel.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="./user_panel/user_general/js/user_general_dashboard.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="./user_panel/user_networks/js/user_networks_dashboard.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="./user_panel/user_photos/js/user_photos.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="./user_panel/user_photos/js/jquery-jcrop-0.9.11.min.js?<%= Vars.rev %>"></script>
<link rel=stylesheet type="text/css" href="./user_panel/css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./user_panel/user_photos/css/jquery-jcrop-0.9.10.min.css?<%= Vars.rev %>">

<script type="text/javascript" src="./modules/user_links/js/user_links.js?<%= Vars.rev %>"></script>
<link rel=stylesheet type="text/css" href="./modules/user_links/css/basic.css?<%= Vars.rev %>">

<script type="text/javascript" src="./modules/profiles/js/dashboard.js?<%= Vars.rev %>"></script>
<link rel=stylesheet type="text/css" href="./modules/profiles/css/basic.css?<%= Vars.rev %>">

<script type="text/javascript" src="./modules/smart_groups/js/smart_search.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="./modules/smart_groups/js/dashboard.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="./modules/smart_groups/js/smart_group_tools.js?<%= Vars.rev %>"></script>
<link rel=stylesheet type="text/css" href="./modules/smart_groups/css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./modules/smart_groups/css/criterion.css?<%= Vars.rev %>">

<script type="text/javascript" src="./modules/share/js/shared_item.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="./modules/share/js/shared_comment.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="./modules/share/js/shared_vote.js?<%= Vars.rev %>"></script>
<link rel=stylesheet type="text/css" href="./modules/share/css/basic.css?<%= Vars.rev %>">

<script type="text/javascript" src="./modules/collaborate/js/question_display.js?<%= Vars.rev %>"></script>
<link rel=stylesheet type="text/css" href="./modules/collaborate/css/basic.css?<%= Vars.rev %>">

<script type="text/javascript" src="./modules/tooltips/js/tooltips.js?<%= Vars.rev %>"></script>
<link rel=stylesheet type="text/css" href="./modules/tooltips/css/basic.css?<%= Vars.rev %>">

<script type="text/javascript" src="./modules/all/js/dashboard.js?<%= Vars.rev %>"></script>
<link rel=stylesheet type="text/css" href="./modules/all/css/basic.css?<%= Vars.rev %>">

<link rel=stylesheet type="text/css" href="../css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./css/basic.css?<%= Vars.rev %>">

<body>
<%@ include file="../includes/browser_check.jsp"%>

<div id="main">

    <% if (tooltip != TooltipEnum.END) {%>

        <div id="tooltips">

            <script type="text/javascript">
                TT = new Tooltips();
            </script>

            <%
                tt_l_tooltip = TooltipEnum.WELCOME;
                tt_l_width = 400;
                tt_l_left = 300;
                tt_l_top = 30;
                tt_l_title = "Intro. 1 of 4";
                tt_l_message = "Welcome! This brief 4 step introduction will show you around.<br/><br/>Have fun!";
                tt_l_gotit = "Let's continue...";
                tt_l_direction = "tip_none";
                tt_l_style = null;
            %>
            <%@ include file="./modules/tooltips/includes/tt_l_tooltip.jsp" %>

            <%
                tt_l_tooltip = TooltipEnum.COMMUNITIES;
                tt_l_width = 400;
                tt_l_left = 200;
                tt_l_top = 200;
                tt_l_title = "Intro. 2 of 4";
                tt_l_message = "These are your communities -- each community will ask you different questions and allow you to find people using different qualities.<br/><br/>Green dots mean you have unanswered questions left.<br/><br/>Try changing your active community, see what happens...";
                tt_l_gotit = "Next!";
                tt_l_direction = "tip_left";
                tt_l_style = null;
            %>
            <%@ include file="./modules/tooltips/includes/tt_l_tooltip.jsp" %>

            <%
                tt_l_tooltip = TooltipEnum.MODULES;
                tt_l_width = 400;
                tt_l_left = 200;
                tt_l_top = 50;
                tt_l_title = "Intro. 3 of 4";
                tt_l_message = "Each community is private and safe. For each community you have a separate profile.<br/><br/>Each community also has really cool search and grouping tools. Like, check out the 'smart groups!'";
                tt_l_gotit = "Nice!";
                tt_l_direction = "tip_left";
                tt_l_style = null;
            %>
            <%@ include file="./modules/tooltips/includes/tt_l_tooltip.jsp" %>


            <%
                tt_l_tooltip = TooltipEnum.EVERYTHING;
                tt_l_width = 400;
                tt_l_left = 120;
                tt_l_top = 65;
                tt_l_title = "Intro. 4 of 4";
                tt_l_message = "The 'Qualities' button and search bar allow you to quickly find people by their unique traits. You can also search for shared messages or people by their name or email.<br/><br/>Click on 'Qualities' and then on the first option... Let's see who you find.";
                tt_l_gotit = "Cool!";
                tt_l_direction = "tip_top";
                tt_l_style = null;
            %>
            <%@ include file="./modules/tooltips/includes/tt_l_tooltip.jsp" %>

            <%
                tt_l_tooltip = TooltipEnum.CONCLUSION;
                tt_l_width = 400;
                tt_l_left = 310;
                tt_l_top = 50;
                tt_l_title = "That's it, thanks!";
                tt_l_message =
                    "Thats the end of this tutorial, if other people are confused please help them.";
                tt_l_gotit = "Finish tutorial!";
                tt_l_direction = "tip_none";
                tt_l_style = null;
            %>
            <%@ include file="./modules/tooltips/includes/tt_l_tooltip.jsp" %>

            <%
                tt_l_tooltip = TooltipEnum.UPLOAD_PHOTO;
                tt_l_width = 350;
                tt_l_left = 270;
                tt_l_top = 0;
                tt_l_title = null;
                tt_l_message = "Oh ya! Upload your photo now!";
                tt_l_gotit = "OK!";
                tt_l_direction = "tip_right";
                tt_l_style = null;
            %>
            <%@ include file="./modules/tooltips/includes/tt_l_tooltip.jsp" %>

            <script type="text/javascript">
                TT.show("<%= tooltip.getTooltipHtmlId() %>");
            </script>
        </div>

    <% } %>

    <div id="header">

        <a href="/d/app">
            <div id="logo">
                <div id="default_logo" class="lg_header white"><%= Vars.name %></div>
                <div id="custom_logo"><img/></div>
            </div>
        </a>

        <div id="dashboard"></div>

        <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.settings() %>');">
            <div class="user">
                <div class="face">
                    <div id="thumbnail"><img src="<%= user.getFaceUrl() %>" alt=""></div>
                </div>
                <div class="details">
                    <div class="detail smd_header white"><%= StringUtils.concat(user.getName(), 10, "&hellip;")  %></div>
                    <div class="detail sm_text white" style="float: right">Settings</div>
                </div>
            </div>
        </a>
    </div>

    <div id="left">
        <div id="currently">
        </div>

        <div id="networks">
        </div>
    </div>

    <div id="center">

        <div id="canvas"></div>

        <%
            String app_c_hCanvasLoadingId = "canvas_loading";
            boolean app_c_withCanvasContainer = true;
        %>
        <%@ include file="./includes/app_c_canvas_loading.jsp" %>

    </div>

</div>

<script type="text/javascript">

    /***************
    /* Routing rules
    ***************/
    ND = new NetworkDashboard();
    SS = new SmartSearch();

    HashRouting.routes = function () {

        routie('', function() {
            LeftMenu.goToNetwork(null);
        });

        routie('/comm/:nid', function(nid) {
            LeftMenu.goToNetwork(null, nid);
        });

        routie('/comm/:nid/all', function(nid) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.ALL);
            });
        });

        routie('/comm/:nid/share', function(nid) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.SHARED_ITEMS);
            });
        });

        routie('/comm/:nid/finder', function(nid) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.FINDER);
            });
        });

        routie('/comm/:nid/finder/:text', function(nid, text) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.FINDER, {s: decodeURIComponent(text)});
            });
        });

        routie('/comm/:nid/sgroups', function(nid) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.SMART_GROUPS);
            });
        });

        routie('/comm/:nid/sgroup/:sgr', function(nid, sgr) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.SMART_GROUP, {nid: nid, sgr: sgr}, function() {
                    SmartGroupDashboard.go(null, SmartGroupDashboard.Section.SHARED_ITEMS, {nid: nid, sgr: sgr});
                });
            });
        });

        routie('/comm/:nid/sgroup/:sgr/share', function(nid, sgr) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.SMART_GROUP, {nid: nid, sgr: sgr}, function() {
                    SmartGroupDashboard.go(null, SmartGroupDashboard.Section.SHARED_ITEMS, {nid: nid, sgr: sgr});
                });
            });
        });

        routie('/comm/:nid/sgroup/:sgr/members', function(nid, sgr) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.SMART_GROUP, {nid: nid, sgr: sgr}, function() {
                    SmartGroupDashboard.go(null, SmartGroupDashboard.Section.MEMBERS, {nid: nid, sgr: sgr});
                });
            });
        });

        routie('/comm/:nid/sgroup/:sgr/share/:sir', function(nid, sgr, sir) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.SMART_GROUP, {nid: nid, sgr: sgr}, function() {
                    SmartGroupDashboard.go(null, SmartGroupDashboard.Section.SHARED_ITEM, {nid: nid, sgr: sgr, sir: sir});
                });
            });
        });

        routie('/comm/:nid/search', function(nid) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.SMART_SEARCH);
            });
        });

        routie('/comm/:nid/search/:snid/:sqr', function(nid, searchNetworkId, searchQuestionRef) {
            LeftMenu.goToNetwork(null, nid, function() {

                // Switching to search on the center canvas
                var parameters = {};
                parameters.snid = searchNetworkId;
                parameters.sqr = searchQuestionRef;

                ND.go(null, NetworkDashboard.Section.SMART_SEARCH, parameters);
            });
        });

        routie('/comm/:nid/search/:snid/:sqr/:sor', function(nid, searchNetworkId, searchQuestionRef, searchOptionRef) {
            LeftMenu.goToNetwork(null, nid, function() {

                // Switching to search on the center canvas
                var parameters = {};
                parameters.snid = searchNetworkId;
                parameters.sqr = searchQuestionRef;
                parameters.sor = searchOptionRef;

                ND.go(null, NetworkDashboard.Section.SMART_SEARCH, parameters);
            });
        });

        routie('/comm/:nid/collaborate', function(nid) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.QUESTIONS);
            });
        });

        routie('/comm/:nid/collaborate/:agqr', function(nid, againQuestionRef) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.QUESTIONS, {agqr: againQuestionRef});
            });
        });

        routie('/comm/:nid/profile', function(nid) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.PROFILE, {vuid: <%= userId %>});
            });
        });

        routie('/comm/:nid/member/:uid', function(nid, uid) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.MEMBER, {vuid: uid});
            });
        });

        routie('/settings', function() {
            UserPanel.view(null, function() {
                UserPanel.go(null, UserPanel.Section.PHOTOS, null, function() {
                    UserPhotos.go(null, UserPhotos.Section.UPLOAD);
                });
            });
        });

        routie('/settings/photos', function() {
            UserPanel.view(null, function() {
                UserPanel.go(null, UserPanel.Section.PHOTOS);
            });
        });

        routie('/settings/photos/upload', function() {
            UserPanel.view(null, function() {
                UserPanel.go(null, UserPanel.Section.PHOTOS, null, function() {
                    UserPhotos.go(null, UserPhotos.Section.UPLOAD);
                });
            });
        });

        routie('/settings/photos/set', function() {
            UserPanel.view(null, function() {
                UserPanel.go(null, UserPanel.Section.PHOTOS, null, function() {
                    UserPhotos.go(null, UserPhotos.Section.SET_FACE);
                });
            });
        });

    };


    // Loads all networks and initializes the user in the correct network
    Transitions.load("#networks", "./modules/networks/networks.jsp", function() {
        HashRouting.routes();
    });
</script>

<%@ include file="../includes/footer.jsp"%>
</body>
</html>