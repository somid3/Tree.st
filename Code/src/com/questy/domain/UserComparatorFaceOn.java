package com.questy.domain;

import java.util.Comparator;

public class UserComparatorFaceOn implements Comparator<User> {

    public int compare(User u1, User u2) {

        final int BEFORE = -1;
        final int EQUAL = 0;
        final int AFTER = 1;

        if (!u1.hasFace() && !u2.hasFace())
            return EQUAL;

        if (u1.hasFace() && !u2.hasFace())
            return BEFORE;

        if (!u1.hasFace() && u2.hasFace())
            return AFTER;

        return EQUAL;

    };
}
