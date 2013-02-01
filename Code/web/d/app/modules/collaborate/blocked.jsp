<%@ include file="../../setup.jsp" %>
<% appDisableBlocked = false; %>
<%@ include file="../../auth.jsp" %>
<%
    List<UserToNetwork> editorOrOwners = UserToNetworkDao.getByNetworkIdAndLowestRoleOrderedBy (
            null,
            homeId,
            RoleEnum.EDITOR,
            AllMembersViewEnum.BY_POINTS,
            SqlLimit.ALL);
%>

<div class="question_note">
    <div class="canvas_container">
        <div class="container">

            <div><img src="./modules/collaborate/img/blocked.png"></div>

            <div class="vl_text dim">Your &quot;<%= home.getName() %> &quot; profile has been blocked. Please contact any of emails below to unblock your account.</div>

            <%
                User editor = null;
                for (UserToNetwork editorOrOwner : editorOrOwners) {

                    editor = UserDao.getById(null, editorOrOwner.getUserId()); %>

                    <a href="mailto:<%= editor.getEmail() %>">
                        <div class="sp_text highlight2"><%= editor.getEmail() %></div>
                    </a>

            <% } %>

        </div>
    </div>
</div>
