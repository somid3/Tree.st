package com.questy.domain;

import com.questy.utils.ResourceInfo;

import java.util.Date;

public class Network extends Parent {

    private static final Integer ANY_NETWORK = 0;
    private static final String ICON_FILENAME = "icon.png";
    private static final String BACKGROUND_FILENAME = "background.jpg";
    private static final String LOGO_FILENAME = "logo.png";

    private Date createdOn;
    private String name;
    private boolean global;
    private Integer totalMembers;
    private String checksum;

    public Network() {}

    public Network(Integer id, String name, boolean isGlobal) {
        setId(id);
        this.name = name;
        this.global = isGlobal;
    }

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

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public Integer getTotalMembers() {
        return totalMembers;
    }

    public void setTotalMembers(Integer totalMembers) {
        this.totalMembers = totalMembers;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public String getIconResourceUrl() {
        return getResourceUrl(ICON_FILENAME);
    }

    public String getBackgroundResourceUrl() {
        return getResourceUrl(BACKGROUND_FILENAME);
    }

    public String getLogoResourceUrl() {
        return getResourceUrl(LOGO_FILENAME);
    }

    public String getResourceUrl(String filename) {

        String url = ResourceInfo.getNetworkUrl(getId());

        return url + filename;
    }


}
