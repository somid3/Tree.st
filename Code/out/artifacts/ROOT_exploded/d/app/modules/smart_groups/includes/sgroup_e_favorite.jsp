<% {
    /**
     * Inputs variables
     *
     *   Integer sgroup_e_userId = null;
     *   Integer sgroup_e_networkId = null;
     *   Integer sgroup_e_smartGroupRef = null;
     */
    UserToSmartGroup sgroup_e_mapping = UserToSmartGroupDao.getByNetworkIdAndSmartGroupRefAndUserId(null, sgroup_e_networkId, sgroup_e_smartGroupRef, sgroup_e_userId);
    String sgroup_e_hFavoriteId = HtmlUtils.getRandomId();
%>

    <div class="smart_group_favorite" id="<%= sgroup_e_hFavoriteId %>">

        <% if (sgroup_e_mapping != null && sgroup_e_mapping.isMember()) { %><img src="./modules/smart_groups/img/tree.png" alt="Auto-matched!"><% } %>

        <% if (sgroup_e_mapping == null || sgroup_e_mapping.getState() == null || sgroup_e_mapping.getState() == UserToSmartGroupStateEnum.NONE) { %>

            <a href="#" onclick="SGT.toggleState(event, <%= UserToSmartGroupStateEnum.FAVORITE.getValue() %>, '<%= sgroup_e_hFavoriteId%>', <%= sgroup_e_networkId %>, <%= sgroup_e_smartGroupRef %>)"><img src="./modules/smart_groups/img/star_off.png" alt="Favorite"></a>
            <a href="#" onclick="SGT.toggleState(event, <%= UserToSmartGroupStateEnum.FLAGGED.getValue() %>, '<%= sgroup_e_hFavoriteId%>', <%= sgroup_e_networkId %>, <%= sgroup_e_smartGroupRef %>)"><img src="./modules/smart_groups/img/flag_off.png" alt="Flag"></a>

        <% } else if (sgroup_e_mapping.getState() == UserToSmartGroupStateEnum.FAVORITE) { %>

            <a href="#" onclick="SGT.toggleState(event, <%= UserToSmartGroupStateEnum.FAVORITE.getValue() %>, '<%= sgroup_e_hFavoriteId%>', <%= sgroup_e_networkId %>, <%= sgroup_e_smartGroupRef %>)"><img src="./modules/smart_groups/img/star_on.png" alt="Favorite"></a>
            <a href="#" onclick="SGT.toggleState(event, <%= UserToSmartGroupStateEnum.FLAGGED.getValue() %>, '<%= sgroup_e_hFavoriteId%>', <%= sgroup_e_networkId %>, <%= sgroup_e_smartGroupRef %>)"><img src="./modules/smart_groups/img/flag_off.png" alt="Flag"></a>

        <% } else if (sgroup_e_mapping.getState() == UserToSmartGroupStateEnum.FLAGGED) { %>

            <a href="#" onclick="SGT.toggleState(event, <%= UserToSmartGroupStateEnum.FAVORITE.getValue() %>, '<%= sgroup_e_hFavoriteId%>', <%= sgroup_e_networkId %>, <%= sgroup_e_smartGroupRef %>)"><img src="./modules/smart_groups/img/star_off.png" alt="Favorite"></a>
            <a href="#" onclick="SGT.toggleState(event, <%= UserToSmartGroupStateEnum.FLAGGED.getValue() %>, '<%= sgroup_e_hFavoriteId%>', <%= sgroup_e_networkId %>, <%= sgroup_e_smartGroupRef %>)"><img src="./modules/smart_groups/img/flag_on.png" alt="Flag"></a>

        <% } %>

    </div>

<% } %>