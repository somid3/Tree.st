package com.questy.domain;


import java.util.Date;

public class EmailConfirmation extends Parent {

    private Integer userId;
    private Date confirmedOn;
    private String checksum;
    private Integer messageCount;
    private Date lastSentOn;
    private String changeEmailTo;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getConfirmedOn() {
        return confirmedOn;
    }

    public void setConfirmedOn(Date confirmedOn) {
        this.confirmedOn = confirmedOn;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public Integer getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Integer messageCount) {
        this.messageCount = messageCount;
    }

    public Date getLastSentOn() {
        return lastSentOn;
    }

    public void setLastSentOn(Date lastSentOn) {
        this.lastSentOn = lastSentOn;
    }

    public String getChangeEmailTo() {
        return changeEmailTo;
    }

    public void setChangeEmailTo(String changeEmailTo) {
        this.changeEmailTo = changeEmailTo;
    }

    public Boolean isConfirmed () {
        if (confirmedOn == null)
            return false;
        else
            return true;
    }
}
