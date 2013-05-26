function SharedItem () {

    this.networkId = null;
    this.smartGroupRef = null;
    this.from = 0;

    this.viewUserId = null;
    this.hTellMeId = null;

    this.addSharedItem = function (event) {

        Event.preventDefault(event);

        var $tellMe = $("#" + this.hTellMeId);
        var $shareSomething = $("#share_something");
        var $loading = $shareSomething.find(".share_loading");
        var $error = $shareSomething.find(".share_error");

        var parameters = {};
        parameters.nid = this.networkId;
        parameters.sgr = this.smartGroupRef;
        parameters.t = $tellMe.val();
                            
        // Display loading
        $loading.show();

        var tmp_this = this;

        // Creating the shared item
        $.post("./modules/share/actions/add_shared_item.jsp", parameters, function(response) {

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

            // Extracting ref of newly created shared item
            var ref = $response.find("add").attr("ref");

            // Extracting the number of points to add to user interface
            var points = $response.find("add").attr("points_added");
            points = parseInt(points);

            // Adding those points to the UI
            Points.increment(points);

            // Placing new shared item to the share canvas
            var $div = $("<div/>");
            $div
            .css('display', 'none')
            .load("./modules/share/shared_item_display.jsp", {nid: tmp_this.networkId, sgr: tmp_this.smartGroupRef, ref: ref})
            .prependTo("#share_canvas")
            .fadeIn();

            // Clearing tell me text
            $tellMe.val("");

            // Hiding loading
            $loading.fadeOut();

            // Hiding the "no items" message in case it existed
            $(".canvas_mini_message").fadeOut();

        });
    }

    this.hideSharedItem = function (event, hSharedItemId, smartGroupRef, sharedItemRef) {

        Event.preventDefault(event);

        var parameters = {};
        parameters.nid = this.networkId;
        parameters.sgr = smartGroupRef;
        parameters.sir = sharedItemRef;

        // Hiding shared item
        $.post("./modules/share/actions/hide_shared_item.jsp", parameters, function(response) {

            // Parsing the results
            var responseDoc = $.parseXML($.trim(response));
            var $response = $(responseDoc);

            // Was there an error?
            if($response.find("error").text().length > 0) {

                return false;

            };

           // Extracting the number of points to add to user interface
            var points = $response.find("hide").attr("points_removed");
            points = parseInt(points);

            // Update points
            Points.increment(points);

            // Removing shared item
            $("#" + hSharedItemId).fadeOut();

        });
    };

    this.displaySharedItems = function () {

        var parameters = {};
        parameters.nid = this.networkId;
        parameters.sgr = this.smartGroupRef;
        parameters.from = this.from;

        // Passing user id being viewed in case looking at user's profile
        if (this.viewUserId != null)
            parameters.vuid = this.viewUserId;

        var tmp_this = this;
        Pagination.bindScrollPagination( function() { tmp_this.scrollSharedItems() } );

        this.scrollSharedItems();

    };

    this.scrollSharedItems = function() {

        var parameters = {};
        parameters.nid = this.networkId;
        parameters.sgr = this.smartGroupRef;
        parameters.from = this.from;

        // Passing user id being viewed in case looking at user's profile
        if (this.viewUserId != null)
            parameters.vuid = this.viewUserId;

        // Loading the next series of shared items
        var $div = $("<div/>");
        var tmp_this = this;
        $div.load("./modules/share/shared_items_display.jsp", parameters, function (response) {

            // Have we reached the end? If so, lock down future requests
            if ($.trim(response).length == 0) {
                Pagination.unbindScrollPagination();
                return false;
            }

            // Increase 'from' count
            var responseCount = $div.find(".shared_item_box").length;
            tmp_this.from = tmp_this.from + responseCount;

            // Adding shared items to share canvas
            $div.appendTo("#share_canvas");

        });

    };

}
