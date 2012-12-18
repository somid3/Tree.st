package com.questy.admin.scrapper;

import com.questy.utils.StringUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

public class MITPeopleAliasScrapperThread implements Runnable {

    private String proxyHost;
    private int proxyPort;
    private int sleep;

    public void run () {

        String content = null;

        try {

            while (true) {

                // Sleeping
                Thread.sleep(sleep);

                // Requesting new content
                content = MITPeopleAliasScrapper.content.remove(0);

                // Are any three letters available? If no, break...
                if (content == null) {
                    System.out.println(proxyHost + ": could not get content...");
                    continue;
                }

                // Doing the http request
                doRequest(content);
            }

        } catch (Exception e) {

            System.out.println(proxyHost + ": error with " + content);
//            e.printStackTrace();

            // Adding three letter to pool
            MITPeopleAliasScrapper.content.add(0, content);

            // Re-spawn this thread
            MITPeopleAliasScrapperThread.spawn(proxyHost, proxyPort, 60000);
        }

    }

    private void doRequest(String content) throws Exception {

        HttpURLConnection connection = null;
        try {

            String queryUrl = "http://web.mit.edu/bin/cgicso?query=alias" + content + "*";

            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, proxyPort));

            connection = (HttpURLConnection)new URL(queryUrl).openConnection(proxy);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Content-type", "text/xml");
            connection.setRequestProperty("Accept", "text/xml, application/xml");
            connection.setRequestMethod("GET");

            String response = StringUtils.convertStreamToString(connection.getInputStream(), "UTF-8");
//            String response = "test";

            FileUtils.writeStringToFile(new File("/Users/omid/Dropbox/Projects/MIT Directory Files/" + content + ".html"), response);

            System.out.println(proxyHost + ": " + content);
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

        MITPeopleAliasScrapperThread mpst = new MITPeopleAliasScrapperThread();
        mpst.setProxyHost(proxyHost);
        mpst.setProxyPort(proxyPort);
        mpst.setSleep(sleep);

        // Start thread
        Thread thread = new Thread(mpst);
        thread.start();

        System.out.println(proxyHost + ": started");
    }
}
