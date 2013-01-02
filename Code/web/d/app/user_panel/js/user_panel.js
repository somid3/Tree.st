UserPanel.Section = {
    PHOTOS: 2,
    NETWORKS: 3,
    GENERAL: 4};

function UserPanel () {}

/**
 * Removes selected class from all items on the network dashboard
 * and adds the selected class to the selector provided
 */
UserPanel.clickItem = function (event, selector, url, parameters, callback) {

    Event.preventDefault(event);

    CurrentlyMenu.unhighlightAll();

    UserPanel.unhighlightAll();

    // Add full opacity to the left menu
    LeftMenu.halfOpacity();

    // Scroll to the top of the page
    Animations.scrollToTop();

    // Display the canvas loading display
    UserPanel.displayLoading();

    // Creating a callback that hides the canvas loading
    var newCallback = function () {
        if (callback) callback();
        UserPanel.hideLoading();
    };

    // Add highlight to the selected box
    if (selector) {
        $(selector).addClass("selected");
    }

    var data = {};
    data = $.extend(data, parameters);

    Transitions.load('#user_panel_canvas', url, data, newCallback);

};

UserPanel.go = function (event, sendTo, parameters, callback) {

    Event.preventDefault(event);

    if (sendTo == null  || sendTo == UserPanel.Section.PHOTOS)

        this.clickItem(event, '#user_panel_shortcut_photos', './user_panel/user_photos/index.jsp', parameters, callback);

    else if (sendTo == UserPanel.Section.NETWORKS)

        this.clickItem(event, "#user_panel_shortcut_networks", './user_panel/user_networks/index.jsp', parameters, callback);

    else if (sendTo == UserPanel.Section.GENERAL)

        this.clickItem(event, "#user_panel_shortcut_general", './user_panel/user_general/index.jsp', parameters, callback);

};

/**
 * Unhighlights the user panel on the header
 */
UserPanel.unhighlightUser = function () {

    $("#header .user").each(
        function() {
            $(this).removeClass("selected");
        }
    );

};

UserPanel.unhighlightAll = function () {

    // Remove all highlights from network dash board
    $("#user_panel_dashboard .shortcut").each(
        function() {
            $(this).removeClass("selected");
        }
    );

};

/**
 * Displays the user panel dashboard
 */
UserPanel.view = function (event, callback) {

    Event.preventDefault(event);

    $("#header .user").addClass("selected");

    NetworkDashboard.displayLoading();

    LeftMenu.halfOpacity();

    Transitions.load("#canvas", "./user_panel/dashboard.jsp", callback);

    NetworkDashboard.hideLoading();
};


UserPanel.displayLoading = function () {
    var $canvasLoading = $("#user_panel_canvas_loading");
    var $canvas = $("#user_panel_canvas");

    $canvas.empty();
    $canvasLoading.show();
};

UserPanel.hideLoading = function () {
    var $canvasLoading = $("#user_panel_canvas_loading");
    $canvasLoading.hide();
};



