<%@ include file="../../all.jsp" %>
<%
    Integer questionRef = StringUtils.parseInt(request.getParameter("qr"));
    String text = StringUtils.parseString(request.getParameter("text"));

    // Adding option
    Integer optionRef = QuestionOptionServices.addOption(meId, homeId, questionRef, text);
    QuestionOption collab_a_qo = QuestionOptionDao.getByNetworkIdAndQuestionRefAndOptionRef(null, homeId, questionRef, optionRef);
%>
<%@ include file="includes/collab_a_question_display_option.jsp" %>

