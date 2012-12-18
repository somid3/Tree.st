package com.questy.services;

import com.questy.dao.*;
import com.questy.domain.Answer;
import com.questy.domain.Question;
import com.questy.domain.QuestionOption;
import com.questy.enums.AnswerVisibilityEnum;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class QuestionServices extends ParentService  {

    public static Question getByNetworkIdAndRef(Integer networkId, Integer questionRef) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving question
        Question question = QuestionDao.getByNetworkIdAndRef(conn, networkId, questionRef);
        if (question == null) return null;

        // Retrieving options
        List<QuestionOption> options = QuestionOptionDao.getByNetworkIdAndQuestionRef(conn, networkId, question.getRef());

        // Setting dependencies
        question.setOptions(options);

        return question;
    }

    public static Integer insert(Integer userId, Integer networkId, String text, Integer points, Integer maxSelectedOptions, AnswerVisibilityEnum maxVisibility, AnswerVisibilityEnum defaultVisibility, Boolean allowAddOptions) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving max ref
        Integer maxRef = QuestionDao.getMaxRefByNetworkId(conn, networkId);

        // Creating next ref
        Integer ref = 1;
        if (maxRef != null)
            ref = maxRef + 1;

        // Inserting question
        QuestionDao.insert(conn, userId, networkId, ref, text, points, maxSelectedOptions, maxVisibility, defaultVisibility, allowAddOptions);

        return ref;
    }

    public static void deleteByNetworkIdAndRef (Integer networkId, Integer ref) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Delete question
        QuestionDao.deleteByNetworkIdAndRef(conn,  networkId, ref);

        // Delete question options
        QuestionOptionDao.deleteByNetworkIdAndQuestionRef(conn, networkId, ref);

        // Delete flow rules that contain question ref
        FlowRuleDao.deleteByNetworkIdAndFromQuestionRef(conn, networkId, ref);
        FlowRuleDao.deleteByNetworkIdAndToQuestionRef(conn, networkId, ref);

        // Delete active answers
        ActiveAnswerDao.deleteByNetworkIdAndQuestionRef(conn, networkId, ref);

        // Delete answers
        AnswerDao.deleteByNetworkIdAndAnsweredQuestionRef(conn, networkId, ref);

        // Delete answer options
        AnswerOptionDao.deleteByNetworkIdAndQuestionRef(conn, networkId, ref);
    }


}
