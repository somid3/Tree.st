<%@ include file="all.jsp" %>
<%
    // Retrieve network from domain
    Network network = UrlRouter.getNetworkByDomain(webUtils);
%>
<!DOCTYPE HTML>
<html>
<head>
    <title><%= network.getName() %></title>
    <link rel="shortcut icon" href=".img/doctorana/favicon.ico" type="image/x-icon">
    <link rel="icon" href=".img/doctorana/favicon.ico" type="image/x-icon">

    <%@ include file="/d/includes/google_analytics.jsp"%>

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

            <div class="magnet" style="width: 100%; padding: 10px 0 10px 15px; line-height: 2em; text-align: center; margin: 20px auto;">
                <span class="vl_header dim">The easiest way for doctors to<br/> connect in the Middle-East</span>
            </div>

            <div class="center" style="width: 300px; padding: 5px;">
                <img src="/d/assets/md_logo.png">
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
                <img src="img/doctorana/break1.png" style="margin-top: 20px; width: 598px; position: relative; left: -10px">
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

                <img src="img/doctorana/doc1.png" id="doc1" style="width: 300px; position: absolute; top: -100px;">
                <img src="img/doctorana/doc2.png" id="doc2" style="z-index: 10; position: absolute; top: 240px; left: 100px; width: 450px;">

                <div class="benefit shadow" style="width: 180px; position: absolute; left: 330px; top: 40px;">
                    <div class="content">
                        <div class="title vl_header dim">Consult</div>
                        <div class="text lg_text dim">
                            <p>Learn from other doctors on the latest techniques and case studies.</p>
                        </div>
                    </div>
                </div>

                <div class="benefit shadow" style="width: 180px; position: absolute; left: -30px; top: 150px;">
                    <div class="content">
                        <div class="title vl_header dim">Find Referrals</div>
                        <div class="text lg_text dim">
                            <p>Connect with different specialists to build a referral network.</p>
                        </div>
                    </div>
                </div>

                <div class="benefit shadow" style="z-index: 20; width: 180px; position: absolute; left: 70px; top: 360px;">
                    <div class="content">
                        <div class="title vl_header dim">Medical News</div>
                        <div class="text lg_text dim">
                            <p>Read the latest news about the practice of medicine in the Middle-East.</p>
                        </div>
                    </div>
                </div>
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
                        &ldquo;I joined, and within a few minutes I found
                        and consulted with amazing specialists across
                        the UAE&hellip;&rdquo;
                    </div>
                    <div class="location md_text dim2">Asiya (Abu Dhabi, UAE)</div>
                </div>
            </div>

            <div class="testimonial shadow" style="position: relative; left: 20px;">
                <div class="face"><img src="img/fitarrow/face2.png"></div>
                <div class="content">
                    <div class="quote dim">
                        &ldquo;My clinic needed to final quality referrals
                        and we easily found other doctors who helped us.&hellip;&rdquo;
                    </div>
                    <div class="location md_text dim2">Tamadur (Doha, Quatar)</div>
                </div>
            </div>

            <div class="testimonial shadow" style="position: relative; left: -20px;">
                <div class="face"><img src="img/fitarrow/face3.png"></div>
                <div class="content">
                    <div class="quote dim">
                        &ldquo;One of my patients was very hard to diagnose,
                        with the help of other doctors in DoctorAna we were
                        able to cure him.&hellip;&rdquo;
                    </div>
                    <div class="location md_text dim2">Musa'id (Riyadh, Saudi Arabia)</div>
                </div>
            </div>

            <style>
                .mission {
                    line-height: 1.5em;
                    font-family: Georgina;
                    margin: 15px;
                }
            </style>

            <div class="w450 center vl_text dim" style="position: relative; margin-top: 60px; margin-bottom: 60px;">

                <img src="img/doctorana/join_community.png" style="z-index: 10; position: absolute; left: -100px; top: -50px; width: 120px;">

                <div class="center" style="width: 120px; position: relative; top: 10px;"><img src="img/doctorana/sep.png"></div>

                <p class="mission">
                Our mission is simple, our cause noble &mdash; we
                are a community of doctors who wish to improve the
                quality of healthcare in the Middle-East.</p>

                <p class="mission">We each strive to help one another to
                improve the quality of care and medical access we provide
                to our patients. If you are a medical doctor in the
                Middle-East join our community today.</p>

                <div class="center" style="width: 120px;"><img src="img/doctorana/sep.png"></div>
            </div>

            <div class="center vl_text dim" style="position: relative">
                <div class="center" style="width: 300px;">
                    <p class="mission lg_text dim">
                        If you have any questions or feedback we'd love to hear from you.
                        Please email us in English, Arabic, or Farsi.
                    </p>
                </div>

                <div style="width: 200px;" class="lg_button submit_button center">
                    <a href="mail:hello@doctorana.com">
                        <span class="lg_header white">hello@doctorana.com</span>
                    </a>
                </div>

                <div class="center" style="width: 400px;">
                    <img src="img/doctorana/doc3.png">
                </div>

                <div id="disclaimer" style="width: 300px; margin: 20px auto; text-align: center;">
                    <span class="md_text dim2">
                        This community is only for doctors, physicians and medical specialists
                        in the Middle-East. If you are in need to medical attention please
                        call your local medical services.
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