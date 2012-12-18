package com.questy.enums;

public enum NetworkEventEnum {


    AUTHOR_OF_SHARED_ITEM_NEW_COMMENT(
        10,
        "new comments on your shared item",
        new EmailNotificationRateEnum[] {
            EmailNotificationRateEnum.INSTANTLY,
            EmailNotificationRateEnum.NEVER
        },
        EmailNotificationRateEnum.INSTANTLY,
        true
    ),

    AUTHOR_OF_COMMENT_FOLLOW_UP_COMMENT(
        11,
        "comment after your comment",
        new EmailNotificationRateEnum[] {
            EmailNotificationRateEnum.INSTANTLY,
            EmailNotificationRateEnum.NEVER
        },
        EmailNotificationRateEnum.INSTANTLY,
        true
    ),

    NEW_SHARED_ITEM (
        90,
        "new shared message",
        EmailNotificationRateEnum.values(),
        EmailNotificationRateEnum.EVERY_DAY,
        true
    ),

    USER_LINK_CREATION(
        91,
        "connection",
        new EmailNotificationRateEnum[] {
            EmailNotificationRateEnum.INSTANTLY,
            EmailNotificationRateEnum.NEVER
        },
        EmailNotificationRateEnum.INSTANTLY,
        true
    ),

    NEW_SMART_GROUP_MAPPINGS (
        92,
        "new smart group mappings",
        new EmailNotificationRateEnum[] {
            EmailNotificationRateEnum.EVERY_HOUR,
            EmailNotificationRateEnum.EVERY_FOUR_HOURS,
            EmailNotificationRateEnum.EVERY_EIGHT_HOURS,
            EmailNotificationRateEnum.EVERY_TWELVE_HOURS,
            EmailNotificationRateEnum.EVERY_DAY,
            EmailNotificationRateEnum.EVERY_OTHER_DAY,
            EmailNotificationRateEnum.EVERY_THREE_DAYS,
            EmailNotificationRateEnum.EVERY_WEEK,
            EmailNotificationRateEnum.EVERY_OTHER_WEEK,
            EmailNotificationRateEnum.NEVER,
        },
        EmailNotificationRateEnum.EVERY_HOUR,
        true
    ),









    SMART_GROUP_NEW_SHARED_ITEM(
        990,
        "new shared message",
        EmailNotificationRateEnum.values(),
        EmailNotificationRateEnum.INSTANTLY,
        false
    );






    private Integer value;
    private String name;
    private EmailNotificationRateEnum[] acceptedRates;
    private EmailNotificationRateEnum defaultRate;
    private boolean forNetworkOnly = false;

    private NetworkEventEnum(
        Integer value,
        String name,
        EmailNotificationRateEnum[] acceptedRates,
        EmailNotificationRateEnum defaultRate,
        boolean forNetworkOnly) {

        this.value = value;
        this.name = name;
        this.acceptedRates = acceptedRates;
        this.defaultRate = defaultRate;
        this.forNetworkOnly = forNetworkOnly;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public EmailNotificationRateEnum getDefaultRate() {
        return defaultRate;
    }

    public static NetworkEventEnum getByValue (Integer value) {

        if (value == null)
            return null;

        for (NetworkEventEnum ve : values()) {
            if (ve.getValue().equals(value))
                return ve;
        }

        return null;
    }

    public EmailNotificationRateEnum[] getAcceptedRates() {
        return acceptedRates;
    }

    public boolean isForNetworkOnly() {
        return forNetworkOnly;
    }
}


