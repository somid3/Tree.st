package com.questy.utils;
import com.questy.enums.EmailMimeEnum;
import com.questy.helpers.Triple;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;

public class AmazonSmtpSender implements Runnable {

    private Transport transport;
    private Session session;
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







    // Supply your SMTP credentials below. Note that your SMTP credentials are different from your AWS credentials.
    static final String SMTP_USERNAME = Vars.amazonEmailPublishableKey;  // Replace wißth your SMTP username.
    static final String SMTP_PASSWORD = Vars.amazonEmailSecretKey;  // Replace with your SMTP password.

    // Amazon SES SMTP host name. This example uses the us-east-1 region.
    static final String HOST = Vars.amazonEmailServer;

    // Port we will connect to on the Amazon SES SMTP endpoint. We are choosing port 587 because we will use
    // STARTTLS to encrypt the connection.
    static final int PORT = 587;

    private void startSessionAndTransport() throws Exception {

        // Create a Properties object to contain connection configuration information.
        Properties props = System.getProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.port", PORT);

        // Set properties indicating that we want to use STARTTLS to encrypt the connection.
        // The SMTP session will begin on an unencrypted connection, and then the client
        // will issue a STARTTLS command to upgrade to an encrypted connection.
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");

        // Create a Session object to represent a mail session with the specified properties.
        session = Session.getDefaultInstance(props);

        // Create a transport.
        transport = session.getTransport();

        // Create a message with the specified information.
        message = new MimeMessage(session);
    }


    private String getMessageSummary () {
        return "Email to " + recipients + " with subject: " + subject;

    }

    private void endSessionAndTransport() {
        try { transport.close(); }
        catch (MessagingException e) { /* Do nothing */ }
    }

    private void transportingMessage () throws MessagingException, IOException {

        // Connect to Amazon SES using the SMTP username and password you specified above.
        transport.connect(HOST, SMTP_USERNAME, SMTP_PASSWORD);

        // Sending the email
        transport.sendMessage(message, message.getAllRecipients());

        // Log sent emails?
        if (Vars.logSentEmails)
            System.out.println("Sent -- " + getMessageSummary());

    }

    private void sendEmail() {

        // Should the email be sent?
        if (!Vars.sendEmails) {
            System.out.println(messageText);
            return;
        }

        // Send the message.
        try {
            startSessionAndTransport();

            String addressesCharset = "utf-8";

            // Adding author
            message.setFrom(new InternetAddress(fromEmail, fromName, addressesCharset));

            // Hacking recipient if needed
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

            // Encoding the email to contain multiple parts incase of attachments
            message.setContent(multipart);

            // Dealing with defining the charset to be used for the email
            messageBodyPart.setHeader("Content-Type", messageMine.getMime());
            messageBodyPart.setHeader("Content-Transfer-Encoding", "quoted-printable");
            messageBodyPart.setContent(messageText, messageMine.getMime());
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

            // Compacting and saving the message
            message.saveChanges();

            // Transporting the message, either to the network or to system out, etc
            transportingMessage();

        } catch (Exception ex) {

            System.out.println("Error message: " + ex.getMessage());
            ex.printStackTrace();

        } finally {

            // Close and terminate the connection.
            endSessionAndTransport();
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

}