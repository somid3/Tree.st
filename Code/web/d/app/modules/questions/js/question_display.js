


function QuestionDisplay () {

    QuestionDisplay.ANY_OPTION_REF = 0;

    this.backToBackCount = null;
    this.networkId = null;
    this.answeringQuestionRef = null;
    this.maxSelectedOptions = null;

    this.hAddOptionInputId = null;
    this.hAddOptionButtonId = null;

    var selectedOptionRefs = [];

    var isLocked = false;

    this.optionClick = function (event, hOptionId, optionRef) {

        Event.preventDefault(event);

        if (isLocked) return false;
                
        var $optionDiv = $('#' + hOptionId);
        var $optionHiddenInput = $('#' + hOptionId + ' input[name="is_set"]');

        // Hiding the loading and error
        QuestionDisplay.hideLoadingAndError();

        // Is the option already selected?
        if (OptionInput.isSelected($optionHiddenInput)) {

            // Remove highlight
            $optionDiv.removeClass(OptionInput.optionSelectedClass);

            // Mark option as unselected
            OptionInput.unselect($optionHiddenInput);

            // Remove option value
            var index = selectedOptionRefs.indexOf(optionRef);
            if(index != -1) selectedOptionRefs.splice(index, 1);

            // Update or submit button text
            this.updateOrSubmit(event);

        } else {

            // Add highlight
            $optionDiv.addClass(OptionInput.optionSelectedClass);

            // Mark option as selected
            OptionInput.select($optionHiddenInput);

            // Add option value to selected array
            selectedOptionRefs.push(optionRef);

            // Update submit button text
            this.updateOrSubmit(event);

        }
    };

    this.optionAdd = function (event) {

        Event.preventDefault(event);

        if (isLocked) return false;

        // Hiding the loading and error
        QuestionDisplay.hideLoadingAndError();

        // Validating option value
        if (Forms.valGreaterThan("#" + this.hAddOptionInputId, 0)) {

            // Retrieving new option text
            var text = $("#" + this.hAddOptionInputId).val();

            // Creating new option
            var tmp_this = this;
            $.post('./modules/questions/add_option.jsp', {nid: this.networkId, qr: this.answeringQuestionRef, text: text},
                function(data) {

                    // Presenting new div on interface
                    var $div = $("<div/>");
                    $div.css('display', 'none').append(data);
                    $(".options").append($div);
                    $div.fadeIn();

                    // Scrolling to the bottom of the div that contains all options
                    $(".options").animate({ scrollTop: $(".options").prop("scrollHeight") }, 500);

                    /* Note: Do not automatically select the newly added option since a question that is single
                     * choice will automatically get submitted by selecting the option. The user might
                     * simply wish to add an option, but not select it
                     */

                    // Restoring the "add" option form
                    $("#" + tmp_this.hAddOptionInputId).val("");
                }
            );

        } else {

            // Displaying error
            Animations.shake("#" + this.hAddOptionButtonId);
            QuestionDisplay.displayError("Can not accept empty option")

        }

        return false;
    };

    this.optionsFilter = function(hFilterOptionsInputId) {

        // Binding all keystroke events to the filter input
        $("#" + hFilterOptionsInputId).bind("propertychange keyup input paste", function(event){

            // Hiding the loading and error
            QuestionDisplay.hideLoadingAndError();

            // Converting filter to lower case to enable case-insensitive search
            var filter = $("#" + hFilterOptionsInputId).val().toLowerCase();

            var $optsDiv = $(".options");
            var $optDivs = $optsDiv.find(".option");
            var $optionHiddenInput = null;

            // Restoring height of all options
            $optDivs.each(function() {

                // Retrieving option hidden input to see if option is set
                $optionHiddenInput = $(this).find('input');

                // Only hide those options that are not selected
                if (!OptionInput.isSelected($optionHiddenInput)) {

                    $(this).css("display", 'none');

                }

            });

            // Looping through options to see if they contain the filtered text
            $optDivs.each(function() {

                // Is the filter text contained by the value of this option?
                if ($(this).children(".value").text().toLowerCase().indexOf(filter) != -1)

                    // Yes, display the option
                    $(this).css("display", '');

            });

        });

    };

    this.updateOrSubmit = function(event) {

        Event.preventDefault(event);
        if (isLocked) return false;

        // Count how many options have been selected
        var selectedOptions = selectedOptionRefs.length;

        // Calculate how many more options are left to reach max
        var selectMore = this.maxSelectedOptions - selectedOptions;

        // Retrieving the spacer that displays number of options selected
        var $answerStatus = $(".answer_status");

        // Should we submit
        if (selectMore <= 0) {

            // Submit this question
            this.submit(event);


        } else {

            /**
             * Dynamically change the answer status on the client
             * side so the end-user knows how many options are
             * left before question is automatically submitted
             */
            if (this.maxSelectedOptions == 1) {

                // Yes, remove the submit button
                $answerStatus.html("Single choice question, one-click submit enabled");

            } else {

                if (selectedOptions > 0)

                    $answerStatus.html("<b>" + selectedOptions + "</b> option(s) picked, select up to <b>" + selectMore + "</b> more option");

                else {

                    $answerStatus.html("Select up to <b>" + selectMore + "</b> options");

                }

            }
        }
    };

    this.submit = function (event) {

        Event.preventDefault(event);
        if (isLocked) { return false; };

        // Checking that some options have been selected
        if (selectedOptionRefs.length == 0) {
           Animations.shake(".submit");
           QuestionDisplay.displayError("Select an option");
           return;
        }

        // Displaying the loading
        QuestionDisplay.displayLoading();

        // Beginning submission, locking down
        isLocked = true;
        
        // Mark all visibilities as public
        var visibility = 9;

        // Animating the current question to slide out
        var tmp_this = this;

        // Report answer
        $.post('./modules/questions/actions/answer.jsp', {nid: tmp_this.networkId, aqr: tmp_this.answeringQuestionRef, vis: visibility, ors: selectedOptionRefs},

            function(response) {

                tmp_this.currentQuestionOut ( function() {

                    // Display the canvas loading
                    NetworkDashboard.displayLoading();

                    // Display new question
                    var $div = $("<div/>");
                    $div
                        .css('display', 'none')
                        .load("./modules/questions/question_display.jsp", {btb: tmp_this.backToBackCount + 1, fqr: tmp_this.answeringQuestionRef, nid: tmp_this.networkId}, function() { NetworkDashboard.hideLoading(); })
                        .prependTo("#canvas").
                        fadeIn(250);

                    // Parsing the results
                    var responseDoc = $.parseXML($.trim(response));
                    var $response = $(responseDoc);

                    // Extracting whether this question was being answered again, and the points gained
                    var again = $response.find("result").attr("again");
                    again = (again.toLowerCase() === 'true');

                    // Extracting the number of points to add to user interface
                    var points = $response.find("result").attr("points_added");
                    points = parseInt(points);

                    // Adding those points to the UI
                    Points.increment(points);

                    // Submission is done, remove lock
                    isLocked = false;
                });
            }
        );

    };

    this.skip = function (event) {

        Event.preventDefault(event);
        if (isLocked) return false;

        selectedOptionRefs = [];
        selectedOptionRefs.push(QuestionDisplay.ANY_OPTION_REF);
        this.submit(event);
    };

    this.currentQuestionOut = function(callback) {

        // Retrieving current question
        var $currentQuestion = $(".question_container");

        // Cloning current question
        var canvasPosition = $("#canvas").position();
        var $currentQuestionClone = $currentQuestion.clone()

            // Ensuring the clone is not active
            .removeAttr('ref')
            .removeAttr('id')

            // Getting ready to move the clone
            .css("z-index", 1)
            .css("position", "absolute")
            .css("top", canvasPosition.top)
            .css("left", canvasPosition.left)
            .css("background-color", "#fff")

            // Adding clone to network canvas so it can be visible
            .prependTo("#canvas");

        // Creating an open space...
        $currentQuestion.css({height: $currentQuestion.height()}).empty();

        // Highlighting my details
        $("#network_shortcut_profile").addClass("selected", 500).removeClass("selected", 500);

        // Moving cloned question
        $currentQuestionClone.animate({top: canvasPosition.top + 130, left: canvasPosition.left - 200, width: 0, height: 0, opacity: 0}, 500, function() {

            // Removing the animated clone
            $(this).remove();

            // Closing the empty space
            $currentQuestion.animate({height: 0}, 200, function() {

                // Clearing height so it can stretch
                $currentQuestion.css({height: "auto"});

                // Calling the callback
                if (callback != null) callback();

            })

        });
    };

    /**
     * Used by the question reporting system to load a new question in the
     * question display section
     *
     */
    QuestionDisplay.again = function (event, networkId, againQuestionRef) {

        Event.preventDefault(event);

        // Removing current question
        $("#canvas .question_container:first").remove();

        // Display question
        var $div = $("<div/>");
        $div.css('display', 'none')
        .load("./modules/questions/question_display.jsp", {agqr: againQuestionRef, nid: networkId})
        .prependTo("#canvas")
        .fadeIn(250);

        // Scrolling to the top
        $("html, body").animate({scrollTop: 0}, 500);
    };
}

QuestionDisplay.displayLoading = function () {
    var $loading = $("#question_submit_loading");
    var $error = $("#question_submit_error");

    $error.empty().hide();
    $loading.show();
};

QuestionDisplay.displayError = function (errorMessage) {
    var $loading = $("#question_submit_loading");
    var $error = $("#question_submit_error");

    $error.text(errorMessage).fadeIn();
    $loading.hide();
};

QuestionDisplay.hideLoadingAndError = function () {
    var $loading = $("#question_submit_loading");
    var $error = $("#question_submit_error");

    $error.hide();
    $loading.hide();
};



