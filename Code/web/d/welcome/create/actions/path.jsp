<%@ include file="../all.jsp" %>
<%
    String path = StringUtils.parseString(request.getParameter("path"));

    StringBuilder buf = new StringBuilder();

    try {

        NetworkServices.testNewPath(path);

    } catch (UIException e) {

        // Documenting error
        buf = new StringBuilder();
        buf.append("<error>");
        buf.append(e.getMessage());
        buf.append("</error>");

    }
%>
<?xml version="1.0"?>
<login>
    <%= buf.toString() %>
</login>