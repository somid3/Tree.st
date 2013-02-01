<%@ include file="../../setup.jsp" %>
<% appDisableBlocked = false; %>
<%@ include file="../../auth.jsp" %>
<%
    // Ensuring user is not blocked
    if (meToHome.getBlockedOn() != null)
        return;

    // Ensuring we are not in collect only mode
    if (homeCollectMode)
        return;

    // Retrieving user to network
    UserToNetwork utn = UserToNetworkDao.getByUserIdAndNetworkId(null, meId, homeId);
%>
<div id="network_dashboard">

    <div id="search_space">

        <div id="search_toolbar">
            <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.finder(homeId)%>')">
                <div id="finder_all" class="sm_button light_button sm_text highlight2">
                    <span>Qualities</span>
                </div>
            </a>

            <div id="search_input">
                <input class="md_input" style="position: relative; width: 230px; left: 75px;" type="text" id="finder" placeholder="Find qualities, people, messages" title="Finder" value=""/>
                <input type="hidden" id="finder_hash" value="<%= HashRouting.finder(homeId, "") %>"/>
            </div>

        </div>

    </div>

    <div id="points">
        <span id="available_points" class="vl_header white"><%= utn.getCurrentPoints() %></span>
        <div class="sm_text white">Points</div>
    </div>

</div>

<script type="text/javascript">

    // Binding find field
    Binding.bindInputKeyPress("findTimer", "#finder", Finder.submitFind );
    Finder.setupFinder();
</script>