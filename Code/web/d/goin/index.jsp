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
     * Retrieving network settings
     */

    Map<NetworkAlphaSettingEnum, String> networkAlphaSettings = NetworkAlphaSettingEnum.getMapByNetworkId(networkId);
    Map<NetworkIntegerSettingEnum, Integer> networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(networkId);

// MAYBE REMOVE!?!
//    String systemMessage = networkAlphaSettings.get(NetworkAlphaSettingEnum.SYSTEM_MESSAGE);
//    String startMessage = networkAlphaSettings.get(NetworkAlphaSettingEnum.START_MESSAGE);
//    String startBody = networkAlphaSettings.get(NetworkAlphaSettingEnum.START_BODY);
//    String manifestoTitle = networkAlphaSettings.get(NetworkAlphaSettingEnum.MANIFESTO_TITLE);
//    String manifestoContent = networkAlphaSettings.get(NetworkAlphaSettingEnum.MANIFESTO_CONTENT);
//    Integer hasBackground = networkIntegerSettings.get(NetworkIntegerSettingEnum.UI_HAS_BACKGROUND);
//    Integer hasLogo = networkIntegerSettings.get(NetworkIntegerSettingEnum.UI_HAS_LOGO);
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
<script type="text/javascript" src="./js/goin.js?<%= Vars.rev %>"></script>

<link rel=stylesheet type="text/css" href="../css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./css/basic.css?<%= Vars.rev %>">
<body style="background-color: blue;">

<%@ include file="../includes/browser_check.jsp"%>


<div class="center w900" style="">

    <div class="w600 square glow4" style="
        background-color: white;
        float: left;">

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
                        5- years ago, so many new friends&hellip;&rdquo;
                    </div>
                    <div class="location md_text dim2">Brian (Boston, MA)</div>
                </div>
            </div>

            <div style="
                text-align: center;
                margin-top: 30px;">
                <img src="./img/sep.png">
            </div>

            <style>
                .sec-title {
                    font-style: italic;
                    font-family: Georgia;
                    text-align: center;
                }
            </style>

            <div class="sec-title gg_text">Benefits</div>
                          
            <style>
                .benefit {
                    margin: 30px 0 0 30px;
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
                }

                .benefit > .content > .text {
                   line-height: 1.5em;
                }

            </style>

            <div class="benefit">
                <div class="icon"><img src="./img/ben1.png"></div>
                <div class="content">
                    <div class="title vl_text dim">Strong Community to Network</div>
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
                    <div class="title vl_text dim">Digital Magazine</div>
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
                    <div class="title vl_text dim">Earn Cash, $2 or $100</div>
                    <div class="text lg_text dim">
                        For each friend you subscribe you will earn
                        $2, it's that simple. For each of your articles
                        videos we publish in our magazine, you will
                        get $100 for sharing your knowledge
                    </div>
                </div>
            </div>

            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>     </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>
            </br>



        </div>

    </div>

    <div class="w300 square" style="
        float: right;">

        <div style="
            padding: 20px 10px 10px 10px;
            border: solid 1px white;
            width:250px;
            top:10px;
            position: fixed;
            height: 400px;
            overflow: hidden;">

            right

        </div>

    </div>



    <%--<a href="/d/forgot"><div id="forgot-begin" class="sm_header highlight2">Forgot your password?</div></a>--%>

</div>

<script type="text/javascript">


    // Binding the return key for the form
    $('#form').keypress(function(e) {
        if(e.which == $.ui.keyCode.ENTER){
            e.preventDefault();
            Start.start(e, <%= networkId %>, '<%= networkChecksum %>');
            return false;
        }
    });

    // Starting the focus on the email field
    $(function() {
      $("#email").focus();
    });

    // Bringing down the join or login form
    $(document).ready(function() {

        Animations.inTopAndBounce("#start", 30);

    })

</script>

</body>
</html>