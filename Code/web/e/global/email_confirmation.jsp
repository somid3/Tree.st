<%@ include file="../../all.jsp"%>
<%
    Integer userId = StringUtils.parseInt(request.getParameter("uid"));

    // Retrieve email confirmation checksum
    String emailChecksum = UserAlphaSettingEnum.EMAIL_CONFIRMATION_CHECKSUM.getValueByUserId(userId);

    String hConfirmLink = null;
    {
        UrlQuery query = new UrlQuery();
        query.add("uid", EmailServices.TO_USER_ID);
        query.add("xcs", emailChecksum);
        hConfirmLink = "http://" + Vars.domain + "/r/confirm/?" + query;
        hConfirmLink = HtmlUtils.createHref(hConfirmLink);
    }
%>
<%@ include file="../includes/a_container_start.jsp"%>

<%
    Network c_network = null;
    SmartGroup c_smartGroup = null;
    String c_title = "Email Confirmation";
%>
<%@ include file="../includes/c_header_row.jsp"%>
<tr>
    <td>

        <table width="100%" cellspacing="10">
            <tr>
                <td valign="top" align="left" width="110">
                    <img width="100" height="100" src="http://<%= Vars.domain %>/e/img/confirm.png">
                </td>
                <td>
                    <span
                        style="
                        font-family: <%= HtmlDesign.fontFamily%>;
                        font-size: 14px;
                        line-height: 1.3em;">

                    Dear <%= EmailServices.TO_USER_FIRST_NAME %>,<br/>
                    <br/>
                    Please click on the link below to confirm your email:<br/>
                    <br/>
                    <%= hConfirmLink %><br/>
                    <br/>
                    Best,<br/>
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