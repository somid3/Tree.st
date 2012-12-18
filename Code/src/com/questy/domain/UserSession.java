package com.questy.domain;

import java.util.Date;

public class UserSession extends Parent {
    private Date createdOn;
    private Date updatedOn;
    private Integer userId;
    private String checksum;
    private String httpAgent;
    private Boolean persistent;

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getHttpAgent() {
        return httpAgent;
    }

    public void setHttpAgent(String httpAgent) {
        this.httpAgent = httpAgent;
    }

    public Boolean isPersistent() {
        return persistent;
    }

    public void setPersistent(Boolean persistent) {
        this.persistent = persistent;
    }

}
