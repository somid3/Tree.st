<%@ include file="../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));

    // Retrieving network
    Network network = NetworkDao.getById(null, networkId);

    // Retrieving user to network
    UserToNetwork utn = UserToNetworkDao.getByUserIdAndNetworkId(null, userId, networkId);

    // Retrieving award description
    String awardDescription = NetworkAlphaSettingEnum.AWARD_DESCRIPTION.getValueByNetwork(networkId);


    String hSearchInputId = HtmlUtils.getRandomId();
%>
<div id="network_dashboard">

    <% if (!network.isGlobal()) { %>
    <div id="search_space">
        <div id="search_toolbar">
            <a href="#" onclick="Finder.viewEverything(event);">
                <div id="all" class="sm_button light_button sm_text highlight2">
                    <span>Qualities</span>
                </div>
            </a>

            <div id="search_input">
                <input class="md_input" style="position: relative; width: 230px; left: 75px;" type="text" id="finder" placeholder="Find qualities, people, messages" title="Finder" value=""/>
            </div>

        </div>
    </div>
    <% } %>

    <% if (!network.isGlobal()) { %>

        <div id="points">
            <span id="available_points" class="vl_header white"><%= utn.getCurrentPoints() %></span>
            <div class="sm_text white">Points</div>

            <% if (!awardDescription.isEmpty()) { %>

            <div style="position: absolute; left: -30px; top: 35px">

                <a href="#" onclick="NetworkDashboard.toggleAward()">
                        <img src="./modules/networks/img/award.png">
                </a>

                <a href="#" onclick="NetworkDashboard.toggleAward()">
                    <div id="award" class="sm_header glow white"><%= awardDescription %></div>
                </a>

            </div>

            <% } %>
        </div>

    <% } %>

</div>

<script type="text/javascript">

    Animations.toPosition("#header #dashboard", 0, 0, 600);

    <% if (!network.isGlobal()) { %>

        // Beginning basic search
        ND.search = Finder;

        // Binding find field
        Binding.bindInputKeyPress("findTimer", "#finder", Finder.submitSearch );

        Finder.setupFinder();

    <% } %>

</script>