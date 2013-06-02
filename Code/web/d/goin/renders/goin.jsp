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
        padding: 10px 10px 5px 10px;
    }

    #signin {
        background-color: white;
        border: solid 1px <%= HtmlDesign.dim2 %>;
        padding: 10px;
        width: 240px;
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
        margin-top: 5px;
    }

    #signup,
    #payment {
        margin-top: 20px;
        background-color: white;
        border: solid 1px <%= HtmlDesign.dim2 %>;
        padding: 10px;
        width: 240px;
    }

    #signup_title,
    #payment_title {
        margin-bottom: 10px;
        width: 230px;
    }

    #signup_error,
    #payment_error {
        margin-bottom: 5px;
        width: 220px;
    }

    #signup_loading,
    #payment_loading {
        float: right;
        position: relative;
        display: none;
    }

    #signup_email_div,
    #signup_name_div,
    #signup_password_div{
        margin-bottom: 5px;
        background-color: white;
        padding: 4px;
        width: 225px;
        border: solid 1px <%= HtmlDesign.dim3 %>;
    }

    #payment_ccnumber_div {
        margin-bottom: 5px;
        background-color: white;
        padding: 4px;
        width: 225px;
        border: solid 1px <%= HtmlDesign.dim3 %>;
    }

    #payment_exp_div {
        margin-bottom: 10px;
        background-color: white;
        padding: 4px;
        width: 225px;
        border: solid 1px <%= HtmlDesign.dim3 %>;
    }

    #payment_cvc_div {
        margin-bottom: 10px;
        background-color: white;
        padding: 4px;
        width: 225px;
        border: solid 1px <%= HtmlDesign.dim3 %>;
    }

    #signup_button {
        width: 212px;
    }

    #provided_by {
        margin-top: 20px;
        background-color: white;
        text-align: center;
        padding: 2px 5px;
        float: right;
    }

</style>

<div id="console">
    <input type="hidden" id="network_id" value="<%= networkId %>">
    <input type="hidden" id="network_checksum" value="<%= networkChecksum %>">

    <div id="sign">
        <div id="signin" class="shadow">
            <div id="signin_loading"><img src="/d/goin/img/sm_loading.gif"></div>
            <div id="signin_error" class="error sm_text"></div>
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

                    <span class="dim">&mdash;</span>

                    <a href="/d/forgot">
                        <span class="sm_text dim" id="signin_forgot">Forgot password?</span>
                    </a>
                </div>
            </div>
        </div>

        <div id="signup" class="shadow">
            <div id="signup_form">
                <div id="signup_title" class="vl_text dim">
                    <div id="signup_loading"><img src="/d/goin/img/sm_loading.gif"></div>
                    New? &mdash; Sign up!
                </div>
                <div id="signup_error" class="error sm_text"></div>
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

    <div id="payment" style="display: none;">
        <div id="payment_form">
            <div id="payment_title" class="vl_text dim">
                <div id="payment_loading"><img src="/d/goin/img/sm_loading.gif"></div>
                Payment Details
            </div>
            <div id="payment_error" class="error sm_text"></div>
            <div id="payment_content">
                <div id="payment_ccnumber_div"><input class="md_input w200" type="text" placeholder="...._...._...._...." id="payment_ccnumber"></div>
                <div id="payment_exp_div"><input class="md_input w200" type="text" placeholder="mm/yy" id="payment_exp"></div>
                <div id="payment_cvc_div"><input class="md_input w200" type="text" placeholder="CVC" id="payment_cvc"></div>
            </div>
            <a href="#" onclick="Payment.createToken(event);">
                <div id="payment_button" class="sm_button submit_button md_header">Let's go</div>
            </a>
        </div>
    </div>

    <div id="confirm">

        <div><span class="sp_text white">Confirm</span></div>
        <p class="note lg_text white">Visit your email inbox and click on the provided link.</p>
        <p class="note lg_text white">Don't forget to visit your <span class="sp_header">spam</span> folder.
            Need help? email <a href="mailto:<%=Vars.supportEmail%>"><span class="lg_header white"><%=Vars.supportEmail%></span></a></p>

        <p class="note smd_text white">For the safety and privacy of all our members we require everyone to confirm their email address.</p>
    </div>

    <div id="provided_by">
        <div class="vsm_text dim">Provided by</div>
        <a href="http://<%= Vars.domain %>"><img src="/d/assets/vsm_logo.png"></a>
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

    // Binding the return key for the form
    $('#signup').keypress( function(event) {
        if(event.which == $.ui.keyCode.ENTER){
            Signup.signup(event);
            return false;
        }
    });

    // Binding the return key for the form
    $('#payment').keypress( function(event) {
        if(event.which == $.ui.keyCode.ENTER){
            Payment.createToken(event);
            return false;
        }
    });

    // Starting the focus on the email field
    $(function() {
        $("#signin_email").focus();
    });

    // Setting up Stripe
    Stripe.setPublishableKey('<%= Vars.stripePublishableKey%>');
</script>


