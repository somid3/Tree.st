<% {
    /**
     * Inputs variables
     *
     *   SmartGroup sgroup_b_smartGroup = null;
     *   QuestionXml sgroup_b_questionXml = null;
     *   Boolean sgroup_b_editable = null;
     */
    Question sgroup_b_question = QuestionServices.getByNetworkIdAndRef(sgroup_b_questionXml.getNetworkId(), sgroup_b_questionXml.getRef());
    String sgroup_b_hCriteriaQuestionId = QueryServices.generateHtmlId(sgroup_b_questionXml.getNetworkId(), sgroup_b_questionXml.getRef(), null);
%>
<div class="criteria" id="<%= sgroup_b_hCriteriaQuestionId %>">

    <div class="elements">

        <% if (sgroup_b_editable) { %>
            <a href="#" onclick="SS.displayEditCriteria(
                event,
                <%= sgroup_b_smartGroup.getNetworkId() %>,
                <%= sgroup_b_smartGroup.getRef() %>,
                <%= sgroup_b_questionXml.getNetworkId() %>,
                <%= sgroup_b_questionXml.getRef() %>);">
        <% } %>

            <div class="question_details">
                <div class="score"><%= sgroup_b_questionXml.getScore()%></div>
                <div class="network"><%= sgroup_b_questionXml.getNetworkId()%></div>
                <div class="ref"><%= sgroup_b_questionXml.getRef()%></div>
                <div class="edit md_header light_button">+</div>
                <div class="text smd_text dim"><%= StringUtils.concat(sgroup_b_question.getText(), 50, "&hellip;") %></div>
            </div>

            <div class="selected_options">

                <% for (OptionXml sgroup_c_optionXml : sgroup_b_questionXml.getOptions()) {

                    Question sgroup_c_question = sgroup_b_question; %>

                    <%@ include file="sgroup_c_criteria_option.jsp"%>

                <% } %>

            </div>

        <% if (sgroup_b_editable) { %>
            </a>
        <% } %>

    </div>

    <div class="tools">
        <% if (sgroup_b_editable) { %>
            <a href="#" onclick="SS.questionRemove(event, <%= sgroup_b_smartGroup.getNetworkId() %>, <%= sgroup_b_smartGroup.getRef() %>, '<%= sgroup_b_hCriteriaQuestionId %>');"><img src="./img/close_dark.png" alt=""></a>
        <% } %>
    </div>

</div>
<% } %>
