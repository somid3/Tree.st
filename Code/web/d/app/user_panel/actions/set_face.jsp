<%@ include file="../../../all.jsp"%>
<%
    Integer ref = StringUtils.parseInt(request.getParameter("r"));
    String checksum = StringUtils.parseString(request.getParameter("cs"));

    Integer x1 = StringUtils.parseInt(request.getParameter("x1"));
    Integer y1 = StringUtils.parseInt(request.getParameter("y1"));
    Integer width = StringUtils.parseInt(request.getParameter("w"));
    Integer height = StringUtils.parseInt(request.getParameter("h"));

    AppResourceServices.setFace(userId, ref, checksum, x1, y1, width, height);
%>
