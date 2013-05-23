<%@ include file="./all.jsp" %>
<%
    // Retrieving network details
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    String networkChecksum = StringUtils.parseString(request.getParameter("ncs"));

    // Retrieving network
    Network network = NetworkDao.getByIdAndChecksum(null, networkId, networkChecksum);

    // Validating variables
    if (network == null)
        webUtils.redirect("./notfound.jsp");

    // Authenticate user session by cookies and send to the specific network
    boolean wasAuthGood = UserWebServices.authenticateViaCookies(webUtils);
    if (wasAuthGood) {

        Integer userId = webUtils.getCookieValueAsInteger("uid");

        // Is the user part of this network?
        UserToNetwork utn = UserToNetworkDao.getByUserIdAndNetworkId(null, userId, networkId);
        if (utn != null)

            // Yes, send user to application with network initial hash
            webUtils.redirect("/d/app" + NetworkServices.getInitialHash(userId, network.getId()));
    }

    // Retrieving log in details
    String defaultEmail = webUtils.getCookieValue("ue");
    if (defaultEmail == null) defaultEmail = "";

    // Retrieving network settings
    Map<NetworkAlphaSettingEnum, String> networkAlphaSettings = NetworkAlphaSettingEnum.getMapByNetworkId(networkId);
    Map<NetworkIntegerSettingEnum, Integer> networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(networkId);

    String systemMessage = networkAlphaSettings.get(NetworkAlphaSettingEnum.SYSTEM_MESSAGE);
    String startMessage = networkAlphaSettings.get(NetworkAlphaSettingEnum.START_MESSAGE);
    String startBody = networkAlphaSettings.get(NetworkAlphaSettingEnum.START_BODY);
    String manifestoTitle = networkAlphaSettings.get(NetworkAlphaSettingEnum.MANIFESTO_TITLE);
    String manifestoContent = networkAlphaSettings.get(NetworkAlphaSettingEnum.MANIFESTO_CONTENT);

    Integer hasBackground = networkIntegerSettings.get(NetworkIntegerSettingEnum.UI_HAS_BACKGROUND);
    Integer hasLogo = networkIntegerSettings.get(NetworkIntegerSettingEnum.UI_HAS_LOGO);
%>
<!DOCTYPE HTML>
<html>
<head>
    <title><%= Vars.name %></title>
    <%@ include file="../includes/google_analytics.jsp"%>
</head>
<script type="text/javascript" src="../js/jquery-1.9.0.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/jquery-ui-1.9.2.custom.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/global.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="./js/start.js?<%= Vars.rev %>"></script>

<link rel=stylesheet type="text/css" href="../css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./css/basic.css?<%= Vars.rev %>">
<body>
<%@ include file="../includes/browser_check.jsp"%>
<div id="main">

    <div id="header">

        <% if (hasLogo == 0) { %>

            <div id="default_logo">
                <a href="/"><img src="/d/assets/logo.png"></a>
                <span id="path" class="sp_header dim">
                    &nbsp;/&nbsp;<%= StringUtils.concat(network.getName(), 15, "&hellip;")%>
                </span>
            </div>

        <%} else {%>

            <div id="custom_logo">
                <a href="/"><img src="<%= network.getLogoResourceUrl() %>"></a>
            </div>

        <% } %>

    </div>

    <div id="container">
        <div id="content">

            <div id="messages">

                <% if (!systemMessage.isEmpty()) { %>
                    <div id="system" class="md_header white"><%= systemMessage %></div>
                <% } %>

                <% if (!startMessage.isEmpty()) { %>
                    <div id="welcome" class="canvas_container sp_header dim"><%= startMessage %></div>
                <% } %>

            </div>

            <% if (!manifestoTitle.isEmpty()) { %>
                <a href="#" onclick="Start.toggleManifesto(event, <%= networkId %>, '<%= networkChecksum %>')">
                    <div id="manifesto_title" class="canvas_container sp_header highlight2">
                        <%= manifestoTitle %>
                    </div>
                </a>

                <a href="#" onclick="Start.toggleManifesto(event, <%= networkId %>, '<%= networkChecksum %>')">
                    <div id="manifesto" class="md_text dim canvas_container">
                        <%= manifestoContent %>
                    </div>
                </a>
            <% } %>

            <% if (!startBody.isEmpty()) { %>
                <%= startBody %>
            <% } %>

        </div>

        <div id="action">
            <div id="start">
                <div id="goin"></div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    // Displaying network background
    $("body").css('background-image','url(<%= network.getBackgroundResourceUrl(hasBackground == 0) %>)');

    Transitions.loadFadeIn("#goin", "../goin/renders/goin.jsp", function() {
        Animations.inTopAndBounce("#start", 30);
    })

</script>

<%@ include file="../includes/footer.jsp"%>
</body>
</html>