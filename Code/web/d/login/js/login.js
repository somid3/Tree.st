function Login () {}

Login.begin = function (event) {

    Event.preventDefault(event);

    // Hide the login begin div
    $("#login-begin").hide();

    // Displaying the login div
    $("#login").show();

};


Login.login = function (event) {

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

//        console.log(response);
//        console.log(response.trim());
//        console.log($.parseXML(response.trim()));


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
