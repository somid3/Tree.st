package com.questy.enums;

@Deprecated
public enum GlobalEventEnum {

    PHOTO_UPLOAD_REMINDER (10, "photo upload reminders");

    private Integer value;
    private String name;

    private GlobalEventEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static GlobalEventEnum getByValue (Integer value) {

        if (value == null)
            return null;

        for (GlobalEventEnum ve : values()) {
            if (ve.getValue().equals(value))
                return ve;
        }

        return null;
    }
}


