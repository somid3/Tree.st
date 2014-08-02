<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    String hOfficialId = HtmlUtils.getRandomId();
    String hSharedId = HtmlUtils.getRandomId();
    String hPrivateId = HtmlUtils.getRandomId();

    String hFavoritesId = HtmlUtils.getRandomId();
    String hMatchId = HtmlUtils.getRandomId();

    String hMinitipId = HtmlUtils.getRandomId();

    // Mini tool tip variables
    String app_d_title = null;
    String app_d_message = null;
    HtmlDesign.Positions app_d_position = null;

%>

<% if (TooltipServices.displayMinitip(UserIntegerSettingEnum.TIP_SMART_GROUPS_CREATE, meId)) {
    String vocabUserSingle = NetworkAlphaSettingEnum.VOCAB_USER_SINGULAR.getValueByNetworkId(homeId); %>

    <div class="minitip" id="<%= hMinitipId %>">
        <div class="lg_header tip">Tip:</div>
        <div class="content lg_text dim">
            To create a
            <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.smartGroups(homeId) %>');">
                <span class="highlight6">Smart Group</span>
            </a>
            use the
            <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.smartSearch(homeId) %>');">
                <span class="highlight2"><%= vocabUserSingle %> Search</span>
            </a>
            &mdash; its easy, just select some qualities and save your search as a smart group.
        </div>
        <div class="close">
            <a href="#" onclick="Tooltips.closeMinitip(event, '<%= hMinitipId %>', <%= UserIntegerSettingEnum.TIP_SMART_GROUPS_CREATE.getId()%>)">
                <img src="./img/close_dark.png">
            </a>
        </div>
    </div>
<% } %>

<div id="smart_group_tools" class="canvas_container">

    <div style="float: left;">
        <span class="title sm_text dim2">Visibility:</span>
        <a href="#" onclick=""><div id="<%= hOfficialId %>" class="view sm_text light_button">Official</div></a>
        <a href="#" onclick=""><div id="<%= hSharedId %>" class="view sm_text light_button">Shared</div></a>
        <a href="#" onclick=""><div id="<%= hPrivateId %>" class="view sm_text light_button">Yours</div></a>
    </div>

    <div style="float: right;">
        <span class="title sm_text dim2">Display:</span>
        <a href="#" onclick=""><div id="<%= hFavoritesId %>" class="view sm_text light_button">Joined</div></a>
        <a href="#" onclick=""><div id="<%= hMatchId %>" class="view sm_text light_button">Auto-Matched</div></a>
    </div>

</div>

<script type="text/javascript">

    // Beginning the smart groups tools
    SGT = new SmartGroupTools();
    SGT.networkId = <%= homeId %>;
    SGT.changeView(null, "<%= hSharedId %>", <%= SmartGroupsViewEnum.SHARED.getId() %>);

    $("#<%= hOfficialId %>").click(function (event) {
        SGT.changeView(event, "<%= hOfficialId %>", <%= SmartGroupsViewEnum.OFFICIAL.getId() %>);
    });

    $("#<%= hSharedId %>").click(function (event) {
        SGT.changeView(event, "<%= hSharedId %>", <%= SmartGroupsViewEnum.SHARED.getId() %>);
    });

    $("#<%= hPrivateId %>").click(function (event) {
        SGT.changeView(event, "<%= hPrivateId %>", <%= SmartGroupsViewEnum.YOURS.getId() %>);
    });

    $("#<%= hMatchId %>").click(function (event) {
        SGT.changeView(event, "<%= hMatchId %>", <%= SmartGroupsViewEnum.MATCHED.getId() %>);
    });

    $("#<%= hFavoritesId %>").click(function (event) {
        SGT.changeView(event, "<%= hFavoritesId %>", <%= SmartGroupsViewEnum.FAVORITES.getId() %>);
    });

</script>

<div id="smart_groups_canvas"></div>




