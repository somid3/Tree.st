<%@ include file="../all.jsp" %>
<%
    // Retrieve network from domain
    Network network = UrlRouter.getNetworkByDomain(webUtils);

    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    Integer sharedItemRef = StringUtils.parseInt(request.getParameter("sir"));

    // Retrieving network settings
    Map<NetworkAlphaSettingEnum, String> networkAlphaSettings = NetworkAlphaSettingEnum.getMapByNetworkId(network.getId());
    Map<NetworkIntegerSettingEnum, Integer> networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(network.getId());

    // Retrieving shared item
    SharedItem sharedItem = SharedItemDao.getByNetworkIdAndSmartGroupRefAndRef(null, network.getId(), smartGroupRef, sharedItemRef);
    List<SharedComment> sharedComments = SharedCommentDao.getByNetworkIdAndSmartGroupRefAndSharedItemRef(null, network.getId(), smartGroupRef, sharedItemRef, SqlLimit.ALL);
%>
<!DOCTYPE HTML>
<html>
<head>
    <title><%= network.getName() %></title>
    <%@ include file="/d/includes/google_analytics.jsp"%>
</head>
<script type="text/javascript" src="/d/js/jquery-1.9.0.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="/d/js/jquery-ui-1.9.2.custom.min.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="/d/js/global.js?<%= Vars.rev %>"></script>
<script type="text/javascript" src="/p/goin/js/goin.js?<%= Vars.rev %>"></script>

<link rel=stylesheet type="text/css" href="/d/css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="/p/css/basic.css?<%= Vars.rev %>">
<link rel=stylesheet type="text/css" href="/p/goin/css/basic.css?<%= Vars.rev %>">

<body style="background-color: #bbb;">

<div class="center w900">

    <div class="page square">

        <div style="padding: 20px 10px 10px 10px;">

            <%
                Network p_network = network;
                String p_section = "items";
            %>
            <%@ include file="../includes/p_navigation.jsp" %>

            <div id="canvas">

                <div class="itembox">

                    <div class="itembox-details">

                        <div class="itembox-title">
                            <span class="lg_header" style="line-height: 1.3em">
                                <%= sharedItem.getTitle() %>
                            </span>
                        </div>

                        <hr/>

                        <span class="smd_text" style="line-height: 1.3em">
                            <%= HtmlUtils.paragraphize ( HtmlUtils.linkify( sharedItem.getText() ) )%>
                        </span>

                    </div>

                    <div class="itembox-comments">

                        <span class="lg_header">
                            <%= sharedItem.getTotalComments() %> answers
                        </span>
                        <hr/>

                        <%
                            User author = null;
                            User pu_a_user = null;
                            Network pu_a_network = network;
                            for (SharedComment sharedComment : sharedComments) {

                                // Retrieving author
                                author = UserDao.getById(null, sharedComment.getUserId());

                        %>
                            <div class="commentbox">

                                <div class="commentbox-votes">
                                    <% if (sharedComment.getUpVotes() > 0) { %>
                                        <div class="commentbox-summ-count"><span class="sp_header dim"><%= sharedComment.getUpVotes() %></span></div>
                                        <div class="commentbox-summ-title"><span class="smd_text dim2">votes</span></div>
                                    <% } %>
                                </div>

                                <div class="commentbox-details">

                                    <div class="commentbox-text">
                                        <span class="smd_text dim" style="line-height: 1.3em">
                                            <%= HtmlUtils.paragraphize ( HtmlUtils.linkify( sharedComment.getText() ) )%>
                                        </span>
                                    </div>

                                    <div class="commentbox-bottom">

                                        <div class="commentbox-date">
                                            <% if (networkIntegerSettings.get(NetworkIntegerSettingEnum.SHARED_ITEM_DISPLAY_CREATED_ON) == 1) { %>
                                                <div class="sm_text dim2"><%= PrettyDate.toString(sharedItem.getCreatedOn()) %></div>
                                            <% } %>
                                        </div>

                                        <div class="commentbox-author">
                                            <% pu_a_user = author; %>
                                            <%@include file="../user/includes/pu_a_user_card.jsp" %>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="w300 square" style="float: left; position: relative; top: 20px; left: 20px;">
        <div id="goin"></div>
    </div>
</div>



<script type="text/javascript">
    var params = {};
    params.nid = <%= network.getId() %>;
    params.ncs = '<%= network.getChecksum() %>';
    Transitions.load("#goin", "../goin/renders/goin.jsp", params);
</script>

<%@ include file="../../d/includes/footer.jsp"%>
</body>
</html>