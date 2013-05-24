<% {
    /**
     * Inputs variables
     *
     *   Integer sgroup_e_userId = null;
     *   Integer sgroup_e_networkId = null;
     *   Integer sgroup_e_smartGroupRef = null;
     */
    UserToSmartGroup sgroup_e_mapping = UserToSmartGroupDao.getByNetworkIdAndSmartGroupRefAndUserId(null, sgroup_e_networkId, sgroup_e_smartGroupRef, sgroup_e_userId);
    String sgroup_e_hStateId = HtmlUtils.getRandomId();

%>

    <div class="smart_group_state" id="<%= sgroup_e_hStateId %>">

        <%
            // If no state exists, button appears un-highlighted, clicking adds favorite state
            if (sgroup_e_mapping == null) { %>

                <a href="#" onclick="SGT.applyState(event, '<%= sgroup_e_hStateId%>', <%= sgroup_e_networkId %>, <%= sgroup_e_smartGroupRef %>, <%= UserToSmartGroupStateEnum.FAVORITE.getId() %>)">
                    <div class="sm_button light_button sm_text">Join</div>
                </a>

            <% }

            // If state exists and user automatically got added to smart group
            else if (sgroup_e_mapping.isMember()) {

                // If not flagged, button is highlighted, clicking changes state to flagged
                if (sgroup_e_mapping.getState() != UserToSmartGroupStateEnum.FLAGGED) { %>

                    <a href="#" onclick="SGT.applyState(event, '<%= sgroup_e_hStateId%>', <%= sgroup_e_networkId %>, <%= sgroup_e_smartGroupRef %>, <%= UserToSmartGroupStateEnum.FLAGGED.getId() %>)">
                        <div class="sm_button pink_button sm_text">Auto-Joined!</div>
                    </a>

                <% }

                // If flagged, button is not highlighted, clicking removes flagged state
                else if (sgroup_e_mapping.getState() == UserToSmartGroupStateEnum.FLAGGED) { %>

                    <a href="#" onclick="SGT.applyState(event, '<%= sgroup_e_hStateId%>', <%= sgroup_e_networkId %>, <%= sgroup_e_smartGroupRef %>, <%= UserToSmartGroupStateEnum.FLAGGED.getId() %>)">
                        <div class="sm_button light_button sm_text">Join</div>
                    </a>

                <% } %>

            <% }

            // If user is not automatically added
            else if (!sgroup_e_mapping.isMember()) {

                // If not favorite, button is not highlighted, clicking adds favorite state
                if (sgroup_e_mapping.getState() != UserToSmartGroupStateEnum.FAVORITE) { %>

                    <a href="#" onclick="SGT.applyState(event, '<%= sgroup_e_hStateId%>', <%= sgroup_e_networkId %>, <%= sgroup_e_smartGroupRef %>, <%= UserToSmartGroupStateEnum.FAVORITE.getId() %>)">
                         <div class="sm_button light_button sm_text">Join</div>
                     </a>

                <% }

                // If favorite, button is highlighted, clicking removes favorite state
                else if (sgroup_e_mapping.getState() == UserToSmartGroupStateEnum.FAVORITE) { %>

                    <a href="#" onclick="SGT.applyState(event, '<%= sgroup_e_hStateId%>', <%= sgroup_e_networkId %>, <%= sgroup_e_smartGroupRef %>, <%= UserToSmartGroupStateEnum.FAVORITE.getId() %>)">
                        <div class="sm_button pink_button sm_text">Joined</div>
                    </a>

                <% }

                // If user is not a member and state is flagged, button is un-highlighted, clicking adds favorite state
                else if (sgroup_e_mapping.getState() == UserToSmartGroupStateEnum.FLAGGED) { %>

                    <a href="#" onclick="SGT.applyState(event, '<%= sgroup_e_hStateId%>', <%= sgroup_e_networkId %>, <%= sgroup_e_smartGroupRef %>, <%= UserToSmartGroupStateEnum.FAVORITE.getId() %>)">
                        <div class="sm_button light_button sm_text">Join</div>
                    </a>

                <% } %>

        <% } %>

    </div>

<% } %>