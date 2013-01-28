<%@ include file="../all.jsp" %>
<%
    Integer userId = StringUtils.parseInt(request.getParameter("uid"));
    String passwordChecksum = StringUtils.parseString(request.getParameter("xcs"));

    String passwordText = StringUtils.parseString(request.getParameter("p"));
    String passwordTextAgain = StringUtils.parseString(request.getParameter("pa"));

    WebUtils webUtils = new WebUtils(request, response);

    StringBuilder buf = new StringBuilder();

    try {

        buf.append(UserWebServices.forgotSetPassword(webUtils, userId, passwordChecksum, passwordText, passwordTextAgain));

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