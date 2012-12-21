<%@ include file="../../all.jsp"%>
<%

    String hActionLink = null;
    {
        UrlQuery query = new UrlQuery();
        query.add("uid", EmailServices.TO_USER_ID);
        query.add("scs", EmailServices.TO_USER_SALT_CHECKSUM);
        hActionLink = "http://" + Vars.domain + "/r/photo/?" + query;
        hActionLink = HtmlUtils.createHref(hActionLink);
    }

    String hLoginLink = HtmlUtils.createHref("http://" + Vars.domain);
%>
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
                    <img width="100" height="100" src="http://<%= Vars.domain %>/e/img/photo.png">
                </td>
                <td>
                    <span
                        style="
                        font-family: <%= HtmlDesign.fontFamily%>;
                        font-size: 14px;
                        line-height: 1.3em;">

                    Dear <%= EmailServices.TO_USER_FIRST_NAME %>,<br/>
                    <br/>
                    Your community can better collaborate with you when they can see you! Please upload your photo by clicking on the link below.<br/>
                    <br/>
                    <%= hActionLink %><br/>
                    <br/>
                    Or, reply to this email with your photo and we will take care of the rest.<br/>
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
    String unsubscribeLink = HtmlUtils.createHref(
        "Unsubscribe",
        EmailServices.createActionUrl(EmailActionEnum.UNSUBSCRIBE_FROM_FIRST_PHOTO_UPLOAD_EMAILS, null));

    List<String> d_removals = new ArrayList<String>();
    d_removals.add(unsubscribeLink + " from 'photo upload' reminders");
%>
<%@ include file="includes/d_footer_row.jsp"%>
<%@ include file="../includes/b_container_end.jsp"%>