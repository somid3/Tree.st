<% {
 /* Inputs variables
     *
     *  Integer share_e_networkId = null;
     */
%>

<div class="question_note">
    <div class="canvas_container">
        <div class="container">

            <div><img src="./modules/share/img/first.png"></div>

            <div class="vl_text dim">Be the first one to share a message!</div>

            <div class="vl_text dim">Create or click on any
            <a href="#" onclick="HashRouting.setHash(event, '<%= HashRouting.smartGroups(share_e_networkId) %>');"><span class="highlight6">Smart Groups</span></a><br/>
            and share your message from there!</div>

        </div>
    </div>
</div>

<% } %>