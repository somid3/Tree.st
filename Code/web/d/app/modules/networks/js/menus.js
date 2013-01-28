function CurrentlyMenu () {}

CurrentlyMenu.unhighlightAll = function () {

    // Remove all highlights from shortcuts
    $("#currently .shortcut").each(
        function() {
            $(this).removeClass("selected");
        }
    );

};





function NetworksMenu () {}

NetworksMenu.unhighlightAll = function () {

    $("#networks .item").each(
        function() {
            $(this).removeClass("selected");
        }
    );

};


function Go () {}

/**
 * Checks a particular go parameter that identifies
 * the network that the user should be redirected
 * to
 */
Go.check = function (parameterName, defaultValue) {

    var value = GO[parameterName];

    if (value != null) {

        return value;

    } else {

        return defaultValue;
    }

};

Go.checkAndRemove = function (parameterName, defaultValue) {

    var value = Go.check(parameterName, defaultValue);

    GO[parameterName] = null;

    return value;
};





function LeftMenu () {}
LeftMenu.CURRENT_NETWORK = null;

LeftMenu.fullOpacity = function () {

    // Add full opacity to the left menu
    $("#left").css({opacity: 1});

};

LeftMenu.halfOpacity = function () {

    // Add full opacity to the left menu
    $("#left").css({opacity: 0.5});

};

/**
 * Removes selected class from all items on the left menu
 * and adds the selected class to the selector provided
 */
LeftMenu.highlightItem = function (selector) {

    NetworksMenu.unhighlightAll();

    CurrentlyMenu.unhighlightAll();

    UserPanel.unhighlightUser();

    LeftMenu.fullOpacity();

    // Add highlight to the selected box
    if (selector) {
        $(selector).addClass("selected");
    }

};

LeftMenu.goToNetwork = function (event, networkId, callback) {

    Event.preventDefault(event);

    ND.networkId = networkId;
    SS.networkId = networkId;

    var selector = "#" + "network" + networkId;

    // Scroll to the top of the page
    Animations.scrollToTop();

    var parameters = {nid : networkId};

    // Checking if we are already inthe same network
    if (LeftMenu.CURRENT_NETWORK == networkId) {

        // Yes, same network, do not load currently again, just do the callback
        if (callback) callback();

    } else {

        // Remembering the current network
        LeftMenu.CURRENT_NETWORK = networkId;

        // Change the current network
        Transitions.load("#currently", "./modules/networks/currently.jsp", parameters, callback);

        // Loading the network dashboard
        Transitions.load("#dashboard", "./modules/networks/dashboard.jsp", {nid : networkId});

        // Highlight the network
        LeftMenu.highlightItem(selector);

        // Moving objects to they ground to zero
        LeftMenu.offsetItems();
    }
};

LeftMenu.offsetItems = function () {

    var $currently = $("#currently");
    $currently.val("").css("top", "100px").empty();

};

