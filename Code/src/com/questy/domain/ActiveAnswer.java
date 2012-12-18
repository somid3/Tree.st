package com.questy.domain;

import com.questy.enums.AnswerVisibilityEnum;

import java.util.Date;

public class ActiveAnswer extends Parent {

    private Integer networkId;
    private Integer questionRef;
    private Integer optionRef;
    private Integer userId;
    private Date createdOn;
    private AnswerVisibilityEnum visibility;
    
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getOptionRef() {
        return optionRef;
    }

    public void setOptionRef(Integer optionRef) {
        this.optionRef = optionRef;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public AnswerVisibilityEnum getVisibility() {
        return visibility;
    }

    public void setVisibility(AnswerVisibilityEnum visibility) {
        this.visibility = visibility;
    }

    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

    public Integer getQuestionRef() {
        return questionRef;
    }

    public void setQuestionRef(Integer questionRef) {
        this.questionRef = questionRef;
    }
}
