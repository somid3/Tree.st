<% {
    /**
     * Inputs variables
     *
     *   Network collab_b_network = null;
     */

    // Random id for the moving car...
    String collab_b_hImageId = HtmlUtils.getRandomId();
%>
<div class="question_note">
    <div class="canvas_container">
        <div class="container">

            <div id="<%= collab_b_hImageId %>" style="position: relative;"><img src="./modules/questions/img/fast.png"></div>

            <div class="vl_text dim">You are answering the questions too fast!</div>

            <div class="vl_text dim">Take a break, view the <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.smartGroups(collab_b_network.getId())%>');"><span class="highlight6">Smart Groups</span></a></div>

            <div class="vl_text dim">Or... To continue click on <a href="#" onclick="ND.go(event, NetworkDashboard.Section.QUESTIONS);"><span class="highlight2">Details</span></a></div>

        </div>
    </div>
</div>

<script type="text/javascript">
    Animations.dance("#<%= collab_b_hImageId %>", 1000, 1000)
</script>
<% } %>