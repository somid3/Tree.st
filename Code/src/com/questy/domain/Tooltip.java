package com.questy.domain;

import com.questy.enums.TooltipEnum;

import java.util.Date;

public class Tooltip extends Parent {

    private Integer userId;
    private Date createdOn;
    private Date updatedOn;
    private TooltipEnum onTooltip;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

    public TooltipEnum getOnTooltip() {
        return onTooltip;
    }

    public void setOnTooltip(TooltipEnum onTooltip) {
        this.onTooltip = onTooltip;
    }
}
