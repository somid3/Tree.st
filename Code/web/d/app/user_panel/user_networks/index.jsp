<%@ include file="../../all.jsp" %>
<%@ include file="../load.jsp" %>
<%

    // Retrieve networks user is a member of
    List<UserToNetwork> meToNetworks = UserToNetworkDao.getByUserIdAndLowestRole(null, meId, RoleEnum.VISITOR, SqlLimit.ALL);

    Network network = null;
    String hTargetId = null;
    String hShortcutsId = null;
    String hShortcutId = null;
%>

<script type="text/javascript">
    UND = new UserNetworksDashboard();
</script>
<div class="canvas_container">

    <% for (UserToNetwork userToNetwork : meToNetworks) {

        network = NetworkDao.getById(null, userToNetwork.getNetworkId());
        hTargetId = "settings" + network.getId(); %>

        <div class="user_setting">

            <div class="top">

                <img src="<%= network.getIconResourceUrl() %>"/>
                <span class="md_header dim"><%= network.getName() %></span>
                <span class="sm_text dim2">&mdash; joined <%= PrettyDate.toString(userToNetwork.getCreatedOn()) %></span>

                <% if (userToNetwork.getCurrentPoints() > 0) { %>
                    <span class="sm_text dim2">&mdash; <%= userToNetwork.getCurrentPoints() %> points</span>
                <% } %>

            </div>

            <% hShortcutsId = HtmlUtils.getRandomId(); %>
            <div id="<%= hShortcutsId %>" class="shortcuts">
                <% hShortcutId = HtmlUtils.getRandomId(); %>
                <a href="#" onclick="UND.clickItem(event, '<%= hShortcutsId %>', '<%= hShortcutId %>', '<%= hTargetId %>', './user_panel/user_networks/email_notifications.jsp', {nid: <%= network.getId() %>})"><div id="<%= hShortcutId %>" class="shortcut sm_text light_button">Email Notifications</div></a>
            </div>

            <div id="<%= hTargetId %>"></div>

        </div>

    <% } %>



</div>
