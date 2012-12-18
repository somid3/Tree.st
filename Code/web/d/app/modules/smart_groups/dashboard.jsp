<%@ include file="../../all.jsp" %>
<%@ include file="./load.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer sgr = StringUtils.parseInt(request.getParameter("sgr"));

    // Retrieving smart group
    SmartGroup group = SmartGroupDao.getByNetworkIdAndRef(null, networkId, sgr);
%>
<script type="text/javascript">
    SGD = new SmartGroupDashboard();
    SGD.networkId = <%= networkId %>;
    SGD.smartGroupRef = <%= sgr %>;
</script>
<div id="smart_group_dashboard">
    <div class="details">
        <div class="message">
            <div class="title md_header highlight6"><%= group.getName() %></div>
            <div class="comment sm_text dim"><%= StringUtils.concat(group.getDescription(), 60, "&hellip;") %></div>
        </div>
        <div class="settings">

            <%
                // Setting up variables for include
                Integer sgroup_e_networkId = group.getNetworkId();
                Integer sgroup_e_userId = userId;
                Integer sgroup_e_smartGroupRef = group.getRef();
            %>
            <%@ include file="includes/sgroup_e_favorite.jsp"%>

        </div>
    </div>
    <div class="menu">
        <div class="shortcuts">

            <% if (!group.getVisibility().equals(SmartGroupVisibilityEnum.PRIVATE)) { %>
                <a href="#" onclick="SGD.go(event, SmartGroupDashboard.Section.SHARE);"><div class="shortcut sm_text dim" id="smart_group_shortcut_share">Share</div></a>
            <% } %>

            <a href="#" onclick="SGD.go(event, SmartGroupDashboard.Section.MEMBERS);"><div class="shortcut sm_text dim" id="smart_group_shortcut_members">Members (<%= group.getResultsCount() %>)</div></a>

            <% if (group.getUserId().equals(userId)) { %>
                <a href="#" onclick="SGD.hideSmartGroup(event, <%= group.getRef() %>);"><div class="shortcut sm_text dim">Remove</div></a>
            <% } %>

        </div>
    </div>
</div>

<div id="smart_group_canvas"></div>

<script type="text/javascript">

    // Is there an automatic redirection set?
    var goSir = Go.checkAndRemove("go_sir");
    if (goSir != null) {

        SGD.go(null, SmartGroupDashboard.Section.SHARED_ITEM, {nid: SGD.networkId, sgr: SGD.smartGroupRef, sir: goSir});

    } else {

        <% if (!group.getVisibility().equals(SmartGroupVisibilityEnum.PRIVATE)) { %>

            SGD.go(null, SmartGroupDashboard.Section.SHARE);

        <% } else { %>

            SGD.go(null, SmartGroupDashboard.Section.MEMBERS);

        <% } %>

    }

</script>