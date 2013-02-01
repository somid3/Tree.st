<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    SmartGroupsViewEnum view = SmartGroupsViewEnum.getById(StringUtils.parseInt(request.getParameter("view")));
    Integer startFrom = StringUtils.parseInt(request.getParameter("from"));

    Integer count = null;
    switch (view) {

        case OFFICIAL:
            count = SmartGroupDao.countByNetworkIdAndVisibility(null, homeId, SmartGroupVisibilityEnum.OFFICIAL);
            break;

        case SHARED:
            count = SmartGroupDao.countByNetworkIdAndVisibility(null, homeId, SmartGroupVisibilityEnum.SHARED);
            break;

        case YOURS:
            count = SmartGroupDao.countNonSearchByNetworkIdAndUserId(null, homeId, meId);
            break;

        case MATCHED:
            count = UserToSmartGroupDao.countByNetworkIdAndUserIdAndMember(null, homeId, meId, true);
            break;

        case FAVORITES:
            count = UserToSmartGroupDao.countByNetworkIdAndUserIdAndState(null, homeId, meId, UserToSmartGroupStateEnum.FAVORITE);
            break;

        case FLAGS:
            count = UserToSmartGroupDao.countByNetworkIdAndUserIdAndState(null, homeId, meId, UserToSmartGroupStateEnum.FLAGGED);
            break;
    }

    Integer sgroup_h_userId = meId;
    Integer sgroup_h_networkId = homeId;
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

