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

    public static String settingsNetworks() {
        return HASH + "/settings/comms";
    };

    public static String settingsGeneral() {
        return HASH + "/settings/general";
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

    public static String finder(Integer networkId, String findText) {
        return HASH + "/comm/" + networkId + "/finder/" + findText;
    };

    public static String finder(Integer networkId) {
        return HASH + "/comm/" + networkId + "/finder";
    };

    public static String questions(Integer networkId) {
        return HASH + "/comm/" + networkId + "/details";
    };

    public static String questionAgain(Integer networkId, Integer questionRef) {
        return HASH + "/comm/" + networkId + "/details/" + questionRef;
    };

    public static String messages(Integer networkId) {
        return HASH + "/comm/" + networkId + "/messages";
    };

    public static String messages(Integer networkId, Integer toUserId) {
        return HASH + "/comm/" + networkId + "/messages/" + toUserId;
    };

    public static String blocked(Integer networkId) {
        return HASH + "/comm/" + networkId + "/blocked";
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

    public static String smartSearchAdd(Integer networkId, Integer searchNetworkId, Integer searchQuestionRef) {
        return HASH + "/comm/" + networkId + "/search/" + searchNetworkId + "/" + searchQuestionRef;
    };

    public static String smartSearchAdd(Integer networkId, Integer searchNetworkId, Integer searchQuestionRef, Integer searchOptionRef) {
        return HASH + "/comm/" + networkId + "/search/" + searchNetworkId + "/" + searchQuestionRef + "/" + searchOptionRef;
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

