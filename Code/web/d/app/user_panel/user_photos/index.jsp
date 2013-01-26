<%@ include file="../../all.jsp" %>
<%@ include file="../load.jsp" %>

<div class="user_setting canvas_container">

    <div id="user_photos_dashboard" class="shortcuts">
        <a href="#" onclick="UserPhotos.go(event, UserPhotos.Section.UPLOAD)"><div id="user_photos_upload" class="shortcut sm_text light_button">Upload Photo</div></a>
        <a href="#" onclick="UserPhotos.go(event, UserPhotos.Section.VIEW_ALL)"><div id="user_photos_view" class="shortcut sm_text light_button">View Photos</div></a>
    </div>

    <div id="user_photos_canvas"></div>

</div>

