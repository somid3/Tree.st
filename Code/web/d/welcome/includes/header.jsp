<%
/* Inputs variables
 *
 *   String header_path = null;
 *
 */

    String header_active = "";
{ %>

<%--<div id="status" class="w400 lg_text white">--%>
    <%--Under construction until January 5th, 2013--%>
<%--</div>--%>

<div id="outer-header" class="square">
    <div class="w800 center">
        <div id="header" class="w800">
            <a href="/">
                <div id="logo">
                    <img src="/d/assets/logo.png">
                </div>
            </a>
            <div id="shortcuts">

                <% header_active = header_path.equals("welcome") ? "active" : ""; %>
                <a href="/d/welcome"><div class="shortcut lg_text <%= header_active %> highlight2">Welcome</div></a>

                <% header_active = header_path.contains("features") ? "active" : ""; %>
                <a href="/d/welcome/features"><div class="shortcut lg_text <%= header_active %>  highlight2">Features</div></a>

                <% header_active = header_path.contains("pricing.css") ? "active" : ""; %>
                <a href="/d/welcome/pricing"><div class="shortcut lg_text <%= header_active %>  highlight2">Pricing</div></a>

                <a href="http://blog.tree.st"><div class="shortcut lg_text highlight2">Blog</div></a>

                <a href="/d/login"><div class="shortcut lg_text highlight2">Login</div></a>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    // Bringing down the logo
    $(document).ready(function() {
        Animations.inTopAndBounce("#logo", 30);
    })
</script>

<% } %>