function Points () {};

Points.increment = function (incrementBy) {

    if (incrementBy == 0)
        return;

    var $availablePointsDiv = $("#available_points");

    // Is point system turned on?
    if ($availablePointsDiv.length == 0)
        return false;

    var $availablePosition = $availablePointsDiv.position();

    var newAvailablePoints = parseInt($availablePointsDiv.text()) + parseInt(incrementBy);

    $availablePointsDiv
        .clone()
        .removeAttr('ref')
        .css("z-index", 1)
        .css("position", "absolute")
        .css("top", $availablePosition.top)
        .css("left", $availablePosition.left)
        .text(incrementBy)
        .prependTo("#points")
        .animate({top: $availablePosition.top - 30, opacity: 0}, 800, function() {$(this).remove();});

    $availablePointsDiv.text(newAvailablePoints);
}

Points.decrease = function (decreaseBy) {

    if (decreaseBy == 0)
        return;

    Points.increment(decreaseBy * -1);

}