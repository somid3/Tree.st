<%@ include file="../../all.jsp" %>
<%@ include file="load.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));

    User me = UserDao.getById(null, userId);

    // Retrieving smart group
    SmartGroup smartGroup = SmartGroupDao.getByNetworkIdAndRef(null, networkId, smartGroupRef);

    // Retrieving total number of shared items
    Integer count = SharedItemDao.countByNetworkIdAndSmartGroupRefAndCreatedAfter(null, networkId, smartGroupRef, DateUtils.BEGINNING_OF_TIME);

    // People in the smart group to whom a shared item would be visible...
    List<SmartGroupResult> others = SmartGroupResultDao.getByNetworkIdAndRef(null, networkId, smartGroupRef, new SqlLimit(0, 6));

    // Retrieving points per shared item
    Integer pointsPerSharedItem = NetworkIntegerSettingEnum.SHARED_ITEM_POINTS_PER.getValueByNetworkId(networkId);

    Integer moreGroupMembers = null;

    String hErrorId = HtmlUtils.getRandomId();
%>

<script type="text/javascript">
    SI = new SharedItem();
    SI.networkId = <%= networkId %>;
    SI.smartGroupRef = <%= smartGroupRef %>;
    SI.hTellMeId = "tell_me";
    SI.hErrorId = '<%= hErrorId %>'
</script>

<div id="share_something">

    <div class="arrow_box sm_glow">
        <textarea id="tell_me" placeholder="Share your thoughts" class="md_input"></textarea>
    </div>

    <div class="share_population">
        <img src="<%= me.getFaceUrl() %>" class="me" alt="">

        <% if (smartGroup.getResultsCount() > 0) { %>
        <div class="not_me">
            <div class="share_with sm_text dim">share with</div>
            <div class="share_others">

                <% for (SmartGroupResult other : others ) {

                    AnswerVisibilityEnum ul_b_lowestVisibility = other.getLowestVisibility();
                    Integer ul_b_networkId = networkId;
                    Integer ul_b_toUserId = other.getUserId(); %>

                    <%@ include file="../user_links/includes/ul_b_face.jsp"%>

                <% }

                    // Calculating how many more other people are there
                    moreGroupMembers = smartGroup.getResultsCount() - others.size(); %>

                    <div class="more sm_text dim">

                        <% if (moreGroupMembers > 0) { %>
                        ... and <%= moreGroupMembers %> more members
                        <% } %>

                    </div>
            </div>
        </div>
        <% } %>

    </div>


    <div class="share_tools">
        <a href="#" onclick="SI.addSharedItem(event)">
            <div class="share_it lg_button submit_button">
            Share
            <% if (pointsPerSharedItem != 0) { %>
                <span class="sm_text">(<%= pointsPerSharedItem %> pts.)</span>
            <% } %>
            </div>
        </a>
        <div id="<%= hErrorId %>" class="share_error smd_text white"></div>
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

<script type="text/javascript">

    // Allows the share something text area to expand
    $("#" + SI.hTellMeId).TextAreaExpander(30, 200);

</script>