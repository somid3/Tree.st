<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%@ include file="./load.jsp" %>
<%
    Integer viewUserId = StringUtils.parseInt(request.getParameter("vuid"));

    // Determine if viewing myself, or if a user link is required
    UserLinkServices.viewMyselfOrValidateUsersLinked(homeId, meId, viewUserId);

    // Retrieving user being viewed
    User viewed = UserDao.getById(null, viewUserId);

    // Retrieving total number of shared items
    Integer count = UserLinkDao.countByNetworkIdAndFromUserIdAndNotDirection(null, homeId, viewed.getId(), UserLinkDirectionEnum.TARGET_TO_ME);
%>

<div id="viewed_canvas">
<% if (count > 0) { %>

    <script type="text/javascript">
        PD.viewUserId = <%= viewUserId %>;
        PD.displayViewedUsers();
    </script>

<% } else {

    String app_a_message = "No connections yet!";
    boolean app_a_withCanvasContainer = true;  %>

    <%@ include file="../../includes/app_a_mini_message.jsp" %>

<% } %>
</div>