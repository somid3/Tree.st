package com.questy.domain;

import com.questy.utils.ResourceInfo;

import java.util.Date;

public class Network extends Parent {

    private Date createdOn;
    private String name;
    private boolean global;
    private Integer totalMembers;
    private String checksum;



    private Date iconOn;
    private Date backgroundOn;


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

    public boolean isGlobal() {
        return global;
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

    public Boolean hasIcon() {
        return iconOn != null;
    }

    public Boolean hasBackground() {
        return backgroundOn != null;
    }

    public Date getIconOn() {
        return iconOn;
    }

    public void setIconOn(Date iconOn) {
        this.iconOn = iconOn;
    }

    public Date getBackgroundOn() {
        return backgroundOn;
    }

    public void setBackgroundOn(Date backgroundOn) {
        this.backgroundOn = backgroundOn;
    }

    public String getIconResourceUrl() {
        return getResourceUrl("icon.png", getIconOn());
    }

    public String getBackgroundResourceUrl() {
        return getResourceUrl("background.jpg", getBackgroundOn());
    }

    public String getResourceUrl(String filename, Date definer) {

        String url = null;

        if (definer != null)
            url = ResourceInfo.getNetworkUrl(getId());
        else
            url = ResourceInfo.getNetworkUrl(null);

        return url + filename;
    }


}
