<%@ include file="../../all.jsp"%>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer userId = StringUtils.parseInt(request.getParameter("uid"));
    EmailNotificationRateEnum rate = EmailNotificationRateEnum.getById(
        StringUtils.parseInt(request.getParameter("rate"))
    );

    // Retrieving network
    Network network = NetworkDao.getById(null, networkId);
 %>

<%@ include file="../includes/a_container_start.jsp"%>

<%
    Network c_network = network;
    SmartGroup c_smartGroup = null;
    String c_title = null;
%>

<%@ include file="../includes/c_header_row.jsp"%>

<%
    // Retrieving all active smart groups of user
    SmartGroup smartGroup = null;
    List<UserToSmartGroup> activeUserToSmartGroups = UserToSmartGroupDao.getActiveByNetworkIdAndUserId(null, networkId, userId, SqlLimit.ALL);

    Network f_network = null;
    SmartGroup f_smartGroup = null;
    SmartGroup g_smartGroup = null;
    SharedItem f_sharedItem = null;

    List<SharedItem> sharedItems = null;
    for (UserToSmartGroup activeUserToSmartGroup : activeUserToSmartGroups) {

        // Retrieving matching smart group
        smartGroup = SmartGroupDao.getByNetworkIdAndRef(null, networkId, activeUserToSmartGroup.getSmartGroupRef());

        // Retrieving shared item messages
        sharedItems = SharedItemDao.getByNetworkIdAndSmartGroupRefAndCreatedAfter(null, networkId, smartGroup.getRef(), rate.getBoundaryDate(), SqlLimit.ALL);

        if (sharedItems.isEmpty())
            continue;

        g_smartGroup = smartGroup; %>

        <%@ include file="../includes/h_spacer_row.jsp" %>
        <%@ include file="../includes/g_smart_group_row.jsp" %>
        <%@ include file="../includes/d_line_row.jsp" %>

        <% for (SharedItem sharedItem : sharedItems) {

            f_network = network;
            f_smartGroup = smartGroup;
            f_sharedItem = sharedItem; %>

            <%@ include file="../includes/d_line_row.jsp" %>
            <%@ include file="../includes/f_shared_item_row.jsp"%>

<%
        }
    }

    List<String> e_removals = new ArrayList<String>();

    // Change digest rate
    {
        StringBuilder buf = new StringBuilder();
        buf.append("Change your 'new message' notification rate to:<br/>");
        buf.append(EmailServices.helperCreateActionRateUrls(EmailActionEnum.CHANGE_ACTIVE_SMART_GROUP_DIGEST_RATE, networkId));
        e_removals.add(buf.toString());
    }

    // Adding unsubscribe from all network new shared messages
    {
        UrlQuery parameters = new UrlQuery();
        parameters.add("nid", network.getId());
        String unsubscribeSharedItemNotifications = HtmlUtils.createHref("Unsubscribe", EmailServices.helperCreateActionUrl(EmailActionEnum.UNSUBSCRIBE_FROM_NEW_SHARED_ITEM_NOTIFICATIONS, parameters));
        e_removals.add(unsubscribeSharedItemNotifications + " from 'new message' notifications at " + StringUtils.concat(network.getName(), 15, "..."));
    }

%>
<%@ include file="../includes/e_footer_row.jsp"%>
<%@ include file="../includes/b_container_end.jsp"%>