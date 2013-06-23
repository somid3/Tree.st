package com.questy.web;

import com.questy.utils.StringUtils;

import java.util.List;


public class HtmlUtils {


    public static String getRandomId() {
        return StringUtils.random();
    }

    public static String getRandomId(Integer length) {
        return StringUtils.random(length);
    }

    /**
     * Takes a string an adds html links to material that appears to be a url
     * @param input
     * @return
     */
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

    /**
     * Adds html paragraph tags to paragraphs separated by new lines
     */
    public static String paragraphize (String input) {

        String[] tokens = input.split("\\n");

        StringBuilder out = new StringBuilder(input.length());
        for (String token : tokens)
            out.append("<p>").append(token).append("</p>");

        return out.toString();

    }

    public static Boolean isPathFriendly(String path) {

        return path.matches("^[a-zA-Z0-9-_]+$");

    }

}
