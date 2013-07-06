<%@ include file="all.jsp" %>
<%
    // Retrieve network from domain
    Network network = UrlRouter.getNetworkByDomain(webUtils);
%>
<!DOCTYPE HTML>
<html>
<head>
    <title><%= network.getName() %></title>
    <link rel="icon" type="image/png" href="img/fitarrow/favicon.png">
    <%@ include file="/d/includes/google_analytics.jsp"%>
    <script type="text/javascript" src="https://js.stripe.com/v2/"></script>
</head>
<script type="text/javascript" src="/d/js/jquery-1.9.0.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="/d/js/jquery-ui-1.9.2.custom.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="/d/js/global.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="/p/goin/js/goin.js?<%= Vars.rev %>"></script>

<link rel=stylesheet type="text/css" href="/d/css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="/p/css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="/p/goin/css/basic.css?<%= Vars.rev %>">

<body style="background-color: #bbb;">
<div class="center w900">
    <div class="page square">

        <div id="free" style="position: absolute; left: 490px; top: 80px;"><img src="./img/fitarrow/pricing.png" style="width: 120px;"></div>
        <div id="capture" class="w300" style=" float: left; position: absolute; left: -170px; top: 80px; ">
            <img src="./img/fitarrow/capture.png" style="width: 200px;">
        </div>

        <script type="text/javascript">
            $(window).load(function() {
                Animations.toPosition("#capture", -75, 80, 3000)
            });
        </script>

        <div style="padding: 20px 10px 10px 10px;">

            <%
                Network p_network = network;
                String p_section = "items";
            %>
            <%@ include file="../includes/p_navigation.jsp" %>

            <div class="magnet" style="width: 100%; padding: 10px 0 10px 15px; line-height: 2em; text-align: center; margin: 20px auto;">
                <span class="vl_header dim">The easiest way to get your<br/> health-related questions<br/> answered by professional trainers</span>
            </div>

            <div class="video center lg_shadow" style="width: 500px; height: 281px; background-color: black;">
                <iframe width="500" height="281" src="https://www.youtube.com/embed/sogVLwKhje0?rel=0&modestbranding=1" frameborder="0" allowfullscreen></iframe>
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
                <img src="img/fitarrow/break1.png" style="margin-top: 20px; width: 590px; position: relative; left: -10px">
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

                <img src="img/fitarrow/trainer2.png" id="trainer1" style="position: absolute; left: -100px; top: -100px; width: 400px;">
                <img src="img/fitarrow/trainer1.png" id="trainer2" style="z-index: 10; position: absolute; top: 340px; left: 200px; width: 550px;">

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
                <img src="img/fitarrow/break6.png" style="z-index: 20; margin-top: 20px; width: 590px; position: relative; left: -10px">
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
                <div class="face"><img src="img/fitarrow/face1.png"></div>
                <div class="content">
                    <div class="quote dim">
                        &ldquo;Seriously, this is the only healthy living
                        community that has changed my life&hellip;&rdquo;
                    </div>
                    <div class="location md_text dim2">Jane (Scottsdale, AZ)</div>
                </div>
            </div>

            <div class="testimonial shadow" style="position: relative; left: 20px;">
                <div class="face"><img src="img/fitarrow/face2.png"></div>
                <div class="content">
                    <div class="quote dim">
                        &ldquo;I've learned great recipes, yoga moves,
                        and have made some great friendships!&hellip;&rdquo;
                    </div>
                    <div class="location md_text dim2">Kelsey (Los Angeles, CA)</div>
                </div>
            </div>

            <div class="testimonial shadow" style="position: relative; left: -20px;">
                <div class="face"><img src="img/fitarrow/face3.png"></div>
                <div class="content">
                    <div class="quote dim">
                        &ldquo;I wish I had learned of FitArrow
                        five years ago, I'm sleeping so much better&hellip;&rdquo;
                    </div>
                    <div class="location md_text dim2">Brian (Boston, MA)</div>
                </div>
            </div>

            <div class="break">
                <img src="img/fitarrow/break4.png" style="margin-top: 20px; width: 590px; position: relative; left: -10px">
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
                <img src="img/fitarrow/join_community.png" style="z-index: 10; position: absolute; left: -100px; top: -50px; width: 120px;">

                <div class="center" style="width: 120px; position: relative; top: 10px;"><img src="img/fitarrow/sep.png"></div>

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

                <div class="center" style="width: 120px;"><img src="img/fitarrow/sep.png"></div>
            </div>

            <div class="break">
                <img src="img/fitarrow/break5.png" style="margin-top: 20px; width: 590px; position: relative; left: -10px">
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
                        <img src="img/fitarrow/coach1.png" style="width: 100px;">
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
                        <img src="img/fitarrow/curved-trainer.png" style="width: 550px;">
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
    params.nid = <%= network.getId() %>;
    params.ncs = '<%= network.getChecksum() %>';
    Transitions.load("#goin", "../goin/renders/goin.jsp", params);
</script>

</body>
</html>