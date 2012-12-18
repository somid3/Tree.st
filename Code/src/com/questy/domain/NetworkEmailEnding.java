package com.questy.domain;

import java.util.Date;

public class NetworkEmailEnding extends Parent {

    private Date createdOn;
    private Integer ref;
    private Integer networkId;
    private String emailEndsWith;

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getRef() {
        return ref;
    }

    public void setRef(Integer ref) {
        this.ref = ref;
    }

    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

    public String getEmailEndsWith() {
        return emailEndsWith;
    }

    public void setEmailEndsWith(String emailEndsWith) {
        this.emailEndsWith = emailEndsWith;
    }

    public String toString() {

        return "'" + emailEndsWith + "'";

    }
}
