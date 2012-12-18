<%@ include file="../all.jsp" %>
<%
    // Retrieving the day of year
    Calendar calendar = GregorianCalendar.getInstance();
    calendar.setTime(new Date());
    Integer dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);


    /* Sending email reminders */

    // Send photo upload reminders
    EmailScheduledReminders.calledDailyPhotoUploadReminder();

    // Send email confirmation messages for new accounts
    EmailScheduledReminders.calledDailyConfirmationEmail();

    // Notify members that recently joined a smart group
    {
        CronServices.notifyNewNonPrivateSmartGroupMembers(EmailNotificationRateEnum.EVERY_DAY);
        if (dayOfYear % 2 == 0)  CronServices.notifyNewNonPrivateSmartGroupMembers(EmailNotificationRateEnum.EVERY_OTHER_DAY);
        if (dayOfYear % 3 == 0)  CronServices.notifyNewNonPrivateSmartGroupMembers(EmailNotificationRateEnum.EVERY_THREE_DAYS);
        if (dayOfYear % 7 == 0)  CronServices.notifyNewNonPrivateSmartGroupMembers(EmailNotificationRateEnum.EVERY_WEEK);
        if (dayOfYear % 14 == 0) CronServices.notifyNewNonPrivateSmartGroupMembers(EmailNotificationRateEnum.EVERY_OTHER_WEEK);
    }


    System.out.println("'Every day' script called at " + new Date());
%>
