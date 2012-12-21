package com.questy.enums;

public enum RoleEnum {

    ADMIN (9, "Admin"),

    EDITOR (6, "Editor"),

    MEMBER (3, "Member"),

    VISITOR (0, "Visitor");

    private Integer id;
    private String name;

    private RoleEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isLowerThan(RoleEnum input) {

        if (input == null) throw new RuntimeException("Can not compare with null input");

        if (this.getId() < input.getId())
            return true;
        else
            return false;
    }

    public static RoleEnum getByValue (Integer id) {

        if (id == null)
            return VISITOR;

        for (RoleEnum ve : values()) {
            if (ve.getId().equals(id))
                return ve;
        }

        return null;
    }
}
