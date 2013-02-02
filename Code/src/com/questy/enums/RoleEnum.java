package com.questy.enums;

public enum RoleEnum {

    /**
     * Can create more administrators
     */
    OWNER (9, "Owner"),

    /**
     * Can create more editors
     */
    ADMIN (7, "Admin"),

    /**
     * Can hide any shared item
     * Can hide any shared comment
     * Can block any member
     */
    EDITOR (6, "Editor"),

    /**
     * Can use all functionality
     */
    MEMBER (3, "Member"),

    /**
     * Can no... TBD
     */
    VISITOR (0, "Visitor");

    private Integer id;
    private String name;

    private RoleEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isLowerThan(RoleEnum input) {

        if (input == null) throw new RuntimeException("Can not compare with null input");
        return (this.getId() < input.getId());
    }

    public boolean isHigherThan(RoleEnum input) {

        if (input == null) throw new RuntimeException("Can not compare with null input");
        return (this.getId() > input.getId());
    }

    public static RoleEnum getById(Integer id) {

        if (id == null)
            return VISITOR;

        for (RoleEnum ve : values()) {
            if (ve.getId().equals(id))
                return ve;
        }

        return null;
    }
}
