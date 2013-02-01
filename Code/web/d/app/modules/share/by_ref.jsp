<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%@ include file="load.jsp" %>
<%
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    Integer sharedItemRef = StringUtils.parseInt(request.getParameter("sir"));

    // Retrieving smart group
    SharedItem sharedItem = SharedItemDao.getByNetworkIdAndSmartGroupRefAndRef(null, homeId, smartGroupRef, sharedItemRef);
%>

<script type="text/javascript">
    SI = new SharedItem();
    SI.networkId = <%= homeId %>;
    SI.smartGroupRef = <%= smartGroupRef %>;
</script>

<div id="share_canvas">

    <%
        User share_c_me = me;
        SharedItem share_c_sharedItem = sharedItem;
        Integer share_c_fromSmartGroupRef = sharedItemRef;
        Map<NetworkIntegerSettingEnum, Integer> share_c_networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(homeId);
        Map<NetworkAlphaSettingEnum, String> share_c_networkAlphaSettings = NetworkAlphaSettingEnum.getMapByNetworkId(homeId);
    %>

    <%@ include file="includes/share_c_shared_item_box.jsp" %>

</div>
