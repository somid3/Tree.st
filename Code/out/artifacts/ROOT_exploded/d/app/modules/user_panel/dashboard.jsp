<%@ include file="../../all.jsp" %>
<%@ include file="./load.jsp" %>
<%
    User me = UserDao.getById(null, userId);
%>
<script type="text/javascript">
    UPD = new UserPanelDashboard();
</script>
<div id="user_panel_dashboard">
    <div class="details">
        <div id="message">
            <div class="thumbnail"><img src="<%= me.getFaceUrl() %>" alt=""></div>
            <div class="title lg_header"><%= me.getName() %></div>
            <div class="comment smd_text dim"><%= me.getEmail() %></div>
        </div>
        <div class="settings">
           <a href="/d/logout"><div class="setting sm_text dim">Log out</div></a>
        </div>
    </div>
    <div class="menu">
        <div class="shortcuts">

            <a href="#" onclick="UPD.clickItem(event, '#user_panel_shortcut_photos', './modules/user_panel/photos.jsp');"><div class="shortcut sm_text dim" id="user_panel_shortcut_photos">Your photos</div></a>
            <a href="#" onclick="UPD.clickItem(event, '#user_panel_shortcut_upload_face', './modules/user_panel/upload_photo.jsp');"><div class="shortcut sm_text dim" id="user_panel_shortcut_upload_face">Upload a photo</div></a>

        </div>
    </div>
</div>

<div id="user_canvas"></div>

<script type="text/javascript">
    UPD.clickItem(null, "#user_panel_shortcut_upload_face", "./modules/user_panel/upload_photo.jsp");
</script>