<%@ include file="../../all.jsp" %>

<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer ref = StringUtils.parseInt(request.getParameter("sgr"));

    // Retrieving search group
    SmartGroup smartGroup = SmartGroupDao.getByNetworkIdAndRef(null, networkId, ref);

    SmartGroup sgroup_a_smartGroup = smartGroup;
    Boolean sgroup_a_editable = false;
%>
<%@ include file="includes/sgroup_a_criterion.jsp"%>

<div id="smart_group_faces">
</div>

<script type="text/javascript">

    // Display faces for the query once it is loaded
    SS.submitQuery(<%= smartGroup.getNetworkId() %>, <%= smartGroup.getSmartGroupRef() %>, null);

</script>