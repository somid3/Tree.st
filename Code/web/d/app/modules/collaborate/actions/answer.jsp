<%@ include file="../../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer answeredQuestionRef = StringUtils.parseInt(request.getParameter("aqr"));
    List<Integer> optionRefs = StringUtils.parseInts(request.getParameterValues("ors[]"));
    AnswerVisibilityEnum visibility = AnswerVisibilityEnum.getById(StringUtils.parseInt(request.getParameter("vis")));

    Tuple<Boolean, Integer> againAndPoints = AnswerServices.answer(userId, networkId, answeredQuestionRef, optionRefs, visibility);

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