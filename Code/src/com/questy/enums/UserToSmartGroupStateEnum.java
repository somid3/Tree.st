package com.questy.enums;

public enum UserToSmartGroupStateEnum {

    NONE (0, "None"),

    FAVORITE (10, "Favorite"),

    FLAGGED (-10, "Flagged");


    private Integer value;
    private String name;

    private UserToSmartGroupStateEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static UserToSmartGroupStateEnum getByValue (Integer value) {

        if (value == null)
            return null;

        for (UserToSmartGroupStateEnum ve : values()) {
            if (ve.getValue().equals(value))
                return ve;
        }

        return null;
    }

}


