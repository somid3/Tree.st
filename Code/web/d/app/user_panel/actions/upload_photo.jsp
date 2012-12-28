<%@ include file="../../all.jsp"%>
<%
    Integer ref = AppResourceServices.saveTemporaryFace(userId, request);
%>
<upload_photo ref="<%= ref %>" />
