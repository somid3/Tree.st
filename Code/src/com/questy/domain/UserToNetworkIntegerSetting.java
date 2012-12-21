package com.questy.domain;

import com.questy.enums.UserToNetworkIntegerSettingEnum;

public class UserToNetworkIntegerSetting extends Parent {

    private Integer userId;
    private Integer networkId;
    private UserToNetworkIntegerSettingEnum settingEnum;
    private Integer value;

    public UserToNetworkIntegerSettingEnum getSettingEnum() {
        return settingEnum;
    }

    public void setSettingEnum(UserToNetworkIntegerSettingEnum settingEnum) {
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

    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }
}
