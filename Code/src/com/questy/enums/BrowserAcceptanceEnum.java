package com.questy.enums;

import cz.mallat.uasparser.CachingOnlineUpdateUASparser;
import cz.mallat.uasparser.UASparser;
import cz.mallat.uasparser.UserAgentInfo;

import java.io.*;

public class BrowserAcceptanceEnum {

    private static UASparser uaParser;

    static {

        // Ensuring user agent parser is a singleton
        if (uaParser == null) {
            try {
                InputStream is = BrowserAcceptanceEnum.class.getResourceAsStream("uas_20120827-01.ini");
                uaParser = new UASparser(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public enum Status {
        FULL,

        LIMITED,

        /**
         * Displays a message on the top of the page that
         * the browser being used is buggy when used on
         * the system
         */
        BUGGY,

        UNKNOWN,

        /**
         * User is redirected to a browser denied page,
         * such browsers are not allowed on the system
         */
        DENIED,
    };

    public enum Browser {

        // Desktop agents
        CHROME_21           (Status.FULL,           "Chrome 21.",             null),
        CHROME_20           (Status.FULL,           "Chrome 20.",             null),
        CHROME_19           (Status.FULL,           "Chrome 19.",             null),
        CHROME_18           (Status.FULL,           "Chrome 18.",             null),
        CHROME_17           (Status.FULL,           "Chrome 17.",             null),
        CHROME_16           (Status.FULL,           "Chrome 16.",             null),
        CHROME_15           (Status.FULL,           "Chrome 15.",             null),
        CHROME_14           (Status.FULL,           "Chrome 14.",             null),
        CHROME_13           (Status.UNKNOWN,        "Chrome 13.",             null),
        CHROME_12           (Status.UNKNOWN,        "Chrome 12.",             null),
        CHROME_11           (Status.UNKNOWN,        "Chrome 11.",             null),
        CHROME_10           (Status.UNKNOWN,        "Chrome 10.",             null),
        CHROME_9            (Status.UNKNOWN,        "Chrome 9.",              null),
        CHROME              (Status.FULL,           "Chrome",                 null),

        FIREFOX_14          (Status.FULL,           "Firefox 14.",            null),
        FIREFOX_13          (Status.FULL,           "Firefox 13.",            null),
        FIREFOX_12          (Status.FULL,           "Firefox 12.",            null),
        FIREFOX_11          (Status.FULL,           "Firefox 11.",            null),
        FIREFOX_10          (Status.FULL,           "Firefox 10.",            null),
        FIREFOX_9           (Status.UNKNOWN,        "Firefox 9.",             null),
        FIREFOX_8           (Status.UNKNOWN,        "Firefox 8.",             null),
        FIREFOX_7           (Status.UNKNOWN,        "Firefox 7.",             null),
        FIREFOX_6           (Status.DENIED,         "Firefox 6.",             null),
        FIREFOX_5           (Status.DENIED,         "Firefox 5.",             null),
        FIREFOX_4           (Status.DENIED,         "Firefox 4.",             null),
        FIREFOX_3           (Status.DENIED,         "Firefox 3.",             null),
        FIREFOX             (Status.FULL,           "Firefox",                null),

        SAFARI_6            (Status.FULL,           "Safari 6.",              null),
        SAFARI_5            (Status.FULL,           "Safari 5.",              null),
        SAFARI_4            (Status.UNKNOWN,        "Safari 4.",              null),
        SAFARI_3            (Status.UNKNOWN,        "Safari 3.",              null),
        SAFARI_2            (Status.DENIED,         "Safari 2.",              null),
        SAFARI_1            (Status.DENIED,         "Safari 1.",              null),
        SAFARI              (Status.FULL,           "Safari",                 null),

        EXPLORER_10         (Status.FULL,           "IE 10.",                 null),
        EXPLORER_9          (Status.BUGGY,          "IE 9.",                  new String[] {"No photo upload", "No custom fonts"}),
        EXPLORER_8          (Status.BUGGY,          "IE 8.",                  new String[] {"No rounded corners", "No photo upload", "No custom fonts"}),
        EXPLORER_7          (Status.DENIED,         "IE 7.",                  null),
        EXPLORER_6          (Status.DENIED,         "IE 6.",                  null),
        EXPLORER            (Status.BUGGY,          "IE",                     null),

        // Mobile agents
        MOBILE_SAFARI_5     (Status.LIMITED,        "Mobile Safari 5.",       new String[] {"No photo upload"}),
        MOBILE_SAFARI       (Status.LIMITED,        "Mobile Safari",          new String[] {"No photo upload"}),

        ANDROID_WEBKIT      (Status.LIMITED,        "Android Webkit",         new String[] {"No photo upload"}),

        CHROME_MOBILE_18    (Status.LIMITED,        "Chrome Mobile 18.",      new String[] {"No photo upload"}),
        CHROME_MOBILE       (Status.LIMITED,        "Chrome Mobile",          new String[] {"No photo upload"}),

        ;

        private Status status;
        private String type;
        private String[] limitedBy;

        private Browser(Status status, String type, String[] limitedBy) {
            this.status = status;
            this.type = type;
            this.limitedBy = limitedBy;
        }



        public static Status getStatus (String userAgent) {

            try {

                // Attempting to parse user agent
                UserAgentInfo uai = uaParser.parse(userAgent);

                // Searching for the user's agent in the acceptance list
                for (Browser browser: Browser.values()) {

                    if (uai.getUaName().startsWith(browser.type))
                        return browser.status;

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return Status.UNKNOWN;

        }


    }


}


