<%@ include file="../../all.jsp" %>
<%@ include file="../load.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));

    String app_d_title = null;
    String app_d_message = null;
    HtmlDesign.Positions app_d_position = null;

    String hSettingsId = HtmlUtils.getRandomId();
    String hFormId = HtmlUtils.getRandomId();

    Object app_e_selectedValue = null;
    Map<String, Object> app_e_options = new LinkedHashMap<String, Object>();
%>

<div id="<%= hSettingsId %>" class="user_setting_container form-verhor">
    <form id="<%= hFormId%>">

        <div class="error smd_text"></div>

        <div class="element">
            <div class="name sm_text dim">"Someone viewed you" notifications</div>
            <div class="input">

                <%
                    app_d_title = "'Someone viewed you'";
                    app_d_message = "When someone in this community views your profile for the first time, you can receive a notification.";
                    app_d_position = HtmlDesign.Positions.BOTTOM;

                    app_e_selectedValue = UserToNetworkIntegerSettingEnum.IS_UNSUBSCRIBED_FROM_NEW_USER_LINK_EMAIL_NOTIFICATIONS.getValueByUserIdAndNetworkId(userId, networkId);
                    app_e_options.clear();
                    app_e_options.put("Receive", 0);
                    app_e_options.put("Do not receive", 1);
                %>
                <%@ include file="../../includes/app_d_mini_tooltip.jsp"%>

                <select name="ulink_notif" class="field">
                    <%@ include file="../../includes/app_e_options.jsp"%>
                </select>
            </div>
        </div>

        <div class="element">
            <div class="name sm_text dim">"Auto-magically added to new smart group" notifications</div>
            <div class="input">

                <%
                    app_d_title = "'Auto-magically added...'";
                    app_d_message = "Based on your profile you can automatically be added to very specific smart groups. When this occurs you will be notified.";
                    app_d_position = HtmlDesign.Positions.BOTTOM;

                    app_e_selectedValue = UserToNetworkIntegerSettingEnum.IS_UNSUBSCRIBED_FROM_NEW_SMART_GROUP_MAPPINGS_EMAIL_NOTIFICATIONS.getValueByUserIdAndNetworkId(userId, networkId);
                    app_e_options.clear();
                    app_e_options.put("Receive", 0);
                    app_e_options.put("Do not receive", 1);
                %>
                <%@ include file="../../includes/app_d_mini_tooltip.jsp"%>

                <select name="sgroup_notif" class="field">
                    <%@ include file="../../includes/app_e_options.jsp"%>
                </select>
            </div>
        </div>

        <div class="element">
            <div class="name sm_text dim">Rate of message digests of active smart groups</div>
            <div class="input">

                <%
                    app_d_title = "Digest Rate";
                    app_d_message = "Smart groups are the places of great conversations. Here set the rate at which you receive new message notifications or digests.";
                    app_d_position = HtmlDesign.Positions.BOTTOM;

                    app_e_selectedValue = UserToNetworkIntegerSettingEnum.NEW_SHARED_ITEM_DIGEST_EMAIL_RATE.getValueByUserIdAndNetworkId(userId, networkId);
                    app_e_options.clear();
                    app_e_options.put(EmailNotificationRateEnum.INSTANTLY.getName(),             EmailNotificationRateEnum.INSTANTLY.getId());
                    app_e_options.put(EmailNotificationRateEnum.EVERY_HOUR.getName(),            EmailNotificationRateEnum.EVERY_HOUR.getId());
                    app_e_options.put(EmailNotificationRateEnum.EVERY_FOUR_HOURS.getName(),      EmailNotificationRateEnum.EVERY_FOUR_HOURS.getId());
                    app_e_options.put(EmailNotificationRateEnum.EVERY_EIGHT_HOURS.getName(),     EmailNotificationRateEnum.EVERY_EIGHT_HOURS.getId());
                    app_e_options.put(EmailNotificationRateEnum.EVERY_TWELVE_HOURS.getName(),    EmailNotificationRateEnum.EVERY_TWELVE_HOURS.getId());
                    app_e_options.put(EmailNotificationRateEnum.EVERY_DAY.getName(),             EmailNotificationRateEnum.EVERY_DAY.getId());
                    app_e_options.put(EmailNotificationRateEnum.EVERY_OTHER_DAY.getName(),       EmailNotificationRateEnum.EVERY_OTHER_DAY.getId());
                    app_e_options.put(EmailNotificationRateEnum.EVERY_THREE_DAYS.getName(),      EmailNotificationRateEnum.EVERY_THREE_DAYS.getId());
                    app_e_options.put(EmailNotificationRateEnum.EVERY_WEEK.getName(),            EmailNotificationRateEnum.EVERY_WEEK.getId());
                    app_e_options.put(EmailNotificationRateEnum.EVERY_OTHER_WEEK.getName(),      EmailNotificationRateEnum.EVERY_OTHER_WEEK.getId());
                    app_e_options.put(EmailNotificationRateEnum.NEVER.getName(),                 EmailNotificationRateEnum.NEVER.getId());
                %>
                <%@ include file="../../includes/app_d_mini_tooltip.jsp"%>

                <select name="digest_rate" class="field">
                    <%@ include file="../../includes/app_e_options.jsp"%>
                </select>
            </div>
        </div>

        <div class="actionable">
            <a href="#" onclick="UND.submitEmailNotifications(event, <%= networkId %>, '<%= hSettingsId %>', '<%= hFormId %>')"><div class="action md_button submit_button">Save Settings</div></a>
            <div class="loading"><img src="./img/sm_loading.gif"></div>
        </div>

    </form>

</div>