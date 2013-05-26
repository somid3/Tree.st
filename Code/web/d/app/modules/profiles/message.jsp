<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    Integer toUserId = StringUtils.parseInt(request.getParameter("vuid"));

    // Retrieving user to be messaged
    User toUser = UserDao.getById(null, toUserId);

    // Retrieving max length for messages
    Integer messageMaxLength = NetworkIntegerSettingEnum.USER_MESSAGE_MAX_LENGTH.getValueByNetworkId(homeId);
%>

<div class="canvas_container" style="background-color: <%= HtmlDesign.dim %>;">


    <div class="w450 center">

        <div id="message_sent" style="display: none;">
            <%
                String  app_a_message = "Your message has been sent!";
                boolean app_a_withCanvasContainer = false; %>
                <%@ include file="../../includes/app_a_mini_message.jsp" %>
        </div>

        <div id="send_message" style="display: inline-block;">

            <div class="white lg_text" style="padding: 10px 0 0 0; text-align: center;">
                Send <%= toUser.getFirstName() %> a message
            </div>

            <div class="white smd_text" style="padding: 5px 0 10px 0; text-align: center;">
                Messages can be no longer than <%= messageMaxLength %> characters
            </div>

            <div style=" padding: 5px; background-color: white; display: inline-block;">
                <textarea id="quote" class="md_input w450" style="height: 150px" maxlength="<%= messageMaxLength %>" placeholder="Type your message here..."></textarea>
            </div>

            <div style="margin: 10px 0 0 0; display: inline-block; width: 100%">

                <div id="send_message_error" class="sm_text white" style="float: left; display: none; padding: 5px; background-color: <%= HtmlDesign.errorBackgroundColor %>;"></div>

                <a href="#" onclick="UserMessage.send(event, <%= homeId %>, <%= toUserId %>)">
                    <div class="submit_button md_button md_text w100" style="float: right; border: solid 1px white">
                        Send
                    </div>
                </a>

                <div id="send_message_loading" style="float: right; margin: 5px 10px 0 0; display: none;"><img src="./img/sm_loading_white.gif"></div>

            </div>


        </div>

    </div>

</div>