package com.questy.services;

import com.questy.dao.*;
import com.questy.domain.*;
import com.questy.enums.RoleEnum;
import com.questy.helpers.SqlLimit;
import com.questy.utils.StringUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServices extends ParentService  {

    /**
     * Attempts to log a user in by creating a new user session. A single user
     * can have many user sessions. If user can not be found, null is returned
     */
    public static UserSession authenticateAndCreateSession(String email, String providedPasswordHash, String httpAgent, Boolean persistent) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving user
        User user =  UserDao.getByEmailAndPasswordHash(conn, email, providedPasswordHash);

        UserSession out = null;

        // Are the credentials correct?
        if (user != null) {

            // Create session checksum
            String checksum = StringUtils.random();

            // Create user session
            UserSessionDao.insert(conn, user.getId(), checksum, httpAgent, persistent);
            out = UserSessionDao.getByUserIdAndChecksum (conn, user.getId(), checksum);

        }

        return out;
    }

    public static boolean checkAuthenticity(Integer userId, String sessionChecksum, String httpAgent) throws SQLException {

        // Validating
        if (userId == null) return false;
        if (sessionChecksum == null) return false;

        // Currently non-transactional
        Connection conn = null;

        Integer updates =  UserSessionDao.updateDetailsByUserIdAndChecksum(conn, userId, sessionChecksum, httpAgent);

        // True if only one update occurred
        return updates == 1;
    }


    // TODO: this does not belong here, create other class
    public static void deleteUserAnswers(Integer userId) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        List<Network> networks = NetworkServices.getByUserId(userId, RoleEnum.MEMBER);

        List<Answer> answers = null;
        Question question = null;
        Integer optionsCount = null;
        List<AnswerOption> options = null;
        List<Integer> optionRefs = new ArrayList<Integer>();
        for (Network n : networks) {

            answers = AnswerDao.getLastByUserIdAndNetworkId(conn, userId, n.getId(), SqlLimit.ALL);

            for (Answer answer : answers) {

                // Loading answer options
                options = AnswerOptionDao.getByUserIdAndNetworkIdAndAnswerRef(conn, answer.getUserId(), answer.getNetworkId(), answer.getRef());

                // Deleting answers
                AnswerDao.deleteByNetworkIdAndUserIdAndRef(conn, answer.getNetworkId(), userId, answer.getRef());
                optionsCount = AnswerOptionDao.deleteByNetworkIdAndUserIdAndId(conn, answer.getNetworkId(), userId, answer.getRef());
                ActiveAnswerDao.deleteByNetworkIdAndUserId(conn, answer.getNetworkId(), userId, answer.getQuestionRef());

                // Subtract count from question
                question = QuestionDao.getByNetworkIdAndRef(conn, answer.getNetworkId(), answer.getQuestionRef());

                QuestionDao.updateTotalAnswersAndTotalOptionAnswers(
                        conn,
                        question.getNetworkId(),
                        question.getRef(),
                        (question.getTotalAnswers() - 1),
                        (question.getTotalOptionAnswers() - optionsCount)
                );

                // Subtract count from question options
                {
                    // Clearing previous option values
                    optionRefs.clear();

                    // Adding all option values to subtract count
                    for (AnswerOption option : options)
                        optionRefs.add(option.getOptionRef());

                    QuestionOptionDao.decreaseTotalAnswers(conn, question.getNetworkId(), question.getRef(), optionRefs);
                }
            }
        }
    }

}
