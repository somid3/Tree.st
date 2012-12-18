<%@ include file="../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer sgr = StringUtils.parseInt(request.getParameter("sgr"));
    Integer ref = StringUtils.parseInt(request.getParameter("ref"));

    SharedItem share_c_sharedItem = SharedItemDao.getByNetworkIdAndSmartGroupRefAndRef(null, networkId, sgr, ref);
    User share_c_me = UserDao.getById(null, userId);
    Integer share_c_fromSmartGroupRef = sgr;
    Integer share_c_settingSharedItemDisplayCreatedOn    = NetworkIntegerSettingEnum.SHARED_ITEM_DISPLAY_CREATED_ON.getValueByNetwork(networkId);
    Integer share_c_settingSharedCommentDisplayCreatedOn = NetworkIntegerSettingEnum.SHARED_COMMENT_DISPLAY_CREATED_ON.getValueByNetwork(networkId);
    Integer share_c_settingSharedCommentPointsPer        = NetworkIntegerSettingEnum.SHARED_COMMENT_POINTS_PER.getValueByNetwork(networkId);
%>
<%@ include file="includes/share_c_shared_item.jsp" %>