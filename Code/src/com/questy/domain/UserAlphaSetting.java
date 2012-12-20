package com.questy.domain;

import com.questy.enums.UserAlphaSettingEnum;

public class UserAlphaSetting extends Parent {

    private Integer userId;
    private UserAlphaSettingEnum settingEnum;
    private String value;

    public UserAlphaSettingEnum getSettingEnum() {
        return settingEnum;
    }

    public void setSettingEnum(UserAlphaSettingEnum settingEnum) {
        this.settingEnum = settingEnum;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
