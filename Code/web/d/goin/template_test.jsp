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
    <link rel="icon" type="image/png" href="./test/favicon.png">
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

        <div id="free" style="position: absolute; left: 490px; top: 30px;"><img src="./test/pricing.png" style="width: 120px;"></div>

        <div id="capture" class="w300" style=" float: left; position: absolute; left: -170px; top: 50px; ">
            <img src="./test/capture.png" style="width: 200px;">
        </div>

        <script type="text/javascript">
            $(window).load(function() {
                Animations.toPosition("#capture", -75, 60, 3000, function() {
                    Animations.toPosition("#free", 490, 60, 1000);
                })
            });
        </script>

        <div style="padding: 20px 10px 10px 10px;">

            <div id="logo" style="position: relative; left: 170px; top: 10px;">
                <img src="./test/logo.png">
            </div>

            <div class="magnet" style="width: 100%; padding: 10px 0 10px 15px; line-height: 2em; text-align: center; margin: 20px auto;">
                <span class="vl_header dim">The easiest way to get your</br> health-related questions</br> answered by personal trainers</span>
            </div>

            <div class="video center lg_shadow" style="width: 500px; height: 281px; background-color: black;">
                <iframe width="500" height="281" src="https://www.youtube.com/embed/sogVLwKhje0?rel=0" frameborder="0" allowfullscreen></iframe>
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
                <a href="#" onclick="Animations.scrollToTop();">
                    <div class="lets_start md_button sm_text dark_button white square">
                        <div class="md_header">Start Now!</div>
                        <div>Let's get you in shape</div>
                    </div>
                </a>
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
                        <div class="title vl_header dim">Personal Trainers</div>
                        <div class="text lg_text dim">
                            <p>Get personalized advice to help
                            you improve your health and fitness.</p>

                            <p>Ask our personal trainers any question and
                            get quick answers.</p>
                        </div>
                    </div>
                </div>

                <div class="benefit shadow" style="width: 180px; position: absolute; left: -30px; top: 150px;">
                    <div class="content">
                        <div class="title vl_header dim">Quick Answers</div>
                        <div class="text lg_text dim">
                            <p>Trying to achieve a fitness goal? Ask our
                            personal trainers and get all your answers in one place.</p>

                            <p>Get unique recipes, tips, exercise routines,
                            and knowledge to live a healthier and
                            and more energetic lifestyle</p>
                        </div>
                    </div>
                </div>

                <div class="benefit shadow" style="z-index: 20; width: 180px; position: absolute; left: 70px; top: 560px;">
                    <div class="content">
                        <div class="title vl_header dim">Rich Community</div>
                        <div class="text lg_text dim">
                            <p>Have fun! Make friends and get know others
                            who are getting fit &mdash; professionals, students,
                            athletes.</p>
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
                        <div>Sign me up!</div>
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
                        <div>Let's start!</div>
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
                        <div>Let's get you in shape</div>
                    </div>
                </a>
            </div>

            <div class="center vl_text dim" style="position: relative">
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


                <div style="position: relative; margin: 0 0 0 10px; display: inline-block; width: 590px; height: 80px;">

                    <div style="position: absolute; top: -20px; left: 40px;">
                        <img src="./test/curved-trainer.png" style="width: 550px;">
                    </div>

                </div>

                <div id="disclaimer" style="width: 300px; margin: 100px auto; text-align: center;">
                    <span class="md_text dim2">
                        Please consult a physician before starting any diet or exercise plan.
                    </span>
                </div>
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