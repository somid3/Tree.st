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

    if (c_network != null) {
        UrlQuery c_query = new UrlQuery();
        c_query.add("uid", EmailServices.TO_USER_ID);
        c_query.add("scs", EmailServices.TO_USER_SALT_CHECKSUM);
        c_query.add("gh", HashRouting.network(c_network.getId()));
        c_hNetworkLink = "http://" + Vars.domain + "/r/go/?" + c_query;
    }

    if (c_smartGroup != null) {
        UrlQuery c_query = new UrlQuery();
        c_query.add("uid", EmailServices.TO_USER_ID);
        c_query.add("scs", EmailServices.TO_USER_SALT_CHECKSUM);
        c_query.add("gh", HashRouting.smartGroup(c_network.getId(), c_smartGroup.getSmartGroupRef()));
        c_hSmartGroupLink = "http://" + Vars.domain + "/r/go/?" + c_query;
    }

%>

<tr>
    <td>
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <td>
                    <span
                        style="
                        font-family: <%= HtmlDesign.fontFamily %>;
                        font-weight: bold;
                        font-size: 20px;">

                    <%
                    // Display the platform name is there is no network defined
                    if (c_network == null) { %>

                        <%= EmailDesign.aBegin(c_hTreeLink, EmailDesign.aStyleNoDeco) %>
                            <span style="color: <%= HtmlDesign.highlight %>">
                                <%= Vars.name %>
                            </span>
                        <%= EmailDesign.aEnd %>

                    <% } %>

                    <% if (c_network != null) { %>

                        <%= EmailDesign.aBegin(c_hNetworkLink, EmailDesign.aStyleNoDeco) %>

                        <% if (NetworkIntegerSettingEnum.IS_UI_LOGO_SET.getValueByNetworkId(c_network.getId()) == 0) { %>

                            <span style="color: <%= HtmlDesign.dim %>">
                                <%= StringUtils.concat(c_network.getName(), 20, "&hellip;") %>
                            </span>

                        <% } else { %>

                            <img src="http://<%= Vars.domain + c_network.getLogoResourceUrl() %>">

                        <% } %>

                        <%= EmailDesign.aEnd %>

                    <% } %>

                    <% if (c_smartGroup != null) { %>

                        <span style="color: <%= HtmlDesign.dim %>">/</span>

                        <%= EmailDesign.aBegin(c_hSmartGroupLink, EmailDesign.aStyleNoDeco) %>
                        <span style="color: <%= HtmlDesign.highlight6 %>">
                            <%= c_smartGroup.getName() %>
                        </span>
                        <%= EmailDesign.aEnd %>

                    <% } %>

                    <% if (c_title != null) { %>

                        <span style=" color: <%= HtmlDesign.dim %>">
                            &mdash; <%= c_title %>
                        </span>

                    <% } %>

                    </span>

                </td>
            </tr>
        </table>
    </td>
</tr>
<%@ include file="d_line_row.jsp"%>

<% } %>
