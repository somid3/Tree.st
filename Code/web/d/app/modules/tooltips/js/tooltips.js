function Tooltips () {};

Tooltips.closeMinitip = function (
    event,
    hMinitipId,
    settingId) {

    Event.preventDefault(event);

    var parameters = {};
    parameters.sid = settingId;

    $.post("./modules/tooltips/actions/close_mini.jsp", parameters, function(response) {

        // Hiding the actual tooltip
        $("#" + hMinitipId).fadeOut();
    });
};
