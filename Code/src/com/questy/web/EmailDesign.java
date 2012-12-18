package com.questy.web;


public class EmailDesign {

    public static final String aStyle = "text-decoration: none;";
    public static final String aEnd = "</a>";

    public static final String spanButtonStyle =
            "background-color: " + HtmlDesign.highlight + "; " +
            "font-family: " + HtmlDesign.fontFamily + "; " +
            "font-size: 12px; " +
            "font-weight: bold; " +
            "padding: 5px; " +
            "color: #fff; " +
            "-webkit-border-radius: 5px; " +
            "-moz-border-radius: 5px; " +
            "border-radius: 5px;";


    public static final String spanEnd = "</span>";



    public static String aBegin (String url) {

        StringBuilder out = new StringBuilder();
        out.append("<a style=\"")
        .append(aStyle)
        .append("\" href=\"")
        .append(url)
        .append("\">");

        return out.toString();

    }


    public static String spanButtonBegin () {

        StringBuilder out = new StringBuilder();
        out.append("<span style=\"")
        .append(spanButtonStyle)
        .append("\">");

        return out.toString();
    }

}
