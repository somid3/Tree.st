<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    Integer toUserId = StringUtils.parseInt(request.getParameter("tuid"));

    UserToNetwork ul_c_userToNetwork = NetworkServices.toggleRemove(homeId, toUserId);

%>
<%@ include file="./includes/ul_c_remove.jsp" %>
