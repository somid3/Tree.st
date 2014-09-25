package com.questy.utils;


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
    public static Long rev = null;

    /**
     * Current stage
     */
//    private static final DeploymentStages deploymentStage = DeploymentStages.DEVELOPMENT;
//    public static final DeploymentStages deploymentStage = DeploymentStages.STAGING;
    public static final DeploymentStages deploymentStage = DeploymentStages.PRODUCTION;






    /***************************
     * Overall variables
     **************************/

    public static String name = null;

    public static String domain = null;


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

    public static String resourcesUrl = null;

    public static String resourcesFilePath = null;

    public static String resourcesTempFilePath = null;

    public static Integer uploadMaxFileSize = 20 * 1024 * 1024;






    /***********************
     * User agent parameters
     **********************/

    /**
     * Makes the application believe the user is using a different browser
     */
    public static String testingMockUserAgent = null;

    /**
     * Changes the requested domain name for the network routing mechanism to the one provided
     */
    public static String testingRequestingDomain = null;






    /***************************
     * Email variables
     **************************/

    /**
     * Domain name used to create the URL where email templates will be generated
     */
    public static String emailTemplateDomain = "localhost:8080";

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
    public static Integer amazonEmailMillisecondDelayPerQueue = 200;

    /**
     * Amazon SES credentials
     */
    public static String amazonEmailPublishableKey = null;

    /**
     * Amazon SES credentials
     */
    public static String amazonEmailSecretKey = null;

    /**
     * Amazon SES Server
     */
    public static String amazonEmailServer = null;

    /**
     * Email address of outgoing emails
     */
    public static String supportEmail = "hello@doctorana.com";

    /**
     * Email name of outgoing emails
     */
    public static String supportEmailName = "Doctorana";


    /***************************
     * Credit Card variables
     **************************/

    /**
     * Stripe keys
     */
    public static String stripePublishableKey = null;
    public static String stripeSecretKey = null;



    /***************************
     * SQL variables
     **************************/

    public static String sqlUsername = null;
    public static String sqlPassword = null;
    public static String sqlHost = null;
    public static String sqlDatabaseName = "questy";





    static {

        try {
            // Setting up the global variables
            if (Vars.isInDevelopment())
                loadProperties("dev.properties");
            else if (Vars.isInStaging())
                loadProperties("stage.properties");
            else if (Vars.isInProduction())
                loadProperties("prod.properties");

            // Load the variables from the properties
            Vars.rev = new Date().getTime();
            setVariables();

            // Display in the console the stage
            yell();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    private static void setVariables () {

        name = loadPropertyAsString("name");
        domain = loadPropertyAsString("domain");
        enableTimelocks = loadPropertyAsBoolean("enableTimelocks");

        resourcesUrl = loadPropertyAsString("resourcesUrl");
        resourcesFilePath = loadPropertyAsString("resourcesFilePath");
        resourcesTempFilePath = loadPropertyAsString("resourcesTempFilePath");

        emailTemplateDomain = loadPropertyAsString("emailTemplateDomain");
        sendEmails =  loadPropertyAsBoolean("sendEmails");
        logSentEmails = loadPropertyAsBoolean("logSentEmails");
        sendAllEmailsTo = loadPropertyAsString("sendAllEmailsTo");
        amazonEmailPublishableKey = loadPropertyAsString("amazonEmailPublishableKey");
        amazonEmailSecretKey = loadPropertyAsString("amazonEmailSecretKey");
        amazonEmailServer = loadPropertyAsString("amazonEmailServer");

        supportEmail = loadPropertyAsString("supportEmail");
        supportEmailName = loadPropertyAsString("supportEmailName");


        sqlUsername = loadPropertyAsString("sqlUsername");
        sqlPassword = loadPropertyAsString("sqlPassword");
        sqlHost = loadPropertyAsString("sqlHost");
        sqlDatabaseName = loadPropertyAsString("sqlDatabaseName");

        stripePublishableKey = loadPropertyAsString("stripePublishableKey");
        stripeSecretKey = loadPropertyAsString("stripeSecretKey");

        testingMockUserAgent = loadPropertyAsString("mockUserAgent");
        testingRequestingDomain = loadPropertyAsString("testingRequestingDomain");
    }


    private static void loadProperties(String fileName) throws IOException {
        properties.load(Vars.class.getResourceAsStream(fileName));
    }

    private static String loadPropertyAsString(String property){
        return (String) properties.getProperty(property);
    }

    private static boolean loadPropertyAsBoolean(String property){
        return Boolean.parseBoolean((String) properties.getProperty(property));
    }

    public static boolean isInDevelopment () {
        return (deploymentStage == DeploymentStages.DEVELOPMENT);
    }

    public static boolean isInStaging () {
        return (deploymentStage == DeploymentStages.STAGING);
    }

    public static boolean isInProduction () {
        return (deploymentStage == DeploymentStages.PRODUCTION);
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
