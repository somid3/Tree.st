function Finder () {};

/**
 * Setups the search input field for a new search
 */
Finder.setupFinder = function () {

    var $searchField = $("#finder");

    // Clear the field input
    $searchField.val("");

};

Finder.viewEverything = function (event) {

    Event.preventDefault(event);

    // Doing the click
    ND.clickItem(event, "#all", './modules/finder/find.jsp', null, null);

};

Finder.submitSearch = function (event) {

    console.log("cacafece");

    Event.preventDefault(event);

    var $finderField = $("#finder");

    // Retrieve search term
    var findText = $finderField.val();

    // Present results on search canvas
    ND.go(event, NetworkDashboard.Section.FINDER, {s: findText}, null);

};
