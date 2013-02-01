<%@ include file="../all.jsp" %>
<%@ page import="com.questy.admin.*" %>

<%
    String adminChecksum = StringUtils.parseString(request.getParameter("acs"));

    if (adminChecksum == null ||
        !adminChecksum.equals(Vars.adminChecksum)) {

        out.println("Wrong admin checksum");
        return;

    }
%>
