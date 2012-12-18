<%@ include file="../../all.jsp" %>
<%@ include file="./load.jsp" %>
<%

    // Retrieving user face photos that are not temporary
    List<AppResource> scaleds = AppResourceDao.getByUserIdAndAppAndTypeAndTemp(null, userId, AppEnum.FACES, AppResourceTypeEnum.FACE_ORIGINAL_SCALED, false);
    AppResource face = null;


    String hPhotoId = null;
%>


<div class="canvas_container">

    <% for (AppResource scaled : scaleds) {

        face = AppResourceDao.getByUserIdAndAppAndTypeAndRef(null, userId, AppEnum.FACES, AppResourceTypeEnum.FACE, scaled.getRef());

        hPhotoId = HtmlUtils.getRandomId(); %>

        <div id="<%= hPhotoId%>" style="margin-bottom: 10px; padding: 5px;">

            <div class="tools" style="float: right;">

                <div style="
                    margin-bottom: 5px;
                ">

                    <img width="60" height="60" src="<%= face.getUrl()%>">

                </div>

                <a href="#" onclick='UPD.clickItem(event, null, "./modules/user_panel/set_face.jsp", {r: <%= scaled.getRef() %>, cs: "<%= scaled.getChecksum() %>"});'><div class="sm_text dim" style="margin: 5px 0">Edit face</div></a>
                <a href="#" onclick='UPD.setProfile(event, <%= face.getRef() %>, "<%= face.getChecksum()%>");'><div class="sm_text dim">Set profile</div></a>
            </div>

            <div class="photo" style="
                border-bottom: 1px dashed #bbb;
                ">

                <img src="<%= scaled.getUrl()%>">

                <div style="
                    display: inline-block;
                    padding: 5px;
                ">

                    <span class="sm_text dim">Uploaded <%= PrettyDate.toString(scaled.getCreatedOn()).toLowerCase() %></span>
                    <a href="#" onclick='UPD.deletePhoto(event, "<%= hPhotoId %>", <%= face.getRef() %>, "<%= face.getChecksum()%>");'>
                        <span class="sm_text dim2" style="margin-left: 30px;">Remove</span>
                    </a>
                </div>


            </div>


        </div>

    <% } %>


    <%
    // If user has no photos uploaded
    if (scaleds.isEmpty()) {
        String app_a_message = "You haven't uploaded any photos yet!";
        boolean app_a_withCanvasContainer = false; %>
        <%@ include file="../../includes/app_a_mini_message.jsp" %>
    <% } %>


</div>
