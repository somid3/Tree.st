/**
 * Used to guide a user between the ajax pages,
 * and load a particular sub tab on this dashboard
 */
ProfileDashboard.Section = {
    SHARED: 1,
    QUESTIONS: 2,
    SMART_GROUPS: 3,
    VIEWED_USERS: 4,
    USER_LINK_NEEDED: 5
};

function ProfileDashboard () {

    this.networkId =  null;
    this.viewUserId =  null;

    /**
     * Removes selected class from all items on the profile dashboard
     * and adds the selected class to the selector provided
     */
    this.clickItem = function (event, selector, url, data, callback) {

        Event.preventDefault(event);
        
        // Remove all highlights from network dash board
        $("#profile_dashboard .shortcut").each(
            function() {
                $(this).removeClass("selected");
                $(this).find("div").removeClass("selected");
            }
        );

        // Add highlight to the selected box
        if (selector) {
            $(selector).addClass("selected", 250);
            $(selector).find("div").addClass("selected", 250);
        }

        // Display the canvas loading display
        ProfileDashboard.displayLoading();

        // Creating a callback that hides the canvas loading
        var newCallback = function () {

            if (callback) callback();

            ProfileDashboard.hideLoading();

        };

        var parameters = {};
        parameters.nid = this.networkId;
        parameters.vuid = this.viewUserId;
        parameters = $.extend(parameters, data);

        Transitions.load('#profile_canvas', url, parameters, newCallback);
    };

    this.go = function (event, go, parameters) {

        if (go == null  || go == ProfileDashboard.Section.QUESTIONS)
            this.clickItem(event, '#profile_shortcut_answers', './modules/profiles/answers.jsp', parameters);

        else if (go == ProfileDashboard.Section.VIEWED_USERS)
            this.clickItem(event, '#profile_shortcut_user_links', './modules/profiles/viewed_users.jsp', parameters);

        else if (go == ProfileDashboard.Section.SHARED)
            this.clickItem(event, '#profile_shortcut_shared', './modules/share/by_user.jsp', parameters);

        else if (go == ProfileDashboard.Section.SMART_GROUPS)
            this.clickItem(event, '#profile_shortcut_smart_groups', './modules/smart_groups/by_user.jsp', parameters);

        else if (go == ProfileDashboard.Section.USER_LINK_NEEDED)
            this.clickItem(event, '#profile_shortcut_user_links', './modules/profiles/access.jsp', parameters);
    }

    this.displayViewedUsers = function () {

        var parameters = {};
        parameters.nid = this.networkId;
        parameters.vuid = this.viewUserId;
        parameters.from = 0;

        console.log(parameters);

        var tmp_this = this;
        Pagination.bindScrollPagination( function() { tmp_this.scrollViewedUsers() } );

        Transitions.load('#viewed_canvas', "./modules/profiles/viewed_users_display.jsp", parameters);

    };

    this.scrollViewedUsers = function() {

        var tmp_this = this;

        // Counting the total number of shared items displayed
        var from = $(".user_link").length;

        var parameters = {};
        parameters.nid = this.networkId;
        parameters.vuid = this.viewUserId;
        parameters.from = from;

        // Loading the next series of shared items
        var $div = $("<div/>");
        $div.load("./modules/profiles/viewed_users_display.jsp", parameters, function (response) {

            // Adding shared items to share canvas
            $div.appendTo("#viewed_canvas");

            // Have we reached the end? If so, lock down future requests
            if ($.trim(response).length == 0) {
                Pagination.unbindScrollPagination();
            }

        });

    };

}










ProfileDashboard.displayLoading = function () {
    var $loading = $("#profile_loading");
    var $canvas = $("#profile_canvas");

    $canvas.empty();
    $loading.show();
};

ProfileDashboard.hideLoading = function () {
    var $loading = $("#profile_loading");
    $loading.hide();
};