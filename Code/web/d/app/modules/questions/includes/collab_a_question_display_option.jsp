
<% {
    /**
     * Inputs variables
     *
     *   QuestionOption collab_a_qo = null;
     */

    String collab_c_hOptionId = HtmlUtils.getRandomId();
%>

<a href="#">
    <div class="option" id="<%= collab_c_hOptionId %>" onclick="QD.optionClick(event, '<%= collab_c_hOptionId %>', <%= collab_a_qo.getRef() %>);">
        <input type="hidden" name="is_set">
        <span class="value smd_text"><%= collab_a_qo.getText() %></span>
    </div>
</a>

<% } %>