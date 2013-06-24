function UserMessageGroups () {

    this.networkId = null;
    this.from = 0;

    this.display = function () {

        var parameters = {};
        parameters.nid = this.networkId;
        parameters.from = this.from;

        var tmp_this = this;
        Pagination.bindScrollPagination( function() { tmp_this.scroll() } );

        this.scroll();

    };

    this.scroll = function() {

        var parameters = {};
        parameters.nid = this.networkId;
        parameters.from = this.from;

        // Loading the next series of shared items
        var $div = $("<div/>");
        var tmp_this = this;
        $div.load("./modules/user_messages/groups_display.jsp", parameters, function (response) {

            // Have we reached the end? If so, lock down future requests
            if ($.trim(response).length == 0) {
                Pagination.unbindScrollPagination();
                return false;
            }

            // Increase 'from' count
            var responseCount = $div.find(".user_message_group").length;
            tmp_this.from = tmp_this.from + responseCount;

            // Adding user message groups
            $div.appendTo("#user_message_groups_canvas");

        });

    };

}


function UserMessage () {};

UserMessage.sendFromProfile = function (event, networkId, toUserId, callback) {

    Event.preventDefault(event);

    // Gathering objects
    var $quote = $("#send_user_message_quote");
    var $sendMessage = $("#send_user_message_container");
    var $messageSent = $("#user_message_sent");

    // Defining parameters
    var parameters = {};
    parameters.nid = networkId;
    parameters.tuid = toUserId;
    parameters.qu = $quote.val();

    UserMessage.displayLoading();

    $.post('./modules/user_messages/actions/send_message.jsp', parameters, function(response) {

        // Parsing the results
        var responseDoc = $.parseXML($.trim(response));
        var $response = $(responseDoc);

        // Did an error occur
        var $responseError = $response.find("error");
        if($responseError.length > 0) {

            UserMessage.displayError($responseError.text());

        } else {

            // Retrieve used points
            var usedPoints = $response.find("points").text();

            // Update points
            Points.increment(usedPoints);

            // Hiding send message and displaying message sent
            $sendMessage.hide()
            $messageSent.fadeIn();
        }

    });

};

UserMessage.sendFromMessages = function (event, networkId, toUserId, callback) {

    Event.preventDefault(event);

    // Gathering objects
    var $quote = $("#send_user_message_quote");
    var $sendMessage = $("#send_user_message_container");
    var $messageSent = $("#user_message_sent");

    // Defining parameters
    var parameters = {};
    parameters.nid = networkId;
    parameters.tuid = toUserId;
    parameters.qu = $quote.val();

    UserMessage.displayLoading();

    $.post('./modules/user_messages/actions/send_message.jsp', parameters, function(response) {

        // Parsing the results
        var responseDoc = $.parseXML($.trim(response));
        var $response = $(responseDoc);

        // Did an error occur
        var $responseError = $response.find("error");
        if($responseError.length > 0) {

            UserMessage.displayError($responseError.text());

        } else {

            // Retrieve used points
            var usedPoints = $response.find("points").text();

            // Update points
            Points.increment(usedPoints);

            // Hiding send message and displaying message sent
            $sendMessage.hide()
            $messageSent.fadeIn();
        }

    });

};

UserMessage.displayLoading = function () {
    var $loadingDiv = $("#send_user_message_loading");
    var $errorDiv = $("#send_user_message_error");

    $loadingDiv.show();
    $errorDiv.hide();
};

UserMessage.displayError = function (errorMessage) {
    var $loadingDiv = $("#send_user_message_loading");
    var $errorDiv = $("#send_user_message_error");

    $errorDiv.html(errorMessage).fadeIn();
    $loadingDiv.hide();
};