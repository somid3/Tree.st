<%@ include file="../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer questionRef = StringUtils.parseInt(request.getParameter("qr"));
    String text = StringUtils.parseString(request.getParameter("text"));

    // Adding option
    Integer optionRef = QuestionOptionServices.addOption(networkId, questionRef, userId, text);
    QuestionOption collab_a_qo = QuestionOptionDao.getByNetworkIdAndQuestionRefAndOptionRef(null, networkId, questionRef, optionRef);
%>
<%@ include file="includes/collab_a_question_display_option.jsp" %>

