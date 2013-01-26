<%@ include file="../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    AllMembersViewEnum sortBy = AllMembersViewEnum.getById(StringUtils.parseInt(request.getParameter("sort")));
    Integer from = StringUtils.parseInt(request.getParameter("from"));

    // Retrieving members
    List<UserToNetwork> results = results = UserToNetworkDao.getByNetworkIdAndLowestRoleOrderedByPoints(null, networkId, RoleEnum.MEMBER, sortBy, new SqlLimit(from, 50));
    Integer count = results.size();
%>

<% if (count > 0) {

    Integer ul_a_toUserId = null;
    Integer ul_a_networkId = null;
    for (UserToNetwork result : results) {

        ul_a_toUserId = result.getUserId();
        ul_a_networkId = result.getNetworkId(); %>

        <div class="all_members_result">

            <div class="result_card">
                <%@ include file="../user_links/includes/ul_a_card.jsp"%>
            </div>
        </div>
    <% } %>

<% } else { %>

    <script>Pagination.unbindScrollPagination();</script>

<% } %>


