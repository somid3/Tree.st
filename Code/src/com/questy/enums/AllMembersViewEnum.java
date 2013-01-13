package com.questy.enums;

public enum AllMembersViewEnum {

    BY_POINTS (1),
    BY_JOINED (2);

    private Integer id;

    private AllMembersViewEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static AllMembersViewEnum getById(Integer id) {

        if (id == null)
            return BY_POINTS;

        for (AllMembersViewEnum ve : values()) {
            if (ve.getId().equals(id))
                return ve;
        }

        return null;
    }
}
