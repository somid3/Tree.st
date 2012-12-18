<%@ include file="../all.jsp" %>
<%
    Integer userId = StringUtils.parseInt(request.getParameter("uid"));
    String checksum = StringUtils.parseString(request.getParameter("cs"));

    String passwordText = StringUtils.parseString(request.getParameter("p"));
    String passwordTextAgain = StringUtils.parseString(request.getParameter("pa"));

    WebUtils wu = new WebUtils(request, response);

    StringBuilder buf = new StringBuilder();

    try {

        // Validating
        if (userId == null)
            throw new UIException ("Incorrect password reset link");

        if (StringUtils.emptyIfNull(checksum).isEmpty())
            throw new UIException ("Incorrect password reset link");

        // Retrieving password reset
        PasswordReset reset = PasswordResetDao.getByUserIdAndChecksum(null, userId, checksum);

        // Validating reset
        if (reset == null)
            throw new UIException ("Incorrect password reset, please start over");

        if (reset.getCompletedOn() != null)
            throw new UIException ("Password reset has already been completed");

        if ((new Date().getTime() - reset.getCreatedOn().getTime() > 3600000))
            throw new UIException ("Password reset link has exceed one hour, please start over");

        User user = UserDao.getById(null, userId);
        if (user == null)
            throw new UIException ("Incorrect password reset link");

        // Validating password
        if (StringUtils.emptyIfNull(passwordText).length() < 6)
            throw new UIException("Password must be greater than five characters");

        if (StringUtils.emptyIfNull(passwordTextAgain).length() < 6)
            throw new UIException("Password must be greater than five characters");

        if (!passwordText.equals(passwordTextAgain))
            throw new UIException("Both passwords do not match");

        /* Yay! We are all good to update the password */

        // Updating the password
        UserDao.updatePasswordByUserId(null, userId, passwordText);

        // Retrieving updated user
        user = UserDao.getById(null, userId);

        // Notify user by email that password was updated
        EmailServices.passwordResetDone(userId);

        // Creating hashed password for login
        String providedPasswordHash = UserDao.hashPassword(passwordText, user.getPasswordSalt());

        // Ensure user gets logged in persistently
        Boolean persistent = true;

        // Logging user in, creating a new user session
        UserSession userSession = UserWebServices.authenticateAndCreateSession(wu, user.getEmail(), providedPasswordHash, persistent);

        // Install login cookies at client
        UserWebServices.installCookies(wu, user.getId(), userSession.getChecksum(), persistent);

        // Add send to application action in response
        buf.append("<app></app>");

    } catch (UIException e) {

        buf = new StringBuilder();
        buf.append("<error>");
        buf.append(e.getMessage());
        buf.append("</error>");

    }

%>
<?xml version="1.0"?>
<forgot>
    <%= buf.toString() %>
</forgot>