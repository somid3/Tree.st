<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>

<form action="./user_panel/user_photos/actions/upload.jsp" method="post" enctype="multipart/form-data">

    <div id="face_upload_container" class="user_setting_container">

        <div class="message smd_text dim">
            Everyone can collaborate more effectively when they can recognize
            you. Please upload your most recent photo, the more
            <b>passport-like</b>, the better.
            <span class="smd_header highlight2">Only use .jpg, .png, .gif, and .bmp photo types.</span>
        </div>

        <div id="error" class="error smd_text"></div>

        <div class="container">

            <div class="top">
                    <input id="file_upload" name="face" type="file" value="">
            </div>

            <div class="bottom">
                <input type="submit" value="Upload photo">
            </div>

        </div>

    </div>
</form>

<script type="text/javascript">

    UPH = new UserPhotos();
    UPH.initSetFace();

</script>