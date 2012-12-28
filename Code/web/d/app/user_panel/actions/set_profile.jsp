<%@ include file="../../all.jsp"%>
<%
    Integer ref = StringUtils.parseInt(request.getParameter("r"));
    String checksum = StringUtils.parseString(request.getParameter("rcs"));

    // Saving new face resource to database
    AppResource faceResource = AppResourceDao.getByUserIdAndAppAndTypeAndRefAndChecksum(null, userId, AppEnum.FACES, AppResourceTypeEnum.FACE, ref, checksum);

    // Updating user to have the correct face ref
    UserDao.updateFaceByUserId(null, userId, ref, faceResource.getUrl());
%>
