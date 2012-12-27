package com.questy.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StringUtils {

    public static String concat (String input, Integer length, String ifLongPostfix) {

      if (input == null) return "";

      String out = null;
      if (input.length() > length)
          out = input.substring(0, length).trim() +  ifLongPostfix;
      else
          out = input;

      return out;
    }

    public static String quoteIt (String input) {

        if (input == null)
            return null;
        else
            return "\"" + input + "\"";

    }

    public static String convertStreamToString(InputStream is, String encoding) {
        try {
            return new java.util.Scanner(is, encoding).useDelimiter("\\A").next();
        } catch (java.util.NoSuchElementException e) {
            return "";
        }
    }

    public static String capitalize (String input) {

        return WordUtils.capitalize(input);

    }

    public static Boolean parseBoolean (String input) {

        if (input == null) return false;

        try {
            return Boolean.parseBoolean(input);
        } catch (Exception e) {
            return null;
        }

    }

    public static Integer parseInt (String input) {

        if (input == null) return null;

        try {
            if (input.contains(".")) {
                input = input.substring(0, input.indexOf("."));
            }

            return Integer.parseInt(input);
        } catch (Exception e) {
            return null;
        }

    }

    public static Double parseDouble (String input) {

        if (input == null) return null;

        try {
            return Double.parseDouble(input);
        } catch (Exception e) {
            return null;
        }

    }

    public static List<Integer> parseInts (String[] inputs) {

        if (inputs == null) return null;
        List<Integer> out = new ArrayList<Integer>();

        try {

            for (String input : inputs)
                out.add(parseInt(input));

        } catch (Exception e) {
            return null;
        }

        return out;
    }

    public static String parseString (String input) {
        return input;
    }

    public static String highlight(String input, String replace, String before, String after) {

        if (before == null) before = "";
        if (after == null) after = "";
        if (input == null || input.isEmpty()) return "";
        if (replace == null || replace.isEmpty()) return input;

        String escapedString = Pattern.quote(replace);

        return input.replaceAll("(?i)(" + escapedString + ")", before + "$1" + after);
    }

    public static String emptyIfNull(String input) {
        return defaultIfNull(input, "");
    }

    public static String defaultIfNull(String input, String def) {

        if (def == null)
            throw new RuntimeException("Default can not be null");

        if (input == null)
            return def;
        else
            return input;
    }

    public static String random() {
        return random(25);
    }

    public static String random(Integer length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static Boolean isEmail (String email) {

        EmailValidator ev = EmailValidator.getInstance();
        return ev.isValid(email);

    }

    /**
     * Provided a string, this method adds a prefix or postfix to the string. If the
     * string provided is null this method returns an empty string
     *
     * @param input string to receive prefix and/or postfix
     * @param prefix
     * @param postfix
     * @return
     */
    public static String surround (Object input, String prefix, String postfix) {

      if (input == null)
        return "";

      String out = input.toString();

      if (out.isEmpty())
        return out;

      if (prefix != null)
        out = prefix + out;

      if (postfix != null)
        out = out + postfix;

      return out;
    }

    public static Boolean isEmpty (String input) {

        return (input == null || input.isEmpty());

    }
}
