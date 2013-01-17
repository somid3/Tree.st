<%@ include file="../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer sgr = StringUtils.parseInt(request.getParameter("sgr"));
    Integer ref = StringUtils.parseInt(request.getParameter("ref"));

    User share_c_me = UserDao.getById(null, userId);
    Integer share_c_fromSmartGroupRef = sgr;
    SharedItem share_c_sharedItem = SharedItemDao.getByNetworkIdAndSmartGroupRefAndRef(null, networkId, sgr, ref);
    Map<NetworkAlphaSettingEnum, String> share_c_networkAlphaSettings = NetworkAlphaSettingEnum.getMapByNetworkId(networkId);
    Map<NetworkIntegerSettingEnum, Integer> share_c_networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(networkId);
%>
<%@ include file="includes/share_c_shared_item.jsp" %>