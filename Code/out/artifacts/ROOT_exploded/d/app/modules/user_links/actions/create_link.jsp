<%@ include file="../../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer toUserId = StringUtils.parseInt(request.getParameter("tuid"));

    StringBuilder buf = new StringBuilder();


    try {

        Tuple<UserLinkDirectionEnum, Integer> directionAndPoints = UserLinkServices.linkUsers(networkId, userId, toUserId);

        buf.append("<confirm>");
        buf.append("<to_user>").append(toUserId).append("</to_user>");
        buf.append("<direction_text>").append(directionAndPoints.getX().getName()).append("</direction_text>");
        buf.append("<direction_value>").append(directionAndPoints.getX().getValue()).append("</direction_value>");
        buf.append("<used_points>").append(directionAndPoints.getY().toString()).append("</used_points>");
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
