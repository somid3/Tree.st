function SmartGroupDashboard() {};

SmartGroupDashboard.Section = {
    SHARED_ITEMS: 1,
    MEMBERS: 2,
    SHARED_ITEM: 3
};

SmartGroupDashboard.hideSmartGroup = function (event, networkId, smartGroupRef) {

    Event.preventDefault(event);

    var parameters = {};
    parameters.nid = networkId;
    parameters.sgr = smartGroupRef;

    // Hiding shared item
    $.post("./modules/smart_groups/actions/hide_smart_group.jsp", parameters, function(response) {

        // Send user to smart groups listing
        ND.go(event, NetworkDashboard.Section.SMART_GROUPS);

    });
};

/**
 * Removes selected class from all items on the network dashboard
 * and adds the selected class to the selector provided
 */
SmartGroupDashboard.clickItem = function (event, selector, url, data, callback) {

    Event.preventDefault(event);

    // Remove all highlights from network dash board
    $("#smart_group_dashboard .shortcut").each(
        function() {
            $(this).removeClass("selected");
        }
    );

    // Add highlight to the selected box
    if (selector) {
        $(selector).addClass("selected", 250);
    }

    var parameters = {};
    parameters = $.extend(parameters, data);

    Transitions.load('#smart_group_canvas', url, parameters, callback);
};


SmartGroupDashboard.go = function (event, sendTo, parameters, callback) {

    if (sendTo == null || sendTo == SmartGroupDashboard.Section.SHARED_ITEMS)

        SmartGroupDashboard.clickItem(event, '#smart_group_shortcut_share', './modules/share/by_smart_group.jsp', parameters, callback);

    else if (sendTo == SmartGroupDashboard.Section.MEMBERS)

        SmartGroupDashboard.clickItem(event, '#smart_group_shortcut_members', './modules/smart_groups/members.jsp', parameters, callback);

    else if (sendTo == SmartGroupDashboard.Section.SHARED_ITEM)

        SmartGroupDashboard.clickItem(event, '#smart_group_shortcut_share', './modules/share/by_ref.jsp', parameters, callback);

};