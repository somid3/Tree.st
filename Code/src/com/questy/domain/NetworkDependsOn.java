package com.questy.domain;

import com.questy.enums.RoleEnum;

import java.util.Date;

public class NetworkDependsOn extends Parent {

    private Date createdOn;
    private Integer networkId;
    private Integer dependsOn;
    private RoleEnum role;

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

    public Integer getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(Integer dependsOn) {
        this.dependsOn = dependsOn;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
