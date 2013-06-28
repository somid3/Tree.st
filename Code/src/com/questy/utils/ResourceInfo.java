package com.questy.utils;

public class ResourceInfo {


    public static String getUserUrl(Integer userId) {
        userId = IntegerUtils.zeroIfNull(userId);
        return Vars.resourcesUrl + "users/" + userId + "/" ;
    }

    public static String getUserFilePath(Integer userId) {
        return Vars.resourcesFilePath + "users/" + userId + "/" ;
    }

    public static String getNetworkUrl(Integer networkId) {
        networkId = IntegerUtils.zeroIfNull(networkId);
        return Vars.resourcesUrl + "networks/" + networkId + "/" ;
    }

}
