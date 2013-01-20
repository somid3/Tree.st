function Create () {}

Create.testPath = function (event) {

    Event.preventDefault(event);

    var $path = $("#path");
    var $error = $path.find(".error");
    var $good = $path.find(".good");
    var pathValue = $path.find("input").val();

    // Clearing error and good
    $error.hide();
    $good.hide();

    // Creating parameters for path test
    var parameters = {};
    parameters.path = pathValue;

    if (pathValue.length <= 3) {

        // Present the error
        $error.text("That's too short").fadeIn();

        return false;

    }

    $.post("./actions/path.jsp", parameters, function(response) {

        // Parsing the results
        var responseDoc = $.parseXML($.trim(response));
        var $response = $(responseDoc);

        // Did an error occur
        if($response.find("error").length > 0) {

            // Present the error
            $error.text("Aww, '" + pathValue + "' is not available").fadeIn();

            return false;

        } else {

            $good.text("Yay! '" + pathValue + "' is available").fadeIn();

        }
    });

};

Create.create = function (event) {

    Event.preventDefault(event);

    var email = $("#email").val();
    var password = $("#pass").val();
    var keep = $("#keep").is(':checked');

    // Encrypt password
    var parameters = {};
    parameters.e = email;
    parameters.p = password;
    parameters.k = keep;

    $.post("./actions/login.jsp", parameters, function(response) {

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

            // Shake the login
            Animations.shake("#login");

            return false;

        } else if($response.find("confirm").length > 0) {

            // Move the login form out
            Animations.outTop("#login", function () {

                // Display message that user needs to confirm account
                var $outer = $("#outer-container");
                $outer.css('display', 'none').load("./renders/confirm.jsp", function () {
                    $outer.fadeIn();
                });

            });

        } else if($response.find("app").length > 0) {

            // Move the login form out
            Animations.outTop("#login", function () {

                /* Cookies should be setup by now, send user to application */
                URL.redirect("/d/app");

            });

            return false;
        }
    });
};
