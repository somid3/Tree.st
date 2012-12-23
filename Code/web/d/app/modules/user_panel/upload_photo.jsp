<%@ include file="../../all.jsp" %>
<%@ include file="./load.jsp" %>

<div id="face_upload_container" class="canvas_container">

    <div class="message smd_text dim">
        Everyone can collaborate more effectively when they can recognize
        you. Please upload your most recent photo, the more
        <b>passport-like</b>, the better.
        <span class="smd_header highlight2">Only use .jpg, .png, .gif, and .bmp photo types.</span>
    </div>

    <div id="error" class="error smd_text"></div>

    <div class="container">

        <div class="top">

            <a href="#">
                <div id="upload_button">
                    <input id="file_upload" name="face" type="file" value="">
                </div>
            </a>

        </div>

    </div>
</div>

<script type="text/javascript">

    UP = new PhotoUpload();
    UP.init();

    $(function() {
        $('#file_upload').uploadifive({

            'uploadScript' : './modules/user_panel/actions/upload_photo.jsp',

            // Put your options here
            'width'           : 200,
            'buttonText'      : 'Upload a photo',
            'buttonClass'     : 'submit_button',
            'auto'            : 'true',
            'multi'           : 'false',
            'fileSizeLimit'   : '20 MB',
            'onAddQueueItem'  : UP.onAddQueueItem,
            'onQueueComplete' : UP.queueComplete,
            'onError'         : UP.onError
        });
    });


</script>