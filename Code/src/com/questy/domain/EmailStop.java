package com.questy.domain;

import com.questy.enums.GlobalEventEnum;

import java.util.Date;

@Deprecated
public class EmailStop extends Parent {

    private Date createdOn;
    private Integer userId;
    public GlobalEventEnum globalEvent;

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

    public GlobalEventEnum getGlobalEvent() {
        return globalEvent;
    }

    public void setGlobalEvent(GlobalEventEnum globalEvent) {
        this.globalEvent = globalEvent;
    }
}
