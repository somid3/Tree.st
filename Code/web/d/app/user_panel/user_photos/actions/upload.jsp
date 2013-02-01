<%@ include file="../../../setup.jsp"%>
<%@ include file="../../../auth.jsp"%>
<%
    Integer ref = AppResourceServices.saveTemporaryFace(meId, request);

    webUtils.redirect("/d/app/" + HashRouting.settingsPhotoSetFace());
%>
