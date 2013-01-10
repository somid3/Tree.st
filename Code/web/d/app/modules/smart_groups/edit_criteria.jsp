<%@ include file="../../all.jsp" %>
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
        <a href="#" onclick="SS.submitEditCriteria(event, <%= smartGroup.getNetworkId() %>, <%= smartGroup.getRef() %>)"><div class="submit submit_button">Submit Changes</div></a>
        <a href="#" onclick="ND.go(event, NetworkDashboard.Section.SMART_SEARCH);"><div class="cancel light_button">Cancel</div></a>
    </div>

    <div class="question">
        <div class="slider" id="<%= hQuestionSliderId %>">
            <div class="ticks">
                <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaQuestionId %>', '<%= hQuestionScoreId %>', 1);"><div class="tick t01 sm_text">1</div></a>
                <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaQuestionId %>', '<%= hQuestionScoreId %>', 10);"><div class="tick t10 sm_text">10</div></a>
                <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaQuestionId %>', '<%= hQuestionScoreId %>', 20);"><div class="tick t20 sm_text">20</div></a>
                <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaQuestionId %>', '<%= hQuestionScoreId %>', 30);"><div class="tick t30 sm_text">30</div></a>
                <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaQuestionId %>', '<%= hQuestionScoreId %>', 40);"><div class="tick t40 sm_text">40</div></a>
                <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaQuestionId %>', '<%= hQuestionScoreId %>', 50);"><div class="tick t50 sm_text">50</div></a>
                <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaQuestionId %>', '<%= hQuestionScoreId %>', 60);"><div class="tick t60 sm_text">60</div></a>
                <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaQuestionId %>', '<%= hQuestionScoreId %>', 70);"><div class="tick t70 sm_text">70</div></a>
                <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaQuestionId %>', '<%= hQuestionScoreId %>', 80);"><div class="tick t80 sm_text">80</div></a>
                <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaQuestionId %>', '<%= hQuestionScoreId %>', 90);"><div class="tick t90 sm_text">90</div></a>
                <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaQuestionId %>', '<%= hQuestionScoreId %>', 99);"><div class="tick t99 sm_text">99</div></a>
            </div>
            <div class="labels">
                <div class="label sm_text dim" style="float: left">not important</div>
                <div class="label sm_header dim" style="float: right">muy importante!</div>
            </div>
        </div>
        <a href="#" onclick="SS.showOrHideSlider(event, '<%= hQuestionSliderId %>')">
            <div class="score" id="<%= hQuestionScoreId %>"><%= criteriaQuestionXml.getScore() %></div>
        </a>
        <div class="text">
            <span class="lg_header"><%= criteriaQuestion.getText() %></span>
            <span class="sm_text dim2"> (<%= network.getName() %>)</span>
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
                <div class="slider" id="<%= hOptionSliderId %>">
                    <div class="ticks">
                        <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaOptionId %>', '<%= hOptionScoreId %>', 1);"><div class="tick t01 sm_text">1</div></a>
                        <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaOptionId %>', '<%= hOptionScoreId %>', 10);"><div class="tick t10 sm_text">10</div></a>
                        <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaOptionId %>', '<%= hOptionScoreId %>', 20);"><div class="tick t20 sm_text">20</div></a>
                        <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaOptionId %>', '<%= hOptionScoreId %>', 30);"><div class="tick t30 sm_text">30</div></a>
                        <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaOptionId %>', '<%= hOptionScoreId %>', 40);"><div class="tick t40 sm_text">40</div></a>
                        <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaOptionId %>', '<%= hOptionScoreId %>', 50);"><div class="tick t50 sm_text">50</div></a>
                        <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaOptionId %>', '<%= hOptionScoreId %>', 60);"><div class="tick t60 sm_text">60</div></a>
                        <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaOptionId %>', '<%= hOptionScoreId %>', 70);"><div class="tick t70 sm_text">70</div></a>
                        <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaOptionId %>', '<%= hOptionScoreId %>', 80);"><div class="tick t80 sm_text">80</div></a>
                        <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaOptionId %>', '<%= hOptionScoreId %>', 90);"><div class="tick t90 sm_text">90</div></a>
                        <a href="#" onclick="SS.changeTick(event, '<%= hCriteriaOptionId %>', '<%= hOptionScoreId %>', 99);"><div class="tick t99 sm_text">99</div></a>
                    </div>
                    <div class="labels">
                        <div class="label sm_text dim" style="float: left">not important</div>
                        <div class="label sm_header dim" style="float: right">muy importante!</div>
                    </div>
                </div>
                <a href="#" onclick="SS.showOrHideSlider(event, '<%= hOptionSliderId %>')">
                    <div class="score" id="<%= hOptionScoreId %>"><%= hOptionScore %></div>
                </a>

                <a href="#" onclick="SS.optionClick(event, '<%= hEditOptionId %>', '<%= hCriteriaQuestionId %>', '<%= hCriteriaOptionId %>')">
                    <div class="bar" style="background-position: <%= MathUtils.barLength(questionOption.getTotalAnswers(), criteriaQuestion.getTotalOptionAnswers(), 330)%>px;">
                        <input type="hidden" name="is_set">
                        <span class="smd_text"><%= questionOption.getText() %></span>
                        <div class="stats sm_text dim2"><%= MathUtils.percentage(questionOption.getTotalAnswers(), criteriaQuestion.getTotalOptionAnswers())%>%, <%= questionOption.getTotalAnswers() %> ppl.</div>
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

