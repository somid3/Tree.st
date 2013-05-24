<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%@ include file="./load.jsp" %>
<%
    // Retrieving total number of shared items
    Integer count = SharedItemDao.countByNetworkId(null, homeId);

    String hMinitipId = HtmlUtils.getRandomId();
%>

<script type="text/javascript">
    SI = new SharedItem();
    SI.networkId = <%= homeId %>;
    SI.smartGroupRef = <%= SmartGroup.ANY_SMART_GROUP_REF %>;
</script>

<% if (TooltipServices.displayMinitip(UserIntegerSettingEnum.TIP_SHARED_ITEMS_HOW, meId)) { %>
    <div class="minitip" id="<%= hMinitipId %>">
        <div class="lg_header tip">Tip:</div>
        <div class="content lg_text dim">
            Share your messages from within the most relevant
            <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.smartGroups(homeId) %>');">
                <span class="highlight6">Smart Group</span>
            </a>
            &mdash; all shared messages will appear here in the
            <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.sharedItems(homeId) %>');">
                <span class="highlight2">Shared Feed</span>
            </a>

        </div>
        <div class="close">
            <a href="#" onclick="Tooltips.closeMinitip(event, '<%= hMinitipId %>', <%= UserIntegerSettingEnum.TIP_SHARED_ITEMS_HOW.getId()%>)">
                <img src="./img/close_dark.png">
            </a>
        </div>
    </div>
<% } %>

<div id="share_canvas">
<% if (count > 0) { %>

    <script type="text/javascript">
        SI.displaySharedItems();
    </script>

<% } else {

    Integer share_e_networkId = homeId; %>

    <%@ include file="./includes/share_e_first.jsp" %>

<% } %>
</div>

