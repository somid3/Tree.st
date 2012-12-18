<%@ include file="../all.jsp" %>
<%
    String email = StringUtils.parseString(request.getParameter("e"));
    String passwordText = StringUtils.parseString(request.getParameter("p"));
    Boolean keep = StringUtils.parseBoolean(request.getParameter("k"));

    WebUtils wu = new WebUtils(request, response);

    StringBuilder buf = new StringBuilder();

    try {

        // Validating email
        if (!StringUtils.isEmail(email))
            throw new UIException("Email is not valid");

        // Retrieving user to get salt
        User user = UserDao.getByEmail(null, email);

        // Validating password
        if (StringUtils.emptyIfNull(passwordText).isEmpty())
            throw new UIException("Please provide a password");

        // Validating credentials
        if (user == null)
            throw new UIException("Incorrect email address or password");

        // Creating hashed password for login
        String providedPasswordHash = UserDao.hashPassword(passwordText, user.getPasswordSalt());

        // Logging user in, creating a new user session
        UserSession userSession = UserWebServices.authenticateAndCreateSession(wu, email, providedPasswordHash, keep);

        // Validating credentials
        if (userSession == null)
            throw new UIException("Incorrect email address or password");

        /* Yay! Credentials are correct */

        // Retrieving user email confirmation object
        EmailConfirmation ec = EmailConfirmationDao.getByUserId(null, user.getId());

        // Has the user been confirmed by email?
        if (ec == null) {

            // Not even began... begin the email confirmation process
            EmailConfirmationServices.beginEmailConfirmation(user.getId());

            // Add email confirmation action to response
            buf.append("<confirm/>");

        } else if (!ec.isConfirmed()) {

            // No, sending another pair of email confirmations
            EmailConfirmationServices.sendConfirmationEmail(user.getId(), 2);

            // Add email confirmation action to response
            buf.append("<confirm/>");

        } else {

            // Install login cookies at client
            UserWebServices.installCookies(wu, user.getId(), userSession.getChecksum(), keep);

            // Add send to application action in response
            buf.append("<app/>");

        }

    } catch (UIException e) {

        // Documenting error
        buf = new StringBuilder();
        buf.append("<error>");
        buf.append(e.getMessage());
        buf.append("</error>");

    }
%>
<?xml version="1.0"?>
<login>
    <%= buf.toString() %>
</login>