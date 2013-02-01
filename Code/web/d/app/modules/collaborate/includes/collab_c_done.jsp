<% {
    /**
     * Inputs variables
     *
     *   Integer collab_c_userId = null;
     *   Network collab_c_network = null;
     *   Map<NetworkIntegerSettingEnum, Integer> collab_c_networkIntegerSettings = null;
     */

    // Retrieving first dependent network, if any, that has a available next question
    List<NetworkDependsOn> collab_c_dependentNetworks = NetworkDependsOnDao.getByNetworkId(null, collab_c_network.getId());
    Network collab_c_firstDependentAndAvailableNetwork = null;
    Integer collab_c_nextQuestionRef = null;
    Network firstDependentAndAvailableNetwork = null;
    for (NetworkDependsOn collab_c_dependentNetwork : collab_c_dependentNetworks) {

        // Attempting to see if any questions remain open on this dependent network
        collab_c_nextQuestionRef = FlowRuleServices.getNextQuestionRef(collab_c_userId, collab_c_dependentNetwork.getDependsOn());

        // Does the dependent network have an available question?
        if (collab_c_nextQuestionRef != null) {

            // Retrieving the available dependent network
            firstDependentAndAvailableNetwork = NetworkDao.getById(null, collab_c_dependentNetwork.getDependsOn());
            break;
        }

    }
%>

<div class="question_note">
    <div class="canvas_container">
        <div class="container">

            <div><img src="./modules/collaborate/img/done.png"></div>

            <div class="vl_text dim">Congratulations! Your profile for the<br/> <%= collab_c_network.getName() %> community is complete!</div>

            <% if (firstDependentAndAvailableNetwork != null) { %>
                <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.questions(firstDependentAndAvailableNetwork.getId())%>');">
                    <div class="vl_text highlight2">Continue and complete your &quot;<%= firstDependentAndAvailableNetwork.getName() %>&quot; profile!</div>
                </a>
            <% } %>

        </div>
    </div>
</div>


<script type="text/javascript">

    // Now that there no more questions, hide the network bullet
    $("#<%= NetworkHtml.getBulletId(collab_c_network.getId())%>").fadeOut(1000);

</script>

<% } %>