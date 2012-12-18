package com.questy.domain;

import com.questy.enums.AppEnum;
import com.questy.enums.AppResourceTypeEnum;
import com.questy.utils.ResourceInfo;
import com.questy.utils.StringUtils;

import java.util.Date;

public class AppResource extends Parent {

    private Integer userId;
    private AppEnum app;
    private Integer ref;
    private AppResourceTypeEnum type;
    private String checksum;
    private Date createdOn;
    private Boolean temporary;
    private Boolean hidden;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRef() {
        return ref;
    }

    public void setRef(Integer ref) {
        this.ref = ref;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public AppEnum getApp() {
        return app;
    }

    public void setApp(AppEnum application) {
        this.app = application;
    }

    public AppResourceTypeEnum getType() {
        return type;
    }

    public void setType(AppResourceTypeEnum type) {
        this.type = type;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Boolean getTemporary() {
        return temporary;
    }

    public void setTemporary(Boolean temporary) {
        this.temporary = temporary;
    }

    public Boolean isHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }











    
    public String getUrl() {
        return getFaceUrl(userId, app, ref, type, checksum, null);
    }

    public String getFilePath() {
        return getResourceFilePath(userId, app, ref, type, checksum);
    }

    public static String getFaceUrl(Integer userId, AppEnum app, Integer ref, AppResourceTypeEnum type, String checksum, String extension) {

        String meta = StringUtils.defaultIfNull(type.getName(), AppResourceTypeEnum.FACE.getName());
        extension = StringUtils.defaultIfNull(extension, "jpg");

        return ResourceInfo.getUserUrl(userId) + app.getName() + "/" + ref + "_" + meta + "_" + checksum + "." + extension;
    }

    public static String getResourceFilePath(Integer userId, AppEnum app, Integer ref, AppResourceTypeEnum type, String checksum) {

        String meta = StringUtils.defaultIfNull(type.getName(), AppResourceTypeEnum.FACE.getName());

        String extension = StringUtils.defaultIfNull(type.getExtension(), "jpg");

        return ResourceInfo.getUserFilePath(userId) + app.getName() + "/" + ref + "_" + meta + "_" + checksum + "." + extension;
    }
}
