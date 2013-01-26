<%@ include file="../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    SmartGroupsViewEnum view = SmartGroupsViewEnum.getById(StringUtils.parseInt(request.getParameter("view")));
    Integer startFrom = StringUtils.parseInt(request.getParameter("from"));

    Integer count = null;
    switch (view) {

        case OFFICIAL:
            count = SmartGroupDao.countByNetworkIdAndVisibility(null, networkId, SmartGroupVisibilityEnum.OFFICIAL);
            break;

        case SHARED:
            count = SmartGroupDao.countByNetworkIdAndVisibility(null, networkId, SmartGroupVisibilityEnum.SHARED);
            break;

        case YOURS:
            count = SmartGroupDao.countNonSearchByNetworkIdAndUserId(null, networkId, userId);
            break;

        case MATCHED:
            count = UserToSmartGroupDao.countByNetworkIdAndUserIdAndMember(null, networkId, userId, true);
            break;

        case FAVORITES:
            count = UserToSmartGroupDao.countByNetworkIdAndUserIdAndState(null, networkId, userId, UserToSmartGroupStateEnum.FAVORITE);
            break;

        case FLAGS:
            count = UserToSmartGroupDao.countByNetworkIdAndUserIdAndState(null, networkId, userId, UserToSmartGroupStateEnum.FLAGGED);
            break;
    }

    Integer sgroup_h_userId = userId;
    Integer sgroup_h_networkId = networkId;
    SmartGroupsViewEnum sgroup_h_view = view;
%>


<% if (count > 0) {

    Integer duration = 12;
    SqlLimit sgroup_h_limit = new SqlLimit(startFrom, duration); %>

    <%@ include file="./includes/sgroup_h_smart_group_boxes.jsp" %>

<% } else {

    String app_a_message = "No such Smart Groups could be found!";
    boolean app_a_withCanvasContainer = true; %>
    <%@ include file="../../includes/app_a_mini_message.jsp" %>
    <script>Pagination.unbindScrollPagination();</script>

<% } %>

