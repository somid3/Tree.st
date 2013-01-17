<%@ include file="../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    Integer viewUserId = StringUtils.parseInt(request.getParameter("vuid"));
    Integer startFrom = StringUtils.parseInt(request.getParameter("from"));

    Integer duration = 10;
    SqlLimit limit = new SqlLimit(startFrom, duration);

    // Does the smart group reference belong to any smart group
    List<SharedItem> share_c_sharedItems = null;
    if (SmartGroupDao.isNetworkRef(smartGroupRef)) {

        // Yes... retrieving the shared messages of the user or the network as a whole

        if (viewUserId == null)

            share_c_sharedItems = SharedItemDao.getByNetworkId(null, networkId, limit);

        else

            share_c_sharedItems = SharedItemDao.getByNetworkIdAndUserId(null, networkId, viewUserId, limit);

    } else {

        // No... retrieve the messages of the particular smart group
        share_c_sharedItems = SharedItemDao.getByNetworkIdAndSmartGroupRef(null, networkId, smartGroupRef, limit);

    }

    Integer share_c_networkId = networkId;
    Integer share_c_fromSmartGroupRef = smartGroupRef;
    User share_c_me = UserDao.getById(null, userId);
    Map<NetworkAlphaSettingEnum, String> share_c_networkAlphaSettings = NetworkAlphaSettingEnum.getMapByNetworkId(networkId);
    Map<NetworkIntegerSettingEnum, Integer> share_c_networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(networkId);

    for (SharedItem share_c_sharedItem : share_c_sharedItems) { %>
        <%@ include file="./includes/share_c_shared_item.jsp" %>
    <% } %>