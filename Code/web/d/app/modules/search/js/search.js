function Search () {

    this.networkId = null;
    this.hSearchInputId = null;

    /**
     * Setups the search input field for a new search
     */
    this.setupSearchInput = function () {

        var $searchField = $("#" + this.hSearchInputId);

        // Clear the field input
        $searchField.val("");

    };


    this.bindSearchInput = function() {

        var timerName = "searchTimer";
        var $searchField = $("#" + this.hSearchInputId);

        // Binding keyup on the search field with a delay
        var tmp_this = this;

        $searchField.bind("keyup", function(e) {

            clearTimeout($.data(this, timerName));

            var wait = setTimeout(

                // The function that submits the search
                function() { tmp_this.submitSearch() }

                // Delay when the search runs after the last keypress
                , 500);

            // Set the timer as a global variable
            $(this).data(timerName, wait);

            // Trigger the search if the user presses "enter"
            if (e.keyCode == 13) {

                // Trigger search
                tmp_this.submitSearch();

                // Clear the timer...
                clearTimeout($.data(this, timerName));
            }
        });

    };

    this.viewEverything = function (event) {

        Event.preventDefault(event);

        // Doing the click
        ND.clickItem(event, "#all", './modules/search/search.jsp', null, null);

    };

    this.submitSearch = function (event) {

        var $searchField = $("#" + this.hSearchInputId);

        // Retrieve search term
        var searchText = $searchField.val();

        // Present results on search canvas
        ND.go(event, NetworkDashboard.Section.SEARCH, {s: searchText}, null);

    };
}