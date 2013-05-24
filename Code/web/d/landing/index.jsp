<%@ include file="./all.jsp" %>
<%
    /**
     * Retrieving URL parameters
     */

    // Network id being viewed
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));

    // Network checksum to avoid automatic visits
    String networkChecksum = StringUtils.parseString(request.getParameter("ncs"));

    // Used to determine if a user referred this person, and what award user should receive
    Integer referralUserId = StringUtils.parseInt(request.getParameter("ruid"));

    // Reference on landing to be used
    Integer landingRef = StringUtils.parseInt(request.getParameter("lr"));


    /**
     * Getting objects and validating
     */

    // Retrieving network
    Network network = NetworkDao.getByIdAndChecksum(null, networkId, networkChecksum);

    // Validating variables
    if (network == null)
        webUtils.redirect("./notfound.jsp");


    /**
     * Testing if user is already logged in and if it should be sent to the application
     */

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


    /**
     * Getting landing objects
     */

    // Retrieving landing
    landingRef = IntegerUtils.zeroIfNull(landingRef);
    NetworkLanding landing = NetworkLandingDao.getByNetworkIdAndRef(null, networkId, landingRef);

    // Validating variables
    if (landing == null)
        webUtils.redirect("./notfound.jsp");

    /**
     * Updating landing lexicon
     */

    landing.setHtml( landing.getHtml().replace("{path:Landing}", "http://localhost:8080/resources/networks/2000/landing"));


    /**
     * Retrieving network settings
     */

    Map<NetworkAlphaSettingEnum, String> networkAlphaSettings = NetworkAlphaSettingEnum.getMapByNetworkId(networkId);
    Map<NetworkIntegerSettingEnum, Integer> networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(networkId);
%>
<!DOCTYPE HTML>
<html>
<head>
    <title><%= network.getName() %></title>
    <%@ include file="../includes/google_analytics.jsp"%>
</head>
<script type="text/javascript" src="../js/jquery-1.9.0.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/jquery-ui-1.9.2.custom.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/global.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../goin/js/goin.js?<%= Vars.rev %>"></script>
<link rel=stylesheet type="text/css" href="../css/basic.css?<%= Vars.rev %>">

<%= landing.getHtml() %>

<script type="text/javascript">
    var params = {};
    params.nid = <%= networkId%>;
    params.ncs = '<%= networkChecksum%>';
    Transitions.load("#goin", "../goin/renders/goin.jsp", params);
</script>
</html>