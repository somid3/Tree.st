<%@ include file="../../../setup.jsp" %>
<%@ include file="../../../auth.jsp" %>
<%
    EmailNotificationRateEnum digestRate = EmailNotificationRateEnum.getById(StringUtils.parseInt(request.getParameter("digest_rate")));

    Integer userLinkNotification = StringUtils.parseInt(request.getParameter("ulink_notif"));
    Integer userMessageNotification = StringUtils.parseInt(request.getParameter("nmsg_notif"));
    Integer smartGroupNotification = StringUtils.parseInt(request.getParameter("sgroup_notif"));

    StringBuilder buf = new StringBuilder();

    try {

        UserToNetworkIntegerSettingEnum.NEW_SHARED_ITEM_DIGEST_EMAIL_RATE.setValueByUserIdAndNetworkId(meId, homeId, digestRate.getId());
        UserToNetworkIntegerSettingEnum.IS_UNSUBSCRIBED_FROM_NEW_SMART_GROUP_MAPPINGS_EMAIL_NOTIFICATIONS.setValueByUserIdAndNetworkId(meId, homeId, smartGroupNotification);
        UserToNetworkIntegerSettingEnum.IS_UNSUBSCRIBED_FROM_NEW_USER_LINK_EMAIL_NOTIFICATIONS.setValueByUserIdAndNetworkId(meId, homeId, userLinkNotification);
        UserToNetworkIntegerSettingEnum.IS_UNSUBSCRIBED_FROM_USER_MESSAGE_EMAIL_NOTIFICATIONS.setValueByUserIdAndNetworkId(meId, homeId, userMessageNotification);

    } catch (UIException e) {

        // Documenting error
        buf = new StringBuilder();
        buf.append("<error>");
        buf.append(e.getMessage());
        buf.append("</error>");

    }
%>
<?xml version="1.0"?>
<email_notifications>
    <%= buf.toString() %>
</email_notifications>