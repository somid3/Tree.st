<% {
/* Inputs variables
 *
 *   Object app_e_selectedValue = null;
 *   Map<String, Object> app_e_options = null;
 */

  String app_e_value = null;
  Object app_e_selected = null;

  for (String app_e_description : app_e_options.keySet()) {

      // Retrieving the value of the option
      app_e_value = app_e_options.get(app_e_description).toString();

      // Determining if option should be selected
      if (app_e_selectedValue.toString().equals(app_e_value))
        app_e_selected = " selected=\"selected\" ";
      else
        app_e_selected = "";
  %>
        <option value="<%= app_e_value %>"<%= app_e_selected %>><%= app_e_description %></option>
  <% } %>
<% } %>