<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    Integer ul_a_networkId = homeId;
    Integer ul_a_toUserId = StringUtils.parseInt(request.getParameter("tuid"));
    UserToNetwork ul_a_meToHome = meToHome;
%>

<%@ include file="./includes/ul_a_card.jsp"%>

