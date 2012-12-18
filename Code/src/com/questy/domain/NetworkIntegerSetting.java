package com.questy.domain;

import com.questy.enums.NetworkIntegerSettingEnum;

public class NetworkIntegerSetting extends Parent {

    private Integer networkId;
    private NetworkIntegerSettingEnum settingEnum;
    private Integer value;

    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

    public NetworkIntegerSettingEnum getSettingEnum() {
        return settingEnum;
    }

    public void setSettingEnum(NetworkIntegerSettingEnum settingEnum) {
        this.settingEnum = settingEnum;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
