<% {
    /**
     * Inputs variables
     *
     *   Network collab_c_network = null;
     */
%>

<div class="question_note">
    <div class="canvas_container">
        <div class="container">

            <div><img src="./modules/collaborate/img/done.png"></div>

            <div class="vl_text dim">Congratulations! Your profile for the<br/> <%= network.getName() %> community is complete!</div>

            <div class="vl_text dim">Check out the <a href="#" onclick="ND.go(event, NetworkDashboard.Section.SMART_GROUPS);"><span class="highlight6">Smart Groups</span></a></div>

            <div class="vl_text dim2">Remember, if you wish to update your<br/> profile click on <a href="#" onclick="ND.go(event, NetworkDashboard.Section.PROFILE, {vuid: <%= userId %>, vucs: '<%= userChecksum %>'});"><span class="highlight2">My profile</span></a></div>

        </div>
    </div>
</div>


<script type="text/javascript">

    // When a click occurs stop sending the user to the smart groups
    $(window).click (function() {
       clearTimeout(autoChange);
    });

    // Now that there no more questions, hide the network bullet
    $("#<%= NetworkHtml.getBulletId(networkId)%>").fadeOut(1000);

</script>

<% } %>