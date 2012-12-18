<%@ include file="../../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer sgr = StringUtils.parseInt(request.getParameter("sgr"));
    Integer sir = StringUtils.parseInt(request.getParameter("sir"));
    String text = StringUtils.parseString(request.getParameter("t"));

    StringBuilder buf = new StringBuilder();
    try {

        // Adding shared item
        Tuple<Integer, Integer> refAndPoints = SharedCommentServices.add (networkId, userId, sgr, sir, text);

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
<shared_comment>
    <%= buf.toString() %>
</shared_comment>
