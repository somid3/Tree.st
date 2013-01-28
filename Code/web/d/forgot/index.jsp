<%@ include file="./all.jsp" %>
<%
    WebUtils wu = new WebUtils(request, response);

    Integer userId = StringUtils.parseInt(request.getParameter("uid"));
    String passwordChecksum = StringUtils.parseString(request.getParameter("xcs"));

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
<%@ include file="../includes/browser_check.jsp"%>
<div id="main" class="w800">
    <div id="header">
        <div id="logo">
            <span class="sp_header highlight"><%= Vars.name %></span>
        </div>
    </div>
    <div id="container">
        <div id="content">

            <div id="messages">
                <div id="welcome" class="sp_header"></div>
            </div>

            <script type="text/javascript">
                <% if (begin) { %>
                    Animations.wordByWord("There are lots of hackers out there, make sure your password is hard to guess &mdash; but easy enough for you to remember.", "#welcome");
                <% } else { %>
                    Animations.wordByWord("Hello <%= user.getFirstName() %>, please enter your new password twice on the right. Once you do so you will be logged in.", "#welcome");
                <% } %>
            </script>

        </div>

        <div id="action"></div>

    </div>

</div>

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


<%@ include file="../includes/footer.jsp"%>
</body>
</html>