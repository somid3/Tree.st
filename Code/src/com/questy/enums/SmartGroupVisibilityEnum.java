package com.questy.enums;

public enum SmartGroupVisibilityEnum {




    OFFICIAL(6, "Official"),
    SHARED (3, "Shared"),
    PRIVATE (0, "Private");

    private Integer id;
    private String name;

    private SmartGroupVisibilityEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static SmartGroupVisibilityEnum getById(Integer id) {

        if (id == null)
            return PRIVATE;

        for (SmartGroupVisibilityEnum ve : values()) {
            if (ve.getId().equals(id))
                return ve;
        }

        return null;
    }
}
