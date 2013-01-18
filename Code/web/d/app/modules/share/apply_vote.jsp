<%@ include file="../../all.jsp" %>

<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    Integer sharedItemRef = StringUtils.parseInt(request.getParameter("sir"));
    Integer sharedCommentRef = StringUtils.parseInt(request.getParameter("scr"));
    SharedVoteEnum applyVote = SharedVoteEnum.getById(StringUtils.parseInt(request.getParameter("av")));

    // Apply the desired vote
    SharedVoteServices.changeVote(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, applyVote);

    // Retrieving the shared votable
    // TODO: put this in a more elegant location
    // TODO: put this in a more elegant location
    // TODO: put this in a more elegant location, like a SharedVotableDao
    SharedVotable share_b_sharedVotable = null;
    if (sharedCommentRef == SharedComment.ANY_SHARED_COMMENT_REF)
        share_b_sharedVotable = SharedItemDao.getByNetworkIdAndSmartGroupRefAndRef(null, networkId, smartGroupRef, sharedItemRef);
    else
        share_b_sharedVotable = SharedCommentDao.getByNetworkIdAndSmartGroupRefAndSharedItemRefAndRef(null, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);

    User share_b_me = UserDao.getById(null, userId);
    Map<NetworkAlphaSettingEnum, String> share_b_networkAlphaSettings = NetworkAlphaSettingEnum.getMapByNetworkId(networkId);
    Map<NetworkIntegerSettingEnum, Integer> share_b_networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(networkId);
%>
<%@ include file="includes/share_b_shared_vote.jsp" %>
