package com.questy.domain;

import java.util.Date;

public class UserMessageGroup extends Parent {

    private Integer networkId;
    private Integer fromUserId;
    private Integer toUserId;
    private Date updatedOn;
    private Boolean isRead;
    private Boolean isReplied;

    public UserMessageGroup() {}

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Boolean getReplied() {
        return isReplied;
    }

    public void setReplied(Boolean replied) {
        isReplied = replied;
    }
}

