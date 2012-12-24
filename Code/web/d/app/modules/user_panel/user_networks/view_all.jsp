<%@ include file="../../../all.jsp" %>
<%@ include file="../load.jsp" %>
<%

    // Retrieve networks user is a member of
    List<UserToNetwork> userToNetworks = UserToNetworkDao.getByUserIdAndLowestRole(null, userId, RoleEnum.VISITOR);

    Network network = null;
    String hTargetId = null;
    String hSelectorId = null;

%>

<script type="text/javascript">
    UND = new UserNetworksDashboard();
</script>
<div id="user_networks" class="canvas_container">

    <% for (UserToNetwork userToNetwork : userToNetworks) {

        network = NetworkDao.getById(null, userToNetwork.getNetworkId());
        hTargetId = "settings" + network.getId(); %>

        <div class="user_network">

            <div class="top">

                <img src="<%= network.getIconResourceUrl() %>"/>
                <span class="md_header dim"><%= network.getName() %></span>
                <span class="sm_text dim2">&mdash; joined <%= PrettyDate.toString(userToNetwork.getCreatedOn()) %></span>

                <% if (userToNetwork.getCurrentPoints() > 0) { %>
                    <span class="sm_text dim2">&mdash; <%= userToNetwork.getCurrentPoints() %> points</span>
                <% } %>

            </div>

            <div class="shortcuts">
                <% hSelectorId = HtmlUtils.getRandomId(); %>
                <a href="#" onclick="UND.clickItem(event, '<%= hSelectorId%>', '<%= hTargetId %>', './modules/user_panel/user_networks/email_notifications.jsp', {nid: <%= network.getId() %>})"><div id="<%= hSelectorId %>" class="shortcut sm_text highlight2">Email Notifications</div></a>
            </div>

            <div id="<%= hTargetId %>"></div>

        </div>

    <% } %>



</div>
