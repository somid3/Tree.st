<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    // Retrieving total number of shared items
    Integer count = UserMessageGroupDao.countByNetworkIdAndFromUserId(null, homeId, meId);

    String hMinitipId = HtmlUtils.getRandomId();
%>

<script type="text/javascript">
    UMG = new UserMessageGroups();
    UMG.networkId = <%= homeId %>;
</script>

<div class="canvas_header">Messages</div>

<% if (TooltipServices.displayMinitip(UserIntegerSettingEnum.TIP_MESSAGES_HOW, meId)) { %>
    <div class="minitip" id="<%= hMinitipId %>">
        <div class="lg_header tip">Tip:</div>
        <div class="content lg_text dim">
            As you message members, all your messages will get grouped below. The latest
            message sent or received will be listed first.
        </div>
        <div class="close">
            <a href="#" onclick="Tooltips.closeMinitip(event, '<%= hMinitipId %>', <%= UserIntegerSettingEnum.TIP_MESSAGES_HOW.getId()%>)">
                <img src="./img/close_dark.png">
            </a>
        </div>
    </div>
<% } %>

<div id="user_message_groups_canvas">
<% if (count > 0) { %>

    <script type="text/javascript">
        UMG.display();
    </script>

<% } else { %>

    <div class="first_note">
        <div class="canvas_container">
            <div class="container">

                <div><img src="./modules/user_messages/img/first.png"></div>

                <div class="vl_text dim">You have no messages</div>

                <div class="vl_text dim">
                    To message someone view their profile and click on the "Message ..." button
                </div>

            </div>
        </div>
    </div>

<% } %>
</div>

