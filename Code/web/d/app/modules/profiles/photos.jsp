<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    Integer viewUserId = StringUtils.parseInt(request.getParameter("vuid"));

    // Determine if viewing myself, or if a user link is required
    UserLinkServices.viewMyselfOrLinkedUsers(homeId, meId, viewUserId);

    // Retrieving user face photos that are not temporary
    List<AppResource> scaleds = AppResourceDao.getByUserIdAndAppAndTypeAndTemp(null, viewUserId, AppEnum.FACES, AppResourceTypeEnum.FACE_ORIGINAL_SCALED, false);
    AppResource face = null;

    String hPhotoId = null;
%>

<div>

    <div style="" class="canvas_container">

        <% for (AppResource scaled : scaleds) {

            face = AppResourceDao.getByUserIdAndAppAndTypeAndRef(null, viewUserId, AppEnum.FACES, AppResourceTypeEnum.FACE, scaled.getRef());

            hPhotoId = HtmlUtils.getRandomId(); %>

            <div id="<%= hPhotoId%>" style="margin: 20px 0 20px 20px;">

                <div style="position: relative;">

                    <div style="position: absolute; top: -10px; left: -10px; padding: 2px; background-color: white;">
                            <img width="60" height="60" src="<%= face.getUrl()%>">
                    </div>

                    <div  style=""><img src="<%= scaled.getUrl()%>"></div>

                </div>
            </div>

        <% }

        // If user has no photos uploaded
        if (scaleds.isEmpty()) {
            String app_a_message = "No photos available!";
            boolean app_a_withCanvasContainer = false; %>
            <%@ include file="../../includes/app_a_mini_message.jsp" %>
        <% } %>

    </div>

</div>
