package com.questy.utils;

import com.questy.helpers.Tuple;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class UrlQuery {

    private List<Tuple<String, Object>> params = new ArrayList<Tuple<String, Object>>();

    public void add(String param, Object value) {
        params.add(new Tuple<String, Object>(param, value));
    }

    public void addAll(UrlQuery urlQuery) {

        if (urlQuery == null)
            return;

        params.addAll(urlQuery.getParams());
    }

    @Override
    public String toString() {

        StringBuilder out = new StringBuilder();

        for (Tuple<String, Object> param : params) {

            if (out.length() > 0)
                out.append("&");

            out.append(String.format("%s=%s",
                urlEncodeUTF8(param.getX()),
                urlEncodeUTF8(param.getY().toString())
            ));

        }

        return out.toString();
    }

    public static String urlEncodeUTF8(String s) {

       try {

           return URLEncoder.encode(s, "UTF-8");

       } catch (UnsupportedEncodingException e) {

           throw new UnsupportedOperationException(e);

       }
   }

    public List<Tuple<String, Object>> getParams() {
        return params;
    }

    public void clearParams() {
        params.clear();
    }
}
