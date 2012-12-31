<%@ include file="../../../all.jsp"%>
<%
    Integer resourceRef = StringUtils.parseInt(request.getParameter("rr"));
    String resourceChecksum = StringUtils.parseString(request.getParameter("rcs"));

    // Attempting to hide face
    AppResourceServices.hideByUserIdAndAppAndTypeAndRefAndChecksum(userId, AppEnum.FACES, AppResourceTypeEnum.FACE, resourceRef, resourceChecksum);
%>
