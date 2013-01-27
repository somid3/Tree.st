<% {
    /**
     * Inputs variables
     *
     *   Integer sgroup_d_userId = null;
     *   SmartGroup sgroup_d_smartGroup = null;
     *   String sgroup_d_highlight = null;
     */
    Integer sgroup_d_facesLimitPerGroup = 5;
    Integer sgroup_d_multiplierToFindFaces = 5;
    Integer sgroup_d_more = null;
    SmartGroupResult sgroup_d_myResult = null;
    String sgroup_d_name = null;

    // Setting up variables for include
    Integer sgroup_e_networkId = sgroup_d_smartGroup.getNetworkId();
    Integer sgroup_e_userId = sgroup_d_userId;
    Integer sgroup_e_smartGroupRef = sgroup_d_smartGroup.getSmartGroupRef();

    /*
     * Retrieving smart group results, here we retrieve times more than necessary results in hopes to
     * capture more results with defined faces
     */
    List<SmartGroupResult> sgroup_d_results = SmartGroupResultDao.getByNetworkIdAndRef(null, sgroup_d_smartGroup.getNetworkId(), sgroup_d_smartGroup.getSmartGroupRef(), new SqlLimit(0, sgroup_d_facesLimitPerGroup * sgroup_d_multiplierToFindFaces));

    // Retrieving result users
    List<User> sgroup_d_resultUsers = new ArrayList<User>();
    for (SmartGroupResult sgroup_d_result : sgroup_d_results) {

        User k_resultUser = UserDao.getById(null, sgroup_d_result.getUserId());
        sgroup_d_resultUsers.add(k_resultUser);

    }

    // Sorting result users by whether or not they have a defined face
    Collections.sort(sgroup_d_resultUsers, new UserComparatorFaceOn());

    // Limit the number of users to the face limit per group
    sgroup_d_resultUsers = ArrayUtils.subList(sgroup_d_resultUsers, 0, sgroup_d_facesLimitPerGroup);

    // Modifying smart group name
    sgroup_d_name = StringUtils.concat(sgroup_d_smartGroup.getName(), 30, "&hellip;");

    // Highlighting smart group name
    if (!StringUtils.isEmail(sgroup_d_highlight))
        sgroup_d_name = StringUtils.highlight(sgroup_d_name, sgroup_d_highlight, "<span class='found'>", "</span>");

%>
    <div class="smart_group_line">

        <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.smartGroupShare(sgroup_d_smartGroup.getNetworkId(), sgroup_d_smartGroup.getSmartGroupRef()) %>');">

            <div class="name smd_header highlight6"><%= sgroup_d_name %></div>
                <div class="minifaces">

                    <% for (User sgroup_d_resultUser : sgroup_d_resultUsers) { %>

                        <div class="miniface"><img alt="" src="<%= sgroup_d_resultUser.getFaceUrl() %>"/></div>

                    <% }

                    // Counting how many more results exist in smart group other than displayed minifaces
                    sgroup_d_more = sgroup_d_smartGroup.getResultsCount() - sgroup_d_facesLimitPerGroup;

                    if (sgroup_d_more > 0) { %>
                        <div class="more sm_text dim"><%= sgroup_d_more %> more</div>
                    <% } %>

                </div>

        </a>

        <%@ include file="sgroup_e_apply_state.jsp"%>

    </div>

<% } %>