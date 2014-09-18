<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>

<%
    String searchText = StringUtils.parseString(request.getParameter("s"));

    // Validating search text
    if (StringUtils.isEmpty(searchText))
        searchText = "";

    // Adding regular expression terms to search text
    searchText = searchText.trim();
    String sqlSearchText = null;
    if (!searchText.isEmpty())
        sqlSearchText = '%' + searchText + '%';
    else
        sqlSearchText = "%";

    // Setting output limit
    Integer limit = 50;
    SqlLimit sqlLimit = new SqlLimit(0, 50);





    /**
     * Filter finder
     */
    {
        /**
         * Searching all qualities across network tree, all the qualities should be displayed if
         * no search text is provided
         **/

        // Retrieving all networks from user
        List<Network> networks = NetworkServices.getNetworkWithAllDependantsMappedToUser(meId, homeId, RoleEnum.VISITOR);

        Integer totalItems = null;
        String highlighted = null;
        String questionText = null;

        // Placing this network as the first network
        Collections.sort(networks);
        Collections.reverse(networks); %>

        <div class="finder_results canvas_container">

            <div class="find_group md_header white">Filters</div> <%

            // Looping over all networks
            for (Network network : networks) {

                LimitCounter counter = new LimitCounter(limit);
                List<Question> matchedQuestions = new ArrayList<Question>();
                List<QuestionOption> matchedQuestionOptions = new ArrayList<QuestionOption>();

                // Searching for questions
                matchedQuestions = QuestionDao.findByNetworkIdAndText(null, network.getId(), sqlSearchText, sqlLimit);

                // Searching for questions with matching options
                if (!searchText.isEmpty())
                    matchedQuestionOptions = QuestionOptionDao.findByNetworkIdAndText(null, network.getId(), sqlSearchText, sqlLimit);

                // Counting total items found
                totalItems = matchedQuestions.size() + matchedQuestionOptions.size();

                // If there are no matches go to the next network
                if (totalItems == 0)
                    continue;

                // Looping over matched questions
                for (Question question : matchedQuestions) {

                    // Ensuring limit has not been reached
                    counter.incrementCount();
                    if (counter.hasReachedMax())
                        break;

                    questionText = StringUtils.concat(question.getText(), 60, "&hellip;");

                    if (!searchText.isEmpty())
                        highlighted = StringUtils.highlight(questionText, searchText, "<span class='found'>", "</span>");
                    else
                        highlighted = questionText; %>

                    <a href="#">
                        <div class="find_question smd_text"
                            onclick="HashRouting.setHash(event, '<%= HashRouting.smartSearchAdd(homeId, question.getNetworkId(), question.getRef()) %>');">
                            <%= highlighted %>
                        </div>
                    </a> <%

                }

                // Looping over matched question options
                Question question = null;
                for (QuestionOption option : matchedQuestionOptions) {

                    // Ensuring limit has not been reached
                    counter.incrementCount();
                    if (counter.hasReachedMax())
                        break;

                    question = QuestionDao.getByNetworkIdAndRef(null, option.getNetworkId(), option.getQuestionRef());
                    highlighted = StringUtils.highlight(StringUtils.concat(option.getText(), 20, "&hellip;"), searchText, "<span class='found'>", "</span>"); %>

                    <a href="#">
                        <div class="find_question smd_text"
                            onclick="HashRouting.setHash(event, '<%= HashRouting.smartSearchAdd(homeId, option.getNetworkId(), option.getQuestionRef(), option.getRef()) %>');">
                            <%= highlighted %> <span class="dim2">(<%= option.getTotalAnswers() %>)</span> &rarr; <%= StringUtils.concat(question.getText(), 50, "&hellip;") %>
                        </div>
                    </a> <%

                    // Ensuring limit has not been reached
                    if (counter.hasReachedMax())
                        break;

                }

                if (counter.hasReachedMax()) { %>
                    <div class="find_message vl_text dim">Maximum results reached for <%= network.getName() %>!</div> <%
                }

            }

        %> </div> <%
    }



    /**
     * Smart Group finder
     */
    if (!searchText.isEmpty()) {

       /* Searching all smart groups */

       LimitCounter counter = new LimitCounter(limit);
       SmartGroup sgroup_d_smartGroup = null;
       Integer sgroup_d_userId = meId;
       String sgroup_d_highlight = searchText;

       // Retrieving all users with a name
       List<SmartGroup> matches = SmartGroupDao.findByNetworkIdAndName(null, homeId, sqlSearchText, SmartGroupVisibilityEnum.SHARED, null, sqlLimit); %>

       <div class="finder_results canvas_container">

           <div class="find_group md_header white">Smart Groups</div> <%

           // Looping through all the matches
           for (SmartGroup match : matches) {

               // Setting up variables for include
               sgroup_d_smartGroup = match; %>

               <%@ include file="../smart_groups/includes/sgroup_d_smart_group_line.jsp"%> <%

               counter.incrementCount();
               if (counter.hasReachedMax())
                   break;
           }

           if (counter.hasReachedMax()) { %>
               <div class="find_message vl_text dim">Maximum results reached!</div> <%
           } %>

       </div>

    <% }







    /**
    * Shared item finder
    */
    if (!searchText.isEmpty()) {

        /* Searching all smart groups */

        LimitCounter counter = new LimitCounter(limit);
        SharedItem share_d_sharedItem = null;
        Integer share_d_fromSmartGroupRef = SharedComment.ANY_SHARED_COMMENT_REF;
        Map<NetworkIntegerSettingEnum, Integer> share_d_networkIntegerSettings = null;
        Map<NetworkIntegerSettingEnum, String> share_d_networkAlphaSettings = null;
        String share_d_highlight = null;
        User share_d_user = me;

        // Retrieving all users with a name
        List<SharedItem> matches = SharedItemDao.findByNetworkIdAndText(null, homeId, sqlSearchText, sqlLimit); %>

        <div class="finder_results canvas_container">

        <div class="find_group md_header white">Shared Messages</div> <%

        // Looping through all the matches
        for (SharedItem match : matches) {

            // Setting up variables for include
            share_d_highlight = searchText;
            share_d_sharedItem = match;
            share_d_networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(match.getNetworkId()); %>

            <%@ include file="../share/includes/share_d_shared_item_line.jsp"%> <%

            counter.incrementCount();
            if (counter.hasReachedMax())
                break;

        }

        if (counter.hasReachedMax()) { %>
            <div class="find_message vl_text dim">Maximum results reached!</div> <%
        } %>

        </div>

    <% }






    /**
     * People finder
     */
    if (!searchText.isEmpty()) {

        /* Searching users */

        LimitCounter counter = new LimitCounter(limit);

        // Retrieving all users with a name
        List<User> matches = UserDao.findByNetworkIdAndName(null, homeId, sqlSearchText, sqlLimit);

        // Sorting result users by whether or not they have a defined face
        Collections.sort(matches, new UserComparatorFaceOn());

        %> <div class="finder_results canvas_container">

            <div class="find_group md_header white">Members</div> <%

            // Variables for user window
            Integer ul_a_toUserId = null;
            Integer ul_a_networkUserLinkPointsPer = NetworkIntegerSettingEnum.USER_LINK_POINTS_PER.getValueByNetworkId(homeId);
            for (User match : matches) {
                ul_a_toUserId = match.getId();

                %> <div class="finder_member_result">
                    <%@ include file="../user_links/includes/ul_a_card.jsp"%>
                </div> <%

                counter.incrementCount();
                if (counter.hasReachedMax())
                    break;
            }

            if (counter.hasReachedMax()) { %>
                <div class="find_message vl_text dim">Maximum results reached!</div> <%
            }

        %> </div> <%
    }
%>
