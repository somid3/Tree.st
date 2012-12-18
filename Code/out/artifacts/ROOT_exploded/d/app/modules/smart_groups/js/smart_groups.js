function SmartGroups () {

    this.networkId = null;
    this.smartGroupRef = null;
    this.networkDashboard = null;

    this.createSmartGroup = function (event, hFormId, hSaveButtonId) {

        Event.preventDefault(event);

        var tmp_this = this;
        $("#" + hSaveButtonId).click( function(event) {

            Event.preventDefault(event);

            // Retrieving form and all inputs
            var $form = $("#" + hFormId);
            var data = Forms.serialize($form);

            // Retrieving error
            var $errorDiv = $form.find(".error");

            // Validating
            if (data["name"].length == 0) {

                $errorDiv.text("Please provide a name");
                $errorDiv.fadeIn();
                Animations.shake("#" + hSaveButtonId);
                return false;
            }

            // Adding system parameters to data
            data["nid"] = tmp_this.networkId;
            data["sgr"] = tmp_this.smartGroupRef;

            // Submit request
            $.post('./modules/smart_groups/actions/create_smart_group.jsp', data,
                function() {

                    // Highlighting smart groups shortcut
                    $("#network_shortcut_smart_groups").addClass("selected", 500).removeClass("selected", 500);

                    // Move create smart group form to the short cut
                    Animations.flyToTarget(event, "#create_smart_group", "#network_shortcut_smart_groups", false, true, function() {

                        // Changing page to smart group display
                        tmp_this.networkDashboard.go(event, NetworkDashboard.Section.SMART_GROUPS)

                    });

                });
        });
    };
}