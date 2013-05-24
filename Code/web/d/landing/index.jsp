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
    landingRef = 0;
    NetworkLanding landing = NetworkLandingDao.getByNetworkIdAndRef(null, networkId, landingRef);

    // Validating variables
//    if (landing == null)
//        webUtils.redirect("./notfound.jsp");


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


<%-- Should this css be included if the user is to have full control of the page? --%>
<%-- Should this css be included if the user is to have full control of the page? --%>
<%-- Should this css be included if the user is to have full control of the page? --%>
<link rel=stylesheet type="text/css" href="../css/basic.css?<%= Vars.rev %>">

<body style="background-color: navy;">
<div class="center w900">

    <div class="w600 square glow4" style="
        background-color: white;
        float: left;
        position: relative">

        <div class="w300" style="
            float: left;
            position: absolute;
            left: -170px;
            top: 50px;
            ">

          <img src="img/face1.png" width="200px">

        </div>


        <div style="padding: 20px 10px 10px 10px;">

            <div class="logo gg_header" style="margin-left: 30px;">ghappy</div>

            <div class="magnet vl_header" style="
                line-height: 1.5em;
                text-align: center;
                margin: 30px 0;">
                The easiest, fastest way to</br>
                meet people who enjoy a gluten-free</br>
                lifestyle, who like the same things you do
            </div>

            <div class="video center" style="
                width: 550px;
                height: 310px;
                background-color: black;">

            </div>

            <div style="
                text-align: center;
                margin-top: 30px;">
                <img src="./img/sep.png">
            </div>

            <style>
                .testimonial {
                    margin: 30px 0 0 30px;
                    display: inline-block;
                }

                .testimonial > .content {
                    float: left;
                    width: 350px;
                    margin: 10px 0 0 20px;
                }

                .testimonial > .face {
                    float: left;
                }

                .testimonial > .content > .quote {
                    font-style: italic;
                    line-height: 1.3em;
                    font-size: 20px;
                    font-family: Georgia;
                }

                .testimonial > .content > .location {
                    margin-top: 5px;
                }
            </style>

            <div class="testimonial">
                <div class="face"><img src="./img/face1.png"></div>
                <div class="content">
                    <div class="quote dim">
                        &ldquo;Seriously, this is the only gluten-free
                        community that has changed my life&hellip;&rdquo;
                    </div>
                    <div class="location md_text dim2">Jane (Scottsdale, AZ)</div>
                </div>
            </div>

            <div class="testimonial">
                <div class="face"><img src="./img/face2.png"></div>
                <div class="content">
                    <div class="quote dim">
                        &ldquo;I've learned great recipes, yoga moves,
                        and have made some great friendships!&hellip;&rdquo;
                    </div>
                    <div class="location md_text dim2">Kelsey (Los Angeles, CA)</div>
                </div>
            </div>

            <div class="testimonial">
                <div class="face"><img src="./img/face3.png"></div>
                <div class="content">
                    <div class="quote dim">
                        &ldquo;I wish I had learned of ghappy
                        five years ago, so many new friends&hellip;&rdquo;
                    </div>
                    <div class="location md_text dim2">Brian (Boston, MA)</div>
                </div>
            </div>

            <style>
                .sec-title {
                    font-style: italic;
                    font-family: Georgia;
                    text-align: center;
                }
            </style>

            <div style="
                text-align: center;
                margin-top: 30px;">
                <img src="./img/sep.png">
            </div>

            <div class="sec-title vl_text">Benefits</div>

            <style>
                .benefit {
                    margin: 30px 0 0 45px;
                    display: inline-block;
                }

                .benefit > .icon {
                    float: left;
                    margin-top: 10px;
                }

                .benefit > .content {
                    width: 350px;
                    float: left;
                    margin: 0 0 0 20px;
                }

                .benefit > .content > .title {
                    margin-bottom: 5px;
                    font-family: Helvetica;
                }

                .benefit > .content > .text {
                   line-height: 1.5em;
                }

            </style>

            <div class="benefit">
                <div class="icon"><img src="./img/ben1.png"></div>
                <div class="content">
                    <div class="title vl_text highlight">Strong Community to Network</div>
                    <div class="text lg_text dim">
                        Discover and meet other members based
                        on details that are unique to you and
                        create new friendships
                    </div>
                </div>
            </div>

            <div class="benefit">
                <div class="icon"><img src="./img/ben2.png"></div>
                <div class="content">
                    <div class="title vl_text highlight">Digital Magazine</div>
                    <div class="text lg_text dim">
                        Get unique recipes, tips, exercise routines,
                        and knowledge to live a healthier and
                        happier gluten-free lifestyle
                    </div>
                </div>
            </div>

            <div class="benefit">
                <div class="icon"><img src="./img/ben3.png"></div>
                <div class="content">
                    <div class="title vl_text highlight">Earn Cash: $2 or $100</div>
                    <div class="text lg_text dim">
                        For each friend you subscribe you will earn
                        $2, it's that simple. For each of your articles or
                        videos we publish in our magazine, you will
                        get $100 for sharing your knowledge
                    </div>
                </div>
            </div>

            <div style="
                text-align: center;
                margin-top: 30px;">
                <img src="./img/sep.png">
            </div>

            <div class="sec-title vl_text">Mission</div>

            <style>
                .mission {
                    line-height: 1.3em;
                    font-family: Helvetica;
                }
            </style>

            <div class="mission w400 center lg_text dim" style="">
                <p>Our mission is simple &mdash; to improve the
                quality of life of the amazing people who
                enjoy eating gluten-free foods</p>
            </div>

        </div>

    </div>

    <div class="w300 square" style="
        float: left;
        position: relative">

        <div style="
            margin-left: 20px;
            padding: 0;
            width:300px;
            top:30px;
            position: fixed;
            height: 500px;
            overflow: hidden;">

            <div id="goin"></div>

        </div>

    </div>
</div>
</body>

<script type="text/javascript">
    var params = {};
    params.nid = <%= networkId%>;
    params.ncs = '<%= networkChecksum%>';
    Transitions.load("#goin", "../goin/renders/goin.jsp", params);
</script>
</html>