NetworkDashboard.Section = {
    QUESTIONS: 1,
    SMART_SEARCH: 2,
    SMART_GROUPS: 3,
    SMART_GROUP: 4,
    SHARED_ITEMS: 5,
    PROFILE: 6,
    ALL: 7,
    FINDER: 8,
    MEMBER: 9,
    BLOCKED: 10,
    MESSAGE_GROUPS: 11,
    MESSAGES: 12
};

function NetworkDashboard () {

    this.networkId = null;

    /**
     * Removes selected class from all items on the network dashboard
     * and adds the selected class to the selector provided
     */
    this.clickItem = function (event, selector, url, parameters, callback) {

        Event.preventDefault(event);

        CurrentlyMenu.unhighlightAll();

        NetworkDashboard.unhighlightAll();

        UserPanel.unhighlightUser();

        // Add full opacity to the left menu
        LeftMenu.fullOpacity();

        // Display the canvas loading display
        NetworkDashboard.displayLoading();

        // Creating a callback that hides the canvas loading
        var newCallback = function () {
            if (callback) callback();
            NetworkDashboard.hideLoading();
        };

        // If no search input has been provided, then reset the search field
        if (!parameters || (parameters && !parameters.s))
            Finder.setupFinder();

        // Add highlight to the selected box
        if (selector) {
            $(selector).addClass("selected", 250);
        }

        var data = {};
        data.nid = this.networkId;
        data = $.extend(data, parameters);

        /*
         * Do not use fading with this loading event since slower machines have
         * trouble fading such large areas of space
         */
        Transitions.load('#canvas', url, data, newCallback);
    };

    this.go = function (event, sendTo, parameters, callback) {

        Event.preventDefault(event);

        if (sendTo == null  || sendTo == NetworkDashboard.Section.QUESTIONS)

            this.clickItem(event, '#network_shortcut_questions', './modules/questions/question_display.jsp', parameters, callback);

        else if (sendTo == NetworkDashboard.Section.SMART_SEARCH)

            this.clickItem(event, "#network_shortcut_smart_search", './modules/smart_groups/smart_search.jsp', parameters, callback);

        else if (sendTo == NetworkDashboard.Section.SMART_GROUPS)

            this.clickItem(event, '#network_shortcut_smart_groups', './modules/smart_groups/by_network.jsp', parameters, callback);

        else if (sendTo == NetworkDashboard.Section.SMART_GROUP)

            this.clickItem(event, '#network_shortcut_smart_groups', './modules/smart_groups/dashboard.jsp', parameters, callback);

        else if (sendTo == NetworkDashboard.Section.SHARED_ITEMS)

            this.clickItem(event, '#network_shortcut_share', './modules/share/by_network.jsp', parameters, callback);

        else if (sendTo == NetworkDashboard.Section.MEMBER)

            this.clickItem(event, '#network_shortcut_all', './modules/profiles/dashboard.jsp', parameters, callback);

        else if (sendTo == NetworkDashboard.Section.PROFILE)

            this.clickItem(event, '#network_shortcut_profile', './modules/profiles/dashboard.jsp', parameters, callback);

        else if (sendTo == NetworkDashboard.Section.MESSAGE_GROUPS)

            this.clickItem(event, '#network_shortcut_messages', './modules/user_messages/groups.jsp', parameters, callback);

        else if (sendTo == NetworkDashboard.Section.MESSAGES)

            this.clickItem(event, '#network_shortcut_messages', './modules/user_messages/messages.jsp', parameters, callback);

        else if (sendTo == NetworkDashboard.Section.ALL)

             this.clickItem(event, '#network_shortcut_all', './modules/all/dashboard.jsp', parameters, callback);

        else if (sendTo == NetworkDashboard.Section.FINDER)

            this.clickItem(event, "#finder_all", './modules/finder/finder.jsp', parameters, callback);

        else if (sendTo == NetworkDashboard.Section.BLOCKED)

            this.clickItem(event, null, './modules/questions/blocked.jsp', parameters, callback);
    };

}

NetworkDashboard.unhighlightAll = function () {

    $("#finder_all").removeClass("selected");

};

NetworkDashboard.displayLoading = function () {
    var $canvasLoading = $("#canvas_loading");
    var $canvas = $("#canvas");

    $canvas.empty();
    $canvasLoading.show();
};

NetworkDashboard.hideLoading = function () {
    var $canvasLoading = $("#canvas_loading");
    $canvasLoading.hide();
};
