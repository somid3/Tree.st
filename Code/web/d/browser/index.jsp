<%@ include file="./all.jsp" %>
<!DOCTYPE HTML>
<html>
<head>
    <title><%= Vars.name %></title>
    <%@ include file="../includes/google_analytics.jsp"%>
</head>

<link rel=stylesheet type="text/css" href="../css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="./css/basic.css?<%= Vars.rev %>">
<body>
<div id="main">
    <div id="header">
        <div id="logo">
            <a href="http://<%= Vars.domain %>"><img src="/d/assets/logo.png"></a>
        </div>
    </div>
    <div id="container">
        <div id="content">
            <div id="browser" class="vl_text white">
                <p class="note">You're awesome &mdash; but your browser is not. We take great pride in ensuring the safety and privacy of all our users, and your browser puts you at risk on <%= Vars.name %>.</p>
                <p class="note">We apologize for the inconvenience and look forward to supporting your preferred browser soon.</p>
                <p class="note">In the meantime, on the right you can see the browsers we support.</p>
            </div>
        </div>

        <div id="action">
            <div id="supported" class="md_text">
                <p class="note"><%= Vars.name %> currently supports the <b>latest</b> mobile and desktop versions of</p>

                <div>
                    <a href="http://www.google.com/chrome"><p class="note highlight2"><img src="./img/chrome.png"><br/>Chrome</p></a>
                </div>

                <div>
                    <a href="http://www.apple.com/safari/"><p class="note highlight2"><img src="./img/safari.png"><br/>Safari</p></a>
                </div>

                <div>
                    <a href="http://www.mozilla.org/"><p class="note highlight2"><img src="./img/firefox.png"><br/>Firefox</p></a>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="../includes/footer.jsp"%>
</body>
</html>