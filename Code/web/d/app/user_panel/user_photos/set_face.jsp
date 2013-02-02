<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%@ include file="../load.jsp" %>
<%
    Integer faceRef = StringUtils.parseInt(request.getParameter("rr"));
    String checksum = StringUtils.parseString(request.getParameter("cs"));

    // Get user photo whose face will be set
    AppResource scaled = null;
    if (faceRef == null) {

        // If no face reference is provided, get the latest non-hidden temporary face
        List<AppResource> faces = AppResourceDao.getByUserIdAndAppAndTypeAndTemp(null, meId, AppEnum.FACES, AppResourceTypeEnum.FACE_ORIGINAL_SCALED, true);

        // If there are no temp images send user to app without any "go_" parameters
        if (faces.isEmpty())
            throw new UIException("User has no temporary faces");

        // Getting the latest temporary scaled face
        scaled = faces.get(0);

    } else {

        // Retrieve the scaled image of the resource whose face needs to be set
        scaled = AppResourceDao.getByUserIdAndAppAndTypeAndRefAndChecksum(null, meId, AppEnum.FACES, AppResourceTypeEnum.FACE_ORIGINAL_SCALED, faceRef, checksum);
    }

    String hScaledImageId = HtmlUtils.getRandomId();
    String scaledFaceUrl = scaled.getUrl();
%>

<script type="text/javascript">
    UPH = new UserPhotos();
    UPH.resourceRef = <%= scaled.getRef() %>;
    UPH.resourceChecksum = '<%= scaled.getChecksum() %>';
    UPH.hScaledImageId = '<%= hScaledImageId %>';
</script>

<div class="user_setting_container">

    <div id="face_upload_container">

        <div class="message smd_text dim">
            Use your mouse to select the area where your face is located, once you have selected
            the area containing your face &mdash; and the previews look all right, click on "Set face."
        </div>

        <div id="error" class="error smd_text"></div>

        <div class="container">

            <div class="top">

                <div style="float: left">

                    <div style="margin-bottom: 5px;" class="sm_text dim2">Face previews</div>

                    <div class="thumbs">
                        <div>
                            <div class="thumb" style="width:60px;height:60px;">
                                <img id="temp_x60" class="x60" src="<%= scaledFaceUrl %>" alt="">
                            </div>

                            <div class="thumb" style="width:40px;height:40px;">
                                <img id="temp_x40" class="x40" src="<%= scaledFaceUrl %>" alt="">
                            </div>

                            <div class="thumb" style="width:20px;height:20px;">
                                <img id="temp_x20" class="x20" src="<%= scaledFaceUrl %>" alt="">
                            </div>
                        </div>
                    </div>

                </div>

                <div style="float: right">
                    <a href="#" onclick="UPH.submitSetFace(event)"><div id="set_button" class="lg_button submit_button">Set face</div></a>
                </div>

            </div>

            <div class="bottom">

                <div class="temp_scaled"><img id="<%= hScaledImageId %>" src="<%= scaledFaceUrl %>" alt=""></div>

            </div>
        </div>
    </div>

    <script type="text/javascript">
        UPH.initSetFace();
    </script>

</div>