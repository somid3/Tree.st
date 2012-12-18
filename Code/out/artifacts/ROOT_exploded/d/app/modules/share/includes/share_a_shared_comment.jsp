<%
{
    /* Inputs variables
     *
     *    SharedComment share_a_sharedComment = null;
     *    User share_a_me = null
     *
     *    Integer share_a_settingSharedCommentDisplayCreatedOn = null;
     */
    User e_author = UserDao.getById(null, share_a_sharedComment.getUserId());
    String e_hSharedCommentId = HtmlUtils.getRandomId();
%>
<div>
    <div class="shared_comment" id="<%= e_hSharedCommentId %>">
        <div class="left">

            <%
                {
                    Integer ul_b_networkId = share_a_sharedComment.getNetworkId();
                    Integer ul_b_toUserId = share_a_sharedComment.getUserId();
                    AnswerVisibilityEnum ul_b_lowestVisibility = AnswerVisibilityEnum.PROTECTED;
            %>

                <%@ include file="../../user_links/includes/ul_b_face.jsp"%>

            <% } %>

        </div>
        <div class="right">
            <div>
                <div class="box">
                    <div class="arrow_box sm_text dim"><%= HtmlUtils.linkify(share_a_sharedComment.getText()) %></div>

                    <% if (share_a_sharedComment.getUserId().equals(share_a_me.getId())) { %>
                        <a href="#" onclick="SharedComment.hideSharedComment(
                            event,
                            <%= share_a_sharedComment.getNetworkId() %>,
                            <%= share_a_sharedComment.getSmartGroupRef() %>,
                            <%= share_a_sharedComment.getSharedItemRef() %>,
                            <%= share_a_sharedComment.getRef() %>,
                            '<%= e_hSharedCommentId %>')">

                            <div class="delete smd_header dim3"><img src="./img/close_dark.png"></div>

                        </a>
                    <% } %>
                </div>
            </div>
            <div class="details">
                <a href="#" onclick="ND.viewProfile(event, <%= e_author.getId() %>);">
                    <div class="name sm_text highlight2"><%= e_author.getName() %></div>
                </a>

                <% if (share_a_settingSharedCommentDisplayCreatedOn != 0) { %>
                    <div class="ago sm_text dim2"><%= PrettyDate.toString( share_a_sharedComment.getCreatedOn()) %></div>
                <% } %>
            </div>
        </div>
    </div>
</div>
<% } %>