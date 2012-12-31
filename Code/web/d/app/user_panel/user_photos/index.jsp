<%@ include file="../../all.jsp" %>
<%@ include file="../load.jsp" %>

<%
    String hTargetId = HtmlUtils.getRandomId();
    String hShortcutsId = null;
    String hShortcutId = null;
%>

<script type="text/javascript">
    UPH = new UserPhotos();
</script>

<div class="canvas_container">

    <div class="user_setting">

        <% hShortcutsId = HtmlUtils.getRandomId(); %>
        <div id="<%= hShortcutsId %>" class="shortcuts">
            <% hShortcutId = HtmlUtils.getRandomId(); %>
            <a href="#" onclick="UPH.clickItem(event, '<%= hShortcutsId %>', '<%= hShortcutId %>', '<%= hTargetId %>', './user_panel/user_general/name_change.jsp')"><div id="<%= hShortcutId %>" class="shortcut sm_text highlight2">Name change</div></a>

            <% hShortcutId = HtmlUtils.getRandomId(); %>
            <a href="#" onclick="UPH.clickItem(event, '<%= hShortcutsId %>', '<%= hShortcutId %>', '<%= hTargetId %>', './user_panel/user_general/email_change.jsp')"><div id="<%= hShortcutId %>" class="shortcut sm_text highlight2">Email change</div></a>

            <% hShortcutId = HtmlUtils.getRandomId(); %>
            <a href="#" onclick="UPH.clickItem(event, '<%= hShortcutsId %>', '<%= hShortcutId %>', '<%= hTargetId %>', './user_panel/user_general/password_change.jsp')"><div id="<%= hShortcutId %>" class="shortcut sm_text highlight2">Password update</div></a>
        </div>

        <div id="<%= hTargetId %>"></div>

    </div>

</div>
