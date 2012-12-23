<% {
/* Inputs variables
 *
 *   Object app_e_selectedValue = null;
 *   Map<String, Object> app_e_options = null;
 *
 */

  String app_e_value = null;
  Object app_e_selected ="";
  for (String app_e_description : app_e_options.keySet()) {

      // Retrieving the value of the option
      app_e_value = app_e_options.get(app_e_description).toString();

      // Deletemining if option should be selected
      if ()
      app_e_selected ="";

  %>

        <option value="<%= app_e_value %>"><%= app_e_description %></option>

  <% } %>


<% } %>