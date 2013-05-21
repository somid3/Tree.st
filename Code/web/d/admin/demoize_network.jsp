<%@ include file="./all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));

    DemoServices.demoize(networkId);
%>