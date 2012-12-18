<%@ include file="../../all.jsp" %>
<%
    List<Network> networks = NetworkServices.getByUserId(userId, RoleEnum.VISITOR);
    Collections.sort(networks);
    Collections.reverse(networks);

    String hItemId = null;
    Integer nextQuestionRef = null;
    String hBulletId = null;

    // Sorting networks by id
    Collections.sort(networks);
    Collections.reverse(networks);

    // Separate private and global networks
    List<Network> privateNetworks = new ArrayList<Network>();
    List<Network> globalNetworks = new ArrayList<Network>();
    for (Network network : networks) {
        if (network.isGlobal())
            globalNetworks.add(network);
        else
            privateNetworks.add(network);
    }

    // First network to display
    Integer firstNetwork = null;
%>

<div class="networks">

    <div class="help">
        <div class="help-rel">
            <a href="/d/how/#communities" target="_help"><img src="./img/help.png" alt="Help"></a>
        </div>
    </div>
    <div class="tab sm_header highlight3">

        Switch Communities</div>

    <% for (Network network : privateNetworks) {

        nextQuestionRef = FlowRuleServices.getNextQuestionRef(userId, network.getId());

        hItemId = "network" + network.getId();
        hBulletId = "bullet" + network.getId();
    %>

        <a href="#" onclick="LeftMenu.goToNetwork(event, <%= network.getId() %>);">
            <div class="item" id="<%= hItemId %>">
                <div class="contents">
                    <div class="icon"><img src="<%= network.getIconResourceUrl() %>" alt=""></div>
                    <div class="name smd_text"><%= StringUtils.concat(network.getName(), 14, "&#8230;") %></div>
                </div>
                <% if (nextQuestionRef != null) { %>
                    <div class="bullet" id="<%= hBulletId %>"><img src="./img/dot-green-16.png"></div>
                    <script type="text/javascript">Animations.dance("#<%= hBulletId%>", 10000, 30000)</script>
                <% } %>
            </div>
        </a>

    <% } %>

    <div style="border-top: 1px dotted #aaa; width: 50%; margin: 5px auto;"></div>
    <div class="sm_text dim2">About you</div>

    <% for (Network network : globalNetworks) {

        nextQuestionRef = FlowRuleServices.getNextQuestionRef(userId, network.getId());

        hItemId = "network" + network.getId();
        hBulletId = "bullet" + network.getId();
    %>
        <a href="#" onclick="LeftMenu.goToNetwork(event, <%= network.getId() %>);">
            <div class="item" id="<%= hItemId %>">
                <div class="contents">
                    <div class="icon"><img src="<%= network.getIconResourceUrl() %>" alt=""></div>
                    <div class="name smd_text"><%= StringUtils.concat(network.getName(), 14, "&#8230;") %></div>
                </div>
                <% if (nextQuestionRef != null) { %>
                    <div class="bullet" id="<%= hBulletId %>"><img src="./img/dot-green-16.png"></div>
                    <script type="text/javascript">Animations.dance("#<%= hBulletId%>", 5000, 10000)</script>
                <% } %>
            </div>
        </a>

    <% } %>
</div>

<%
    // Starting with the latest community
    firstNetwork = privateNetworks.get(0).getId();
%>

<script type="text/javascript">
    LeftMenu.goToNetwork(null, <%= firstNetwork %>);
</script>