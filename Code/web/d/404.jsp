<%@ include file="./all.jsp" %>
<%
    // Retrieving the path to find the desired network
    String path = (String) request.getAttribute("javax.servlet.forward.request_uri");

    if (!Vars.isInProduction())
        System.out.println("*** 404 NOT FOUND *** " + path);

    path = path.substring(1).toLowerCase();

    // Ensuring browser is not denied
    BrowserAcceptanceEnum.Status status = BrowserAcceptanceEnum.Browser.getStatus(webUtils.getUserAgent());
    if (status == BrowserAcceptanceEnum.Status.DENIED)
        webUtils.redirect("/d/browser");

    // Retrieving network url path
    Integer networkId = NetworkAlphaSettingEnum.URL_PATH.getNetworkIdByValue(path);

    if (networkId == null) {

        webUtils.redirect("/d/welcome?404");

    } else {

        Network network = NetworkDao.getById(null, networkId);

        String uri = "/d/start?nid=" + network.getId() + "&ncs=" + network.getChecksum();

        webUtils.redirect(uri);

    }
%>