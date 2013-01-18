<%@ include file="../../all.jsp" %>
<%@ include file="./load.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));

    // Retrieving network
    Network network = NetworkDao.getById(null, networkId);

    // Retrieving parameters to automatically add a question
    Integer searchNetworkId = StringUtils.parseInt(request.getParameter("snid"));
    Integer searchQuestionRef = StringUtils.parseInt(request.getParameter("sqr"));
    Integer searchOptionRef = StringUtils.parseInt(request.getParameter("sor"));

    // Retrieve user plural setting
    String settingUserPlural = NetworkAlphaSettingEnum.VOCAB_USER_PLURAL.getValueByNetwork(networkId);

    SmartGroup smartGroup = SmartGroupServices.getOrCreateSearchSmartGroup(networkId, userId);
%>
<div id="smart_search_welcome">
    <%
       Network sgroup_f_network = network;
       String sgroup_f_settingUserPlural = settingUserPlural;
    %>
    <%@ include file="includes/sgroup_f_empty_search.jsp" %>
</div>

<%
    SmartGroup sgroup_a_smartGroup = smartGroup;
    Boolean sgroup_a_editable = true;
%>
<%@ include file="includes/sgroup_a_criterion.jsp"%>

<%
   boolean app_c_withCanvasContainer = true;
   String app_c_hCanvasLoadingId = "smart_search_loading";
%>
<%@ include file="../../includes/app_c_canvas_loading.jsp" %>

<div id="smart_search_error">
<% String app_b_message = "";
boolean app_b_withCanvasContainer = true; %>
<%@ include file="../../includes/app_b_error.jsp" %>
</div>

<div id="smart_group_faces">
</div>

<script type="text/javascript">

    <% if (searchNetworkId != null && searchQuestionRef != null) {

        String hCriteriaSearchQuestionId = QueryServices.generateHtmlId(searchNetworkId, searchQuestionRef, null); %>

        // Add the question user selected
        SS.questionAdd(
            null,
            <%= smartGroup.getNetworkId() %>,
            <%= smartGroup.getSmartGroupRef() %>,
            '<%= hCriteriaSearchQuestionId %>',
            <%= searchNetworkId %>,
            <%= searchQuestionRef %>,
            <%= searchOptionRef %>);

    <% } else { %>

        // Display faces for the query once it is loaded
        SS.submitQuery(<%= smartGroup.getNetworkId() %>, <%= smartGroup.getSmartGroupRef() %>, null);

    <% } %>

</script>