package com.questy.helpers;

import com.questy.enums.AnswerVisibilityEnum;

import java.util.Date;

public class UserScore {

    private int userId;
    private double score;

    /**
     * Start lowest visibility at a very large number since
     * it will slowly move to the lowest common denominator
     */
    private AnswerVisibilityEnum lowestVisibility = AnswerVisibilityEnum.PUBLIC;

    private Date latestDate = new Date(0);
    private int totalOptionsMet;
    private int totalQuestionsMet;
    private boolean queryMet;
    private StringBuilder flare = new StringBuilder();

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getScore() {
        return score;
    }

    public void incrementScore(double increment) {
        score = score + increment;
    }

    public AnswerVisibilityEnum getLowestVisibility() {
        return lowestVisibility;
    }

    public void analyzeVisibility(AnswerVisibilityEnum visibility) {

        if (lowestVisibility.getId() > visibility.getId())
            lowestVisibility = visibility;

    }

    public Date getLatestDate() {
        return latestDate;
    }

    public void analyzeLatestDate(Date date) {

        if (latestDate.before(date))
            latestDate = date;

    }

    public void appendFlare(String flare) {
        this.flare.append(flare).append(' ');
    }

    public int getTotalOptionsMet() {
        return totalOptionsMet;
    }

    public int incrementOptionsMet() {
        return totalOptionsMet++;
    }

    public void resetOptionsMet() {
        totalOptionsMet = 0;
    }


    public int getTotalQuestionsMet() {
        return totalQuestionsMet;
    }

    public int incrementQuestionsMet() {
        return totalQuestionsMet++;
    }

    public boolean isQueryMet() {
        return queryMet;
    }

    public void setQueryMet(boolean queryMet) {
        this.queryMet = queryMet;
    }

    public String getFlare() {
        return flare.toString().trim();
    }

    public String toString() {

        StringBuilder buf = new StringBuilder();
        buf.append("used id: " + userId + " ");
        buf.append("score: " + score + " ");
        buf.append("total options met: " + totalOptionsMet + " ");
        buf.append("total questions met: " + totalQuestionsMet + " ");
        buf.append("query met?: " + queryMet + " ");
        buf.append("visibility: " + lowestVisibility + " ");
        buf.append("latestDate: " + latestDate + " ");
        buf.append("flare string: " + flare + " ");

        return buf.toString();
    }
}
