package com.questy.domain;

import com.questy.enums.EmailNotificationRateEnum;
import com.questy.enums.NetworkEventEnum;

import java.util.Date;

@Deprecated
public class EmailNotificationRate extends Parent {

    private Date createdOn;
    private Integer networkId;
    private Integer smartGroupId;
    private Integer userId;
    public NetworkEventEnum event;
    public EmailNotificationRateEnum rate;

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

    public Integer getSmartGroupId() {
        return smartGroupId;
    }

    public void setSmartGroupId(Integer smartGroupId) {
        this.smartGroupId = smartGroupId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public NetworkEventEnum getEvent() {
        return event;
    }

    public void setEvent(NetworkEventEnum event) {
        this.event = event;
    }

    public EmailNotificationRateEnum getRate() {
        return rate;
    }

    public void setRate(EmailNotificationRateEnum rate) {
        this.rate = rate;
    }
}
