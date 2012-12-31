function Welcome () {}

Welcome.displayVideo = function (event, hTargetId, hVimeoId) {

    Event.preventDefault(event);

    var $video = $("#" + hTargetId);

    $video
    .fadeOut(500)
    .html('<iframe src="http://player.vimeo.com/video/' + hVimeoId + '?autoplay=true" width="500" height="281" frameborder="0" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe>')
    .fadeIn(500);
};

