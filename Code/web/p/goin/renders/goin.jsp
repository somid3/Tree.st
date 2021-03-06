<%@ include file="../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    String networkChecksum = StringUtils.parseString(request.getParameter("ncs"));
%>


<style>

    #confirm {
        margin-top: 20px;
        background-color: <%= HtmlDesign.highlight %>;
        border: solid 1px white;
        padding: 10px;
        width: 240px;
        display: none;
    }

    #console {
        width: 264px;
    }

    #signin {
        background-color: white;
        padding: 10px;
        width: 240px;
        border: 1px solid <%= HtmlDesign.dim4 %>;
    }

    #signin_error {
        margin-bottom: 5px;
        width: 220px;
    }

    #signin_loading {
        float: right;
        position: relative;
        display: none;
    }

    #signin_email_div {
        margin-bottom: 5px;
        background-color: white;
        padding: 4px;
        width: 225px;
        border: solid 1px <%= HtmlDesign.dim3 %>;
    }

    #signin_fields {
        display: inline-block;
        margin-bottom: 10px;
    }

    #signin_password_div {
        float: left;
        background-color: white;
        padding: 4px;
        width: 150px;
        border: solid 1px <%= HtmlDesign.dim3 %>;
    }

    #signin_tools {
        display: inline-block;
        width: 100%;
    }

    #signin_remember {
        float: left;
    }

    #signin_keep {
        float: left;
    }

    #signin_button {
        float: left;
        margin-left: 5px;
    }

    #signin_forgot {
        margin-top: 2px;
        float: right;
    }

    #signup{
        margin-top: 20px;
        background-color: white;
        padding: 10px;
        border: 1px solid <%= HtmlDesign.dim4 %>;
        width: 240px;
        position: relative;
    }

    #signup_title,
    #payment_title {
        margin: 10px 0;
        width: 230px;
        text-align: center;
    }

    #signup_error,
    #payment_error {
        margin-bottom: 5px;
        width: 220px;
    }

    #signup_loading,
    #payment_loading {
        float: right;
        position: absolute;
        display: none;
        left: 230px;
    }

    #signup_email_div,
    #signup_name_div,
    #signup_password_div {
        margin-bottom: 5px;
        background-color: white;
        padding: 4px;
        width: 225px;
        border: solid 1px <%= HtmlDesign.dim3 %>;
    }

    #signup_button {
        width: 215px;
    }

    #payment {
        margin-top: 20px;
        background-color: white;
        padding: 10px;
        width: 240px;
        display: none;
        position: relative;
    }

    #payment_details {
        margin: 10px 0;
    }

    #payment_cards {
        margin: 10px 0 0 15px;
    }

    .payment_cc_image {
        width: 32px;
    }

    #payment_content {
        margin-top: 20px;
    }

    #payment_cc_div {
        background-color: white;
        padding: 4px;
        width: 225px;
        border: solid 1px <%= HtmlDesign.dim3 %>;
    }

    #payment_cc {}

    .payment_check {
        float: right;
        position: relative;
        width: 20px;
    }

    #payment_check_cc_good,
    #payment_check_exp_good,
    #payment_check_cvc_good {
        display: none;
    }

    #payment_check_cc_type {
        width: 40px;
        margin-left: 2px;
    }

    #payment_exp_div {
        margin-bottom: 10px;
        background-color: white;
        padding: 4px;
        width: 100px;
        border: solid 1px <%= HtmlDesign.dim3 %>;
    }

    #payment_cvc_div {
        margin-bottom: 10px;
        background-color: white;
        padding: 4px;
        width: 102px;
        border: solid 1px <%= HtmlDesign.dim3 %>;
    }

    #payment_expcvc_div {
        margin-top: 10px;
        display: inline-block;
    }

    #provided_by {
        margin-top: 20px;
        background-color: white;
        text-align: center;
        padding: 3px 5px 0 2px;
        float: right;
    }

</style>

<div id="console">
    <input type="hidden" id="network_id" value="<%= networkId %>">
    <input type="hidden" id="network_checksum" value="<%= networkChecksum %>">

    <div id="sign">
        <div id="signin">
            <div id="signin_loading"><img src="/d/assets/sm_loading.gif"></div>
            <div id="signin_error" class="center error sm_text"></div>
            <div id="signin_content">
                <div id="signin_email_div"><input class="md_input w200" type="text" placeholder="Email" id="signin_email"></div>
                <div id="signin_fields">
                    <div id="signin_password_div"><input class="md_input w125" type="password" placeholder="Password" id="signin_password"></div>
                    <a href="#" onclick="Signin.signin(event);">
                        <div id="signin_button" class="sm_button md_header submit_button">Sign in</div>
                    </a>
                </div>
                <div id="signin_tools">
                    <span id="signin_remember">
                        <a href="#">
                            <input id="signin_keep" type="checkbox" name="signin_keep">
                            <label for="signin_keep"><span class="sm_text dim">Keep me in</span></label>
                        </a>
                    </span>
                    <a href="/d/forgot">
                        <span class="sm_text highlight2" id="signin_forgot">Forgot password?</span>
                    </a>
                </div>
            </div>
        </div>

        <div id="signup">
            <div id="signup_form">
                <div id="signup_title" class="vl_text dim">
                    <div id="signup_loading"><img src="/d/assets/sm_loading.gif"></div>
                    New? &mdash; Sign up!
                </div>
                <div id="signup_error" class="center error sm_text"></div>
                <div id="signup_content">
                    <div id="signup_email_div"><input class="md_input w200" type="text" placeholder="Email" id="signup_email"></div>
                    <div id="signup_password_div"><input class="md_input w200" type="password" placeholder="Password" id="signup_password"></div>
                    <div id="signup_name_div"><input class="md_input w200" type="text" placeholder="Full name" id="signup_name"></div>
                    <input class="md_input w200" type="hidden" id="card_token">
                </div>
                <a href="#" onclick="Signup.signup(event);">
                    <div id="signup_button" class="sm_button submit_button md_header">Sign up</div>
                </a>
            </div>
        </div>
    </div>


    <div id="confirm">

        <div><span class="sp_text white">Confirm</span></div>
        <p class="note lg_text white">Visit your email inbox and click on the provided link.</p>
        <p class="note lg_text white">Don't forget to visit your spam folder.
            Need help? email <a href="mailto:<%=Vars.supportEmail%>"><span class="lg_header white"><%=Vars.supportEmail%></span></a></p>

        <p class="note smd_text white">For the safety and privacy of all our members we require everyone to confirm their email address.</p>
    </div>


</div>



<script type="text/javascript">

    // Binding the return key for the form
    $('#signin').keypress( function(event) {
        if(event.which == $.ui.keyCode.ENTER){
            Signin.signin(event);
            return false;
        }
    });

    // Starting the focus on the email field
    $(function() {
        $("#signin_email").focus();
    });

    // Binding the return key for the form
    $('#signup').keypress( function(event) {
        if(event.which == $.ui.keyCode.ENTER){
            Signup.signup(event);
            return false;
        }
    });

</script>


