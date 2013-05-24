<%@ include file="../all.jsp" %>
<%
    // Retrieving log in details
    String defaultEmail = webUtils.getCookieValue("ue");
    if (defaultEmail == null) defaultEmail = "";
%>

<div id="forgot" class="shadow center">
    <div id="form">
        <div class="element">
            <div class="sp_text">Password</div>
        </div>

        <div class="element error smd_text" id="error"></div>

        <div class="element">
            <div class="note lg_text">Let's set you up with a new password. What is your email address?</div>
        </div>

        <div class="element">
            <div class="text smd_header dim">Email</div>
            <div><input id="email" type="text" name="email" class="lg_input" value="<%= defaultEmail %>"></div>
        </div>

        <div class="element">
            <div class="note smd_text dim">Please only continue if you can access your email inbox within the next hour</div>
        </div>

        <div class="element">
            <a href="#" onclick="Forgot.send(event);"><div id="button" class="lg_button submit_button">Continue</div></a>
        </div>
    </div>
</div>

<div id="check" class="center shadow">
    <div><span class="sp_text white">Email Sent</span></div>

    <p class="lg_text white">Visit your email inbox, we sent you a reset link that is valid for one hour only</p>

    <p class="lg_text white">Don't forget to visit your <span class="vl_header">spam</span> folder.
    Need help? email <a href="mailto:<%=Vars.supportEmail%>"><span class="lg_header white"><%=Vars.supportEmail%></span></a></p>

    <p class="smd_text white">
        We need to validate your identity via email before you can set a new password for your account
    </p>
</div>

<script type="text/javascript">

    // Binding the return key for the form
    $('#forgot').keypress(function(e) {
        if(e.which == $.ui.keyCode.ENTER){
            Forgot.send(e);
            return false;
        }
    });

    // Starting the focus on the email field
    $(document).ready( $(function() { $("#email").focus(); }));

</script>