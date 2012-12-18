<%@ include file="../../../all.jsp"%>
<%
    Integer ref = StringUtils.parseInt(request.getParameter("r"));
    String checksum = StringUtils.parseString(request.getParameter("cs"));

    // Attempting to hide face
    AppResourceServices.hideByUserIdAndAppAndTypeAndRefAndChecksum(userId, AppEnum.FACES, AppResourceTypeEnum.FACE, ref, checksum);
%>
