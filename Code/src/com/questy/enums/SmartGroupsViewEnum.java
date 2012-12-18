package com.questy.enums;

public enum SmartGroupsViewEnum {

    FAVORITES (15),
    FLAGS     (12),
    MATCHED   (9),

    OFFICIAL  (6),
    SHARED    (3),
    YOURS     (0);

    private Integer value;
    private String name;

    private SmartGroupsViewEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static SmartGroupsViewEnum getByValue (Integer value) {

        if (value == null)
            return YOURS;

        for (SmartGroupsViewEnum ve : values()) {
            if (ve.getValue().equals(value))
                return ve;
        }

        return null;
    }
}
