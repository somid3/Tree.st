<%@ include file="./all.jsp" %>
<%

    /**
     * Retrieving parameters
     */
    Integer userId = StringUtils.parseInt(request.getParameter("uid"));
    String passwordChecksum = StringUtils.parseString(request.getParameter("xcs"));

    /**
     * Did the user click on the email link?
     */
    Boolean begin = true;
    User user = null;
    if (userId != null && passwordChecksum != null) {

        // Attempt to retrieve password reset object
        PasswordReset reset = PasswordResetDao.getByUserIdAndChecksum(null, userId, passwordChecksum);

        // Validating reset
        if (reset != null) {

            // Displays the set new password form...
            begin = false;

            // Retrieving user
            user = UserDao.getById(null, userId);
        }
    }
%>
<!DOCTYPE HTML>
<html>
<head>
    <title><%= Vars.name %></title>
    <%@ include file="../includes/google_analytics.jsp"%>
</head>
<script type="text/javascript" src="../js/jquery-1.9.0.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/jquery-ui-1.9.2.custom.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/global.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="./js/forgot.js?<%= Vars.rev %>"></script>

<link rel=stylesheet type="text/css" href="../css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./css/basic.css?<%= Vars.rev %>">
<body>

<div id="logo">
    <a href="/"><img src="/d/assets/logo.png"></a>
</div>

<div id="action"></div>


<script type="text/javascript">

   <% if (begin) { %>

       $("#action").load("./renders/begin.jsp");

   <% } else { %>

       var parameters = {};
       parameters.uid = <%= userId %>;
       parameters.xcs = '<%= passwordChecksum %>';
       $("#action").load("./renders/set.jsp", parameters);

   <% }%>

</script>

</body>
</html>