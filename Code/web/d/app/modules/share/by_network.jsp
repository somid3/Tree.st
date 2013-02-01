<%@ include file="../../all.jsp" %>
<%@ include file="./load.jsp" %>
<%
    // Retrieving total number of shared items
    Integer count = SharedItemDao.countByNetworkId(null, homeId);
%>

<script type="text/javascript">
    SI = new SharedItem();
    SI.networkId = <%= homeId %>;
    SI.smartGroupRef = <%= SmartGroup.ANY_SMART_GROUP_REF %>;
</script>

<div id="share_canvas">
<% if (count > 0) { %>

    <script type="text/javascript">
        SI.displaySharedItems();
    </script>

<% } else { %>

    <%@ include file="./includes/share_e_first.jsp" %>

<% } %>
</div>

