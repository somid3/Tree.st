<%
{
    /* Inputs variables
     *
     *    SharedComment share_a_sharedComment = null;
     *    User share_a_me = null;
     *
     *    Map<NetworkAlphaSettingEnum, String> share_a_networkAlphaSettings = null;
     *    Map<NetworkIntegerSettingEnum, Integer> share_a_networkIntegerSettings = null;
     */
    User e_author = UserDao.getById(null, share_a_sharedComment.getUserId());
    String e_hSharedCommentId = HtmlUtils.getRandomId();
%>
<div>
    <div class="shared_comment" id="<%= e_hSharedCommentId %>">
        <div class="left">

            <% {
               Integer ul_b_networkId = share_a_sharedComment.getNetworkId();
               Integer ul_b_toUserId = share_a_sharedComment.getUserId(); %>

                <%@ include file="../../user_links/includes/ul_b_face.jsp"%>
            <% } %>

        </div>
        <div class="right">

            <% if (share_a_sharedComment.getUserId().equals(share_a_me.getId())) { %>
                <a href="#" onclick="SharedComment.hideSharedComment(
                    event,
                    <%= share_a_sharedComment.getNetworkId() %>,
                    <%= share_a_sharedComment.getSmartGroupRef() %>,
                    <%= share_a_sharedComment.getSharedItemRef() %>,
                    <%= share_a_sharedComment.getSharedCommentRef() %>,
                    '<%= e_hSharedCommentId %>')">

                    <div class="delete"><img src="./img/close_dark.png"></div>
                </a>
            <% } %>

            <div class="box">
                <a href="#" onclick="ND.viewProfile(event, <%= e_author.getId() %>);">
                    <span class="sm_header highlight2"><%= e_author.getName() %></span>:
                </a>

                <span class="sm_text dim"><%= HtmlUtils.linkify( HtmlUtils.paragraphize( share_a_sharedComment.getText() )) %></span>
            </div>

            <div class="bottom">
                <% if (share_a_networkIntegerSettings.get(NetworkIntegerSettingEnum.SHARED_COMMENT_DISPLAY_CREATED_ON) != 0) { %>
                    <span class="ago sm_text dim2"><%= PrettyDate.toString( share_a_sharedComment.getCreatedOn()) %></span>
                <% } %>

                <% {
                    User share_b_me = share_a_me;
                    SharedVotable share_b_sharedVotable = share_a_sharedComment;
                    Map<NetworkAlphaSettingEnum, String> share_b_networkAlphaSettings = share_a_networkAlphaSettings;
                    Map<NetworkIntegerSettingEnum, Integer> share_b_networkIntegerSettings = share_a_networkIntegerSettings; %>
                <%@ include file="share_b_shared_vote.jsp" %>
                <% } %>
            </div>

        </div>
    </div>
</div>
<% } %>