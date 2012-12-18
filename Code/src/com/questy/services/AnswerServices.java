package com.questy.services;

import com.questy.dao.*;
import com.questy.domain.Answer;
import com.questy.domain.AnswerOption;
import com.questy.domain.Question;
import com.questy.enums.AnswerVisibilityEnum;
import com.questy.helpers.Tuple;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerServices extends ParentService  {


    public static Tuple<Boolean, Integer> answer (
        Integer userId,
        Integer networkId,
        Integer answeringQuestionRef,
        List<Integer> optionRefs,
        AnswerVisibilityEnum visibility) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving question
        Question answeringQuestion = QuestionDao.getByNetworkIdAndRef(conn, networkId, answeringQuestionRef);

        // Deciding whether this question been answered before by the same user
        Answer lastAnswer = getLastByUserIdAndNetworkIdAndAnsweredQuestionRef(userId, networkId, answeringQuestionRef);
        boolean answeringAgain = lastAnswer != null;

        // Are we answering the question again?
        if (answeringAgain) {

            // Delete all active answers
            ActiveAnswerDao.deleteByUserIdAndNetworkIdAndQuestionRef(conn, userId, networkId, answeringQuestionRef);

            // Reduce answer count from question
            QuestionDao.updateTotalAnswersAndTotalOptionAnswers(
                conn,
                answeringQuestion.getNetworkId(),
                answeringQuestion.getRef(),
                answeringQuestion.getTotalAnswers() - 1,
                answeringQuestion.getTotalOptionAnswers() - lastAnswer.getOptions().size());

            // Reduce answer count from question options
            {
                List<Integer> lastAnswerOptionRefs = new ArrayList<Integer>();
                for (AnswerOption ao : lastAnswer.getOptions()) {lastAnswerOptionRefs.add(ao.getOptionRef());};
                QuestionOptionDao.decreaseTotalAnswers(conn, answeringQuestion.getNetworkId(), answeringQuestion.getRef(), lastAnswerOptionRefs);
            }

            // Retrieving the question again to refresh all counts
            answeringQuestion = QuestionDao.getByNetworkIdAndRef(conn, networkId, answeringQuestionRef);
        }

        // Get next ref for answer
        Integer answerNextRef = AnswerDao.getNextRefByNetworkIdAndUserId(conn, networkId, userId);

        // Insert answer
        AnswerDao.insert(conn, answeringQuestion.getNetworkId(), answeringQuestionRef, userId, answerNextRef, visibility, answeringAgain);

        // Insert answer options
        AnswerOptionDao.insert(conn, answeringQuestion.getNetworkId(), answeringQuestion.getRef(), answerNextRef, userId, optionRefs);

        // Was the visibility of this answer not private?
        if (visibility != AnswerVisibilityEnum.PRIVATE) {

            // Store options in the active answers index
            for(Integer optionRef : optionRefs)
                ActiveAnswerDao.insert(conn, answeringQuestion.getNetworkId(), answeringQuestion.getRef(), optionRef, userId, visibility);
        }

        // Update question answer count
        QuestionDao.updateTotalAnswersAndTotalOptionAnswers(
                conn,
                answeringQuestion.getNetworkId(),
                answeringQuestion.getRef(),
                answeringQuestion.getTotalAnswers() + 1,
                answeringQuestion.getTotalOptionAnswers() + optionRefs.size());

        // Update question option answer count
        QuestionOptionDao.incrementTotalAnswers(conn, answeringQuestion.getNetworkId(), answeringQuestion.getRef(), optionRefs);

        // Update user points -- ONLY if this questions is not being answered again
        Integer pointsAdded = 0;
        if (!answeringAgain) {
            pointsAdded = answeringQuestion.getPoints();
            UserToNetworkDao.incrementPointsByUserIdAndNetworkId(conn, userId, answeringQuestion.getNetworkId(), pointsAdded);
        }

        Tuple<Boolean, Integer> out = new Tuple<Boolean, Integer>(answeringAgain, pointsAdded);

        return out;
    }

    public static Answer getLastByUserIdAndNetworkIdAndAnsweredQuestionRef(Integer userId, Integer networkId, Integer answeredQuestionRef) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Loading answer
        Answer answer = AnswerDao.getLastByUserIdAndNetworkIdAndAnsweredQuestionRef(conn, userId, networkId, answeredQuestionRef);
        if (answer == null) return null;

        // Loading all answer options
        List<AnswerOption> options = AnswerOptionDao.getByUserIdAndNetworkIdAndAnswerRef(conn, userId, networkId, answer.getRef());

        // Setting all dependencies
        answer.setOptions(options);

        return answer;
    }

}
