package com.questy.domain;

import com.questy.enums.UserLinkDirectionEnum;

import java.util.Date;

public class UserLink extends Parent {

    /**
     * Network for which the user link is valid
     */
    private Integer networkId;

    private Integer fromUserId;
    private Integer toUserId;
    private Date createdOn;
    private UserLinkDirectionEnum direction;

    public UserLink() {}

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

    public UserLinkDirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(UserLinkDirectionEnum direction) {
        this.direction = direction;
    }

}

