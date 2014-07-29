package com.questy.utils;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleemail.AWSJavaMailTransport;
import com.questy.enums.EmailMimeEnum;
import com.questy.helpers.Triple;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AmazonEmailSender implements Runnable {

    private Transport AWSTransport;
    private Session AWSsession;
    private Message message;

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

    private void sendEmail() {

        // Should the email be sent?
        if (!Vars.sendEmails) {
            System.out.println(messageText);
            return;
        }

        try {

            // Starting the AWS session and transport
            startTransport();

            String addressesCharset = "utf-8";

            // Create new message
            message = new MimeMessage(AWSsession);

            // Adding author
            message.setFrom(new InternetAddress(fromEmail, fromName, addressesCharset));

            // Deciding whether to send an emails to the actual recipients or not...
            if (Vars.sendAllEmailsTo != null) {

                System.out.println("*** Overriding email recipients to: " + Vars.sendAllEmailsTo);
                recipients.clear();
                recipients.add(Vars.sendAllEmailsTo);

            }

            // Adding recipients
            for (String toEmail : recipients)
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail, null, addressesCharset));

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

        } catch (Exception e) {

            System.out.println("Failed -- " + getMessageSummary());
            throw new RuntimeException(e);

        } finally {

            // Ending the transport
            endTransport();

        }

    }

    private void transportingMessage () throws MessagingException, IOException {

        // Sending the email
        AWSTransport.sendMessage(message, null);

        // Log sent emails?
        if (Vars.logSentEmails)
            System.out.println("Sent -- " + getMessageSummary());
    }

    private String getMessageSummary () {
        return "Email to " + recipients + " with subject: " + subject;

    }

    private void startTransport() throws MessagingException {

        String keyId = Vars.amazonEmailPublishableKey;
        String secretKey = Vars.amazonEmailSecretKey;

        BasicAWSCredentials credentials = new BasicAWSCredentials(keyId, secretKey);

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "aws");
        props.setProperty("mail.aws.user", credentials.getAWSAccessKeyId());
        props.setProperty("mail.aws.password", credentials.getAWSSecretKey());

        AWSsession = Session.getInstance(props);
        AWSTransport = new AWSJavaMailTransport(AWSsession, null);
        AWSTransport.connect();
    }

    private void endTransport() {

        try {
            AWSTransport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void run() {
       sendEmail();
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

    public void setMessageMine(EmailMimeEnum messageMine) {
        this.messageMine = messageMine;
    }

    public void addAttachment(byte[] attachment, String mimeType, String filename) {

        attachments.add(
            new Triple<byte[], String, String>(attachment, mimeType, filename)
        );

    }

}