function Signin () {}

Signin.signin = function (event) {

    Event.preventDefault(event);

    Signin.displayLoading();

    // Retrieving values
    var email = $("#signin_email").val();
    var password = $("#signin_password").val();
    var keep = $("#signin_keep").is(':checked');
    var networkId = $("#network_id").val();

    // Retrieving objects
    var $confirm = $("#confirm");

    // Encrypt password
    var parameters = {};
    parameters.nid = networkId;
    parameters.e = email;
    parameters.p = password;
    parameters.k = keep;

    $.post("/d/goin/actions/signin.jsp", parameters, function(response) {

        // Parsing the results
        var responseDoc = $.parseXML($.trim(response));
        var $response = $(responseDoc);

        // Did an error occur
        if($response.find("error").length > 0) {

            // Present the error
            var errorResponse = $response.find("error").text();
            Signin.displayError(errorResponse);

            return false;

        } else if($response.find("confirm").length > 0) {

            // Move the login form out
            Animations.outTop("#sign", function () {
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

Signin.displayLoading = function () {
    var $loading = $("#signin_loading");
    var $error = $("#signin_error");
    $error.empty().hide();
    $loading.show();
};

Signin.displayError = function (errorMessage) {
    var $loading = $("#signin_loading");
    var $error = $("#signin_error");
    $error.text(errorMessage).fadeIn();
    $loading.hide();
};

Signin.hideLoadingAndError = function () {
    var $loading = $("#signin_loading");
    var $error = $("#signin_error");
    $error.hide();
    $loading.hide();
};




function Signup() {}

Signup.signup = function (event) {

    Event.preventDefault(event);

    Signup.displayLoading();

    // Retrieving values
    var email = $("#signup_email").val();
    var password = $("#signup_password").val();
    var name = $("#signup_name").val();
    var cardToken = $("#card_token").val();
    var networkId = $("#network_id").val();
    var networkChecksum = $("#network_checksum").val();

    // Retrieving objects
    var $payment = $("#payment");
    var $confirm = $("#confirm");

    var parameters = {};
    parameters.nid = networkId;
    parameters.ncs = networkChecksum;
    parameters.e = email;
    parameters.p = password;
    parameters.n = name;
    parameters.ct = cardToken;

    $.post("/d/goin/actions/signup.jsp", parameters, function(response) {

        // Parsing the results
        var responseDoc = $.parseXML($.trim(response));
        var $response = $(responseDoc);

        // Did an error occur
        if($response.find("error").length > 0) {

            // Present the error
            var errorResponse = $response.find("error").text();
            Signup.displayError(errorResponse);

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
            Animations.outTop("#sign", function () {
                $confirm.fadeIn();
            });

            return false;

        } else if($response.find("payment").length > 0) {

            // Hide the sign up form
            Animations.outTop("#sign", function() {
                $payment.fadeIn();
            });

            return false;

        } else {

            alert("Unknown response: " + $response.trim());

        }

    });

};

Signup.displayLoading = function () {
    var $loading = $("#signup_loading");
    var $error = $("#signup_error");
    $error.empty().hide();
    $loading.show();
};

Signup.displayError = function (errorMessage) {
    var $loading = $("#signup_loading");
    var $error = $("#signup_error");
    $error.text(errorMessage).fadeIn();
    $loading.hide();
};

Signup.hideLoadingAndError = function () {
    var $loading = $("#signup_loading");
    var $error = $("#signup_error");
    $error.hide();
    $loading.hide();
};




function Payment() {}

Payment.createToken = function (event) {

    Event.preventDefault(event);

    Payment.displayLoading();

    // Retrieving values
    var number = $("#payment_ccnumber").val();
    var expiration = $("#payment_exp").val();
    var cvc = $("#payment_cvc").val();

    // Retrieving objects
    var $error = $("#payment_error");
    var $loading = $("#payment_loading");

    // Separating expiration month and year
    var month = 01;
    var year = 2014;

    Stripe.card.createToken({
        number: number,
        cvc: cvc,
        exp_month: month,
        exp_year: year
    }, Payment.response);

    return false;
};

Payment.response = function (status, response) {

    Payment.displayLoading();

    if (response.error) {

        Payment.displayError(response.error.message);

    } else {

        // Token contains id, last4, and card type
        var token = response['id'];

        // Insert the token into the form so it gets submitted to the server
        var $paymentToken = $("#card_token");
        $paymentToken.val(token);

        // Submitting signup
        Signup.signup(null);
    }

}

Payment.displayLoading = function () {
    var $loading = $("#payment_loading");
    var $error = $("#payment_error");
    $error.empty().hide();
    $loading.show();
};

Payment.displayError = function (errorMessage) {
    var $loading = $("#payment_loading");
    var $error = $("#payment_error");
    $error.text(errorMessage).fadeIn();
    $loading.hide();
};

Payment.hideLoadingAndError = function () {
    var $loading = $("#payment_loading");
    var $error = $("#payment_error");
    $error.hide();
    $loading.hide();
};
