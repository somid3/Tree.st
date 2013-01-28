<%@ include file="../all.jsp"%>
<%
    WebUtils wu = new WebUtils(request, response);

    Integer userId = StringUtils.parseInt(request.getParameter("uid"));

    String confirmationChecksum = StringUtils.parseString(request.getParameter("xcs"));

    // Attempting to confirm email
    try {

        // Attempting to confirm user email
        EmailConfirmationServices.confirmEmail(wu, userId, confirmationChecksum);


    } catch (UIException e) {

        // Confirmation failed, checksum is incorrect
        wu.redirect("/d/logout");
    }

%>
