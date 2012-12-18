package com.questy.enums;

import com.questy.domain.Network;
import com.questy.utils.StringUtils;

public enum AnswerVisibilityEnum {
    PUBLIC    (9, "Public",      "Available to the world",                   "Available to the world"),
    PROTECTED (6, "Protected",   "Community only",                           "Community only"),
    ANONYMOUS (3, "Anonymously", "Answer Anonymously",                       "Answer Anonymously"),
    PRIVATE   (0, "Private",     "Only for me",                              "Only for me");

    private int value;
    private String name;
    private String privateDescription;
    private String globalDescription;


    private AnswerVisibilityEnum(int value, String name, String privateDescription, String globalDescription) {
        this.value = value;
        this.name = name;
        this.privateDescription = privateDescription;
        this.globalDescription = globalDescription;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getDescription(Network network) {

        if (!network.isGlobal())
            return privateDescription;
        else
            return globalDescription;
    }

    public boolean isLowerThan(AnswerVisibilityEnum input) {

        if (input == null) throw new RuntimeException("Can not compare with null input");

        if (this.getValue() < input.getValue())
            return true;
        else
            return false;
    }

    public static AnswerVisibilityEnum getByValue (Integer value) {

        if (value == null)
            return PRIVATE;
                
        for (AnswerVisibilityEnum ve : values()) {
            if (ve.getValue() == value)
                return ve;
        }

        return null;
    }
}
