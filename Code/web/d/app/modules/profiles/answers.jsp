<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    Integer viewUserId = StringUtils.parseInt(request.getParameter("vuid"));

    // Determine if viewing myself, or if a user link is required
    boolean viewMyself = UserLinkServices.viewMyselfOrLinkedUsers(homeId, meId, viewUserId);

    // Retrieving user being viewed
    User viewed = null;
    if (viewMyself)
        viewed = me;
    else
        viewed = UserDao.getById(null, viewUserId);

    // List of approved networks to display
    List<Network> approvedNetworks = new ArrayList<Network>();

    // From root network, get all dependant networks or which I am a member
    List<Network> myNetworksFromRoot = NetworkServices.getNetworkWithAllDependantsMappedToUser(meId, homeId, RoleEnum.MEMBER);

    // Deciding how to selected the approved networks
    if (viewMyself) {

        // If I am viewing myself, then present all my networks from this root
        approvedNetworks = myNetworksFromRoot;

    } else {

        // If I am viewing other user, retrieve all networks user is mapped to
        List<Network> viewedUserNetworks = NetworkServices.getByUserId(viewUserId, RoleEnum.MEMBER, SqlLimit.ALL);

        approvedNetworks = new ArrayList<Network>();

        // Filter for networks we are both members of
        for (Network myNetworkFromRoot : myNetworksFromRoot) {
            if (viewedUserNetworks.contains(myNetworkFromRoot))
                approvedNetworks.add(myNetworkFromRoot);
        }
    }

    // Sorting the networks to show the most relevant network first
    Collections.sort(approvedNetworks);
    Collections.reverse(approvedNetworks);

    List<Question> networkQuestions = null;
    QuestionOption questionOption = null;
    Answer viewedUserAnswer = null;
    Answer userAnswer = null;
    String hOptionStyle = null; %>

    <%

    // Looping over all approved networks
    for (Network approvedNetwork : approvedNetworks) { %>

        <div class="profile_answers canvas_container">

            <% if (!home.equals(approvedNetwork)) { %>
                <div class="network_profile">
                    <span class="lg_header smd_header highlight3"><%= approvedNetwork.getName() %></span>
                    <span class="smd_text dim2"> is part of the &#8220;<%= home.getName() %>&#8221; lineage.</span>
                </div>
            <% } %>


            <%
            int totalAnswerOptions = 0;

            // Retrieving questions in network
            networkQuestions = QuestionDao.getByNetworkId(null, approvedNetwork.getId());

            // For every question in the network...
            for (Question question : networkQuestions) {

                viewedUserAnswer = AnswerServices.getLastByUserIdAndNetworkIdAndAnsweredQuestionRef(viewUserId, approvedNetwork.getId(), question.getRef());

                // If the user being viewed has not answered this question, skip it
                if (viewedUserAnswer == null)
                    continue;

                // If the viewed user is not itself, then answers lower than protected visibility should not be displayed
                if (!viewMyself && viewedUserAnswer.getVisibility().isLowerThan(AnswerVisibilityEnum.PROTECTED))
                    continue;

                // If the viewed user is not itself, attempt to see if the user has answered the same question
                userAnswer = null;
                if (!viewMyself) {

                    // Attempting to retrieve answer from user for the same question
                    userAnswer = AnswerServices.getLastByUserIdAndNetworkIdAndAnsweredQuestionRef(meId, approvedNetwork.getId(), question.getRef());
                }

                // Retrieving question with options loaded
                question = QuestionServices.getByNetworkIdAndRef(question.getNetworkId(), question.getRef()); %>


                <div class="answer">

                    <div class="question">
                        <div class="text smd_text dim"><%= question.getText() %></div>
                        <div class="qualities">
                          <div class="date sm_text dim2">Updated <%= PrettyDate.toString(viewedUserAnswer.getCreatedOn()) %></div>
                        </div>
                    </div>

                    <div class="options">

                    <% for (AnswerOption viewedUserAnswerOption : viewedUserAnswer.getOptions()) {

                        // Increasing count of total answer options
                        totalAnswerOptions = viewedUserAnswer.getOptions().size();

                        // Retrieving the question option that was answered
                        questionOption = question.findOptionByRef(viewedUserAnswerOption.getOptionRef());

                        if (viewedUserAnswerOption.getOptionRef() == 0) { %>

                            <div class="smd_text dim">Skipped</div>

                        <% } else if (questionOption == null) { %>

                            <div class="smd_text highlight">Option has been removed (<%= viewedUserAnswerOption.getQuestionRef() %> <%= viewedUserAnswerOption.getOptionRef() %>)</div>

                        <% } else {

                            // If the user has answered this question in common, is this option also a common option?
                            if (userAnswer != null) {

                                if (userAnswer.findOptionByRef(questionOption.getRef()) != null)
                                    hOptionStyle = "background-color: " + HtmlDesign.highlight + ";";
                                else
                                    hOptionStyle = null;

                            } else {

                                hOptionStyle = null;

                            } %>

                                <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.smartSearchAdd(homeId, questionOption.getNetworkId(), questionOption.getQuestionRef(), questionOption.getRef()) %>');">
                                    <div class="option smd_text" style="<%= hOptionStyle%>"><%= questionOption.getText() %></div>
                                </a>

                        <% } %>

                    <% } %>


                    </div>

                    <div class="details">
                        <% if (viewMyself && home.equals(approvedNetwork)) { %>
                            <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.profileQuestionAgain(question.getNetworkId(), question.getRef()) %>');">
                                <div class="again sm_text highlight2">Update</div>
                            </a>
                        <% } %>
                    </div>

                </div>

                <hr class="answer_sep">

            <% } %>

            <% if (totalAnswerOptions == 0) {

                String app_a_message = null;

                if (viewMyself) { %>

                        <div class="canvas_mini_message">
                            <div class="container">
                                <div class="content">
                                    <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.profileQuestions(approvedNetwork.getId()) %>');">
                                        <div class="message smd_text highlight2">
                                            Complete your profile!
                                        </div>
                                    </a>
                                </div>
                            </div>
                        </div>

                <% } else {

                    app_a_message = viewed.getFirstName() + " has not provided any details yet!";
                    boolean app_a_withCanvasContainer = false; %>
                    <%@ include file="../../includes/app_a_mini_message.jsp" %>

                <% } %>

            <% } %>
        </div>
<% } %>



