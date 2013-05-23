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

    $.post("/d/goin/actions/signin.jsp", parameters, function(response) {

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
                var goHash = $response.find("app").attr("go");
                var sendTo = "/d/app/" + goHash;
                URL.redirect(sendTo);

            });

            return false;
        }
    });
};

function Signup() {}

Signup.signup = function (event, networkdId, networkChecksum) {

    Event.preventDefault(event);

    // Retrieving values
    var email = $("#signup-email").val();
    var password = $("#signup-password").val();
    var name = $("#signup-name").val();

    // Retrieving objects
    var $confirm = $("#confirm");
    var $error = $("#signup-error");
    var $loading = $("#signup-loading");

    var parameters = {};
    parameters.nid = networkdId;
    parameters.ncs = networkChecksum;
    parameters.e = email;
    parameters.p = password;
    parameters.n = name;

    $.post("/d/goin/actions/signup.jsp", parameters, function(response) {


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

            // Shake the form
            Animations.shake("#signup");

            return false;

        } else if($response.find("app").length > 0) {

            // Move the start form out
            Animations.outTop("#console", function () {

                // Send user to app
                var goHash = $response.find("app").attr("go");
                var sendTo = "/d/app/" + goHash;
                URL.redirect(sendTo);

            });

            return false;

        } else if($response.find("confirm").length > 0) {

            // Move the start form out
            Animations.outTop("#console", function () {

                $confirm.fadeIn();

            });

            return false;

        } else {

            alert("Unknown response: " + $response.trim());

        }

    });

};