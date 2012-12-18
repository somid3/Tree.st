package com.questy.utils;


import com.questy.helpers.Tuple;

import java.math.BigDecimal;

public class ImageUtils {

    /**
     * Returns the x-ratio and the y-ratio to transform
     * coordinates between the first set of width and height
     * measurements to the second set
     */
    public static Tuple<BigDecimal, BigDecimal> getTransformationRatio (
            BigDecimal width1,
            BigDecimal height1,
            BigDecimal width2,
            BigDecimal height2,
            int precision) {

        if (width1 == null) throw new RuntimeException ("Width1 can not be null");
        if (height1 == null) throw new RuntimeException ("Height1 can not be null");
        if (width2 == null) throw new RuntimeException ("Width2 can not be null");
        if (height2 == null) throw new RuntimeException ("Height2 can not be null");

        if (BigDecimal.ZERO.equals(width1)) throw new RuntimeException ("Width1 can not be zero");
        if (BigDecimal.ZERO.equals(width1)) throw new RuntimeException ("Height1 can not be zero");
        if (BigDecimal.ZERO.equals(width2)) throw new RuntimeException ("Width2 can not be zero");
        if (BigDecimal.ZERO.equals(height2)) throw new RuntimeException ("Height2 can not be zero");

        BigDecimal widthRatio = width1.divide(width2, precision, BigDecimal.ROUND_FLOOR);
        BigDecimal heightRatio = height1.divide(height2, precision, BigDecimal.ROUND_FLOOR);

        return new Tuple<BigDecimal, BigDecimal>(widthRatio, heightRatio);
    }

    public static Tuple<BigDecimal, BigDecimal> getTransformationRatio (
                int width1,
                int height1,
                int width2,
                int height2,
                int precision) {

        return getTransformationRatio (
                new BigDecimal(width1),
                new BigDecimal(height1),
                new BigDecimal(width2),
                new BigDecimal(height2),
                precision);
    }

}
