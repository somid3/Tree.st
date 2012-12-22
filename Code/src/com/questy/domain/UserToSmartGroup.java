package com.questy.domain;

import com.questy.enums.UserToSmartGroupStateEnum;

import java.util.Date;

/**
 * Mappings between smart groups and the users that are either members of the smart group or
 * have marked the smart group with a particular state (i.e.: Favorite or Flagged)
 */
public class UserToSmartGroup extends Parent {

    private Integer networkId;
    private Integer userId;
    private Integer smartGroupRef;
    private Date createdOn;
    private UserToSmartGroupStateEnum state;
    private Boolean member;

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

    public UserToSmartGroupStateEnum getState() {
        return state;
    }

    public void setState(UserToSmartGroupStateEnum state) {
        this.state = state;
    }

    public Boolean isMember() {
        return member;
    }

    public void setMember(Boolean member) {
        this.member = member;
    }

    public Boolean isActive() {

        if (member || state == UserToSmartGroupStateEnum.FAVORITE)
            return true;

        return false;
    }
}
