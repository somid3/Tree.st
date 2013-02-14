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

        sendEmails(800);

    }

    public static void sendEmails(Integer count) throws SQLException {

        List<MITEmail> mitEmails = MITEmailDao.getUnsentBySchool(null);

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
            "A team of MIT students have create a new tool to help you find and meet others across MIT.\n" +
            "\n" +
            "http://www.hackpack.org\n" +
            "\n" +
            "There are currently about 800+ MIT students, and growing. Would be great if you could join the community today.\n" +
            "\n" +
            "Also, thanks to MIT's ODGE grant we are also giving away up to $50 (for food) to anyone who organizes a meet up at MIT. http://bit.ly/XxQG1I -- let me know if you have any questions!\n" +
            "\n" +
            "Thank you,\n" +
            "\n" +
            "Hackpack Team";

        message = message.replaceAll("\\[first_name\\]", firstName);

        // Creating runnable to send email on new thread
        AmazonMailSender ser = new AmazonMailSender();
        ser.setMessageMine(EmailMimeEnum.TEXT_UTF8);
        ser.setFromName("omid@mit.edu");
        ser.setFromEmail("omid@mit.edu");
        ser.addRecipient(email);
        ser.setSubject("meets others at MIT + get $50 to ...");
        ser.setMessageText(message);

        // Sending the email
        Thread thread = new Thread(ser);
        thread.start();

    }
}
