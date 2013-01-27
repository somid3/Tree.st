<%@ include file="../../../all.jsp" %>

<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer smartGroupRef = StringUtils.parseInt(request.getParameter("sgr"));

    Integer searchNetworkId = StringUtils.parseInt(request.getParameter("snid"));
    Integer searchQuestionRef = StringUtils.parseInt(request.getParameter("sqr"));
    Integer searchOptionRef = StringUtils.parseInt(request.getParameter("sor"));

    // Retrieving question
    Question searchQuestion = QuestionDao.getByNetworkIdAndRef(null, searchNetworkId, searchQuestionRef);

    // Creating default question query
    QuestionXml sgroup_b_questionXml = new QuestionXml();
    sgroup_b_questionXml.setRef(searchQuestion.getRef());
    sgroup_b_questionXml.setScore(50);
    sgroup_b_questionXml.setNetworkId(searchQuestion.getNetworkId());

    // Creating a criteria that matches user's last response to question
    Answer searchAnswer = AnswerDao.getLastByUserIdAndNetworkIdAndAnsweredQuestionRef(null, userId, searchQuestion.getNetworkId(), searchQuestion.getRef());

    // Has an option reference been provided?
    if (searchOptionRef != null) {

        // Add the provided option as a "is set" criteria
        CriteriaXml cx = new CriteriaXml();
        cx.setType(CriteriaXml.Type.IS_SET);

        OptionXml ox = new OptionXml();
        ox.setRef(searchOptionRef);
        ox.setScore(50);
        ox.setCriteria(cx);

        sgroup_b_questionXml.addOption(ox);

    // Has the user answered this question?
    } else if (searchAnswer != null) {

        // By default add "is set" criteria to the same options the answer contains
        List<AnswerOption> options = AnswerOptionDao.getByUserIdAndNetworkIdAndAnswerRef(null, userId, searchQuestion.getNetworkId(), searchAnswer.getRef());

        // Loop through all the options the user selected when question was answered
        for(AnswerOption ao : options) {

            // Add each option as a "is set" criteria
            CriteriaXml cx = new CriteriaXml();
            cx.setType(CriteriaXml.Type.IS_SET);

            OptionXml ox = new OptionXml();
            ox.setRef(ao.getOptionRef());
            ox.setScore(50);
            ox.setCriteria(cx);

            sgroup_b_questionXml.addOption(ox);

        }

    // No, the user has not answered this question, select the option with the most votes...
    } else {

        // Retrieve all possible options of question
        List<QuestionOption> options = QuestionOptionDao.getByNetworkIdAndQuestionRef(null, searchQuestion.getNetworkId(), searchQuestion.getRef());

        // Select option with most votes
        QuestionOption selectedOption = options.get(0);
        for (QuestionOption option : options) {

            if (selectedOption.getTotalAnswers() < option.getTotalAnswers())
                selectedOption = option;
        }

        CriteriaXml cx = new CriteriaXml();
        cx.setType(CriteriaXml.Type.IS_SET);

        OptionXml ox = new OptionXml();
        ox.setRef(selectedOption.getRef());
        ox.setScore(50);
        ox.setCriteria(cx);

        sgroup_b_questionXml.addOption(ox);
    }

    SmartGroup sgroup_b_smartGroup = SmartGroupDao.getByNetworkIdAndRef(null, networkId, smartGroupRef);
    Boolean sgroup_b_editable = true;
%>

<%@ include file="../includes/sgroup_b_criteria.jsp"%>

