<%@ include file="../../all.jsp" %>

<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    Integer sharedItemRef = StringUtils.parseInt(request.getParameter("sir"));
    Integer sharedCommentRef = StringUtils.parseInt(request.getParameter("scr"));
    SharedVoteEnum applyVote = SharedVoteEnum.getById(StringUtils.parseInt(request.getParameter("sve")));

    // Apply the desired vote
    SharedVoteServices.changeVote(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, applyVote);

    // Retrieving the shared votable


    User share_b_me = UserDao.getById(null, userId);
    SharedVotable share_b_sharedVotable = null;
    Map<NetworkAlphaSettingEnum, String> share_b_networkAlphaSettings = null;
    Map<NetworkIntegerSettingEnum, Integer> share_b_networkIntegerSettings = null;
%>
<%@ include file="includes/share_b_shared_vote.jsp" %>
