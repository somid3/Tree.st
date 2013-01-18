package com.questy.services;

import com.questy.dao.SharedCommentDao;
import com.questy.dao.SharedItemDao;
import com.questy.dao.SharedVoteDao;
import com.questy.domain.SharedComment;
import com.questy.domain.SharedItem;
import com.questy.domain.SharedVote;
import com.questy.enums.SharedVoteEnum;
import com.questy.ifaces.SharedVotable;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class SharedVoteServicesTest {

    @Test
    public void testOne() throws SQLException {

        Integer userId = 3;
        Integer networkId = 2000;
        Integer smartGroupRef = 113;
        Integer sharedItemRef = 4;
        Integer sharedCommentRef = SharedComment.ANY_SHARED_COMMENT_REF;
        SharedVoteEnum vote = SharedVoteEnum.UP;
        SharedVote sharedVote = null;
        SharedVotable sharedVotable = null;

        // Deleting all inactive votes
        SharedVoteDao.deleteInactive();

        // Changing vote of shared votable
        SharedVoteServices.changeVote(
            userId,
            networkId,
            smartGroupRef,
            sharedItemRef,
            sharedCommentRef,
            vote);

        // Retrieving the shared votable
        sharedVotable = SharedItemDao.getByNetworkIdAndSmartGroupRefAndRef(
                null,
                networkId,
                smartGroupRef,
                sharedItemRef);

        // Retrieving the shared vote via the shared votable, and that the vote value is correct
        sharedVote = SharedVoteDao.getByUserIdAndSharedVotable(userId, sharedVotable);
        Assert.assertNotNull(sharedVote);
        Assert.assertEquals(SharedVoteEnum.UP, sharedVote.getVote());

        // Making the vote inactive
        SharedVoteServices.changeVote(
                userId,
                networkId,
                smartGroupRef,
                sharedItemRef,
                sharedCommentRef,
                vote);

        // Ensuring shared vote got changed to none
        sharedVote = SharedVoteDao.getByUserIdAndSharedVotable(userId, sharedVotable);
        Assert.assertEquals(SharedVoteEnum.NONE, sharedVote.getVote());

        // Deleting all inactive votes
        SharedVoteDao.deleteInactive();

        // Ensuring inactive vote got deleted
        sharedVote = SharedVoteDao.getByUserIdAndSharedVotable(userId, sharedVotable);
        Assert.assertNull(sharedVote);
    }

    @Test
    public void testAll() throws SQLException {

        Integer userId = 3;
        Integer networkId = 0;
        Integer smartGroupRef = 0;
        Integer sharedItemRef = 0;
        Integer sharedCommentRef = 1;
        SharedVote vote = null;

        // Ensuring vote is first deleted
        SharedVoteDao.updateVoteByAllFields(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, SharedVoteEnum.NONE);
        SharedVoteDao.deleteInactive();

        // Attempting to retrieve a non existing shared vote
        vote = SharedVoteDao.getByAllFields(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);
        Assert.assertEquals(null, vote);

        // Submit up vote
        SharedVoteServices.changeVote(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, SharedVoteEnum.UP);
        vote = SharedVoteDao.getByAllFields(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);
        Assert.assertEquals(SharedVoteEnum.UP, vote.getVote());

        // Cancel up vote
        SharedVoteServices.changeVote(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, SharedVoteEnum.UP);
        vote = SharedVoteDao.getByAllFields(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);
        Assert.assertEquals(SharedVoteEnum.NONE, vote.getVote());

        // Submit up vote
        SharedVoteServices.changeVote(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, SharedVoteEnum.UP);
        vote = SharedVoteDao.getByAllFields(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);
        Assert.assertEquals(SharedVoteEnum.UP, vote.getVote());

        // Switch to down vote
        SharedVoteServices.changeVote(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, SharedVoteEnum.DOWN);
        vote = SharedVoteDao.getByAllFields(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);
        Assert.assertEquals(SharedVoteEnum.DOWN, vote.getVote());

        // Cancel down vote
        SharedVoteServices.changeVote(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, SharedVoteEnum.DOWN);
        vote = SharedVoteDao.getByAllFields(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);
        Assert.assertEquals(SharedVoteEnum.NONE, vote.getVote());

    }

}
