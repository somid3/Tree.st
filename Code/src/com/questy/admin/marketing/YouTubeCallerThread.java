package com.questy.admin.marketing;

import com.questy.utils.StringUtils;
import com.questy.web.HtmlUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class YouTubeCallerThread implements Runnable {

    private String proxyHost;
    private int proxyPort;
    private int sleep;

    public void run () {

        try {

            while (true) {

                // Sleeping
                Thread.sleep(sleep);

                // Doing the http request
                doRequest();
            }

        } catch (Exception e) {

            // Re-spawn this thread
            YouTubeCallerThread.spawn(proxyHost, proxyPort, 60000);
        }

    }

    private void doRequest() throws Exception {

        HttpURLConnection connection = null;
        try {

            String queryUrl = "http://www.youtube.com/watch?v=sogVLwKhje0&feature=youtu.be";

            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));

            connection = (HttpURLConnection)new URL(queryUrl).openConnection(proxy);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-type", "text/xml");
            connection.setRequestProperty("Accept", "text/xml, application/xml");
            connection.setRequestMethod("GET");

            String response = StringUtils.convertStreamToString(connection.getInputStream(), "UTF-8");
//            String response = "test";

            FileUtils.writeStringToFile(new File("/Users/omid/Desktop/" + HtmlUtils.getRandomId() + ".html"), response);

            connection.disconnect();
            setSleep(60000);

        } catch (Exception e) {

            // Closing all resources
            if (connection != null)
                connection.disconnect();

            // Throwing exception to re-spawn thread
            throw e;
        }

    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public int getSleep() {
        return sleep;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

    public static void spawn (String proxyHost, int proxyPort, int sleep) {

        YouTubeCallerThread mpst = new YouTubeCallerThread();
        mpst.setProxyHost(proxyHost);
        mpst.setProxyPort(proxyPort);
        mpst.setSleep(sleep);

        // Start thread
        Thread thread = new Thread(mpst);
        thread.start();

        System.out.println(proxyHost + ": started");
    }
}
