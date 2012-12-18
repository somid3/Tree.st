<%@ include file="../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));

    // Retrieving network
    Network network = NetworkDao.getById(null, networkId);

    // Optional parameters
    Integer againQuestionRef = StringUtils.parseInt(request.getParameter("agqr"));
    Integer backToBackCount = StringUtils.parseInt(request.getParameter("btb"));
    if (backToBackCount == null)
        backToBackCount = 1;

    Boolean isFatigued = false;
    if (backToBackCount > 8 && !network.isGlobal())
        isFatigued = true;

    String hFilterOptionsInputId = HtmlUtils.getRandomId();
    String hAddOptionInputId = HtmlUtils.getRandomId();
    String hAddOptionButtonId = HtmlUtils.getRandomId();

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
        answeringQuestionRef = FlowRuleServices.getNextQuestionRef(userId, networkId);

    }

    // Should we display the question?
    if (answeringQuestionRef != null && !isFatigued) {

        // Retrieving question to be answered
        Question question = QuestionServices.getByNetworkIdAndRef(networkId, answeringQuestionRef);
%>

<script type="text/javascript">
    QD = new QuestionDisplay();
    QD.backToBackCount = <%= backToBackCount %>;
    QD.networkId = <%= networkId %>;
    QD.answeringQuestionRef = <%= answeringQuestionRef %>;
    QD.maxSelectedOptions = <%= question.getMaxSelectedOptions() %>;

    QD.hAddOptionInputId = '<%= hAddOptionInputId %>';
    QD.hAddOptionButtonId = '<%= hAddOptionButtonId %>';
</script>

<div class="question_container">
    <div class="question_display">
        <div class="input">
            <div class="question">

                <span class="lg_header"><%= question.getText() %></span>

                <% if (question.getPoints() >= 100) { %>
                    <div class="pts md_button submit_button">+<%= question.getPoints() %> pts.</div>
                <% } else if (question.getPoints() >= 50) { %>
                    <div class="pts md_button">+<%= question.getPoints() %> pts.</div>
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
                        function() { $(".filter_options").show() });
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
                    <a href="#" onclick="QD.submit(event)"><div class="submit lg_button submit_button">Submit</div></a>
                    <a href="#" onclick="QD.skip(event)"><div class="skip lg_button">Skip</div></a>
                </div>
                <div class="settings">
                    <div class="visibility">

                        <img src="./modules/collaborate/img/lock.png" alt="">

                        <%
                            app_d_title = "Answer Visibility";
                            app_d_message = "Each answer can have a different visibility. 'Anonymous' answers allows others to find and message you without learning who you are.";
                            app_d_position = HtmlDesign.Positions.LEFT; %>
                        <%@ include file="../../includes/app_d_mini_tooltip.jsp"%>

                        <select name="visibility" class="smd_text">
                            <%
                                String selected = "";
                                Integer maxVisibility = 9;
                                Integer defaultVisibility = 6;

                                if (question.getMaxVisibility() != null)
                                    maxVisibility = question.getMaxVisibility();

                                if (question.getDefaultVisibility() != null)
                                    defaultVisibility = question.getDefaultVisibility();

                                for (AnswerVisibilityEnum ave : AnswerVisibilityEnum.values()) {

                                    if (maxVisibility >= ave.getValue()) {

                                        if (defaultVisibility == ave.getValue())
                                            selected = " selected";
                                        else
                                            selected = ""; %>

                                        <option value="<%= ave.getValue() %>"<%= selected %>><%= ave.getDescription(network) %></option>
                                    <% } %>
                                <% } %>
                        </select>
                    </div>
                </div>
            </div>
        </div>

        <% if (!network.isGlobal()) { %>
            <div class="output shadow"></div>
            <script type="text/javascript">
                $(".output").load("./modules/collaborate/question_display_faces.jsp", {nid: <%= networkId %>, qr: <%= answeringQuestionRef %>});
            </script>
        <% } %>

        <script type="text/javascript">

            QD.updateOrSubmit(null);
            Animations.toPosition(".output", 20, 10, 600);

        </script>
    </div>
</div>

<% } else {

    // There are no more questions left...
    if (answeringQuestionRef == null) {

        Network collab_c_network = network; %>
        <%@ include file="./includes/collab_c_done.jsp" %>

    <%
    // User has answered too many questions...
    } else if (isFatigued) { %>

        <%@ include file="./includes/collab_b_fatigue.jsp" %>

    <% } %>

<% } %>