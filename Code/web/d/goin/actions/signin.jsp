<%@ include file="../all.jsp" %>
<%
    Thread.sleep(1000);

    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    String email = StringUtils.parseString(request.getParameter("e"));
    String passwordText = StringUtils.parseString(request.getParameter("p"));
    Boolean keep = StringUtils.parseBoolean(request.getParameter("k"));

    StringBuilder buf = new StringBuilder();

    try {

        buf.append(UserWebServices.signin(webUtils, networkId, email, passwordText, keep));

    } catch (UIException e) {

        // Documenting error
        buf = new StringBuilder();
        buf.append("<error>");
        buf.append(e.getMessage());
        buf.append("</error>");

    }
%>
<?xml version="1.0"?>
<signin>
    <%= buf.toString() %>
</signin>