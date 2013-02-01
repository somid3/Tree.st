<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%@ include file="./load.jsp" %>
<%
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));

    // Retrieving smart group
    SmartGroup group = SmartGroupDao.getByNetworkIdAndRef(null, homeId, smartGroupRef);
%>
<div id="smart_group_dashboard">
    <div class="details">
        <div class="message">
            <div class="title smd_header highlight6"><%= group.getName() %></div>
            <div class="comment sm_text dim2"><%= StringUtils.concat(group.getDescription(), 60, "&hellip;") %></div>
        </div>
        <div class="settings">

            <%
                // Setting up variables for include
                Integer sgroup_e_networkId = group.getNetworkId();
                Integer sgroup_e_userId = meId;
                Integer sgroup_e_smartGroupRef = group.getSmartGroupRef();
            %>
            <%@ include file="includes/sgroup_e_apply_state.jsp"%>

        </div>
    </div>
    <div class="menu">
        <div class="shortcuts">

            <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.smartGroupShare(group.getNetworkId(), group.getSmartGroupRef()) %>');"><div class="shortcut sm_text light_button" id="smart_group_shortcut_share">Share</div></a>

            <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.smartGroupMembers(group.getNetworkId(), group.getSmartGroupRef()) %>');;"><div class="shortcut sm_text light_button" id="smart_group_shortcut_members">Members (<%= group.getResultsCount() %>)</div></a>

            <% if (group.getUserId().equals(meId)) { %>
                <a href="#" onclick="SmartGroupDashboard.hideSmartGroup(event, <%= group.getNetworkId() %>, <%= group.getSmartGroupRef() %>);"><div class="shortcut sm_text light_button">Remove</div></a>
            <% } %>

        </div>
    </div>
</div>

<div id="smart_group_canvas"></div>