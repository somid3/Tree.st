package com.questy.utils;


public class IntegerUtils {
    public static int zeroIfNull(Integer id) {
    if (id == null)
        return 0;
    else
        return id;
    }
}
