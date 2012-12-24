<%@ include file="../../../../all.jsp" %>
<%
    String email = StringUtils.parseString(request.getParameter("e"));
    String passwordText = StringUtils.parseString(request.getParameter("p"));
    Boolean keep = StringUtils.parseBoolean(request.getParameter("k"));

    WebUtils webUtils = new WebUtils(request, response);

    StringBuilder buf = new StringBuilder();

    try {

//        buf.append(UserWebServices.login(webUtils, email,  passwordText, keep));

    } catch (UIException e) {

        // Documenting error
        buf = new StringBuilder();
        buf.append("<error>");
        buf.append(e.getMessage());
        buf.append("</error>");

    }
%>
<?xml version="1.0"?>
<email_notifications>
    <%= buf.toString() %>
</email_notifications>