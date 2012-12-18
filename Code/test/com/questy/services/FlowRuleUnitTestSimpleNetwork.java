package com.questy.services;

import com.questy.dao.*;
import com.questy.domain.Question;
import com.questy.domain.QuestionOption;
import com.questy.enums.AnswerVisibilityEnum;
import com.questy.enums.RoleEnum;
import com.questy.utils.DatabaseUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

/**
 * Creates a network with the following decision tree:
 *
 * Root 1
 *  |
 *  +- Question 1
 *  |   |
 *  |   +- If A: Question 1A
 *  |   |
 *  |   +- If B: Question 1B
 *  |   |
 *  |   +- If C: Question 1C
 *  |
 *  +- Question 2
 *
 * Root 2
 */

public class FlowRuleUnitTestSimpleNetwork {

    static Integer USERID = 2;

    enum Questions {
        ROOT_1("Root Question 1"),
        QUESTION_1("Question 1"),
        QUESTION_1A ("Question 1A"),
        QUESTION_1B ("Question 1B"),
        QUESTION_1C ("Question 1C"),
        QUESTION_2 ("Question 2"),
        ROOT_2 ("Root Question 2");

        private String text;
        private com.questy.domain.Question question;

        Questions(String name) {this.text = name;}
        String getText() {return text;}
        void setQuestion (com.questy.domain.Question question) {this.question = question;};
        Question getQuestion () {return question;};
    }

    enum Options {
        OPTION_A ("Option A"),
        OPTION_B ("Option B"),
        OPTION_C ("Option C");

        private String text;

        Options (String text) {this.text = text;}
        String getText() {return text;}
    }

    @Test
    public void testCreateNetwork() throws SQLException {
        createNetwork();
    }

    public static Integer createNetwork () throws SQLException {

        // Generating array of options
        String[] OPTIONS = new String[Options.values().length];
        int i = 0;
        for (Options o : Options.values()) {
            OPTIONS[i] = o.getText();
            i++;
        }

        Connection conn = null;

        // Creating network
        Integer networkId = NetworkDao.insert(conn, "Test Unit Network" + new Date().getTime(), false, "testunitchecksum");

        // Creating questions
        Integer addedQuestionRef = null;
        com.questy.domain.Question addedQuestion = null;
        QuestionOption option = null;

        // Root 1
        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(USERID, networkId, Questions.ROOT_1.getText(), 10, 1, AnswerVisibilityEnum.PROTECTED, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(conn, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(networkId, addedQuestion.getRef(), USERID, OPTIONS);
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());
            Questions.ROOT_1.setQuestion(addedQuestion);

            // Adding flow rules
            FlowRuleServices.insert(
                 networkId,
                 QuestionDao.ROOT_QUESTION_REF,
                 QuestionOptionDao.ANY_OPTION_REF,
                 addedQuestion.getRef());

        }

        // Question 1
        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(USERID, networkId, Questions.QUESTION_1.getText(), 10, 3, AnswerVisibilityEnum.PROTECTED, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(conn, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(networkId, addedQuestion.getRef(), USERID, OPTIONS);
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());
            Questions.QUESTION_1.setQuestion(addedQuestion);

            // Adding flow rules
            FlowRuleServices.insert(
                networkId,
                Questions.ROOT_1.getQuestion().getRef(),
                QuestionOptionDao.ANY_OPTION_REF,
                Questions.QUESTION_1.getQuestion().getRef());
        }

        // Question 1A
        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(USERID, networkId, Questions.QUESTION_1A.getText(), 10, 2, AnswerVisibilityEnum.PROTECTED, AnswerVisibilityEnum.PROTECTED, true);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(conn, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(networkId, addedQuestion.getRef(), USERID, OPTIONS);
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());
            Questions.QUESTION_1A.setQuestion(addedQuestion);

            // Adding flow rules
            option = Questions.QUESTION_1.getQuestion().findOptionByText( Options.OPTION_A.getText() );
            FlowRuleServices.insert(
                networkId,
                Questions.QUESTION_1.getQuestion().getRef(),
                option.getRef(),
                Questions.QUESTION_1A.getQuestion().getRef());
        }

        // Question 1B
        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(USERID, networkId, Questions.QUESTION_1B.getText(), 10, 2, AnswerVisibilityEnum.PROTECTED, AnswerVisibilityEnum.PROTECTED, true);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(conn, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(networkId, addedQuestion.getRef(), USERID, OPTIONS);
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());
            Questions.QUESTION_1B.setQuestion(addedQuestion);

            // Adding flow rules
            option = Questions.QUESTION_1.getQuestion().findOptionByText( Options.OPTION_B.getText() );
            FlowRuleServices.insert(
                    networkId,
                    Questions.QUESTION_1.getQuestion().getRef(),
                    option.getRef(),
                    Questions.QUESTION_1B.getQuestion().getRef());
        }

        // Question 1C
        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(USERID, networkId, Questions.QUESTION_1C.getText(), 10, 2, AnswerVisibilityEnum.PROTECTED, AnswerVisibilityEnum.PROTECTED, true);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(conn, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(networkId, addedQuestion.getRef(), USERID, OPTIONS);
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());
            Questions.QUESTION_1C.setQuestion(addedQuestion);

            // Adding flow rules
            option = Questions.QUESTION_1.getQuestion().findOptionByText( Options.OPTION_C.getText() );
            FlowRuleServices.insert(
                networkId,
                Questions.QUESTION_1.getQuestion().getRef(),
                option.getRef(),
                Questions.QUESTION_1C.getQuestion().getRef());
        }

        // Question 2
        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(USERID, networkId, Questions.QUESTION_2.getText(), 10, 2, AnswerVisibilityEnum.PROTECTED, AnswerVisibilityEnum.PROTECTED, true);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(conn, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(networkId, addedQuestion.getRef(), USERID, OPTIONS);
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());
            Questions.QUESTION_2.setQuestion(addedQuestion);

            // Adding flow rules
            FlowRuleServices.insert(
                networkId,
                Questions.ROOT_1.getQuestion().getRef(),
                QuestionOptionDao.ANY_OPTION_REF,
                Questions.QUESTION_2.getQuestion().getRef());
        }

        // Root 2
        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(USERID, networkId, Questions.ROOT_2.getText(), 10, 1, AnswerVisibilityEnum.PROTECTED, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(conn, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(networkId, addedQuestion.getRef(), USERID, OPTIONS);
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());
            Questions.ROOT_2.setQuestion(addedQuestion);

            // Adding flow rules
            FlowRuleServices.insert(
                networkId,
                QuestionDao.ROOT_QUESTION_REF,
                QuestionOptionDao.ANY_OPTION_REF,
                addedQuestion.getRef());
        }


        System.out.println(networkId);

        // Adding network to test user
        NetworkServices.addUserToSingleNetwork(networkId, USERID, RoleEnum.MEMBER);

        return networkId;
    }
}
