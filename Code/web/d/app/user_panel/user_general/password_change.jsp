<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    String app_d_title = null;
    String app_d_message = null;
    HtmlDesign.Positions app_d_position = null;

    String hSettingsId = HtmlUtils.getRandomId();
    String hFormId = HtmlUtils.getRandomId();
%>

<div id="<%= hSettingsId %>" class="user_setting_container form-verhor">
    <form id="<%= hFormId%>">

        <div class="error smd_text"></div>

        <div class="element">
            <div class="name sm_text dim">New password:</div>
            <div class="input">

                <%
                    app_d_title = "New password";
                    app_d_message = "Please ensure your new password is hard to guess, and it is longer than 5 characters.";
                    app_d_position = HtmlDesign.Positions.BOTTOM;
                %>
                <%@ include file="../../includes/app_d_mini_tooltip.jsp"%>

                <input name="new_pass1" type="password" class="md_input w250 bottom_line">
            </div>
        </div>

        <div class="element">
            <div class="name sm_text dim">New password again:</div>
            <div class="input">

                <%
                    app_d_title = "New password again";
                    app_d_message = "To make sure there are no mistakes, please enter your new password once again.";
                    app_d_position = HtmlDesign.Positions.BOTTOM;
                %>
                <%@ include file="../../includes/app_d_mini_tooltip.jsp"%>

                <input name="new_pass2" type="password" class="md_input w250 bottom_line">
            </div>
        </div>

        <div class="element">
            <div class="name sm_text dim">Current password</div>
            <div class="input">

                <%
                    app_d_title = "Current Password";
                    app_d_message = "Given that this change can alter your login capability, we ask all our users to enter their current password again.";
                    app_d_position = HtmlDesign.Positions.BOTTOM;
                %>
                <%@ include file="../../includes/app_d_mini_tooltip.jsp"%>

                <input name="pass" type="password" class="md_input w250 bottom_line">
            </div>
        </div>

        <div class="note sm_text dim">
            After your settings are saved you will be<br/>
            automatically logged out and requested to log back in
        </div>

        <div class="actionable">
            <div class="loading"><img src="./img/sm_loading.gif"></div>
            <a href="#" onclick="UGD.submitPasswordChange(event, '<%= hSettingsId %>', '<%= hFormId %>')"><div class="action md_button submit_button">Save Settings</div></a>
            <a href="#" onclick="UGD.cancelSetting(event, '<%= hSettingsId %>')"><div class="action light_button md_button">Cancel</div></a>
        </div>

    </form>

</div>

<script type="text/javascript">
$('#<%= hFormId %>').keypress( function(event) {
    if(event.which == $.ui.keyCode.ENTER){
        UGD.submitPasswordChange(event, '<%= hSettingsId %>', '<%= hFormId %>');
        return false;
    }
});
</script>