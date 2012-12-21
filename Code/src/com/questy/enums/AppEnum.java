package com.questy.enums;

public enum AppEnum {

    FACES (1, "faces");

    private Integer id;
    private String name;

    private AppEnum(Integer id, String name) {
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

    public static AppEnum getById(Integer id) {

        if (id == null)
            return null;

        for (AppEnum ve : values()) {
            if (ve.getId().equals(id))
                return ve;
        }

        return null;
    }
}


