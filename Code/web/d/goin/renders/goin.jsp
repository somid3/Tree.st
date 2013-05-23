<%@ include file="../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    String networkChecksum = StringUtils.parseString(request.getParameter("ncs"));
%>


<style>

    #confirm {
        background-color: <%= HtmlDesign.highlight %>;
        border: solid 2px white;
        padding: 10px;
        width: 260px;
        display: none;
    }

    #console {
        width: 264px;
        background-color: white;
        padding: 10px 10px 5px 10px;
    }

    #signin {
        background-color: white;
        border: solid 1px <%= HtmlDesign.dim2 %>;
        padding: 10px;
        width: 240px;
    }

    #signin-error {
        margin-bottom: 5px;
        width: 220px;
    }

    #signin-loading {
        float: right;
        position: relative;
        display: none;
    }

    #signin-email-div {
        margin-bottom: 5px;
        background-color: white;
        padding: 2px;
        width: 225px;
        border: solid 1px <%= HtmlDesign.dim3 %>;
    }

    #signin-fields {
        display: inline-block;
        margin-bottom: 10px;
    }

    #signin-password-div {
        float: left;
        background-color: white;
        padding: 2px;
        width: 150px;
        border: solid 1px <%= HtmlDesign.dim3 %>;
    }

    #signin-remember {
        float: left;
    }

    #signin-keep {
        float: left;
    }

    #signin-button {
        float: left;
        margin-left: 5px;
    }

    #signin-forgot {
        margin-top: 5px;
    }

    #signup {
        margin-top: 20px;
        background-color: white;
        border: solid 1px <%= HtmlDesign.dim2 %>;
        padding: 10px;
        width: 240px;
    }

    #signup-title {
        margin-bottom: 10px;
        width: 230px;
    }

    #signup-error {
        margin-bottom: 5px;
        width: 220px;
    }

    #signup-loading {
        float: right;
        position: relative;
        display: none;
    }

    #signup-email-div {
        margin-bottom: 5px;
        background-color: white;
        padding: 2px;
        width: 225px;
        border: solid 1px <%= HtmlDesign.dim3 %>;
    }

    #signup-name-div {
        margin-bottom: 10px;
        background-color: white;
        padding: 2px;
        width: 225px;
        border: solid 1px <%= HtmlDesign.dim3 %>;
    }

    #signup-password-div {
        margin-bottom: 5px;
        background-color: white;
        padding: 2px;
        width: 225px;
        border: solid 1px <%= HtmlDesign.dim3 %>;
    }

    #signup-button {
    }

    #provided-by {
        margin-top: 10px;
        background-color: white;
        text-align: center;
        opacity:0.5;
    }

</style>

<div id="console">
    <div id="signin" class="shadow">
        <div id="signin-loading"><img src="/d/goin/img/sm_loading.gif"></div>
        <div id="signin-error" class="error sm_text"></div>
        <div id="signin-content">
            <div id="signin-email-div"><input class="md_input w200" type="text" placeholder="Email" id="signin-email"></div>
            <div id="signin-fields">
                <div id="signin-password-div"><input class="md_input w125" type="password" placeholder="Password" id="signin-password"></div>
                <a href="#" onclick="Signin.signin(event, <%= networkId %>);">
                    <div id="signin-button" class="sm_button md_header submit_button">Sign in</div>
                </a>
            </div>
            <div id="signin-tools">
              <span id="signin-remember">
                  <a href="#">
                      <input id="signin-keep" type="checkbox" name="signin-keep">
                      <label for="signin-keep"><span class="sm_text dim">Keep me in</span></label>
                  </a>
              </span>

                <span class="dim">&mdash;</span>

                <a href="/d/forgot">
                    <span class="sm_text dim" id="signin-forgot">Forgot password?</span>
                </a>
            </div>
        </div>
    </div>

    <div id="signup" class="shadow">
        <div id="signup-title" class="vl_text dim">
            <div id="signup-loading"><img src="/d/goin/img/sm_loading.gif"></div>
            New? &mdash; Sign up!
        </div>
        <div id="signup-error" class="error sm_text"></div>
        <div id="signup-content">
            <div id="signup-email-div"><input class="md_input" type="text" placeholder="Email" id="signup-email"></div>
            <div id="signup-password-div"><input class="md_input" type="password" placeholder="Password" id="signup-password"></div>
            <div id="signup-name-div"><input class="md_input" type="text" placeholder="Full name" id="signup-name"></div>
            <a href="#" onclick="Signup.signup(event, <%= networkId %>, '<%= networkChecksum %>');">
                <div id="signup-button" class="sm_button submit_button md_header w150">Sign up</div>
            </a>
        </div>
    </div>

    <div id="provided-by">
        <span class="sm_text dim">Provided by</span>
        <a href="http://<%= Vars.domain %>"><img src="/d/assets/sm_logo.png"></a>
    </div>

</div>

<div id="confirm">

    <div><span class="sp_text white">Confirm</span></div>

    <p class="note lg_text white">Visit your email inbox and click on the provided link.</p>

    <p class="note lg_text white">Don't forget to visit your <span class="sp_header">spam</span> folder.
        Need help? email <a href="mailto:<%=Vars.supportEmail%>"><span class="lg_header white"><%=Vars.supportEmail%></span></a></p>

    <p class="note smd_text white">For the safety and privacy of all our members we require everyone to confirm their email address.</p>

</div>


<script type="text/javascript">

    // Binding the return key for the form
    $('#signin').keypress( function(event) {
        if(event.which == $.ui.keyCode.ENTER){
            Signin.signin(event, <%= networkId %>);
            return false;
        }
    });

    // Binding the return key for the form
    $('#signup').keypress( function(event) {
        if(event.which == $.ui.keyCode.ENTER){
            Signup.signup(event, <%= networkId %>, '<%= networkChecksum %>');
            return false;
        }
    });

    // Starting the focus on the email field
    $(function() {
        $("#signin-email").focus();
    });

</script>


