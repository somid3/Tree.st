<%@ include file="../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    Integer viewUserId = StringUtils.parseInt(request.getParameter("vuid"));
    Integer startFrom = StringUtils.parseInt(request.getParameter("from"));

    Integer duration = 10;
    SqlLimit limit = new SqlLimit(startFrom, duration);

    // Does the smart group reference belong to any smart group
    List<SharedItem> share_b_sharedItems = null;
    if (SmartGroupDao.isNetworkRef(smartGroupRef)) {

        // Yes... retrieving the shared messages of the user or the network as a whole

        if (viewUserId == null)

            share_b_sharedItems = SharedItemDao.getByNetworkId(null, networkId, limit);

        else

            share_b_sharedItems = SharedItemDao.getByNetworkIdAndUserId(null, networkId, viewUserId, limit);

    } else {

        // No... retrieve the messages of the particular smart group
        share_b_sharedItems = SharedItemDao.getByNetworkIdAndSmartGroupRef(null, networkId, smartGroupRef, limit);

    }

    Integer share_b_networkId = networkId;
    Integer share_b_fromSmartGroupRef = smartGroupRef;
    User share_b_me = UserDao.getById(null, userId);
    Integer share_b_settingSharedItemDisplayCreatedOn     = NetworkIntegerSettingEnum.SHARED_ITEM_DISPLAY_CREATED_ON.getValueByNetworkId(networkId);
    Integer share_b_settingSharedCommentDisplayCreatedOn  = NetworkIntegerSettingEnum.SHARED_COMMENT_DISPLAY_CREATED_ON.getValueByNetworkId(networkId);
    Integer share_b_settingSharedCommentPointsPer         = NetworkIntegerSettingEnum.SHARED_COMMENT_POINTS_PER.getValueByNetworkId(networkId);
    Integer share_b_settingSharedItemDisplayUpVotes       = NetworkIntegerSettingEnum.SHARED_ITEM_DISPLAY_UP_VOTES.getValueByNetworkId(networkId);
    Integer share_b_settingSharedItemDisplayDownVotes     = NetworkIntegerSettingEnum.SHARED_ITEM_DISPLAY_DOWN_VOTES.getValueByNetworkId(networkId);
    Integer share_b_settingSharedCommentsDisplayUpVotes   = NetworkIntegerSettingEnum.SHARED_COMMENTS_DISPLAY_UP_VOTES.getValueByNetworkId(networkId);
    Integer share_b_settingSharedCommentsDisplayDownVotes = NetworkIntegerSettingEnum.SHARED_COMMENTS_DISPLAY_DOWN_VOTES.getValueByNetworkId(networkId);
%>

<%@ include file="includes/share_b_shared_items.jsp" %>

