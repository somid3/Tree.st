<%@ include file="../../../all.jsp"%>
<%
    Integer ref = StringUtils.parseInt(request.getParameter("r"));
    String resourceChecksum = StringUtils.parseString(request.getParameter("rcs"));

    // Attempting to hide face
    AppResourceServices.hideByUserIdAndAppAndTypeAndRefAndChecksum(userId, AppEnum.FACES, AppResourceTypeEnum.FACE, ref, resourceChecksum);
%>
