function UserLink () {};



UserLink.bindDetails = function (event, hFaceId, networkId, toUserId, hCardHolderId) {

    var $faceDiv = $("#" + hFaceId);

    var timer;

    $faceDiv.bind("click", function(event) {

        Event.preventDefault(event);

        if(timer) { clearTimeout(timer); timer = null }

        UserLink.openCard (event, networkId, toUserId, hCardHolderId);
    });

    $faceDiv.bind("mouseenter", function(event) {

        Event.preventDefault(event);

        if(timer) { clearTimeout(timer); timer = null }

        timer = setTimeout(function() {

            UserLink.openCard (event, networkId, toUserId, hCardHolderId);

        }, 1000);

    });

    $faceDiv.bind("mouseleave", function(event) {

        Event.preventDefault(event);

        if(timer) { clearTimeout(timer); timer = null }

        timer = setTimeout(function() {

            // Hiding all holder
            UserLink.hideCardHolder (event, hCardHolderId);

        }, 250);

    });

    // Hiding the details when mouse moves out
    var $cardHolder = $("#" + hCardHolderId);

    $cardHolder.bind("mouseleave", function(event) {

        Event.preventDefault(event);

        if(timer) { clearTimeout(timer); timer = null }

        timer = setTimeout(function() {

            // Hiding all holder
            UserLink.hideCardHolder (event, hCardHolderId);

        }, 250);

    });

    $cardHolder.bind("mouseenter", function(event) {

        Event.preventDefault(event);

        if(timer) { clearTimeout(timer); timer = null }

    });
};

UserLink.openCard = function (event, networkId, toUserId, hCardHolderId) {

    Event.preventDefault(event);

    // Hiding all card holders
    UserLink.hideAllCardHolders();

    // Loading user window
    UserLink.loadCardToTarget(networkId, toUserId, hCardHolderId);

};

UserLink.hideAllCardHolders = function () {

    // Close all other open user windows
    $(".user_face .user_card_holder").each(function(i) {
        $(this).hide();
    });

}

UserLink.hideCardHolder = function (event, hCardHolderId) {

    Event.preventDefault(event);
        
    $("#" + hCardHolderId).hide();

};

UserLink.loadCardToTarget = function (networkId, toUserId, hTargetId) {

    Transitions.loadFadeIn("#" + hTargetId, "./modules/user_links/user_card.jsp",

        // Parameters of details
        {nid: networkId, tuid: toUserId},

        // Display target
        function () { $("#" + hTargetId).fadeIn();}
    );

};

UserLink.connect = function (event, networkId, toUserId, hCardId, hErrorId, hConnectButtonId, callback) {

    Event.preventDefault(event);
                            
    // Attempt to connect users
    var parameters = {};
    parameters.nid = networkId;
    parameters.tuid = toUserId;

    // Display loading
    var $loadingDiv = $("#" + hCardId).find(".loading");
    $loadingDiv.show();

    $.post('./modules/user_links/actions/create_link.jsp', parameters, function(response) {

        // Parsing the results
        var responseDoc = $.parseXML($.trim(response));
        var $response = $(responseDoc);

        // Did an error occur
        var $responseError = $response.find("error");
        if($responseError.length > 0) {

            // Display error
            var $error = $("#" + hErrorId);
            $error.fadeIn(1000).html($responseError.text()).fadeOut(1000);

            // Hiding loading
            $loadingDiv.fadeOut();

        } else {

            // Retrieve used points
            var usedPoints = $response.find("used_points").text();

            // Update points
            Points.increment(usedPoints);

            if (!callback) {

                // Display contact details in the card itself
                UserLink.loadCardToTarget(networkId, toUserId, hCardId);

            } else { callback(); }

        }

    });

};


