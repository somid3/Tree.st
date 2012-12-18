<% {
    /**
     * Inputs variables
     *
     *   Question sgroup_c_question = null;
     *   OptionXml sgroup_c_optionXml = null;
     */
    QuestionOption sgroup_c_questionOption = sgroup_c_question.findOptionByRef(sgroup_c_optionXml.getRef());
    String sgroup_c_hOptionId = QueryServices.generateHtmlId(sgroup_c_question.getNetworkId(), sgroup_c_question.getRef(), sgroup_c_optionXml.getRef());

%>

<div class="selected_option" id="<%= sgroup_c_hOptionId %>">
    <div class="score"><%= sgroup_c_optionXml.getScore()%></div>
    <div class="ref"><%= sgroup_c_optionXml.getRef()%></div>
    <div class="text white smd_text"><%= sgroup_c_questionOption.getText() %></div>
</div>

<% } %>
