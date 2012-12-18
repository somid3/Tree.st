package com.questy.enums;

import java.util.Calendar;
import java.util.Date;

public enum EmailNotificationRateEnum {

    INSTANTLY (10, "Instantly"),

    EVERY_HOUR (60, "Every hour"),

    EVERY_FOUR_HOURS (240, "Every four hours"),

    EVERY_EIGHT_HOURS (480, "Every eight hours"),

    EVERY_TWELVE_HOURS (720, "Every twelve hours"),

    EVERY_DAY (1440, "Every day"),

    EVERY_OTHER_DAY (2880, "Every other day"),

    EVERY_THREE_DAYS (4320, "Every three days"),

    EVERY_WEEK (10080, "Every week"),

    EVERY_OTHER_WEEK (20160, "Every other week"),

    NEVER (99999, "Never");

    private Integer value;
    private String name;

    private EmailNotificationRateEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    /**
     * Returns the date after which this rate would encompass
     */
    public Date getBoundaryDate() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        switch (this) {
            case INSTANTLY: break;
            case EVERY_HOUR:           cal.add(Calendar.HOUR, -1); break;
            case EVERY_FOUR_HOURS:     cal.add(Calendar.HOUR, -4); break;
            case EVERY_EIGHT_HOURS:    cal.add(Calendar.HOUR, -8); break;
            case EVERY_TWELVE_HOURS:   cal.add(Calendar.HOUR, -12); break;
            case EVERY_DAY:            cal.add(Calendar.DATE, -1); break;
            case EVERY_OTHER_DAY:      cal.add(Calendar.DATE, -2); break;
            case EVERY_THREE_DAYS:     cal.add(Calendar.DATE, -3); break;
            case EVERY_WEEK:           cal.add(Calendar.DATE, -7); break;
            case EVERY_OTHER_WEEK:     cal.add(Calendar.DATE, -14); break;
            case NEVER:                cal.add(Calendar.YEAR, 100); break;
        }

        return cal.getTime();
    }

    public static EmailNotificationRateEnum getByValue (Integer value) {

        if (value == null)
            return null;

        for (EmailNotificationRateEnum ve : values()) {
            if (ve.getValue().equals(value))
                return ve;
        }

        return null;
    }

    public static EmailNotificationRateEnum next (EmailNotificationRateEnum input) {

        int nextOrdinal = input.ordinal() + 1;

        if (nextOrdinal >= EmailNotificationRateEnum.values().length)
            return null;
        else
            return EmailNotificationRateEnum.values()[nextOrdinal];

    }


}


