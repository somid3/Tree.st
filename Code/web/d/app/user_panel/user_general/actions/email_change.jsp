<%@ include file="../../../all.jsp" %>
<%
    String newEmail1 = StringUtils.parseString(request.getParameter("email1"));
    String newEmail2 = StringUtils.parseString(request.getParameter("email2"));
    String password = StringUtils.parseString(request.getParameter("pass"));

    StringBuilder buf = new StringBuilder();

    try {

        UserServices.emailChange(meId, password, newEmail1, newEmail2);

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