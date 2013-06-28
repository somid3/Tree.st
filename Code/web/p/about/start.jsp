<%@ include file="all.jsp" %>
<%
    // Retrieve network from domain
    Network network = UrlRouter.getNetworkByDomain(webUtils);

    // Authenticate user session by cookies and send to the specific network
    boolean wasAuthGood = UserWebServices.authenticateViaCookies(webUtils);
    if (wasAuthGood) {

        Integer userId = webUtils.getCookieValueAsInteger("uid");

        // Is the user part of this network?
        UserToNetwork utn = UserToNetworkDao.getByUserIdAndNetworkId(null, userId, network.getId());
        if (utn != null)

            // Yes, send user to application with network initial hash
            webUtils.redirect("/d/app" + NetworkServices.getInitialHash(userId, network.getId()));
    }

    // Retrieving log in details
    String defaultEmail = webUtils.getCookieValue("ue");
    if (defaultEmail == null) defaultEmail = "";

    // Retrieving network settings
    Map<NetworkAlphaSettingEnum, String> networkAlphaSettings = NetworkAlphaSettingEnum.getMapByNetworkId(network.getId());
    Map<NetworkIntegerSettingEnum, Integer> networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(network.getId());

    String startMessage = networkAlphaSettings.get(NetworkAlphaSettingEnum.START_MESSAGE);
    String startBody = networkAlphaSettings.get(NetworkAlphaSettingEnum.START_BODY);

    Integer hasBackground = networkIntegerSettings.get(NetworkIntegerSettingEnum.IS_UI_BACKGROUND_SET);
    Integer hasLogo = networkIntegerSettings.get(NetworkIntegerSettingEnum.IS_UI_LOGO_SET);
%>
<!DOCTYPE HTML>
<html>
<head>
    <title><%= Vars.name %></title>
    <%@ include file="/d/includes/google_analytics.jsp"%>
    <script type="text/javascript" src="https://js.stripe.com/v2/"></script>
</head>
<script type="text/javascript" src="/d/js/jquery-1.9.0.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="/d/js/jquery-ui-1.9.2.custom.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="/d/js/global.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="/p/goin/js/goin.js?<%= Vars.rev %>"></script>

<link rel=stylesheet type="text/css" href="/d/css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="css/start.css?<%= Vars.rev %>">
<body>
<%@ include file="/d/includes/browser_check.jsp"%>
<div id="main">

    <div id="header">

        <% if (hasLogo == 0) { %>

            <div id="default_logo">
                <span id="path" class="sp_header dim">
                    <%= StringUtils.concat(network.getName(), 15, "&hellip;")%>
                </span>
            </div>

        <%} else {%>

            <div id="custom_logo">
                <a href="/"><img src="<%= network.getLogoResourceUrl() %>"></a>
            </div>

        <% } %>

    </div>

    <div id="start">
        <div id="left">

            <% if (!startMessage.isEmpty()) { %>
                <div id="welcome" class="canvas_container vl_header dim"><%= startMessage %></div>
            <% } %>

            <div style="display: block;">
                <%= startBody %>
            </div>

        </div>

        <div id="right">
            <div id="goin"></div>
        </div>
    </div>
</div>

<script type="text/javascript">

    // Displaying network background
    $("body").css('background-image','url(<%= network.getBackgroundResourceUrl(hasBackground == 0) %>)');

    var params = {};
    params.nid = <%= network.getId() %>;
    params.ncs = '<%= network.getChecksum() %>';
    Transitions.load("#goin", "../goin/renders/goin.jsp", params);
</script>

</body>
</html>