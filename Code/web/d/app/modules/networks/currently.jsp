<%@ include file="../../setup.jsp" %>
<% appDisableBlocked = false; %>
<%@ include file="../../auth.jsp" %>
<%
    // Retrieving next question user has left to answer
    Integer nextQuestionRef = FlowRuleServices.getNextQuestionRef(meId, homeId);

    // Counting the number of unread user message groups
    Integer unReadUserMessageGroupCount = UserMessageGroupDao.countUnReadByNetworkIdAndFromUserId(null, homeId, meId);

    // Retrieving network settings
    Map<NetworkAlphaSettingEnum, String> networkAlphaSettings = NetworkAlphaSettingEnum.getMapByNetworkId(homeId);
    Map<NetworkIntegerSettingEnum, Integer> networkIntegerSettings = NetworkIntegerSettingEnum.getMapByNetworkId(homeId);

    Integer hasLogo = networkIntegerSettings.get(NetworkIntegerSettingEnum.IS_UI_LOGO_SET);
%>

<script type="text/javascript">

    // Moving the currently menu
    Animations.toPosition("#currently", 0, 0, 600);

</script>

<%
    // Ensuring user is not blocked
    if (meToHome.getBlockedOn() != null)
        return;
%>

<div class="help">
    <div class="help-rel">
        <a href="/d/how/#share" target="_help"><img src="./img/help.png" alt="Help"></a>
    </div>
</div>
<a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.sharedItems(homeId)%>')">
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
<a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.smartGroups(homeId)%>')">
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
<a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.smartSearch(homeId)%>')">
    <div class="shortcut" id="network_shortcut_smart_search">
        <div class="contents">
            <div class="icon"><img src="./modules/networks/img/smart_search.png" alt="Smart Search"></div>
            <div class="name smd_text">Doctor Search</div>
        </div>
    </div>
</a>

<a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.all(homeId)%>')">
    <div class="shortcut" id="network_shortcut_all">
        <div class="contents">
            <div class="icon"><img src="./modules/networks/img/premium.png" alt="All"></div>
            <div class="name smd_text">All Doctors</div>
        </div>
    </div>
</a>

<br/>

<div class="help">
    <div class="help-rel">
        <a href="/d/how/#profile" target="_help"><img src="./img/help.png" alt="Help"></a>
    </div>
</div>

<a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.profile(homeId)%>')">
    <div class="shortcut" id="network_shortcut_profile">
        <div class="contents">
            <div class="icon"><img src="./modules/networks/img/profile.png" alt="My Profile"></div>
            <div class="name smd_text">My profile</div>
        </div>
    </div>
</a>

<a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.profileQuestions(homeId)%>')">
    <div class="shortcut" id="network_shortcut_questions">
        <div class="contents">
            <div class="icon"><img src="./modules/networks/img/questions.png" alt="Details"></div>
            <div class="name smd_text">Details</div>
        </div>
        <% if (nextQuestionRef != null) { %>
            <div class="bullet <%= NetworkHtml.getBulletClass(homeId) %>"><img src="./img/dot-16.png"></div>
        <% } %>
    </div>
</a>

<a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.profileMessages(homeId)%>')">
    <div class="shortcut" id="network_shortcut_messages">
        <div class="contents">
            <div class="icon"><img src="./modules/networks/img/email.png" alt="Details"></div>
            <div class="name smd_text">Messages</div>
        </div>
        <% if (unReadUserMessageGroupCount > 0) { %>
            <div class="notifications vsm_header"><%= unReadUserMessageGroupCount %></div>
        <% } %>
    </div>
</a>