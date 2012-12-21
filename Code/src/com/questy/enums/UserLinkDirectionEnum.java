package com.questy.enums;

public enum UserLinkDirectionEnum {
    ME_TO_TARGET (1, "ME_TO_TARGET"),
    TARGET_TO_ME (2, "TARGET_TO_ME"),
    BOTH (3, "BOTH");

    private Integer id;
    private String name;

    private UserLinkDirectionEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static UserLinkDirectionEnum getById(Integer id) {

        for (UserLinkDirectionEnum ve : values()) {
            if (ve.getId().equals(id))
                return ve;
        }

        return null;
    }

}
