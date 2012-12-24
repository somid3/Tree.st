function SmartGroups () {

    this.networkId = null;
    this.smartGroupRef = null;
    this.networkDashboard = null;

    this.createSmartGroup = function (event, hFormId, hSaveButtonId) {

        Event.preventDefault(event);

        var $form = $("#" + hFormId);
        var $errorDiv = $form.find(".error");
        var $loadingDiv = $form.find(".loading");

        // Hiding error
        $errorDiv.hide();

        // Show loading
        $loadingDiv.show();

        // Adding system parameters to data
        var parameters = Forms.serialize($form);
        parameters.nid = this.networkId;
        parameters.sgr = this.smartGroupRef;

        // Submit request
        var tmp_this = this;
        $.post('./modules/smart_groups/actions/create_smart_group.jsp', parameters, function(response) {

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

                // Move create smart group form to the short cut
                Animations.flyToTarget(event, "#create_smart_group", "#network_shortcut_smart_groups", false, true, function() {

                    // Changing page to smart group display
                    tmp_this.networkDashboard.go(event, NetworkDashboard.Section.SMART_GROUPS)

                });

            }

        });

    };

}