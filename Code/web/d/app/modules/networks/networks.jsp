<%@ include file="../../setup.jsp" %>
<%@ include file="../../auth.jsp" %>
<%
    List<Network> networks = NetworkServices.getByUserId(meId, RoleEnum.VISITOR, SqlLimit.ALL);
    Collections.sort(networks);
    Collections.reverse(networks);

    Integer nextQuestionRef = null;

    // Sorting networks by id
    Collections.sort(networks);
    Collections.reverse(networks);

    Integer hasIcon = null;
    Integer collectModeSetting = null;
    Boolean collectMode = false;
    Map<NetworkIntegerSettingEnum, Integer> networkIntegerSettings = null;
    UserToNetwork meToNetwork = null;
    String iconSrc = null;
%>

<div class="networks">

    <div class="help">
        <div class="help-rel">
            <a href="/d/how/#communities" target="_help"><img src="./img/help.png" alt="Help"></a>
        </div>
    </div>
    <div class="switch sm_header highlight3">Switch Communities</div>

    <% for (Network network : networks) {

        nextQuestionRef = FlowRuleServices.getNextQuestionRef(meId, network.getId());
        networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(network.getId());
        hasIcon = networkIntegerSettings.get(NetworkIntegerSettingEnum.UI_HAS_ICON);
        collectModeSetting = networkIntegerSettings.get(NetworkIntegerSettingEnum.MODE_COLLECT_ONLY);
        meToNetwork = UserToNetworkDao.getByUserIdAndNetworkId(null, meId,  network.getId());



        if (hasIcon != 0)
            iconSrc = network.getIconResourceUrl();
        else
            iconSrc = "./modules/networks/img/tree.png";

        if (meToNetwork.getBlockedOn() != null) { %>
            <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.blocked(network.getId())%>')">
        <% } else if (collectModeSetting == 0 ) { %>
            <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.smartGroups(network.getId())%>')">
        <% } else { %>
            <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.questions(network.getId())%>')">
        <% } %>

                <div class="item" id="<%= NetworkHtml.getNetworkId(network.getId()) %>">

                <div class="contents">
                    <div class="icon"><img src="<%= iconSrc %>" alt=""></div>
                    <div class="name smd_text"><%= StringUtils.concat(network.getName(), 10, "&#8230;") %></div>
                </div>

                <div class="tags">

                    <% if (nextQuestionRef != null) { %>
                        <div class="bullet" id="<%= NetworkHtml.getBulletId(network.getId()) %>"><img src="./img/dot-green-16.png"></div>
                        <script type="text/javascript">Animations.dance("#<%= NetworkHtml.getBulletId(network.getId()) %>", 10000, 10000)</script>
                    <% } %>

                    <% if (meToNetwork.getBlockedOn() != null) { %>
                        <div class="blocked vsm_button error_button vsm_text white">blocked</div>
                    <% } %>

                </div>

            </div>
        </a>

    <% } %>

</div>