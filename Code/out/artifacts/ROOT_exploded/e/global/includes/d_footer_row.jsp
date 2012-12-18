<%
    /* Inputs variables
     *
     *    List<String> d_removals = null;
     */
{
%>

<tr>
    <td>

    <table width="100%" border="0" cellpadding="10" cellspacing="0">

        <% for (String remove : d_removals) { %>
        <tr>

            <td
                style="
                border-top: 2px solid #ddd;">
                <span
                    style="
                    font-family: <%= HtmlDesign.fontFamily%>;
                    font-size: 12px;
                    line-height: 1.3em;
                    color: <%= HtmlDesign.dim %>">

                    <%= remove %>

                </span>

            </td>

        </tr>
        <% } %>

    </table>

    </td>
</tr>
</table>
<% } %>
