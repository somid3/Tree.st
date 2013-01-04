package com.questy;

import com.questy.admin.AdminServices;
import com.questy.admin.ColorAndFoods;
import com.questy.admin.MIT;
import com.questy.admin.marketing.GeneralEmailSender;
import com.questy.dao.AppResourceDao;
import com.questy.domain.AppResource;
import com.questy.enums.AppEnum;
import com.questy.enums.AppResourceTypeEnum;
import com.questy.enums.EmailNotificationRateEnum;
import com.questy.services.AppResourceServices;
import com.questy.services.cron.CronServices;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OmidsSandbox {

    private static Random randomGenerator = new Random();

    public static void main(String[] args) throws Exception {

        GeneralEmailSender.CVSToDatabase("/Users/omid/Desktop/scrapedaddresses/Universities.csv");


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
