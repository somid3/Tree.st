<%@ include file="../../all.jsp" %>
<%
    Integer networkId = StringUtils.parseInt(request.getParameter("nid"));
    Integer sgr = StringUtils.parseInt(request.getParameter("sgr"));

    // Retrieving user to network relationship
    UserToNetwork utn = UserToNetworkDao.getByUserIdAndNetworkId(null, userId, networkId);

    String hSaveButtonId = HtmlUtils.getRandomId();
    String hFormId = HtmlUtils.getRandomId();
    String hVisibilityGroup = HtmlUtils.getRandomId();
    String hVisibilityShare = HtmlUtils.getRandomId();
    String hVisibilityPrivate = HtmlUtils.getRandomId();
%>

<div id="create_smart_group">
    <form id="<%= hFormId %>">

        <div class="message smd_text white">
            Smart Groups are continually running 24/7 searches that group the best individuals that match your search! Smart Groups
            can be shared with your community, or kept private
        </div>

        <div class="form-hor">
            <div class="element">
                <div class="name smd_header white">Name</div>
                <div class="input">
                    <div class="field"><input type="text" class="smd_input w150" maxlength="40" name="name" /></div>
                    <div class="example"></div>
                </div>
            </div>
            <div class="element">
                <div class="name smd_header white">Purpose or mission</div>
                <div class="input">
                    <div class="field"><input type="text" class="smd_input w150" maxlength="150" name="desc" /></div>
                </div>
                <div class="example vsm_text white">
                    i.e.: inter-disciplinary discussion...
                </div>
            </div>
            <div class="element">
                <div class="name smd_header white">Visibility</div>
                                                   
                <% if (!utn.getRole().isLowerThan(RoleEnum.EDITOR)) { %>
                    <div class="field smd_text white"><input name="share" type="radio" id="<%= hVisibilityGroup %>" value="6"> <label for="<%= hVisibilityGroup %>">Official network Smart Group</label></div>
                <% } %>

                <div class="field smd_text white"><input name="share" type="radio" id="<%= hVisibilityShare %>" value="3"> <label for="<%= hVisibilityShare %>">Shared Smart Group</label></div>
                <div class="field smd_text white"><input name="share" type="radio" id="<%= hVisibilityPrivate %>" value="0" checked> <label for="<%= hVisibilityPrivate %>">Visible only to me</label></div>
                <div class="example"></div>
            </div>
            <div class="actionable">
                <a href="#"><div id="<%= hSaveButtonId %>" class="md_button pink_button">Save Smart Group</div></a>
            </div>
            <div class="actionable">
                <div class="error smd_text"></div>
            </div>
        </div>
    </form>    

    <script type="text/javascript">

        Animations.toPosition("#create_smart_group", 30, 0, 600);

        SG = new SmartGroups();
        SG.networkId = <%= networkId %>;
        SG.smartGroupRef = <%= sgr %>;
        SG.networkDashboard = ND;
        SG.createSmartGroup(null, '<%= hFormId %>', '<%= hSaveButtonId %>');
    </script>
</div>
