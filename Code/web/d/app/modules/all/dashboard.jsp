<%@ include file="../../all.jsp" %>
<%@ include file="./load.jsp" %>

<script type="text/javascript">
    AMD = new AllMembersDashboard();
    AMD.networkId = <%= homeId %>;
</script>
<div id="all_members_dashboard">

        <div class="shortcuts">

            <span class="smd_text dim2">Sort all members by:</span>

            <a href="#" onclick="AMD.go(event, AllMembersDashboard.Section.BY_POINTS);"><div class="shortcut sm_text light_button" id="all_members_shortcut_points">Collaboration Points</div></a>
            <a href="#" onclick="AMD.go(event, AllMembersDashboard.Section.BY_JOINED);"><div class="shortcut sm_text light_button" id="all_members_shortcut_joined">Recently Joined</div></a>

        </div>
</div>

<div id="all_members_canvas"></div>

<script type="text/javascript">

    AMD.go(null, AllMembersDashboard.Section.BY_JOINED);

</script>