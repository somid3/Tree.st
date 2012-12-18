<%@ include file="../../all.jsp" %>
<%@ include file="load.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    Integer sharedItemRef = StringUtils.parseInt(request.getParameter("sir"));

    User me = UserDao.getById(null, userId);

    // Retrieving smart group
    SharedItem sharedItem = SharedItemDao.getByNetworkIdAndSmartGroupRefAndRef(null, networkId, smartGroupRef, sharedItemRef);
%>

<script type="text/javascript">
    SI = new SharedItem();
    SI.networkId = <%= networkId %>;
    SI.smartGroupRef = <%= smartGroupRef %>;
</script>

<div id="share_canvas">

    <%
        SharedItem share_c_sharedItem = sharedItem;
        User share_c_me = me;
        Integer share_c_fromSmartGroupRef = sharedItemRef;
        Integer share_c_settingSharedItemDisplayCreatedOn    = NetworkIntegerSettingEnum.SHARED_ITEM_DISPLAY_CREATED_ON.getValueByNetwork(networkId);
        Integer share_c_settingSharedCommentDisplayCreatedOn = NetworkIntegerSettingEnum.SHARED_COMMENT_DISPLAY_CREATED_ON.getValueByNetwork(networkId);
        Integer share_c_settingSharedCommentPointsPer        = NetworkIntegerSettingEnum.SHARED_COMMENT_POINTS_PER.getValueByNetwork(networkId);
    %>

    <%@ include file="includes/share_c_shared_item.jsp" %>

</div>
