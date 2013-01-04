<%@ page import="com.questy.admin.marketing.MITEmailSender" %>
<%@ include file="./all.jsp" %>
<%
    Integer count = StringUtils.parseInt(request.getParameter("count"));

    MITEmailSender.sendEmails(count);
%>