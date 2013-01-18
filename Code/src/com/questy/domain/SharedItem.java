package com.questy.domain;

import com.questy.dao.SharedCommentDao;
import com.questy.ifaces.SharedVotable;

import java.util.Date;

public class SharedItem extends Parent implements SharedVotable {

    private Integer userId;
    private Integer networkId;
    private Integer sharedItemRef;
    private Integer smartGroupRef;
    private Date createdOn;
    private Integer totalComments;
    private String text;
    private Boolean hidden;
    private Integer upVotes;
    private Integer downVotes;

    public Integer getSharedItemRef() {
        return sharedItemRef;
    }

    public void setSharedItemRef(Integer sharedItemRef) {
        this.sharedItemRef = sharedItemRef;
    }

    public Integer getSharedCommentRef() {
        return SharedComment.ANY_SHARED_COMMENT_REF;
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(Integer totalComments) {
        this.totalComments = totalComments;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean isHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Integer getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(Integer upVotes) {
        this.upVotes = upVotes;
    }

    public Integer getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(Integer downVotes) {
        this.downVotes = downVotes;
    }

}
