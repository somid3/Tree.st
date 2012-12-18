package com.questy.utils;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;


public class MathUtilsTest {

    @Test
    public void ask () {

        Assert.assertEquals("25", MathUtils.percentage(5f, 20f));
        Assert.assertEquals("1", MathUtils.percentage(1f, 100f));

        Assert.assertEquals("25", MathUtils.percentage(5, 20));
        Assert.assertEquals("1", MathUtils.percentage(1, 100));


        Assert.assertEquals((Integer) 10, MathUtils.barLength(1f, 100f, 1000f));
        Assert.assertEquals((Integer) 125, MathUtils.barLength(5f, 20f, 500f));
        Assert.assertEquals((Integer) 440, MathUtils.barLength(10f, 10f, 440f));
    }

}
