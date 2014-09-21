package com.questy.web;

import org.junit.Test;

/**
 * Created by o.sadeghpour on 9/21/14.
 */
public class WebUtilsTest {

    @Test
    public  void testGetRootDomain() {

        String r1 = WebUtils.getRootDomain("localhost");
        String r2 = WebUtils.getRootDomain("www.docji.com");
        String r3 = WebUtils.getRootDomain("cdn1.www.docji.com");
        String r4 = WebUtils.getRootDomain("docji.com");

        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);
        System.out.println(r4);
    }

}
