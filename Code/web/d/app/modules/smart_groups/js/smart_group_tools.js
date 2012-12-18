function SmartGroupTools () {

    this.networkId = null;
    this.view = null;

    this.changeView = function(event, hVisibilityId, view) {

        Event.preventDefault(event);

        var $viewDiv = $("#" + hVisibilityId);

        var $smartGroupTools = $("#smart_group_tools");

        // Un highlight all types
        $smartGroupTools.find(".view").each(function () {
            $(this).removeClass("selected");
        })

        // Highlight selected item
        $viewDiv.addClass("selected", 500);

        // Update type variable
        this.view = view;
    };


    /**
     * Displays the smart groups based on the selected tools
     */
    this.displaySmartGroups = function () {

        var tmp_this = this;
        Pagination.bindScrollPagination( function() { tmp_this.scrollSmartGroups() } );

        Transitions.load('#smart_groups_canvas', "./modules/smart_groups/smart_groups_display.jsp", {nid: this.networkId, sgv: this.view, from: 0});

    };


    this.toggleState = function (event, toggleState, hFavoriteId, networkId, smartGroupRef) {

        Event.preventDefault(event);

        // Send the post event to toggle the favorite
        $.post('./modules/smart_groups/toggle_state.jsp', {nid: networkId, sgr: smartGroupRef, ts: toggleState}, function(data) {

            var $favoriteDiv  = $("#" + hFavoriteId);

            // Remove old star
            $favoriteDiv.replaceWith(data);
        });

    };

    this.scrollSmartGroups = function() {

        // Counting the total number of shared items displayed
        var from = $(".smart_group_box").length;

        var parameters = {};
        parameters.nid = this.networkId;
        parameters.from = from;

        // Loading the next series of shared items
        var $div = $("<div/>");
        $div.load("./modules/smart_groups/smart_groups_display.jsp", parameters, function (response) {

            // Adding shared items to share canvas
            $div.appendTo("#smart_groups_canvas");

            // Have we reached the end? If so, lock down future requests
            if ($.trim(response).length == 0) {
                Pagination.unbindScrollPagination();
            }

        });

    };
}

