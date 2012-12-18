<%
    /* Inputs variables
     *
     *    Network c_network = null;
     *    SmartGroup c_smartGroup = null;
     *    String c_title = null;
     */

{
    String c_hTreeLink = "http://" + Vars.domain;
    String c_hNetworkLink = null;
    String c_hSmartGroupLink = null;

    UrlQuery c_query = new UrlQuery();

    if (c_network != null) {
        c_query.add("uid", EmailServices.TO_USER_ID);
        c_query.add("scs", EmailServices.TO_USER_SALT_CHECKSUM);
        c_query.add("nid", c_network.getId());
        c_hNetworkLink = "http://" + Vars.domain + "/r/go/?" + c_query;
    }

    if (c_smartGroup != null) {
        c_query.add("sgr", c_smartGroup.getRef());
        c_hSmartGroupLink = "http://" + Vars.domain + "/r/go/?" + c_query;
    }

%>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr>
    <td>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td
                    style="
                    border-bottom: 2px solid #ddd;">

                    <span
                        style="
                        font-family: <%= HtmlDesign.fontFamily %>;
                        font-weight: bold;
                        font-size: 20px;
                        color: <%= HtmlDesign.highlight %>">

                    <%= EmailDesign.aBegin(c_hTreeLink) %>
                        <span style="color: <%= HtmlDesign.highlight %>">
                            <%= Vars.name %>
                        </span>
                    <%= EmailDesign.aEnd %>

                    <% if (c_network != null) { %>

                        <%= EmailDesign.aBegin(c_hNetworkLink) %>
                        <span style="color: <%= HtmlDesign.dim %>">
                            / <%= StringUtils.concat(c_network.getName(), 20, "&hellip;") %>
                        </span>
                        <%= EmailDesign.aEnd %>

                    <% } %>

                    <% if (c_smartGroup != null) { %>

                        <span style="color: <%= HtmlDesign.dim %>">/</span>

                        <%= EmailDesign.aBegin(c_hSmartGroupLink) %>
                        <span style="color: <%= HtmlDesign.highlight6 %>">
                            <%= c_smartGroup.getName() %>
                        </span>
                        <%= EmailDesign.aEnd %>

                    <% } %>

                    <% if (c_title != null) { %>

                        <span
                            style="
                            font-family: <%= HtmlDesign.fontFamily %>;
                            font-weight: bold;
                            font-size: 20px;
                            color: <%= HtmlDesign.dim %>">

                            &mdash; <%= c_title %>
                        </span>

                    <% } %>

                </td>
            </tr>
        </table>
    </td>
</tr>
<% } %>
