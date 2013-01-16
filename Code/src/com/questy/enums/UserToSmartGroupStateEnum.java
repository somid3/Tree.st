package com.questy.enums;

public enum UserToSmartGroupStateEnum {

    NONE (0, "None"),

    FAVORITE (10, "Favorite"),

    FLAGGED (-10, "Flagged");


    private Integer id;
    private String name;

    private UserToSmartGroupStateEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static UserToSmartGroupStateEnum getById(Integer value) {

        if (value == null)
            return null;

        for (UserToSmartGroupStateEnum ve : values()) {
            if (ve.getId().equals(value))
                return ve;
        }

        return null;
    }

}


