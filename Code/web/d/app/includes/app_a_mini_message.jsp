<%
/* Inputs variables
 *
 *   String app_a_message = null;
 *   boolean app_a_withCanvasContainer = true;
 *
 */
{ %>

<div class="canvas_mini_message">

    <% if (app_a_withCanvasContainer) { %>
        <div class="canvas_container">
    <% } %>

    <div class="container">
        <div class="content">
            <div class="message smd_text dim"><%= app_a_message %></div>
        </div>
    </div>

    <% if (app_a_withCanvasContainer) { %>
        </div>
    <% } %>

</div>

<% } %>