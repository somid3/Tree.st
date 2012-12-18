package com.questy.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;


@Deprecated
public class SendEmailRunnable implements Runnable {


    // SMTP email variables
    private String smtpHost;
    private Integer smtpPort;

    // General email variables
    private String fromName;
    private String fromEmail;
    private String fromEmailPassword;
    private String toEmail;
    private String subject;
    private String messageText;

    /**
     * MIME content is stored in the string component, the
     * byte array contains the attachment being sent
     *
     */
    private Map<byte[], String> attachments;


    public void run() {

        sendEmailViaSmtp();

    }






    private synchronized void sendEmailViaSmtp () {


        Properties props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.socketFactory.port", smtpPort);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", smtpPort);

        try {

            Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, fromEmailPassword);
                    }
                });

            // Ensuring the final email address contains the from name
            String finalFromAddress = null;
            String finalFromName = StringUtils.quoteIt(fromName);
            if (finalFromName != null)
                finalFromAddress = finalFromName + " <" + fromEmail + ">";
            else
                finalFromAddress = fromEmail;

            // Putting together the message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(finalFromAddress));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(messageText);

            // Should we be sending real emails out?
            if (Vars.sendEmails) {

                // Yes, send the message awaaaay...
                Transport.send(message);

            } else {

                // No, just display email message on console...
                message.writeTo(System.out);
            }


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }








    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public Integer getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(Integer smtpPort) {
        this.smtpPort = smtpPort;
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

    public String getFromEmailPassword() {
        return fromEmailPassword;
    }

    public void setFromEmailPassword(String fromEmailPassword) {
        this.fromEmailPassword = fromEmailPassword;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
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

}

