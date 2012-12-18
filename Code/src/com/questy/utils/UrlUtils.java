package com.questy.utils;


import java.net.URL;

public class UrlUtils {

    public static String getUrlContents(String urlString) {

        String out = null;

        try {

            URL url = new URL(urlString);
            out = StringUtils.convertStreamToString(url.openStream(), "UTF-8");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return out;

    }

}
