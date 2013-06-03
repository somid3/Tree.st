<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer viewUserId = StringUtils.parseInt(request.getParameter("vuid"));
    Integer startFrom = StringUtils.parseInt(request.getParameter("from"));

    // Determine if viewing myself, or if a user link is required
    UserLinkServices.viewMyselfOrValidateUsersLinked(networkId, meId, viewUserId);

    Integer duration = 20;
    SqlLimit limit = new SqlLimit(startFrom, duration);

    // Retrieving user links
    List<UserLink> viewedUserLinks = UserLinkDao.getByNetworkIdAndFromUserIdAndNotDirection(null, networkId, viewUserId, UserLinkDirectionEnum.TARGET_TO_ME, limit);
    Integer count = viewedUserLinks.size();

    if (count > 0) {

        Integer ul_a_toUserId = null;
        Integer ul_a_networkUserLinkPointsPer = NetworkIntegerSettingEnum.USER_LINK_POINTS_PER.getValueByNetworkId(homeId);
        for (UserLink viewedUserLink : viewedUserLinks) {

            ul_a_toUserId = viewedUserLink.getToUserId(); %>

            <div class="user_link">
                <%@ include file="../user_links/includes/ul_a_card.jsp" %>
            </div>

        <% } %>

    <% } else { %>

        <script>Pagination.unbindScrollPagination();</script>

    <% } %>
