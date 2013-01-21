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

    var $path = $("#path");
    var $name = $("#name");
    var $desc = $("#desc");
    var $q1 = $("#q1");
    var $q2 = $("#q2");
    var $q3 = $("#q3");
    var $q4 = $("#q4");
    var $q5 = $("#q5");

    var pathValue = $path.find("input").val();
    var nameValue = $name.find("input").val();
    var descValue = $desc.find("input").val();
    var q1Value = $q1.find("textarea").val();
    var q2Value = $q2.find("textarea").val();
    var q3Value = $q3.find("textarea").val();
    var q4Value = $q4.find("textarea").val();
    var q5Value = $q5.find("textarea").val();

    // Creating parameters
    var parameters = {};
    parameters.path = pathValue;
    parameters.name = nameValue;
    parameters.desc = descValue;
    parameters.q1 = q1Value;
    parameters.q2 = q2Value;
    parameters.q3 = q3Value;
    parameters.q4 = q4Value;
    parameters.q5 = q5Value;

    $.post("./actions/create.jsp", parameters, function(response) {

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

        } else {

            // Move the login form out
            Animations.outTop("#login", function () {

                /* Cookies should be setup by now, send user to application */
                URL.redirect("/d/app");

            });

            return false;
        }
    });
};
