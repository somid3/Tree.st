<%@ include file="../../../setup.jsp" %>
<%@ include file="../../../auth.jsp" %>
<%
    String newPassword1 = StringUtils.parseString(request.getParameter("new_pass1"));
    String newPassword2 = StringUtils.parseString(request.getParameter("new_pass2"));
    String password = StringUtils.parseString(request.getParameter("pass"));

    StringBuilder buf = new StringBuilder();

    try {

        UserServices.passwordChange(meId, password, newPassword1, newPassword2);

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