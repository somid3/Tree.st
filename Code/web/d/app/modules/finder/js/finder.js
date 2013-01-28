function Finder () {};

/**
 * Setups the search input field for a new search
 */
Finder.setupFinder = function () {

    var $finderField = $("#finder");

    // Clear the field input
    $finderField.val("");

};

Finder.submitFind = function (event) {

    Event.preventDefault(event);

    var $finderField = $("#finder");
    var $hashField = $("#finder_hash");

    // Retrieve search term
    var findText = $finderField.val();
    var hashText = $hashField.val();

    // Present results on search canvas
    HashRouting.setHash(event, hashText + encodeURIComponent(findText));

};
