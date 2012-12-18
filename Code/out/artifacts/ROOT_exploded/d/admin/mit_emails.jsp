<%@ page import="com.questy.admin.scrapper.MITEmailSender" %>
<%@ include file="./all.jsp" %>
<%
    Integer count = StringUtils.parseInt(request.getParameter("count"));

    MITEmailSender.sendEmails(count);
%>