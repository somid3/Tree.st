<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    Integer sharedItemRef = StringUtils.parseInt(request.getParameter("sir"));
    Integer sharedCommentRef = StringUtils.parseInt(request.getParameter("ref"));

    SharedComment share_a_sharedComment = SharedCommentDao.getByNetworkIdAndSmartGroupRefAndSharedItemRefAndRef(null, homeId, smartGroupRef, sharedItemRef, sharedCommentRef);
    User share_a_me = me;
    Map<NetworkAlphaSettingEnum, String> share_a_networkAlphaSettings = NetworkAlphaSettingEnum.getMapByNetworkId(homeId);
    Map<NetworkIntegerSettingEnum, Integer> share_a_networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(homeId);
%>
<%@ include file="includes/share_a_shared_comment.jsp" %>