package com.questy.utils;

import com.questy.web.HtmlUtils;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;


public class HtmlUtilsTest {

    @Test
    public void isPathFriendly () {

        Assert.assertTrue(HtmlUtils.isPathFriendly("simple"));
        Assert.assertTrue(HtmlUtils.isPathFriendly("simp-le"));
        Assert.assertTrue(HtmlUtils.isPathFriendly("si_mple"));
        Assert.assertTrue(HtmlUtils.isPathFriendly("s6-8imple"));
        Assert.assertTrue(HtmlUtils.isPathFriendly("-s6-8imple"));
        Assert.assertFalse(HtmlUtils.isPathFriendly("sim."));

    }

}
