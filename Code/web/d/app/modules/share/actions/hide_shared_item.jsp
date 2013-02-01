<%@ include file="../../../setup.jsp" %>
<%@ include file="../../../auth.jsp" %>
<%
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    Integer sharedItemRef = StringUtils.parseInt(request.getParameter("sir"));

    StringBuilder buf = new StringBuilder();
    try {

        // Hiding shared item
        Tuple<Integer, Integer> refAndPoints = SharedItemServices.hide(homeId, meId, smartGroupRef, sharedItemRef);

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
<shared_item>
    <%= buf.toString() %>
</shared_item>
