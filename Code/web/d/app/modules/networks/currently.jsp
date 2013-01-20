<%@ include file="../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));

    // Retrieving network
    Network network = NetworkDao.getById(null, networkId);

    // Retrieving user to network
    UserToNetwork utn = UserToNetworkDao.getByUserIdAndNetworkId(null, userId, networkId);

    // Retrieving next available question to see what tab to open
    Integer nextQuestionRef = FlowRuleServices.getNextQuestionRef(userId, network.getId());

    // Retrieving network settings
    String singularVocabulary = NetworkAlphaSettingEnum.VOCAB_USER_SINGULAR.getValueByNetwork(networkId);
    String pluralVocabulary = NetworkAlphaSettingEnum.VOCAB_USER_PLURAL.getValueByNetwork(networkId);
%>
<script type="text/javascript">

    ND = new NetworkDashboard();
    ND.networkId = <%= networkId %>;

    <% if (!network.isGlobal()) { %>

        // Beginning smart search
        SS = new SmartSearch();
        SS.networkId = <%= networkId %>;

        // Loading the network dashboard
        Transitions.load("#dashboard", "./modules/networks/dashboard.jsp", {nid : <%= networkId %>});

    <% } %>

</script>

<div class="header">
    <span class="smd_header dim"><%= StringUtils.concat(network.getName(), 18, "&hellip;") %></span>
</div>

<% if (!network.isGlobal()) { %>

    <a href="#" onclick="ND.go(event, NetworkDashboard.Section.ALL);">
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
    <a href="#" onclick="ND.go(event, NetworkDashboard.Section.SHARE);">
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
    <a href="#" onclick="ND.go(event, NetworkDashboard.Section.SMART_GROUPS);">
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
    <a href="#" onclick="ND.go(event, NetworkDashboard.Section.SMART_SEARCH);">
        <div class="shortcut" id="network_shortcut_smart_search">
            <div class="contents">
                <div class="icon"><img src="./modules/networks/img/smart_search.png" alt="Smart Search"></div>
                <div class="name smd_text"><%= singularVocabulary %> Search</div>
            </div>
        </div>
    </a>

    <br/>

<% } %>

<% if (nextQuestionRef != null) { %>
    <a href="#" onclick="ND.go(event, NetworkDashboard.Section.QUESTIONS);">
        <div class="shortcut" id="network_shortcut_questions">
            <div class="contents">
                <div class="icon"><img src="./modules/networks/img/collaborate.png" alt="Collaborate"></div>
                <div class="name smd_text">Collaborate</div>
            </div>
        </div>
    </a>
<% } %>

<div class="help">
    <div class="help-rel">
        <a href="/d/how/#profile" target="_help"><img src="./img/help.png" alt="Help"></a>
    </div>
</div>
<a href="#" onclick="ND.go(event, NetworkDashboard.Section.ANSWERS, {vuid: <%= userId %>, vucs: '<%= userChecksum %>'});">
    <div class="shortcut" id="network_shortcut_profile">
        <div class="contents">
            <div class="icon"><img src="./modules/networks/img/profile.png" alt="My Profile"></div>
            <div class="name smd_text">My profile</div>
        </div>
    </div>
</a>

<script type="text/javascript">

    Animations.toPosition("#currently", 0, 0, 600);

    /**
     * Is there a particular region we need to move the user
     * to? This redirects the user to a particular smart
     * groups, people finder, etc. It also sends the user
     * to a particular location in the user panel
     */

    // Redirect user to user panel?
    var goSetFace = Go.checkAndRemove("go_set_face");
    var goPhotoUpload = Go.checkAndRemove("go_photo_upload");

    // Redirect user to particular module?
    var goSgr = Go.checkAndRemove("go_sgr");
    var goVuid = Go.checkAndRemove("go_vuid");

    // Doing the redirections
    if (goSetFace != null) {

        // Go to the page where user crops face
        UserPanel.view(null, function () {
            UserPanel.go(null, UserPanel.Section.PHOTOS, null, function() {
                setTimeout(function() { UserPhotos.go(null, UserPhotos.Section.SET_FACE); }, 500);
            });
        });

    } else if (goPhotoUpload != null) {

        UserPanel.view(null, function () {
            UserPanel.go(null, UserPanel.Section.PHOTOS);
        });

    } else if (goVuid != null) {

        ND.viewProfile(null, goVuid);

    } else if (goSgr != null) {

        ND.go(null, NetworkDashboard.Section.SMART_GROUP, {nid: ND.networkId, sgr: goSgr});

    } else {

        // No, define where the network will start
        <% if (network.isGlobal()) { %>

            ND.go(null, NetworkDashboard.Section.QUESTIONS);

        <% } else if (nextQuestionRef != null) { %>

            ND.go(null, NetworkDashboard.Section.QUESTIONS);

        <% } else { %>

            ND.go(null, NetworkDashboard.Section.SMART_GROUPS);

        <% } %>

    }

</script>