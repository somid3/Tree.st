<%@ include file="../../all.jsp" %>
<%
    String newEmail = StringUtils.parseString(request.getParameter("email"));
%>

<span class="lg_text highlight">
    Please visit your email inbox at <span class="lg_header"><%= newEmail %></span>
    and click on the provided link. Don't forget to visit your <span class="sp_header">spam</span> folder.
</span>
