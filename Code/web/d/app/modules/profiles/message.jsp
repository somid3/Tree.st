<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    Integer toUserId = StringUtils.parseInt(request.getParameter("tuid"));

    // Retrieving user who will receive this message
    User toUser = UserDao.getById(null, toUserId);

%>

<div class="message canvas_container">

    <div class="error"></div>
    <div class="loading"></div>

    <div class="shadow w450 center" style="padding: 5px; margin-bottom: 10px;">
        <textarea class="md_input w450" placeholder="Type your message here..."></textarea>
    </div>

    <div class="submit_button md_button md_text w150 center">
        Send
    </div>

</div>