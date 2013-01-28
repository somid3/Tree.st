<%@ include file="../all.jsp" %>
<%
    Integer userId = StringUtils.parseInt(request.getParameter("uid"));
    String passwordChecksum = StringUtils.parseString(request.getParameter("xcs"));
%>
<div id="forgot">
    <div id="form">
        <div class="element">
            <div><span class="sp_text">Reset</span></div>
        </div>

        <div class="element error smd_text" id="error"></div>

        <div class="element">
            <div class="text smd_header dim"><label for="pass">New password</label></div>
            <div><input id="pass" placeholder="" type="password" name="pass" class="lg_input w200"></div>
        </div>

        <div class="element">
            <div class="text smd_header dim"><label for="pass_again">New password again</label></div>
            <div><input id="pass_again" type="password" name="pass" class="lg_input w200"></div>
        </div>

        <div class="element">
            <div class="note sm_text dim">
                After you reset your password you will automatically get logged in. Enjoy!
            </div>
        </div>

        <div class="element">
            <a href="#" onclick="Forgot.set(event, <%= userId%>, '<%= passwordChecksum %>');"><div id="button" class="lg_button submit_button">Set new password</div></a>
        </div>
    </div>
</div>

<script type="text/javascript">

    // Binding the return key for the form
    $('#form').keypress(function(event) {
        if(event.which == $.ui.keyCode.ENTER){
            Event.preventDefault(event);
            Forgot.set(event, <%= userId%>, '<%= passwordChecksum %>');
            return false;
        }
    });

    // Starting the focus on the email field
    $(function() {
      $("#pass").focus();
    });

</script>