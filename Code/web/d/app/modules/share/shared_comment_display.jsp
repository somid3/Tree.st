<%@ include file="../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer sgr = StringUtils.parseInt(request.getParameter("sgr"));
    Integer sir = StringUtils.parseInt(request.getParameter("sir"));
    Integer ref = StringUtils.parseInt(request.getParameter("ref"));

    SharedComment share_a_sharedComment = SharedCommentDao.getByNetworkIdAndSmartGroupRefAndSharedItemRefAndRef(null, networkId, sgr, sir, ref);
    User share_a_me = UserDao.getById(null, userId);
    Map<NetworkAlphaSettingEnum, String> share_a_networkAlphaSettings = NetworkAlphaSettingEnum.getMapByNetworkId(networkId);
    Map<NetworkIntegerSettingEnum, Integer> share_a_networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(networkId);
%>
<%@ include file="includes/share_a_shared_comment.jsp" %>