<%@ include file="../../../all.jsp" %>
<%@ include file="../load.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));

    String app_d_title = null;
    String app_d_message = null;
    HtmlDesign.Positions app_d_position = null;

    Object app_e_selectedValue = null;
    Map<String, Object> app_e_options = new HashMap<String, Object>();
%>

<div id="email_notificaitons" class="user_network_container form-verhor">

    <div class="element">
        <div class="name sm_text dim">"Someone viewed you" notifications</div>
        <div class="input">

            <%
                app_d_title = "'Someone viewed you'";
                app_d_message = "When someone in this community views your profile for the first time, you can receive a notification.";
                app_d_position = HtmlDesign.Positions.BOTTOM;

                app_e_selectedValue = 1;
                app_e_options.clear();
                app_e_options.put("Receive", 1);
                app_e_options.put("Do not receive", 0);
            %>
            <%@ include file="../../../includes/app_d_mini_tooltip.jsp"%>

            <select class="field">
                <%@ include file="../../../includes/app_e_options.jsp"%>
            </select>
        </div>
    </div>

    <div class="element">
        <div class="name sm_text dim">"Auto-magically added to new smart group" notifications</div>
        <div class="input">

            <% app_d_title = "'Auto-magically added...'";
            app_d_message = "Based on your profile you can automatically be added to very specific smart groups. When this occurs you will be notified.";
            app_d_position = HtmlDesign.Positions.BOTTOM; %>
            <%@ include file="../../../includes/app_d_mini_tooltip.jsp"%>

            <select class="field">
                <option value="1">Receive</option>
                <option value="0">Do no receive</option>
            </select>
        </div>
    </div>

    <div class="element">
        <div class="name sm_text dim">Rate of message digests of active smart groups</div>
        <div class="input">

            <% app_d_title = "Digest Rate";
            app_d_message = "Smart groups are the places of great conversations. Here set the rate at which you receive new message notifications or digests.";
            app_d_position = HtmlDesign.Positions.BOTTOM; %>
            <%@ include file="../../../includes/app_d_mini_tooltip.jsp"%>


            <select class="field">
                <option value="1">Instantly</option>
                <option value="1">Every 4 hours</option>
                <option value="0">Never</option>
            </select>
        </div>
    </div>

    <div class="actionable">
        <a href="#"><div id="" class="md_button submit_button">Save Settings</div></a>
    </div>

    <%--IS_UNSUBSCRIBED_FROM_NEW_USER_LINK_EMAIL_NOTIFICATIONS (101, 0),--%>
<%----%>
    <%--IS_UNSUBSCRIBED_FROM_NEW_SMART_GROUP_MAPPINGS_EMAIL_NOTIFICATIONS(102, 0),--%>
<%----%>
    <%--NEW_SHARED_ITEM_DIGEST_EMAIL_RATE (200, 10)--%>
<%----%>
</div>