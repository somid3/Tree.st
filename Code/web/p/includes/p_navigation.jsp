<% {
        /**
         * Network p_network = null;
         * String p_section = null;
         */
%>
    <div id="nav">
        <div id="logo" style="float: left">
            <a href="/"><img src="<%= p_network.getLogoResourceUrl() %>"></a>
        </div>

        <div id="nav-about" class="nav-button">
            <a href="../about"><span class="lg_text white">About</span></a>
        </div>

        <div id="nav-items" class="nav-button nav-highlighted">
            <a href="../items"><span class="lg_text white">Items</span></a>
        </div>
    </div>
<% } %>