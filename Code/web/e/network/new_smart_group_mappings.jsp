<%@ include file="../../all.jsp"%>
<%
    EmailNotificationRateEnum callingRate = EmailNotificationRateEnum.getById(StringUtils.parseInt(request.getParameter("cr")));
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer userId = StringUtils.parseInt(request.getParameter("uid"));

    // Retrieving user
    User user = UserDao.getById(null, userId);

    // Retrieving network
    Network network = NetworkDao.getById(null, networkId);

    // Retrieving smart groups user has been added to
    List<UserToSmartGroup> newMappings = UserToSmartGroupDao.getMembersByNetworkIdAndUserIdAndCreatedAfter(
            null,
            network.getId(),
            user.getId(),
            callingRate.getBoundaryDate());

    // Adding up all matching smart groups
    SmartGroup smartGroup = null;
    List<SmartGroup> smartGroups = new ArrayList<SmartGroup>();
    for (UserToSmartGroup newMapping : newMappings) {

        // Retrieving smart group while ensuring visibility is greater than private
        smartGroup = SmartGroupDao.getNonHiddenByNetworkIdAndRefAndLowestVisibility(null, network.getId(), newMapping.getSmartGroupRef(), SmartGroupVisibilityEnum.SHARED);

        // Ensure that only non-private smart groups are presented
        if (smartGroup != null)
           smartGroups.add(smartGroup);
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
    <td align="center">
        <table>
        <tr>
            <td>
                <br/>
                <span
                    style="
                    font-family: <%= HtmlDesign.fontFamily%>;
                    font-size: 14px;">
                    Based on your profile, you've been added to <%= smartGroups.size() %> new smart groups! Say hello!
                </span>
                <br/>
                <br/>
            </td>
        </tr>
        </table>
    </td>
</tr>


<% for (SmartGroup g_smartGroup : smartGroups) { %>
    <%@ include file="../includes/g_smart_group_row.jsp"%>
<% } %>

<%
    UrlQuery parameters = new UrlQuery();
    parameters.add("nid", networkId);
    String unsubscribeLink = HtmlUtils.createHref("Unsubscribe", EmailServices.helperCreateActionUrl(EmailActionEnum.UNSUBSCRIBE_FROM_NEW_SMART_GROUP_MAPPINGS, parameters));

    List<String> e_removals = new ArrayList<String>();
    e_removals.add(unsubscribeLink + " from 'smart group mapping' notifications at " + StringUtils.concat(network.getName(), 15, "..."));
%>
<%@ include file="../includes/e_footer_row.jsp"%>
<%@ include file="../includes/b_container_end.jsp"%>