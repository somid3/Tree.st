package com.questy.web;

import com.questy.utils.StringUtils;


public class HtmlUtils {


    public static String getRandomId() {
        return StringUtils.random();
    }

    public static String getRandomId(Integer length) {
        return StringUtils.random(length);
    }

    public static String linkify (String input) {

        input = StringUtils.emptyIfNull(input);

        return input.replaceAll("(?:https|http)://([^\\s\\|]+)", "<a href=\"$0\" class=\"highlight2\" target=\"_blank\">$1</a>");
    }

    public static String createHref(String input) {

        return  StringUtils.surround(input, "<a href=\"" + input + "\">", "</a>" );

    }

    public static String createHref(String visible, String url) {

        return  StringUtils.surround(visible, "<a href=\"" + url + "\">", "</a>" );

    }

}
