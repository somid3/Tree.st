<%@ include file="../../all.jsp" %>
<%@ include file="../load.jsp" %>
<%
    // Retrieving user
    User user = UserDao.getById(null, userId);

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
            <div class="name sm_text dim">Your first name</div>
            <div class="input">

                <%
                    app_d_title = "First name";
                    app_d_message = "Enter your first name, if you have a middle name, or many middle names, write them one after another. For example, 'Ariel Joy', where 'Ariel is the first name and 'Joy' the middle name.";
                    app_d_position = HtmlDesign.Positions.BOTTOM;
                %>
                <%@ include file="../../includes/app_d_mini_tooltip.jsp"%>

                <input name="fname" class="md_input w250 bottom_line" value="<%= user.getFirstName() %>">
            </div>
        </div>

        <div class="element">
            <div class="name sm_text dim">Your last name</div>
            <div class="input">

                <%
                    app_d_title = "Last name";
                    app_d_message = "Based on your profile you can automatically be added to very specific smart groups. When this occurs you will be notified.";
                    app_d_position = HtmlDesign.Positions.BOTTOM;
                %>
                <%@ include file="../../includes/app_d_mini_tooltip.jsp"%>

                <input name="lname" class="md_input w250 bottom_line" value="<%= user.getLastName() %>">
            </div>
        </div>

        <div class="element">
            <div class="name sm_text dim">Current password</div>
            <div class="input">

                <%
                    app_d_title = "Current Password";
                    app_d_message = "Smart groups are the places of great conversations. Here set the rate at which you receive new message notifications or digests.";
                    app_d_position = HtmlDesign.Positions.BOTTOM;
                %>
                <%@ include file="../../includes/app_d_mini_tooltip.jsp"%>

                <input name="pass" type="password" class="md_input w250 bottom_line">
            </div>
        </div>

        <div class="actionable">
            <a href="#" onclick="UGD.submitNameChange(event, '<%= hSettingsId %>', '<%= hFormId %>')"><div class="action md_button submit_button">Save Settings</div></a>
            <div class="loading"><img src="./img/sm_loading.gif"></div>
        </div>

    </form>

</div>