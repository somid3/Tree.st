<%@ include file="../../all.jsp" %>

<%
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    UserToSmartGroupStateEnum applyState = UserToSmartGroupStateEnum.getById(StringUtils.parseInt(request.getParameter("ts")));

    // Toggle the state the user wishes to change
    UserToSmartGroupServices.changeState(applyState, homeId, smartGroupRef, meId);

    // Variables for favorite include
    Integer sgroup_e_networkId = homeId;
    Integer sgroup_e_userId = meId;
    Integer sgroup_e_smartGroupRef = smartGroupRef;
%>
<%@ include file="includes/sgroup_e_apply_state.jsp" %>
