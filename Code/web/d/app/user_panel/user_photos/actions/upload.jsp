<%@ include file="../../../setup.jsp"%>
<%@ include file="../../../auth.jsp"%>
<%
    try {

        AppResourceServices.saveTemporaryFace(meId, request);

    } catch (Exception e) {

        webUtils.redirect("/d/app/" + HashRouting.settingsPhotoUpload());

    }

    // Recent face was uploaded and saved, redirect to select face
    webUtils.redirect("/d/app/" + HashRouting.settingsPhotoSetFace());
%>
