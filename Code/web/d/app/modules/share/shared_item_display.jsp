<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    Integer sharedItemRef = StringUtils.parseInt(request.getParameter("ref"));

    Integer share_c_fromSmartGroupRef = smartGroupRef;
    SharedItem share_c_sharedItem = SharedItemDao.getByNetworkIdAndSmartGroupRefAndRef(null, homeId, smartGroupRef, sharedItemRef);
    Map<NetworkAlphaSettingEnum, String> share_c_networkAlphaSettings = NetworkAlphaSettingEnum.getMapByNetworkId(homeId);
    Map<NetworkIntegerSettingEnum, Integer> share_c_networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(homeId);
%>
<%@ include file="includes/share_c_shared_item_box.jsp" %>