<%@ include file="../../../setup.jsp" %>
<%@ include file="../../../auth.jsp" %>
<%
    Integer toUserId = StringUtils.parseInt(request.getParameter("tuid"));
    String quote = StringUtils.parseString(request.getParameter("qu"));

    StringBuilder buf = new StringBuilder();

    try {

        Integer points = UserMessageServices.sendMessage(homeId, meId, toUserId, quote);

        buf.append("<confirm>");
        buf.append("<to_user>").append(toUserId).append("</to_user>");
        buf.append("<points>").append(points).append("</points>");
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
<send_message>
    <%= buf.toString() %>
</send_message>
