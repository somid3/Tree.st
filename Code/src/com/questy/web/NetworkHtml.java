package com.questy.web;

public class NetworkHtml {

    public static String getBulletClass(Integer networkId) {
        return "bullet" + networkId;
    };

    public static String getNetworkId(Integer networkId) {
        return "network" + networkId;
    };

}
