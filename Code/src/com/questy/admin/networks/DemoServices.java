package com.questy.admin.networks;

import com.questy.dao.*;
import com.questy.domain.*;
import com.questy.enums.AnswerVisibilityEnum;
import com.questy.enums.RoleEnum;
import com.questy.enums.SmartGroupVisibilityEnum;
import com.questy.helpers.Tuple;
import com.questy.helpers.UIException;
import com.questy.services.*;
import com.questy.services.cron.CronServices;
import com.questy.utils.Vars;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DemoServices {

    private static boolean log = true;

    private static Random randomGenerator = new Random();
    private static String randomText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce tempus elit eu tellus consectetur laoreet. Phasellus urna libero, sollicitudin quis rutrum id, fringilla id elit. Vestibulum bibendum luctus est euismod blandit. Donec non sem eros. Etiam bibendum cursus ligula eget tristique. Phasellus quis malesuada dui. Nunc turpis enim, tristique ut lacinia non, sodales vitae nisl. Donec viverra lacinia tortor, varius placerat odio sollicitudin eleifend. Nunc et adipiscing lacus. Etiam facilisis purus urna, in pretium purus. Nullam accumsan, lorem id pharetra sodales, nibh elit vulputate tellus, consectetur gravida erat massa eget quam. Cras laoreet magna at justo tristique in hendrerit ante interdum. Etiam quis dui ante. Cras et nisi nisl, quis tincidunt mi.";

    // List of users to add
    public static List<Integer> demoUsersIds = new ArrayList<Integer>();

    // List of demo users
    static {

        demoUsersIds.add(2);
        demoUsersIds.add(3);
        demoUsersIds.add(4);
        demoUsersIds.add(5);
        demoUsersIds.add(6);
        demoUsersIds.add(7);
        demoUsersIds.add(8);
        demoUsersIds.add(9);
        demoUsersIds.add(10);
        demoUsersIds.add(11);
        demoUsersIds.add(12);
        demoUsersIds.add(13);
        demoUsersIds.add(14);
        demoUsersIds.add(15);
        demoUsersIds.add(16);
        demoUsersIds.add(17);
        demoUsersIds.add(18);
        demoUsersIds.add(19);
        demoUsersIds.add(20);
        demoUsersIds.add(21);
        demoUsersIds.add(22);
        demoUsersIds.add(23);
        demoUsersIds.add(24);
        demoUsersIds.add(25);
        demoUsersIds.add(26);
    }

    public static void main (String[] args) throws Exception  {

        demoize(2);

    }

    public static void demoize (Integer networkId) throws Exception {

        // Change application to receive demo data
        Vars.sendEmails = false;
        Vars.enableTimelocks = false;

        // Demo parameters
        Integer answersPerUser = 50;
        Integer totalItems = 100;
        Integer userLinksPerUser = 20;

        // Inject demo data
        // addDemoUsersToNetwork(networkId);
        randomAnswers(networkId, answersPerUser);
        CronServices.calledHourlyPopulateSmartGroups();
        randomSharedItemsAndComments(networkId, totalItems, 5);
        randomConnections(networkId, userLinksPerUser);

        // Reminder to restart server
        log("** Remember to re-start server!");
        log("** Remember to re-start server!");
        log("** Remember to re-start server!");
    }



    /**
     * Populates a network with about 20 members
     *
     * @throws Exception
     */
    public static void addDemoUsersToNetwork(Integer networkId) throws Exception {

        // Adding demo users to network
        for (Integer demoUserId : demoUsersIds)
            NetworkServices.addUserToNetworkWithDependencies(networkId, demoUserId, RoleEnum.MEMBER);

    }








    public static void randomAnswers(Integer networkId, Integer answersPerUser) throws SQLException {

        // How many answers should expect
        AnswerVisibilityEnum visibility = AnswerVisibilityEnum.PROTECTED;
        Integer answeringQuestionRef = null;
        Network network = NetworkDao.getById(null, networkId);

        for (Integer demoUserId : demoUsersIds) {

            // Select random user
            User user = UserDao.getById(null, demoUserId);

            for (int i = 0; i < answersPerUser; i++) {

                // Get user's next question
                answeringQuestionRef = FlowRuleServices.getNextQuestionRef(user.getId(), network.getId());

                DemoServices.log("Answering Question id: " + answeringQuestionRef);

                if (answeringQuestionRef == null) {
                    DemoServices.log("CONTINUE: No next demo user.");
                    DemoServices.log("");
                    continue;
                }

                // Get a random subset of options from the question
                List<Integer> randomOptionRefs = getRandomOptionsRefs(network.getId(), answeringQuestionRef);
                if (randomOptionRefs == null)
                    continue;

                // Submit answer
                Tuple<Boolean, Integer> result = AnswerServices.answer(user.getId(), network.getId(), answeringQuestionRef, randomOptionRefs, visibility);

                DemoServices.log("Again?: " + result.getX());
                DemoServices.log("Points added: " + result.getY());
                DemoServices.log("Answer " + i);
                DemoServices.log("");
            }

        }

    }

    public static void randomSharedItemsAndComments(Integer networkId, Integer totalItems, Integer maxCommentsPerItem) throws SQLException {

        String randomString = null;
        Tuple<Integer, Integer> addedItemResult = null;

        for (int i = 0; i < totalItems; i++) {

            // Select random user
            User user = getRandomDemoUser();

            // Select random smart group of network
            SmartGroup group = getRandomSmartGroupByNetworkId(networkId);
            if (group == null) continue;

            // Write shared item
            randomString = getRandomText(3, 100);
            try { addedItemResult = SharedItemServices.add(networkId, user.getId(), group.getSmartGroupRef(), randomString); }
            catch (UIException uie) { continue; }

            // Write commends for shared item
            Integer totalComments = randomGenerator.nextInt(maxCommentsPerItem);
            for (int u = 0; u < totalComments; u++) {

                // Select random user
                user = getRandomDemoUser();

                // Write shared comment
                randomString = getRandomText(3, 150);

                try { SharedCommentServices.add(networkId, user.getId(), group.getSmartGroupRef(), addedItemResult.getX(), randomString); }
                catch (UIException uie) { continue; }

            }

            DemoServices.log("");

        }

    }

    public static void randomConnections(Integer networkId, Integer userLinksPerUser) throws Exception {

        User fromUser = null;

        for (Integer demoUserId : demoUsersIds) {

            // Setting from user
            fromUser = UserDao.getById(null, demoUserId);

            // Creating a random number of connections for demo user
            for (int i = 0; i < randomGenerator.nextInt(userLinksPerUser); i++) {

                // Getting random user
                User toUser = getRandomDemoUser();

                // Connection both users
                try { UserLinkServices.linkUsers(networkId, fromUser.getId(), toUser.getId()); }
                catch (UIException uie) { continue; }

            }

        }

    }



    private static User getRandomDemoUser() throws SQLException {

        int randomInt = randomGenerator.nextInt(demoUsersIds.size());
        Integer randomDemoUserId = demoUsersIds.get(randomInt);
        User user = UserDao.getById(null, randomDemoUserId);

        return user;
    };


    private static SmartGroup getRandomSmartGroupByNetworkId(Integer networkId) throws SQLException {

        List<SmartGroup> groups = SmartGroupDao.getNonHiddenByNetworkIdAndLowestVisibility(null, networkId, SmartGroupVisibilityEnum.SHARED);

        // Selecting a random smart group
        int randomInt = randomGenerator.nextInt(groups.size());
        SmartGroup group = groups.get(randomInt);

        // Ignore if the "search" smart group was selected
        if (group.getName().equals(SmartGroup.SEARCH_NAME)) return null;

        DemoServices.log("Smart Group ref: " + group.getSmartGroupRef() + " (" + group.getName() + ").");

        return group;
    };

    public static List<Integer> getRandomOptionsRefs(Integer networkId, Integer questionRef) throws SQLException {

        List<QuestionOption> questionOptions = QuestionOptionDao.getByNetworkIdAndQuestionRef(null, networkId, questionRef);

        if (questionOptions.size() == 0)
            return null;

        // Retrieving question to answer
        Question question = QuestionDao.getByNetworkIdAndRef(null, networkId, questionRef);
        int randomNumberOptions = randomGenerator.nextInt(question.getMaxSelectedOptions());
        randomNumberOptions++;

       // Generating random list of options
       List<Integer> randomOptionRefs = new ArrayList<Integer>();
       int randomOptionIndex = 0;
       for (int u = 0; u < randomNumberOptions; u++) {

           randomOptionIndex = randomGenerator.nextInt(questionOptions.size());

           int randomOptionRef = questionOptions.get(randomOptionIndex).getRef();

           if (randomOptionRefs.contains(randomOptionRef)) continue;

           randomOptionRefs.add(randomOptionRef);
       }

       return randomOptionRefs;

    }

    public static String getRandomText(Integer minLength, Integer maxLength) {

        Integer length = randomGenerator.nextInt(maxLength - minLength);
        length = length + minLength;

        Integer randomTextSize = randomText.length();
        Integer maxEnd = randomTextSize - length;

        Integer start = randomGenerator.nextInt(maxEnd);

        return randomText.substring(start, start + length).trim();
    }

    private static void log(String string) {

        if (log) System.out.println(string);
    }

}
