function UserPanelDashboard () {

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


    this.deletePhoto = function (event, hPhotoId, ref, checksum) {

        Event.preventDefault(event);

        // Sending request to hide photo
        $.post('./user_panel/actions/delete_photo.jsp', {r: ref, rcs: checksum},
            function(data) {

                $("#" + hPhotoId).fadeOut();

            });
    };

    this.setProfile = function (event, ref, checksum) {

        Event.preventDefault(event);

        // Sending request to hide photo
        $.post('./user_panel/actions/set_profile.jsp', {r: ref, rcs: checksum},
            function(data) {

                URL.redirect("/d/app");

            });
    }
}