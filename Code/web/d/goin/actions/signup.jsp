<%@ include file="../all.jsp" %>
<%
    Thread.sleep(1000);

    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    String networkChecksum = StringUtils.parseString(request.getParameter("ncs"));

    String email = StringUtils.parseString(request.getParameter("e"));
    String passwordText = StringUtils.parseString(request.getParameter("p"));
    String name = StringUtils.parseString(request.getParameter("n"));
    String cardToken = StringUtils.parseString(request.getParameter("ct"));

    StringBuilder buf = new StringBuilder();

    try {

        buf.append(UserWebServices.signup(webUtils, networkId, networkChecksum, email, passwordText, name, cardToken));

    } catch (UIException e) {

        buf = new StringBuilder();
        buf.append("<error>");
        buf.append(e.getMessage());
        buf.append("</error>");

    }

%>
<?xml version="1.0"?>
<signup>
    <%= buf.toString() %>
</signup>