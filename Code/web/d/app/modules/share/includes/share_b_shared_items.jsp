<% {
    /* Inputs variables
     *
     *    // User that is logged in
     *    User share_b_me = null
     *
     *    // All the shared items that need to be displayed
     *    List<SharedItem> share_b_sharedItems = null;
     *
     *    // Smart group ref from where the request comes from
     *    Integer share_b_fromSmartGroupRef = null;
     *
     *    Integer share_b_settingSharedItemDisplayCreatedOn
     *    Integer share_b_settingSharedCommentDisplayCreatedOn
     *    Integer share_b_settingSharedCommentPointsPer
     */

    User share_c_me = share_b_me;
    Integer share_c_fromSmartGroupRef = share_b_fromSmartGroupRef;
    Integer share_c_settingSharedItemDisplayCreatedOn = share_b_settingSharedItemDisplayCreatedOn;
    Integer share_c_settingSharedCommentDisplayCreatedOn = share_b_settingSharedCommentDisplayCreatedOn;
    Integer share_c_settingSharedCommentPointsPer = share_b_settingSharedCommentPointsPer;

    for (SharedItem share_c_sharedItem : share_b_sharedItems) { %>

        <%@ include file="share_c_shared_item.jsp" %>

    <% } %>

<% } %>

