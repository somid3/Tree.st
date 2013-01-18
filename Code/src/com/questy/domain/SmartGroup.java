package com.questy.domain;

import com.questy.enums.SmartGroupVisibilityEnum;
import com.questy.utils.ResourceInfo;

import java.util.Date;

public class SmartGroup extends Parent {

    private Date createdOn;
    private Integer userId;
    private Integer networkId;
    private Integer smartGroupRef;
    private String name;
    private String description;
    private String query;
    private Integer queryUpdates;
    private Date queryUpdatedOn;
    private Date refreshedOn;
    private SmartGroupVisibilityEnum visibility;
    private Date iconOn;
    private Integer resultsCount;
    private Boolean hidden;

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getSmartGroupRef() {
        return smartGroupRef;
    }

    public void setSmartGroupRef(Integer smartGroupRef) {
        this.smartGroupRef = smartGroupRef;
    }

    public Date getRefreshedOn() {
        return refreshedOn;
    }

    public void setRefreshedOn(Date refreshedOn) {
        this.refreshedOn = refreshedOn;
    }

    public Integer getQueryUpdates() {
        return queryUpdates;
    }

    public void setQueryUpdates(Integer queryUpdates) {
        this.queryUpdates = queryUpdates;
    }

    public Date getQueryUpdatedOn() {
        return queryUpdatedOn;
    }

    public void setQueryUpdatedOn(Date updatedOn) {
        this.queryUpdatedOn = updatedOn;
    }

    public SmartGroupVisibilityEnum getVisibility() {
        return visibility;
    }

    public void setVisibility(SmartGroupVisibilityEnum visibility) {
        this.visibility = visibility;
    }

    public Boolean hasIcon() {
        return iconOn != null;
    }

    public Date getIconOn() {
        return iconOn;
    }

    public void setIconOn(Date iconOn) {
        this.iconOn = iconOn;
    }

    public Integer getResultsCount() {
        return resultsCount;
    }

    public void setResultsCount(Integer resultsCount) {
        this.resultsCount = resultsCount;
    }

    public Boolean isHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public String getResourceUrlIcon() {
        return getResourceUrl("icon.png");
    }

    private String getResourceUrl(String filename) {

        String url = null;

        if (getIconOn() != null)
            url = ResourceInfo.getSmartGroupUrl(getNetworkId(), getId());
        else {
            url = ResourceInfo.getSmartGroupUrl(null, null);
        }

        return url + filename;
    }
}
