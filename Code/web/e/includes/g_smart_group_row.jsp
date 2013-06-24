<%
    /* Inputs variables
     *
     *    SmartGroup g_smartGroup = null;
     */

{
    // Create link to take user to the author's profile
    String f_hSmartGroupLink = null;
    {
        UrlQuery query = new UrlQuery();
        query.add("uid", EmailServices.TO_USER_ID);
        query.add("scs", EmailServices.TO_USER_SALT_CHECKSUM);
        query.add("gh", HashRouting.smartGroup(g_smartGroup.getNetworkId(), g_smartGroup.getSmartGroupRef()));
        f_hSmartGroupLink = "http://" + Vars.domain + "/r/go/?" + query;
    }
%>
<tr>
    <td>
        <table width="100%">
        <tr>
            <td>
                <span style="font-family: <%= HtmlDesign.fontFamily %>; font-size: 18px;">

                <%= EmailDesign.aBegin(f_hSmartGroupLink, EmailDesign.aStyleNoDeco)%>
                    <span style="color: <%= HtmlDesign.highlight6 %>"><b><%= StringUtils.concat(g_smartGroup.getName(), 30, "...") %></b></span>
                <%= EmailDesign.aEnd %>

                <span style="font-size: 14px; color: <%= HtmlDesign.dim2 %>"><i> with <%= g_smartGroup.getResultsCount()%> other members</i></span>
                </span>
            </td>
        </tr>

        <% if (!g_smartGroup.getDescription().isEmpty()) { %>
        <tr>
            <td>
                <span style="font-family: <%= HtmlDesign.fontFamily %>; color: <%= HtmlDesign.dim %>; font-size: 12px;">
                    <%= StringUtils.concat(g_smartGroup.getDescription(), 80, "...") %>
                </span>
            </td>
        </tr>
        <% } %>
        </table>
    </td>
</tr>
<% } %>
