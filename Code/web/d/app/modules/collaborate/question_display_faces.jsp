<%@ include file="../../all.jsp" %>
<%
    Integer questionRef = StringUtils.parseInt(request.getParameter("qr"));
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));

    // Retrieving question
    Question question = QuestionServices.getByNetworkIdAndRef(networkId, questionRef);

    // Looping over all answer options, we don't want tree to be ordered, so we use a hash map
    Map<Integer, AnswerVisibilityEnum> userVisibility = new HashMap<Integer, AnswerVisibilityEnum>();
    List<ActiveAnswer> aas = null;
    Integer maxFaces = 6;

    for (QuestionOption qo : question.getOptions()) {

        // Retrieving latest active answers
        aas = ActiveAnswerDao.getByNetworkIdAndQuestionRefAndOptionRef(null, qo.getNetworkId(), question.getRef(), qo.getRef(), AnswerVisibilityEnum.PROTECTED, maxFaces);

        // Add active answer user to faces
        for(ActiveAnswer aa : aas)
            userVisibility.put(aa.getUserId(), aa.getVisibility());

        // Do we already have the max faces?
        if (userVisibility.size() >= maxFaces)
            break;
    }
%>

<% if (!userVisibility.isEmpty()) { %>
    <div class="similar sm_text dim">Recently answered by</div>

    <div>
    <%
        Integer ul_b_networkId = networkId;

        int i = 1;
        for (Integer ul_b_toUserId : userVisibility.keySet()) { %>

            <%@ include file="../user_links/includes/ul_b_face.jsp"%>

        <% i++; if (i > maxFaces) break; } %>
    </div>

    <a href="#" onclick="SS.goSmartSearch(event, <%= networkId %>, <%= question.getRef() %>, null)">
        <span class="similar sm_text highlight2">I'm feeling lucky</span>
    </a>

    <%
        String app_d_title = "I'm Feeling Lucky";
        String app_d_message = "Use the finder to find all members who answered a random option.";
        HtmlDesign.Positions app_d_position = HtmlDesign.Positions.LEFT; %>
    <%@ include file="../../includes/app_d_mini_tooltip.jsp"%>

<% } else { %>

    <div class="first md_text dim">Congrats! You are the first one to answer this question!</div>

<% } %>


