package com.questy.domain;

import com.questy.enums.UserIntegerSettingEnum;

public class UserToNetworkIntegerSetting extends Parent {

    private Integer userId;
    private UserIntegerSettingEnum settingEnum;
    private Integer value;

    public UserIntegerSettingEnum getSettingEnum() {
        return settingEnum;
    }

    public void setSettingEnum(UserIntegerSettingEnum settingEnum) {
        this.settingEnum = settingEnum;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
