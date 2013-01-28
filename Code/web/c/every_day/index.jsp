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

    // Send digest emails
    {
        CronServices.sharedItemDigest(EmailNotificationRateEnum.EVERY_DAY);
        if (dayOfYear % 2 == 0)  CronServices.sharedItemDigest(EmailNotificationRateEnum.EVERY_OTHER_DAY);
        if (dayOfYear % 3 == 0)  CronServices.sharedItemDigest(EmailNotificationRateEnum.EVERY_THREE_DAYS);
        if (dayOfYear % 7 == 0)  CronServices.sharedItemDigest(EmailNotificationRateEnum.EVERY_WEEK);
        if (dayOfYear % 14 == 0) CronServices.sharedItemDigest(EmailNotificationRateEnum.EVERY_OTHER_WEEK);
    }

    // Garbage collecting the user sessions
    CronServices.calledDailyUserSessionCleanUp();

    // Garbage collect inactive votes
    SharedVoteDao.deleteInactive();

    // Delete old password resets
    PasswordResetDao.deleteByCreatedBefore(null, DateUtils.daysAgo(2));


    System.out.println("'Every day' script called at " + new Date());
%>
