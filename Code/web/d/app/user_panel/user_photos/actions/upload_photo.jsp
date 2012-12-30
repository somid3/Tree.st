<%@ include file="../../../all.jsp"%>
<%
    Integer ref = AppResourceServices.saveTemporaryFace(userId, request);

    wu.redirect("/d/app/?go_face");
%>
