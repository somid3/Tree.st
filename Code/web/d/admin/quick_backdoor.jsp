<%@ include file="./all.jsp"%>

<%

    String error = "";
    String done = "";
    String action = StringUtils.parseString(request.getParameter("action"));

    // Do we need to add a demo user to a network?
    if(action != null && action.equals("add_to_network")) {

        // Retrieve parameters
        Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
        Integer userId = StringUtils.parseInt(request.getParameter("uid"));

        // Validate parameters
        Network network = NetworkDao.getById(null, networkId);
        if (network == null)
            error = "Network does not exist";

        User user = UserDao.getById(null, userId);
        if (user == null)
            error = "User does not exist";

        // Adding user to network, and all its dependencies
        if (error.isEmpty()) {
            NetworkServices.addUserToNetworkWithDependencies(network.getId(), user.getId(), RoleEnum.MEMBER);
            done = "User " + user.getName() + " added to network " + network.getName();
        }
    }

%>


<html>
<head>
    <title></title>
</head>
<body>


<% if (!error.isEmpty()) {%>
<div style="padding: 20px; margin-bottom: 10px; background-color: orangered; color: white;">
    <%= error %>
</div>
<% } %>


<% if (!done.isEmpty()) {%>
<div style="padding: 20px; margin-bottom: 10px; background-color: green; color: white;">
    <%= done %>
</div>
<% } %>


<%
    User user = null;
    for (Integer demoUserId : DemoServices.demoUsersIds) {
        user = UserDao.getById(null, demoUserId);
%>

    <div>
        <div>
            <a href="./backdoor.jsp?acs=<%= Vars.adminChecksum %>&uid=<%= demoUserId %>">
                <img src="<%= user.getFaceUrl() %>">
                <%= user.getName() %>
            </a>
            (<%= user.getId() %>)
        </div>

        <div>
            <form action="./quick_backdoor.jsp?acs=<%= Vars.adminChecksum%>" method="post">
                Add to network id: <input type="text" name="nid" placeholder="1234">
                <input type="hidden" name="uid" value="<%= user.getId() %>">
                <input type="hidden" name="action" value="add_to_network">
                <input type="submit" value="add">
            </form>
        </div>
    </div>

    <br/>
    <br/>

<% } %>


</body>
</html>