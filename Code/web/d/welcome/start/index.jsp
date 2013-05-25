<%@ include file="../all.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><%= Vars.name %></title>
    <%@ include file="../../includes/google_analytics.jsp"%>
</head>

<script type="text/javascript" src="../../js/jquery-1.9.0.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../../js/jquery-ui-1.9.2.custom.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../../js/global.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/welcome.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="./js/start.js?<%= Vars.rev %>"></script>

<link rel=stylesheet type="text/css" href="../../css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./../css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./css/start.css">

<body>
<%@ include file="../../includes/browser_check.jsp"%>

<% String header_path = "start"; %>
<%@ include file="../includes/header.jsp"%>

<div class="main-block">
    <div class="w800 center">
        <div class="block">
            <div class="main gg_header white">Let's Begin...</div>
            <div class="sub vl_text white">We'll get your community created in less than a minute</div>
        </div>
    </div>
</div>

<div id="create" class="w800 center">

    <div class="step shadow">
        <div class="generalities">
            <div class="general" id="path">
                <div class="desc">
                    <div class="big vl_text dim">Choose your address</div>
                    <div class="small sm_text dim">This is the web address your members can go to create their accounts and log in.</div>
                </div>
                <div class="gather">

                    <span class="md_text dim">http://<%= Vars.domain %>/</span>

                    <div class="input square"><input class="lg_input w150" maxlength="30"></div>

                    <div class="feedback">
                        <div class="error"></div>
                        <div class="good"></div>
                    </div>
                </div>
            </div>

            <div class="general" id="name">
                <div class="desc">
                    <div class="big vl_text dim">Name</div>
                    <div class="small sm_text dim">What is the name of your community? For example "Garage Bands of San Francisco"</div>
                </div>
                <div class="gather">
                    <div class="input square"><input class="lg_input w300" maxlength="60"></div>
                </div>
            </div>

            <div class="general" id="desc">
                <div class="desc">
                    <div class="big vl_text dim">Purpose & Description</div>
                    <div class="small sm_text dim">In one or two lines, describe the purpose of your community. For instance "Musicians of San Francisco, find other artists near you to play music together."</div>
                </div>
                <div class="gather"><div class="input square"><textarea class="md_input w300" maxlength="250"></textarea></div></div>
            </div>
        </div>
    </div>

    <div class="step shadow">

        <div class="header md_text white">
            <span class="md_header">Important</span>: Qualities will help your members to find one another. Write
            each quality as a question on the first line of the text boxes below, and each option in a new line. The
            examples below are for a "San Francisco Musicians" searchable community.
        </div>

        <div class="qualities">
            <div class="quality" id="q1">
                <div class="big vl_text dim">First Quality</div>
                <div class="example">
                    <div class="title">Example</div>
                    <div class="small smd_text dim">
                        <div class="line">What instruments do you play?</div>
                        <div class="line">Drums</div>
                        <div class="line">Guitar</div>
                        <div class="line">Harp</div>
                    </div>
                </div>
                <div class="gather">
                    <div class="title">Your Quality</div>
                    <div class="input square"><textarea class="md_input"></textarea></div>
                </div>
            </div>

            <div class="quality" id="q2">
                <div class="big vl_text dim">Second Quality</div>
                <div class="example">
                    <div class="title">Example</div>
                    <div class="small smd_text dim">
                        <div class="line">What nights of the week are you free?</div>
                        <div class="line">Mondays</div>
                        <div class="line">Tuesdays</div>
                        <div class="line">Wednesdays</div>
                    </div>
                </div>
                <div class="gather">
                    <div class="title">Your Quality</div>
                    <div class="input square"><textarea class="md_input"></textarea></div>
                </div>
            </div>

            <div id="add3">
                <a href="#" onclick="Create.addQuality(event, '#add3', '#q3')">
                    <span class="lg_text highlight2">(+) Add third quality</span>
                </a>
            </div>
            <div class="quality" id="q3" style="display: none;">
                <div class="big vl_text dim">Third Quality</div>
                <div class="example">
                    <div class="title">Example</div>
                    <div class="small smd_text dim">
                        <div class="line">Where in San Francisco do you live?</div>
                        <div class="line">Mission</div>
                        <div class="line">Daly City</div>
                        <div class="line">Nearby, Berkeley</div>
                    </div>
                </div>
                <div class="gather">
                    <div class="title">Your Quality</div>
                    <div class="input square"><textarea class="md_input"></textarea></div>
                </div>
            </div>

            <div id="add4">
                <a href="#" onclick="Create.addQuality(event, '#add4', '#q4')">
                    <span class="lg_text highlight2">(+) Add fourth quality</span>
                </a>
            </div>
            <div class="quality" id="q4" style="display: none;">
                <div class="big vl_text dim">Fourth Quality</div>
                <div class="example">
                    <div class="title">Example</div>
                    <div class="small smd_text dim">
                        <div class="line">What type of music do you play?</div>
                        <div class="line">Classical</div>
                        <div class="line">Alternative Rock</div>
                        <div class="line">Dubstep</div>
                    </div>
                </div>
                <div class="gather">
                    <div class="title">Your Quality</div>
                    <div class="input square"><textarea class="md_input"></textarea></div>
                </div>
            </div>

            <div id="add5">
                <a href="#" onclick="Create.addQuality(event, '#add5', '#q5')">
                    <span class="lg_text highlight2">(+) Add fifth quality</span>
                </a>
            </div>
            <div class="quality" id="q5" style="display: none;">
                <div class="big vl_text dim">Fifth Quality</div>
                <div class="example">
                    <div class="title">Example</div>
                    <div class="small smd_text dim">
                        <div class="line">Who/What are you looking for?</div>
                        <div class="line">New members to join my band</div>
                        <div class="line">Friendly musicians</div>
                        <div class="line">To join another band</div>
                    </div>
                </div>
                <div class="gather">
                    <div class="title">Your Quality</div>
                    <div class="input square"><textarea class="md_input"></textarea></div>
                </div>
            </div>

        </div>

        <div class="header md_text white">
            <span class="md_header">Important</span>: Once you create your searchable community, you must quickly join
            it and become the first member in order to declare yourself as the owner.
        </div>
    </div>

    <div id="share" class="glow lg_text dim">
        Remember, share, share, and share

        <div id="new_community">
            <span class="vl_header dim">http://<%= Vars.domain %>/</span><span class="vl_header dim" id="final_path"></span>
        </div>

        on your

        <a target="_new" class="highlight2" href="http://www.facebook.com">facebook</a>,
        <a target="_new" class="highlight2" href="http://www.twitter.com">twitter</a>,
        <a target="_new" class="highlight2" href="http://www.pinterest.com">pinterest</a>,
        <a target="_new" class="highlight2" href="http://www.tumblr.com">tumblr</a>,
        <a target="_new" class="highlight2" href="http://plus.google.com">google+</a><br/>
        &mdash; and email your friends and family so they join your new community!
    </div>

    <div id="create_error" class="error"></div>
    <div id="create_loading"><img src="../img/loading.gif"></div>

    <a href="#" onclick="Create.create(event)">
        <div id="submit" class="start sp_header white">Create!</div>
    </a>

</div>

<script>
    // Binding the path field to the path checker method...
    Binding.bindInputKeyPress("pathTimer", "#path input", Create.testPath);
</script>

<%@ include file="../../includes/footer.jsp"%>
</body>
</html>
