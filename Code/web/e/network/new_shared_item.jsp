<%@ include file="../../all.jsp"%>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    Integer sharedItemRef = StringUtils.parseInt(request.getParameter("sir"));
    Boolean isDigest = StringUtils.parseBoolean(request.getParameter("digest"));

    // Retrieving network
    Network network = NetworkDao.getById(null, networkId);

    // Retrieving smart group
    SmartGroup smartGroup = null;
    if (!SmartGroupDao.isNetworkRef(smartGroupRef))
        smartGroup = SmartGroupDao.getByNetworkIdAndRef(null, networkId, smartGroupRef);

    // Retrieving shared item
    SharedItem sharedItem = SharedItemDao.getByNetworkIdAndSmartGroupRefAndRef(null, networkId, smartGroupRef, sharedItemRef);
%>

<%@ include file="../includes/a_container_start.jsp"%>

<%
    Network c_network = network;
    SmartGroup c_smartGroup = smartGroup;
    String c_title = null;
%>
<%@ include file="../includes/c_header_row.jsp"%>

<%
    Network f_network = network;
    SmartGroup f_smartGroup = smartGroup;
    SharedItem f_sharedItem = sharedItem;
%>
<%@ include file="../includes/f_shared_item_row.jsp"%>

<%
    List<String> e_removals = new ArrayList<String>();

    // Show we add the modify digest rate links?
    if (isDigest) {

        // Change digest rate
        {
            StringBuilder buf = new StringBuilder();
            buf.append("Change your 'new message' notification rate to:</br></br>");
            buf.append(EmailServices.helperCreateActionRateUrls(EmailActionEnum.CHANGE_ACTIVE_SMART_GROUP_DIGEST_RATE, networkId));
            e_removals.add(buf.toString());
        }


        // Adding flag this smart group link
        {
            UrlQuery parameters = new UrlQuery();
            parameters.add("nid", network.getId());
            parameters.add("sgr", smartGroup.getRef());
            String flagSmartGroupLink = HtmlUtils.createHref("Unsubscribe", EmailServices.helperCreateActionUrl(EmailActionEnum.FLAG_SMART_GROUP, parameters));
            e_removals.add(flagSmartGroupLink + " from the '" + StringUtils.concat(smartGroup.getName(), 15, "...") + "' smart group");
        }

        // Adding unsubscribe from all network new shared messages
        {
            UrlQuery parameters = new UrlQuery();
            parameters.add("nid", network.getId());
            String unsubscribeSharedItemNotifications = HtmlUtils.createHref("Unsubscribe", EmailServices.helperCreateActionUrl(EmailActionEnum.UNSUBSCRIBE_FROM_NEW_SHARED_ITEM_NOTIFICATIONS, parameters));
            e_removals.add(unsubscribeSharedItemNotifications + " from 'new message' notifications at '" + StringUtils.concat(network.getName(), 15, "...") + "'");
        }

    }
%>
<%@ include file="../includes/e_footer_row.jsp"%>
<%@ include file="../includes/b_container_end.jsp"%>