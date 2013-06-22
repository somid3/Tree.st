<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>


<%
    Integer toUserId = StringUtils.parseInt(request.getParameter("tuid"));

    // Retrieving all messages -- sent and received
    List<UserMessage> userMessages = UserMessageDao.getByNetworkIdAndBetweenUserIds(null, homeId,  meId, toUserId, SqlLimit.ALL);

    // Retrieving user to received message
    User toUser = UserDao.getById(null, toUserId);

    // Marking message group as read
    UserMessageGroupDao.updateReadByNetworkIdAndFromUserIdAndToUserId(null, true, homeId, meId, toUserId);

    // Counting total messages exchanged
    Integer count = userMessages.size();
%>

<div class="canvas_header">
    Messages with
    <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.member(homeId, toUser.getId(), meId)%>');">
    <span class="highlight2"><%= toUser.getName() %></span>
</div>

<%
    if (count > 0) {

        User author = null;
        for (UserMessage userMessage : userMessages) {

            // Determining the author of the message
            if (userMessage.getFromUserId().equals(meId))
                author = me;
            else
                author = toUser;
%>
            <div class="user_message canvas_container" style="display: inline-block">

                <div style="float: left;">
                    <img style="height: 50px; width: 50px;" src="<%= author.getFaceUrl() %>">
                </div>

                <div style="float: left; width: 520px; margin-left: 10px;">

                    <div style="width: 100%; display: inline-block">
                        <div style="float: left; margin-top: 5px;">
                            <span class="smd_header dim"><%= StringUtils.concat(author.getName(), 20, "...") %></span>
                        </div>

                        <div style="float: right;">
                            <span class="sm_text dim2"><%= PrettyDate.toString(userMessage.getCreatedOn()) %></span>
                        </div>
                    </div>

                    <div style="width: 100%; display: inline-block">
                        <span class="smd_text dim"><%= userMessage.getMessage() %></span>
                    </div>
                </div>
            </div>
        <% } %>

    <% } else {

        String app_a_message = "No message from " + toUser.getFirstName();
        boolean app_a_withCanvasContainer = true; %>
        <%@ include file="../../includes/app_a_mini_message.jsp" %>
        <script>Pagination.unbindScrollPagination();</script>

    <% } %>
