<% {
    /**
     * Inputs variables
     *
     *   Integer sgroup_h_userId = null;
     *   Integer sgroup_h_networkId = null;
     *   SqlLimit sgroup_h_limit = null;
     *   SmartGroupViewEnum sgroup_h_view = null;
     */


    List<SmartGroup> sgroup_h_smartGroups = null;
    List<UserToSmartGroup> sgroup_h_userToSmartGroups = null;
    switch (view) {

        case OFFICIAL:
            sgroup_h_smartGroups = SmartGroupDao.getNonHiddenByNetworkIdAndVisibility(null, sgroup_h_networkId, SmartGroupVisibilityEnum.OFFICIAL, sgroup_h_limit);
            break;

        case SHARED:
            sgroup_h_smartGroups = SmartGroupDao.getNonHiddenByNetworkIdAndVisibility(null, sgroup_h_networkId, SmartGroupVisibilityEnum.SHARED, sgroup_h_limit);
            break;

        case YOURS:
            sgroup_h_smartGroups = SmartGroupDao.getNonHiddenByNetworkIdAndUserId(null, sgroup_h_networkId, sgroup_h_userId, sgroup_h_limit);
            break;

        case MATCHED:
            sgroup_h_userToSmartGroups = UserToSmartGroupDao.getMembersByNetworkIdAndUserId(null, sgroup_h_networkId,  sgroup_h_userId, sgroup_h_limit);
            sgroup_h_smartGroups = UserToSmartGroupServices.getSmartGroups(sgroup_h_userToSmartGroups);
            break;

        case FAVORITES:
            sgroup_h_userToSmartGroups = UserToSmartGroupDao.getByNetworkIdAndUserIdAndState(null, sgroup_h_networkId,  sgroup_h_userId, UserToSmartGroupStateEnum.FAVORITE, sgroup_h_limit);
            sgroup_h_smartGroups = UserToSmartGroupServices.getSmartGroups(sgroup_h_userToSmartGroups);
            break;

        case FLAGS:
            sgroup_h_userToSmartGroups = UserToSmartGroupDao.getByNetworkIdAndUserIdAndState(null, sgroup_h_networkId,  sgroup_h_userId, UserToSmartGroupStateEnum.FLAGGED, sgroup_h_limit);
            sgroup_h_smartGroups = UserToSmartGroupServices.getSmartGroups(sgroup_h_userToSmartGroups);
            break;
    }

    // Remove search smart groups
    SmartGroupDao.removeSearchSmartGroups(sgroup_h_smartGroups);

    Integer sgroup_g_userId = sgroup_h_userId;
    for (SmartGroup sgroup_g_smartGroup : sgroup_h_smartGroups) { %>

        <%@ include file="sgroup_g_smart_group_box.jsp" %>

    <% } %>

<% } %>