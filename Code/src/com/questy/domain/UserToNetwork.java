package com.questy.domain;

import com.questy.enums.RoleEnum;

import java.util.Date;

public class UserToNetwork extends Parent {

    private Date createdOn;
    private Integer userId;
    private Integer networkId;
    private Integer currentPoints;
    private Integer pointsPerLink;
    private Date removedOn;
    private RoleEnum role;

    public UserToNetwork() {}

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

    public Integer getCurrentPoints() {
        return currentPoints;
    }

    public void setCurrentPoints(Integer currentPoints) {
        this.currentPoints = currentPoints;
    }

    public Integer getPointsPerLink() {
        return pointsPerLink;
    }

    public void setPointsPerLink(Integer pointsPerLink) {
        this.pointsPerLink = pointsPerLink;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public Date getRemovedOn() {
        return removedOn;
    }

    public void setRemovedOn(Date removedOn) {
        this.removedOn = removedOn;
    }
}
