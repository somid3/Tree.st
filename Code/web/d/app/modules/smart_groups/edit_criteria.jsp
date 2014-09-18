<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    Integer smartGroupNetworkId = StringUtils.parseInt(request.getParameter("sgnid"));
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));
    Integer criteriaNetworkId = StringUtils.parseInt(request.getParameter("cnid"));
    Integer criteriaQuestionRef = StringUtils.parseInt(request.getParameter("cqr"));

    // Retrieving network
    Network network = NetworkDao.getById(null, smartGroupNetworkId);

    // Retrieving smart group
    SmartGroup smartGroup = SmartGroupDao.getByNetworkIdAndRef(null, smartGroupNetworkId, smartGroupRef);

    // Looking for the question matching the criteria
    QueryXml queryXml = QueryXmlReader.parseAndLoad(smartGroup.getQuery());
    QuestionXml criteriaQuestionXml = null;
    for (QuestionXml questionXml : queryXml.getQuestions() ) {

        // Can a question be found in the query to match the criteria?
        if (questionXml.getNetworkId().equals(criteriaNetworkId) &&
            questionXml.getRef().equals(criteriaQuestionRef)) {

            // An adequate criteria was found!
            criteriaQuestionXml = questionXml;

            break;
        }
    }

    Question criteriaQuestion = QuestionServices.getByNetworkIdAndRef(criteriaQuestionXml.getNetworkId(), criteriaQuestionXml.getRef());
    String hCriteriaQuestionId = QueryServices.generateHtmlId(criteriaQuestion.getNetworkId(), criteriaQuestion.getRef(), null);


    /**
     * Inputs variables
     *
     *   QuestionXml sgd_questionXml = null;
     */
    String hQuestionScoreId = HtmlUtils.getRandomId();
    String hQuestionSliderId = HtmlUtils.getRandomId();
%>
<div class="question_query">

    <div class="tools">
        <a href="#" onclick="SS.submitEditCriteria(event, <%= smartGroup.getNetworkId() %>, <%= smartGroup.getSmartGroupRef() %>)"><div class="submit submit_button">Submit Changes</div></a>
        <a href="#" onclick="ND.go(event, NetworkDashboard.Section.SMART_SEARCH);"><div class="cancel light_button">Cancel</div></a>
    </div>

    <div class="question">

        <div class="text">
            <span class="lg_header"><%= criteriaQuestion.getText() %></span>
        </div>
    </div>

    <div class="options">
        <%
         String hCriteriaOptionId = null;
         String hEditOptionId = null;
         String hOptionScoreId = null;
         String hOptionSliderId = null;
         boolean highlightOption;
         Integer hOptionScore = null;

         // Identify which question options have a criteria
         List<QuestionOption> toBeMoved = new ArrayList<QuestionOption>();
         for (OptionXml criteriaOptionXml : criteriaQuestionXml.getOptions()) {

             // Is this option in the query set?
             if (criteriaOptionXml.hasCriteria()) {

                 // Yes, place this option in the question on the top
                 toBeMoved.add(criteriaQuestion.findOptionByRef(criteriaOptionXml.getRef()));

             }

         }

         // Place those question options with a criteria to the top
         criteriaQuestion.getOptions().removeAll(toBeMoved);
         criteriaQuestion.getOptions().addAll(0, toBeMoved);

         // Looping through all question options
         OptionXml optionXml = null;
         for (QuestionOption questionOption : criteriaQuestion.getOptions()) {

             optionXml = criteriaQuestionXml.getOptionByRef(questionOption.getRef());

             hCriteriaOptionId = QueryServices.generateHtmlId(criteriaQuestion.getNetworkId(), criteriaQuestion.getRef(), questionOption.getRef());

             hEditOptionId = HtmlUtils.getRandomId();
             hOptionScoreId = HtmlUtils.getRandomId();
             hOptionSliderId = HtmlUtils.getRandomId();
             hOptionScore = 50;
             highlightOption = false;

             // Query xml does not contain all options, optionXml could be null
             if (optionXml != null) {

                 // Set the option score
                 hOptionScore = optionXml.getScore();

                 // Does this query option have a criteria, if so, highlight it?
                 if(optionXml.hasCriteria())
                     highlightOption = true;

             }; %>

            <div id="<%= hEditOptionId %>" class="option">

                <a href="#" onclick="SS.optionClick(event, '<%= hEditOptionId %>', '<%= hCriteriaQuestionId %>', '<%= hCriteriaOptionId %>')">
                    <div class="bar">
                        <input type="hidden" name="is_set">
                        <span class="smd_text"><%= questionOption.getText() %></span>
                    </div>
                </a>

                <%-- Template that will be used to place in criterion when highlighted --%>
                <div class="selected_option" style="display: none;">
                    <div class="score"><%= hOptionScore %></div>
                    <div class="ref"><%= questionOption.getRef() %></div>
                    <div class="text white smd_text"><%= questionOption.getText() %></div>
                </div>

            </div>

            <% if (highlightOption) { %>
            <script type="text/javascript">
                SS.toggleHighlightOption('<%= hEditOptionId %>');
            </script>
            <% } %>

        <% } %>
    </div>

</div>

