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
%>
<!DOCTYPE HTML>
<html>
<head>
    <title><%= network.getName() %></title>
    <%@ include file="../includes/google_analytics.jsp"%>
    <script type="text/javascript" src="https://js.stripe.com/v2/"></script>
</head>
<script type="text/javascript" src="../js/jquery-1.9.0.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/jquery-ui-1.9.2.custom.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/global.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="js/goin.js?<%= Vars.rev %>"></script>

<link rel=stylesheet type="text/css" href="../css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./css/basic.css?<%= Vars.rev %>">

<body style="background-color: #bbb;">

<%@ include file="../includes/browser_check.jsp"%>

<div class="center w900">

    <div class="square" style="background-color: white; float: left; position: relative; width: 590px; border-right: solid 5px #888; border-left: solid 5px #888;">

        <div style="position: absolute; left: 490px; top: 10px;"><img src="./test/pricing2.png" style="width: 120px;"></div>

        <div id="capture" class="w300" style=" float: left; position: absolute; left: -170px; top: 50px; ">
            <img src="./test/capture.png" width="200px">
        </div>

        <script type="text/javascript">
            $(window).load(function() { Animations.toPosition("#capture", -75, 60, 5000); })
        </script>

        <div style="padding: 20px 10px 10px 10px;">

            <div id="logo" style="position: relative; left: 170px; top: 10px;">
                <img src="./test/logo.png">
            </div>

            <div class="magnet vl_header" style="width: 100%; padding: 10px 0 10px 15px; line-height: 1.5em; text-align: center; margin: 20px auto;">
                The easiest, fastest way to get<br/>
                personalized, one-on-one training <br/>
                &mdash; and start getting fit today!
            </div>

            <div class="video center lg_shadow" style="width: 500px; height: 281px; background-color: black;">

                <div>
                    <iframe src="http://player.vimeo.com/video/67688246?byline=0portrait=0&autoplay=1" width="500" height="281" frameborder="0" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe>
                </div>

            </div>

            <style>
                .break {
                    position: relative;
                }

                .lets_start {
                    z-index: 30;
                    float: right;
                    width: 150px;
                    position: absolute;
                    top: 20px;
                    left: 580px;
                }

            </style>

            <div class="break">
                <img src="./test/break1.png" style="margin-top: 20px; width: 590px; position: relative; left: -10px">
            </div>

            <style>
                .benefit {
                    display: inline-block;
                    padding: 20px;
                    background-color: white;
                }

                .benefit > .content {
                    float: left;
                }

                .benefit > .content > .title {
                    margin-bottom: 5px;
                    font-family: Helvetica;
                }

                .benefit > .content > .text {
                   line-height: 1.6em;
                }
            </style>

            <div id="benefits" style="position: relative; height: 810px;">

                <img src="./test/trainer2.png" id="trainer1" style="position: absolute; left: -100px; top: -100px; width: 400px;">
                <img src="./test/trainer1.png" id="trainer2" style="z-index: 10; position: absolute; top: 340px; left: 200px; width: 550px;">

                <div class="benefit shadow" style="width: 180px; position: absolute; left: 330px; top: -50px;">
                    <div class="content">
                        <div class="title vl_header dim">Get Personalized Advice</div>
                        <div class="text lg_text dim">
                            We analyze you with over 250 dimensions
                            and give you personalized advice
                            to live a healthier life
                        </div>
                    </div>
                </div>

                <div class="benefit shadow" style="width: 180px; position: absolute; left: -30px; top: 150px;">
                    <div class="content">
                        <div class="title vl_header dim">Digital Content</div>
                        <div class="text lg_text dim">
                            Get unique recipes, tips, exercise routines,
                            and knowledge to live a healthier and
                            and more energetic lifestyle
                        </div>
                    </div>
                </div>

                <div class="benefit shadow" style="width: 180px; position: absolute; left: 390px; top: 210px;">
                    <div class="content">
                        <div class="title vl_header dim">Earn Cash</div>
                        <div class="text lg_text dim">
                            For each friend you subscribe you will earn
                            $10, it's that simple.
                        </div>
                    </div>
                </div>

                <div class="benefit shadow" style="z-index: 20; width: 180px; position: absolute; left: 40px; top: 600px;">
                    <div class="content">
                        <div class="title vl_header dim">Community</div>
                        <div class="text lg_text dim">
                            Have fun! Make friends and get know others
                            who are getting fit &mdash; professionals, students,
                            athletes.
                        </div>
                    </div>
                </div>
            </div>
            <script type="text/javascript">
                var animateTrainer1 = true;
                var animateTrainer2 = true;
                $(window).scroll(function () {
                    var scrollTop = $(window).scrollTop();

                    if (scrollTop > 400 && animateTrainer1) {
                        animateTrainer1 = false;
                        Animations.toPosition("#trainer1", 0, -100, 4000);
                    }

                    if (scrollTop > 700 && animateTrainer2) {
                        animateTrainer2 = false;
                        Animations.toPosition("#trainer2", 100, 340, 4000);
                    }
                });
            </script>

            <div class="break">
                <img src="./test/break6.png" style="z-index: 20; margin-top: 20px; width: 590px; position: relative; left: -10px">
                <a href="#" onclick="Animations.scrollToTop();">
                    <div class="lets_start md_button sm_text dark_button white square">
                        <div class="md_header"> Let's go!</div>
                        <div>Scroll to top & Sign up!</div>
                    </div>
                </a>
            </div>

            <style>
                .testimonial {
                    margin: 20px 0 0 30px;
                    display: inline-block;
                    padding: 20px;
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
                    float: right;
                }
            </style>

            <div class="testimonial shadow" style="position: relative; left: -20px;">
                <div class="face"><img src="./test/face1.png"></div>
                <div class="content">
                    <div class="quote dim">
                        &ldquo;Seriously, this is the only healthy living
                        community that has changed my life&hellip;&rdquo;
                    </div>
                    <div class="location md_text dim2">Jane (Scottsdale, AZ)</div>
                </div>
            </div>

            <div class="testimonial shadow" style="position: relative; left: 20px;">
                <div class="face"><img src="./test/face2.png"></div>
                <div class="content">
                    <div class="quote dim">
                        &ldquo;I've learned great recipes, yoga moves,
                        and have made some great friendships!&hellip;&rdquo;
                    </div>
                    <div class="location md_text dim2">Kelsey (Los Angeles, CA)</div>
                </div>
            </div>

            <div class="testimonial shadow" style="position: relative; left: -20px;">
                <div class="face"><img src="./test/face3.png"></div>
                <div class="content">
                    <div class="quote dim">
                        &ldquo;I wish I had learned of FitArrow
                        five years ago, I'm sleeping so much better&hellip;&rdquo;
                    </div>
                    <div class="location md_text dim2">Brian (Boston, MA)</div>
                </div>
            </div>

            <div class="break">
                <img src="./test/break4.png" style="margin-top: 20px; width: 590px; position: relative; left: -10px">
                <a href="#" onclick="Animations.scrollToTop();">
                    <div class="lets_start md_button sm_text dark_button white square">
                        <div class="md_header"> Awesome!</div>
                        <div>Scroll to top & Let's start!</div>
                    </div>
                </a>
            </div>

            <style>
                .mission {
                    line-height: 1.5em;
                    font-family: Georgina;
                    margin: 15px;
                }
            </style>

            <div class="w450 center vl_text dim" style="position: relative">
                <img src="./test/join_community.png" style="z-index: 10; position: absolute; left: -100px; top: -50px; width: 120px;">

                <div class="center" style="width: 120px; position: relative; top: 10px;"><img src="./test/sep.png"></div>

                <p class="mission">
                Our mission is simple, our cause noble &mdash; we
                are a community of movers, shakers, and producers of a
                healthy lifestyle. We are engineers, nurses, artists,
                students, parents, who actively help one another
                improve our health.</p>

                <p class="mission">Partnered with trainers and coaches who guide us, we
                inspire and help one another to eat better and live
                an active lifestyle. If you like our mission, we
                welcome you to join us.</p>

                <div class="center" style="width: 120px;"><img src="./test/sep.png"></div>
            </div>

            <div class="break">
                <img src="./test/break5.png" style="margin-top: 20px; width: 590px; position: relative; left: -10px">
                <a href="#" onclick="Animations.scrollToTop();">
                    <div class="lets_start md_button sm_text dark_button white square">
                        <div class="md_header">Start Now!</div>
                        <div>Scroll to top &<br/>Let's change the world</div>
                    </div>
                </a>
            </div>

            <div class="center vl_text dim" style="position: relative">
                <img src="./test/pricing2.png" style="z-index: 10; position: absolute; left: 420px; top: -50px; width: 150px;">
                <div style="display: inline-block">
                    <div class="shadow" style="float: left; margin: 0 30px 0 0; position: relative; top: -50px; left: 20px;">
                        <img src="./test/coach1.png" style="width: 100px;">
                    </div>
                    <div style="width: 300px; float: left;">
                        <p class="mission lg_text dim">
                            If you have any questions or feedback we'd love to hear from you!
                        </p>
                    </div>
                </div>

                <div style="width: 200px;" class="lg_button active_button center">
                    <a href="mail:hello@fitarrow.com">
                        <span class="lg_header white">hello@fitarrow.com</span>
                    </a>
                </div>

                <br/>
                <br/>
                <br/>
            </div>
        </div>
    </div>


    <div class="w300 square" style="float: left; position: relative; top: 20px; left: 20px;">
        <div id="goin"></div>
    </div>

</div>

<script type="text/javascript">
    var params = {};
    params.nid = <%= networkId%>;
    params.ncs = '<%= networkChecksum%>';
    Transitions.load("#goin", "./renders/goin.jsp", params);
</script>

</body>
</html>