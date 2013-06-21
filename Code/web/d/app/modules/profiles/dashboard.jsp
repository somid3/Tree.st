<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    Integer viewUserId = StringUtils.parseInt(request.getParameter("vuid"));
    Integer go = StringUtils.parseInt(request.getParameter("go"));

    // Have these two users seen each other, or is the user viewing itself?
    boolean requireUserLink = false;
    boolean viewMyself = false;
    try {
        viewMyself = UserLinkServices.viewMyselfOrValidateUsersLinked(homeId, meId, viewUserId);
    } catch (RuntimeException e) {
        requireUserLink = true;
    }

    // Retrieving user
    User viewed = UserDao.getById(null, viewUserId);

    // Retrieving user to network
    UserToNetwork userToNetwork = UserToNetworkDao.getByUserIdAndNetworkId(null, viewUserId, homeId);

    // Determining whether we should display user email addresses
    Integer displayEmails = NetworkIntegerSettingEnum.NETWORK_DISPLAY_TEXT_EMAILS.getValueByNetworkId(homeId);

    // Retrieving network settings
    Integer pointsPer = NetworkIntegerSettingEnum.USER_MESSAGE_POINTS_PER.getValueByNetworkId(homeId);
%>
<script type="text/javascript">
    PD = new ProfileDashboard();
    PD.networkId = <%= homeId %>;
    PD.viewUserId = <%= viewUserId %>;
</script>
<div id="profile_dashboard">

    <div class="left">

        <div class="thumbnail sm_shadow"><img src="<%= viewed.getFaceUrl() %>" alt=""></div>

        <% if (viewMyself && !viewed.hasFace()) {
            String hUploadPhotoId = HtmlUtils.getRandomId(); %>
            <div id="<%= hUploadPhotoId %>" class="upload md_text lg_button error_button sm_glow7">Upload photo</div>
        <% } %>

        <% if (!viewMyself) { %>
            <div class="points">
                <div class="sm_button vsm_text w50 center sm_shadow" style="background-color: white">
                    <%= userToNetwork.getCurrentPoints() %> pts.
                </div>
            </div>
        <% } %>

    </div>

    <div class="right">

        <div class="top">

            <div class="title lg_header"><%= viewed.getName() %></div>

            <% if (!requireUserLink) { %>

                <% if (displayEmails == 1) { %>

                    <a href="mailto:<%= viewed.getEmail() %>" target="_new">
                        <div class="comment md_text highlight2"><%= viewed.getEmail() %></div>
                    </a>

                <% } else { %>

                    <a href="#" onclick="PD.go(event, ProfileDashboard.Section.MESSAGE);">
                        <div id="profile_message" class="clickable md_button dark_button md_text selected">
                            Message <%= viewed.getFirstName() %>

                            <% if (pointsPer != 0) { %>
                                <span class="sm_text">(<%= pointsPer %> pts.)</span>
                            <% } %>
                        </div>
                    </a>

                <% } %>

            <% } %>

        </div>


        <% if (!requireUserLink) {

            Integer countViewedUsers = UserLinkDao.countByNetworkIdAndFromUserIdAndNotDirection(null, homeId, viewed.getId(), UserLinkDirectionEnum.TARGET_TO_ME);
            Integer countSharedItems = SharedItemDao.countByNetworkIdAndUserId(null, homeId, viewed.getId());
            Integer countPhotos = AppResourceDao.countByUserIdAndAppAndType(null, viewed.getId(), AppEnum.FACES, AppResourceTypeEnum.FACE_ORIGINAL_SCALED); %>

            <div class="shortcuts">

                <a href="#" onclick="PD.go(event, ProfileDashboard.Section.QUESTIONS);">
                    <div class="clickable shortcut" id="profile_shortcut_answers">
                        <div class="text sm_text highlight2">Details</div>
                    </div>
                </a>

                <a href="#" onclick="PD.go(event, ProfileDashboard.Section.PHOTOS);">
                    <div class="clickable shortcut" id="profile_shortcut_photos">
                        <div class="text sm_text highlight2">Photos</div>
                        <div class="count vsm_text white"><%= countPhotos %></div>
                    </div>
                </a>

                <a href="#" onclick="PD.go(event, ProfileDashboard.Section.VIEWED_USERS);">
                    <div class="clickable shortcut" id="profile_shortcut_user_links">
                        <div class="text sm_text highlight2">Viewed</div>
                        <div class="count vsm_text white"><%= countViewedUsers %></div>
                    </div>
                </a>

                <a href="#" onclick="PD.go(event, ProfileDashboard.Section.SMART_GROUPS);">
                    <div class="clickable shortcut" id="profile_shortcut_smart_groups">
                        <div class="text sm_text highlight6">Smart Groups</div>
                    </div>
                </a>

                <a href="#" onclick="PD.go(event, ProfileDashboard.Section.SHARED);">
                    <div class="clickable shortcut" id="profile_shortcut_shared">
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