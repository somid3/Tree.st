package com.questy.services;

import com.questy.dao.SmartGroupDao;
import com.questy.dao.SmartGroupResultDao;
import com.questy.enums.AnswerVisibilityEnum;
import com.questy.helpers.UserScore;
import com.questy.helpers.UserScores;
import com.questy.xml.query.CriteriaXml;
import com.questy.xml.query.OptionXml;
import com.questy.xml.query.QueryXml;
import com.questy.xml.query.QuestionXml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public class QueryServices extends ParentService {

    private static double QUESTION_SCORE_SCALE = 100000d;
    private static double QUERY_SCORE    = 10000000000d;

    public static UserScores createScores(Integer hostingNetworkId, Integer smartGroupRef, QueryXml queryXml) throws SQLException {

        if (queryXml == null) return null;

        // Currently non-transactional
        Connection conn = null;

        // Calculate user scores
        UserScores userScores = calculateUserScores(hostingNetworkId, queryXml);
        List<UserScore> queryMetUserScores = userScores.getQueryMetUserScores();

        // Delete previous user results
        SmartGroupResultDao.deleteByNetworkIdAndRef(conn, hostingNetworkId, smartGroupRef);

        // Insert all user results
        for (UserScore userScore: queryMetUserScores)
            SmartGroupResultDao.insert(conn, hostingNetworkId, smartGroupRef, userScore);

        // Create user to smart group mappings
        UserToSmartGroupServices.createSmartGroupMappings(hostingNetworkId, smartGroupRef);

        // Update smart group with refresh details
        SmartGroupDao.updateRefreshedOnByNetworkIdAndRef(null, hostingNetworkId, smartGroupRef, queryMetUserScores.size());

        return userScores;
    }

    private static UserScores calculateUserScores(Integer hostingNetworkId, QueryXml queryXml) throws SQLException {

        UserScores userScores = new UserScores();
        CriteriaXml criteria = null;
        if (queryXml.hasQuestions()) {

            // Looping over all questions in the query
            for (QuestionXml questionXml : queryXml.getQuestions()) {

                // Does this question have a options set?
                if (!questionXml.hasOptions()) continue;

                // Yes, then loop over all options
                for (OptionXml option : questionXml.getOptions()) {

                    // Does this option have any criteria set?
                    if (!option.hasCriteria()) continue;

                    // Yes, what kind of criteria is being analyzed?
                    criteria = option.getCriteria();
                    switch (criteria.getType()) {

                        case IS_SET:
                            criteriaIsSet(userScores, hostingNetworkId, questionXml.getNetworkId(), questionXml.getRef(), option.getRef(), option.getScore());
                            break;
                        default:
                            throw new RuntimeException("Unknown criteria type " + criteria.getType());
                    }

                }

                // Recording if all options for a question have been met
                isQuestionMet(userScores, questionXml);
            }

            // Recording if all questions for a query have been met
            isQueryMet(userScores, queryXml.getQuestions().size());

        }

        return userScores;
    }

    private static void isQuestionMet(UserScores userScores, QuestionXml questionXml) {

        // Looping over all users
        for (UserScore userScore : userScores.getUserScores()) {

            if (

                /* Does the user match at least one option in the question */
                (userScore.getTotalOptionsMet() >= 1)

            ) {

                // Yes, increment score
                userScore.incrementScore(questionXml.getScore() * QUESTION_SCORE_SCALE);

                // Increment total questions met
                userScore.incrementQuestionsMet();

                // Document the flare
                userScore.appendFlare( generateFlare(questionXml.getNetworkId(), questionXml.getRef(), null) );
            }

            // Resetting option count on user score to analyze next question
            userScore.resetOptionsMet();
        }

    }


    private static void isQueryMet(UserScores userScores, Integer totalQuestions) {

        // Looping over all users
        for (UserScore userScore : userScores.getUserScores()) {

            // Are all the questions for the query met?
            if (userScore.getTotalQuestionsMet() >= totalQuestions) {

                // Yes, increment score
                userScore.incrementScore(QUERY_SCORE);

                // Document that query has been met
                userScore.setQueryMet(true);
            }
        }
    }


    static UserScores criteriaIsSet(
            UserScores userScores,
            Integer hostingNetworkId,
            Integer questionNetworkId,
            Integer questionRef,
            Integer optionRef,
            Integer optionScore) throws SQLException {

        Connection conn = start(null);

        String sql =
            "select " +
                "`visibility`, " +
                "`user_id`, " +
                "`created_on` " +
            "from " +
                "`active_answers` " +
            "where " +
                "`network_id` = ? " +
                "and `question_ref` = ? " +
                "and `option_ref` = ? " +

                // Ensures all results are members of the network that hosts the smart group
                "and `user_id` in (select `user_id` from `users_to_networks` where `network_id` = ?);";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, questionNetworkId);
        ps.setInt(2, questionRef);
        ps.setInt(3, optionRef);
        ps.setInt(4, hostingNetworkId);

        ResultSet rs = ps.executeQuery();

        Integer userId = null;
        AnswerVisibilityEnum visibility = null;
        Date createdOn = null;
        String flare = null;
        while (rs.next()) {

            userId = rs.getInt("user_id");
            visibility = AnswerVisibilityEnum.getById(rs.getInt("visibility"));
            createdOn = rs.getTimestamp("created_on");
            flare = generateFlare(questionNetworkId, questionRef, optionRef);
            userScores.isSetCriteria(userId, optionScore, visibility, createdOn, flare);

        }

        end(conn, ps, rs);
        return userScores;
    }


    /**
    * Creates the string that documents all the options and questions that have been met
    */
    public static String generateFlare(Integer networkId, Integer questionRef, Integer optionRef) {

        StringBuilder buf = new StringBuilder();
        buf.append(networkId);
        buf.append('-');
        buf.append(questionRef);

        if (optionRef != null) {
            buf.append('-');
            buf.append(optionRef);
        }

        return buf.toString();
    }

    public static String generateHtmlId(Integer networkId, Integer questionRef, Integer optionRef) {

        return "hid" + generateFlare(networkId, questionRef, optionRef);

    }

}
