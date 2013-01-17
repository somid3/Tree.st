<%@ include file="../../all.jsp" %>

<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    UserToSmartGroupStateEnum applyState = UserToSmartGroupStateEnum.getById(StringUtils.parseInt(request.getParameter("ts")));

    // Toggle the state the user wishes to change
    UserToSmartGroupServices.changeState(applyState, networkId, smartGroupRef, userId);

    // Variables for favorite include
    Integer sgroup_e_networkId = networkId;
    Integer sgroup_e_userId = userId;
    Integer sgroup_e_smartGroupRef = smartGroupRef;
%>
<%@ include file="includes/sgroup_e_favorite.jsp" %>
