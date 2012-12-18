<%@ include file="../../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer sgr = StringUtils.parseInt(request.getParameter("sgr"));
    String text = StringUtils.parseString(request.getParameter("t"));

    StringBuilder buf = new StringBuilder();
    try {

        // Adding shared item
        Tuple<Integer, Integer> refAndPoints = SharedItemServices.add (networkId, userId, sgr, text);

        // Was creating the shared item successful?

        // Yes, presenting results to user interface
        buf = new StringBuilder();
        buf.append("<add ref=\"");
        buf.append(refAndPoints.getX());
        buf.append("\" points_added=\"");
        buf.append(refAndPoints.getY());
        buf.append("\"/>");

    } catch (UIException e) {

        // No, documenting error
        buf.append("<error>");
        buf.append(e.getMessage());
        buf.append("</error>");

    }
%>
<?xml version="1.0"?>
<shared_item>
    <%= buf.toString() %>
</shared_item>
