package com.questy.services;

import com.questy.dao.NetworkDao;
import com.questy.dao.SharedVoteDao;
import com.questy.dao.UserDao;
import com.questy.domain.Network;
import com.questy.domain.SharedVote;
import com.questy.domain.User;
import com.questy.enums.SharedVoteEnum;
import com.questy.enums.UserAlphaSettingEnum;
import com.questy.enums.UserIntegerSettingEnum;
import com.questy.utils.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class SharedVoteServicesTest {

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
        SharedVoteServices.toggleVote(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, SharedVoteEnum.UP);
        vote = SharedVoteDao.getByAllFields(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);
        Assert.assertEquals(SharedVoteEnum.UP, vote.getVote());

        // Cancel up vote
        SharedVoteServices.toggleVote(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, SharedVoteEnum.UP);
        vote = SharedVoteDao.getByAllFields(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);
        Assert.assertEquals(SharedVoteEnum.NONE, vote.getVote());

        // Submit up vote
        SharedVoteServices.toggleVote(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, SharedVoteEnum.UP);
        vote = SharedVoteDao.getByAllFields(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);
        Assert.assertEquals(SharedVoteEnum.UP, vote.getVote());

        // Switch to down vote
        SharedVoteServices.toggleVote(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, SharedVoteEnum.DOWN);
        vote = SharedVoteDao.getByAllFields(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);
        Assert.assertEquals(SharedVoteEnum.DOWN, vote.getVote());

        // Cancel down vote
        SharedVoteServices.toggleVote(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef, SharedVoteEnum.DOWN);
        vote = SharedVoteDao.getByAllFields(userId, networkId, smartGroupRef, sharedItemRef, sharedCommentRef);
        Assert.assertEquals(SharedVoteEnum.NONE, vote.getVote());

    }

}
