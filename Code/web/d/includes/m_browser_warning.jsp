<% {
    String m_message = null;

    if (status == BrowserAcceptanceEnum.Status.UNKNOWN)
        m_message = "Your browser is unknown to Tree.st and could lead to limited functionality";
    else if (status == BrowserAcceptanceEnum.Status.BUGGY)
        m_message = "Warning, your browser is known to be buggy with Tree.st";

%>

    <div id="browser_warning" class="square">
        <span class="white"><%= m_message %> &mdash;</span>
        <a href="/d/browser" target="_blank"><span class="highlight2">view supported browsers</span></a>
    </div>

<% } %>
