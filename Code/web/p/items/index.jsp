<%@ include file="../all.jsp" %>
<%
    // Pagination details
    Integer pageNo = StringUtils.parseInt(request.getParameter("p"));
    if (pageNo == null || pageNo < 0)
        pageNo = 0;


    // Retrieve network from domain
    Network network = UrlRouter.getNetworkByDomain(webUtils);

    // Retrieving network settings
    Map<NetworkAlphaSettingEnum, String> networkAlphaSettings = NetworkAlphaSettingEnum.getMapByNetworkId(network.getId());
    Map<NetworkIntegerSettingEnum, Integer> networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(network.getId());

    // Retrieving shared items
    Integer itemsPerPage = 15;
    SqlLimit sqlLimit = new SqlLimit(pageNo * itemsPerPage, itemsPerPage + 1);
    List<SharedItem> sharedItems = SharedItemDao.getByNetworkId(null, network.getId(), sqlLimit);

    // Pagination links
    Boolean displayPreviousPage = false;
    Boolean displayNextPage = false;
    if (pageNo > 0) displayPreviousPage = true;
    if (sharedItems.size() > itemsPerPage) {

        // Ensuring next link appears
        displayNextPage = true;

        // Removing last index as it was to test for next page
        sharedItems.remove(sharedItems.size() - 1);
    }
    UrlQuery previousPageUrlQuery = new UrlQuery(); previousPageUrlQuery.add("p", pageNo - 1);
    UrlQuery nextPageUrlQuery = new UrlQuery(); nextPageUrlQuery.add("p", pageNo + 1);

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

    <div class="square" style="background-color: white; float: left; position: relative; width: 590px; border-right: solid 5px #888; border-left: solid 5px #888;">

        <div style="padding: 20px 10px 10px 10px;">

            <%
                Network p_network = network;
                String p_section = "items";
            %>
            <%@ include file="../includes/p_navigation.jsp" %>


            <div id="details">

                <div id="pagination">
                    <% if (displayPreviousPage) { %>
                        <div class="pagination-button" id="prev">
                            <a href="../items?<%= previousPageUrlQuery %>">
                                <div class="light_button md_button smd_text highlight2">Previous</div>
                            </a>
                        </div>
                    <% } %>

                    <% if (displayNextPage) { %>
                        <div class="pagination-button" id="next">
                            <a href="../items?<%= nextPageUrlQuery %>">
                                <div class="light_button md_button smd_text highlight2">Next</div>
                            </a>
                        </div>
                    <% } %>
                </div>
            </div>

            <div id="canvas">

                <%
                    SmartGroup smartGroup = null;
                    User author = null;
                    User pu_a_user = null;
                    Network pu_a_network = network;
                    UrlQuery itemUrlQuery = new UrlQuery();
                    UrlQuery smartGroupUrlQuery = new UrlQuery();
                    for (SharedItem sharedItem : sharedItems) {

                        // Retrieving author
                        author = UserDao.getById(null, sharedItem.getUserId());

                        // Retrieving smart group
                        smartGroup = SmartGroupDao.getByNetworkIdAndRef(null, sharedItem.getNetworkId(), sharedItem.getSmartGroupRef());

                        // Creating shared item url query
                        itemUrlQuery.add("sgr", smartGroup.getSmartGroupRef());
                        itemUrlQuery.add("sir", sharedItem.getSharedItemRef());

                        // Creating smart group url query
                        smartGroupUrlQuery.add("sgr", smartGroup.getSmartGroupRef());
                %>
                        <div class="itemline">
                            <div class="itemline-datum">
                                <div class="itemline-data">
                                    <% if (sharedItem.getTotalComments() > 0) { %>
                                        <div class="itemline-summ-count"><span class="sp_header dim"><%= sharedItem.getTotalComments() %></span></div>
                                        <div class="itemline-summ-title"><span class="smd_text dim2">replies</span></div>
                                    <% } %>
                                </div>
                                <div class="itemline-data">
                                    <% if (sharedItem.getUpVotes() > 0) { %>
                                        <div class="itemline-summ-count"><span class="sp_header dim"><%= sharedItem.getUpVotes() %></span></div>
                                        <div class="itemline-summ-title"><span class="smd_text dim2">votes</span></div>
                                    <% } %>
                                </div>
                            </div>

                            <div class="itemline-details">

                                <div class="itemline-title">
                                    <a href="../item?<%= itemUrlQuery%>">
                                        <span class="lg_text highlight2" style="line-height: 1.3em">
                                            <%= sharedItem.getTitle() %>
                                        </span>
                                    </a>
                                </div>

                                <div class="itemline-bottom">
                                    <div style="float: left;">
                                        <span class="smd_text dim2">via</span>
                                        <span class="smd_header highlight6"><%= StringUtils.concat(smartGroup.getName(), 25, "...")%></span>
                                        <% if (networkIntegerSettings.get(NetworkIntegerSettingEnum.SHARED_ITEM_DISPLAY_CREATED_ON) == 1) { %>
                                            <div class="sm_text dim2"><%= PrettyDate.toString(sharedItem.getCreatedOn()) %></div>
                                        <% } %>
                                    </div>
                                    <div style="float: right;">
                                        <% pu_a_user = author; %>
                                        <%@include file="../user/includes/pu_a_user_card.jsp" %>
                                    </div>
                                </div>
                            </div>
                        </div>
                <%
                        // Clearing url query objects
                        itemUrlQuery.clearParams();
                        smartGroupUrlQuery.clearParams();
                } %>

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