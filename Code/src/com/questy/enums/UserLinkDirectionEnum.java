package com.questy.enums;

public enum UserLinkDirectionEnum {
    ME_TO_TARGET (1, "ME_TO_TARGET"),
    TARGET_TO_ME (2, "TARGET_TO_ME"),
    BOTH (3, "BOTH");

    private Integer value;
    private String name;

    private UserLinkDirectionEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static UserLinkDirectionEnum getByValue (Integer value) {

        for (UserLinkDirectionEnum ve : values()) {
            if (ve.getValue().equals(value))
                return ve;
        }

        return null;
    }

    public Boolean isFromMe() {
        return (UserLinkDirectionEnum.isFromMe(this));
    }

    public static Boolean isFromMe(UserLinkDirectionEnum direction) {

        return (direction != null &&
               (UserLinkDirectionEnum.ME_TO_TARGET.equals(direction) ||
                UserLinkDirectionEnum.BOTH.equals(direction)));

    }
}
