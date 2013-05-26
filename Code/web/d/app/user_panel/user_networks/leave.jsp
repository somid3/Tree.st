<%@ include file="../../setup.jsp" %>
<% appDisableBlocked = false; %>
<%@ include file="../../auth.jsp" %>
<%
    String app_d_title = null;
    String app_d_message = null;
    HtmlDesign.Positions app_d_position = null;

    String hSettingsId = HtmlUtils.getRandomId();
    String hFormId = HtmlUtils.getRandomId();

    Object app_e_selectedValue = null;
    Map<String, Object> app_e_options = new LinkedHashMap<String, Object>();
%>

<div id="<%= hSettingsId %>" class="user_setting_container form-verhor">

    <span class="sm_text dim">
        We are still working on this feature. Please email us at <a href="mailto:<%= Vars.supportEmail %>"><span class="highlight2"><%= Vars.supportEmail %></span></a>
        to remove you from this community or delete you account.
    </span>

</div>