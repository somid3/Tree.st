<%@ include file="../../../setup.jsp" %>
<%@ include file="../../../auth.jsp" %>
<%
    Integer answeredQuestionRef = StringUtils.parseInt(request.getParameter("aqr"));
    List<Integer> optionRefs = StringUtils.parseInts(request.getParameterValues("ors[]"));
    AnswerVisibilityEnum visibility = AnswerVisibilityEnum.getById(StringUtils.parseInt(request.getParameter("vis")));

    Tuple<Boolean, Integer> againAndPoints = AnswerServices.answer(meId, homeId, answeredQuestionRef, optionRefs, visibility);

    StringBuilder buf = new StringBuilder();
    buf.append("<result again=\"");
    buf.append(againAndPoints.getX());
    buf.append("\" points_added=\"");
    buf.append(againAndPoints.getY());
    buf.append("\"/>");
%>
<?xml version="1.0"?>
<answer>
    <%= buf.toString() %>
</answer>