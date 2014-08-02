package com.questy.utils;

import java.util.LinkedList;
import java.util.Queue;

public class AmazonMailQueue implements Runnable {

    private static AmazonMailQueue instance = null;

    private Queue<AmazonSmtpSender> emailQueue;

    static {

        // Instantiating the singleton
        instance = new AmazonMailQueue();

        // Starting the mail queue to send emails
        Thread queueThread = new Thread(instance);
        queueThread.start();
    }

    private AmazonMailQueue() {

        emailQueue = new LinkedList<AmazonSmtpSender>();

    }

    private synchronized void sleep(Integer milliseconds) {

        try {

            Thread.sleep(milliseconds);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }

    public void run() {

        // Begin timer to send messages
        AmazonSmtpSender outboundEmail = null;

        while(true) {

            // Are there any emails in the queue?
            outboundEmail = emailQueue.poll();

            // Send the email if one exists
            if (outboundEmail != null) {

                // Starting the new email to be sent
                Thread emailThread = new Thread(outboundEmail);
                emailThread.start();
            }

            // Control for throttle
            sleep(Vars.amazonEmailMillisecondDelayPerQueue);

        }
    }

    /**
     * Add an email to the sending queue
     *
     * @param email
     */
    public static void queueEmail(AmazonSmtpSender email) {
        instance.emailQueue.add(email);
    }
}
