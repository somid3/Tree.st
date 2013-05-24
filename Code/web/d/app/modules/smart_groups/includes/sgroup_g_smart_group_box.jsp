<% {
    /**
     * Inputs variables
     *
     *   Integer sgroup_g_userId = null;
     *   SmartGroup sgroup_g_smartGroup = null;
     */

    Integer sgroup_g_facesLimitPerGroup = 5;
    Integer sgroup_g_multiplierToFindFaces = 5;
    Integer sgroup_g_more = null;
    SmartGroupResult sgroup_g_myResult = null;

    // Setting up variables for include
    Integer sgroup_e_networkId = sgroup_g_smartGroup.getNetworkId();
    Integer sgroup_e_userId = sgroup_g_userId;
    Integer sgroup_e_smartGroupRef = sgroup_g_smartGroup.getSmartGroupRef();

    // Setting visibility class to define color
    String sgroup_g_visibilityText = null;
    switch(sgroup_g_smartGroup.getVisibility()) {
        case SHARED: sgroup_g_visibilityText = "Shared"; break;
        case OFFICIAL: sgroup_g_visibilityText = "Official"; break;
    }

    /*
     * Retrieving smart group results, here we retrieve times more than necessary results in hopes to
     * capture more results with defined faces
     */
    List<SmartGroupResult> sgroup_g_results = SmartGroupResultDao.getByNetworkIdAndRef(null, sgroup_g_smartGroup.getNetworkId(), sgroup_g_smartGroup.getSmartGroupRef(), new SqlLimit(0, sgroup_g_facesLimitPerGroup * sgroup_g_multiplierToFindFaces));

    // Retrieving result users
    List<User> sgroup_g_resultUsers = new ArrayList<User>();
    for (SmartGroupResult k_result : sgroup_g_results) {

        User k_resultUser = UserDao.getById(null, k_result.getUserId());
        sgroup_g_resultUsers.add(k_resultUser);

    }

    // Sorting result users by whether or not they have a defined face
    Collections.sort(sgroup_g_resultUsers, new UserComparatorFaceOn());

    // Limit the number of users to the face limit per group
    sgroup_g_resultUsers = ArrayUtils.subList(sgroup_g_resultUsers, 0, sgroup_g_facesLimitPerGroup);
%>
<div class="smart_group_box">

    <div class="header">
        <div class="visibility">
            <div class="sm_text dim2"><%= sgroup_g_visibilityText %></div>
        </div>

        <%@ include file="sgroup_e_apply_state.jsp"%>
    </div>

    <a href="#" class="no_deco" onclick="HashRouting.setHash(event, '<%= HashRouting.smartGroupShare(sgroup_g_smartGroup.getNetworkId(), sgroup_g_smartGroup.getSmartGroupRef()) %>');">

        <div class="details">
            <div class="name smd_header highlight6"><%= StringUtils.concat(sgroup_g_smartGroup.getName(), 50, "&#8230;") %></div>
            <div class="description sm_text dim"><%= StringUtils.concat(sgroup_g_smartGroup.getDescription(), 150, "&#8230;") %></div>
        </div>
        <div class="minifaces">
            <% for (User sgroup_g_resultUser : sgroup_g_resultUsers) { %>

                <div class="miniface"><img alt="" src="<%= sgroup_g_resultUser.getFaceUrl() %>"/></div>

            <% } %>

            <%
            // Counting how many more results exist in smart group other than displayed minifaces
            sgroup_g_more = sgroup_g_smartGroup.getResultsCount() - sgroup_g_facesLimitPerGroup;
            if (sgroup_g_more > 0) { %>
                <div class="more sm_text dim"><%= sgroup_g_more %> more</div>
            <% } %>
        </div>

    </a>
</div>

<% } %>