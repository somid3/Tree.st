<%@ include file="../../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer sgr = StringUtils.parseInt(request.getParameter("sgr"));

    // Update search smart group
    SmartGroupServices.hideSmartGroup(networkId, sgr, userId);

%>