<%@ include file="../../../setup.jsp" %>
<%@ include file="../../../auth.jsp" %>
<%
    UserIntegerSettingEnum settingEnum = UserIntegerSettingEnum.getById(StringUtils.parseInt(request.getParameter("sid")));

    // Updating setting to turn the minitip off
    settingEnum.setValueByUserId(meId, 0);
%>
