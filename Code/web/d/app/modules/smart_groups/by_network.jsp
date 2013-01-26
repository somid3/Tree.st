<%@ include file="../../all.jsp" %>
<%@ include file="./load.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));

    String hOfficialId = HtmlUtils.getRandomId();
    String hSharedId = HtmlUtils.getRandomId();
    String hPrivateId = HtmlUtils.getRandomId();

    String hFavoritesId = HtmlUtils.getRandomId();
    String hMatchId = HtmlUtils.getRandomId();
    String hFlaggedId = HtmlUtils.getRandomId();

    // Mini tool tip variables
    String app_d_title = null;
    String app_d_message = null;
    HtmlDesign.Positions app_d_position = null;

%>

<div id="smart_group_tools" class="canvas_container">
    <div class="keys">
        <span class="title sm_text dim2">Key: </span>
        <span class="key"><img src="./modules/smart_groups/img/tree.png" alt="Auto-matched!"></span>
        <span class="sm_text dim">Auto-matched</span>

        <%
            app_d_title = "Auto-Matched Smart Groups";
            app_d_message = "Based on your profile, you are automatically added to certain smart groups. These 'auto-matched' smart groups will display a small tree on their upper right hand corner.<br/><br/>When messages are shared in these smart groups, you will be notified. To remove yourself from an auto-matched smart group click on the blue flag.";
            app_d_position = HtmlDesign.Positions.BOTTOM;
        %>
        <%@ include file="../../includes/app_d_mini_tooltip.jsp"%>

        <span class="key"><img src="./modules/smart_groups/img/star_on.png" alt="Manually added"></span>
        <span class="sm_text dim">Manually added</span>

        <%
            app_d_title = "Manually Added Smart Groups";
            app_d_message = "If you would like to manually make yourself a member of smart group, you must favorite it by clicking on the star.<br/><br/>When you manually add yourself to a smart group you will receive a notification when a message is shared.";
            app_d_position = HtmlDesign.Positions.BOTTOM;
        %>
        <%@ include file="../../includes/app_d_mini_tooltip.jsp"%>

        <span class="key"><img src="./modules/smart_groups/img/flag_on.png" alt="Manually ignored"></span>
        <span class="sm_text dim">Manually ignored</span>

        <%
            app_d_title = "Flagged Smart Groups";
            app_d_message = "To ensure you are never a member of a particular smart group, even if you are qualified to be auto-matched, click on the blue flag.<br/><br/>Your will never receive a notification when a message is shared in a flagged smart group.";
            app_d_position = HtmlDesign.Positions.BOTTOM;
        %>
        <%@ include file="../../includes/app_d_mini_tooltip.jsp"%>

    </div>

    <span class="title sm_text dim2">Visibility:</span>
    <a href="#" onclick=""><div id="<%= hOfficialId %>" class="view sm_text light_button">Official</div></a>
    <a href="#" onclick=""><div id="<%= hSharedId %>" class="view sm_text light_button">Shared</div></a>
    <a href="#" onclick=""><div id="<%= hPrivateId %>" class="view sm_text light_button">Yours</div></a>
    <div class="sep">&nbsp;</div>
    <span class="title sm_text dim2">Display:</span>
    <a href="#" onclick=""><div id="<%= hFavoritesId %>" class="view sm_text light_button">Favorites</div></a>
    <a href="#" onclick=""><div id="<%= hMatchId %>" class="view sm_text light_button">Auto-Matched</div></a>
    <a href="#" onclick=""><div id="<%= hFlaggedId %>" class="view sm_text light_button">Flagged</div></a>

</div>

<script type="text/javascript">

    // Beginning the smart groups tools
    SGT = new SmartGroupTools();
    SGT.networkId = <%= networkId %>;
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

    $("#<%= hFlaggedId %>").click(function (event) {
        SGT.changeView(event, "<%= hFlaggedId %>", <%= SmartGroupsViewEnum.FLAGS.getId() %>);
    });

</script>

<div id="smart_groups_canvas"></div>




