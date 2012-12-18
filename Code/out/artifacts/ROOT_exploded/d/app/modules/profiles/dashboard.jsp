<%@ include file="../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer viewUserId = StringUtils.parseInt(request.getParameter("vuid"));
    Integer go = StringUtils.parseInt(request.getParameter("go"));

    // Should the dashboard require a user link?
    boolean requireUserLink = false;
    boolean viewMyself = false;
    try {
        viewMyself = UserLinkServices.viewMyselfOrValidateUsersLinked(networkId, userId, viewUserId);
    } catch (RuntimeException e) {
        requireUserLink = true;
    }

    // Retrieving user
    User viewed = UserDao.getById(null, viewUserId);

    // Retrieving network
    Network network = NetworkDao.getById(null, networkId);

    // Retrieving user to network
    UserToNetwork userToNetwork = UserToNetworkDao.getByUserIdAndNetworkId(null, viewUserId, networkId);
%>
<script type="text/javascript">
    PD = new ProfileDashboard();
    PD.networkId = <%= networkId %>;
    PD.viewUserId = <%= viewUserId %>;
</script>
<div id="profile_dashboard">

    <div class="left">
        <div class="thumbnail"><img src="<%= viewed.getFaceUrl() %>" alt=""></div>
    </div>

    <div class="right">

        <div class="top">

            <% if (!viewMyself) { %>
                <div class="points smd_text white"><%= userToNetwork.getCurrentPoints() %> pts.</div>
            <% } %>

            <div class="title lg_header"><%= viewed.getName() %></div>

            <% if (!requireUserLink) { %>
                <a href="mailto:<%= viewed.getEmail() %>" target="_new">
                    <div class="comment md_text highlight2"><%= viewed.getEmail() %></div>
                </a>
            <% } %>

        </div>


        <% if (!requireUserLink) { %>
        <div class="shortcuts">

            <a href="#" onclick="PD.go(event, ProfileDashboard.Section.QUESTIONS);">
                <div class="shortcut" id="profile_shortcut_answers">
                    <div class="text sm_text dim">Profile</div>
                </div>
            </a>

            <% if (!network.isGlobal()) {

                Integer countViewedUsers = UserLinkDao.countByNetworkIdAndFromUserIdAndNotDirection(null, networkId, viewed.getId(), UserLinkDirectionEnum.TARGET_TO_ME);
                Integer countSharedItems = SharedItemDao.countByNetworkIdAndUserId(null, networkId, viewed.getId());


            %>

                <a href="#" onclick="PD.go(event, ProfileDashboard.Section.VIEWED_USERS);">
                    <div class="shortcut" id="profile_shortcut_user_links">
                        <div class="text sm_text dim">Viewed</div>
                        <div class="count vsm_text white"><%= countViewedUsers %></div>
                    </div>
                </a>

                <a href="#" onclick="PD.go(event, ProfileDashboard.Section.SMART_GROUPS);">
                    <div class="shortcut" id="profile_shortcut_smart_groups">
                        <div class="text sm_text highlight6">Smart Groups</div>
                    </div>
                </a>

                <a href="#" onclick="PD.go(event, ProfileDashboard.Section.SHARED);">
                    <div class="shortcut" id="profile_shortcut_shared">
                        <div class="text sm_text dim">Shared</div>
                        <div class="count vsm_text white"><%= countSharedItems %></div>
                    </div>
                </a>

            <% } %>
        </div>
        <% } %>
    </div>
</div>

<div id="profile_canvas"></div>

<%
    String app_c_hCanvasLoadingId = "profile_loading";
    boolean app_c_withCanvasContainer = true;
%>
<%@ include file="../../includes/app_c_canvas_loading.jsp" %>

<script type="text/javascript">
    <% if (requireUserLink) { %>
        PD.go(null, ProfileDashboard.Section.USER_LINK_NEEDED);
    <% } else { %>
        PD.go(null, <%= go %>);
    <% } %>
</script>