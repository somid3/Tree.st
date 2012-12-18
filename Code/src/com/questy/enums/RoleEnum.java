package com.questy.enums;

public enum RoleEnum {

    ADMIN (9, "Admin"),

    EDITOR (6, "Editor"),

    MEMBER (3, "Member"),

    VISITOR (0, "Visitor");

    private Integer value;
    private String name;

    private RoleEnum(Integer value, String name) {
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

    public static RoleEnum getByValue (Integer value) {

        if (value == null)
            return VISITOR;

        for (RoleEnum ve : values()) {
            if (ve.getValue().equals(value))
                return ve;
        }

        return null;
    }
}
