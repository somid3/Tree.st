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

            <% if (!collab_c_network.isGlobal()) { %>
                <div class="vl_text dim">Check out the <a href="#" onclick="ND.go(event, NetworkDashboard.Section.SMART_GROUPS);"><span class="highlight6">Smart Groups</span></a></div>
            <% } %>

            <div class="vl_text dim2">Remember, if you wish to update your<br/> profile click on <a href="#" onclick="ND.go(event, NetworkDashboard.Section.ANSWERS, {vuid: <%= userId %>, vucs: '<%= userChecksum %>'});"><span class="highlight2">My profile</span></a></div>

        </div>
    </div>
</div>

<% if (!collab_c_network.isGlobal()) { %>

    <script type="text/javascript">
        var auto = setTimeout(
            function(){ ND.go(null, NetworkDashboard.Section.SMART_GROUPS);
            }, 15000);

        $(window).click (function() {
           clearTimeout(auto);
        });
    </script>

<% } %>

<script type="text/javascript">
    // Now that there no more questions, hide the network bullet
    var hBulletId = "bullet" + <%= network.getId() %>;
    $("#" + hBulletId).fadeOut(1000);

    // Now that there no more questions, hide the collaborate shortcut
    $("#network_shortcut_questions").fadeOut(1000);

</script>

<% } %>