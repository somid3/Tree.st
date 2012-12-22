<%@ include file="../../all.jsp" %>
<%@ include file="./load.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));

    // Retrieving top users
    List<UserToNetwork> results = UserToNetworkDao.getByNetworkIdAndLowestRoleOrderedByPoints(null, networkId, RoleEnum.MEMBER, new SqlLimit(0, 50));

    // Retrieve award description
    String awardDescription = NetworkAlphaSettingEnum.AWARD_DESCRIPTION.getValueByNetwork(networkId);
%>

<div id="top_canvas">

    <div class="description canvas_container">
        <div class="title md_header dim">Top 50 <%= NetworkAlphaSettingEnum.VOCAB_USER_PLURAL.getValueByNetwork(networkId) %> by points</div>

        <div class="sm_text dim2">Gain points by viewing others, sharing thoughts, commenting, etc.</div>

        <% if (!awardDescription.isEmpty()) { %>
            <div class="award sm_header white"><%= awardDescription %></div>
        <% } %>

    </div>

</div>

<div id="top_results">

    <%
        Integer ul_a_toUserId = null;
        Integer ul_a_networkId = null;
        for (UserToNetwork result : results) {

            ul_a_toUserId = result.getUserId();
            ul_a_networkId = result.getNetworkId(); %>

            <div class="top_result">

                <div class="result_card">
                    <%@ include file="../user_links/includes/ul_a_card.jsp"%>
                </div>
            </div>

    <% } %>


</div>




