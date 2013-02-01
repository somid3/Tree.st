<%@ include file="../../all.jsp" %>
<%@ include file="../load.jsp" %>
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
            <div class="name sm_text dim">New email:</div>
            <div class="input">

                <%
                    app_d_title = "New email";
                    app_d_message = "Please enter your new email. Later we will send you a confirmation email.";
                    app_d_position = HtmlDesign.Positions.BOTTOM;
                %>
                <%@ include file="../../includes/app_d_mini_tooltip.jsp"%>

                <input name="email1" class="md_input w250 bottom_line" value="<%= me.getEmail() %>">
            </div>
        </div>

        <div class="element">
            <div class="name sm_text dim">Type new email again:</div>
            <div class="input">

                <%
                    app_d_title = "Email again";
                    app_d_message = "To make sure there are no mistakes, please enter your new email address once again.";
                    app_d_position = HtmlDesign.Positions.BOTTOM;
                %>
                <%@ include file="../../includes/app_d_mini_tooltip.jsp"%>

                <input name="email2" class="md_input w250 bottom_line" value="<%= me.getEmail() %>">
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
            After you save your settings we will require you<br/>
            to confirm your new email address.
        </div>

        <div class="actionable">
            <div class="loading"><img src="./img/sm_loading.gif"></div>
            <a href="#" onclick="UGD.submitEmailChange(event, '<%= hSettingsId %>', '<%= hFormId %>')"><div class="action md_button submit_button">Save Settings</div></a>
            <a href="#" onclick="UGD.cancelSetting(event, '<%= hSettingsId %>')"><div class="action light_button md_button">Cancel</div></a>
        </div>

    </form>

</div>

<script type="text/javascript">
$('#<%= hFormId %>').keypress( function(event) {
    if(event.which == $.ui.keyCode.ENTER){
        UGD.submitEmailChange(event, '<%= hSettingsId %>', '<%= hFormId %>');
        return false;
    }
});
</script>
