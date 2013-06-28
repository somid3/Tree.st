package com.questy.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;


public class StringUtilsTest {

    @Test
    public void concatTest () {

        System.out.println( StringUtils.concat("Omid Sadeghpour", 5, "..."));


    }

    @Test
    public void parseString() throws UnsupportedEncodingException {

        System.out.print(StringUtils.parseString("��"));

    }

    @Test
    public void highlight() {

        String highlighted = StringUtils.highlight("Hello world!", "world", "<before>", "<after>");
        Assert.assertEquals("Hello <before>world<after>!", highlighted);

        highlighted = StringUtils.highlight("Hello world!", "", "<before>", "<after>");
        Assert.assertEquals("Hello world!", highlighted);
    }


    @Test
    public void remainderAfterToken () {

        Assert.assertEquals("secondFolder/thirdFolder",
            StringUtils.remainderAfterFirstToken("/", "firstFolder/secondFolder/thirdFolder"));

        Assert.assertEquals("",
            StringUtils.remainderAfterFirstToken("/", "firstFolder"));

    }
}
