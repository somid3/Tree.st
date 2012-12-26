<%@ include file="../all.jsp" %>
<%@ include file="load.jsp" %>
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
           <a href="/d/logout"><div class="setting sm_text highlight2">Log out</div></a>
        </div>
    </div>
    <div class="menu">
        <div class="shortcuts">

            <a href="#" onclick="UPD.clickItem(event, '#user_panel_shortcut_communities', './user_panel/user_networks/index.jsp');"><div class="shortcut sm_text highlight2" id="user_panel_shortcut_communities">Communities</div></a>
            <a href="#" onclick="UPD.clickItem(event, '#user_panel_shortcut_photos', './user_panel/photos.jsp');"><div class="shortcut sm_text highlight2" id="user_panel_shortcut_photos">Your photos</div></a>
            <a href="#" onclick="UPD.clickItem(event, '#user_panel_shortcut_upload_face', './user_panel/upload_photo.jsp');"><div class="shortcut sm_text highlight2" id="user_panel_shortcut_upload_face">Upload a photo</div></a>
            <a href="#" onclick="UPD.clickItem(event, '#user_panel_shortcut_general', './user_panel/user_general/index.jsp');"><div class="shortcut sm_text highlight2" id="user_panel_shortcut_general">General</div></a>

        </div>
    </div>
</div>

<div id="user_panel_canvas"></div>

<script type="text/javascript">
    UPD.clickItem(null, "#user_panel_shortcut_upload_face", "./user_panel/upload_photo.jsp");
</script>