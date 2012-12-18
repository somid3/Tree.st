package com.questy.domain;

import com.questy.enums.AnswerVisibilityEnum;

import java.util.Date;
import java.util.List;

public class Answer extends Parent {

    private Integer ref;
    private Date createdOn;
    private Integer questionRef;
    private Integer networkId;
    private Integer userId;
    private AnswerVisibilityEnum visibility;
    private List<AnswerOption> options;
    private Boolean again;

    public Integer getRef() {
        return ref;
    }

    public void setRef(Integer ref) {
        this.ref = ref;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getQuestionRef() {
        return questionRef;
    }

    public void setQuestionRef(Integer questionRef) {
        this.questionRef = questionRef;
    }

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

    public AnswerVisibilityEnum getVisibility() {
        return visibility;
    }

    public void setVisibility(AnswerVisibilityEnum visibility) {
        this.visibility = visibility;
    }

    public List<AnswerOption> getOptions() {
        return options;
    }

    public void setOptions(List<AnswerOption> options) {
        this.options = options;
    }

    public Boolean isAgain() {
        return again;
    }

    public void setAgain(Boolean again) {
        this.again = again;
    }

    /**
     * Returns true is any of the options are the "skip"
     * option. Realistically, when a "skip" occurs, the
     * skip option should be the only option in the answer,
     * but there is a loop there in case there is a bug
     * and more than one answer options exist
     * 
     * @return
     */
    public Boolean isSkip() {

        for (AnswerOption ao : options) {
            if (ao.isSkip())
                return true;
        }

        return false;
    }

    public AnswerOption findOptionByRef(Integer ref) {

        if (ref == null)
            return null;

        for (AnswerOption ao : options) {
            if (ref.equals(ao.getOptionRef()))
                return ao;
        }

        return null;
    }
}
