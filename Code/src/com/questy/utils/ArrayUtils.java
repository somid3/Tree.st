package com.questy.utils;

import java.util.List;

public class ArrayUtils {

    public static <E> List<E> subList (List<E> list, int fromIndex, int toIndex) {

        int size = list.size();

        int realFrom = fromIndex > size ? size : fromIndex;
        int realTo = toIndex > size ? size : toIndex;

        return list.subList(realFrom, realTo);
    }
}
