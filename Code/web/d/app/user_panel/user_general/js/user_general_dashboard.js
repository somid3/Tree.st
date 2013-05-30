function UserGeneralDashboard () {

    /**
     * Removes selected class from all items on the network dashboard
     * and adds the selected class to the selector provided
     */
    this.clickItem = function (event, hShortcutsId, hShortcutId, hTargetId, url, data) {

        Event.preventDefault(event);
                                    
        // Remove all highlights from network dash board
        $('#' + hShortcutsId + " .shortcut").each(
            function() {
                $(this).removeClass("selected");
            }
        );

        // Add highlight to the selected box
        if (hShortcutId) {
            $('#' + hShortcutId).addClass("selected");
        }

        var parameters = {};
        parameters = $.extend(parameters, data);

        Transitions.load('#' + hTargetId, url, parameters);
    };


    this.cancelSetting = function (event, hSettingsId) {

        Event.preventDefault(event);

        $('#' + hSettingsId).fadeOut();

    };

    this.submitSetting = function (
        event,
        hSettingsId,
        hFormId,
        url,
        callback) {

        Event.preventDefault(event);

        var $settingsDiv = $('#' + hSettingsId);
        var $loadingDiv = $settingsDiv.find('.loading');
        var $errorDiv = $settingsDiv.find('.error');

        // Hide error
        $errorDiv.hide();

        // Display loading
        $loadingDiv.show();

        // Retrieving form and all inputs
        var parameters = Forms.serialize('#' + hFormId);

        // Submit request
        var tmp_this = this;
        $.post(url, parameters, function(response) {

            // Parsing the results
            var responseDoc = $.parseXML($.trim(response));
            var $response = $(responseDoc);

            // Did an error occur
            if($response.find("error").length > 0) {

                // Present the error
                var errorResponse = $response.find("error").text();
                $errorDiv.text(errorResponse).fadeIn();
                $loadingDiv.hide();

                return false;

            } else {

               if (callback) callback(parameters);

            }

        });

    };

    this.submitEmailChange = function (
        event,
        hSettingsId,
        hFormId) {

        this.submitSetting (
            event,
            hSettingsId,
            hFormId,
            './user_panel/user_general/actions/email_change.jsp',
            function (parameters) {

                Transitions.loadFadeIn(
                    '#' + hSettingsId,
                    './user_panel/user_general/email_change_confirm.jsp',
                    {email: parameters.email1}
                );

            }
        );
    };

    this.submitNameChange = function (
        event,
        hSettingsId,
        hFormId) {

        this.submitSetting (
            event,
            hSettingsId,
            hFormId,
            './user_panel/user_general/actions/name_change.jsp',
            function () {
                URL.redirect("/d/app")
            }
        );
    };

    this.submitPasswordChange = function (
        event,
        hSettingsId,
        hFormId) {

        this.submitSetting (
            event,
            hSettingsId,
            hFormId,
            './user_panel/user_general/actions/password_change.jsp',
            function () {
                URL.redirect("/d/signout")
            }
        );
    };

}