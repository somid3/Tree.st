package com.questy.domain;

import java.util.Date;
import java.util.List;

public class Question extends Parent {
    private Integer ref;
    private Date createdOn;
    private Integer createdBy;
    private Integer totalAnswers;
    private Integer totalOptionAnswers;
    private Integer points;
    private Integer networkId;
    private String text;
    private Integer maxSelectedOptions;
    private List<QuestionOption> options;
    private Integer maxVisibility;
    private Integer defaultVisibility;
    private Boolean allowAddOptions;

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

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<QuestionOption> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionOption> options) {
        this.options = options;
    }

    public Integer getTotalAnswers() {
        return totalAnswers;
    }

    public void setTotalAnswers(Integer totalAnswers) {
        this.totalAnswers = totalAnswers;
    }

    public Integer getTotalOptionAnswers() {
        return totalOptionAnswers;
    }

    public void setTotalOptionAnswers(Integer totalOptionAnswers) {
        this.totalOptionAnswers = totalOptionAnswers;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

    public Integer getMaxSelectedOptions() {
        return maxSelectedOptions;
    }

    public void setMaxSelectedOptions(Integer maxSelectedOptions) {
        this.maxSelectedOptions = maxSelectedOptions;
    }

    public Integer getMaxVisibility() {
        return maxVisibility;
    }

    public void setMaxVisibility(Integer maxVisibility) {
        this.maxVisibility = maxVisibility;
    }

    public Integer getDefaultVisibility() {
        return defaultVisibility;
    }

    public void setDefaultVisibility(Integer defaultVisibility) {
        this.defaultVisibility = defaultVisibility;
    }

    public Boolean getAllowAddOptions() {
        return allowAddOptions;
    }

    public void setAllowAddOptions(Boolean allowAddOptions) {
        this.allowAddOptions = allowAddOptions;
    }

    public QuestionOption findOptionByText(String text) {

        if (text == null)
            return null;

        for (QuestionOption qo : options) {
            if (qo.getText().equals(text))
                return qo;
        }

        return null;
    }

    public QuestionOption findOptionByTextStartsWith(String text) {

        if (text == null)
            return null;

        for (QuestionOption qo : options) {
            if (qo.getText().startsWith(text))
                return qo;
        }

        return null;
    }

    public QuestionOption findOptionByRef(Integer ref) {

        if (ref == null)
            return null;

        for (QuestionOption qo : options) {
            if (ref.equals(qo.getRef()))
                return qo;
        }

        return null;
    }
}
