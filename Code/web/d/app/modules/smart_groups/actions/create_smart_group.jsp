<%@ include file="../../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    String name = StringUtils.parseString(request.getParameter("name"));
    String description = StringUtils.parseString(request.getParameter("desc"));
    SmartGroupVisibilityEnum visibility = SmartGroupVisibilityEnum.getById(StringUtils.parseInt(request.getParameter("share")));

    // Convert search to smart group
    SmartGroupServices.convertSearchToSmartGroup(
        networkId,
        smartGroupRef,
        name,
        description,
        visibility);

%>