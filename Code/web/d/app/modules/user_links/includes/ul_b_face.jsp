<%
{
    /* Inputs variables
     *
     * Integer ul_b_networkId = null;
     * Integer ul_b_toUserId = null;
     * AnswerVisibilityEnum ul_b_lowestVisibility = null;
     */

    /**
     * Private variables
     */
    User ul_b_toUser = UserDao.getById(null, ul_b_toUserId);
    ul_b_toUser  = VisibilityConvertor.convert(ul_b_toUser, ul_b_lowestVisibility);

    // Html variables
    String ul_b_hCardHolderId = HtmlUtils.getRandomId();
    String ul_b_hFaceId = HtmlUtils.getRandomId();
%>

<div class="user_face">

    <div class="user_card_holder vl_shadow" id="<%= ul_b_hCardHolderId %>"></div>


    <%
        /* REGULAR FACE USE CASE
         *
         * Since the user has not answered a question anonymously,
         * then his/her face should be displayed
         */
        if (AnswerVisibilityEnum.ANONYMOUS == ul_b_lowestVisibility) { %>

        <div class="face" id="<%= ul_b_hFaceId %>">
            <img src="<%= ul_b_toUser.getFaceUrl() %>"/>
        </div>


    <%
        /* ANONYMOUS USE CASE
         *
         * If the user has answered a question anonymously, then the face
         * displayed should be that of an anonymous user
         */
        } else { %>

        <a href="#" onmouseover="UserLink.bindDetails(null, '<%= ul_b_hFaceId %>', <%= ul_b_networkId %>, <%= ul_b_toUser.getId() %>, '<%= ul_b_hCardHolderId %>');">
            <div class="mini_face" id="<%= ul_b_hFaceId %>">
                <img src="<%= ul_b_toUser.getFaceUrl() %>"/>
            </div>
        </a>

    <% } %>

</div>

<% } %>