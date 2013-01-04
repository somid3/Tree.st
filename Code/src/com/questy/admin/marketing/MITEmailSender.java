package com.questy.admin.marketing;

import com.questy.admin.dao.MITEmailDao;
import com.questy.admin.domain.MITEmail;
import com.questy.enums.EmailMimeEnum;
import com.questy.utils.AmazonMailSender;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MITEmailSender {

    public static void main (String[] args) throws SQLException {

        sendEmails(1);

    }

    public static void sendEmails(Integer count) throws SQLException {

        List<MITEmail> mitEmails = MITEmailDao.getUnsentBySchool(null, "%Engineering%");

        Integer randomIndex = null;
        Random randomGenerator = new Random();
        MITEmail mitEmail = null;
        String firstName = null;

        if (mitEmails.size() == 0)
            return;

        System.out.println("Total pool: " + mitEmails.size() );

        for (int i = 0; i < count; i++) {

            randomIndex = randomGenerator.nextInt(mitEmails.size());

            mitEmail = mitEmails.get(randomIndex);

            if (mitEmail.getEmail() == null)
                continue;

            String[] fullName = mitEmail.getName().split(", ");

            if (fullName.length == 1)
                firstName = fullName[0];
            else
                firstName = fullName[1].split(" ")[0];

            sendEmail(firstName, mitEmail.getEmail().toLowerCase());

            MITEmailDao.updateSentOnById(null, mitEmail.getId(), new Date());
        }

    }




    public static void sendEmail(String firstName, String email) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Creating message
        String message = "Hi [first_name],\n" +
            "\n" +
            "I've created a new tool for MIT to help you find anyone across departments to talk science.\n" +
            "\n" +
            "http://www.tree.st/mit\n" +
            "\n" +
            "There are currently about 1000+ MIT students on it. We also got a grant, so we are giving away $20 Amazon gift-cards. Its a bit experimental, I would appreciate it if you could join and send me some feedback.\n" +
            "\n" +
            "Thank you,\n" +
            "Omid S.\n" +
            "MIT '13";

        message = message.replaceAll("\\[first_name\\]", firstName);

        // Creating runnable to send email on new thread
        AmazonMailSender ser = new AmazonMailSender();
        ser.setMessageMine(EmailMimeEnum.TEXT_UTF8);
        ser.setFromName("omid@mit.edu");
        ser.setFromEmail("omid@mit.edu");
        ser.addRecipient(email);
        ser.setSubject("find others at MIT");
        ser.setMessageText(message);

        // Sending the email
        Thread thread = new Thread(ser);
        thread.start();

    }
}
