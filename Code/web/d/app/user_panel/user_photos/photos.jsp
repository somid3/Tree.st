<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%@ include file="../load.jsp" %>
<%

    // Retrieving user face photos that are not temporary
    List<AppResource> scaleds = AppResourceDao.getByUserIdAndAppAndTypeAndTemp(null, meId, AppEnum.FACES, AppResourceTypeEnum.FACE_ORIGINAL_SCALED, false);
    AppResource face = null;


    String hPhotoId = null;
%>


<div class="user_setting_container">

    <div class="user_photos">

        <% for (AppResource scaled : scaleds) {

            face = AppResourceDao.getByUserIdAndAppAndTypeAndRef(null, meId, AppEnum.FACES, AppResourceTypeEnum.FACE, scaled.getRef());

            hPhotoId = HtmlUtils.getRandomId(); %>

            <div id="<%= hPhotoId%>" class="user_photo">

                <div class="face_summary">

                    <div class="face"><img width="60" height="60" src="<%= face.getUrl()%>"></div>

                    <div class="face_shortcuts">

                        <a href="#" onclick='UserPhotos.go(event, UserPhotos.Section.SET_FACE, {rr: <%= scaled.getRef() %>, cs: "<%= scaled.getChecksum() %>"});'>
                            <div class="face_shortcut light_button sm_text">Edit face</div>
                        </a>

                        <a href="#" onclick='UserPhotos.setProfile(event, <%= face.getRef() %>, "<%= face.getChecksum()%>");'>
                            <div class="face_shortcut light_button sm_text">Set profile</div>
                        </a>

                        <a href="#" onclick='UserPhotos.hidePhoto(event, "<%= hPhotoId %>", <%= face.getRef() %>, "<%= face.getChecksum()%>");'>
                            <div class="face_shortcut light_button sm_text">Remove</div>
                        </a>

                    </div>

                    <div class="sm_text dim2">Uploaded <%= PrettyDate.toString(scaled.getCreatedOn()).toLowerCase() %></div>

                </div>

                <div class="photo"><img src="<%= scaled.getUrl()%>"></div>

            </div>

        <% }

        // If user has no photos uploaded
        if (scaleds.isEmpty()) {
            String app_a_message = "You haven't uploaded any photos yet!";
            boolean app_a_withCanvasContainer = false; %>
            <%@ include file="../../includes/app_a_mini_message.jsp" %>
        <% } %>

    </div>

</div>
