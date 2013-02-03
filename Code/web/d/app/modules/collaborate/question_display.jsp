<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    // Retrieving network
    Map<NetworkIntegerSettingEnum, Integer> networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(homeId);

    // Optional parameters
    Integer againQuestionRef = StringUtils.parseInt(request.getParameter("agqr"));
    Integer backToBackCount = StringUtils.parseInt(request.getParameter("btb"));
    if (backToBackCount == null)
        backToBackCount = 1;

    Boolean isFatigued = false;
    if (backToBackCount > 5 && homeCollectMode)
        isFatigued = true;

    String hFilterOptionsInputId = HtmlUtils.getRandomId();
    String hAddOptionInputId = HtmlUtils.getRandomId();
    String hAddOptionButtonId = HtmlUtils.getRandomId();
    String hMinitipId = HtmlUtils.getRandomId();

    // Mini tool tip variables
    String app_d_title = null;
    String app_d_message = null;
    HtmlDesign.Positions app_d_position = null;

    // Figuring out what question answer next
    Integer answeringQuestionRef = null;

    // Do we have to answer a question again?
    if (againQuestionRef != null) {

        // Yes, set the next question to the question we need to answer again
        answeringQuestionRef = againQuestionRef;

    } else {

        // No, find the next question for this network
        answeringQuestionRef = FlowRuleServices.getNextQuestionRef(meId, homeId);

    }

    // Should we display the question?
    if (answeringQuestionRef != null && !isFatigued) {

        // Retrieving question to be answered
        Question question = QuestionServices.getByNetworkIdAndRef(homeId, answeringQuestionRef);

%>

<script type="text/javascript">
    QD = new QuestionDisplay();
    QD.backToBackCount = <%= backToBackCount %>;
    QD.networkId = <%= homeId %>;
    QD.answeringQuestionRef = <%= answeringQuestionRef %>;
    QD.maxSelectedOptions = <%= question.getMaxSelectedOptions() %>;

    QD.hAddOptionInputId = '<%= hAddOptionInputId %>';
    QD.hAddOptionButtonId = '<%= hAddOptionButtonId %>';
</script>

<% if (TooltipServices.displayMinitip(UserIntegerSettingEnum.TIP_QUESTIONS_HOW, meId)) { %>
    <div class="minitip" id="<%= hMinitipId %>">
        <div class="lg_header tip">Tip:</div>
        <div class="content lg_text dim">
            Below you will see some questions, as you answer them you will complete your profile
            &mdash; to update your answers visit
            <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.profile(homeId) %>');">
                <span class="highlight2">&quot;My Profile&quot;</span>
            </a>

        </div>
        <div class="close">
            <a href="#" onclick="Tooltips.closeMinitip(event, '<%= hMinitipId %>', <%= UserIntegerSettingEnum.TIP_QUESTIONS_HOW.getId()%>)">
                <img src="./img/close_dark.png">
            </a>
        </div>
    </div>
<% } %>

<div class="question_container">
    <div class="question_display">
        <div class="input">
            <div class="question">

                <span class="lg_header"><%= question.getText() %></span>

                <% if (question.getPoints() >= 100) { %>
                    <div class="pts md_button submit_button smd_text white">+<%= question.getPoints() %> pts.</div>
                <% } else if (question.getPoints() >= 50) { %>
                    <div class="pts md_button dark_button smd_text white">+<%= question.getPoints() %> pts.</div>
                <% } else if (question.getPoints() > 0) { %>
                    <div class="pts md_button light_button smd_text dim">+<%= question.getPoints() %> pts.</div>
                <% } %>

                <% if (question.getPoints() > 0) {

                    app_d_title = "Points Per Question";
                    app_d_message = "When you answer questions you gain points. More important questions let you gain more points.";
                    app_d_position = HtmlDesign.Positions.BOTTOM; %>
                    <%@ include file="../../includes/app_d_mini_tooltip.jsp"%>

                <% } %>

            </div>

            <div class="answer_status smd_text highlight3"></div>

            <div class="options_wrap">

                <div class="filter_options lg_shadow">
                    <div class="abc"><input class="md_input w250" type="text" id="<%= hFilterOptionsInputId %>" maxlength="40" placeholder="Filter options..." value=""></div>
                </div>

                <script type="text/javascript">
                    QD.optionsFilter('<%= hFilterOptionsInputId %>');
                </script>

                <div class="options">
                <% for (QuestionOption collab_a_qo : question.getOptions()) { %>
                        <%@ include file="includes/collab_a_question_display_option.jsp"%>
                <% } %>
                </div>

                <script type="text/javascript">
                    Divs.maxHeight(".options", 400, 300,
                        function() { $(".filter_options").hide() },
                        function() { $(".filter_options").show() }
                    );
                </script>

                <%
                // Dynamically add options?
                if (question.getAllowAddOptions()) { %>
                    <div class="add_option lg_shadow">
                        <div class="abc"><input class="md_input w250" type="text" id="<%= hAddOptionInputId %>" maxlength="40" placeholder="Add an option..." value=""></div>
                        <a href="#" onclick="QD.optionAdd(event)"><div id="<%= hAddOptionButtonId %>" class="add md_button">Add</div></a>
                    </div>
                    <script type="text/javascript">

                        // Presenting the "add" option button when focus occurs
                        $("#<%= hAddOptionInputId %>").focus(function() {
                            $("#<%= hAddOptionButtonId %>").fadeIn();
                        });

                        // Removing the "add" option button when blur occurs
                        $("#<%= hAddOptionInputId %>").blur(function() {
                            $("#<%= hAddOptionButtonId %>").fadeOut();
                        });

                    </script>
                <% } %>
            </div>

            <div class="answer">
                <div class="buttons">
                    <a href="#" onclick="QD.submit(event)"><div class="submit lg_button submit_button md_text white">Submit</div></a>
                    <a href="#" onclick="QD.skip(event)"><div class="skip lg_button dark_button md_text white">Skip</div></a>
                </div>
                <div class="settings">
                    <div class="visibility">

                        <img src="./modules/collaborate/img/lock.png" alt="">

                        <%
                            app_d_title = "Answer Visibility";
                            app_d_message = "Each answer can have a different visibility. 'Public' answers can be viewed in the public internet. 'Protected' answers are only visible by community members. 'Private' answers can only be viewed by you.";
                            app_d_position = HtmlDesign.Positions.LEFT; %>
                        <%@ include file="../../includes/app_d_mini_tooltip.jsp"%>

                        <select name="visibility" class="smd_text">
                            <%
                                String selected = "";
                                AnswerVisibilityEnum maxVisibility = AnswerVisibilityEnum.PUBLIC;
                                AnswerVisibilityEnum defaultVisibility = AnswerVisibilityEnum.PROTECTED;

                                if (question.getMaxVisibility() != null)
                                    maxVisibility = question.getMaxVisibility();

                                if (question.getDefaultVisibility() != null)
                                    defaultVisibility = question.getDefaultVisibility();

                                for (AnswerVisibilityEnum ave : AnswerVisibilityEnum.values()) {

                                    if (ave.isLowerThan(maxVisibility)) {

                                        if (defaultVisibility == ave)
                                            selected = " selected";
                                        else
                                            selected = ""; %>

                                        <option value="<%= ave.getId() %>"<%= selected %>><%= ave.getDescription() %></option>
                                    <% } %>
                                <% } %>
                        </select>
                    </div>
                </div>
            </div>
        </div>

        <% if (!homeCollectMode) { %>
            <div class="output shadow"></div>
            <script type="text/javascript">
                $(".output").load("./modules/collaborate/question_display_faces.jsp", {nid: <%= homeId %>, qr: <%= answeringQuestionRef %>});
            </script>
        <% }%>

        <script type="text/javascript">
            QD.updateOrSubmit(null);
            Animations.toPosition(".output", 20, 10, 600);
        </script>
    </div>
</div>

<% } else {

    // There are no more questions left...
    if (answeringQuestionRef == null) {

        Integer collab_c_userId = meId;
        Network collab_c_network = home;
        Map<NetworkIntegerSettingEnum, Integer> collab_c_networkIntegerSettings = networkIntegerSettings; %>
        <%@ include file="./includes/collab_c_done.jsp" %>

    <%

    // User has answered too many questions...
    } else if (isFatigued) {

        Network collab_b_network = home; %>

        <%@ include file="./includes/collab_b_fatigue.jsp" %>

    <% } %>

<% } %>