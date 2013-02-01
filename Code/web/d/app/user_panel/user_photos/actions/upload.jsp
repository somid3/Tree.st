<%@ include file="../../../all.jsp"%>
<%
    Integer ref = AppResourceServices.saveTemporaryFace(meId, request);

    webUtils.redirect("/d/app/" + HashRouting.settingsPhotoSetFace());
%>
