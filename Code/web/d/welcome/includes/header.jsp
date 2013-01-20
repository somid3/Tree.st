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

            <div id="mini">
                <a href="mailto:<%= Vars.supportEmail %>"><span class="smd_text highlight2">Contact us</span></a>
                <span class="sm_text dim2">or read our</span>
                <a href="http://blog.tree.st"><span class="smd_text highlight2">Blog</span></a>
            </div>

            <div id="shortcuts">

                <% header_active = header_path.equals("welcome") ? "active" : ""; %>
                <a href="/d/welcome"><div class="shortcut lg_text <%= header_active %> highlight2">Welcome</div></a>

                <% header_active = header_path.contains("create") ? "active" : ""; %>
                <a href="/d/welcome/create"><div class="shortcut lg_text <%= header_active %> highlight2">Create</div></a>

                <% header_active = header_path.contains("pricing") ? "active" : ""; %>
                <a href="/d/welcome/pricing"><div class="shortcut lg_text <%= header_active %> highlight2">Pricing</div></a>

                <a href="/d/login"><div class="shortcut lg_text highlight2">Login</div></a>
            </div>
        </div>
    </div>
</div>

<% } %>