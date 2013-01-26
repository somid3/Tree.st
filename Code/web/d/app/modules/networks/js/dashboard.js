NetworkDashboard.Section = {
    QUESTIONS: 1,
    SMART_SEARCH: 2,
    SMART_GROUPS: 3,
    SMART_GROUP: 4,
    SHARED_ITEMS: 5,
    PROFILE: 6,
    ALL: 7,
    FINDER: 8
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

        // Scroll to the top of the page
//        Animations.scrollToTop();

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

    /**
     * Displays the network profile of the user id provided
     * @param userId
     */
    this.viewProfile = function (event, viewUserId, parameters, callback) {

        Event.preventDefault(event);

        var data = {};
        data.vuid = viewUserId;
        data = $.extend(data, parameters);

        this.go(event, NetworkDashboard.Section.PROFILE, data, callback);

    };

    this.go = function (event, sendTo, parameters, callback) {

        Event.preventDefault(event);

        if (sendTo == null  || sendTo == NetworkDashboard.Section.QUESTIONS)

            this.clickItem(event, '#network_shortcut_questions', './modules/collaborate/question_display.jsp', parameters, callback);

        else if (sendTo == NetworkDashboard.Section.SMART_SEARCH)

            this.clickItem(event, "#network_shortcut_smart_search", './modules/smart_groups/smart_search.jsp', parameters, callback);

        else if (sendTo == NetworkDashboard.Section.SMART_GROUPS)

            this.clickItem(event, '#network_shortcut_smart_groups', './modules/smart_groups/by_network.jsp', parameters, callback);

        else if (sendTo == NetworkDashboard.Section.SMART_GROUP)

            this.clickItem(event, '#network_shortcut_smart_groups', './modules/smart_groups/dashboard.jsp', parameters, callback);

        else if (sendTo == NetworkDashboard.Section.SHARED_ITEMS)

            this.clickItem(event, '#network_shortcut_share', './modules/share/by_network.jsp', parameters, callback);

        else if (sendTo == NetworkDashboard.Section.PROFILE)

            this.clickItem(event, '#network_shortcut_profile', './modules/profiles/dashboard.jsp', parameters, callback);

        else if (sendTo == NetworkDashboard.Section.ALL)

             this.clickItem(event, '#network_shortcut_all', './modules/all/dashboard.jsp', parameters, callback);

        else if (sendTo == NetworkDashboard.Section.FINDER)

             this.clickItem(event, null, './modules/finder/find.jsp', parameters, callback);

    };

}

NetworkDashboard.unhighlightAll = function () {

    $("#all").removeClass("selected");
    $("#smart_search_button").removeClass("selected");

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

NetworkDashboard.toggleAward = function () {
    var $award = $("#award");
    $award.fadeToggle();
};

NetworkDashboard.showCustomLogo = function (logoSource) {

    $("#default_logo").hide();
    $("#custom_logo img").attr("src", logoSource);
    $("#custom_logo").fadeIn();
};

NetworkDashboard.showDefaultLogo = function (networkName) {

    $("#custom_logo").hide();
    $("#default_logo").text(networkName).fadeIn();
};

