<%
/* Inputs variables
 *
 *   String app_b_message = null;
 *   boolean app_b_withCanvasContainer = true;
 *
 */
{ %>

<div class="canvas_error">

    <% if (app_b_withCanvasContainer) { %>
        <div class="canvas_container">
    <% } %>

    <div class="container">
        <div class="content">
            <div class="message smd_text highlight2"><%= app_b_message %></div>
        </div>
    </div>

    <% if (app_b_withCanvasContainer) { %>
        </div>
    <% } %>

</div>

<% } %>