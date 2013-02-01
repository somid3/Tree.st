<%@ include file="../../../all.jsp" %>
<%
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    Integer sharedItemRef = StringUtils.parseInt(request.getParameter("sir"));
    String text = StringUtils.parseString(request.getParameter("t"));

    StringBuilder buf = new StringBuilder();
    try {

        // Adding shared item
        Tuple<Integer, Integer> refAndPoints = SharedCommentServices.add (homeId, meId, smartGroupRef, sharedItemRef, text);

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
