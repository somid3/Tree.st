<%@ include file="../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));

    // Retrieving network
    Network network = NetworkDao.getById(null, networkId);

    // Retrieving next question user has left to answer
    Integer nextQuestionRef = FlowRuleServices.getNextQuestionRef(userId, network.getId());

    // Retrieving user to network
    UserToNetwork utn = UserToNetworkDao.getByUserIdAndNetworkId(null, userId, networkId);

    // Retrieving network settings
    Map<NetworkAlphaSettingEnum, String> networkAlphaSettings = NetworkAlphaSettingEnum.getMapByNetworkId(networkId);
    Map<NetworkIntegerSettingEnum, Integer> networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(networkId);

    Integer hasLogo = networkIntegerSettings.get(NetworkIntegerSettingEnum.UI_HAS_LOGO);

    String singularVocabulary = networkAlphaSettings.get(NetworkAlphaSettingEnum.VOCAB_USER_SINGULAR);
    String pluralVocabulary = networkAlphaSettings.get(NetworkAlphaSettingEnum.VOCAB_USER_PLURAL);
%>

<a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.all(network.getId())%>')">
    <div class="shortcut" id="network_shortcut_all">
        <div class="contents">
            <div class="icon"><img src="./modules/networks/img/premium.png" alt="All <%= network.getTotalMembers() %> <%= pluralVocabulary %>"></div>
            <div class="name smd_text">All <%= network.getTotalMembers() %> <%= pluralVocabulary %></div>
        </div>
    </div>
</a>

<div class="help">
    <div class="help-rel">
        <a href="/d/how/#share" target="_help"><img src="./img/help.png" alt="Help"></a>
    </div>
</div>
<a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.sharedItems(network.getId())%>')">
    <div class="shortcut" id="network_shortcut_share">
        <div class="contents">
            <div class="icon"><img src="./modules/networks/img/share.png" alt="Share"></div>
            <div class="name smd_text">Shared Feed</div>
        </div>
    </div>
</a>

<div class="help">
    <div class="help-rel">
        <a href="/d/how/#groups" target="_help"><img src="./img/help.png" alt="Help"></a>
    </div>
</div>
<a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.smartGroups(network.getId())%>')">
    <div class="shortcut" id="network_shortcut_smart_groups">
        <div class="contents">
            <div class="icon"><img src="./modules/networks/img/smart_groups.png" alt="Smart Groups"></div>
            <div class="name smd_text">Smart Groups</div>
        </div>
    </div>
</a>

<div class="help">
    <div class="help-rel">
        <a href="/d/how/#finder" target="_help"><img src="./img/help.png" alt="Help"></a>
    </div>
</div>
<a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.smartSearch(network.getId())%>')">
    <div class="shortcut" id="network_shortcut_smart_search">
        <div class="contents">
            <div class="icon"><img src="./modules/networks/img/smart_search.png" alt="Smart Search"></div>
            <div class="name smd_text"><%= singularVocabulary %> Search</div>
        </div>
    </div>
</a>

<br/>

<a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.questions(network.getId())%>')">
    <div class="shortcut" id="network_shortcut_questions">
        <div class="contents">
            <div class="icon"><img src="./modules/networks/img/collaborate.png" alt="Collaborate"></div>
            <div class="name smd_text">Collaborate</div>
        </div>
        <% if (nextQuestionRef != null) { %>
            <div class="bullet" id="<%= NetworkHtml.getBulletId(network.getId()) %>"><img src="./img/dot-green-16.png"></div>
        <% } %>
    </div>
</a>

<div class="help">
    <div class="help-rel">
        <a href="/d/how/#profile" target="_help"><img src="./img/help.png" alt="Help"></a>
    </div>
</div>

<a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.profile(network.getId())%>')">
    <div class="shortcut" id="network_shortcut_profile">
        <div class="contents">
            <div class="icon"><img src="./modules/networks/img/profile.png" alt="My Profile"></div>
            <div class="name smd_text">My profile</div>
        </div>
    </div>
</a>

<script type="text/javascript">

    // Moving the currently menu
    Animations.toPosition("#currently", 0, 0, 600);

    // Changing header color
    $("#header").css("background-color", "<%= NetworkAlphaSettingEnum.UI_HEADER_BACKGROUND_COLOR.getValueByNetworkId(networkId) %>")

    // Changing the logo
    // TODO: fix this, place in javascript classes
    // TODO: fix this, place in javascript classes
    // TODO: fix this, place in javascript classes
    <% if (hasLogo != 0) { %>
        NetworkDashboard.showCustomLogo("<%= network.getLogoResourceUrl() %>");
    <% } else { %>
        NetworkDashboard.showDefaultLogo("<%= StringUtils.concat(network.getName().replaceAll("\"", "'"), 14, "...") %>");
    <% } %>


</script>