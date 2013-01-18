package com.questy.services;

import com.questy.dao.*;
import com.questy.domain.*;
import com.questy.enums.SharedVoteEnum;
import java.sql.SQLException;

public class SharedVoteServices extends ParentService {

    public static void changeVote(
            Integer userId,
            Integer networkId,
            Integer smartGroupRef,
            Integer sharedItemRef,
            Integer sharedCommentRef,
            SharedVoteEnum applyVote) throws SQLException {

        // Validating
        if (applyVote == SharedVoteEnum.NONE)
            throw new RuntimeException("Can not manually change a shared vote to none");

        // Creating or retrieving shared vote
        SharedVote sharedVote = getOrCreateMapping(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);

        // Does a vote already exist with the same provided vote state
        if (sharedVote.getVote() == applyVote) {

            // Yes, in that case cancel the previous vote to set the as none...

            if (applyVote == SharedVoteEnum.UP)
                cancelUpVote(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);
            else if (applyVote == SharedVoteEnum.DOWN)
                cancelDownVote(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);

        // Is the current state of the user's vote none?
        } else if (sharedVote.getVote() == SharedVoteEnum.NONE) {

            // Yes, in that case simply execute the vote change

            if (applyVote == SharedVoteEnum.UP)
                doUpVote(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);
            else if (applyVote == SharedVoteEnum.DOWN)
                doDownVote(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);

        // Had the user already set a different vote
        } else {

            // Yes, then execute a cancellation first, and then do the vote change

            if (applyVote == SharedVoteEnum.UP) {

                cancelDownVote(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);
                doUpVote(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);

            } else if (applyVote == SharedVoteEnum.DOWN) {

                cancelUpVote(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);
                doDownVote(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);

            }

        }

    }

    private static SharedVote getOrCreateMapping(
        Integer userId,
        Integer networkId,
        Integer smartGroupRef,
        Integer sharedItemRef,
        Integer sharedCommentRef) throws SQLException {

        // Attempting to retrieve object to see if new needs to be created
        SharedVote out = SharedVoteDao.getByAllFields(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);

        // If no vote exists yet then create and retrieve one.
        if (out == null) {
            SharedVoteDao.insert(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);
            out = SharedVoteDao.getByAllFields(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);
        }

        return out;
    }

    /**
     * Increments the gained points of an up vote
     */
    private static void doUpVote(
            Integer userId,
            Integer networkId,
            Integer smartGroupRef,
            Integer sharedItemRef,
            Integer sharedCommentRef) throws SQLException {

        // Update the user to network count
        UserToNetworkDao.incrementSharedUpVotesByUserIdAndNetworkId(null, userId, networkId, 1);

        // Is this a vote change for a shared item or a comment?
        if (sharedCommentRef == SharedComment.ANY_SHARED_COMMENT_REF)

            // Removing vote from a shared item
            SharedItemDao.incrementUpVotesByNetworkIdAndSmartGroupRefAndRef(null, networkId, smartGroupRef, sharedItemRef, 1);

        else

            // Removing vote from a shared comment
            SharedCommentDao.incrementUpVotesByNetworkIdAndSmartGroupRefAndSharedItemRefAndRef(null, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, 1);

        // Updating the state of the vote in the shared vote object
        SharedVoteDao.updateVoteByAllFields(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, SharedVoteEnum.UP);
    }

    /**
     * Cancels the previously gained points of an up vote
     */
    private static void cancelUpVote(
            Integer userId,
            Integer networkId,
            Integer smartGroupRef,
            Integer sharedItemRef,
            Integer sharedCommentRef) throws SQLException {

        // Update the user to network count
        UserToNetworkDao.incrementSharedUpVotesByUserIdAndNetworkId(null, userId, networkId, -1);

        // Is this a vote change for a shared item or a comment?
        if (sharedCommentRef == SharedComment.ANY_SHARED_COMMENT_REF)

            // Change points of a shared item
            SharedItemDao.incrementUpVotesByNetworkIdAndSmartGroupRefAndRef(null, networkId, smartGroupRef, sharedItemRef, -1);

        else

            // Change points of a shared comment
            SharedCommentDao.incrementUpVotesByNetworkIdAndSmartGroupRefAndSharedItemRefAndRef(null, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, -1);

        // Updating the state of the vote in the shared vote object
        SharedVoteDao.updateVoteByAllFields(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, SharedVoteEnum.NONE);
    }

    /**
    * Decreases the lost points due to a down vote
    */
   private static void doDownVote(
           Integer userId,
           Integer networkId,
           Integer smartGroupRef,
           Integer sharedItemRef,
           Integer sharedCommentRef) throws SQLException {

       // Update the user to network count
       UserToNetworkDao.incrementSharedDownVotesByUserIdAndNetworkId(null, userId, networkId, 1);

       // Is this a vote change for a shared item or a comment?
       if (sharedCommentRef == SharedComment.ANY_SHARED_COMMENT_REF)

           // Change points of a shared item
           SharedItemDao.incrementDownVotesByNetworkIdAndSmartGroupRefAndRef(null, networkId, smartGroupRef, sharedItemRef, 1);

       else

           // Change points of a shared comment
           SharedCommentDao.incrementDownVotesByNetworkIdAndSmartGroupRefAndSharedItemRefAndRef(null, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, 1);

       // Updating the state of the vote in the shared vote object
       SharedVoteDao.updateVoteByAllFields(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, SharedVoteEnum.DOWN);
   }

   /**
    * Cancels the previously lost points of a down vote
    */
   private static void cancelDownVote(
           Integer userId,
           Integer networkId,
           Integer smartGroupRef,
           Integer sharedItemRef,
           Integer sharedCommentRef) throws SQLException {

       // Update the user to network count
       UserToNetworkDao.incrementSharedDownVotesByUserIdAndNetworkId(null, userId, networkId, -1);

       // Is this a vote change for a shared item or a comment?
       if (sharedCommentRef == SharedComment.ANY_SHARED_COMMENT_REF)

           // Change points of a shared item
           SharedItemDao.incrementDownVotesByNetworkIdAndSmartGroupRefAndRef(null, networkId, smartGroupRef, sharedItemRef, -1);

       else

           // Change points of a shared comment
           SharedCommentDao.incrementDownVotesByNetworkIdAndSmartGroupRefAndSharedItemRefAndRef(null, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, -1);

       // Updating the state of the vote in the shared vote object
       SharedVoteDao.updateVoteByAllFields(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, SharedVoteEnum.NONE);
   }

}
