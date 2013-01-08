<%@ include file="../../all.jsp" %>
<%
    String queryString = StringUtils.parseString(request.getParameter("query"));
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));

    // Retrieving smart group
    SmartGroup group = SmartGroupDao.getByNetworkIdAndRef(null, networkId, smartGroupRef);

    // Retrieving whether to display lasted date of result
    Integer displaySince = NetworkIntegerSettingEnum.SMART_RESULTS_DISPLAY_SINCE.getValueByNetwork(networkId);

    // Is the smart group private?
    if (group.getVisibility() == SmartGroupVisibilityEnum.PRIVATE) {

        // New query or retrieve old one?
        if (queryString == null) {

            // No new query provided, retrieve old one
            queryString = group.getQuery();

        } else {

            // New query provided, update the new query
            SmartGroupDao.updateQueryByNetworkIdAndRef(null, networkId, smartGroupRef, queryString);
        }


        // Analyzing new query
        QueryXml queryXml = QueryXmlReader.parseAndLoad(queryString);

        // Run search and store results
        UserScores userScores = QueryServices.createScoresAndMappings(networkId, smartGroupRef, queryXml);

    }

    // Retrieving top results
    List<SmartGroupResult> results = SmartGroupResultDao.getByNetworkIdAndRef(null, networkId, smartGroupRef, new SqlLimit(0, 100));
    Integer totalResults = results.size();
    if (totalResults > 50)
        results = results.subList(0, 50);

    // Are there any results to display?
    if (results.size() > 0) { %>

    <div id="smart_search_results">

        <%
            Integer ul_a_toUserId = null;
            Integer ul_a_networkId = null;
            for (SmartGroupResult result : results) {

                // Skip displaying the current user
                if (result.getUserId().equals(userId))
                    continue;

                ul_a_toUserId = result.getUserId();
                ul_a_networkId = result.getNetworkId(); %>

                <div class="smart_search_result">

                    <div class="result_card">
                        <%@ include file="../user_links/includes/ul_a_card.jsp"%>
                    </div>

                    <div class="result_details">

                        <div class="flares">
                            <% for (QuestionOption option : QuestionOptionServices.getFromFlares(result.getFlare())) { %>

                                <div class="flare sm_text white"><%= StringUtils.concat(option.getText(), 10, "&hellip;") %></div>

                            <% } %>
                        </div>

                        <% if (displaySince > 0) { %>
                            <div>
                                <div class="since vsm_text dim2">since <%= PrettyDate.toString(result.getLatestDate()).toLowerCase() %></div>
                            </div>
                        <% }%>

                    </div>
               </div>
            <% } %>

    </div>
    <% } %>

    <% if (totalResults > 50) {
       String app_a_message = "Too many results found!";
       boolean app_a_withCanvasContainer = true; %>
       <%@ include file="../../includes/app_a_mini_message.jsp" %>
    <% } %>

    <% if (totalResults == 0) {
        String app_a_message = "Sorry, no results were found";
        boolean app_a_withCanvasContainer = true; %>
        <%@ include file="../../includes/app_a_mini_message.jsp" %>

    <% } %>
