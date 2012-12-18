<%
/* Inputs variables
 *
 *   boolean app_c_withCanvasContainer = true;
 *   String app_c_hCanvasLoadingId = null;
 *
 */
{ %>

<div class="canvas_loading" id="<%= app_c_hCanvasLoadingId %>">

    <% if (app_c_withCanvasContainer) { %>
        <div class="canvas_container">
    <% } %>

        <img src="./img/loading.gif">

    <% if (app_c_withCanvasContainer) { %>
        </div>
    <% } %>

</div>

<% } %>