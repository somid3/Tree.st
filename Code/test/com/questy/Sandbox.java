package com.questy;

import com.questy.admin.AdminServices;
import com.questy.admin.MIT;
import com.questy.dao.UserToSmartGroupDao;
import com.questy.domain.UserToSmartGroup;
import com.questy.helpers.SqlLimit;
import com.questy.services.cron.CronServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sandbox {

    private static Random randomGenerator = new Random();

    public static void main(String[] args) throws Exception {

//        UserServices.deleteUserAnswers(3);

//        Integer smartGroupsCount = UserToSmartGroupDao.countByNetworkIdAndUserIdAndMember(null, 2000, 3, true);
//
//        System.out.println(smartGroupsCount);


        List<UserToSmartGroup> activeUserToSmartGroups = UserToSmartGroupDao.getActiveByNetworkIdAndUserId(null, 2000, 3, SqlLimit.ALL);

        System.out.println(activeUserToSmartGroups.size());


//        EmailServices.firstPhotoUpload(3);



//        Integer nextQuestionRef = FlowRuleServices.getNextQuestionRef(3, 2002);
//
//        System.out.println(nextQuestionRef);


//        Integer networkId = NetworkAlphaSettingEnum.URL_PATH.getNetworkIdByValue("mit");

//        System.out.println(networkId);

//
//          Integer networkId = 2003;
//          Connection conn = null;
//          Integer userId = 3;
//
//
//        Integer perMinute = NetworkIntegerSettingEnum.SHARED_COMMENTS_PER_HOUR.getValueByNetwork(networkId);
//        Integer lastMinute = SharedCommentDao.countByNetworkIdAndUserIdAndCreatedAfter(conn, networkId, userId, DateUtils.hoursAgo(1));
//        if (lastMinute >= perMinute) throw new UIException("Limit: " + perMinute + " comments per minute");
//
//          System.out.println(perMinute);
//          System.out.println(lastMinute);
//
//         if (lastMinute > perMinute) throw new UIException("Limit is " + perMinute + "views per minute");





//        CronServices.notifyNewNonPrivateSmartGroupMembers(EmailNotificationRateEnum.EVERY_HOUR);



//        String mock = "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)";
//        System.out.println(BrowserAcceptanceEnum.Browser.getStatus(mock));

////        UASparser uaParser = new CachingOnlineUpdateUASparser();
//        UASparser uaParser = new UASparser("/WEB-INF/uas_20120827-01.ini");
//
//        UserAgentInfo uai = uaParser.parse(mock);



       // CronServices.calledHourlyPopulateSmartGroups();
//
//        System.out.println(EmailNotificationRateEnum.INSTANTLY.getBoundaryDate());
//        System.out.println(EmailNotificationRateEnum.EVERY_HOUR.getBoundaryDate());
//        System.out.println(EmailNotificationRateEnum.EVERY_DAY.getBoundaryDate());
//

//        CronServices.notifyNewNonPrivateSmartGroupMembers(EmailNotificationRateEnum.EVERY_HOUR);



//        Tuple<UserLinkDirectionEnum, Integer> directionAndPoints = UserLinkServices.linkUsers(2003, 3, 2316);
//
//        System.out.println(directionAndPoints.getX());
//        System.out.println(directionAndPoints.getY());


//        List<UserToSmartGroup> usersToSmartGroup = UserToSmartGroupDao.getByNetworkIdAndSmartGroupRef(null, 2003, 14);
//
//        int i = 0;
//        for (UserToSmartGroup userToSmartGroup : usersToSmartGroup) {
//
//            // Is the user a member or has made the smart group a favorite
//            if (userToSmartGroup.isFavoriteOrMember()) {
//                i = i+ 1;
//                System.out.println(i);
//            }
//
//        }


//        EmailServices.newSharedItemForSmartGroup(2003, 7, 2);

//        EmailServices.newSharedItemForSmartGroup(2003, 14, 1);

//        EmailServices.newSharedItemForSmartGroup(2003, 10, 3);



//          Vars.sendEmails = true;
//        EmailServices.photoUpload(3);




//
//        AmazonMailSender ser = new AmazonMailSender();
//        ser.setFromName("Omid");
//        ser.setFromEmail("help@tree.st");
//        ser.addRecipient("omid@mit.edu");
//        ser.setSubject("html message");
//        ser.setMessageMine(EmailMimeEnum.HTML_UTF8);
//        ser.setMessageText("<table width=\"100%\"><tr><td><b>Omid was here</b></td></tr></table>");
//
//        // Sending the email
//        Thread thread = new Thread(ser);
//        thread.start();

//        Vars.sendEmails = false;



//        System.out.println(
//                EmailNotificationServices.getRate(2010, 0, 2206, NetworkEventEnum.USER_LINK_CREATION)
//
//        );

//        System.out.println(
//            EmailNotificationRateEnum.next(EmailNotificationRateEnum.NEVER)
//        );

//        UserToSmartGroupServices.toggleState(UserToSmartGroupStateEnum.FAVORITE, 2003, 3, 10);



    }





    public static void createNetworks() throws Exception {

        MIT.create();
    }

    public static void populate() throws Exception {

        // Total number of users and actions
        Integer totalUsers = 100;
        Integer thisPerUsers = 10;

        // List of networks to sign up users to
        List<Integer> networkIds = new ArrayList<Integer>();
        networkIds.add(1);


        // Create users
        AdminServices.populateUsers(totalUsers, networkIds);

        // Answer randomly
        AdminServices.randomAnswers(totalUsers, thisPerUsers);

        // Refresh all smart groups
        CronServices.calledHourlyPopulateSmartGroups();

        // Write shared items and comments
        AdminServices.randomSharedItemsAndComments(totalUsers, thisPerUsers, 3);

        // Adding connections
        AdminServices.randomConnections(totalUsers, thisPerUsers);

    }


}
