<%@ include file="../../all.jsp" %>
<%@ include file="load.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer viewUserId = StringUtils.parseInt(request.getParameter("vuid"));

    // Determine if viewing myself, or if a user link is required
    boolean viewMyself = UserLinkServices.viewMyselfOrValidateUsersLinked(networkId, userId, viewUserId);

    // Retrieving total number of shared items
    Integer count = SharedItemDao.countByNetworkIdAndUserId(null, networkId, viewUserId);
%>

<script type="text/javascript">
    SI = new SharedItem();
    SI.networkId = <%= networkId %>;
    SI.smartGroupRef = <%= SmartGroup.ANY_SMART_GROUP_REF %>;
    SI.viewUserId = <%= viewUserId %>;
</script>

<div id="share_canvas">
<% if (count > 0) { %>

    <script type="text/javascript">
        SI.displaySharedItems();
    </script>

<% } else {

    String app_a_message = "No shared messages!";
    boolean app_a_withCanvasContainer = true;  %>

    <%@ include file="../../includes/app_a_mini_message.jsp" %>

<% } %>
</div>