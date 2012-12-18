function SharedComment () {};

SharedComment.displayNewSharedComment = function (hNewSharedCommentId) {

    $("#" + hNewSharedCommentId).hide().fadeIn();

};

SharedComment.activateNewSharedComment = function (event, hNewSharedCommentTextId, hNewSharedCommentId) {

    Event.preventDefault(event);

    // Display add shared comment form
    SharedComment.displayNewSharedComment(hNewSharedCommentId);

    // Focus on the add new comment textarea
    $("#" + hNewSharedCommentTextId).focus();

};

/**
 * Adds a shared comment to a shared item. Notice that the parameter list of this function
 * takes in a network id and a smart group ref instead of using this object's network id
 * and smart group ref (sgr). That is because shared items could be viewed from the network
 * view, or the smart group view. When adding a comment we must ensure we add the comment
 * correctly, regardless if the shared item is being viewed from the network or smart group.
 *
 * @param event
 * @param networkId
 * @param smartGroupRef
 * @param sharedItemRef
 * @param hNewSharedCommentTextId
 * @param hCommentsId
 */
SharedComment.addSharedComment = function (event, networkId, smartGroupRef, sharedItemRef, hNewSharedCommentTextId, hSharedCommentsErrorId, hSharedCommentsLoadingId, hCommentsId) {

    Event.preventDefault(event);

    var $textarea = $("#" + hNewSharedCommentTextId);
    var $error = $("#" + hSharedCommentsErrorId);
    var $loading = $("#" + hSharedCommentsLoadingId);

    var parameters = {};
    parameters.nid = networkId;
    parameters.sgr = smartGroupRef;
    parameters.sir = sharedItemRef;
    parameters.t = $textarea.val();

    // Validating input
    if ($textarea.val().length == 0) {
        $error.fadeIn(1000).html("No message entered").fadeOut(1000);
        return false;
    }

    // Display loading
    $loading.show();

    // Creating the shared item
    $.post("./modules/share/actions/add_shared_comment.jsp", parameters, function(response) {


        // Parsing the results
        var responseDoc = $.parseXML($.trim(response));
        var $response = $(responseDoc);

        // Was there an error?
        var $responseError = $response.find("error");
        if($responseError.length > 0) {

            // Display error
            $error.fadeIn(1000).html($responseError.text()).fadeOut(1000);

            // Hiding loading
            $loading.fadeOut();

            return false;

        };

        // Extracting id of newly created shared comment
        var ref = $response.find("add").attr("ref");

        // Extracting the number of points to add to user interface
        var points = $response.find("add").attr("points_added");
        points = parseInt(points);

        // Adding those points to the UI
        Points.increment(points);

        // Placing new shared item to the share canvas
        var $div = $("<div/>");
        $div.css('display', 'none').load("./modules/share/shared_comment_display.jsp", {nid: networkId, sgr: smartGroupRef, sir: sharedItemRef, ref: ref});
        $div.appendTo("#" + hCommentsId).fadeIn();

        // Clearing tell me text
        $textarea.val("");

        // Hiding loading
        $loading.fadeOut();
    });
};



SharedComment.hideSharedComment = function (event, networkId, smartGroupRef, sharedItemRef, ref, hSharedCommentId) {

    Event.preventDefault(event);

    var parameters = {};
    parameters.nid = networkId;
    parameters.sgr = smartGroupRef;
    parameters.sir = sharedItemRef;
    parameters.ref = ref;

    // Hiding shared item
    $.post("./modules/share/actions/hide_shared_comment.jsp", parameters, function(response) {

        // Parsing the results
        var responseDoc = $.parseXML($.trim(response));
        var $response = $(responseDoc);

        // Was there an error?
        if($response.find("error").text().length > 0) {

            return false;

        }

        // Extracting the number of points to add to user interface
        var points = $response.find("hide").attr("points_removed");
        points = parseInt(points);

        // Update points
        Points.increment(points);

        // Removing shared item
        $("#" + hSharedCommentId).fadeOut();

    });
};