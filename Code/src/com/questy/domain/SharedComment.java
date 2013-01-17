package com.questy.domain;

import com.questy.ifaces.SharedVotable;

import java.util.Date;

public class SharedComment extends Parent implements SharedVotable {

    private Integer networkId;
    private Integer userId;
    private Integer smartGroupRef;
    private Integer sharedItemRef;
    private Integer ref;
    private String text;
    private Date createdOn;
    private Boolean hidden;
    private Integer upVotes;
    private Integer downVotes;


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

    public Integer getRef() {
        return ref;
    }

    public void setRef(Integer ref) {
        this.ref = ref;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Boolean getHidden() {
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
