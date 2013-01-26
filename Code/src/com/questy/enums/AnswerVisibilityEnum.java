package com.questy.enums;

import com.questy.domain.Network;

public enum AnswerVisibilityEnum {
    PUBLIC    (9, "Public",      "Available to the world"),
    PROTECTED (6, "Protected",   "Community only"),
    PRIVATE   (0, "Private",     "Only for me");

    private int id;
    private String name;
    private String description;

    private AnswerVisibilityEnum(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isLowerThan(AnswerVisibilityEnum input) {

        if (input == null) throw new RuntimeException("Can not compare with null input");

        if (this.getId() < input.getId())
            return true;
        else
            return false;
    }

    public static AnswerVisibilityEnum getById(Integer id) {

        if (id == null)
            return PRIVATE;
                
        for (AnswerVisibilityEnum ve : values()) {
            if (ve.getId() == id)
                return ve;
        }

        return null;
    }
}
