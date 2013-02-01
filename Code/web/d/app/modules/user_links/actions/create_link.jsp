<%@ include file="../../../all.jsp" %>
<%
    Integer toUserId = StringUtils.parseInt(request.getParameter("tuid"));

    StringBuilder buf = new StringBuilder();

    try {

        Tuple<UserLinkDirectionEnum, Integer> directionAndPoints = UserLinkServices.linkUsers(homeId, meId, toUserId);

        buf.append("<confirm>");
        buf.append("<to_user>").append(toUserId).append("</to_user>");
        buf.append("<direction_text>").append(directionAndPoints.getX().getName()).append("</direction_text>");
        buf.append("<direction_value>").append(directionAndPoints.getX().getId()).append("</direction_value>");
        buf.append("<points>").append(directionAndPoints.getY().toString()).append("</points>");
        buf.append("</confirm>");

    } catch (UIException ue) {

        // Documenting error
        buf = new StringBuilder();
        buf.append("<error>");
        buf.append(ue.getMessage());
        buf.append("</error>");

    }

%>
<?xml version="1.0"?>
<create_link>
    <%= buf.toString() %>
</create_link>
