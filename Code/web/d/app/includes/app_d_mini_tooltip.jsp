<% {
/* Inputs variables
 *
 *   String app_d_title = null;
 *   String app_d_message = null;
 *   HmltDesign.Position app_d_position = null;
 *
 */
    String app_d_hMiniTooltipId = HtmlUtils.getRandomId();

    Integer app_d_top = 1;
    Integer app_d_left = 1;
    switch (app_d_position) {
        case BOTTOM:
            app_d_top = 30;
            app_d_left = -130;
            break;
        case LEFT:
            app_d_top = 1;
            app_d_left = -260;
            break;
        case RIGHT:
            app_d_top = 1;
            app_d_left = 5;
            break;
    }

%>

    <span onclick="Event.preventDefault(event); $('#<%= app_d_hMiniTooltipId %>').fadeToggle(100);" class="mini_tooltip_trigger sm_text highlight2">(?)</span>

    <span id="<%= app_d_hMiniTooltipId %>" class="mini_tooltip">

       <a href="#" onclick="Event.preventDefault(event); $('#<%= app_d_hMiniTooltipId %>').fadeToggle(100);">

           <div class="mini_tooltip_bubble sm_text rounded white shadow" style="top: <%= app_d_top %>px; left: <%= app_d_left %>px;">

               <div class="close"><img src="/d/app/img/close_white.png"></div>
               <div class="title sm_header"><%= app_d_title %></div>
               <div class="message sm_text"><%= app_d_message %></div>

           </div>

       </a>

   </span>


<% } %>