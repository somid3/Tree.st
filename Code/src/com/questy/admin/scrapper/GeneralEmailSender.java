package com.questy.admin.scrapper;
import au.com.bytecode.opencsv.CSVReader;
import com.questy.admin.domain.GeneralEmail;
import com.questy.admin.dao.GeneralEmailDao;
import com.questy.enums.EmailMimeEnum;
import com.questy.utils.AmazonMailSender;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Shlomo
 * Date: 1/3/13
 * Time: 4:26 PM
 * To change this template use File | Settings | File Templates.
 */

public class GeneralEmailSender {

    public static void sendEmails(Integer count) throws SQLException
    {
        List<GeneralEmail> generalEmails = GeneralEmailDao.getUnsent(null);
        Integer randomIndex = null;
        Random randomGenerator = new Random();
        GeneralEmail generalEmail = null;
        String firstName = null;

        if (generalEmails.size() == 0)
            return;

        System.out.println("Total pool: " + generalEmails.size() );

        for (int i = 0; i < count; i++) {

            randomIndex = randomGenerator.nextInt(generalEmails.size());

            generalEmail = generalEmails.get(randomIndex);

            if (generalEmail.getEmail() == null)
                continue;

            firstName = generalEmail.getFirst_name();

            sendEmail(firstName, generalEmail.getEmail().toLowerCase());

            GeneralEmailDao.updateSentOnById(null, generalEmail.getId(), new Date());
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

    //Input: contacts in csv format
    //Output: Nothing
    public static void sendEmailsTo(String filename) throws Exception
    {
        List<GeneralEmail> contacts = CSVToContacts(filename);
        writeContactsToDB(contacts);   //Contact list is now in DB
        sendEmails(10);         //Sends emails to Contacts in DB
    }

    //Input: contacts in csv format
    //Output: List of contacts in GeneralEmail Objects
    private static List<GeneralEmail> CSVToContacts(String filename) throws Exception
    {
        List<GeneralEmail> contacts = new ArrayList<GeneralEmail>();
        String [] nextLine;
        CSVReader reader = new CSVReader(new FileReader(filename), '\t');
        while ((nextLine = reader.readNext()) != null) {
            contacts.add(new GeneralEmail(
                    nextLine[0],
                    nextLine[2]
                    )
            );
        }
        return contacts;
    }

    //Input: contacts to put into database
    //Output: nothing
    private static void writeContactsToDB(List<GeneralEmail> contacts) throws SQLException
    {
        for(GeneralEmail c : contacts)
        {
            if(!GeneralEmailDao.exists(c))
            {
                System.out.println("Email Added!");
                GeneralEmailDao.insert(null,c);
            }
            else
                System.out.println("Email already exisits.");
        }
    }
}
