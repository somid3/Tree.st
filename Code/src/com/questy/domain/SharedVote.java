package com.questy.domain;

import com.questy.enums.SharedVoteEnum;

import java.util.Date;

public class SharedVote extends Parent {

    private Date createdOn;
    private Integer networkId;
    private Integer userId;
    private Integer smartGroupRef;
    private Integer sharedItemRef;
    private Integer sharedCommentRef;
    private SharedVoteEnum vote;

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSmartGroupRef() {
        return smartGroupRef;
    }

    public void setSmartGroupRef(Integer smartGroupRef) {
        this.smartGroupRef = smartGroupRef;
    }

    public Integer getSharedItemRef() {
        return sharedItemRef;
    }

    public void setSharedItemRef(Integer sharedItemRef) {
        this.sharedItemRef = sharedItemRef;
    }

    public Integer getSharedCommentRef() {
        return sharedCommentRef;
    }

    public void setSharedCommentRef(Integer sharedCommentRef) {
        this.sharedCommentRef = sharedCommentRef;
    }

    public SharedVoteEnum getVote() {
        return vote;
    }

    public void setVote(SharedVoteEnum vote) {
        this.vote = vote;
    }
}
