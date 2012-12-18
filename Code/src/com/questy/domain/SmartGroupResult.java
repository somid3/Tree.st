package com.questy.domain;

import com.questy.enums.AnswerVisibilityEnum;

import java.util.Date;

public class SmartGroupResult {
    
    private Integer networkId;
    private Integer ref;
    private Integer userId;
    private Double score;
    private AnswerVisibilityEnum lowestVisibility;
    private Date latestDate;
    private Integer totalOptionsMet;
    private Integer totalQuestionsMet;
    private Boolean queryMet;
    private String flare;

    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

    public Integer getRef() {
        return ref;
    }

    public void setRef(Integer ref) {
        this.ref = ref;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public AnswerVisibilityEnum getLowestVisibility() {
        return lowestVisibility;
    }

    public void setLowestVisibility(AnswerVisibilityEnum lowestVisibility) {
        this.lowestVisibility = lowestVisibility;
    }

    public Date getLatestDate() {
        return latestDate;
    }

    public void setLatestDate(Date latestDate) {
        this.latestDate = latestDate;
    }

    public Integer getTotalOptionsMet() {
        return totalOptionsMet;
    }

    public void setTotalOptionsMet(Integer totalOptionsMet) {
        this.totalOptionsMet = totalOptionsMet;
    }

    public Integer getTotalQuestionsMet() {
        return totalQuestionsMet;
    }

    public void setTotalQuestionsMet(Integer totalQuestionsMet) {
        this.totalQuestionsMet = totalQuestionsMet;
    }

    public Boolean isQueryMet() {
        return queryMet;
    }

    public void setQueryMet(Boolean queryMet) {
        this.queryMet = queryMet;
    }

    public String getFlare() {
        return flare;
    }

    public void setFlare(String flare) {
        this.flare = flare;
    }
}
