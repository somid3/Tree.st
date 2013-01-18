package com.questy;

import com.questy.admin.AdminServices;
import com.questy.admin.Foods;
import com.questy.admin.marketing.GeneralEmailSender;
import com.questy.dao.SharedCommentDao;
import com.questy.dao.SharedVoteDao;
import com.questy.dao.UserSessionDao;
import com.questy.dao.UserToSmartGroupDao;
import com.questy.domain.SharedComment;
import com.questy.domain.SharedVote;
import com.questy.enums.NetworkAlphaSettingEnum;
import com.questy.enums.NetworkIntegerSettingEnum;
import com.questy.enums.SharedVoteEnum;
import com.questy.ifaces.SharedVotable;
import com.questy.services.SharedVoteServices;
import com.questy.services.cron.CronServices;
import com.questy.utils.DateUtils;
import com.questy.utils.Vars;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OmidsSandbox {

    private static Random randomGenerator = new Random();

    public static void main(String[] args) throws Exception {


        Integer userId = 3;
          Integer networkId = 2000;
          Integer smartGroupRef = 113;
          Integer sharedItemRef = 4;
          Integer sharedCommentRef = SharedComment.ANY_SHARED_COMMENT_REF;
          SharedVoteEnum vote = SharedVoteEnum.UP;
          SharedVote sharedVote = null;
          SharedVotable sharedVotable = null;

          // Deleting all inactive votes
          SharedVoteDao.deleteInactive();

          // Changing vote of shared votable
          SharedVoteServices.changeVote(
                  userId,
                  networkId,
                  smartGroupRef,
                  sharedItemRef,
                  sharedCommentRef,
                  vote);


//        System.out.println(
//            UserToSmartGroupDao.deleteInactiveByNetworkId(null, 2003)
//        );


//        Foods.create(3);




//        GeneralEmailSender.CVSToDatabase("/Users/omid/Desktop/scrapedaddresses/Universities.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/6600-assoc.csv");

//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/11500-assoc.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/12000-assoc.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/12500-assoc.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/13000-assoc.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/13500-assoc.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/14000-assoc.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/14500-assoc.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/15000-assoc.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/15500-assoc.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/16000-assoc.csv");
//        GeneralEmailSender.CVSToDatabase("/Users/omid/Google Drive/Tree.st (Google Drive)/Marketing/Emails/Associations/16500-assoc.csv");
//




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
