<%@ include file="../../all.jsp"%>
<%@ include file="../includes/a_container_start.jsp"%>

<%
    Network c_network = null;
    SmartGroup c_smartGroup = null;
    String c_title = "Password Updated";
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

                    Dear <%= EmailServices.TO_USER_FIRST_NAME %>,<br/>
                    <br/>
                    Just wanted to let you know that recently your password was updated at Tree.st<br/>
                    <br/>
                    If you were unaware of this update, contact us immediately at <%= Vars.supportEmail %><br/>
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
    List<String> d_removals = new ArrayList<String>();
%>
<%@ include file="includes/d_footer_row.jsp"%>
<%@ include file="../includes/b_container_end.jsp"%>