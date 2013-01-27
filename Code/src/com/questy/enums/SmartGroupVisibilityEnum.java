package com.questy.enums;

public enum SmartGroupVisibilityEnum {




    OFFICIAL(6, "Official"),
    SHARED (3, "Shared"),

    /**
     * This visibility is to only be used for smart searches that
     * are saved as temporary smarty groups. No smart groups should
     * be saved with a private visibility
     */
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
