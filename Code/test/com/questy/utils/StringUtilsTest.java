package com.questy.utils;

import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StringUtilsTest {

    @Test
    public void concatTest () {

        Assert.assertEquals("Omid...", StringUtils.concat("Omid Sadeghpour", 4, "..."));
        Assert.assertEquals("Omid S...", StringUtils.concat("Omid Sadeghpour", 6, "..."));

    }

    @Test
    public void concatHeavyTest () {

        List<String> exitWiths = new ArrayList<String>();
        exitWiths.add("?");
        exitWiths.add(":");
        exitWiths.add("\n");
        exitWiths.add("--");

        Integer maxLength = 80;
        String ifMaxLengthExitWith = " ";
        String ifMaxLengthPostfix = "...";

        System.out.println(
            StringUtils.concat(
                "Omid Sadeghpour",
                exitWiths,
                maxLength,
                ifMaxLengthExitWith,
                ifMaxLengthPostfix)
        );


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
