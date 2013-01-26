package com.questy.web;


import java.lang.Integer;
import java.lang.String;

public class HashRouting {

    public static final String HASH = "#";

    /**
     * Settings related
     */

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

    public static String smartGroupSharedItems(Integer networkId, Integer smartGroupRef) {
        return HASH + "/comm/" + networkId + "/sgroup/" + smartGroupRef + "/sitems";
    };

    public static String smartGroupMembers(Integer networkId, Integer smartGroupRef) {
        return HASH + "/comm/" + networkId + "/sgroup/" + smartGroupRef + "/members";
    };

    public static String sharedItems(Integer networkId) {
        return HASH + "/comm/" + networkId + "/sitems";
    };

    public static String sharedItem(Integer networkId, Integer smartGroupRef, Integer sharedItem) {
        return HASH + "/comm/" + networkId + "/sgroup/" + smartGroupRef + "/sitem/" + sharedItem;
    };

    public static String smartSearch(Integer networkId) {
        return HASH + "/comm/" + networkId + "/search";
    };

    public static String profile(Integer networkId) {
        return HASH + "/comm/" + networkId + "/profile";
    };

}
