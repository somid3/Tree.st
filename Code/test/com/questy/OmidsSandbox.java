package com.questy;

import com.questy.enums.EmailMimeEnum;
import com.questy.utils.AmazonEmailSender;
import com.questy.utils.AmazonMailQueue;
import com.questy.utils.Vars;

import java.util.Random;

public class OmidsSandbox {

    private static Random randomGenerator = new Random();

    public static void main(String[] args) throws Exception {

        // Creating runnable to send email on new thread
        AmazonEmailSender ser = new AmazonEmailSender();
        ser.setMessageMine(EmailMimeEnum.HTML_UTF8);
        ser.setFromName("Tester");
        ser.setFromEmail(Vars.supportEmail);
        ser.addRecipient("somid3@gmail.com");

        // Creating subject
        ser.setSubject("Test email " + randomGenerator.nextInt());

        // Customizing the message with receiver information
        ser.setMessageText("Message testing...");

        // Queueing the email
        AmazonMailQueue.queueEmail(ser);

    }


}
