<%@ include file="../all.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><%= Vars.name %></title>
    <%@ include file="../../includes/google_analytics.jsp"%>
</head>

<script type="text/javascript" src="../../js/jquery-1.9.0.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="../../js/jquery-ui-1.9.2.custom.min.js?<%= Vars.rev %>"></script>
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
             <div class="sub vl_text white">Unlimited members. No contracts. Cancel anytime. No surprises.</div>
         </div>
     </div>
 </div>

 <div class="w800 center">
        <div class="package shadow">
            <div class="breakdowns">
                <div class="breakdown">
                    <div class="desc">
                        <div class="big sp_text dim">Monthly Hosting</div>
                        <div class="small smd_text dim2">To keep your community up and running smoothly</div>
                    </div>
                    <div class="gather">
                        <span class="unit lg_text dim2">$</span>
                        <span class="dollars sp_text dim">49</span>
                        <span class="cents md_text dim">99</span>
                        <div class="timeframe dim2">monthly</div>
                    </div>
                </div>

                <div class="breakdown">
                    <div class="desc">
                        <div class="big sp_text dim">One-time Setup</div>
                        <div class="small smd_text dim2">One-time payment to setup your community</div>
                    </div>
                    <div class="gather">
                        <span class="unit lg_text dim2">$</span>
                        <span class="dollars sp_text dim">149</span>
                        <span class="cents md_text dim">99</span>
                        <div class="timeframe dim2">one&mdash;time</div>
                    </div>
                </div>
            </div>
        </div>

        <a href="/d/welcome/start">
            <div class="start glow sp_header highlight">Start today!</div>
        </a>

        <div class="includes shadow">
            <div class="feature-title lg_text">Enterprise software at an affordable price. Included features:</div>
            <div class="features">
                <div class="feature smd_text">Patent-pending "People Finder" and "Smart Group" technology</div>
                <div class="feature smd_text">Message sharing at Smart Group level <span class="dim2"> &mdash; so you members can communicate</span></div>
                <div class="feature smd_text">Gamification features <span class="dim2"> &mdash; to ensure uptake and high compliance</span></div>
                <div class="feature smd_text">Up to <span class="highlight">30</span> custom filters <span class="dim2"> &mdash; that way your members can easily find one another</span></div>
                <div class="feature smd_text">Custom domain and brand support <span class="dim2"> &mdash; place your own brand on your community</span></div>
                <div class="feature smd_text">Unlimited email support <span class="dim2"> &mdash; yep, we will help you every step of the way</span></div>
                <div class="feature smd_text">Member email address domain locking <span class="dim2"> &mdash; ensure that all your members are part of your organization</span></div>
                <div class="feature smd_text">... plus 100s of features</div>
            </div>
        </div>

 </div>

<%@ include file="../../includes/footer.jsp"%>
</body>
</html>
