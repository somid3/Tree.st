<%@ include file="../all.jsp" %>
<%
    WebUtils wu = new WebUtils(request, response);
%>
<!DOCTYPE HTML>
<html>
<head>
    <title><%= Vars.name %></title>
    <%@ include file="../../includes/google_analytics.jsp"%>
</head>

<script type="text/javascript" src="../../js/jquery-1.7.1.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../../js/jquery-ui-1.8.18.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../../js/global.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../js/welcome.js?<%= Vars.rev %>"></script>

<link rel=stylesheet type="text/css" href="../../css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./../css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./css/pricing.css">

<body>
 <%@ include file="../../includes/browser_check.jsp"%>
 <% String header_path = "pricing.css"; %>
 <%@ include file="../includes/header.jsp"%>
 <div class="main-block">
     <div class="w800 center">
         <div class="block">
             <div class="main gg_header white">Pricing</div>
             <div class="sub lg_text white">We are cheap and affordable!</div>
         </div>
     </div>
 </div>
 <br>
 <div class="w800 center">
     <div class="price-box">
        <div class="price-left left-box-shadow">
            <div class="top" allign="center">
                <div class="title">Premium</div>
                <div class="price">99/Month</div>
            </div>
            <div class="middle">Features</div>
            <div class="bottom">
                <div class="signup">Signup Today!</div>
            </div>
        </div>
        <div class="price-middle middle-box-shadow">
            <div class="top">
                <div class="title">Deluxe</div>
                <div class="price">50/Month</div>
            </div>
            <div class="middle">Features</div>
            <div class="bottom">
                <div class="signup">Signup Today!</div>
            </div>
        </div>
        <div class="price-right right-box-shadow">
            <div class="top">
                <div class="title">Standard</div>
                <div class="price">35/Month</div>
            </div>
            <div class="middle">Features</div>
            <div class="bottom">
                <div class="signup">Signup Today!</div>
            </div>
        </div>
     </div>
 </div>

</body>
</html>