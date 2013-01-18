function SharedVote () { }


/**
 * Applies a specific vote to a shared votable item
 */
SharedVote.applyVote = function (event, hVotesId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, applyVote) {

    Event.preventDefault(event);

    // Send the post event to toggle the favorite
    $.post('./modules/share/apply_vote.jsp', {nid: networkId, sgr: smartGroupRef, sir: sharedItemRef, scr: sharedCommentRef, av: applyVote}, function(data) {

        var $votesDiv  = $("#" + hVotesId);

        // Remove old star
        $votesDiv.replaceWith(data);

    });

};

