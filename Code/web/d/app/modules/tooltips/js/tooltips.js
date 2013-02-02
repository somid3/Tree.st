function Tooltips () {

    this.timer = null;

    this.show = function (hTooltipId) {

        var $tooltipDiv = $("#" + hTooltipId);

        // Display the tooltip
        $tooltipDiv.fadeIn();

        // Begin timer to shake tooltip
        Animations.wave("#" + hTooltipId);

    };

    this.hide = function (hContainerId, hTooltipId, callback) {

        var $containerTip = $("#" + hContainerId);
        var $toolTip = $("#" + hTooltipId);

        // Stop any current animations
        $toolTip.stop(true, true);

        // Stop shaking timer
        clearInterval(this.timer);

        // Move the current tip out and remove it from the dom
        $toolTip.fadeOut( function () {
            $toolTip.remove();
            $containerTip.remove();
        });


    };

    this.next = function (event, hContainerId, hTooltipId, tooltipValue, callback) {

        Event.preventDefault(event);

        var parameters = {tv: tooltipValue};

        // Creating the shared item
        var tmp_this = this;
        $.post("./modules/tooltips/actions/next.jsp", parameters, function(response) {

            // Hide current tooltip
            tmp_this.hide(hContainerId, hTooltipId, callback);

            // Parsing the results
            var responseDoc = $.parseXML($.trim(response));
            var $response = $(responseDoc);

            // Extracting next tooltip value
            var hNextTooltipId = $response.find("next").attr("tt");

            // Show the next tooltip
            tmp_this.show(hNextTooltipId);

        });
    };

};

Tooltips.closeMinitip = function (
    event,
    hMinitipId,
    networkId,
    userToNetworkIntegerSettingId) {

    Event.preventDefault(event);

    var parameters = {};
    parameters.nid = networkId;
    parameters.utnisid = userToNetworkIntegerSettingId;

    $.post("./modules/tooltips/actions/close_mini.jsp", parameters, function(response) {

        // Hiding the actual tooltip
        $("#" + hMinitipId).fadeOut();
    });
};
