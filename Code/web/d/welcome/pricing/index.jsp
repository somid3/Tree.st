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

 <% String header_path = "pricing"; %>
 <%@ include file="../includes/header.jsp"%>

 <div class="main-block">
     <div class="w800 center">
         <div class="block">
             <div class="main gg_header white">Simple Prices</div>
             <div class="sub vl_text white">We grow with you. No contracts. Cancel anytime. No surprises.</div>
         </div>
     </div>
 </div>

 <div class="w800 center">
        <div class="package glow4">
            <div class="breakdowns">
                <div class="breakdown">
                    <div class="desc">
                        <div class="big sp_text dim">Per Member</div>
                        <div class="small smd_text dim2">Only a dime per month for each of your members</div>
                    </div>
                    <div class="gather">
                        <span class="unit lg_text dim2">$</span>
                        <span class="dollars sp_text dim">0</span>
                        <span class="cents md_text dim">10</span>
                        <div class="timeframe dim2">monthly</div>
                    </div>
                </div>

                <div class="breakdown">
                    <div class="desc">
                        <div class="big sp_text dim">Monthly Hosting</div>
                        <div class="small smd_text dim2">To keep your servers up and running smoothly</div>
                    </div>
                    <div class="gather">
                        <span class="unit lg_text dim2">$</span>
                        <span class="dollars sp_text dim">9</span>
                        <span class="cents md_text dim">99</span>
                        <div class="timeframe dim2">monthly</div>
                    </div>
                </div>

                <div class="breakdown">
                    <div class="desc">
                        <div class="big sp_text dim">One-time Setup</div>
                        <div class="small smd_text dim2">To build your private custom-made community with your own qualities and decision trees.</div>
                    </div>
                    <div class="gather">
                        <span class="unit lg_text dim2">$</span>
                        <span class="dollars sp_text dim">49</span>
                        <span class="cents md_text dim">99</span>
                        <div class="timeframe dim2">one&mdash;time</div>
                    </div>
                </div>
            </div>
        </div>

        <a href="/d/welcome/#request">
            <div class="start glow sp_header highlight">Start today!</div>
        </a>

        <div class="w600 center includes">
            <div class="feature-title lg_text">Enterprise software at an affordable price. Included features:</div>
            <div class="features">
                <div class="feature smd_text">Patent-pending "People Finder" and "Smart Group" technology</div>
                <div class="feature smd_text">Message sharing at Smart Group level</div>
                <div class="feature smd_text">Gamification features</div>
                <div class="feature smd_text">Social Networking features</div>
                <div class="feature smd_text">Up to <span class="highlight">5</span> nestable custom communities</div>
                <div class="feature smd_text">Up to <span class="highlight">30</span> custom qualities</div>
                <div class="feature smd_text">Custom domain support</div>
                <div class="feature smd_text">Unlimited email support</div>
                <div class="feature smd_text">Member email address domain locking</div>
                <div class="feature smd_text">... plus 100s of unique and patent-pending features</div>
            </div>
        </div>

 </div>

<%@ include file="../../includes/footer.jsp"%>
</body>
</html>
