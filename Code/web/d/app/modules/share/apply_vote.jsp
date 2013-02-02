<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>

<%
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    Integer sharedItemRef = StringUtils.parseInt(request.getParameter("sir"));
    Integer sharedCommentRef = StringUtils.parseInt(request.getParameter("scr"));
    SharedVoteEnum applyVote = SharedVoteEnum.getById(StringUtils.parseInt(request.getParameter("av")));

    // Apply the desired vote
    SharedVoteServices.changeVote(meId, homeId, smartGroupRef, sharedItemRef, sharedCommentRef, applyVote);

    // Retrieving the shared votable
    // TODO: put this in a more elegant location
    // TODO: put this in a more elegant location
    // TODO: put this in a more elegant location, like a SharedVotableDao
    SharedVotable share_b_sharedVotable = null;
    if (sharedCommentRef == SharedComment.ANY_SHARED_COMMENT_REF)
        share_b_sharedVotable = SharedItemDao.getByNetworkIdAndSmartGroupRefAndRef(null, homeId, smartGroupRef, sharedItemRef);
    else
        share_b_sharedVotable = SharedCommentDao.getByNetworkIdAndSmartGroupRefAndSharedItemRefAndRef(null, homeId, smartGroupRef, sharedItemRef, sharedCommentRef);

    Map<NetworkAlphaSettingEnum, String> share_b_networkAlphaSettings = NetworkAlphaSettingEnum.getMapByNetworkId(homeId);
    Map<NetworkIntegerSettingEnum, Integer> share_b_networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(homeId);
%>
<%@ include file="includes/share_b_shared_vote.jsp" %>
