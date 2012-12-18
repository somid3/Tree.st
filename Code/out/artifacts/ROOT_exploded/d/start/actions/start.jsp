<%@ include file="../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    String networkChecksum = StringUtils.parseString(request.getParameter("ncs"));

    String email = StringUtils.parseString(request.getParameter("e"));
    String passwordText = StringUtils.parseString(request.getParameter("p"));
    String first = StringUtils.parseString(request.getParameter("f"));
    String last = StringUtils.parseString(request.getParameter("l"));

    WebUtils wu = new WebUtils(request, response);

    StringBuilder buf = new StringBuilder();

    try {

        buf.append(UserWebServices.addUser(wu, networkId, networkChecksum, email, passwordText, first, last));

    } catch (UIException e) {

        buf = new StringBuilder();
        buf.append("<error>");
        buf.append(e.getMessage());
        buf.append("</error>");

    }

%>
<?xml version="1.0"?>
<start>
    <%= buf.toString() %>
</start>