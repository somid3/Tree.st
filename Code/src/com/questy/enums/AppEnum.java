package com.questy.enums;

public enum AppEnum {

    FACES (1, "faces");

    private Integer value;
    private String name;

    private AppEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public boolean isLowerThan(RoleEnum input) {

        if (input == null) throw new RuntimeException("Can not compare with null input");

        if (this.getValue() < input.getValue())
            return true;
        else
            return false;
    }

    public static AppEnum getByValue (Integer value) {

        if (value == null)
            return null;

        for (AppEnum ve : values()) {
            if (ve.getValue().equals(value))
                return ve;
        }

        return null;
    }
}


