<% {
    /**
     * Inputs variables
     *
     *   Integer collab_c_userId = null;
     *   Network collab_c_network = null;
     *   Map<NetworkIntegerSettingEnum, Integer> collab_c_networkIntegerSettings = null;
     */

%>

<div class="first_note">
    <div class="canvas_container">
        <div class="container">

            <div><img src="/d/assets/done.png"></div>

            <div class="vl_text dim">

                Congratulations!

                <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.profile(collab_c_network.getId())%>');">
                    <span class="vl_text highlight2">Your profile</span>
                </a>

                for the<br/> <%= collab_c_network.getName() %> community is complete!<br/>

                <br/>

                Continue and view the

                <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.smartGroups(collab_c_network.getId()) %>');">
                    <span class="vl_text highlight2">Smart Groups</span>
                </a>

                of <%= Vars.name %>

                 
            </div>


        </div>
    </div>
</div>


<script type="text/javascript">

    // Now that there no more questions, hide the network bullet
    $(".<%= NetworkHtml.getBulletClass(collab_c_network.getId())%>").fadeOut();

</script>

<% } %>