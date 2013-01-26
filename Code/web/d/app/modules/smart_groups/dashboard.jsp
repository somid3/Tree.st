<%@ include file="../../all.jsp" %>
<%@ include file="./load.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));

    // Retrieving smart group
    SmartGroup group = SmartGroupDao.getByNetworkIdAndRef(null, networkId, smartGroupRef);
%>
<div id="smart_group_dashboard">
    <div class="details">
        <div class="message">
            <div class="title lg_header highlight6"><%= group.getName() %></div>
            <div class="comment sm_text dim2"><%= StringUtils.concat(group.getDescription(), 60, "&hellip;") %></div>
        </div>
        <div class="settings">

            <%
                // Setting up variables for include
                Integer sgroup_e_networkId = group.getNetworkId();
                Integer sgroup_e_userId = userId;
                Integer sgroup_e_smartGroupRef = group.getSmartGroupRef();
            %>
            <%@ include file="includes/sgroup_e_apply_state.jsp"%>

        </div>
    </div>
    <div class="menu">
        <div class="shortcuts">

            <% if (!group.getVisibility().equals(SmartGroupVisibilityEnum.PRIVATE)) { %>
                <a href="#" onclick="SmartGroupDashboard.go(event, SmartGroupDashboard.Section.SHARED_ITEMS, {nid: <%= group.getNetworkId() %>, sgr: <%= group.getSmartGroupRef() %>});"><div class="shortcut sm_text light_button" id="smart_group_shortcut_share">Share</div></a>
            <% } %>

            <a href="#" onclick="SmartGroupDashboard.go(event, SmartGroupDashboard.Section.MEMBERS, {nid: <%= group.getNetworkId() %>, sgr: <%= group.getSmartGroupRef() %>});"><div class="shortcut sm_text light_button" id="smart_group_shortcut_members">Members (<%= group.getResultsCount() %>)</div></a>

            <% if (group.getUserId().equals(userId)) { %>
                <a href="#" onclick="SmartGroupDashboard.hideSmartGroup(event, <%= group.getNetworkId() %>, <%= group.getSmartGroupRef() %>);"><div class="shortcut sm_text light_button">Remove</div></a>
            <% } %>

        </div>
    </div>
</div>

<div id="smart_group_canvas"></div>

<script type="text/javascript">

    <% if (!group.getVisibility().equals(SmartGroupVisibilityEnum.PRIVATE)) { %>

        SmartGroupDashboard.go(null, SmartGroupDashboard.Section.SHARED_ITEMS, {nid: <%= group.getNetworkId() %>, sgr: <%= group.getSmartGroupRef() %>});

    <% } else { %>

        SmartGroupDashboard.go(null, SmartGroupDashboard.Section.MEMBERS, {nid: <%= group.getNetworkId() %>, sgr: <%= group.getSmartGroupRef() %>});

    <% } %>


</script>