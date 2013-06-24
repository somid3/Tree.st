<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    Integer startFrom = StringUtils.parseInt(request.getParameter("from"));

    Integer duration = 10;
    SqlLimit limit = new SqlLimit(startFrom, duration);

    List<UserMessageGroup> userMessageGroups =
        UserMessageGroupDao.getByNetworkIdAndFromUserId(null, homeId,  meId, limit);

    Integer count = userMessageGroups.size();

    if (count > 0) {

        User toUser = null;
        String nonReadHtmlClass = null;
        for (UserMessageGroup userMessageGroup : userMessageGroups) {
            toUser = UserDao.getById(null, userMessageGroup.getToUserId());

            if (!userMessageGroup.isRead())
                nonReadHtmlClass = "border: solid 1px " + HtmlDesign.errorBackgroundColor +";";
            else
                nonReadHtmlClass = "";

            %>

            <a href="#" class="no_deco" onclick="HashRouting.setHash(event, '<%= HashRouting.profileMessages(homeId, userMessageGroup.getToUserId())%>')">
                <div class="user_message_group canvas_container" style="position: relative; display: inline-block; <%= nonReadHtmlClass%>">

                    <% if (userMessageGroup.isReplied()) { %>
                        <div style="position: absolute; left: -12px; top: 20px;">
                            <img src="./modules/user_messages/img/replied.png">
                        </div>
                    <% } %>


                    <div style="float: left;">
                        <img style="height: 50px; width: 50px;" src="<%= toUser.getFaceUrl() %>">
                    </div>

                    <div style="float: left; width: 520px; margin-left: 10px;">

                        <div style="width: 100%; display: inline-block">
                            <div style="float: left; margin-top: 5px;">
                                <span class="smd_header dim"><%= StringUtils.concat(toUser.getName(), 20, "...") %></span>
                            </div>

                            <div style="float: right;">
                                <span class="sm_text dim2"><%= PrettyDate.toString(userMessageGroup.getUpdatedOn()) %></span>
                            </div>
                        </div>

                        <div style="width: 100%; display: inline-block">
                            <span class="sm_text dim2"><%= StringUtils.concat(userMessageGroup.getSummary(), 100, "...") %></span>
                        </div>
                    </div>

                </div>
            </a>
        <% } %>

    <% } %>