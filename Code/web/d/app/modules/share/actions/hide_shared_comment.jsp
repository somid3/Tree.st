<%@ include file="../../../all.jsp" %>
<%
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    Integer sharedItemRef = StringUtils.parseInt(request.getParameter("sir"));
    Integer ref= StringUtils.parseInt(request.getParameter("ref"));

    StringBuilder buf = new StringBuilder();
    try {

        // Hiding shared item
        Tuple<Integer, Integer> refAndPoints = SharedCommentServices.hide(homeId, meId, smartGroupRef, sharedItemRef, ref);

        // Presenting results to user interface
        buf = new StringBuilder();
        buf.append("<hide ref=\"");
        buf.append(refAndPoints.getX());
        buf.append("\" points_removed=\"");
        buf.append(refAndPoints.getY());
        buf.append("\"/>");

    } catch (UIException e) {

        // Documenting error
        buf.append("<error>");
        buf.append(e.getMessage());
        buf.append("</error>");

    }
%>
<?xml version="1.0"?>
<shared_comment>
    <%= buf.toString() %>
</shared_comment>
