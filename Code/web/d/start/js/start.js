function Start() {}

Start.start = function (event, networkdId, networkChecksum) {

    Event.preventDefault(event);

    var email = $("#email").val();
    var password = $("#pass").val();
    var first = $("#first").val();
    var last = $("#last").val();

    // Encrypt password
    var parameters = {};
    parameters.nid = networkdId;
    parameters.ncs = networkChecksum;
    parameters.e = email;
    parameters.p = password;
    parameters.f = first;
    parameters.l = last;

    $.post("./actions/start.jsp", parameters, function(response) {

        var $error = $("#error");

        // Hiding error
        $error.fadeOut();

        // Parsing the results
        var responseDoc = $.parseXML($.trim(response));
        var $response = $(responseDoc);

        // Did an error occur
        if($response.find("error").length > 0) {

            // Present the error
            var errorResponse = $response.find("error").text();
            $error.text(errorResponse).fadeIn();

            // Shake the form
            Animations.shake("#start");

            return false;

        } else if($response.find("app").length > 0) {

            // Move the start form out
            Animations.outTop("#start", function () {

                // Send user to app
                var goHash = $response.find("app").attr("go");
                var sendTo = "/d/app/" + goHash;
                URL.redirect(sendTo);

            });

            return false;

        } else if($response.find("confirm").length > 0) {

            // Move the start form out
            Animations.outTop("#start", function () {

                // Display message that user needs to confirm account
                var $action = $("#action");
                $action.css('display', 'none').load("./renders/confirm.jsp", function () {
                    $action.fadeIn();
                });

            });

            return false;

        } else {

            alert("Unknown response: " + $response.trim());

        }

    });

};

Start.toggleManifesto = function (event) {

    Event.preventDefault(event);
    var $manifesto = $("#manifesto");
    $manifesto.fadeToggle();

};