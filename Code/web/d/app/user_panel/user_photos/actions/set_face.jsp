<%@ include file="../../../all.jsp"%>
<%
    Integer resourceRef = StringUtils.parseInt(request.getParameter("rr"));
    String resourceChecksum = StringUtils.parseString(request.getParameter("rcs"));

    Integer x1 = StringUtils.parseInt(request.getParameter("x1"));
    Integer y1 = StringUtils.parseInt(request.getParameter("y1"));
    Integer width = StringUtils.parseInt(request.getParameter("w"));
    Integer height = StringUtils.parseInt(request.getParameter("h"));

    AppResourceServices.setFace(userId, resourceRef, resourceChecksum, x1, y1, width, height);
%>
