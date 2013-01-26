<%@ include file="../../all.jsp" %>
<%
    List<Network> networks = NetworkServices.getByUserId(userId, RoleEnum.VISITOR, SqlLimit.ALL);
    Collections.sort(networks);
    Collections.reverse(networks);

    Integer nextQuestionRef = null;

    // Sorting networks by id
    Collections.sort(networks);
    Collections.reverse(networks);

    // First network to display
    Integer firstNetwork = null;
%>

<div class="networks">

    <div class="help">
        <div class="help-rel">
            <a href="/d/how/#communities" target="_help"><img src="./img/help.png" alt="Help"></a>
        </div>
    </div>
    <div class="switch sm_header highlight3">Switch Communities</div>

    <% for (Network network : networks) {
        nextQuestionRef = FlowRuleServices.getNextQuestionRef(userId, network.getId()); %>

        <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.smartGroups(network.getId())%>')">
            <div class="item" id="<%= NetworkHtml.getNetworkId(network.getId()) %>">
                <div class="contents">
                    <div class="icon"><img src="<%= network.getIconResourceUrl() %>" alt=""></div>
                    <div class="name smd_text"><%= StringUtils.concat(network.getName(), 10, "&#8230;") %></div>
                </div>
                <% if (nextQuestionRef != null) { %>
                    <div class="bullet" id="<%= NetworkHtml.getBulletId(network.getId()) %>"><img src="./img/dot-green-16.png"></div>
                    <script type="text/javascript">Animations.dance("#<%= NetworkHtml.getBulletId(network.getId()) %>", 10000, 30000)</script>
                <% } %>
            </div>
        </a>

    <% } %>

</div>