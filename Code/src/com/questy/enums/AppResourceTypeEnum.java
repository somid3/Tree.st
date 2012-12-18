package com.questy.enums;

public enum AppResourceTypeEnum {
    
    FACE (100, "face", "jpg"),
    FACE_ORIGINAL_SCALED (6, "scaled", "jpg"),
    FACE_ORIGINAL (3, "orig", "jpg"),
    FACE_UPLOADED(0, "tmp", "unkn");

    private Integer value;
    private String name;
    private String extension;

    private AppResourceTypeEnum(Integer value, String name, String extension) {
        this.value = value;
        this.name = name;
        this.extension = extension;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public boolean isLowerThan(RoleEnum input) {

        if (input == null) throw new RuntimeException("Can not compare with null input");

        if (this.getValue() < input.getValue())
            return true;
        else
            return false;
    }

    public static AppResourceTypeEnum getByValue (Integer value) {

        if (value == null)
            return null;

        for (AppResourceTypeEnum ve : values()) {
            if (ve.getValue().equals(value))
                return ve;
        }

        return null;
    }
}


