AllMembersDashboard.Section = {
    BY_POINTS: 1,
    BY_JOINED: 2
};


function AllMembersDashboard () {

    this.networkId = null;
    this.sort = null;
    this.from = 0;

    /**
     * Removes selected class from all items on the network dashboard
     * and adds the selected class to the selector provided
     */
    this.clickItem = function (event, selector) {

        Event.preventDefault(event);
                                    
        // Remove all highlights from network dash board
        $("#all_members_dashboard .shortcut").each(
            function() {
                $(this).removeClass("selected");
            }
        );

        // Add highlight to the selected box
        if (selector) {
            $(selector).addClass("selected", 250);
        }

        // Resetting the from count to begin from zero
        this.from = 0;

        // Clear the canvas
        $("#all_members_canvas").empty();

        // Display members
        this.displayMembers();
    };

    this.go = function (event, sendTo, parameters, callback) {

        /* Loading the desired module */

        if (sendTo == null || sendTo == AllMembersDashboard.Section.BY_POINTS) {

            this.sort = 1;
            this.clickItem(event, '#all_members_shortcut_points');

        } else if (sendTo == AllMembersDashboard.Section.BY_JOINED) {

            this.sort = 2;
            this.clickItem(event, '#all_members_shortcut_joined');

        } else if (sendTo == AllMembersDashboard.Section.BY_SHARED_POINTS) {

            this.sort = 3;
            this.clickItem(event, '#all_members_shortcut_shared');

        }
    };

    this.displayMembers = function () {

        // Bind the scrolling
        var tmp_this = this;
        Pagination.bindScrollPagination( function() { tmp_this.scrollMembers() } );

        // Display the first page
        this.scrollMembers();

    };

    this.scrollMembers = function() {

        var parameters = {};
        parameters.nid = this.networkId;
        parameters.from = this.from;
        parameters.sort = this.sort;

        // Loading the next series of shared items
        var $div = $("<div/>");
        var tmp_this = this;
        $div.load("./modules/all/by_network.jsp", parameters, function (response) {

            // Increase 'from' count
            var responseCount = $div.find(".all_members_result").length;
            tmp_this.from = tmp_this.from + responseCount;

            // Adding shared items to share canvas
            $div.appendTo("#all_members_canvas");

        });

    };

}