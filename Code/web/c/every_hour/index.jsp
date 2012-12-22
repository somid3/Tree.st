<%@ include file="../all.jsp" %>
<%
    // Retrieving the hour of year
    Calendar calendar = GregorianCalendar.getInstance();
    calendar.setTime(new Date());
    Integer hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

    /* Doing the grunt work... */

    // Calculate smart group results
    CronServices.calledHourlyPopulateSmartGroups();

    // Notify members that recently joined a smart group
    CronServices.notifyNewNonPrivateSmartGroupMembers(EmailNotificationRateEnum.EVERY_HOUR);

    // Send digest emails
    {
        CronServices.sharedItemDigest(EmailNotificationRateEnum.EVERY_HOUR);
        if (hourOfDay % 4 == 0)  CronServices.sharedItemDigest(EmailNotificationRateEnum.EVERY_FOUR_HOURS);
        if (hourOfDay % 8 == 0)  CronServices.sharedItemDigest(EmailNotificationRateEnum.EVERY_EIGHT_HOURS);
        if (hourOfDay % 12 == 0) CronServices.sharedItemDigest(EmailNotificationRateEnum.EVERY_TWELVE_HOURS);
    }

    System.out.println("'Every hour' script called at " + new Date());
%>
