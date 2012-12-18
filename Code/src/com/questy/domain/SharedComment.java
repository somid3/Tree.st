package com.questy.domain;

import java.util.Date;

public class SharedComment extends Parent {

    private Integer networkId;
    private Integer userId;
    private Integer smartGroupRef;
    private Integer sharedItemRef;
    private Integer ref;
    private String text;
    private Date createdOn;
    private Boolean hidden;

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
}
