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

    $.post("/p/goin/actions/signin.jsp", parameters, function(response) {

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
    var $button = $("#signin_button");
    $button.removeClass("submit_button").addClass("dark_button");
    $error.empty().hide();
    $loading.show();     
};

Signin.displayError = function (errorMessage) {
    var $loading = $("#signin_loading");
    var $error = $("#signin_error");
    var $button = $("#signin_button");
    $button.removeClass("dark_button").addClass("submit_button");
    $error.text(errorMessage).fadeIn();
    $loading.hide();
};

Signin.hideLoadingAndError = function () {
    var $loading = $("#signin_loading");
    var $error = $("#signin_error");
    var $button = $("#signin_button");
    $button.removeClass("dark_button").addClass("submit_button");
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
    var $signup = $("#signup");

    var parameters = {};
    parameters.nid = networkId;
    parameters.ncs = networkChecksum;
    parameters.e = email;
    parameters.p = password;
    parameters.n = name;
    parameters.ct = cardToken;

    $.post("/p/goin/actions/signup.jsp", parameters, function(response) {

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

            $signup.hide();
            $confirm.fadeIn();

            return false;

        } else if($response.find("payment").length > 0) {

            $signup.hide();
            $payment.fadeIn();

            return false;

        } else {

            alert("Unknown response: " + $response.trim());

        }

    });

};

Signup.displayLoading = function () {
    var $loading = $("#signup_loading");
    var $error = $("#signup_error");
    var $button = $("#signup_button");
    $button.removeClass("submit_button").addClass("dark_button");
    $error.empty().hide();
    $loading.show();
};

Signup.displayError = function (errorMessage) {
    var $loading = $("#signup_loading");
    var $error = $("#signup_error");
    var $button = $("#signup_button");
    $button.removeClass("dark_button").addClass("submit_button");
    $error.text(errorMessage).fadeIn();

    // Clearing card token to restart payment
    $("#card_token").val("");

    $loading.hide();
};

Signup.hideLoadingAndError = function () {
    var $loading = $("#signup_loading");
    var $error = $("#signup_error");
    var $button = $("#signup_button");
    $button.removeClass("dark_button").addClass("submit_button");
    $error.hide();
    $loading.hide();
};




function Payment() {}

Payment.createToken = function (event) {

    Event.preventDefault(event);

    Payment.displayLoading();

    // Retrieving values
    var number = $("#payment_cc").val();
    var exp = $("#payment_exp").val();
    var cvc = $("#payment_cvc").val();

    // Separating expiration month and year
    var expTokens = exp.split("/");
    var month = expTokens[0];
    var year = 20 + expTokens[1];

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

    // Retrieving objects
    var $payment = $("#payment");
    var $signup = $("#signup");

    if (response.error) {

        Payment.displayError(response.error.message);

    } else {

        // Token contains id, last4, and card type
        var token = response['id'];

        // Insert the token into the form so it gets submitted to the server
        var $cardToken = $("#card_token");
        $cardToken.val(token);

        Payment.hideLoadingAndError();

        $payment.hide();
        $signup.fadeIn();

        // Submitting signup
        Signup.signup(null);

    }

};

Payment.checkCC = function () {
    var value = $("#payment_cc").val();
    var $bad = $("#payment_check_cc_bad");
    var $good = $("#payment_check_cc_good");
    var $image = $("#payment_check_cc_type");

    if (Stripe.card.validateCardNumber(value)) {
        $bad.hide(); $good.fadeIn();
    } else {
        $good.hide(); $bad.fadeIn();
    }

    var ccImages = {};
    ccImages["Visa"] = "cc_visa.png";
    ccImages["MasterCard"] = "cc_mastercard.png";
    ccImages["American Express"] = "cc_american.png";
    ccImages["Discover"] = "cc_discover.png";
    ccImages["Diners Club"] = "cc_diners.png";
    ccImages["JCB"] = "cc_jcb.png";
    ccImages["Unknown"] = "cc_gray.png";

    var ccUsed = Stripe.card.cardType(value);
    var ccUsedSrc = ccImages[ccUsed];
    $image.attr("src", "/d/goin/img/" + ccUsedSrc);
};

Payment.checkExp = function () {
    var value = $("#payment_exp").val();
    var tokens = value.split("/");
    var month = tokens[0];
    var year = 20 + tokens[1];
    var $bad = $("#payment_check_exp_bad");
    var $good = $("#payment_check_exp_good");

    if (Stripe.card.validateExpiry(month, year)) {
        $bad.hide(); $good.fadeIn();
    } else {
        $good.hide(); $bad.fadeIn();
    }
};

Payment.checkCvc = function () {
    var value = $("#payment_cvc").val();
    var $bad = $("#payment_check_cvc_bad");
    var $good = $("#payment_check_cvc_good");

    if (Stripe.card.validateCVC(value)) {
        $bad.hide(); $good.fadeIn();
    } else {
        $good.hide(); $bad.fadeIn();
    }
};



Payment.displayLoading = function () {
    var $loading = $("#payment_loading");
    var $error = $("#payment_error");
    var $button = $("#payment_button");
    $button.removeClass("submit_button").addClass("dark_button");
    $error.empty().hide();
    $loading.show();
};

Payment.displayError = function (errorMessage) {
    var $loading = $("#payment_loading");
    var $error = $("#payment_error");
    var $button = $("#payment_button");
    $button.removeClass("dark_button").addClass("submit_button");
    $error.text(errorMessage).fadeIn();
    $loading.hide();
};

Payment.hideLoadingAndError = function () {
    var $loading = $("#payment_loading");
    var $error = $("#payment_error");
    var $button = $("#payment_button");
    $button.removeClass("dark_button").addClass("submit_button");
    $error.hide();
    $loading.hide();
};
