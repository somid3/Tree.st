function UserNetworksDashboard () {

    /**
     * Removes selected class from all items on the network dashboard
     * and adds the selected class to the selector provided
     */
    this.clickItem = function (event, hSelectorId, hTargetId, url, data) {

        Event.preventDefault(event);
                                    
        // Remove all highlights from network dash board
        $("#user_networks .shortcut").each(
            function() {
                $(this).removeClass("selected");
            }
        );

        // Add highlight to the selected box
        if (hSelectorId) {
            $('#' + hSelectorId).addClass("selected", 250);
            console.log("class added");
        }

        var parameters = {};
        parameters = $.extend(parameters, data);

        Transitions.load('#' + hTargetId, url, parameters);
    };


}