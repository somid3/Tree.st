package com.questy;

import com.questy.admin.AdminServices;
import com.questy.admin.Foods;
import com.questy.admin.marketing.GeneralEmailSender;
import com.questy.dao.UserSessionDao;
import com.questy.dao.UserToSmartGroupDao;
import com.questy.services.cron.CronServices;
import com.questy.utils.DateUtils;
import com.questy.utils.Vars;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OmidsSandbox {

    private static Random randomGenerator = new Random();

    public static void main(String[] args) throws Exception {

//        System.out.println(
//            UserToSmartGroupDao.deleteInactiveByNetworkId(null, 2003)
//        );


//        Foods.create(3);




//        GeneralEmailSender.CVSToDatabase("/Users/omid/Desktop/scrapedaddresses/Universities.csv");
        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Sales/2500-assoc.csv");





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
