package com.questy.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

public class NoCacheFilter implements Filter {

    public void  init(FilterConfig config) throws ServletException {

    }

    public void  doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {

        /**
         * Ensuring all data is passed to the server in the correct character set,
         * if this is removed parameters will not be interpreted with the correct
         * character set
         */
        request.setCharacterEncoding("UTF-8");

        // Preventing any type of caching
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        httpResponse.setHeader("Pragma", "no-cache");
        httpResponse.setDateHeader("Expires", 0);
        httpResponse.setCharacterEncoding("UTF-8");

        // Pass request back down the filter chain
        chain.doFilter(request, response);
    }

    public void destroy( ){

    }

}
