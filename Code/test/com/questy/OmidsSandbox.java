package com.questy;

import com.questy.admin.AdminServices;
import com.questy.dao.NetworkAlphaSettingDao;
import com.questy.dao.PasswordResetDao;
import com.questy.dao.QuestionOptionDao;
import com.questy.dao.UserDao;
import com.questy.domain.QuestionOption;
import com.questy.enums.NetworkAlphaSettingEnum;
import com.questy.services.NetworkServices;
import com.questy.services.QuestionOptionServices;
import com.questy.services.QuestionServices;
import com.questy.services.cron.CronServices;
import com.questy.utils.DateUtils;
import com.questy.utils.StringUtils;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OmidsSandbox {

    private static Random randomGenerator = new Random();

    public static void main(String[] args) throws Exception {


        NetworkServices.toggleRemove(2000, 3);

//        List<Integer> users = new ArrayList<Integer>();
//        users.add(2814);
//        users.add(2815);
//        users.add(2816);
//        users.add(2818);
//        users.add(2819);
//        users.add(2820);
//        users.add(2821);
//        users.add(2822);
//        users.add(2823);
//        users.add(2824);
//        users.add(2825);
//        users.add(2826);
//        users.add(2827);
//        users.add(2828);
//        users.add(2830);
//        users.add(2831);
//        users.add(2832);
//        users.add(2833);
//        users.add(2834);
//        users.add(2835);
//        users.add(2836);
//        users.add(2837);
//        users.add(2841);
//        users.add(2842);
//        users.add(2843);
//        users.add(2845);
//        users.add(2846);
//        users.add(2847);
//        users.add(2848);
//        users.add(2849);
//        users.add(2850);
//        users.add(2851);
//        users.add(2852);
//        users.add(2854);
//        users.add(2855);
//        users.add(2856);
//
//        for (Integer user : users) {
//
//            System.out.println("Removing user " + user);
//            NetworkServices.deleteUserFromNetwork(user, 1);
//            NetworkServices.deleteUserFromNetwork(user, 2);
//
//        }


//        PasswordResetDao.deleteByCreatedBefore(null, DateUtils.daysAgo(2));


//        PrintStream sysout = new PrintStream(System.out, true, "UTF-8");
//
//        sysout.println(new String("??".getBytes(), "UTF-8"));


//        List<QuestionOption> options = QuestionOptionDao.getByNetworkIdAndQuestionRef(null, 2000, 1);
//        options = QuestionOptionServices.sortByText(options);
//
//        for (QuestionOption option : options)
//            System.out.println(option.getText());
//


//        System.out.println(NetworkAlphaSettingEnum.UI_HEADER_BACKGROUND_COLOR.getValueByNetworkId(2000));
//

        List<String> qualities = new ArrayList<String>();
        String path = "arcem";

        NetworkAlphaSettingDao.deleteByValue(null, NetworkAlphaSettingEnum.URL_PATH, path);

        qualities.add(
            "My gender is:\n" +
            "Male\n" +
            "Female\n" +
            "Decline to provide");

        qualities.add(
            "To what have extent have you supported the American Red Cross?\n" +
            "Volunteered\n" +
            "Donated money\n" +
            "Donated blood\n" +
            "Donated supplies (books, blankets, clothes, etc.)\n" +
            "Donated other services (consulting, nursing, etc.)\n" +
            "Took a class (i.e.: lifeguarding, EMT, etc.)");

        qualities.add(
            "I have been with the American Red Cross for the following number of years:\n" +
            "Less than 1 year\n" +
            "1 to 2 years\n" +
            "2 to 3 years\n" +
            "3 to 4 years\n" +
            "4 to 5 years\n" +
            "5 to 10 years\n" +
            "More than 10 years\n");

        qualities.add(
            "I have the following hobbies and interests?\n" +
            "Aerobics\n" +
            "Acting\n" +
            "Amateur Radio\n" +
            "Antiques\n" +
            "Aquariums\n" +
            "Archery\n" +
            "Architecture\n" +
            "Archaeology\n" +
            "Aromatherapy\n" +
            "Astrology\n" +
            "Astronomy\n" +
            "Autographs\n" +
            "Badminton\n" +
            "Badge Collecting\n" +
            "Baking\n" +
            "Ballet\n" +
            "Baseball\n" +
            "Base Jumping\n" +
            "Basketball\n" +
            "Bee Keeping\n" +
            "Bird Keeping\n" +
            "Body Building\n" +
            "Books\n" +
            "Bottle Tops Collecting\n" +
            "Bowls\n" +
            "Bridge\n" +
            "Bungee Jumping\n" +
            "Butterflies\n" +
            "Camping\n" +
            "Canoeing\n" +
            "Caravaning\n" +
            "Cats\n" +
            "Caving\n" +
            "Ceramics\n" +
            "Chess\n" +
            "Classic Cars\n" +
            "Climbing\n" +
            "Cooking\n" +
            "Coin Collecting\n" +

            "Weather Forecasting\n" +
            "Wind Surfing\n" +
            "Wine Making\n" +
            "Wall Art\n" +
            "Water Skiing\n" +
            "Wood Working\n" +
            "YoYo\n" +
            "Yoga");

        qualities.add(
            " I live in the following town in Massachusetts:\n" +
            "Abington\n" +
            "Acton\n" +
            "Acushnet\n" +
            "Adams\n" +
            "Agawam\n" +

            "Winchester\n" +
            "Windsor\n" +
            "Winthrop\n" +
            "Woburn\n" +
            "Worcester\n" +
            "Worthington\n" +
            "Wrentham\n" +
            "Yarmouth\n");

        qualities.add(
            "I am in the following age range:\n" +
            "5 to 10 years\n" +
            "10 to 12 years\n" +
            "13 to 17 years\n" +
            "18 to 20 years\n" +
            "21 to 29 years\n" +
            "30 to 30 years\n" +
            "40 to 49 years\n" +
            "50 to 59 years\n" +
            "60 or more years\n" +
            "Decline to provide");

        qualities.add(
            "My blood type is (note, you can answer this privately)\n" +
            "A+\n" +
            "A-\n" +
            "B+\n" +
            "B-\n" +
            "AB+\n" +
            "AB-\n" +
            "O+\n" +
            "O-\n" +
            "Decline to provide");

        Integer newNetworkId = NetworkServices.createSimpleNetwork(
            path,
            "American Red Cross of Eastern Massachusetts",
            "Red Cross volunteers, find one another and collaborate on improving your goals and careers!",
            qualities);


        System.out.println(
            newNetworkId
        );
//

//        Integer userId = 3;
//          Integer networkId = 2000;
//          Integer smartGroupRef = 113;
//          Integer sharedItemRef = 4;
//          Integer sharedCommentRef = SharedComment.ANY_SHARED_COMMENT_REF;
//          SharedVoteEnum vote = SharedVoteEnum.UP;
//          SharedVote sharedVote = null;
//          SharedVotable sharedVotable = null;
//
//          // Deleting all inactive votes
//          SharedVoteDao.deleteInactive();
//
//          // Changing vote of shared votable
//          SharedVoteServices.changeVote(
//                  userId,
//                  networkId,
//                  smartGroupRef,
//                  sharedItemRef,
//                  sharedCommentRef,
//                  vote);


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
