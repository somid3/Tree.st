<%
/* Inputs variables
 *
 *   String header_path = null;
 *
 */

    String header_active = "";
{ %>

<div id="outer-header" class="square">
    <div class="w800 center">
        <div id="header" class="w800">

            <a href="/">
                <div id="logo">
                    <img src="/d/assets/logo.png">
                </div>
            </a>

            <div id="contact_details">
                <a href="mailto:hello@treelift.com"><div class="smd_text highlight2">hello@treelift.com</div></a>
                <div class="smd_text dim2">1-888-682-3976</div>
            </div>

            <div id="shortcuts">

                <% header_active = header_path.equals("welcome") ? "active" : ""; %>
                <a href="/d/welcome"><div class="shortcut lg_text <%= header_active %> highlight2">Welcome</div></a>

                <% header_active = header_path.contains("features") ? "active" : ""; %>
                <a href="/d/welcome/features"><div class="shortcut lg_text <%= header_active %> highlight2">Features</div></a>

                <% header_active = header_path.contains("pricing") ? "active" : ""; %>
                <a href="/d/welcome/pricing"><div class="shortcut lg_text <%= header_active %> highlight2">Pricing</div></a>

                <% header_active = header_path.contains("start") ? "active" : ""; %>
                <a href="/d/welcome/start"><div class="shortcut lg_text <%= header_active %> highlight2">Start</div></a>
            </div>
        </div>
    </div>
</div>

<% } %>