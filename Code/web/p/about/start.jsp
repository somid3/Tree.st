<%@ include file="all.jsp" %>
<%
    // Retrieve network from domain
    Network network = UrlRouter.getNetworkByDomain(webUtils);

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
<link rel=stylesheet type="text/css" href="/p/css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="css/start.css?<%= Vars.rev %>">
<body>
<div id="main">

    <div id="start">
        <div id="left">

            <%
                Network p_network = network;
                String p_section = "items";
            %>

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