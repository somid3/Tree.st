<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    Integer ul_a_toUserId = StringUtils.parseInt(request.getParameter("tuid"));
    Integer ul_a_networkUserLinkPointsPer = NetworkIntegerSettingEnum.USER_LINK_POINTS_PER.getValueByNetworkId(homeId);
%>

<%@ include file="./includes/ul_a_card.jsp"%>

