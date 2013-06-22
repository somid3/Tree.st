<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    Integer questionRef = StringUtils.parseInt(request.getParameter("qr"));

    // Retrieving question
    Question question = QuestionServices.getByNetworkIdAndRef(homeId, questionRef);

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
        Integer ul_b_networkId = homeId;

        int i = 1;
        for (Integer ul_b_toUserId : userVisibility.keySet()) { %>

            <%@ include file="../user_links/includes/ul_b_face.jsp"%>

        <% i++; if (i > maxFaces) break; } %>
    </div>

<% } else { %>

    <div class="first md_text dim">Congrats! You are the first one to answer this question!</div>

<% } %>


