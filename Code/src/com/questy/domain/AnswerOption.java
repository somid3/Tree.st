package com.questy.domain;

import com.questy.dao.AnswerOptionDao;

public class AnswerOption extends Parent {

    private Integer answerRef;
    private Integer questionRef;
    private Integer networkId;
    private Integer userId;
    private Integer optionRef;

    public Integer getOptionRef() {
        return optionRef;
    }

    public void setOptionRef(Integer optionRef) {
        this.optionRef = optionRef;
    }

    public Integer getAnswerRef() {
        return answerRef;
    }

    public void setAnswerRef(Integer answerRef) {
        this.answerRef = answerRef;
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

    public Boolean isSkip () {

        return (AnswerOptionDao.SKIP_OPTION_REF.equals(optionRef));

    }
}
