<% {
    /* Inputs variables
     *
     *    SharedItem share_d_sharedItem = null;
     *    User share_d_me = null
     *    Integer share_c_fromSmartGroupRef = null;
     *    String share_d_highlight = null;
     *    Map<NetworkAlphaSettingEnum, String> share_c_networkAlphaSettings = null;
     *    Map<NetworkIntegerSettingEnum, Integer> share_c_networkIntegerSettings = null;
     */

    User share_d_author = UserDao.getById(null, share_d_sharedItem.getUserId());
    SmartGroup share_d_smartGroup = null;
    String share_d_hSharedItemId = HtmlUtils.getRandomId();

    Integer share_d_settingSharedItemDisplayCreatedOn = share_d_networkIntegerSettings.get(NetworkIntegerSettingEnum.SHARED_ITEM_DISPLAY_CREATED_ON);

    // Highlighting smart group name
    String share_d_text = share_d_sharedItem.getText();
    if (!StringUtils.isEmail(share_d_highlight))
        share_d_text = StringUtils.highlight(share_d_text, share_d_highlight, "<span class='found'>", "</span>");
%>

<div class="shared_item_line" id="<%= share_d_hSharedItemId %>">

    <div class="left">

        <% {
            Integer ul_b_networkId = share_d_sharedItem.getNetworkId();
            Integer ul_b_toUserId = share_d_sharedItem.getUserId(); %>

            <%@ include file="../../user_links/includes/ul_b_face.jsp"%> <%
        } %>

    </div>
    <div class="right">
        <div>
            <div class="top">

                <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.member(share_d_sharedItem.getNetworkId(), share_d_sharedItem.getUserId(), share_d_user.getId()) %>');">
                    <span class="name sm_header highlight2"><%= share_d_author.getName() %></span>
                </a>

                <% if (share_d_sharedItem.getSmartGroupRef() != share_d_fromSmartGroupRef) {

                    share_d_smartGroup = SmartGroupDao.getByNetworkIdAndRef(null, share_d_sharedItem.getNetworkId(), share_d_sharedItem.getSmartGroupRef()); %>
                    <span class="in sm_text dim2">via</span>

                    <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.smartGroupShare(share_d_sharedItem.getNetworkId(), share_d_sharedItem.getSmartGroupRef()) %>');">
                        <span class="via sm_header highlight6"><%= StringUtils.concat(share_d_smartGroup.getName(), 30, "&hellip;") %></span>
                    </a>

                <% } %>

            </div>
        </div>
        <div class="content">

            <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.sharedItem(share_d_sharedItem.getNetworkId(), share_d_sharedItem.getSmartGroupRef(), share_d_sharedItem.getSharedItemRef()) %>');">

                <div class="box smd_text dim"><%= HtmlUtils.linkify( HtmlUtils.paragraphize( share_d_text ) )%></div>
                <div>

                    <% if (share_d_settingSharedItemDisplayCreatedOn != 0) { %>
                         <span class="ago sm_text dim2"><%= PrettyDate.toString(share_d_sharedItem.getCreatedOn()) %></span>
                    <% } %>

                    <% if (share_d_sharedItem.getTotalComments() > 0) { %>
                         <span class="ago sm_text dim2"><%= share_d_sharedItem.getTotalComments() %> comments</span>
                    <% } %>

                </div>

            </a>

        </div>

    </div>
</div>

<% } %>