package com.questy.utils;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;


public class StringUtilsTest {

    @Test
    public void concatTest () {

        System.out.println( StringUtils.concat("Omid Sadeghpour", 5, "..."));


    }

    @Test
    public void parseString() {

        System.out.print(StringUtils.parseString("��"));

    }

    @Test
    public void highlight() {

        String highlighted = StringUtils.highlight("Hello world!", "world", "<before>", "<after>");
        Assert.assertEquals("Hello <before>world<after>!", highlighted);

        highlighted = StringUtils.highlight("Hello world!", "", "<before>", "<after>");
        Assert.assertEquals("Hello world!", highlighted);
    }


}
