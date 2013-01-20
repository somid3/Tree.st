

function Transitions() {}

Transitions.postFadeIn = function (selector, postUrl, parameters, callback) {

    $(selector).css({opacity: 0})

    $.post(postUrl, parameters, function(data) {

        $(selector).html(data);

        $(selector).animate({opacity: 1}, 400, callback);
    });

};

/*
 * Quickly replaces the contents of the page
 */
Transitions.load = function (selector, loadUrl, parameters, onCompleteCallback) {

    $(selector).load(loadUrl, parameters, onCompleteCallback);

};

/*
 * Fades out, loads and fades in the contents for a selector
 */
Transitions.fadeOutLoadFadeIn = function (selector, loadUrl, parameters, callback) {

    $(selector).animate({opacity: 0}, 400, function() {
        $(this).load(loadUrl, parameters, function() {
            $(this).animate({opacity: 1}, 400, callback);
        });
    });

};

/*
 * Loads and fades in the contents for a selector
 */
Transitions.loadFadeIn = function (selector, loadUrl, parameters, callback) {

    $(selector).css({opacity: 0}).load(loadUrl, parameters, function() {
        $(this).animate({opacity: 1}, 400, callback);
    });
            
};

/*
 * Fades out
 */
Transitions.fadeOut = function (selector, callback) {

    $(selector).animate({opacity: 0}, 400, callback);

};

Transitions.fadeIn = function (selector, callback) {

    $(selector).animate({opacity: 1}, 400, callback);

};

function Divs() {}
Divs.maxHeight = function (selector, maxHeight, setHeightTo, callbackHeightUnder, callbackHeightOver) {

    var $div = $(selector);
    var height = $div.height();

    if (height > maxHeight) {

        // Setting height to the maximum
        $div.css("height", setHeightTo);

        // Callback because height went over
        if (callbackHeightOver  != null)
            callbackHeightOver();

    } else {

        // Callback because height is not over
        if (callbackHeightUnder != null)
            callbackHeightUnder();

    }

};

function Textareas() {}

/* Serializes a form into a hash */
Textareas.autosize = function (selector, minHeight) {

    var $textarea = $(selector);

    $textarea.keydown(function () {

        // Retrieve height of content
        var contentHeight = $textarea.height();

        if (contentHeight > minHeight) {

            // Set height of text area to that of content
            $textarea.css("height", contentHeight)

        }


    });

}

function Forms() {}

/* Serializes a form into a hash */
Forms.serialize = function (formSelector) {

    var $form = $(formSelector);

    var array = $form.serializeArray();
    var data = {};

    for(i=0; i<array.length; i++) {
        var a = array[i];
        var name = a.name;
        var value = a.value;
        data[name] = value;
    };

    return data;
};

Forms.valGreaterThan = function (selector, greaterThan) {

    var input = $(selector);
    var val = input.val();
    var placeholder = input.attr("title");

    if (val == placeholder) {

        return false;
    }

    if (val.length > greaterThan) {

        return true;
    }


    return false;

};



function URL() {}

URL.redirect = function (url) {

    setTimeout (

        function(){

            window.location.href = url;

        } , 300
     )

}


/**
 * A simple two dimensional array that works as a registry
 * @constructor
 */
function Registry() {

    this.registeredItems = {};

    this.registerParent = function (parent) {

        // Create space for string1 if it does not exist
        if (!this.registeredItems[parent])
            this.registeredItems[parent] = {};

    };

    this.registerChild = function (parent, child, value) {

        // Create the parent if it does not exist already
        this.registerParent(parent);

        this.registeredItems[parent][child] = value;
    };

    this.unregisterParent = function (parent) {

        if (this.registeredItems[parent])
            this.registeredItems[parent] = null;

    };

    this.unregisterChild = function (parent, child) {

        this.registerParent(parent);

        this.registeredItems[parent][child] = null;
    };


    this.getChildrenOfParent = function (parent) {

        var children = this.registeredItems[parent];

        // If there are no children then return false
        if (!children)
            return null;

        // Looping through all the registered children
        var out = {};
        for (var child in children) {

            if (children[child])
                out[child] = children[child];
        }

        return out;
    };

    this.getChild = function (parent, child) {

        var children = this.registeredItems[parent];

        if (!children) return null;

        return children[child];
    };


    this.isParentWithChildren = function (parent) {

        var children = this.registeredItems[parent];

        // If there are no children then return false
        if (!children)
            return false;


        // Looping through all the reqistered children
        for (var child in children) {

            if (children[child])
                return true;
        }

        return false;
    };

    this.isChildRegistered = function (parent, child) {

        var child = this.getChild(parent, child)

        if (child)
            return true;

        return false;
    };

    this.getRegistry = function () {
        return this.registeredItems;
    };

    this.isEmpty = function () {

        if (Object.keys(this.getRegistry()).length)
            return false;
        else
            return true;
    };

    this.containsChildren = function () {

        for (var parent in this.getRegistry()) {

            if (this.isParentWithChildren(parent))
                return true;
        }

        return false;
    }




};







function Animations() {};

/**
 * This is a sensitive function. If the "moveMeSelector" starts positioned as
 * "absolute," then "clone" must be true!
 *
 */
Animations.flyToTarget = function (event, moveMeSelector, targetSelector, clone, shrink, callback) {

    var $to = $(targetSelector);
    var $moveMe = $(moveMeSelector);

    var position_at = $moveMe.offset();
    var position_to = $to.offset();

    // Going to the middle of goal
    position_to.left = position_to.left + Math.round(($to.width() / 2));
    position_to.top = position_to.top + Math.round(($to.height() / 2));

    var moveY;
    var moveX;

    if (clone) {

        // Cloning item to move
        $moveMe = $moveMe.clone();

        // Making clone movable
        $moveMe
        .css("position", "absolute")
        .css("z-index", 1000)
        .css("left", 0)
        .css("top", 0)

        // Adding clone to the DOM
        .prependTo(moveMeSelector);

        // Movement of 'absolute' position is the difference of the movement
        moveX = position_to.left - position_at.left;
        moveY = position_to.top - position_at.top;

    } else {

        // Making item movable
       $moveMe
        .css("position", "fixed")
        .css("left", position_at.left)
        .css("top", position_at.top);

        // Movement of 'fixed' position is the final goal
        moveX = position_to.left;
        moveY = position_to.top;

    }

    var animationParameters = {};
    animationParameters.top = moveY;
    animationParameters.left = moveX;

    if (shrink) {
        animationParameters.width = 0;
        animationParameters.height = 0;
    }

    $moveMe
    .css("z-index", 1000)
    .animate(animationParameters, 600, function() {

        // Hide the item that was moved
        if (shrink) $moveMe.remove();

        // Callback after all the animations are said and done
        if (callback != null)
            callback();
    });
};

/**
 * General shaking function when an error occurs
 * @param selector
 */
Animations.shake = function (selector) {
    $(selector).effect("shake", { times: 2, distance: 5 }, 250);
};

Animations.bounce = function (selector) {
    $(selector).effect("bounce", { times: 2, distance: 5 }, 250);
};

Animations.wave = function (selector) {
    $(selector).effect("bounce", { times: 1, distance: 15 }, 2000);
};

Animations.outTop = function (selector, callback) {

    var $moveMe = $(selector);

    // Stop any current animations
    $moveMe.stop();

    // The the item out
    $moveMe
    .css("position", "absolute")
    .animate({top: -1500}, 400, function (){

        if (callback != null)
            callback();

    });
};

Animations.inTopAndBounce = function (selector, delay, callback) {

    var $moveMe = $(selector);

    $moveMe.delay(delay).animate({top: 120}, 500, function () {
        $moveMe.animate({top: -10}, 500, function () {
            $moveMe.animate({top: 5}, 700, function () {
                $moveMe.animate({top: 0}, 800, function () {

                    if (callback != null)
                        callback();

                })
            })
        })
    });

}


Animations.toPosition = function (selector, toLeft, toTop, delay, callback) {

    var $moveMe = $(selector);

    $moveMe.animate({left: toLeft, top: toTop}, delay, function (){

        if (callback != null)
            callback();

    });
};

Animations.wordByWord = function (text, receivingSelector) {

    var str = text;

    var divs = '<div>' + str.split(/\s+/).join(' </div><div>') + '</div>';

    var $divs = $(divs);

    $divs.hide().appendTo(receivingSelector);

    var i = 0;

    var interval = setInterval(function() {

        var $div = $($divs.get(i));

        // Base case to end interval
        if (!$div.length) {
            clearInterval(interval);
        }

        $div.show();

        i++;

    }, 300);

};

Animations.dance = function (selector, danceNoiseLength, danceAfter) {

    setInterval(function() {
        $(selector)
            .animate({left: 10}, 100)
            .animate({left: -5}, 100)
            .animate({left: 0}, 100);
    }, (Math.random() * danceNoiseLength + danceAfter));

};

Animations.scrollTo = function (hItemId) {

    // Retrieve selected item
    var $item = $("#" + hItemId);

    // Scrolling to the bottom of the div that contains all options
    $("html, body").animate({ scrollTop: $item.prop("scrollHeight") }, 500);

};

Animations.scrollToTop = function () {

    // Scrolling to the bottom of the div that contains all options
    $("html, body").animate({ scrollTop: 0}, 500);

};



function Css () {};

Css.devRefreshCss = function (href, rev, development) {

    if (!development) return false;

    var alreadyLoaded = false;

    // Loop through all 'link' elements in the head
    $("head link").each( function () {

        /**
         *  If we are in development mode, iterate
         *  through all the 'link' elements and remove
         *  the link element whose url contains the one
         *  we are attempting to add, and then add it
         *  again -- in essence, refresh the 'link'
         *  element
         */
      
         // Do the hrefs match
         if ( $(this).attr("href").toLowerCase().indexOf(href) == 0 ) {

            // Document that css has already been loaded
            alreadyLoaded = true;

            // Yes, delete the current 'link' element
            $(this).remove();
            alreadyLoaded = false;

        }
    });

    if (!alreadyLoaded) {
        Css.addCss(href + "?" + rev, development);
    }

};

Css.addCss = function (href, development) {

    /**
     * If in development mode add timestamp to href so no browser
     * can cache the results
     */
     if (development) {
        var timestamp = (new Date()).getTime();
        href = href + "?" + timestamp;
     }

    var script = $('<link>', {
       rel: 'stylesheet',
       type: 'text/css',
       href: href
    }).appendTo(document.head);

};


/**
 * Makes sure all javascript classes use the same logic to ensure
 * whether or not an option has been selected
 *
 * @constructor
 */
function OptionInput () {};

OptionInput.optionSelectedValue = "is_set";
OptionInput.optionSelectedClass = "is_set";

OptionInput.select = function ($optionInput) {
    $optionInput.val(OptionInput.optionSelectedValue);
};

OptionInput.unselect = function ($optionInput) {
    $optionInput.val("");
};

OptionInput.isSelected = function ($optionInput) {
    return ($optionInput.val() === OptionInput.optionSelectedValue)
};




function Global () {};

Global.randomString = function (length) {

    if (length == null) length = 32;

	var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz";
	var out = '';
    var i;
    var rnum;

	for (i = 0; i < length; i++) {
		rnum = Math.floor(Math.random() * chars.length);
		out += chars.substring(rnum,rnum+1);
	}

    return out;
}

function Pagination () {};
Pagination.bindScrollPagination = function (callbackOnScroll) {

    // Unbind all scroll events on the window
    Pagination.unbindScrollPagination();

    // Bind a window scroll to call the shared item scroll
    $(window).bind("scroll", function(event) {

        var docHeight      = parseInt($(document).height());
        var windowHeight   = parseInt($(window).height());
        var scrollAtHeight = parseFloat(docHeight - parseFloat(docHeight / 10) - windowHeight);
        var scrollY        = $(window).scrollTop();

        // Check if the scroll height is met and pull next record set
        if (scrollY >= scrollAtHeight) {

            clearTimeout($.data(this, 'scrollPagination'));

            var wait = setTimeout(callbackOnScroll, 200);

            $(this).data('scrollPagination', wait);

        }

    });

};

Pagination.unbindScrollPagination = function () {
    $(window).unbind("scroll");
    clearTimeout($.data(this, 'scrollPagination'));
};








function Binding () {};
Binding.bindInputKeyPress = function (timerName, fieldSelector, callback) {

    var $field = $(fieldSelector);

    $field.bind("keyup", function(event) {

        clearTimeout($.data(this, timerName));

        var wait = setTimeout(

            callback

            // Delay when the search runs after the last keypress
            , 500);

        // Set the timer as a global variable
        $(this).data(timerName, wait);

        // Trigger the search if the user presses "enter"
        if (event.keyCode == 13) {

            callback();

            // Clear the timer...
            clearTimeout($.data(this, timerName));
        }
    });
};




function Event () {};
Event.preventDefault = function (event) {

    if (event != null && event.preventDefault) {

        try {
            event.preventDefault();
        } catch (e) {}

    }

};