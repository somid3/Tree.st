package com.questy.domain;

import java.util.Comparator;

public class SmartGroupComparatorSize implements Comparator<SmartGroup> {

    public int compare(SmartGroup sg1, SmartGroup sg2) {

        return sg1.getResultsCount().compareTo(sg2.getResultsCount());

    };
}
