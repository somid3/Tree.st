function SmartGroupTools () {

    this.networkId = null;
    this.view = null;
    this.from = 0;

    this.changeView = function(event, hVisibilityId, view) {

        Event.preventDefault(event);

        var $viewDiv = $("#" + hVisibilityId);

        var $smartGroupTools = $("#smart_group_tools");

        // Un highlight all types
        $smartGroupTools.find(".view").each(function () {
            $(this).removeClass("selected");
        });

        // Highlight selected item
        $viewDiv.addClass("selected", 500);

        // Update type variable
        this.view = view;

        // Resetting the from count to begin from zero
        this.from = 0;

        // Clear the canvas
        $("#smart_groups_canvas").empty();

        // Bind scrolling and display smart groups
        this.displaySmartGroups();
    };


    /**
     * Displays the smart groups based on the selected tools
     */
    this.displaySmartGroups = function () {

        var tmp_this = this;
        Pagination.bindScrollPagination( function() { tmp_this.scrollSmartGroups() } );

        // Display the first page
        this.scrollSmartGroups();

    };

    this.scrollSmartGroups = function() {

        var parameters = {};
        parameters.nid = this.networkId;
        parameters.from = this.from;
        parameters.view = this.view;

        // Loading the next series of shared items
        var $div = $("<div/>");
        var tmp_this = this;
        $div.load("./modules/smart_groups/smart_groups_display.jsp", parameters, function (response) {

            // Increase 'from' count
            var responseCount = $div.find(".smart_group_box").length;
            tmp_this.from = tmp_this.from + responseCount;

            // Adding shared items to share canvas
            $div.appendTo("#smart_groups_canvas");

        });

    };


    /**
     * Marks as a favorite or flag a particular smart group given a user
     *
     * @param event
     * @param applyState
     * @param hStateId
     * @param networkId
     * @param smartGroupRef
     */
    this.applyState = function (event, hStateId, networkId, smartGroupRef, applyState) {

        Event.preventDefault(event);

        // Send the post event to toggle the favorite
        $.post('./modules/smart_groups/apply_state.jsp', {nid: networkId, sgr: smartGroupRef, ts: applyState}, function(data) {

            var $stateDiv  = $("#" + hStateId);
            $stateDiv.replaceWith(data);
        });

    };
}

