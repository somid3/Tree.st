package com.questy.enums;

public enum SmartGroupVisibilityEnum {




    OFFICIAL(6, "Official"),
    SHARED (3, "Shared"),
    PRIVATE (0, "Private");

    private Integer value;
    private String name;

    private SmartGroupVisibilityEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static SmartGroupVisibilityEnum getByValue (Integer value) {

        if (value == null)
            return PRIVATE;

        for (SmartGroupVisibilityEnum ve : values()) {
            if (ve.getValue().equals(value))
                return ve;
        }

        return null;
    }
}
