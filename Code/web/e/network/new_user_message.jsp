<%@ include file="../../all.jsp"%>
<%
    Integer fromUserId = StringUtils.parseInt(request.getParameter("fuid"));
    Integer toUserId = StringUtils.parseInt(request.getParameter("tuid"));
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));

    // Retrieving from user
    User fromUser = UserDao.getById(null, fromUserId);

    // Retrieving network
    Network network = NetworkDao.getById(null, networkId);

    // Creating link to view user who created user link
    String hUserMessageLink = null;
    {
        UrlQuery query = new UrlQuery();
        query.add("uid", EmailServices.TO_USER_ID);
        query.add("scs", EmailServices.TO_USER_SALT_CHECKSUM);
        query.add("gh", HashRouting.profileMessages(network.getId(), toUserId));
        hUserMessageLink = "http://" + Vars.domain + "/r/go/?" + query;
    }

    // Retrieving message sent
    UserMessageGroup messageGroup = UserMessageGroupDao.getByNetworkIdAndFromUserIdAndToUserId(null, networkId, fromUserId, toUserId);

    // Replacing new lines with html new lines
    String quote = StringUtils.concat(messageGroup.getSummary(), 100, "...");
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
                    <%= EmailDesign.aBegin(hUserMessageLink)%>
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
                        <%= fromUser.getFirstName() %> from "<%= network.getName() %>" just sent you a message!<br/>
                        <br/>

                        <span
                            style="
                            font-family: <%= HtmlDesign.fontFamilyQuote%>;
                            font-size: 18px;
                            line-height: 1.3em;
                            font-style: italic">

                        "<%= quote %>"
                        </span>
                    </span>
                </td>
            </tr>
            <tr>
                <td></td>
                <td valign="top" align="center">
                    <br/>
                    <%= EmailDesign.aBegin(hUserMessageLink) %>
                        <%= EmailDesign.spanButtonBegin() %>

                            View your messages

                        <%= EmailDesign.spanEnd %>
                    <%= EmailDesign.aEnd%>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <br/>
                    Best,<br/>
                    <%= network.getName() %>
                    </span>
                </td>
            </tr>
        </table>

    </td>
</tr>

<%
    UrlQuery parameters = new UrlQuery();
    parameters.add("nid", networkId);
    String unsubscribeLink = HtmlUtils.createHref("Unsubscribe", EmailServices.helperCreateActionUrl(EmailActionEnum.UNSUBSCRIBE_FROM_NEW_USER_MESSAGE_NOTIFICATIONS, parameters));

    List<String> e_removals = new ArrayList<String>();
    e_removals.add(unsubscribeLink + " from 'new message' notifications at " + StringUtils.concat(network.getName(), 15, "..."));
%>
<%@ include file="../includes/e_footer_row.jsp"%>
<%@ include file="../includes/b_container_end.jsp"%>