<%@ include file="../all.jsp" %>
<%
    // Retrieving log in details
    String defaultEmail = webUtils.getCookieValue("ue");
    if (defaultEmail == null) defaultEmail = "";
%>
<div id="forgot">
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

<script type="text/javascript">

    // Binding the return key for the form
    $('#form').keypress(function(e) {
        if(e.which == $.ui.keyCode.ENTER){
            e.preventDefault();
            Forgot.send(e);
            return false;
        }
    });

    // Starting the focus on the email field
    $(function() {
      $("#email").focus();
    });

</script>