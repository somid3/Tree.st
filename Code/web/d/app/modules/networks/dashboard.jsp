<%@ include file="../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));

    // Retrieving network
    Network network = NetworkDao.getById(null, networkId);

    // Retrieving user to network
    UserToNetwork utn = UserToNetworkDao.getByUserIdAndNetworkId(null, userId, networkId);
%>
<div id="network_dashboard">

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

    <div id="points">
        <span id="available_points" class="vl_header white"><%= utn.getCurrentPoints() %></span>
        <div class="sm_text white">Points</div>
    </div>

</div>

<script type="text/javascript">

    // Beginning basic search
    ND.search = Finder;

    // Binding find field
    Binding.bindInputKeyPress("findTimer", "#finder", Finder.submitFind );

    Finder.setupFinder();

</script>