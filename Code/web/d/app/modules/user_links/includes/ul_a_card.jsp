<% {
    /* Inputs variables
     *
     * Integer ul_a_toUserId = null;
     * Integer ul_a_networkId = null;
     * UserToNetwork ul_a_meToHome = null;
     */

    // Retrieving user whose card will be displayed
    User ul_a_toUser = UserDao.getById(null, ul_a_toUserId);

    // Retrieving user to network to get points...
    UserToNetwork ul_a_toUserToNetwork = UserToNetworkDao.getByUserIdAndNetworkId(null, ul_a_toUserId, ul_a_networkId);

    // Attempt to see if the user has user link
    UserLink ul_a_toUserLink = UserLinkDao.getByNetworkIdAndFromUserIdAndToUserId(null, ul_a_networkId, ul_a_meToHome.getUserId(), ul_a_toUserId);

    Boolean displayDetails = false;
    if (ul_a_toUserLink != null && ul_a_toUserLink.getDirection() != UserLinkDirectionEnum.TARGET_TO_ME)
        displayDetails = true;

    String ul_a_hCardId = HtmlUtils.getRandomId();
    String ul_a_hConnectButtonId = HtmlUtils.getRandomId();
%>

<div id="<%= ul_a_hCardId %>">
    <div class="user_card">
       <div>

           <div class="card_left">

               <%
                   // Changing the link on the photo depending if a user link exists
                   if (displayDetails) { %>
                   <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.member(ul_a_networkId, ul_a_toUserId, ul_a_meToHome.getUserId())%>');">
               <% } else { %>
                   <a href="#" onclick="UserLink.connect(event, <%= ul_a_networkId %>, <%= ul_a_toUser.getId() %>, '<%= ul_a_hCardId %>', '<%= ul_a_hConnectButtonId %>')">
               <% } %>

                   <div class="card_face">
                       <img src="<%= ul_a_toUser.getFaceUrl() %>"/>
                       <div class="points sm_text white"><%= ul_a_toUserToNetwork.getCurrentPoints() %> pts.</div>
                 </div>
               </a>

           </div>

           <div class="card_right">

               <% if (displayDetails) { %>

                   <div class="card_top">

                       <div class="card_details">
                           <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.member(ul_a_networkId, ul_a_toUserId, ul_a_meToHome.getUserId())%>');">
                               <div class="name smd_header highlight2"><%= StringUtils.concat(ul_a_toUser.getName(), 18, "&hellip;") %></div>
                           </a>
                           <a href="mailto:<%= ul_a_toUser.getEmail() %>" target="_new">
                               <div class="sm_text highlight2"><%= StringUtils.concat(ul_a_toUser.getEmail(), 18, "&hellip;") %></div>
                           </a>
                       </div>

                       <div class="card_shortcuts">

                           <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.member(ul_a_networkId, ul_a_toUserId, ul_a_meToHome.getUserId())%>');">
                               <div class="card_shortcut">
                                   <div class="text sm_text highlight2">Profile</div>
                               </div>
                           </a>

                       </div>

                       <div class="card_viewed_date">
                           <div class="vsm_text dim3">First viewed</div>
                           <div class="sm_text dim2">
                               <%= PrettyDate.toString(ul_a_toUserLink.getCreatedOn()) %>
                           </div>
                       </div>

                   </div>

               <%
                   /* CREATE NEW CONNECTION USE CASE
                    *
                    * Users are not linked, display connect button and required points
                    */
                   } else {

                       Integer ul_a_pointsForLink = UserLinkServices.getPointsPerLink(ul_a_networkId, ul_a_toUser.getId()); %>

                       <div class="card_top">

                           <div class="card_details">
                               <a href="#" onclick="UserLink.connect(event, <%= ul_a_networkId %>, <%= ul_a_toUser.getId() %>, '<%= ul_a_hCardId %>', '<%= ul_a_hConnectButtonId %>')">
                                   <div class="name smd_header highlight2"><%= StringUtils.concat(ul_a_toUser.getName(), 18, "&hellip;") %></div>
                               </a>
                           </div>

                       </div>

                       <div class="error sm_text"></div>

                       <div class="card_create">

                           <div class="loading"><img src="./img/sm_loading.gif"></div>

                           <a href="#" onclick="UserLink.connect(event, <%= ul_a_networkId %>, <%= ul_a_toUser.getId() %>, '<%= ul_a_hCardId %>',  '<%= ul_a_hConnectButtonId %>')">
                               <div id="<%= ul_a_hConnectButtonId %>" class="connect lg_button active_button">View</div>
                           </a>

                           <% if (ul_a_pointsForLink < 0) { %>
                               <div class="sm_text highlight2">Requires <span class="vl_header"><%= ul_a_pointsForLink * -1 %></span> points</div>
                           <% } %>

                           <% if (ul_a_pointsForLink > 0) { %>
                               <div class="sm_text highlight2">Gain <span class="vl_header"><%= ul_a_pointsForLink %></span> points</div>
                           <% } %>

                       </div>

               <% } %>

           </div>

       </div>

       <%
           /*
            * Displaying the admin card if the user is an editor or above, and if the
            * card being displayed is no the same card that belows to the user.
            */
           if (ul_a_meToHome.getRole().isHigherThan(RoleEnum.MEMBER) &&
                !ul_a_meToHome.getUserId().equals(ul_a_toUserId)) { %>

            <div class="admin_card">
                 <% UserToNetwork ul_c_userToNetwork = ul_a_toUserToNetwork; %>
                 <%@ include file="ul_c_block.jsp" %>
            </div>

        <% } %>

    </div>
</div>
<% } %>