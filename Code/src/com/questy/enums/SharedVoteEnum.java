package com.questy.enums;

public enum SharedVoteEnum {

    NONE (0, "Up"),

    UP (1, "Up"),

    DOWN (2, "Down");

    private Integer id;
    private String name;

    private SharedVoteEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static SharedVoteEnum getById(Integer id) {

        if (id == null)
            return NONE;

        for (SharedVoteEnum ve : values()) {
            if (ve.getId().equals(id))
                return ve;
        }

        return null;
    }
}
