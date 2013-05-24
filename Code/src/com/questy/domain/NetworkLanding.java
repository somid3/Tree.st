package com.questy.domain;

import com.questy.enums.RoleEnum;

import java.util.Date;

public class NetworkLanding extends Parent {

    private Integer networkId;
    private Integer ref;
    private String html;

    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

    public Integer getRef() {
        return ref;
    }

    public void setRef(Integer ref) {
        this.ref = ref;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
