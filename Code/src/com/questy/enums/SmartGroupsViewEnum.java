package com.questy.enums;

public enum SmartGroupsViewEnum {

    FAVORITES (15),
    FLAGS     (12),
    MATCHED   (9),

    OFFICIAL  (6),
    SHARED    (3),
    YOURS     (0);

    private Integer id;

    private SmartGroupsViewEnum(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public static SmartGroupsViewEnum getById(Integer id) {

        if (id == null)
            return YOURS;

        for (SmartGroupsViewEnum ve : values()) {
            if (ve.getId().equals(id))
                return ve;
        }

        return null;
    }
}
