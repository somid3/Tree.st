<%@ include file="../../all.jsp" %>
<%@ include file="../load.jsp" %>

<div id="face_upload_container" class="canvas_container">

    <div class="message smd_text dim">
        Everyone can collaborate more effectively when they can recognize
        you. Please upload your most recent photo, the more
        <b>passport-like</b>, the better.
        <span class="smd_header highlight2">Only use .jpg, .png, .gif, and .bmp photo types.</span>
    </div>

    <div id="error" class="error smd_text"></div>

    <form action="/d/app/user_panel/user_photos/actions/upload_photo.jsp" method="post" enctype="multipart/form-data">
    <div class="container">
        <div class="top">
                <input id="file_upload" name="face" type="file" value="">
        </div>
        <div class="bottom">
            <input type="submit" value="Upload button">
        </div>
    </div>
    </form>

</div>

<script type="text/javascript">

    UP = new UserPhotos();
    UP.init();

</script>