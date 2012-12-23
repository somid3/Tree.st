function PhotoUpload () {

    this.ref = null;
    this.checksum = null;
    this.hScaledImageId = null;

    this.coordinates = null;
    this.locked = false;

    /**
     * Saves the html id of the image that will be
     * cropped. Also binds the Jcrop mechanism to
     * such image
     */
    this.init = function () {

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

        /**
         * Binding the set face mechanism to the set button. This
         * retrieves the set coordinates and passes it to the server
         * to create the new thumbnail
         */
        $("#set_button").bind("click", function(event) {

            Event.preventDefault(event);

            // Set parameters for calling the set face action
            var parameters = {};

            // Passing user face details
            parameters.r = tmp_this.ref;
            parameters.cs = tmp_this.checksum;

            // Passing coordinates
            var coords = tmp_this.coordinates;

            parameters.x1 = Math.round(coords.x);
            parameters.y1 = Math.round(coords.y);
            parameters.x2 = Math.round(coords.x2);
            parameters.y2 = Math.round(coords.y2);
            parameters.w =  Math.round(coords.w);
            parameters.h =  Math.round(coords.h);

            // Call the set face action
            $.post('./modules/user_panel/actions/set_face.jsp', parameters, function() {

                // Refresh the application
                URL.redirect("/d/app/");

            })

        });

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

    this.onAddQueueItem = function () {

        this.locked = false;

        var $container = $("#face_upload_container");

        var $error = $container.find(".error:first");

        $error.fadeOut();

    };

    this.queueComplete = function () {

        if (this.locked)
            return false;

        var parameters = {};
        parameters.uploaded = "true";
        UPD.clickItem(null, "#user_panel_shortcut_upload_face", "./modules/user_panel/set_face.jsp", parameters);

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