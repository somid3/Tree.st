package com.questy.enums;

public enum TooltipEnum {

    END ("T4YUd", null),

    UPLOAD_PHOTO("Ph07o2", END),

    CONCLUSION("C0n3u5", UPLOAD_PHOTO),

    EVERYTHING("sTnNWQ", CONCLUSION),

    MODULES("Kl7YkQ", EVERYTHING),

    COMMUNITIES("whF2kR", MODULES),

    WELCOME("LHlR5X", COMMUNITIES),

    START ("sT8rT4", WELCOME);

    private String value;
    private TooltipEnum next;

    private TooltipEnum(String value, TooltipEnum next) {
        this.value = value;
        this.next = next;
    }

    public String getValue() {
        return value;
    }

    public TooltipEnum getNext() {
        return next;
    }

    public static TooltipEnum getByValue (String value) {

        if (value == null)
            return null;

        for (TooltipEnum ve : values()) {
            if (ve.getValue().equals(value))
                return ve;
        }

        return null;
    }

    public String getTooltipHtmlId() {
        return ("tt-" + value);
    }

    public String getContainerHtmlId() {
        return ("ttc-" + value);
    }
}


