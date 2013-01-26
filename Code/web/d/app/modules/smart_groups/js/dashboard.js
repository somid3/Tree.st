SmartGroupDashboard.Section = {
    SHARE_ITEMS: 1,
    MEMBERS: 2,
    SHARED_ITEM: 3
};


function SmartGroupDashboard () {

    this.networkId = null;
    this.smartGroupRef = null;

    /**
     * Removes selected class from all items on the network dashboard
     * and adds the selected class to the selector provided
     */
    this.clickItem = function (event, selector, url, data, callback) {

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
        parameters.nid = this.networkId;
        parameters.sgr = this.smartGroupRef;
        parameters = $.extend(parameters, data);

        Transitions.load('#smart_group_canvas', url, parameters, callback);
    };




    this.hideSmartGroup = function (event, smartGroupRef) {

        Event.preventDefault(event);

        var parameters = {};
        parameters.nid = this.networkId;
        parameters.sgr = smartGroupRef;

        // Hiding shared item
        $.post("./modules/smart_groups/actions/hide_smart_group.jsp", parameters, function(response) {

            // Send user to smart groups listing
            ND.go(event, NetworkDashboard.Section.SMART_GROUPS);

        });
    };

};

SmartGroupDashboard.go = function (event, sendTo, parameters, callback) {

    /* Loading the desired module */

    if (sendTo == null || sendTo == SmartGroupDashboard.Section.SHARE_ITEMS)

        this.clickItem(event, '#smart_group_shortcut_share', './modules/share/by_smart_group.jsp', parameters, callback);

    else if (sendTo == SmartGroupDashboard.Section.MEMBERS)

        this.clickItem(event, '#smart_group_shortcut_members', './modules/smart_groups/members.jsp', parameters, callback);

    else if (sendTo == SmartGroupDashboard.Section.SHARED_ITEM)

        this.clickItem(event, '#smart_group_shortcut_share', './modules/share/by_ref.jsp', parameters, callback);

};