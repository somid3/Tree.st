<%@ include file="../../all.jsp" %>

<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    String searchText = StringUtils.parseString(request.getParameter("s"));

    // Validating search text
    if (searchText == null || searchText.isEmpty())
        searchText = "";

    // Adding regular expression terms to search text
    searchText = searchText.trim();
    String sqlSearchText = null;
    if (searchText != null)
        sqlSearchText = '%' + searchText + '%';
    else
        sqlSearchText = "%";
%>

<div id="search_result" class="canvas_container">

    <%
    {
       /* Searching all smart groups */

       LimitCounter counter = new LimitCounter(30);
       SmartGroup sgroup_d_smartGroup = null;
       Integer sgroup_d_userId = userId;
       String sgroup_d_highlight = searchText;

       // Retrieving all users with a name
       Set<SmartGroup> matches = new TreeSet<SmartGroup>();
       matches.addAll(SmartGroupDao.findByNetworkIdAndName(null, networkId, sqlSearchText, SmartGroupVisibilityEnum.PRIVATE, userId));
       matches.addAll(SmartGroupDao.findByNetworkIdAndName(null, networkId, sqlSearchText, SmartGroupVisibilityEnum.SHARED, null));


       %> <div class="search_group md_header white">Smart Groups (<%= matches.size() %>)</div> <%

       // Looping through all the matches
       for (SmartGroup match : matches) {

           // Setting up variables for include
           sgroup_d_smartGroup = match;

           %> <%@ include file="../smart_groups/includes/sgroup_d_smart_group_line.jsp"%> <%

           counter.incrementCount();
           if (counter.hasReachedMax())
               break;
       }

       if (counter.hasReachedMax()) {

           %> <div class="search_message lg_text dim2">Maximum results reached</div> <%

       }

       if (!matches.isEmpty()) {
           %> <div class="search_sep">&nbsp;</div> <%
       }

    }








    {
        /* Searching all qualities across network tree */

        // Retrieving all networks from user
        List<Network> networks = NetworkServices.getNetworkWithAllDependantsMappedToUser(networkId, userId, RoleEnum.VISITOR);

        Integer totalItems = null;
        String highlighted = null;
        String questionText = null;

        // Placing this network as the first network
        Collections.sort(networks);
        Collections.reverse(networks);

        %> <div class="search_group md_header white">Qualities</div> <%

        // Looping over all networks
        for (Network network : networks) {

            LimitCounter counter = new LimitCounter(30);
            List<Question> matchedQuestions = new ArrayList<Question>();
            List<QuestionOption> matchedQuestionOptions = new ArrayList<QuestionOption>();

            // Searching for questions
            matchedQuestions = QuestionDao.findByNetworkIdAndText(null, network.getId(), sqlSearchText, counter.getLimit());

            // Searching for questions with matching options
            if (!searchText.isEmpty())
                matchedQuestionOptions = QuestionOptionDao.findByNetworkIdAndText(null, network.getId(), sqlSearchText, counter.getLimit());

            // Counting total items found
            totalItems = matchedQuestions.size() + matchedQuestionOptions.size();

            // If there are no matches go to the next network
            if (totalItems == 0)
                continue;

        %>
            <div class="search_network sm_button">
                <%= network.getName() %> (<%= totalItems %>)
            </div>
        <%

            // Looping over matched questions
            for (Question question : matchedQuestions) {

                // Ensuring limit has not been reached
                counter.incrementCount();
                if (counter.hasReachedMax())
                    break;

                questionText = StringUtils.concat(question.getText(), 60, "&hellip;");

                if (!searchText.isEmpty())
                    highlighted = StringUtils.highlight(questionText, searchText, "<span class=\"search_found highlight2\">", "</span>");
                else
                    highlighted = questionText;

        %>
                <a href="#">

                    <div class="search_question smd_text" onclick="SS.goSmartSearch(event, <%= question.getNetworkId() %>, <%= question.getRef() %>)">
                        <%= highlighted %> <span class="dim2">(<%= question.getTotalAnswers() %>)</span>
                    </div>
                </a>
        <%
            }

            // Looping over matched question options
            Question question = null;
            for (QuestionOption option : matchedQuestionOptions) {

                // Ensuring limit has not been reached
                counter.incrementCount();
                if (counter.hasReachedMax())
                    break;

                question = QuestionDao.getByNetworkIdAndRef(null, option.getNetworkId(), option.getQuestionRef());
                highlighted = StringUtils.highlight(StringUtils.concat(option.getText(), 20, "&hellip;"), searchText, "<span class=\"search_found highlight2\">", "</span>");

        %>
                <a href="#">
                    <div class="search_question smd_text" onclick="SS.goSmartSearch(event, <%= question.getNetworkId() %>, <%= question.getRef() %>, <%= option.getRef() %>)">
                        <%= highlighted %> <span class="dim2">(<%= option.getTotalAnswers() %>)</span> &rarr; <%= StringUtils.concat(question.getText(), 50, "&hellip;") %>
                    </div>
                </a>
        <%

                // Ensuring limit has not been reached
                if (counter.hasReachedMax())
                    break;

            }

            if (counter.hasReachedMax()) {

                %> <div class="search_message lg_text dim2">Maximum results reached</div> <%
            }

            %> <div class="search_sep">&nbsp;</div> <%

        }

    }

    {
        /* Searching all users */

        LimitCounter counter = new LimitCounter(20);

        // Retrieving all users with a name
        List<User> matches = UserDao.findByNetworkIdAndName(null, networkId, sqlSearchText);

        // Shuffling users to get a random result set everything
        Collections.shuffle(matches);

        // Sorting result users by whether or not they have a defined face
        Collections.sort(matches, new UserComparatorFaceOn());

        %> <div class="search_group md_header white">People (<%= matches.size() %>)</div>
        <div style="display: inline-block;"> <%

        // Variables for user window
        Integer ul_b_networkId = networkId;
        Integer ul_b_toUserId = null;
        AnswerVisibilityEnum ul_b_lowestVisibility = AnswerVisibilityEnum.PROTECTED;

        // Looping through all the matches
        for (User match : matches) {
            ul_b_toUserId = match.getId();

            %> <%@ include file="../user_links/includes/ul_b_face.jsp"%> <%

            counter.incrementCount();
            if (counter.hasReachedMax())
                break;
        }

        %> </div> <%

        if (counter.hasReachedMax()) {
            %> <div class="search_message lg_text dim2">Maximum results reached</div> <%
        }


    }

%>

</div>
