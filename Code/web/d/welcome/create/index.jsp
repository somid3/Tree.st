<%@ include file="../all.jsp" %>
<%
    WebUtils wu = new WebUtils(request, response);
%>
<!DOCTYPE HTML>
<html>
<head>
    <title><%= Vars.name %></title>
    <%@ include file="../../includes/google_analytics.jsp"%>
</head>

<script type="text/javascript" src="../../js/jquery-1.7.1.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../../js/jquery-ui-1.8.18.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../../js/global.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/welcome.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="./js/create.js?<%= Vars.rev %>"></script>

<link rel=stylesheet type="text/css" href="../../css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./../css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./css/create.css">

<body>

<% String header_path = "create"; %>
<%@ include file="../includes/header.jsp"%>

<div class="main-block">
    <div class="w800 center">
        <div class="block">
            <div class="main gg_header white">Let's Begin...</div>
            <div class="sub vl_text white">We'll get your searchable community created in less than a minute.</div>
        </div>
    </div>
</div>

<div id="create" class="w800 center">

    <div class="step glow4">
        <div class="breakdowns">

            <div class="breakdown" id="path">
                <div class="desc">
                    <div class="big vl_text dim">Choose your address</div>
                    <div class="small sm_text dim2">This is the web address your members can go to create their accounts and log in.</div>
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

            <div class="breakdown" id="name">
                <div class="desc">
                    <div class="big vl_text dim">Name</div>
                    <div class="small sm_text dim2">What is the name of your community? For example "Garage Bands of San Francisco"</div>
                </div>
                <div class="gather">
                    <div class="input square"><input class="lg_input w300" maxlength="30"></div>
                    <div class="error"></div>
                </div>
            </div>

            <div class="breakdown" id="desc">
                <div class="desc">
                    <div class="big vl_text dim">Purpose & Description</div>
                    <div class="small sm_text dim2">In one line, describe the purpose of your community. For instance "Musicians of San Francisco, find other artists near you to play music together."</div>
                </div>
                <div class="gather"><div class="input square"><input class="lg_input w300" maxlength="100"></div></div>
                <div class="error"></div>
            </div>
        </div>
    </div>

    <div class="step glow4">

        <div class="header md_text white">
            <span class="md_header">Important</span>: Qualities will help your members to find one another. Write
            each quality as a question on the first line of the text boxes below, and each option in a new line. The
            examples below are for a "San Francisco Musicians" searchable community.
        </div>

        <div class="breakdowns">
            <div class="breakdown" id="q1">
                <div class="desc">
                    <div class="big vl_text dim">First Quality</div>
                    <div class="small sm_text dim2">
                        What instruments do you play?<br/>
                        Drums<br/>
                        Guitar<br/>
                        Harp<br/>
                        ...<br/>
                    </div>
                </div>
                <div class="gather">
                    <div class="input square"><textarea class="quality lg_input"></textarea></div>
                </div>
                <div class="error"></div>
            </div>

            <div class="breakdown" id="q2">
                <div class="desc">
                    <div class="big vl_text dim">Second Quality</div>
                    <div class="small sm_text dim2">
                        What nights of the week are you free?<br/>
                        Mondays<br/>
                        Tuesdays<br/>
                        Wednesdays<br/>
                        ...<br/>
                    </div>
                </div>
                <div class="gather">
                    <div class="input square"><textarea class="quality lg_input"></textarea></div>
                </div>
                <div class="error"></div>
            </div>

            <div class="breakdown" id="q3">
                <div class="desc">
                    <div class="big vl_text dim">Third Quality</div>
                    <div class="small sm_text dim2">
                        Where in San Francisco do you reside?<br/>
                        Soma<br/>
                        Mission<br/>
                        Nearby, Berkeley<br/>
                        ...<br/>
                    </div>
                </div>
                <div class="gather">
                    <div class="input square"><textarea class="quality lg_input"></textarea></div>
               </div>
                <div class="error"></div>
            </div>

            <div class="breakdown" id="q4">
                <div class="desc">
                    <div class="big vl_text dim">Fourth Quality</div>
                    <div class="small sm_text dim2">
                        What type of music do you play?<br/>
                        Classical<br/>
                        Alternative Rock<br/>
                        Dubstep<br/>
                        ...<br/>
                    </div>
                </div>
                <div class="gather">
                    <div class="input square"><textarea class="quality lg_input"></textarea></div>
                </div>
                <div class="error"></div>
            </div>

            <div class="breakdown" id="q5">
                <div class="desc">
                    <div class="big vl_text dim">Last Free Quality</div>
                    <div class="small sm_text dim2">
                        What are you looking for?<br/>
                        Members to join my band<br/>
                        To find new musician friends<br/>
                        Join other bands<br/>
                        ...<br/>
                    </div>
                </div>
                <div class="gather">
                    <div class="input square"><textarea class="quality lg_input"></textarea></div>
                </div>
                <div class="error"></div>
            </div>
        </div>

        <div class="header md_text white">
            <span class="md_header">Important</span>: Once you create your searchable community, you must quickly join
            it and become the first member in order to declare yourself as the owner.
        </div>
    </div>

    <div class="share glow4 lg_text dim">
        Don't forget to share

        <div id="newcommunity">
            <span class="vl_header">http://<%= Vars.domain %>/</span>
            <span class="vl_header highlight" id="finalpanth"></span>
        </div>

        on your
        <a target="_new" class="highlight2" href="http://www.facebook.com">facebook</a>,
        <a target="_new" class="highlight2" href="http://www.twitter.com">twitter</a>,
        <a target="_new" class="highlight2" href="http://www.pinterest.com">pinterest</a>,
        <a target="_new" class="highlight2" href="http://www.tumblr.com">tumblr</a>,
        <a target="_new" class="highlight2" href="http://plus.google.com">google+</a><br/>
        &mdash; and email your friends so they join your new community!
    </div>


    <a href="#" onclick="Create.create()">
        <div class="start sp_header white">Create!</div>
    </a>

</div>

<script>
    // Binding the path field to the path checker method...
    Binding.bindInputKeyPress("pathTimer", "#path input", Create.testPath);
</script>

<%@ include file="../../includes/footer.jsp"%>
</body>
</html>
