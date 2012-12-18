<%@ include file="../all.jsp" %>
<%
    String email = StringUtils.parseString(request.getParameter("e"));

    StringBuilder buf = new StringBuilder();

    // Begin a new password reset process
    try {

        PasswordResetServices.begin(email);

    } catch (UIException e) {

        buf = new StringBuilder();
        buf.append("<error>");
        buf.append(e.getMessage());
        buf.append("</error>");
    }
%>
<?xml version="1.0"?>
<forgot>
    <%= buf.toString() %>
</forgot>