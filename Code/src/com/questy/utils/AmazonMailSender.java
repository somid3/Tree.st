package com.questy.utils;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AWSJavaMailTransport;
import com.questy.enums.EmailMimeEnum;
import com.questy.helpers.Triple;
import org.apache.http.conn.HttpHostConnectException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AmazonMailSender implements Runnable {

    private Transport AWSTransport;
    private Session AWSsession;
    private Message message;

    // Retry related variables
    private int sendRetries;
    private int maxSendRetries = 5;
    private int retrySleep = 10000;
    private Exception retryException;

    // General email variables
    private String fromName;
    private String fromEmail;
    private List<String> recipients = new ArrayList<String>();

    private String subject;
    private String messageText;
    private EmailMimeEnum messageMine = EmailMimeEnum.TEXT_UTF8;

    /**
     * Triple containing the attachment byte array, the attachment
     * MIME type, and the attachment filename in respective order
     */
    private List<Triple<byte[], String, String>> attachments;

    private void initAWSTransport() throws MessagingException {

        String keyId = Vars.emailAmazonKeyId;
        String secretKey = Vars.emailAmazonSecretKey;

        BasicAWSCredentials credentials = new BasicAWSCredentials(keyId, secretKey);

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "aws");
        props.setProperty("mail.aws.user", credentials.getAWSAccessKeyId());
        props.setProperty("mail.aws.password", credentials.getAWSSecretKey());

        AWSsession = Session.getInstance(props);
        AWSTransport = new AWSJavaMailTransport(AWSsession, null);
        AWSTransport.connect();
    }

    private synchronized void sendEmail() {

        // Repeat until message is sent or limit is reached
        boolean sent = false;
        while (!sent) {

            try {

                // Have we already attempted to send this message multiple times?
                if (sendRetries >= maxSendRetries)
                    throw new Exception("Could not send email after " + maxSendRetries + " attempts", retryException);

                // Starting the AWS session and transport
                initAWSTransport();

                String addressesCharset = "utf-8";

                // Create new message
                message = new MimeMessage(AWSsession);

                // Adding author
                message.setFrom(new InternetAddress(fromEmail, fromName, addressesCharset));

                // Deciding whether to send an emails to the actual recipients or not...
                if (Vars.sendAllEmailsTo != null) {

                    // Do we need to send all emails to a particular address?
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(Vars.sendAllEmailsTo, null, addressesCharset));

                } else {

                    // Adding recipients
                    for (String toEmail : recipients)
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail, null, addressesCharset));
                }

                // Adding subject
                message.setSubject(subject);

                // Adding message
                Multipart multipart = new MimeMultipart();
                BodyPart messageBodyPart = new MimeBodyPart();

                // Dealing with defining the charset to be used for the email
                messageBodyPart.setHeader("Content-Type", messageMine.getMime());
                messageBodyPart.setHeader("Content-Transfer-Encoding", "quoted-printable");
                messageBodyPart.setContent(messageText, messageMine.getMime());

                // Adding the textual body of the message
                multipart.addBodyPart(messageBodyPart);

                // Adding attachments
                if (attachments != null && attachments.size() != 0) {

                    for (Triple<byte[], String, String> attachment : attachments) {
                        messageBodyPart = new MimeBodyPart();

                        // Dealing with defining the charset to be used for the email
                        DataSource source = new ByteArrayDataSource(attachment.getX(), attachment.getY());
                        messageBodyPart.setDataHandler(new DataHandler(source));
                        messageBodyPart.setFileName(attachment.getZ());
                        multipart.addBodyPart(messageBodyPart);
                    }

                }

                // Encoding the email to contain multiple parts
                message.setContent(multipart);

                // Compacting and saving the message
                message.saveChanges();

                // Transporting the message, either to the network or to system out, etc
                transportingMessage();

                // Email was sent, break from the while
                sent = true;

            } catch (HttpHostConnectException e) {

                // Clarifying that the email was not sent
                sent = false;

                // Attempting to retry
                doRetry(e);

            } catch (ConnectException e) {

                // Clarifying that the email was not sent
                sent = false;

                // Attempting to retry
                doRetry(e);

            } catch (Exception e) {

                System.out.println("Failed -- " + getMessageSummary());
                throw new RuntimeException(e);

            }


        }


    }

    public synchronized void run() {

        start();
        sendEmail();
        end();

    }




    private void transportingMessage () throws MessagingException, IOException {

        if (Vars.sendEmails) {

            // Sending the email
            AWSTransport.sendMessage(message, null);

            // Log sent emails?
            if (Vars.logSentEmails)
                System.out.println("Sent -- " + getMessageSummary());

        } else {

            // Output the email content
            PrintStream out = System.out;
            message.writeTo(out);

        }

    }

    private void doRetry(Exception e) {

        // Increasing retry count to document failure
        sendRetries++;

        // Saving exception in case this is the last one
        retryException = e;

        // Waiting for next retry
        sleep(retrySleep);

        // Should this be logged?
        if (Vars.logSentEmails)
            System.out.println("Retry " + sendRetries + " -- " + getMessageSummary());

    }

    private String getMessageSummary () {
        return "Email to " + recipients + " with subject: " + subject;

    }

    private synchronized void start() {

        // Increase the email queue count
        Vars.emailAmazonQueueCount++;

        // Calculate how much to sleep based on queue
        Integer sleepMilli = Vars.emailAmazonQueueCount * Vars.emailAmazonMillisecondDelayPerQueue;

        // Sleep to wait queue turn
        sleep(sleepMilli);

    }


    private synchronized void end() {

        // Decrease the email queue count
        Vars.emailAmazonQueueCount--;

        try {

            AWSTransport.close();

        } catch (MessagingException e) {

            e.printStackTrace();

        }
    }

    private synchronized void sleep(Integer milliseconds) {

        try {

            Thread.sleep(milliseconds);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

    }



    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void addRecipient(String recipient) {
        recipients.add(recipient) ;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public EmailMimeEnum getMessageMine() {
        return messageMine;
    }

    public int getRetrySleep() {
        return retrySleep;
    }

    public void setRetrySleep(int retrySleep) {
        this.retrySleep = retrySleep;
    }

    public int getMaxSendRetries() {
        return maxSendRetries;
    }

    public void setMaxSendRetries(int maxSendRetries) {
        this.maxSendRetries = maxSendRetries;
    }

    public void setMessageMine(EmailMimeEnum messageMine) {
        this.messageMine = messageMine;
    }

    public void addAttachment(byte[] attachment, String mimeType, String filename) {

        attachments.add(
            new Triple<byte[], String, String>(attachment, mimeType, filename)
        );

    }
}