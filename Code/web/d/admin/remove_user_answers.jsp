<%@ include file="./all.jsp" %>
<%
    Integer userId = StringUtils.parseInt(request.getParameter("uid"));

    UserServices.deleteUserAnswers(userId);
%>