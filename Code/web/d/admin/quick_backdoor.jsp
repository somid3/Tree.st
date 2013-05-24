<%@ include file="./all.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>

<% for (Integer demoUserId : DemoServices.demoUsersIds) { %>

    <a href="./backdoor.jsp?acs=<%= Vars.adminChecksum %>&uid=<%= demoUserId %>"><%= demoUserId %></a> </br> </br>

<% } %>


</body>
</html>