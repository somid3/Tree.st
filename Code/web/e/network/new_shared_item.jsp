<%@ include file="../../all.jsp"%>
<%
    NetworkEventEnum event = NetworkEventEnum.getByValue(StringUtils.parseInt(request.getParameter("ne")));
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    Integer sharedItemRef = StringUtils.parseInt(request.getParameter("sir"));

    // Retrieving network
    Network network = NetworkDao.getById(null, networkId);

    // Retrieving smart group
    SmartGroup smartGroup = null;
    if (!SmartGroupDao.isNetworkRef(smartGroupRef))
        smartGroup = SmartGroupDao.getByNetworkIdAndRef(null, networkId, smartGroupRef);

    // Retrieving shared item
    SharedItem sharedItem = SharedItemDao.getByNetworkIdAndSmartGroupRefAndRef(null, networkId, smartGroupRef, sharedItemRef);

    // Creating shared item link
    String hSharedItemLink = null;
    {
        UrlQuery query = new UrlQuery();
        query.add("uid", EmailServices.TO_USER_ID);
        query.add("scs", EmailServices.TO_USER_SALT_CHECKSUM);
        query.add("nid", sharedItem.getNetworkId());
        query.add("sgr", sharedItem.getSmartGroupRef());
        query.add("sir", sharedItem.getRef());
        hSharedItemLink = "http://" + Vars.domain + "/r/go/?" + query;
    }
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
    Network e_network = network;
    SmartGroup e_smartGroup = smartGroup;
    NetworkEventEnum e_event = event;
%>
<%@ include file="./includes/e_footer_row.jsp"%>
<%@ include file="../includes/b_container_end.jsp"%>