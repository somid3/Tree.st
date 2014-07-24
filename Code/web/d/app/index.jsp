<%@ include file="setup.jsp" %>
<% appDisableBlocked = false; %>
<%@ include file="auth.jsp" %>
<%
    // Getting initiation hash in case user has no hash
    String initiationHash = UserWebServices.getInitialHash(meId);
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

<script type="text/javascript" src="./modules/user_messages/js/user_messages.js?<%= Vars.rev %>"></script>
<%--<link rel=stylesheet type="text/css" href="./modules/user_messages/css/basic.css?<%= Vars.rev %>">--%>

<script type="text/javascript" src="./modules/smart_groups/js/smart_search.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="./modules/smart_groups/js/dashboard.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="./modules/smart_groups/js/smart_group_tools.js?<%= Vars.rev %>"></script>
<link rel=stylesheet type="text/css" href="./modules/smart_groups/css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./modules/smart_groups/css/criterion.css?<%= Vars.rev %>">

<script type="text/javascript" src="./modules/share/js/shared_item.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="./modules/share/js/shared_comment.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="./modules/share/js/shared_vote.js?<%= Vars.rev %>"></script>
<link rel=stylesheet type="text/css" href="./modules/share/css/basic.css?<%= Vars.rev %>">

<script type="text/javascript" src="./modules/questions/js/question_display.js?<%= Vars.rev %>"></script>
<link rel=stylesheet type="text/css" href="./modules/questions/css/basic.css?<%= Vars.rev %>">

<script type="text/javascript" src="./modules/tooltips/js/tooltips.js?<%= Vars.rev %>"></script>
<link rel=stylesheet type="text/css" href="./modules/tooltips/css/basic.css?<%= Vars.rev %>">

<script type="text/javascript" src="./modules/all/js/dashboard.js?<%= Vars.rev %>"></script>
<link rel=stylesheet type="text/css" href="./modules/all/css/basic.css?<%= Vars.rev %>">

<link rel=stylesheet type="text/css" href="../css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./css/basic.css?<%= Vars.rev %>">

<body>

<div id="header" class="square">

    <div class="w850 center">

        <a href="/d/app">
            <div id="logo">
                <div id="default_logo" class="lg_header white"><%= Vars.name %></div>
                <div id="custom_logo"><img/></div>
            </div>
        </a>

        <div id="dashboard"></div>

        <div id="user">
            <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.settingsPhotoUpload() %>');">
                <div class="face">
                    <div id="thumbnail"><img src="<%= me.getFaceUrl() %>" alt=""></div>
                </div>
            </a>

            <div class="details">
                <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.settingsGeneral() %>');">
                    <div class="detail sm_text white">Settings</div>
                </a>

                <%
                // If no face, warning user to upload
                if (!me.hasFace()) {
                    String hUploadPhotoId = HtmlUtils.getRandomId(); %>
                    <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.settingsPhotoUpload() %>');">
                        <div id="<%= hUploadPhotoId %>" class="detail vsm_button error_button sm_text white">Upload photo</div>
                    </a>
                    <script type="text/javascript">
                        Animations.dance("#<%= hUploadPhotoId %>", 1000, 3000);
                    </script>
                <% } %>
            </div>
        </div>
    </div>
</div>

<div id="main" class="w850 center">

    <div id="left">
        <div id="currently">
        </div>

        <div id="networks">
        </div>
    </div>

    <div id="center" class="w600">

        <div id="canvas"></div>

        <%
            String app_c_hCanvasLoadingId = "canvas_loading";
            boolean app_c_withCanvasContainer = true;
        %>
        <%@ include file="./includes/app_c_canvas_loading.jsp" %>

    </div>

</div>

<script type="text/javascript">

    /**
     * Routing rules
    **/
    ND = new NetworkDashboard();
    SS = new SmartSearch();

    HashRouting.routes = function () {

        routie('', function() {
            HashRouting.setHash(null, '<%= initiationHash %>');
        });

        routie('/comm/:nid', function(nid) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.SHARED_ITEMS);
            });
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

        routie('/comm/:nid/blocked', function(nid) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.BLOCKED);
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

        routie('/comm/:nid/profile/details', function(nid) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.QUESTIONS);
            });
        });

        routie('/comm/:nid/profile/details/:agqr', function(nid, againQuestionRef) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.QUESTIONS, {agqr: againQuestionRef});
            });
        });

        routie('/comm/:nid/profile', function(nid) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.PROFILE, {vuid: <%= meId %>});
            });
        });

        routie('/comm/:nid/profile/messages', function(nid) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.MESSAGE_GROUPS);
            });
        });

        routie('/comm/:nid/profile/messages/:uid', function(nid, tuid) {
            LeftMenu.goToNetwork(null, nid, function() {
                ND.go(null, NetworkDashboard.Section.MESSAGES, {tuid: tuid});
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

        routie('/settings/general', function() {
            UserPanel.view(null, function() {
                UserPanel.go(null, UserPanel.Section.GENERAL);
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

        routie('/settings/comms', function() {
            UserPanel.view(null, function() {
                UserPanel.go(null, UserPanel.Section.NETWORKS);
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