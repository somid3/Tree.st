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
             <div class="main gg_header white">Packages & Pricing</div>
             <div class="sub vl_text white">No-contracts, cancel anytime, no surprises.</div>
         </div>
     </div>
 </div>

 <div class="w800 center">
     <div class="statement">
         <div class="sub md_text dim">All accounts require a one-time $99 dollar setup fee.</div>
     </div>
 </div>

 <div class="w800 center">
     <div class="packages">

         <div class="package package1 glow4">

            <div class="top" allign="center">
                <div class="title dim">Basic</div>
                <div class="price">$99 per month</div>
            </div>

            <div class="middle">
                <div class="feature-title lg_text">Features</div>
                <div class="features">
                    <div class="feature smd_text">One custom community</div>
                    <div class="feature smd_text">Up to <span class="highlight">10</span> custom qualities</div>
                    <div class="feature smd_text">Up to <span class="highlight">5K</span> members, $0.50 thereafter</div>
                    <div class="feature smd_text">Smart Group module</div>
                    <div class="feature smd_text">Message and email digest functionality</div>
                    <div class="feature smd_text">Gamification features</div>

                    <div class="non-feature smd_text">Custom domain support</div>
                </div>

            </div>

             <a href="/d/welcome/#request">
                 <div class="bottom lg_header highlight">Start today!</div>
             </a>
        </div>

        <div class="package package2 glow4">

            <div class="top">
                <div class="title dim">Deluxe</div>
                <div class="price dim">$499 per month</div>
            </div>

            <div class="middle">
                <div class="feature-title lg_text">Extra Features</div>
                <div class="features">
                     <div class="feature smd_text">All "Basic" features</div>
                     <div class="feature smd_text">Up to <span class="highlight">5</span> nestable custom communities</div>
                     <div class="feature smd_text">Up to <span class="highlight">75</span> custom qualities</div>
                     <div class="feature smd_text">Up to <span class="highlight">15K</span> members, $0.50 thereafter</div>
                     <div class="feature smd_text">Custom domain support</div>
                     <div class="feature smd_text">Unlimited email support</div>
                     <div class="feature smd_text">Member email domain locking</div>
                </div>

            </div>

            <a href="/d/welcome/#request">
                <div class="bottom2 glow sp_header highlight">Start today!</div>
            </a>
        </div>

        <div class="package package3 glow4">

            <div class="top">
                <div class="title dim">Enterprise</div>
                <div class="price dim">$1,499+ per month</div>
            </div>

            <div class="middle">
                <div class="feature-title lg_text">Extra Extra Features</div>
                <div class="features">
                     <div class="feature smd_text">All "Deluxe" features</div>
                     <div class="feature smd_text">Custom reporting, with +5 hours of consulting services</div>
                     <div class="feature smd_text">Up to <span class="highlight">50K</span> members, $0.50 thereafter</div>
                     <div class="feature smd_text"><span class="md_header highlight2">25</span> nested communities</div>
                     <div class="feature smd_text"><span class="md_header highlight2">500</span> custom qualities</div>
                     <div class="feature smd_text">Unlimited phone support</div>
                     <div class="feature smd_text">On-premise training</div>
                </div>

            </div>
            <a href="/d/welcome/#request">
                <div class="bottom lg_header highlight">Start today!</div>
            </a>
        </div>

     </div>
 </div>

<%@ include file="../includes/footer.jsp"%>
</body>
</html>
