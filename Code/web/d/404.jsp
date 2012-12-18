<%@ include file="./all.jsp" %>
<%
    // Retrieving the path to find the desired network
    String path = (String) request.getAttribute("javax.servlet.forward.request_uri");
    path = path.substring(1).toLowerCase();

    WebUtils wu = new WebUtils(request, response);

    // Ensuring browser is not denied
    BrowserAcceptanceEnum.Status status = BrowserAcceptanceEnum.Browser.getStatus(wu.getUserAgent());
    if (status == BrowserAcceptanceEnum.Status.DENIED)
        wu.redirect("/d/browser");

    // Retrieving network url path
    Integer networkId = NetworkAlphaSettingEnum.URL_PATH.getNetworkIdByValue(path);

    if (networkId == null) {

        wu.redirect("/d/welcome?404");

    } else {

        Network network = NetworkDao.getById(null, networkId);

        String uri = "/d/start?nid=" + network.getId() + "&ncs=" + network.getChecksum();

        wu.redirect(uri);

    }
%>