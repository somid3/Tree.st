<%@ include file="../../all.jsp"%>
<%@ include file="../includes/a_container_start.jsp"%>
<%
    Network c_network = null;
    SmartGroup c_smartGroup = null;
    String c_title = null;
%>
<%@ include file="../includes/c_header_row.jsp"%>
<tr>
    <td>

        <table width="100%" cellspacing="10">
            <tr>
                <td valign="top" align="left" width="110">
                    <img width="100" height="100" src="http://<%= Vars.domain %>/e/img/password.png">
                </td>
                <td>
                    <span
                        style="
                        font-family: <%= HtmlDesign.fontFamily%>;
                        font-size: 14px;
                        line-height: 1.3em;">

                    Dear Friend,<br/>
                    <br/>
                    You or someone else requested to update the password of a user on Tree.st with this email address.<br/>
                    <br/>
                    Unfortunately, no account exists on Tree.st with this email address.<br/>
                    <br/>
                    If you need further assistance please email us at <%= Vars.supportEmail %><br/>
                    <br/>
                    Thank you,<br/>
                    <%= Vars.supportEmailName %>
                    </span>
                </td>
            </tr>
        </table>

    </td>
</tr>

<%
    List<String> e_removals = new ArrayList<String>();
%>
<%@ include file="../includes/e_footer_row.jsp"%>
<%@ include file="../includes/b_container_end.jsp"%>