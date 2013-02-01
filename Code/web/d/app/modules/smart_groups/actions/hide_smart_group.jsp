<%@ include file="../../../setup.jsp" %>
<%@ include file="../../../auth.jsp" %>
<%
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));

    // Update search smart group
    SmartGroupServices.hideSmartGroup(homeId, smartGroupRef, meId);

%>