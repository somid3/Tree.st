<%@ include file="../../../all.jsp" %>
<%
    String firstName = StringUtils.parseString(request.getParameter("fname"));
    String lastName = StringUtils.parseString(request.getParameter("lname"));
    String password = StringUtils.parseString(request.getParameter("pass"));

    WebUtils webUtils = new WebUtils(request, response);

    StringBuilder buf = new StringBuilder();

    try {

        UserServices.nameChange(userId, password, firstName, lastName);

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