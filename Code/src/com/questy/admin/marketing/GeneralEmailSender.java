package com.questy.admin.marketing;
import au.com.bytecode.opencsv.CSVReader;
import com.questy.admin.domain.GeneralEmail;
import com.questy.admin.dao.GeneralEmailDao;
import com.questy.enums.EmailMimeEnum;
import com.questy.helpers.SqlLimit;
import com.questy.utils.AmazonMailSender;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;

public class GeneralEmailSender {

    public static void main (String[] args) throws SQLException {

        // Send email to folks in particular industry
        sendEmails(2000, "University");

    }

    public static void sendEmails(Integer count, String industry) throws SQLException {

        // Retrieving emails
        List<GeneralEmail> generalEmails = GeneralEmailDao.getUnsentByIndustry(null, industry, new SqlLimit(0, count));

        System.out.println("Total pool: " + generalEmails.size() );

        // Looping through each email to send message
        for (GeneralEmail generalEmail : generalEmails) {

            // Sending the email
            sendEmail(generalEmail);

            // Updating date sent of email contact
            GeneralEmailDao.updateSentOnById(null, generalEmail.getId(), new Date());
        }
    }

    public static void sendEmail(GeneralEmail generalEmail) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Creating message
        String message ="Hello from MIT,\n" +
            "\n" +
            "We are a group of graduate students who have created a patent-pending technology that can bring immediate value to your school.\n" +
            "\n" +
            "This tool allows your students and faculty to find exactly who they are looking for based on attributes that are specific to your institution. For example, students here at MIT have used this to find roommates, connect with alumni, and discover inter-departmental research opportunities -- among many other applications.\n" +
            "\n" +
            "Here is a 90 second video to learn more: http://www.tree.st.\n" +
            "\n" +
            "A simple demo takes only 15 minutes, could we set up a time that works for you?\n" +
            "\n" +
            "Thanks,\n" +
            "Socrates Rosenfeld\n" +
            "Tree St., Inc.\n";

        message = message.replaceAll("\\[from_url\\]", generalEmail.getFromUrl());
        message = message.replaceAll("\\[keyword_1\\]", generalEmail.getKeyword1());
        message = message.replaceAll("\\[keyword_2\\]", generalEmail.getKeyword2());
        message = message.replaceAll("\\[keyword_3\\]", generalEmail.getKeyword3());

        // Creating runnable to send email on new thread
        AmazonMailSender ser = new AmazonMailSender();
        ser.setMessageMine(EmailMimeEnum.TEXT_UTF8);
        ser.setFromName("socrates@tree.st");
        ser.setFromEmail("socrates@tree.st");
        ser.addRecipient(generalEmail.getEmail());
        ser.setSubject("tool for your school");
        ser.setMessageText(message);

        // Sending the email
        Thread thread = new Thread(ser);
        thread.start();
    }

    /**
     * Inserts a CVS containing emails into the general emails
     * database
     *
     * @param filename
     * @throws Exception
     */
    public static void CVSToDatabase(String filename) throws Exception {

        String [] nextLine;
        CSVReader reader = new CSVReader(new FileReader(filename));

        while ((nextLine = reader.readNext()) != null) {

            GeneralEmail ge = new GeneralEmail();
            ge.setEmail(nextLine[0]);
            ge.setFromUrl(nextLine[1]);
            ge.setIndustry(nextLine[2]);

            // Validation: checking no duplicate emails
            if(GeneralEmailDao.getCountByEmail(null, ge.getEmail()) > 0)
                continue;

            // Adding contact to database
            GeneralEmailDao.insert(
                null,
                ge.getEmail(),
                ge.getFromUrl(),
                ge.getIndustry(),
                "",
                "",
                "");

        }

    }


}
