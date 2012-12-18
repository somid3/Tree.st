package com.questy.domain;

import java.util.Date;


public class FlowRule extends Parent {

    private Date createdOn;

    /**
     * Question reference that the user last answered
     */
    private Integer fromQuestionRef;

    /**
     * An optional option reference from the previous question
     * that the user selected in the previous answer
     */
    private Integer fromOptionRef;

    /**
     * If multiple flow rules with the same question reference and
     * option reference exist, then the order defines the sequence
     * in which these questions will be displayed
     */
    private Integer order;

    /**
     * Next question which the user should be taken to based
     * on the question (and option, if any) that the user
     * last answered
     */
    private Integer toQuestionRef;

    /**
     * All questions are local to this network's scope
     */
    private Integer networkId;

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getFromQuestionRef() {
        return fromQuestionRef;
    }

    public void setFromQuestionRef(Integer fromQuestionRef) {
        this.fromQuestionRef = fromQuestionRef;
    }

    public Integer getToQuestionRef() {
        return toQuestionRef;
    }

    public void setToQuestionRef(Integer toQuestionRef) {
        this.toQuestionRef = toQuestionRef;
    }

    public Integer getFromOptionRef() {
        return fromOptionRef;
    }

    public void setFromOptionRef(Integer fromOptionRef) {
        this.fromOptionRef = fromOptionRef;
    }

    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
