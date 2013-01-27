package com.questy.web;


import java.lang.Integer;
import java.lang.String;

public class HashRouting {

    public static final String HASH = "#";

    /**
     * Settings related
     */

    public static String settings() {
        return HASH + "/settings";
    };

    public static String settingsPhotoUpload() {
        return HASH + "/settings/photos/upload";
    };

    public static String settingsPhotoSetFace() {
        return HASH + "/settings/photos/set";
    };




    /**
     * Network related
     */

    public static String network(Integer networkId) {
        return HASH + "/comm/" + networkId;
    };

    public static String all(Integer networkId) {
        return HASH + "/comm/" + networkId + "/all";
    };

    public static String questions(Integer networkId) {
        return HASH + "/comm/" + networkId + "/collaborate";
    };

    public static String smartGroups(Integer networkId) {
        return HASH + "/comm/" + networkId + "/sgroups";
    };

    public static String smartGroup(Integer networkId, Integer smartGroupRef) {
        return HASH + "/comm/" + networkId + "/sgroup/" + smartGroupRef;
    };

    public static String smartGroupShare(Integer networkId, Integer smartGroupRef) {
        return HASH + "/comm/" + networkId + "/sgroup/" + smartGroupRef + "/share";
    };

    public static String smartGroupMembers(Integer networkId, Integer smartGroupRef) {
        return HASH + "/comm/" + networkId + "/sgroup/" + smartGroupRef + "/members";
    };

    public static String sharedItems(Integer networkId) {
        return HASH + "/comm/" + networkId + "/share";
    };

    public static String sharedItem(Integer networkId, Integer smartGroupRef, Integer sharedItem) {
        return HASH + "/comm/" + networkId + "/sgroup/" + smartGroupRef + "/share/" + sharedItem;
    };

    public static String smartSearch(Integer networkId) {
        return HASH + "/comm/" + networkId + "/search";
    };

    public static String profile(Integer networkId) {
        return HASH + "/comm/" + networkId + "/profile";
    };

    public static String member(Integer networkId, Integer toUserId, Integer myUserId) {
        if (toUserId != myUserId)
            return HASH + "/comm/" + networkId + "/member/" + toUserId;
        else
            return profile(networkId);

    };

}

