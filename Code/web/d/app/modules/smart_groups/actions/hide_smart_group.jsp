<%@ include file="../../../all.jsp" %>
<%
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));

    // Update search smart group
    SmartGroupServices.hideSmartGroup(homeId, smartGroupRef, meId);

%>