<%@ include file="../../../setup.jsp" %>
<%@ include file="../../../auth.jsp" %>
<%
    UserToNetworkIntegerSettingEnum settingEnum = UserToNetworkIntegerSettingEnum.getById(StringUtils.parseInt(request.getParameter("utnisid")));

    // Updating setting to turn the minitip off
    settingEnum.setValueByUserIdAndNetworkId(meId, homeId, 0);
%>
