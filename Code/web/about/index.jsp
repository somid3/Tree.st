<%@ include file="all.jsp" %>
<%
    // Retrieve network from domain
    Network network = UrlRouter.getNetworkByDomain(webUtils);

    // Does the network have a custom landing page?
    {
        String customLandingJsp = NetworkAlphaSettingEnum.CUSTOM_LANDING.getValueByNetworkId(network.getId());
        if (!StringUtils.isEmpty(customLandingJsp))
            webUtils.stealth(customLandingJsp);
    }

    // Does the network have a formal landing page?
    {
        Integer landingRef = StringUtils.parseInt(request.getParameter("lr"));
        if (landingRef == null)
            landingRef = 1;

        NetworkLanding landing = NetworkLandingDao.getByNetworkIdAndRef(null, network.getId(), landingRef);
        if (landing != null) {
            UrlQuery urlQuery = new UrlQuery();
            urlQuery.add("lr", landingRef);

            webUtils.stealth("./landing.jsp?" + urlQuery);
        }
    }

    // Send user to default start page
    webUtils.stealth("./start.jsp");
%>