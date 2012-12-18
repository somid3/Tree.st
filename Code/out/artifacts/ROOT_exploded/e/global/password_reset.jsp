<%@ include file="../../all.jsp"%>
<%
    String checksum = StringUtils.parseString(request.getParameter("xcs"));

    String hActionLink = null;
    {
        UrlQuery query = new UrlQuery();
        query.add("uid", EmailServices.TO_USER_ID);
        query.add("xcs", checksum);
        hActionLink = "http://" + Vars.domain + "/d/forgot/?" + query;
        hActionLink = HtmlUtils.createHref(hActionLink);
    }

    String hLoginLink = HtmlUtils.createHref("http://" + Vars.domain);
%>
<%@ include file="../includes/a_container_start.jsp"%>

<%
    Network c_network = null;
    SmartGroup c_smartGroup = null;
    String c_title = "Password Reset";
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
                    To set a new password for your account please click on the following link:<br/>
                    <br/>
                    <%= hActionLink %><br/>
                    <br/>
                    For security reasons, the link above is only valid for one hour only. In the future you can login by visiting:<br/>
                    <br/>
                    <%= hLoginLink %><br/>
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
    List<String> d_removals = new ArrayList<String>();
%>
<%@ include file="includes/d_footer_row.jsp"%>
<%@ include file="../includes/b_container_end.jsp"%>