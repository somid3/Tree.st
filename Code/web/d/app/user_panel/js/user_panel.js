function UserPanel () {

    /**
     * Removes selected class from all items on the network dashboard
     * and adds the selected class to the selector provided
     */
    this.clickItem = function (event, selector, url, data) {

        Event.preventDefault(event);
                                    
        // Remove all highlights from network dash board
        $("#user_panel_dashboard .shortcut").each(
            function() {
                $(this).removeClass("selected");
            }
        );

        // Add highlight to the selected box
        if (selector) {
            $(selector).addClass("selected", 250);
        }

        var parameters = {};
        parameters = $.extend(parameters, data);

        Transitions.load('#user_panel_canvas', url, parameters);
    };
}


/**
 * Unhighlights the user panel on the header
 */
UserPanel.unhighlight = function () {

    $("#header .user").each(
        function() {
            $(this).removeClass("selected");
        }
    );

};

/**
 * Displays the user panel dashboard
 */
UserPanel.view = function (event) {

    Event.preventDefault(event);

    $("#header .user").addClass("selected");

    Transitions.load("#canvas", "./user_panel/dashboard.jsp");

    LeftMenu.halfOpacity();
};



