<%@ include file="../../all.jsp" %>
<%
    Integer viewUserId = StringUtils.parseInt(request.getParameter("vuid"));

    // Retrieving user being viewed
    User viewed = UserDao.getById(null, viewUserId);

    // Retrieving viewed user to smart group mappings
    List<UserToSmartGroup> userToSmartGroups = UserToSmartGroupDao.getByNetworkIdAndUserId(null, homeId, viewUserId);

    // Gathering all smart groups viewed user is a member of has marked as favorite
    SmartGroup group = null;
    List<SmartGroup> smartGroups = new ArrayList<SmartGroup>();
    for (UserToSmartGroup userToSmartGroup : userToSmartGroups) {

        // Ensuring smart group mapping occurs because user is interested
        if (userToSmartGroup.isActive()) {

            // Ensuring smart group has correct visibility
            group = SmartGroupDao.getNonHiddenByNetworkIdAndRefAndLowestVisibility(null, userToSmartGroup.getNetworkId(), userToSmartGroup.getSmartGroupRef(), SmartGroupVisibilityEnum.SHARED);
            if (group != null)
                smartGroups.add(group);
        }
    }

    // Sort collection by size
    Collections.sort(smartGroups, new SmartGroupComparatorSize());

    // Reverse the order to have the largest smart groups first
    Collections.reverse(smartGroups);
%>

<div class="canvas_container">
<%
String sgroup_d_highlight = null;
Integer sgroup_d_userId = meId;
for (SmartGroup sgroup_d_smartGroup : smartGroups) { %>

    <%@ include file="includes/sgroup_d_smart_group_line.jsp"%>

<% } %>

<% if (smartGroups.isEmpty()) {

    String app_a_message = viewed.getFirstName() + " is not in any Smart Groups yet!";
    boolean app_a_withCanvasContainer = false; %>
    <%@ include file="../../includes/app_a_mini_message.jsp" %>

<% } %>
</div>

