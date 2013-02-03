<% {
    /* Inputs variables
     *
     * UserToNetwork ul_c_userToNetwork = null;
     */

    String ul_c_hTargetId = HtmlUtils.getRandomId();
%>
    <a href="#" id="<%= ul_c_hTargetId %>" onclick="UserLink.toggleRemove(event, <%= ul_c_userToNetwork.getNetworkId() %>, <%= ul_c_userToNetwork.getUserId() %>, '<%= ul_c_hTargetId %>');">
        <% if (ul_c_userToNetwork.getBlockedOn() == null) { %>
            <div class="vsm_button light_button vsm_text">Approved</div>
        <% } else { %>
            <div class="vsm_button pink_button vsm_text">Blocked</div>
        <% } %>
    </a>

<% } %>