function UserMessageGroups () {

    this.networkId = null;
    this.from = 0;

    this.display = function () {

        var parameters = {};
        parameters.nid = this.networkId;
        parameters.from = this.from;

        var tmp_this = this;
        Pagination.bindScrollPagination( function() { tmp_this.scroll() } );

        this.scroll();

    };

    this.scroll = function() {

        var parameters = {};
        parameters.nid = this.networkId;
        parameters.from = this.from;

        // Loading the next series of shared items
        var $div = $("<div/>");
        var tmp_this = this;
        $div.load("./modules/user_messages/groups_display.jsp", parameters, function (response) {

            // Have we reached the end? If so, lock down future requests
            if ($.trim(response).length == 0) {
                Pagination.unbindScrollPagination();
                return false;
            }

            // Increase 'from' count
            var responseCount = $div.find(".user_message_group").length;
            tmp_this.from = tmp_this.from + responseCount;

            // Adding user message groups
            $div.appendTo("#user_message_groups_canvas");

        });

    };

}
