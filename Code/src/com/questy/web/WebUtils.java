package com.questy.web;

import com.questy.utils.StringUtils;
import com.questy.utils.Vars;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.SkipPageException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class WebUtils {

    public static final Integer SECONDS_IN_YEAR = 365 * 24 * 60 * 60;

    HttpServletRequest request;
    HttpServletResponse response;

    Map<String, Cookie> cookieIndex = new HashMap<String, Cookie>();

    public WebUtils(HttpServletRequest request, HttpServletResponse response) {

        this.request = request;
        this.response = response;

        // Creating cookie index
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cook : cookies) {
                cookieIndex.put(cook.getName(), cook);
            }
        }
    }

    public String getUserAgent() {

        if (Vars.testingMockUserAgent != null)
            return Vars.testingMockUserAgent;

        return request.getHeader("User-Agent");
    }


    public Cookie getCookie(String name) {
        return cookieIndex.get(name);
    }

    public String getCookieValue(String name) {

        Cookie cookie = cookieIndex.get(name);

        if (cookie != null)
            return cookie.getValue();
        else
            return null;
    }

    public Integer getCookieValueAsInteger(String name) {
        return StringUtils.parseInt(getCookieValue(name));
    }

    public void createCookie(String name, String value, Integer maxAge, String path) {

        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        response.addCookie(cookie);

    }

    public void deleteCookieByName(String name) {

        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    /**
     * Internal forward to a particular page. This method stops the processing of a jsp page
     *
     * @param pathFromRootPage Should start with a slash "/" and must represent the location to a sevlet or a jsp page from the root of the context
     * @throws ServletException
     * @throws IOException
     * @throws SkipPageException
     */
    public void forward(String pathFromRootPage) throws ServletException, IOException, SkipPageException {

        if (pathFromRootPage == null)
            throw new IllegalStateException("Path from root page can not be null");

        if (!pathFromRootPage.startsWith("/"))
            throw new IllegalStateException("Path from root page must be a non-absolute url, that is, it must start with a forward slash");

        RequestDispatcher rd;
        rd = request.getRequestDispatcher(pathFromRootPage);
        rd.forward(request, response);
        response.flushBuffer();
        throw new SkipPageException();
    }

    /**
     * Internal include of a particular page. This method stops the processing of a jsp page
     *
     * @param pathFromRootPage Should start with a slash "/" and must represent the location to a sevlet or a jsp page from the root of the context
     * @throws ServletException
     * @throws IOException
     * @throws SkipPageException
     */
    public void stealth(String pathFromRootPage) throws ServletException, IOException, SkipPageException {

        if (pathFromRootPage == null)
            throw new IllegalStateException("Path from root page can not be null");

        if (!pathFromRootPage.startsWith("/"))
            throw new IllegalStateException("Path from root page must be a non-absolute url, that is, it must start with a forward slash");

        RequestDispatcher rd;
        rd = request.getRequestDispatcher(pathFromRootPage);
        rd.include(request, response);
        response.flushBuffer();
        throw new SkipPageException();
    }


    /**
     * Redirection to a particular page
     *
     * @param location Absolute location relative to the root
     * @throws SkipPageException
     * @throws IOException
     */
    public void redirect(String location) throws SkipPageException, IOException {
        location = response.encodeURL(location);
        response.sendRedirect(location);
        throw new SkipPageException();
    }

    public String getRequestedPath() {
        String path = (String) request.getAttribute("javax.servlet.forward.request_uri");
        return path.substring(1).toLowerCase();
    }

    public String getRequestedDomain() {
        String domain = request.getServerName();
        return domain.toLowerCase();
    }


    /**
     * Dumps all the data contained by a http servlet request,
     * used for debugging purposes
     *
     * @param request
     * @return
     */
    public static String requestDump(HttpServletRequest request) {

        StringBuilder buf = new StringBuilder();

        {
            Object result = null;
            String methodName = null;
            buf.append("Method values:\n");
            for (Method method : request.getClass().getMethods()) {

                methodName = method.getName();

                if (!methodName.startsWith("get"))
                    continue;

                buf.append(methodName + ": ");

                if (method.getParameterTypes().length == 0) {

                    try {

                        result = method.invoke(request);

                        buf.append(result);
                        buf.append('\n');

                    } catch (Exception e) {
                        buf.append("illegal call...\n");
                    }

                }
            }

        }

        buf.append('\n');

        {
            buf.append("Attributes:\n");
            Enumeration enume = request.getAttributeNames();
            String attr = null;
            while (enume.hasMoreElements()) {
                attr = (String) enume.nextElement();
                buf.append(attr + ": " + request.getAttribute(attr) + '\n');
            }
        }

        buf.append('\n');

        {
            buf.append("Full Stacktrace:\n");
            try {

                Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
                buf.append(getStackTrace(exception));

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        buf.append('\n');

        {
            buf.append("Headers:\n");
            Enumeration enume = request.getHeaderNames();
            String attr = null;
            while (enume.hasMoreElements()) {
                attr = (String) enume.nextElement();
                buf.append(attr + ": " + request.getHeader(attr) + '\n');
            }
        }

        buf.append('\n');

        {
            buf.append("Locales:\n");
            Enumeration enume = request.getLocales();
            Locale attr = null;
            while (enume.hasMoreElements()) {
                attr = (Locale) enume.nextElement();
                buf.append(attr.toString() + '\n');
            }
        }

        buf.append('\n');

        {
            buf.append("Parameters:\n");
            Enumeration enume = request.getParameterNames();
            String attr = null;
            while (enume.hasMoreElements()) {
                attr = (String) enume.nextElement();
                buf.append(attr + ": " + request.getParameter(attr) + '\n');
            }
        }

        buf.append('\n');

        {
            buf.append("Cookies:\n");
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                buf.append(cookie.getName() + ": " + cookie.getValue() + '\n');
            }
        }

        return buf.toString();

    }

    public static String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }

}
