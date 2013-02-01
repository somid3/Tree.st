<%@ include file="../../../setup.jsp" %>
<%@ include file="../../../auth.jsp" %>
<%
    TooltipEnum onTooltip = TooltipEnum.getByValue(StringUtils.parseString(request.getParameter("tv")));

    StringBuilder buf = new StringBuilder();

    TooltipServices.updateOnTooltipByUserId(meId, onTooltip);

    TooltipEnum nextTooltip = onTooltip.getNext();
%>
<?xml version="1.0"?>
<tooltips>
    <next tt="<%= nextTooltip.getTooltipHtmlId() %>" />
</tooltips>