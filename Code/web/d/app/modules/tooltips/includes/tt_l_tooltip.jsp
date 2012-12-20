<% {
    /**
     * Inputs variables
     *
     *   TooltipEnum tt_l_tooltip = null;
     *   Integer tt_l_width = null;
     *   Integer tt_l_left = null;
     *   Integer tt_l_top = null;
     *   String tt_l_title = null;
     *   String tt_l_message = null;
     *   String tt_l_gotit = null;
     *   String tt_l_direction = null;
     *   String tt_l_style = null;
     *
     */

    tt_l_style = StringUtils.emptyIfNull(tt_l_style);
    tt_l_direction = StringUtils.defaultIfNull(tt_l_direction, "tip_right");
%>

<div id="<%= tt_l_tooltip.getContainerHtmlId() %>">
    <div class="tooltip glow4" id="<%= tt_l_tooltip.getTooltipHtmlId() %>" style="width: <%= tt_l_width %>px; left: <%= tt_l_left %>px; top: <%= tt_l_top %>px; <%= tt_l_style%>">
        <a href="#" onclick="TT.next(event, '<%= tt_l_tooltip.getContainerHtmlId() %>', '<%= tt_l_tooltip.getTooltipHtmlId() %>', '<%= tt_l_tooltip.getValue() %>');">

            <% if (tt_l_title != null) { %>
                <div class="title smd_text"><%= tt_l_title %></div>
            <% } %>

            <div class="<%= tt_l_direction %>">
                <div class="box lg_text"><%= tt_l_message %></div>
            </div>

            <div class="tools">
                <% if (tt_l_gotit != null) { %>
                    <div class="gotit lg_shadow md_text"><%= tt_l_gotit %></div>
                <% } %>
            </div>

        </a>
    </div>
</div>
<% } %>
