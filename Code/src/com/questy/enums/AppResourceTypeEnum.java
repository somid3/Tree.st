package com.questy.enums;

public enum AppResourceTypeEnum {
    
    FACE (100, "face", "jpg"),
    FACE_ORIGINAL_SCALED (6, "scaled", "jpg"),
    FACE_ORIGINAL (3, "orig", "jpg"),
    FACE_UPLOADED(0, "tmp", "unkn");

    private Integer id;
    private String name;
    private String extension;

    private AppResourceTypeEnum(Integer id, String name, String extension) {
        this.id = id;
        this.name = name;
        this.extension = extension;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public boolean isLowerThan(RoleEnum input) {

        if (input == null) throw new RuntimeException("Can not compare with null input");

        if (this.getId() < input.getId())
            return true;
        else
            return false;
    }

    public static AppResourceTypeEnum getById(Integer id) {

        if (id == null)
            return null;

        for (AppResourceTypeEnum ve : values()) {
            if (ve.getId().equals(id))
                return ve;
        }

        return null;
    }
}


