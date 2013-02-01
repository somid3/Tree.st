<%@ include file="../../../setup.jsp"%>
<%@ include file="../../../auth.jsp"%>
<%
    Integer resourceRef = StringUtils.parseInt(request.getParameter("rr"));
    String resourceChecksum = StringUtils.parseString(request.getParameter("rcs"));

    // Saving new face resource to database
    AppResource faceResource = AppResourceDao.getByUserIdAndAppAndTypeAndRefAndChecksum(null, meId, AppEnum.FACES, AppResourceTypeEnum.FACE, resourceRef, resourceChecksum);

    // Updating user to have the correct face ref
    UserDao.updateFaceByUserId(null, meId, resourceRef, faceResource.getUrl());
%>
