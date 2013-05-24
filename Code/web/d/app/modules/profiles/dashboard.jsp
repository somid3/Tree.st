<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer viewUserId = StringUtils.parseInt(request.getParameter("vuid"));
    Integer go = StringUtils.parseInt(request.getParameter("go"));

    // Should the dashboard require a user link?
    boolean requireUserLink = false;
    boolean viewMyself = false;
    try {
        viewMyself = UserLinkServices.viewMyselfOrValidateUsersLinked(networkId, meId, viewUserId);
    } catch (RuntimeException e) {
        requireUserLink = true;
    }

    // Retrieving user
    User viewed = UserDao.getById(null, viewUserId);

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

        <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.settingsPhotoUpload() %>');">

            <div class="thumbnail"><img src="<%= viewed.getFaceUrl() %>" alt=""></div>

            <% if (viewMyself && viewed.getFaceOn() == null) { %>
                <div id="123" class="upload md_text lg_button error_button">Upload photo</div>
                <script type="text/javascript">
                    Animations.shake("#123", function() {
                        Animations.bounce("#123");
                    });
                </script>
            <% } %>

        </a>
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


        <% if (!requireUserLink) {

            Integer countViewedUsers = UserLinkDao.countByNetworkIdAndFromUserIdAndNotDirection(null, networkId, viewed.getId(), UserLinkDirectionEnum.TARGET_TO_ME);
            Integer countSharedItems = SharedItemDao.countByNetworkIdAndUserId(null, networkId, viewed.getId()); %>
            <div class="shortcuts">

                <a href="#" onclick="PD.go(event, ProfileDashboard.Section.QUESTIONS);">
                    <div class="shortcut" id="profile_shortcut_answers">
                        <div class="text sm_text highlight2">Profile</div>
                    </div>
                </a>

                    <a href="#" onclick="PD.go(event, ProfileDashboard.Section.VIEWED_USERS);">
                        <div class="shortcut" id="profile_shortcut_user_links">
                            <div class="text sm_text highlight2">Viewed</div>
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
                            <div class="text sm_text highlight2">Shared</div>
                            <div class="count vsm_text white"><%= countSharedItems %></div>
                        </div>
                    </a>

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