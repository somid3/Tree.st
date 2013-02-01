<%@ include file="./all.jsp" %>
<%

    // Document the error in the logs
    System.err.println(WebUtils.requestDump(request));

    // Are we in a non production mode?
    if(!Vars.isInProduction()) {

        // Re-throw the exception
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        throw exception;

    } else {

        webUtils.redirect("/d/app?error");

    }

%>