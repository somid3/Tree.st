<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    AllMembersViewEnum sortBy = AllMembersViewEnum.getById(StringUtils.parseInt(request.getParameter("sort")));
    Integer from = StringUtils.parseInt(request.getParameter("from"));

    // Retrieving members
    List<UserToNetwork> results = UserToNetworkDao.getByNetworkIdAndLowestRoleOrderedBy(null, homeId, RoleEnum.MEMBER, sortBy, new SqlLimit(from, 50));
    Integer count = results.size();
%>

<% if (count > 0) {

    Integer ul_a_toUserId = null;
    Integer ul_a_networkUserLinkPointsPer = NetworkIntegerSettingEnum.USER_LINK_POINTS_PER.getValueByNetworkId(homeId);
    for (UserToNetwork result : results) {

        ul_a_toUserId = result.getUserId(); %>

        <div class="all_members_result">

            <div class="result_card">
                <%@ include file="../user_links/includes/ul_a_card.jsp"%>
            </div>
        </div>
    <% } %>

<% } else { %>

    <script>Pagination.unbindScrollPagination();</script>

<% } %>


