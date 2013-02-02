<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%@ include file="../load.jsp" %>
<%

    // Retrieve networks user is a member of
    List<UserToNetwork> meToNetworks = UserToNetworkDao.getByUserIdAndLowestRole(null, meId, RoleEnum.VISITOR, SqlLimit.ALL);

    Network network = null;
    String hTargetId = null;
    String hShortcutsId = null;
    String hShortcutId = null;
    Integer hasIcon = null;
%>
<div class="canvas_container">

    <% for (UserToNetwork meToNetwork : meToNetworks) {

        network = NetworkDao.getById(null, meToNetwork.getNetworkId());
        hasIcon = NetworkIntegerSettingEnum.UI_HAS_ICON.getValueByNetworkId(meToNetwork.getNetworkId());
        hTargetId = "settings" + network.getId(); %>

        <div class="user_setting">

            <div class="top">

                <img src="<%= network.getIconResourceUrl(hasIcon == 0) %>"/>
                <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.smartGroups(meToNetwork.getNetworkId())%>')"><span class="md_header highlight2"><%= network.getName() %></span></a>
                <span class="sm_text dim2">&mdash; joined <%= PrettyDate.toString(meToNetwork.getCreatedOn()) %></span>

                <% if (meToNetwork.getCurrentPoints() > 0) { %>
                    <span class="sm_text dim2">&mdash; <%= meToNetwork.getCurrentPoints() %> points</span>
                <% } %>

            </div>

            <% hShortcutsId = HtmlUtils.getRandomId(); %>
            <div id="<%= hShortcutsId %>" class="shortcuts">
                <% hShortcutId = HtmlUtils.getRandomId(); %>
                <a href="#" onclick="UserNetworksDashboard.clickItem(event, '<%= hShortcutsId %>', '<%= hShortcutId %>', '<%= hTargetId %>', './user_panel/user_networks/email_notifications.jsp', {nid: <%= network.getId() %>})"><div id="<%= hShortcutId %>" class="shortcut sm_text light_button">Email Notifications</div></a>

                <% hShortcutId = HtmlUtils.getRandomId(); %>
                <a href="#" onclick="UserNetworksDashboard.clickItem(event, '<%= hShortcutsId %>', '<%= hShortcutId %>', '<%= hTargetId %>', './user_panel/user_networks/leave.jsp', {nid: <%= network.getId() %>})"><div id="<%= hShortcutId %>" class="shortcut sm_text light_button">Leave Community</div></a>
            </div>

            <div id="<%= hTargetId %>"></div>

        </div>

    <% } %>



</div>
