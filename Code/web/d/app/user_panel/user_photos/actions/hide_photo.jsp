<%@ include file="../../../setup.jsp"%>
<%@ include file="../../../auth.jsp"%>
<%
    Integer resourceRef = StringUtils.parseInt(request.getParameter("rr"));
    String resourceChecksum = StringUtils.parseString(request.getParameter("rcs"));

    // Attempting to hide face
    AppResourceServices.hideByUserIdAndAppAndTypeAndRefAndChecksum(meId, AppEnum.FACES, AppResourceTypeEnum.FACE, resourceRef, resourceChecksum);
%>
