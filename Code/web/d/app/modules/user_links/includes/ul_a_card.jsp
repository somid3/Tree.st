<% {
    /* Inputs variables
     *
     * Integer ul_a_toUserId = null;
     * Integer ul_a_networkUserLinkPointsPer = null;
     */

    // Retrieving user whose card will be displayed
    User ul_a_toUser = UserDao.getById(null, ul_a_toUserId);

    // Retrieving user to network to get points...
    UserToNetwork ul_a_toUserToNetwork = UserToNetworkDao.getByUserIdAndNetworkId(null, ul_a_toUserId, homeId);

    // Attempt to see if the user has user link
    UserLink ul_a_toUserLink = UserLinkDao.getByNetworkIdAndFromUserIdAndToUserId(null, homeId, meToHome.getUserId(), ul_a_toUserId);

    Boolean ul_a_displayDetails = false;
    if (ul_a_toUserLink != null && ul_a_toUserLink.getDirection() != UserLinkDirectionEnum.TARGET_TO_ME)
        ul_a_displayDetails = true;

    String ul_a_hCardId = HtmlUtils.getRandomId();
    String ul_a_hConnectButtonId = HtmlUtils.getRandomId();
%>

<div id="<%= ul_a_hCardId %>">
    <div class="user_card sm_shadow">
       <div>

           <div class="card_left">

               <%
                   // Changing the link on the photo depending if a user link exists
                   if (ul_a_displayDetails) { %>
                   <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.member(homeId, ul_a_toUserId, meToHome.getUserId())%>');">

               <% } else { %>

                   <a href="#" onclick="UserLink.connect(event, <%= homeId %>, <%= ul_a_toUser.getId() %>, '<%= ul_a_hCardId %>', '<%= ul_a_hConnectButtonId %>')">

               <% } %>

                   <div class="card_face">
                       <img src="<%= ul_a_toUser.getFaceUrl() %>"/>
                       <div class="points vsm_text dim shadow"><%= ul_a_toUserToNetwork.getCurrentPoints() %> pts.</div>
                 </div>
               </a>

           </div>

           <div class="card_right">

               <%
                   /**
                   * ALREADY CONNECTED USE CASE -  Users are already linked up
                   */
                   if (ul_a_displayDetails) { %>

                   <div class="card_top">

                       <div class="card_details">
                           <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.member(homeId, ul_a_toUserId, meToHome.getUserId())%>');">
                               <div class="name smd_header highlight2"><%= StringUtils.concat(ul_a_toUser.getName(), 18, "&hellip;") %></div>
                           </a>
                       </div>

                       <div class="card_shortcuts">

                           <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.member(homeId, ul_a_toUserId, meToHome.getUserId())%>');">
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
                   /**
                    * CREATE NEW CONNECTION USE CASE -  Users are not linked, display connect button and required points
                    */
                   } else { %>

                       <div class="card_top">

                           <div class="card_details">
                               <a href="#" onclick="UserLink.connect(event, <%= homeId %>, <%= ul_a_toUser.getId() %>, '<%= ul_a_hCardId %>', '<%= ul_a_hConnectButtonId %>')">
                                   <div class="name smd_header highlight2"><%= StringUtils.concat(ul_a_toUser.getName(), 18, "&hellip;") %></div>
                               </a>
                           </div>

                       </div>

                       <div class="error sm_text"></div>

                       <div class="card_create">

                           <div class="loading"><img src="./img/sm_loading.gif"></div>

                           <a href="#" onclick="UserLink.connect(event, <%= homeId %>, <%= ul_a_toUser.getId() %>, '<%= ul_a_hCardId %>',  '<%= ul_a_hConnectButtonId %>')">
                               <div id="<%= ul_a_hConnectButtonId %>" class="connect lg_button active_button">View</div>
                           </a>

                           <% if (ul_a_networkUserLinkPointsPer < 0) { %>
                               <div class="sm_text highlight2">Requires <span class="vl_header"><%= ul_a_networkUserLinkPointsPer * -1 %></span> points</div>
                           <% } %>

                           <% if (ul_a_networkUserLinkPointsPer > 0) { %>
                               <div class="sm_text highlight2">Gain <span class="vl_header"><%= ul_a_networkUserLinkPointsPer %></span> points</div>
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
           if (meToHome.getRole().isHigherThan(RoleEnum.MEMBER) &&
                !meToHome.getUserId().equals(ul_a_toUserId)) { %>

            <div class="admin_card">
                 <% UserToNetwork ul_c_userToNetwork = ul_a_toUserToNetwork; %>
                 <%@ include file="ul_c_block.jsp" %>
            </div>

        <% } %>

    </div>
</div>
<% } %>