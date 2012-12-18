<%
    /* Inputs variables
     *
     *    Network e_network = null;
     *    SmartGroup e_smartGroup = null;
     *    NetworkEventEnum e_event = event;
     */
{
%>

<tr>
    <td>

    <br/>
    <table width="100%" border="0" cellpadding="10" cellspacing="0">

        <% if (!event.isForNetworkOnly() && e_smartGroup != null) {
            String rateLinks = EmailServices.networkEventRateLinks(e_network.getId(), e_smartGroup.getRef(), e_event); %>
        <tr>
            <td
                style="
                border-top: 2px solid #ddd;">
                <span
                    style="
                    font-family: <%= HtmlDesign.fontFamily%>;
                    font-size: 12px;
                    line-height: 1.5em;
                    color: <%= HtmlDesign.dim %>">

                    To change the rate at which you receive

                    <b><%= network.getName() %> / <span style="color: <%= HtmlDesign.highlight6%>;"><%= e_smartGroup.getName() %></span></b>

                    "<b><%= event.getName() %></b>" notifications &mdash; click on your preferred interval &mdash;

                    <%= rateLinks %>

                </span>
            </td>
        </tr>
        <% } %>



        <% if (e_network != null) {
            String rateLinks = EmailServices.networkEventRateLinks(e_network.getId(), SmartGroupDao.ANY_SMART_GROUP_REF, e_event);
        %>
        <tr>
            <td
                style="
                border-top: 2px solid #ddd;">
                <span
                    style="
                    font-family: <%= HtmlDesign.fontFamily%>;
                    font-size: 12px;
                    line-height: 1.5em;
                    color: <%= HtmlDesign.dim %>">

                    To change the rate at which you receive

                    <b><%= network.getName() %></b> "<b><%= event.getName() %></b>" notifications

                    <% if(!event.isForNetworkOnly()) { %>
                        for <b>all smart groups you have starred or you're a member of</b> &mdash;
                    <% } %>

                    click on your preferred interval &mdash;

                    <%= rateLinks %>

                </span>
            </td>
        </tr>
        <% } %>

    </table>

    </td>
</tr>
</table>
<% } %>
