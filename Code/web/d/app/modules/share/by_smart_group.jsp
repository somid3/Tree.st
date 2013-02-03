<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%@ include file="load.jsp" %>
<%
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));

    // Retrieving smart group
    SmartGroup smartGroup = SmartGroupDao.getByNetworkIdAndRef(null, homeId, smartGroupRef);

    // Retrieving total number of shared items
    Integer count = SharedItemDao.countByNetworkIdAndSmartGroupRefAndCreatedAfter(null, homeId, smartGroupRef, DateUtils.BEGINNING_OF_TIME);

    // Retrieving network settings
    Integer pointsPerSharedItem = NetworkIntegerSettingEnum.SHARED_ITEM_POINTS_PER.getValueByNetworkId(homeId);

    String hMinitipId = HtmlUtils.getRandomId();
%>

<script type="text/javascript">
    SI = new SharedItem();
    SI.networkId = <%= homeId %>;
    SI.smartGroupRef = <%= smartGroupRef %>;
    SI.hTellMeId = "tell_me";
</script>

<div id="share_something">

    <div class="arrow_box sm_glow">
        <textarea id="tell_me" placeholder="Share your thoughts" class="md_input"></textarea>
    </div>

    <div class="share_tools">

        <img src="<%= me.getFaceUrl() %>" class="me" alt="">

        <a href="#" onclick="SI.addSharedItem(event)">
            <div class="share_it lg_button submit_button">
            Share
            <% if (pointsPerSharedItem != 0) { %>
                <span class="sm_text">(<%= pointsPerSharedItem %> pts.)</span>
            <% } %>
            </div>
        </a>
        <div class="share_error smd_text white"></div>
        <div class="share_loading"><img src="./img/sm_loading.gif"></div>
    </div>

</div>

<div id="share_canvas">
<% if (count > 0) { %>

    <script type="text/javascript">
        SI.displaySharedItems();
    </script>

<% } else {

    String app_a_message = "Be the first to share a message!";
    boolean app_a_withCanvasContainer = true;  %>

    <%@ include file="../../includes/app_a_mini_message.jsp" %>

<% } %>
</div>