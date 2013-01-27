function SmartSearch () {

    this.MAX_CRITERION = 5;

    this.createSmartGroup = function (event, networkId, smartGroupRef, hFormId) {

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
        parameters.nid = networkId;
        parameters.sgr = smartGroupRef;

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
                    ND.go(event, NetworkDashboard.Section.SMART_GROUPS)

                });

            }

        });

    };


    /**
     * When outside of the search page, this transitions to the search page and adds the desired
     * question
     */
    this.goSmartSearch = function (event, searchNetworkId, searchQuestionRef, searchOptionRef) {

        Event.preventDefault(event);

        // Scroll to the logo
        Animations.scrollToTop();

        // Switching to search on the center canvas
        var parameters = {};
        parameters.snid = searchNetworkId;
        parameters.sqr = searchQuestionRef;
        parameters.sor = searchOptionRef;

        // Go the smart search page and add clicked question
        ND.go(event, NetworkDashboard.Section.SMART_SEARCH, parameters);
    };

    this.displayError = function (message) {

        var $smartSearchError = $("#smart_search_error");
        var $canvasLoading = $("#smart_search_loading");
        $canvasLoading.hide();

        var $smartSearchErrorMessage = $smartSearchError.find(".message");
        $smartSearchErrorMessage.text(message);
        $smartSearchError.css({display: "block"});

    };


    /**
     * Once in the search page, this adds a question to the search criteria
     */
    this.questionAdd = function (event, hostingNetworkId, hostingSmartGroupRef, hCriteriaSearchQuestionId, questionNetwork, questionRef, optionRef) {


        Event.preventDefault(event);

        var $smartSearchCriterion = $("#smart_search_criterion");


        // Ensuring there are no more than 10 criteria
        var noOfCriterias = $smartSearchCriterion.find(".criteria").length;
        if (noOfCriterias >= this.MAX_CRITERION) {

            /* Display smart search error! */
            this.displayError("Too many qualities in the Smart Search!");
            return true;
        }

        // Collecting all parameters to add question
        var parameters = {};
        parameters.nid = hostingNetworkId;
        parameters.sgr = hostingSmartGroupRef;

        parameters.snid = questionNetwork;
        parameters.sqr = questionRef;

        // Adding a question with an option reference is optional
        if (optionRef)
            parameters.sor = optionRef;

        // Removing duplicate questions
        var $criteriaSearchQuestionId = $("#" + hCriteriaSearchQuestionId);
        $criteriaSearchQuestionId.remove();

        /* Adding the new question - Before we submit the new search,
         * we MUST wait for the load() to finish since the XML query
         * requires the new DOM
         */
        var tmp_this = this;
        var $div = $("<div/>");
        $div
        .hide()
        .load("./modules/smart_groups/actions/add_criteria.jsp", parameters, function() {

            $div.appendTo("#smart_search_criterion").fadeIn(250);
            tmp_this.generateAndSubmit(hostingNetworkId, hostingSmartGroupRef);

        })

    };



    /**
     * This function can not make a direct reference to "this"
     * because it is called after the ajax completes by jQuery,
     * see the questionAdd method
     */
    this.generateAndSubmit = function (hostingNetworkId, hostingSmartGroupRef) {

      // Generate and submit query to retrieve faces
      var query = SS.generateQuery();

      // Submitting the query to see latest results
      SS.submitQuery(hostingNetworkId, hostingSmartGroupRef, query);

    };




    this.questionRemove = function (event, hostingNetworkId, hostingSmartGroupRef, hQuestionQueryId) {

        Event.preventDefault(event);

        // Removing question
        $("#" + hQuestionQueryId).remove();

        // Generate and submit query to retrieve faces
        var query = this.generateQuery();
        this.submitQuery(hostingNetworkId, hostingSmartGroupRef, query);

    };




    /**
     * Analyzes the form and generates the XML query
     */
    this.generateQuery = function () {

        // Defining communication protocol
        var query = "<?xml version=\"1.0\"?>";

        // Write Smart Group query
        query = '<query>';

        // For every question
        var score = null;
        var network = null;
        var ref = null;

        // Looping over all criteria
        var $criterion = $("#smart_search_criterion .criteria");
        var $criteria = null;
        var $options = null;
        var $option = null;
        var optionScore = null;
        var optionRef = null;

        // Looping over each criteria
        $criterion.each( function () {

            $criteria = $(this);

            // Gathering question details
            score = $criteria.find('.score:first').text();
            network = $criteria.find('.network:first').text();
            ref = $criteria.find('.ref:first').text();

            // Skip this question if score, network or ref are undefined
            if(!score){ return true; }
            if(!network){ return true; }
            if(!ref){ return true; }

            // Retrieving selected options
            $options = $criteria.find(".selected_option");

            // Skip this question if no options are selected
            if($options.length == 0){ return true; }

            // Adding question details to query
            query = query + '<question ref="' + ref + '" score="' + score + '" network="' + network + '">';

            // Looping over each selected option
            $options.each( function() {

                $option = $(this);

                // Gathering option details
                optionScore = $option.find('.score:first').text();
                optionRef = $option.find('.ref:first').text();

                // Skip this option if score, ref are undefined
                if(!score){ return true; }
                if(!ref){ return true; }

                // Adding option to query
                query = query + '<option ref="' + optionRef + '" score="' + optionScore + '">';
                query = query + '<criteria type="is_set"></criteria>';
                query = query + "</option>";

            });

            // Ending the question
            query = query + "</question>";

        });

        // Ending the question
        query = query + "</query>";

        return query;
    };


    /**
     * Sends the AJAX post to retrieve the faces of the smart group
     */
    this.submitQuery = function (hostingNetworkId, hostingSmartGroupRef, query) {

        // Show or hide certain tools, messages
        this.showOrHideItems();

        // Execute query
        this.executeQuery(hostingNetworkId, hostingSmartGroupRef, query)
    };


    this.executeQuery = function (hostingNetworkId, hostingSmartGroupRef, query) {

        var parameters = {};
        parameters.nid = hostingNetworkId;
        parameters.sgr = hostingSmartGroupRef;
        if (query != null) {
            parameters.query = query;
        }

        var $smartGroupFaces = $("#smart_group_faces");
        var $canvasLoading = $("#smart_search_loading");
        var $smartSearchError = $("#smart_search_error");

        // Showing the working dialog and hiding faces
        $canvasLoading.show();
        $smartGroupFaces.hide();
        $smartSearchError.hide();

        // Retrieve results
        var tmp_this = this;
        $.post("./modules/smart_groups/smart_results.jsp", parameters, function(data) {

            $canvasLoading.hide();
            $smartGroupFaces.empty().append(data).show();

        }).error(function () {

            /* Display smart search error! */
            tmp_this.displayError("Argh! There was an error!");
            return true;

        });

    };

    /**
     * Analyzes if any questions are being displayed, and
     * presents/hides different user interfaces accordingly.
     * Method gets run befoe a query is submitted
     */
    this.showOrHideItems = function () {

        var $smartSearchWelcome = $("#smart_search_welcome");
        var $smartSearchCriterion = $("#smart_search_criterion");
        var $smartGroupFaces = $("#smart_group_faces");

        var $criterion = $("#smart_search_criterion .criteria");

        // Are there any options in the query?
        if ($criterion.length == 0) {

            // Show welcome message
            $smartSearchWelcome.css({display: "block"});

            // Hide tools
            $smartSearchCriterion.hide();

            // Empty out the faces results
            $smartGroupFaces.hide();

        } else {

            // Hide welcome message
            $smartSearchWelcome.hide();

            // Show tools
            $smartSearchCriterion.css({display: "block"});

        }

    };


    this.optionClick = function (event, hEditOptionId, hCriterionQuestionId, hCriterionOptionId) {

        Event.preventDefault(event);

        // Highlight all necessary options
        var isHighlighted = this.toggleHighlightOption(hEditOptionId);

        // Is the option now highlighted?
        if (isHighlighted) {

            // Removing the option were are about to add
            var $criterionOption = $("#" + hCriterionOptionId);
            $criterionOption.remove();

            // Criterion question
            var $criterionSelectedOptions = $("#" + hCriterionQuestionId + " .selected_options");

            // Add option to criterion
            var $editOption = $('#' + hEditOptionId);
            var $templateCriterionOption = $editOption.find(".selected_option").clone();
            $templateCriterionOption.attr("id", hCriterionOptionId).attr("style", null);
            $criterionSelectedOptions.append($templateCriterionOption);

        } else {

            // Criterion option
            var $criterionOption = $("#" + hCriterionOptionId);

            // Remove option from criterion list
            $criterionOption.remove();

        }

        // Hide all other sliders
        this.hideAllSliders();
    };



    this.toggleHighlightOption = function (hEditOptionId) {

        // Question objects in the edit criteria dialog
        var $editCriteria = $("#edit_criteria");
        var $editQuestion = $editCriteria.find(".question:first");
        var $editQuestionScore = $editQuestion.find(".score:first");

        // Option objects in the edit criteria dialog
        var $editOption = $('#' + hEditOptionId);
        var $editOptionBar = $editOption.find(".bar:first");
        var $editOptionScore = $editOption.find(".score:first");
        var $editOptionHiddenInput = $editOptionBar.children('input[name="is_set"]:first');

        var isHighlighted = false;

        // Check whether criteria is set or not
        if (OptionInput.isSelected($editOptionHiddenInput)) {

            // Criteria was set, unset it
            OptionInput.unselect($editOptionHiddenInput);

            // Remove option highlight
            $editOptionBar.removeClass(OptionInput.optionSelectedClass);

            // Hide option score
            $editOptionScore.css("display", "none");

            isHighlighted = false;

        } else {

            // Criteria was not set, set it
            OptionInput.select($editOptionHiddenInput);

            // Add option highlight
            $editOptionBar.addClass(OptionInput.optionSelectedClass);

            // Display option score
            $editOptionScore.css("display", "block");

            // Display question score
            $editQuestionScore.css("display", "block");

            isHighlighted = true;
        }

        return isHighlighted;
    };

    this.showOrHideSlider = function(event, hSliderId) {

        Event.preventDefault(event);
        
        $('#' + hSliderId).toggle();
    };


    this.hideAllSliders = function() {
        $('.question_query .slider').each( function(i) {
            $(this).hide();
        });
    };


    this.changeTick = function(event, hCriteriaObjectId, hScoreId, score) {

        Event.preventDefault(event);

        // Displaying new score
        var $scoreDiv = $('#' + hScoreId);
        $scoreDiv.text(score);

        // Replace score in criterion (could be for the question or option)
        var $criteriaObject = $("#" + hCriteriaObjectId);
        var $criteriaScore = $criteriaObject.find(".score:first");
        $criteriaScore.text(score);

        // Hide all other sliders
        this.hideAllSliders();
    };


    this.displayEditCriteria = function(
        event,
        hostingNetworkId,
        hsmartGroupRef,
        criteriaNetworkId,
        criteriaQuestionRef,
        data,
        callback) {

        Event.preventDefault(event);

        // Retrieving edit criteria dialog
        var editCriteriaDialog = $("#edit_criteria");

        // Creating parameters
        var parameters = {};
        parameters.sgnid = hostingNetworkId;
        parameters.sgr = hsmartGroupRef;
        parameters.cnid = criteriaNetworkId;
        parameters.cqr = criteriaQuestionRef;
        parameters = $.extend(parameters, data);

        // Loading the criteria contents
        editCriteriaDialog.load("./modules/smart_groups/edit_criteria.jsp", parameters, function () {

            // Showing edit criteria dialog
            editCriteriaDialog.show();

            if (callback)
                callback();
        });

    };

    this.submitEditCriteria = function(event, hostingNetworkId, hostingSmartGroupRef)  {

        Event.preventDefault(event);

        // Clear the recent has of added criteria options
        var tmp_this = this;
        $("#edit_criteria").fadeOut( function() {

            tmp_this.generateAndSubmit(hostingNetworkId, hostingSmartGroupRef);

        });

    };

};




