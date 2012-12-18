<%@ include file="./all.jsp" %>
<%
    WebUtils wu = new WebUtils(request, response);

    // Retrieving network details
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    String networkChecksum = StringUtils.parseString(request.getParameter("ncs"));

    // Retrieving log in details
    String defaultEmail = wu.getCookieValue("ue");
    if (defaultEmail == null) defaultEmail = "";

    // Retrieving network
    Network network = NetworkDao.getByIdAndChecksum(null, networkId, networkChecksum);
    String manifestoTitle = NetworkAlphaSettingEnum.MANIFESTO_TITLE.getValueByNetwork(network.getId());

    // Validating variables
    if (network == null)
        wu.redirect("./notfound.jsp");
%>

<!DOCTYPE HTML>
<html>
<head>
    <title><%= Vars.name %></title>
    <%@ include file="../includes/google_analytics.jsp"%>
</head>
<script type="text/javascript" src="../js/jquery-1.7.1.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/jquery-ui-1.8.18.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/global.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="./js/start.js?<%= Vars.rev %>"></script>

<link rel=stylesheet type="text/css" href="../css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./css/basic.css?<%= Vars.rev %>">
<body>
<%@ include file="../includes/browser_check.jsp"%>
<div id="main">
    <div id="header">

        <a href="/">
            <div id="logo"><img src="/d/assets/logo.png"></div>
        </a>

        <div id="path">
            <span class="sp_text dim">/</span>
            <span class="sp_header dim"><%= StringUtils.concat(network.getName(), 20, "&hellip;")%></span>
        </div>
    </div>
    <div id="container">
        <div id="content">

            <div id="messages">
                <% if (network.getDemoMessage() != null) { %>
                    <div id="demo" class="smd_text">
                         <%= network.getDemoMessage() %>
                    </div>
                <% } %>

                <% if (network.getStartMessage() != null) { %>
                    <div id="welcome" class="canvas_container sp_header dim">
                        <%= network.getStartMessage() %>
                    </div>

               <% } %>

                <% if (network.getSystemMessage() != null) { %>
                    <div id="system" class="smd_text dim">
                         <%= network.getSystemMessage() %>
                    </div>
                <% } %>
            </div>

            <% if (!manifestoTitle.isEmpty()) { %>
                <a href="#" onclick="Start.toggleManifesto(event, <%= networkId %>, '<%= networkChecksum %>')">
                    <div id="manifesto_title" class="md_header highlight2">
                        <%= manifestoTitle %>
                    </div>
                </a>

                <a href="#" onclick="Start.toggleManifesto(event, <%= networkId %>, '<%= networkChecksum %>')">
                    <div id="manifesto" class="sm_text dim canvas_container">
                        <pre><%= NetworkAlphaSettingEnum.MANIFESTO_CONTENT.getValueByNetwork(networkId) %></pre>
                    </div>
                </a>
            <% } %>



            <div id="video" class="glow">
                <%= network.getStartVideoCode() %>
            </div>

        </div>


        <div id="action">
            <div id="start">
                <div id="form">
                    <div class="element">
                        <div>
                            <span class="sp_text">Join</span>
                            <span class="sp_text dim3">/</span>
                            <span class="sp_text">Login</span>
                        </div>
                    </div>

                    <div class="element error smd_text" id="error"></div>

                    <div class="element">
                        <div class="text smd_header dim"><label for="email">Email</label></div>
                        <div><input id="email" placeholder="" type="text" name="email" value="<%= defaultEmail %>" class="lg_input w200"></div>
                    </div>

                    <div class="element">
                        <div class="text smd_header dim"><label for="pass">Password</label></div>
                        <div><input id="pass" type="password" name="pass" class="lg_input w200"></div>
                    </div>

                    <div class="element">
                        <div class="text smd_header dim"><label for="name">Name</label></div>
                        <div>
                            <input id="first" placeholder="First" type="text" name="first" class="lg_input w75">
                            <input id="last" placeholder="Last" type="text" name="last" class="lg_input w100">
                        </div>
                    </div>

                    <div class="element">
                        <div class="note sm_text dim">
                            In the future, to <b>login</b> enter your email and password, no need type your name again
                        </div>
                    </div>


                    <div class="element">
                        <a href="#" onclick="Start.start(event, <%= networkId%>, '<%= networkChecksum %>');"><div id="button" class="lg_button submit_button">Start</div></a>
                    </div>

                    <a href="/d/forgot"><div id="forgot-begin" class="sm_header highlight2">Forgot your password?</div></a>
                </div>
            </div>
        </div>
    </div>
    <div id="footer">
        <div class="content smd_text dim">
            <a href="mailto:<%= Vars.supportEmail %>"><span class="highlight2">Support</span></a> &middot;
            Patent pending &middot;
            Made at MIT &#x2764; &middot;
            You're awesome!
        </div>
    </div>
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

    // Displaying network background
    <% if (network.hasBackground()) { %>
        $("body").css('background-image','url(<%= network.getBackgroundResourceUrl() %>)');
    <% } %>

    // Bringing down the join or login form
    $(document).ready(function() {

        Animations.inTopAndBounce("#start", 30);

    })

</script>

</body>
</html>