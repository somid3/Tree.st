package com.questy.domain;

import com.questy.enums.SmartGroupVisibilityEnum;
import com.questy.utils.ResourceInfo;

import java.util.Date;

public class SmartGroup extends Parent {

    /**
     * Name of the internal smart group each user has, here they
     * store the search criteria for the last search they submitted
     */
    public final static String SEARCH_NAME = "_search";

    /**
     * A ref that symbolizes the community which holds the smart group
     */
    public final static int ANY_SMART_GROUP_REF = 0;

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

}
