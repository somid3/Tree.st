<%@ include file="../all.jsp"%>
<%
    Integer userId = StringUtils.parseInt(request.getParameter("uid"));

    String confirmationChecksum = StringUtils.parseString(request.getParameter("xcs"));

    // Attempting to confirm email
    try {

        // Attempting to confirm user email
        EmailConfirmationServices.confirmEmail(webUtils, userId, confirmationChecksum);


    } catch (UIException e) {

        // Confirmation failed, checksum is incorrect
        webUtils.redirect("/d/signout");
    }

%>
