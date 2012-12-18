package com.questy.domain;

import com.questy.enums.NetworkAlphaSettingEnum;

public class NetworkAlphaSetting extends Parent {

    private Integer networkId;
    private NetworkAlphaSettingEnum settingEnum;
    private String value;

    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

    public NetworkAlphaSettingEnum getSettingEnum() {
        return settingEnum;
    }

    public void setSettingEnum(NetworkAlphaSettingEnum settingEnum) {
        this.settingEnum = settingEnum;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
