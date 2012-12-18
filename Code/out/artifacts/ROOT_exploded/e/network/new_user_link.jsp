<%@ include file="../../all.jsp"%>
<%
    NetworkEventEnum event = NetworkEventEnum.getByValue(StringUtils.parseInt(request.getParameter("ne")));
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer fromUserId = StringUtils.parseInt(request.getParameter("fuid"));

    // Retrieving from user
    User fromUser = UserDao.getById(null, fromUserId);

    // Retrieving network
    Network network = NetworkDao.getById(null, networkId);

    // Creating link to view user who created user link
    String hAuthorProfileLink = null;
    {
        UrlQuery query = new UrlQuery();
        query.add("uid", EmailServices.TO_USER_ID);
        query.add("scs", EmailServices.TO_USER_SALT_CHECKSUM);
        query.add("vuid", fromUser.getId());
        query.add("nid", network.getId());
        hAuthorProfileLink = "http://" + Vars.domain + "/r/go/?" + query;
    }
%>
<%@ include file="../includes/a_container_start.jsp"%>

<%
    Network c_network = network;
    SmartGroup c_smartGroup = null;
    String c_title = null;
%>
<%@ include file="../includes/c_header_row.jsp"%>
<tr>
    <td>

        <table width="100%" cellspacing="10">
            <tr>
                <td valign="top" align="left" width="110">
                    <%= EmailDesign.aBegin(hAuthorProfileLink)%>
                        <img width="100" height="100" src="http://<%= Vars.domain %>/<%= fromUser.getFaceUrl() %>">
                    <%= EmailDesign.aEnd %>
                </td>
                <td>
                    <span
                        style="
                        font-family: <%= HtmlDesign.fontFamily%>;
                        font-size: 14px;
                        line-height: 1.3em;">

                    Dear <%= EmailServices.TO_USER_FIRST_NAME %>,<br/>
                    <br/>
                    <%= fromUser.getFirstName() %> from "<%= network.getName() %>" just viewed you!<br/>
                    <br/>
                    Cheers!<br/>
                    <%= Vars.supportEmailName %>
                    </span>
                </td>
            </tr>
        </table>

    </td>
</tr>

<%
    Network e_network = network;
    SmartGroup e_smartGroup = null;
    NetworkEventEnum e_event = event;
%>
<%@ include file="./includes/e_footer_row.jsp"%>
<%@ include file="../includes/b_container_end.jsp"%>