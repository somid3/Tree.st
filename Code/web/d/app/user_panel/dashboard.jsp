<%@ include file="../all.jsp" %>
<%@ include file="load.jsp" %>
<%
    User me = UserDao.getById(null, userId);
%>
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

            <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.settingsNetworks() %>');"><div class="shortcut sm_text light_button" id="user_panel_shortcut_networks">Communities</div></a>
            <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.settingsPhotoUpload() %>');"><div class="shortcut sm_text light_button" id="user_panel_shortcut_photos">Photos</div></a>
            <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.settingsGeneral() %>');"><div class="shortcut sm_text light_button" id="user_panel_shortcut_general">General</div></a>

        </div>
    </div>
</div>

<div id="user_panel_canvas"></div>