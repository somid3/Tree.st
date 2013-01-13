<%@ include file="./all.jsp" %>
<%
    WebUtils wu = new WebUtils(request, response);

    // Retrieving log in details
    String defaultEmail = wu.getCookieValue("ue");
    if (defaultEmail == null) defaultEmail = "";
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
<script type="text/javascript" src="./js/login.js?<%= Vars.rev %>"></script>

<link rel=stylesheet type="text/css" href="../css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./css/basic.css?<%= Vars.rev %>">

<body>
<%@ include file="../includes/browser_check.jsp"%>

<div id="logo">
    <a href="/"><img src="/d/assets/logo.png"></a>
</div>

<div id="outer-container">
    <div id="login">
        <div id="form">
            <div class="element">
                <div class="sp_text">Log in</div>
            </div>

            <div class="element error smd_text" id="error"></div>

            <div class="element">
                <div class="text smd_header dim">Email</div>
                <div><input id="email" type="text" name="email" class="lg_input" value="<%= defaultEmail %>"></div>
            </div>

            <div class="element">
                <div class="text smd_header dim">Password</div>
                <div><input id="pass" type="password" name="pass" class="lg_input"></div>
            </div>

            <div id="features" class="element">
                <a href="#" onclick="Login.login(event);"><div id="button" class="lg_button submit_button">Log in</div></a>
                <div id="keepme">
                    <div id="checkbox"><input id="keep" type="checkbox"></div>
                    <a href="#">
                        <div id="text" class="smd_text dim"><label for="keep">Keep me in!</label></div>
                    </a>
                </div>
            </div>
        </div>
        <a href="/d/forgot"><div id="forgot-begin" class="sm_header highlight2">Forgot your password?</div></a>
    </div>
</div>

<script type="text/javascript">

    // Binding the return key for the form
    $('#form').keypress( function(event) {

        if(event.which == $.ui.keyCode.ENTER){

            Event.preventDefault(event);

            Login.login(event);

            return false;

        }
    });

    // Starting the focus on the email field
    $(function() {

      $("#email").focus();

    });

    // Bringing down the logo
    $(document).ready(function() {

        Animations.inTopAndBounce("#logo", 30);

    })


</script>

</body>
</html>