<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    Integer toUserId = StringUtils.parseInt(request.getParameter("vuid"));

    // Determine if viewing myself, or if a user link is required
    UserLinkServices.jspViewMyselfOrValidateUsersLinked(homeId, meId, toUserId);

    // Retrieving max length for messages
    Integer messageMaxLength = NetworkIntegerSettingEnum.USER_MESSAGE_MAX_LENGTH.getValueByNetworkId(homeId);

    // Retrieving network settings
    Integer pointsPer = NetworkIntegerSettingEnum.USER_MESSAGE_POINTS_PER.getValueByNetworkId(homeId);
%>

<div class="canvas_container" style="background-color: <%= HtmlDesign.dim %>;">
    <div class="w450 center">

        <div id="user_message_sent" class="white vl_text" style="display: none; font-family: adam; padding: 20px; text-align: center; line-height: 1.5em;">
            Your message has been sent!
        </div>

        <div id="send_user_message_container" style="display: inline-block;">

            <div class="white smd_text" style="padding: 5px 0 10px 0; text-align: center;">
                Messages can be no longer than <%= messageMaxLength %> characters
            </div>

            <div style=" padding: 5px; background-color: white; display: inline-block;">
                <textarea id="send_user_message_quote" class="md_input w450" style="height: 100px" maxlength="<%= messageMaxLength %>" placeholder="Type your message here..."></textarea>
            </div>

            <div style="margin: 10px 0 0 0; display: inline-block; width: 100%">

                <div id="send_user_message_error" class="sm_text white" style="float: left; display: none; padding: 5px; background-color: <%= HtmlDesign.errorBackgroundColor %>;"></div>

                <a href="#" onclick="UserMessage.sendFromProfile(event, <%= homeId %>, <%= toUserId %>)">
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