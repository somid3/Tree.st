<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    AllMembersViewEnum sortBy = AllMembersViewEnum.getById(StringUtils.parseInt(request.getParameter("sort")));
    Integer from = StringUtils.parseInt(request.getParameter("from"));

    // Retrieving members
    List<UserToNetwork> results = results = UserToNetworkDao.getByNetworkIdAndLowestRoleOrderedBy(null, homeId, RoleEnum.MEMBER, sortBy, new SqlLimit(from, 50));
    Integer count = results.size();
%>

<% if (count > 0) {

    Integer ul_a_toUserId = null;
    Integer ul_a_networkId = null;
    UserToNetwork ul_a_meToHome = null;
    for (UserToNetwork result : results) {

        ul_a_toUserId = result.getUserId();
        ul_a_networkId = result.getNetworkId();
        ul_a_meToHome = meToHome; %>

        <div class="all_members_result">

            <div class="result_card">
                <%@ include file="../user_links/includes/ul_a_card.jsp"%>
            </div>
        </div>
    <% } %>

<% } else { %>

    <script>Pagination.unbindScrollPagination();</script>

<% } %>


