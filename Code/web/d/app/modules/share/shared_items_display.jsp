<%@ include file="../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    Integer viewUserId = StringUtils.parseInt(request.getParameter("vuid"));
    Integer startFrom = StringUtils.parseInt(request.getParameter("from"));

    Integer duration = 10;
    SqlLimit limit = new SqlLimit(startFrom, duration);

    // Does the smart group reference belong to any smart group
    List<SharedItem> sharedItems = null;
    if (SmartGroupDao.isNetworkRef(smartGroupRef)) {

        // Yes... retrieving the shared messages of the user or the network as a whole

        if (viewUserId == null)

            sharedItems = SharedItemDao.getByNetworkId(null, networkId, limit);

        else

            sharedItems = SharedItemDao.getByNetworkIdAndUserId(null, networkId, viewUserId, limit);

    } else {

        // No... retrieve the messages of the particular smart group
        sharedItems = SharedItemDao.getByNetworkIdAndSmartGroupRef(null, networkId, smartGroupRef, limit);

    }

    Integer share_c_networkId = networkId;
    Integer share_c_fromSmartGroupRef = smartGroupRef;
    User share_c_me = UserDao.getById(null, userId);
    Map<NetworkAlphaSettingEnum, String> share_c_networkAlphaSettings = NetworkAlphaSettingEnum.getMapByNetworkId(networkId);
    Map<NetworkIntegerSettingEnum, Integer> share_c_networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(networkId);
    Integer count = sharedItems.size();

    if (count > 0) {

        for (SharedItem share_c_sharedItem : sharedItems) { %>
            <%@ include file="includes/share_c_shared_item_box.jsp" %>
        <% } %>

    <% } else {

        String app_a_message = "No more messages could be found!";
        boolean app_a_withCanvasContainer = true; %>
        <%@ include file="../../includes/app_a_mini_message.jsp" %>
        <script>Pagination.unbindScrollPagination();</script>

    <% } %>
