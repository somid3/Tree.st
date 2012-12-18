package com.questy.utils;

import java.text.DecimalFormat;

public class MathUtils {

    private static DecimalFormat df = new DecimalFormat("#");

    public static String percentage (Integer numerator, Integer denominator) {

        if (numerator == null || denominator == null) return "0";

        return percentage(numerator.floatValue(), denominator.floatValue());
    }


    public static String percentage (Float numerator, Float denominator) {

        if (numerator == null ||  denominator == null || denominator == 0f)
            return "0";
        else
            return df.format( (numerator / denominator) * 100 );
    }

    public static Integer barLength (Integer numerator, Integer denominator, Integer normalizeTo) {

        if (numerator == null || denominator == null || normalizeTo == null) return 0;
      
        return barLength(numerator.floatValue(), denominator.floatValue(), normalizeTo.floatValue());
    }

    public static Integer barLength (Float numerator, Float denominator, Float normalizeTo) {

        if (numerator == null) return 0;
        if (denominator == null || denominator == 0f) return 0;
        if (normalizeTo == null || normalizeTo == 0) return 0;

        return Math.round((numerator / denominator) * normalizeTo);
    }

}
