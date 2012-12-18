package com.questy.enums;

public enum EmailMimeEnum {

    TEXT_UTF8("text/plain; charset=UTF-8"),
    HTML_UTF8("text/html; charset=UTF-8");

    private String name;

    private EmailMimeEnum(String name) {
        this.name = name;
    }

    public String getMime() {
        return name;
    }


}


