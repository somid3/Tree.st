function Forgot () {};

Forgot.begin = function (event) {

    Event.preventDefault(event);

    // Move the login form out
    Animations.outTop("#login", function () {

        // Display message that user needs to confirm account
        var $action = $("#action");
        $action.css('display', 'none').load("./renders/forgot_begin.jsp", function () {
            $action.fadeIn();
        });

    });
};

Forgot.send = function (event) {

    Event.preventDefault(event);

    var email = $("#email").val();

    var parameters = {};
    parameters.e = email;

    $.post("./actions/begin.jsp", parameters, function(response) {

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

            Animations.shake("#forgot");
            return false;

        } else {

            // Move out the forgot form
            $("#forgot").fadeOut( function () {

                // Display message that user needs to confirm account
                $("#check").fadeIn();
            });

            return false;

        }

    });

};

Forgot.set = function (event, userId, passwordChecksum) {

    Event.preventDefault(event);

    var password = $("#pass").val();
    var passwordAgain = $("#pass_again").val();

    var parameters = {};
    parameters.uid = userId;
    parameters.xcs = passwordChecksum;
    parameters.p = password;
    parameters.pa = passwordAgain;

    $.post("./actions/set.jsp", parameters, function(response) {

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

            Animations.shake("#forgot");

            return false;

        } else if($response.find("app").length > 0) {

            // Send user to app
            URL.redirect("/d/updated");

            return false;

        }

    });

};