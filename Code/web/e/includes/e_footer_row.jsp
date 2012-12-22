<% {
    /* Inputs variables
     *
     *    List<String> e_removals = null;
     */
%>

    <% for (String remove : e_removals) { %>

        <%@ include file="./d_line_row.jsp" %>
        <tr>
            <td>
                <table border="0" cellpadding="10" cellspacing="0">
                    <tr>
                        <td>
                            <span
                                style="
                                font-family: <%= HtmlDesign.fontFamily%>;
                                font-size: 12px;
                                line-height: 1.5em;
                                color: <%= HtmlDesign.dim %>">
                                <%= remove %>
                            </span>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    <% } %>

<% } %>
