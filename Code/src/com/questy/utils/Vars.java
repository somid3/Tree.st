
package com.questy.utils;


import java.util.Date;

public class Vars {

    /**
     * Documents the deployment state of the current release
     */
    public enum DeploymentStages {DEVELOPMENT, STAGING, PRODUCTION}

    /**
     * Last production revision number, current format is the date
     * of the last push to production
     */
    public static Long rev = 20121012L;






    /***************************
     * Company variables
     **************************/

    public static final String name = "Tree.st";

    public static String domain = "www.tree.st";





    /***********************************
     * Administration related variables
     **********************************/

    public static final String adminChecksum = "Il0v3.Pe@n6tVYt!3R";




    /***************************
     * Resource upload variables
     **************************/

    public static String resourcesUrl = "/resources/";

    public static String resourcesFilePath = "/webapps/tree.st/resources/";

    public static String resourcesTempFilePath = "/webapps/tree.st/tmp/";

    public static Integer uploadMaxFileSize = 20 * 1024 * 1024;



    /**
     * User agent the server will believe the client is forcefully using
     */
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
    public static Boolean sendEmails = true;

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
    public static Integer emailAmazonMillisecondDelayPerQueue = 500;

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

    public static final String supportEmail = "help@tree.st";

    public static final String supportEmailName = "Tree.st";

    public static final String supportEmailPassword = "s.U7-%lL+e!23~ha,rd";


    /***************************
     * CSS related variables
     *
     **************************/

    /**
     * Whether or not the CSS files of each module should be reloaded when certain
     * pages of that module are called
     */
    public static boolean reloadCss = false;


    /**
     * If the system is in development mode or not
     */
    private static final DeploymentStages deploymentStage = DeploymentStages.DEVELOPMENT;
//    public static final DeploymentStages deploymentStage = DeploymentStages.STAGING;
//    public static final DeploymentStages deploymentStage = DeploymentStages.PRODUCTION;



    static {

        // Setting up the global variables
        if (Vars.isInDevelopment())
            setToDevelopment();
        else if (Vars.isInStaging())
            setToStaging();
        else if (Vars.isInProduction())
            setToProduction();

    }




    private static void setToDevelopment () {

        domain = "localhost:8080";
        emailTemplateDomain = "localhost:8080";

        resourcesFilePath = "/Users/omid/Dropbox/Projects/Tree.st/Resources/";
        resourcesTempFilePath = "/Users/omid/Desktop/";

        sendEmails = false;
        logSentEmails = true;

//        reloadCss = true;
        reloadCss = false;
        mockUserAgent = null;

//        mockUserAgent = "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)";  // Mock IE 9 on Windows
//        mockUserAgent = "Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.1; Win64; x64; Trident/5.0)";  // Mock IE 8 on Windows

        yell();
    }

    private static void setToStaging () {

        setToDevelopment();

        reloadCss = false;
        sendEmails = true;
        logSentEmails = true;
        sendAllEmailsTo = "omid@mit.edu";

//        mockUserAgent = "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)";  // Mock IE 9 on Windows

        yell();
    }

    private static void setToProduction() {
        yell();
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

    private static void yell() {

        System.out.println("*** In " + Vars.deploymentStage + " stage!" + " Pointing to " + Vars.domain);
        System.out.println("*** In " + Vars.deploymentStage + " stage!" + " Pointing to " + Vars.domain);
        System.out.println("*** In " + Vars.deploymentStage + " stage!" + " Pointing to " + Vars.domain);

    }
}
