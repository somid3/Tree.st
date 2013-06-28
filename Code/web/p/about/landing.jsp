<%@ include file="all.jsp" %>
<%
    // Retrieve network from domain
    Network network = UrlRouter.getNetworkByDomain(webUtils);

    // Reference on landing to be used
    Integer landingRef = StringUtils.parseInt(request.getParameter("lr"));

    // Retrieving landing
    landingRef = IntegerUtils.zeroIfNull(landingRef);
    NetworkLanding landing = NetworkLandingDao.getByNetworkIdAndRef(null, network.getId(), landingRef);
    if (landing == null)
        return;

    // Updating landing lexicon
    landing.setHtml( landing.getHtml().replace("{path:Landing}", "http://" + Vars.domain + "/resources/networks/" + network.getId() + "/landing"));

    // Retrieving network settings
    Map<NetworkAlphaSettingEnum, String> networkAlphaSettings = NetworkAlphaSettingEnum.getMapByNetworkId(network.getId());
    Map<NetworkIntegerSettingEnum, Integer> networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(network.getId());
%>
<!DOCTYPE HTML>
<html>
<head>
    <title><%= network.getName() %></title>
    <%@ include file="/d/includes/google_analytics.jsp"%>
    <script type="text/javascript" src="https://js.stripe.com/v2/"></script>
</head>
<script type="text/javascript" src="/d/js/jquery-1.9.0.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="/d/js/jquery-ui-1.9.2.custom.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="/d/js/global.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="/p/goin/js/goin.js?<%= Vars.rev %>"></script>
<link rel=stylesheet type="text/css" href="/d/css/basic.css?<%= Vars.rev %>">

<%= landing.getHtml() %>

<script type="text/javascript">
    var params = {};
    params.nid = <%= network.getId() %>;
    params.ncs = '<%= network.getChecksum() %>';
    Transitions.load("#goin", "../d/goin/renders/goin.jsp", params);
</script>
</html>