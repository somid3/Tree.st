<% {
    /* Inputs variables
     *
     *    SharedItem share_c_sharedItem = null;
     *    User share_c_me = null
     *    Integer share_c_fromSmartGroupRef = null
     *    Map<NetworkAlphaSettingEnum, String> share_c_networkAlphaSettings = null;
     *    Map<NetworkIntegerSettingEnum, Integer> share_c_networkIntegerSettings = null;
     */

    User share_c_author = UserDao.getById(null, share_c_sharedItem.getUserId());
    SmartGroup share_c_smartGroup = null;
    String share_c_hSharedItemId = HtmlUtils.getRandomId();
    String share_c_hNewSharedCommentId = HtmlUtils.getRandomId();
    String share_c_hNewSharedCommentTextId = HtmlUtils.getRandomId();
    String share_c_hSharedCommentsId = HtmlUtils.getRandomId();

    Integer share_c_settingSharedCommentPointsPer = share_c_networkIntegerSettings.get(NetworkIntegerSettingEnum.SHARED_COMMENT_POINTS_PER);
    Integer share_c_settingSharedItemDisplayCreatedOn = share_c_networkIntegerSettings.get(NetworkIntegerSettingEnum.SHARED_ITEM_DISPLAY_CREATED_ON);

    String share_c_addCommentPlaceHolder = "Leave a comment";
    if (share_c_settingSharedCommentPointsPer != 0)
        share_c_addCommentPlaceHolder = share_c_addCommentPlaceHolder + " (" + share_c_settingSharedCommentPointsPer + " pts.)";
%>

<div class="shared_item_box" id="<%= share_c_hSharedItemId %>">

    <div class="left">

            <% {
                Integer ul_b_networkId = share_c_sharedItem.getNetworkId();
                Integer ul_b_toUserId = share_c_sharedItem.getUserId(); %>

                <%@ include file="../../user_links/includes/ul_b_face.jsp"%>

            <% } %>

    </div>
    <div class="right">
        <div>
            <div class="top">
                <a href="#" onclick="ND.viewProfile(event, <%= share_c_author.getId() %>);">
                    <span class="name sm_header highlight2"><%= share_c_author.getName() %></span>
                </a>

                <% if (share_c_sharedItem.getSmartGroupRef() != share_c_fromSmartGroupRef) {

                    share_c_smartGroup = SmartGroupDao.getByNetworkIdAndRef(null, share_c_sharedItem.getNetworkId(), share_c_sharedItem.getSmartGroupRef()); %>
                    <span class="in sm_text dim2">via</span>

                    <a href="#" onclick="ND.go(event, NetworkDashboard.Section.SMART_GROUP, {nid: <%= share_c_sharedItem.getNetworkId() %>, sgr: <%= share_c_sharedItem.getSmartGroupRef() %>})">
                        <span class="via sm_header highlight6"><%= StringUtils.concat(share_c_smartGroup.getName(), 30, "&hellip;") %></span>
                    </a>

                <% } %>

                <% if (share_c_sharedItem.getUserId().equals(share_c_me.getId())) { %>

                    <a href="#" onclick="SI.hideSharedItem(event, '<%= share_c_hSharedItemId %>', <%= share_c_sharedItem.getSmartGroupRef() %>, <%= share_c_sharedItem.getSharedItemRef() %>)">
                        <div class="delete smd_header dim3">
                            <img src="./img/close_dark.png">
                        </div>
                    </a>

                <% } %>

            </div>
        </div>
        <div class="content">
            <div class="box smd_text dim"><%= HtmlUtils.linkify( HtmlUtils.paragraphize( share_c_sharedItem.getText() ) )%></div>

            <div>

                <% if (share_c_settingSharedItemDisplayCreatedOn != 0) { %>
                     <span class="ago sm_text dim2"><%= PrettyDate.toString(share_c_sharedItem.getCreatedOn()) %></span>
                <% } %>

                <% {
                    User share_b_me = share_c_me;
                    SharedVotable share_b_sharedVotable = share_c_sharedItem;
                    Map<NetworkAlphaSettingEnum, String> share_b_networkAlphaSettings = share_c_networkAlphaSettings;
                    Map<NetworkIntegerSettingEnum, Integer> share_b_networkIntegerSettings = share_c_networkIntegerSettings; %>
                <%@ include file="share_b_shared_vote.jsp" %>
                <% } %>
            </div>

        </div>

        <%-- Comments --%>
        <div class="comments" id="<%= share_c_hSharedCommentsId %>">

            <%
                // Retrieving all shared comments of shared item
                List<SharedComment> sharedComments = SharedCommentDao.getByNetworkIdAndSmartGroupRefAndSharedItemRef(null, networkId, share_c_sharedItem.getSmartGroupRef(), share_c_sharedItem.getSharedItemRef(), SqlLimit.ALL);

                User share_a_me = share_c_me;
                Map<NetworkAlphaSettingEnum, String> share_a_networkAlphaSettings = share_c_networkAlphaSettings;
                Map<NetworkIntegerSettingEnum, Integer> share_a_networkIntegerSettings = share_c_networkIntegerSettings;
                for (SharedComment share_a_sharedComment : sharedComments) { %>

                    <%@ include file="share_a_shared_comment.jsp" %>

            <% } %>

        </div>

        <%-- Adding new comment form, begins hidden --%>
        <div class="add_comment" id="<%= share_c_hNewSharedCommentId %>">

            <div class="add_comment_top">
                <div class="error sm_text white"></div>
                <div class="loading"><img src="/d/app/img/sm_loading.gif"></div>
            </div>

            <div class="left">
                <div class="face">
                    <img src="<%= share_c_me.getFaceUrl() %>" alt="">
                </div>
            </div>

            <div class="right">
                <div class="box">
                    <textarea
                        onclick="$(this).TextAreaExpander(40, 500);"
                        onkeydown="SharedComment.addSharedComment(
                                    event,
                                    <%= share_c_sharedItem.getNetworkId() %>,
                                    <%= share_c_sharedItem.getSmartGroupRef() %>,
                                    <%= share_c_sharedItem.getSharedItemRef() %>,
                                    '<%= share_c_hNewSharedCommentId %>',
                                    '<%= share_c_hSharedCommentsId %>')"
                        class="tell_me"
                        id="<%= share_c_hNewSharedCommentTextId %>"
                        placeholder="<%= share_c_addCommentPlaceHolder %>"></textarea>
                </div>
            </div>

        </div>

    </div>
</div>

<% } %>