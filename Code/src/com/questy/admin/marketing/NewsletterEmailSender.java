package com.questy.admin.marketing;

import com.questy.admin.dao.MITEmailDao;
import com.questy.admin.domain.MITEmail;
import com.questy.dao.UserDao;
import com.questy.dao.UserToNetworkDao;
import com.questy.domain.User;
import com.questy.domain.UserToNetwork;
import com.questy.enums.AllMembersViewEnum;
import com.questy.enums.EmailMimeEnum;
import com.questy.enums.RoleEnum;
import com.questy.helpers.SqlLimit;
import com.questy.utils.AmazonMailSender;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class NewsletterEmailSender {

    public static void main (String[] args) throws SQLException {

        sendEmails(2000);

    }

    public static void sendEmails(Integer networkId) throws SQLException {

        // Retrieving users
        List<UserToNetwork> utns = UserToNetworkDao.getByNetworkIdAndLowestRoleOrderedBy(null, networkId, RoleEnum.VISITOR, AllMembersViewEnum.BY_JOINED, SqlLimit.ALL);

        User user = null;

        System.out.println("Total pool: " + utns.size() );

        for (UserToNetwork utn : utns) {

            user = UserDao.getById(null, utn.getUserId());

            sendEmail(user.getFirstName(), user.getEmail().toLowerCase());

        }

    }




    public static void sendEmail(String firstName, String email) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Creating message
        String message = "Hi " + firstName + ",\n" +
            "\n" +
            "Welcome to www.hackpack.org (formally tree.st/MIT) -- the unofficial MIT social search engine. Our vision is to foster collaborations and connect all MIT community members.\n" +
            "\n" +
            "Hackpack will now give you up to $50 for snacks as you hack & brainstorm to solve some of the world's most challenging issues.\n" +
            "\n" +
            "Thanks to a $5,000 grant from MIT's ODGE -- you can now connect with like-minded MIT members and host your very own hackpack.\n" +
            "\n" +
            "Sign up to organize a hackpack today! http://bit.ly/XxQG1I\n" +
            "\n" +
            "Help spread the word,\n" +
            "\n" +
            "Hackpack Team";

        message = message.replaceAll("\\[first_name\\]", firstName);

        // Creating runnable to send email on new thread
        AmazonMailSender ser = new AmazonMailSender();
        ser.setMessageMine(EmailMimeEnum.TEXT_UTF8);
        ser.setFromName("omid@mit.edu");
        ser.setFromEmail("omid@mit.edu");
        ser.addRecipient(email);
        ser.setSubject("hack at MIT + get $50 ...");
        ser.setMessageText(message);

        // Sending the email
        Thread thread = new Thread(ser);
        thread.start();

    }
}
