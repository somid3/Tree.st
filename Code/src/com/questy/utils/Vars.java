package com.questy.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class Vars {

    /**
     * Documents the deployment stage of the current release
     */
    public enum DeploymentStages {DEVELOPMENT, STAGING, PRODUCTION}

    /**
     * Overall properties used to define all the variable parameters
     */
    public static final Properties properties = new Properties();

    /**
     * Last production revision number, current format is the date
     * of the last push to production
     */
    public static Long rev = 20130113L;

    /**
     * Current stage
     */
//    private static final DeploymentStages deploymentStage = DeploymentStages.DEVELOPMENT;
//    public static final DeploymentStages deploymentStage = DeploymentStages.STAGING;
    public static final DeploymentStages deploymentStage = DeploymentStages.PRODUCTION;






    /***************************
     * Overall variables
     **************************/

    public static final String name = "treelift";

    public static String domain = "www.treelift.com";


    /***************************
     * Application variables
     **************************/

    public static Boolean enableTimelocks = true;





    /***********************************
     * Administration related variables
     **********************************/

    public static final String adminChecksum = "Il0v3.Pe@n6tVYt!3R";







    /***************************
     * Resource upload variables
     **************************/

    public static String resourcesUrl = "/resources/";

    public static String resourcesFilePath = null;

    public static String resourcesTempFilePath = null;

    public static Integer uploadMaxFileSize = 20 * 1024 * 1024;






    /***********************
     * User agent parameters
     **********************/

    public static String mockUserAgent = null;






    /***************************
     * Email variables
     **************************/

    /**
     * Domain name used to create the URL where email templates will be generated
     */
    public static String emailTemplateDomain = "localhost";

    /**
     * Should the server send emails or publish them to the console?
     */
    public static Boolean sendEmails = false;

    /**
     * Should we print out a message every time an email is sent?
     */
    public static Boolean logSentEmails = true;

    /**
     * For all emails to be sent to this email address. Used for development purposes
     */
    public static String sendAllEmailsTo = null;

    /**
     * Based on the email queue location, the number of milliseconds an email thread
     * should wait before transmitting its message
     */
    public static Integer emailAmazonMillisecondDelayPerQueue = 100;

    /**
     * Amazon SES credentials
     */
    public static String emailAmazonKeyId = "17PSBE0Y2JY8PAC6VBR2";

    /**
     * Amazon SES credentials
     */
    public static String emailAmazonSecretKey = "eAQCx/eHMPrnRtLKbhLNg8x+c/CTtOvruOwqybvv";

    /**
     * Queue of outgoing emails
     */
    public static int emailAmazonQueueCount;

    public static String supportEmail = "hello@treelift.com";

    public static String supportEmailName = "treelift.com";






    /***************************
     * CSS related variables
     **************************/

    /**
     * Whether or not the CSS files of each module should be reloaded when certain
     * pages of that module are called
     */
    public static boolean reloadCss = false;


    /***************************
     * SQL variables
     **************************/

    public static String sqlUsername = "";
    public static String sqlPassword = "";
    public static String sqlHost = "";
    public static String sqlDatabaseName = "";
    public static String sqlParameters = "";





    static {

        try {
            // Setting up the global variables
            if (Vars.isInDevelopment())
                setToDevelopment();
            else if (Vars.isInStaging())
                setToStaging();
            else if (Vars.isInProduction())
                setToProduction();

            // Display in the console the stage
            yell();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    private static void setToDevelopment () throws IOException {
        loadProperties("dev.properties");

        domain = loadPropertyAsString("domain");
        emailTemplateDomain = loadPropertyAsString("emailTemplateDomain");

        resourcesFilePath = loadPropertyAsString("resourcesFilePath");
        resourcesTempFilePath = loadPropertyAsString("resourcesTempFilePath");

        sendEmails =  loadPropertyAsBoolean("sendEmails");
        logSentEmails = loadPropertyAsBoolean("logSentEmails");

        reloadCss = loadPropertyAsBoolean("reloadCss");
        mockUserAgent = loadPropertyAsString("mockUserAgent");

        sqlUsername = loadPropertyAsString("sqlUsername");
        sqlPassword = loadPropertyAsString("sqlPassword");
        sqlHost = loadPropertyAsString("sqlHost");
        sqlDatabaseName = loadPropertyAsString("sqlDatabaseName");
        sqlParameters = loadPropertyAsString("sqlParameters");

//        mockUserAgent = "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)";  // Mock IE 9 on Windows
//        mockUserAgent = "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.1; Win64; x64; Trident/5.0)";  // Mock IE 8 on Windows

        //loadProperties("dev.properties");
    }

    private static void setToStaging () throws IOException {
        loadProperties("stage.properties");

        domain = loadPropertyAsString("domain");
        emailTemplateDomain = loadPropertyAsString("emailTemplateDomain");

        resourcesFilePath = loadPropertyAsString("resourcesFilePath");
        resourcesTempFilePath = loadPropertyAsString("resourcesTempFilePath");

        reloadCss = loadPropertyAsBoolean("reloadCss");
        sendEmails =  loadPropertyAsBoolean("sendEmails");
        logSentEmails = loadPropertyAsBoolean("logSentEmails");
        resourcesFilePath = loadPropertyAsString("resourcesFilePath");
        sendAllEmailsTo = loadPropertyAsString("sendAllEmailsTo");

        sqlUsername = loadPropertyAsString("sqlUsername");
        sqlPassword = loadPropertyAsString("sqlPassword");
        sqlHost = loadPropertyAsString("sqlHost");
        sqlDatabaseName = loadPropertyAsString("sqlDatabaseName");
        sqlParameters = loadPropertyAsString("sqlParameters");

//        mockUserAgent = "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)";  // Mock IE 9 on Windows



    }

    private static void setToProduction() throws IOException {
        loadProperties("prod.properties");

        sendEmails =  loadPropertyAsBoolean("sendEmails");

        resourcesFilePath = loadPropertyAsString("resourcesFilePath");
        resourcesTempFilePath = loadPropertyAsString("resourcesTempFilePath");


        sqlUsername = loadPropertyAsString("sqlUsername");
        sqlPassword = loadPropertyAsString("sqlPassword");
        sqlHost = loadPropertyAsString("sqlHost");
        sqlDatabaseName = loadPropertyAsString("sqlDatabaseName");
        sqlParameters = loadPropertyAsString("sqlParameters");

    }


    /**
     * Loads a properties relative to the Vars class given the file name
     *
     * @param fileName
     * @throws IOException
     */
    private static void loadProperties(String fileName) throws IOException {

        // Load the properties file relative to the Vars class
        properties.load(Vars.class.getResourceAsStream(fileName));

    }

    private static String loadPropertyAsString(String property){
        return (String) properties.getProperty(property);
    }

    private static boolean loadPropertyAsBoolean(String property){
        return Boolean.parseBoolean((String) properties.getProperty(property));
    }


    public static boolean isInDevelopment () {

        if (deploymentStage == DeploymentStages.DEVELOPMENT)
            return true;
        else
            return false;
    }

    public static boolean isInStaging () {

        if (deploymentStage == DeploymentStages.STAGING)
            return true;
        else
            return false;
    }

    public static boolean isInProduction () {

        if (deploymentStage == DeploymentStages.PRODUCTION)
            return true;
        else
            return false;
    }



    /**
     * Updates the revision dynamically to make sure a new revision number is created every time. Used
     * to prevent caching on browsers while developing
     *
     */
    public static void setDevelopmentRev () {

        if (Vars.isInDevelopment())
            Vars.rev = new Date().getTime();
    }

    /**
     * Documents to the console the current stage of the application
     */
    private static void yell() {

        System.out.println("*** In " + Vars.deploymentStage + " stage!" + " Pointing to " + Vars.domain);
        System.out.println("*** In " + Vars.deploymentStage + " stage!" + " Pointing to " + Vars.domain);
        System.out.println("*** In " + Vars.deploymentStage + " stage!" + " Pointing to " + Vars.domain);

    }
}
