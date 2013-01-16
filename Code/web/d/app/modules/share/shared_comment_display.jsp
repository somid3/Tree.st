<%@ include file="../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer sgr = StringUtils.parseInt(request.getParameter("sgr"));
    Integer sir = StringUtils.parseInt(request.getParameter("sir"));
    Integer ref = StringUtils.parseInt(request.getParameter("ref"));

    SharedComment share_a_sharedComment = SharedCommentDao.getByNetworkIdAndSmartGroupRefAndSharedItemRefAndRef(null, networkId, sgr, sir, ref);
    User share_a_me = UserDao.getById(null, userId);
    Integer share_a_settingSharedCommentDisplayCreatedOn = NetworkIntegerSettingEnum.SHARED_COMMENT_DISPLAY_CREATED_ON.getValueByNetworkId(networkId);;
%>
<%@ include file="includes/share_a_shared_comment.jsp" %>