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
    private Integer totalMembers;
    private String checksum;

    public Network() {}

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

    public String getIconResourceUrl(boolean isDefault) {

        if (isDefault)
            return getDefaultResourceUrl(ICON_FILENAME);
        else
            return getResourceUrl(ICON_FILENAME);
    }

    public String getBackgroundResourceUrl(boolean isDefault) {

        if (isDefault)
            return getDefaultResourceUrl(BACKGROUND_FILENAME);
        else
            return getResourceUrl(BACKGROUND_FILENAME);
    }

    public String getLogoResourceUrl() {
        return getResourceUrl(LOGO_FILENAME);
    }

    public String getResourceUrl(String filename) {
        String url = ResourceInfo.getNetworkUrl(getId());
        return url + filename;
    }

    public String getDefaultResourceUrl(String filename) {
        String url = ResourceInfo.getNetworkUrl(ANY_NETWORK);
        return url + filename;
    }

}
