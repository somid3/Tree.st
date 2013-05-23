function Signin () {}

Signin.signin = function (event, networkId) {

    Event.preventDefault(event);

    // Retrieving values
    var email = $("#signin-email").val();
    var password = $("#signin-password").val();
    var keep = $("#signin-keep").is(':checked');

    // Retrieving objects
    var $confirm = $("#confirm");
    var $error = $("#signin-error");
    var $loading = $("#signin-loading");

    // Encrypt password
    var parameters = {};
    parameters.nid = networkId;
    parameters.e = email;
    parameters.p = password;
    parameters.k = keep;

    $.post("./actions/signin.jsp", parameters, function(response) {

        // Hiding error
        $error.fadeOut();
        $loading.fadeIn();

        // Parsing the results
        var responseDoc = $.parseXML($.trim(response));
        var $response = $(responseDoc);

        // Did an error occur
        if($response.find("error").length > 0) {

            // Present the error
            var errorResponse = $response.find("error").text();
            $error.text(errorResponse).fadeIn();
            $loading.fadeOut();

            // Shake the login
            Animations.shake("#signin");

            return false;

        } else if($response.find("confirm").length > 0) {

            // Move the login form out
            Animations.outTop("#console", function () {

                $confirm.fadeIn();

            });

        } else if($response.find("app").length > 0) {

            // Move the login form out
            Animations.outTop("#console", function () {

                // Send user to app
                var sendTo = "/d/app";
                URL.redirect(sendTo);

            });

            return false;
        }
    });
};
