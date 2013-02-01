<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    Integer viewUserId = StringUtils.parseInt(request.getParameter("vuid"));

    // Retrieving user being viewed
    User viewed = UserDao.getById(null, viewUserId);

    // Retrieving viewed user's mapping to network
    Integer pointsForLink = UserLinkServices.getPointsPerLink(homeId, viewUserId);

    String hCardId = HtmlUtils.getRandomId();
    String hConnectButtonId = HtmlUtils.getRandomId();
%>

<div class="access canvas_container" id="<%= hCardId %>">

    <div class="error"></div>

    <div class="message md_text dim">
        Do you wish to view <%= viewed.getFirstName() %>'s profile ...
    </div>

    <a href="#" onclick="
        UserLink.connect(
            event,
            <%= homeId %>,
            <%= viewUserId %>,
            '<%= hCardId%>',
            '<%= hConnectButtonId %>',
            function (){
                ND.go(null, NetworkDashboard.Section.MEMBER, {vuid: <%= viewUserId %>});
            });">

        <div class="connect vl_button active_button" id="<%= hConnectButtonId %>">Yes</div>
    </a>

    <% if (pointsForLink > 0) { %>
    <div class="requires vl_text highlight2">
        Gain <span class="sp_header"><%= pointsForLink %></span> points
    </div>
    <% } %>

    <% if (pointsForLink < 0) { %>
        <div class="requires vl_text highlight2">
            Requires <span class="sp_header"><%= pointsForLink * -1 %></span> points
        </div>
    <% } %>

</div>