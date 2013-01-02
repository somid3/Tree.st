UserPhotos.Section = {
    UPLOAD: 1,
    VIEW_ALL: 2,
    SET_FACE: 3
};

function UserPhotos () {

    this.resourceRef = null;
    this.resourceChecksum = null;
    this.hScaledImageId = null;

    this.coordinates = null;
    this.locked = false;





    /**
     * Saves the html id of the image that will be
     * cropped. Also binds the Jcrop mechanism to
     * such image
     */
    this.initSetFace = function () {

        var tmp_this = this;

        /**
         * Biding the Jcrop mechanism to the html image that will display the image to be cropped
         */
        $("#" + this.hScaledImageId).Jcrop({

            onChange: function(c) { tmp_this.showPreview(c, tmp_this) },
            onSelect: function(c) { tmp_this.showPreview(c, tmp_this) },
            onRelease: function(c) { tmp_this.showPreview(c, tmp_this) },
            aspectRatio: 1,
            setSelect: [ 10, 10, 70, 70 ]

        });
    };

    /**
     * Binding the set face mechanism to the set button. This
     * retrieves the set coordinates and passes it to the server
     * to create the new thumbnail
     */
    this.submitSetFace = function(event) {

        Event.preventDefault(event);

        // Set parameters for calling the set face action
        var parameters = {};

        // Passing user face details
        parameters.rr = this.resourceRef;
        parameters.rcs = this.resourceChecksum;

        // Passing coordinates
        var coords = this.coordinates;

        parameters.x1 = Math.round(coords.x);
        parameters.y1 = Math.round(coords.y);
        parameters.x2 = Math.round(coords.x2);
        parameters.y2 = Math.round(coords.y2);
        parameters.w =  Math.round(coords.w);
        parameters.h =  Math.round(coords.h);

        // Call the set face action
        $.post('./user_panel/user_photos/actions/set_face.jsp', parameters, function() {

            // Refresh the application
            URL.redirect("/d/app/");

        })

    };


    this.showPreview = function (coords, parent) {

        var rx;
        var ry;

        var $ref = $("#" + parent.hScaledImageId);

        var refWidth = $ref.width();
        var refHeight = $ref.height();

        rx = 60 / coords.w;
        ry = 60 / coords.h;
        $('#temp_x60').css({
            width: Math.round(rx * refWidth) + 'px',
            height: Math.round(ry * refHeight) + 'px',
            marginLeft: '-' + Math.round(rx * coords.x) + 'px',
            marginTop: '-' + Math.round(ry * coords.y) + 'px'
        });

        rx = 40 / coords.w;
        ry = 40 / coords.h;
        $('#temp_x40').css({
            width: Math.round(rx * refWidth) + 'px',
            height: Math.round(ry * refHeight) + 'px',
            marginLeft: '-' + Math.round(rx * coords.x) + 'px',
            marginTop: '-' + Math.round(ry * coords.y) + 'px'
        });

        rx = 20 / coords.w;
        ry = 20 / coords.h;
        $('#temp_x20').css({
            width: Math.round(rx * refWidth) + 'px',
            height: Math.round(ry * refHeight) + 'px',
            marginLeft: '-' + Math.round(rx * coords.x) + 'px',
            marginTop: '-' + Math.round(ry * coords.y) + 'px'
        });

        /**
         * Saves the coordinates set on Jcrop to the parent object
         */
        parent.coordinates = coords;
    };


    this.onError = function (errorType) {

        this.locked = true;

        var $container = $("#face_upload_container");

        var $error = $container.find(".error:first");

        if (errorType.indexOf("UPLOAD_LIMIT_EXCEEDED") != -1) {

            $error.html("Please make sure your photo is less than five (5) megabytes");

        } else if (errorType.indexOf("FILE_SIZE_LIMIT_EXCEEDED") != -1) {

            $error.html("Please make sure your photo is less than five (5) megabytes");

        } else if (errorType.indexOf("FORBIDDEN_FILE_TYPE") != -1) {

            $error.html("The file type you attempted to upload is not a photo");

        } else if (errorType.indexOf("404_FILE_NOT_FOUND") != -1) {

            $error.html("File being uploaded could not be found");

        } else if (errorType.indexOf("Unknown Error") != -1) {

            $error.html("An unknown error occurred, please try again later");

        }

        $error.fadeIn();
    }
}

/**
 * Hides a particular photo of a user
 */
UserPhotos.hidePhoto = function (event, hPhotoId, resourceRef, checksum) {

   Event.preventDefault(event);

   // Sending request to hide photo
   $.post('./user_panel/user_photos/actions/hide_photo.jsp', {rr: resourceRef, rcs: checksum},
       function(data) {

           $("#" + hPhotoId).fadeOut();

       });
};

/**
 * Sets a particular face as the main face for a user
 */
UserPhotos.setProfile = function (event, resourceRef, checksum) {

    Event.preventDefault(event);

    // Sending request to hide photo
    $.post('./user_panel/user_photos/actions/set_profile.jsp', {rr: resourceRef, rcs: checksum},
        function(data) {

            URL.redirect("/d/app");

        });
};

/**
 * Removes selected class from all items on the network dashboard
 * and adds the selected class to the selector provided
 */
UserPhotos.clickItem = function (event, selector, url, parameters, callback) {

    Event.preventDefault(event);

    UserPhotos.unhighlightAll();

    // Add highlight to the selected box
    if (selector) {
        $(selector).addClass("selected");
    }

    var data = {};
    data = $.extend(data, parameters);

    Transitions.load('#user_photos_canvas', url, data, callback);

};

UserPhotos.go = function (event, sendTo, parameters, callback) {

    Event.preventDefault(event);

    if (sendTo == null  || sendTo == UserPhotos.Section.UPLOAD)

        this.clickItem(event, '#user_photos_upload', './user_panel/user_photos/upload.jsp', parameters, callback);

    else if (sendTo == UserPhotos.Section.VIEW_ALL)

        this.clickItem(event, "#user_photos_view", './user_panel/user_photos/photos.jsp', parameters, callback);

    else if (sendTo == UserPhotos.Section.SET_FACE)

        this.clickItem(event, "#user_photos_view", './user_panel/user_photos/set_face.jsp', parameters, callback);

};

UserPhotos.unhighlightAll = function () {

    // Remove all highlights from network dash board
    $("#user_photos_dashboard .shortcut").each(
        function() {
            $(this).removeClass("selected");
        }
    );

};