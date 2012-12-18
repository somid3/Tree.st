package com.questy.xml.query;


public class CriteriaXml {

    public enum Type {
        IS_SET,
        IS_NOT_SET,
        EQUALS,
        LESS_THAN,
        GREATER_THAN,
        BETWEEN,
        CONTAINS
    }

    private Type type;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
