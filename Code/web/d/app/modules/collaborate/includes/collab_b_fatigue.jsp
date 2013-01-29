<% {

    // Random id for the moving car...
    String collab_b_hImageId = HtmlUtils.getRandomId();
%>
<div class="question_note">
    <div class="canvas_container">
        <div class="container">

            <div id="<%= collab_b_hImageId %>" style="position: relative;"><img src="./modules/collaborate/img/fast.png"></div>

            <div class="vl_text dim">You are answering the questions too fast!</div>

            <div class="vl_text dim">Take a break, view the <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.smartGroups(networkId)%>');"><span class="highlight6">Smart Groups</span></a></div>

            <div class="vl_text dim">Or... To continue click on <a href="#" onclick="ND.go(event, NetworkDashboard.Section.QUESTIONS);"><span class="highlight2">Collaborate</span></a></div>

            <div class="vl_text dim2">Remember, if you wish to update your<br/> profile click on <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.profile(networkId)%>');"><span class="highlight2">My profile</span></a></div>

        </div>
    </div>
</div>

<script type="text/javascript">
    Animations.dance("#<%= collab_b_hImageId %>", 1000, 1000)
</script>
<% } %>