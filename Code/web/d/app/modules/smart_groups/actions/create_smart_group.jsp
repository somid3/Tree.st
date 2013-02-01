<%@ include file="../../../setup.jsp" %>
<%@ include file="../../../auth.jsp" %>
<%
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    String name = StringUtils.parseString(request.getParameter("name"));
    String description = StringUtils.parseString(request.getParameter("desc"));
    SmartGroupVisibilityEnum visibility = SmartGroupVisibilityEnum.getById(StringUtils.parseInt(request.getParameter("share")));

    StringBuilder buf = new StringBuilder();

    try {

        // Convert search to smart group
        SmartGroupServices.convertSearchToSmartGroup(
            homeId,
            smartGroupRef,
            name,
            description,
            visibility);

       } catch (UIException e) {

           // Documenting error
           buf = new StringBuilder();
           buf.append("<error>");
           buf.append(e.getMessage());
           buf.append("</error>");

       }
%>
<?xml version="1.0"?>
<create_smart_group>
   <%= buf.toString() %>
</create_smart_group>
