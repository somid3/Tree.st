<% {
    /**
     * Inputs variables
     *
     *   SmartGroup sgroup_a_smartGroup = null;
     *   Boolean sgroup_a_editable = null;
     */

    QueryXml sgroup_a_queryXml = QueryXmlReader.parseAndLoad(sgroup_a_smartGroup.getQuery());
    Boolean sgroup_b_editable = sgroup_a_editable;
    SmartGroup sgroup_b_smartGroup = sgroup_a_smartGroup;
%>
<div id="smart_search_criterion" class="canvas_container">

    <div id="edit_criteria" class="popup glow4"></div>

    <div id="save_as"></div>

    <div class="header">
        <div class="title smd_text dim2">Search criteria(s):</div>

        <% if (sgroup_a_editable) { %>
            <a href="#"><div id="save_it" class="smd_text white">Save as Smart Group!</div></a>
        <% } %>
    </div>

    <% for (QuestionXml sgroup_b_questionXml : sgroup_a_queryXml.getQuestions()) { %>

        <%@ include file="sgroup_b_criteria.jsp"%>

    <% } %>

</div>


<script type="text/javascript">
    $("#save_it").click(function(event) {

        Event.preventDefault(event);

        $("#save_it").fadeOut();
        Transitions.fadeOutLoadFadeIn("#save_as", "./modules/smart_groups/create_smart_group.jsp",  {nid: <%= sgroup_a_smartGroup.getNetworkId() %>, sgr: <%= sgroup_a_smartGroup.getRef() %>});
    });
</script>

<% } %>
