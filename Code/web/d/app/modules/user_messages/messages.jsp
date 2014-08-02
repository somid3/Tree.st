<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    Integer toUserId = StringUtils.parseInt(request.getParameter("tuid"));

    // Retrieving all messages -- sent and received
    List<UserMessage> userMessages = UserMessageDao.getByNetworkIdAndBetweenUserIds(null, homeId,  meId, toUserId, SqlLimit.ALL);

    // Validate to user is in this network
    UserToNetworkServices.jspValidateUserInNetwork(toUserId, homeId);

    // Retrieving user to received message
    User toUser = UserDao.getById(null, toUserId);

    // Marking message group as read
    UserMessageGroupDao.updateReadByNetworkIdAndFromUserIdAndToUserId(null, true, homeId, meId, toUserId);

    // Retrieving max length for messages
    Integer messageMaxLength = NetworkIntegerSettingEnum.USER_MESSAGE_MAX_LENGTH.getValueByNetworkId(homeId);

    // Retrieving network settings
    Integer pointsPer = NetworkIntegerSettingEnum.USER_MESSAGE_POINTS_PER.getValueByNetworkId(homeId);

    // Counting total messages exchanged
    Integer count = userMessages.size();
%>

<%
    if (count > 0) {

        User author = null;
        UserMessage lastUserMessage = userMessages.get(userMessages.size() - 1);
        for (UserMessage userMessage : userMessages) {

            // Determining the author of the message
            if (userMessage.getFromUserId().equals(meId))
                author = me;
            else
                author = toUser;
%>

            <% if (lastUserMessage.equals(userMessage)) { %>
                <div id="user_message_last"></div>
            <% } %>

            <div class="user_message canvas_container" style="display: inline-block">

                <div style="float: left;">
                    <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.member(homeId, author.getId(), meId)%>');">
                        <img style="height: 50px; width: 50px;" src="<%= author.getFaceUrl() %>">
                    </a>
                </div>

                <div style="float: left; width: 520px; margin-left: 10px;">

                    <div style="width: 100%; display: inline-block">

                        <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.member(homeId, author.getId(), meId) %>');">
                            <div style="float: left; margin-top: 5px;">
                                <span class="smd_header highlight2"><%= StringUtils.concat(author.getName(), 20, "...") %></span>
                            </div>
                        </a>

                        <div style="float: right;">
                            <span class="sm_text dim2"><%= PrettyDate.toString(userMessage.getCreatedOn()) %></span>
                        </div>
                    </div>

                    <div style="width: 100%; display: inline-block">
                        <span class="smd_text dim"><%= HtmlUtils.paragraphize(userMessage.getMessage()) %></span>
                    </div>
                </div>
            </div>
        <% } %>

        <script type="text/javascript">
            Animations.scrollTo("user_message_last");
        </script>
<%
    } else {

        String app_a_message = "No messages!";
        boolean app_a_withCanvasContainer = true; %>
        <%@ include file="../../includes/app_a_mini_message.jsp" %>
        <script>Pagination.unbindScrollPagination();</script>

<%  } %>


<div class="canvas_container" style="background-color: <%= HtmlDesign.dim %>; margin-top: 10px;">
    <div class="w450 center">

        <div id="user_message_sent" class="white vl_text" style="display: none; font-family: adam; padding: 20px; text-align: center; line-height: 1.5em;">
            Your message has been sent!
        </div>

        <div id="send_user_message_container" style="display: inline-block;">

            <div class="white" style="padding: 5px 0 10px 0; text-align: center;">
                <div class="md_text">Message <%= toUser.getFirstName() %></div>
            </div>

            <div style=" padding: 5px; background-color: white; display: inline-block;">
                <textarea id="send_user_message_quote" class="md_input w450" style="height: 100px" maxlength="<%= messageMaxLength %>" placeholder="Type your message here..."></textarea>
            </div>

            <div style="margin: 10px 0 0 0; display: inline-block; width: 100%">

                <div id="send_user_message_error" class="sm_text white" style="float: left; display: none; padding: 5px; background-color: <%= HtmlDesign.errorBackgroundColor %>;"></div>

                <a href="#" onclick="UserMessage.send(event, <%= homeId %>, <%= toUserId %>)">
                    <div class="submit_button md_button md_text w100" style="float: right; border: solid 1px white">
                        Send

                        <% if (pointsPer != 0) { %>
                            <span class="sm_text">(<%= pointsPer %> pts.)</span>
                        <% } %>
                    </div>
                </a>

                <div id="send_user_message_loading" style="float: right; margin: 5px 10px 0 0; display: none;"><img src="./img/sm_loading_white.gif"></div>
            </div>
        </div>
    </div>
</div>

