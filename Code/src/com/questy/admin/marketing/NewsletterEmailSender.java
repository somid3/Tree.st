package com.questy.admin.marketing;

import com.questy.dao.UserDao;
import com.questy.dao.UserToNetworkDao;
import com.questy.domain.User;
import com.questy.domain.UserToNetwork;
import com.questy.enums.AllMembersViewEnum;
import com.questy.enums.EmailMimeEnum;
import com.questy.enums.RoleEnum;
import com.questy.helpers.SqlLimit;
import com.questy.utils.AmazonEmailSender;
import com.questy.utils.AmazonMailQueue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
        AmazonEmailSender ams = new AmazonEmailSender();
        ams.setMessageMine(EmailMimeEnum.TEXT_UTF8);
        ams.setFromName("omid@mit.edu");
        ams.setFromEmail("omid@mit.edu");
        ams.addRecipient(email);
        ams.setSubject("hack at MIT + get $50 ...");
        ams.setMessageText(message);

        // Queueing the email
        AmazonMailQueue.queueEmail(ams);
    }
}
