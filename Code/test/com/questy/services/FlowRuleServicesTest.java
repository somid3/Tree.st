package com.questy.services;

import com.questy.domain.Question;
import com.questy.enums.AnswerVisibilityEnum;
import com.questy.utils.DatabaseUtils;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlowRuleServicesTest {

    private Integer networkId = null;

    @Test
    public void testSimpleNetwork() throws SQLException {

        networkId = FlowRuleUnitTestSimpleNetwork.createNetwork();

        System.out.println("Created network id: " + networkId);

        /**
         * Answering the simple network for the first time
         */
        simpleNetworkRunThrough();

        /**
         * Answering the simple network *again*
         */
        simpleNetworkRunThroughAgainRoot1();
    }


    private void simpleNetworkRunThrough() throws SQLException {

        {
            // Answering Root 1
            List<FlowRuleUnitTestSimpleNetwork.Options> selectedOptions = new ArrayList<FlowRuleUnitTestSimpleNetwork.Options>();
            selectedOptions.add( FlowRuleUnitTestSimpleNetwork.Options.OPTION_B );
            answerAndTest(FlowRuleUnitTestSimpleNetwork.Questions.ROOT_1, selectedOptions);
        }

        {
            // Answering Question 1, with options B and C
            List<FlowRuleUnitTestSimpleNetwork.Options> selectedOptions = new ArrayList<FlowRuleUnitTestSimpleNetwork.Options>();
            selectedOptions.add( FlowRuleUnitTestSimpleNetwork.Options.OPTION_B );
            selectedOptions.add( FlowRuleUnitTestSimpleNetwork.Options.OPTION_C );
            answerAndTest(FlowRuleUnitTestSimpleNetwork.Questions.QUESTION_1, selectedOptions);
        }

        {
            List<FlowRuleUnitTestSimpleNetwork.Options> selectedOptions = new ArrayList<FlowRuleUnitTestSimpleNetwork.Options>();
            selectedOptions.add( FlowRuleUnitTestSimpleNetwork.Options.OPTION_A );
            answerAndTest(FlowRuleUnitTestSimpleNetwork.Questions.QUESTION_1B, selectedOptions);
        }

        {
            List<FlowRuleUnitTestSimpleNetwork.Options> selectedOptions = new ArrayList<FlowRuleUnitTestSimpleNetwork.Options>();
            selectedOptions.add( FlowRuleUnitTestSimpleNetwork.Options.OPTION_C );
            answerAndTest(FlowRuleUnitTestSimpleNetwork.Questions.QUESTION_1C, selectedOptions);
        }

        {
            List<FlowRuleUnitTestSimpleNetwork.Options> selectedOptions = new ArrayList<FlowRuleUnitTestSimpleNetwork.Options>();
            selectedOptions.add( FlowRuleUnitTestSimpleNetwork.Options.OPTION_C );
            answerAndTest(FlowRuleUnitTestSimpleNetwork.Questions.QUESTION_2, selectedOptions);
        }

        {
            List<FlowRuleUnitTestSimpleNetwork.Options> selectedOptions = new ArrayList<FlowRuleUnitTestSimpleNetwork.Options>();
            selectedOptions.add( FlowRuleUnitTestSimpleNetwork.Options.OPTION_B );
            answerAndTest(FlowRuleUnitTestSimpleNetwork.Questions.ROOT_2, selectedOptions);
        }

        {
            Integer nextQuestion = FlowRuleServices.getNextQuestionRef(FlowRuleUnitTestSimpleNetwork.USERID, networkId);
            Assert.assertEquals(nextQuestion, null);
        }
    }

    private void simpleNetworkRunThroughAgainRoot1() throws SQLException {

        {
            Integer nextQuestionRef = FlowRuleUnitTestSimpleNetwork.Questions.ROOT_1.getQuestion().getRef();
            Question question = QuestionServices.getByNetworkIdAndRef(networkId, nextQuestionRef);

            // Answering the question with the option references
            List<Integer> selectedOptions = new ArrayList<Integer>();
            selectedOptions.add( question.findOptionByText( FlowRuleUnitTestSimpleNetwork.Options.OPTION_A.getText() ).getRef() );
            selectedOptions.add( question.findOptionByText( FlowRuleUnitTestSimpleNetwork.Options.OPTION_C.getText() ).getRef() );

            AnswerServices.answer(
                FlowRuleUnitTestSimpleNetwork.USERID,
                networkId,
                nextQuestionRef,
                selectedOptions,
                AnswerVisibilityEnum.PROTECTED);

        }

        {
            List<FlowRuleUnitTestSimpleNetwork.Options> selectedOptions = new ArrayList<FlowRuleUnitTestSimpleNetwork.Options>();
            selectedOptions.add( FlowRuleUnitTestSimpleNetwork.Options.OPTION_B );
            selectedOptions.add( FlowRuleUnitTestSimpleNetwork.Options.OPTION_C );
            answerAndTest(FlowRuleUnitTestSimpleNetwork.Questions.QUESTION_1, selectedOptions);
        }

        {
            List<FlowRuleUnitTestSimpleNetwork.Options> selectedOptions = new ArrayList<FlowRuleUnitTestSimpleNetwork.Options>();
            selectedOptions.add( FlowRuleUnitTestSimpleNetwork.Options.OPTION_A );
            answerAndTest(FlowRuleUnitTestSimpleNetwork.Questions.QUESTION_1B, selectedOptions);
        }

        {
            List<FlowRuleUnitTestSimpleNetwork.Options> selectedOptions = new ArrayList<FlowRuleUnitTestSimpleNetwork.Options>();
            selectedOptions.add( FlowRuleUnitTestSimpleNetwork.Options.OPTION_C );
            answerAndTest(FlowRuleUnitTestSimpleNetwork.Questions.QUESTION_1C, selectedOptions);
        }

        {
            List<FlowRuleUnitTestSimpleNetwork.Options> selectedOptions = new ArrayList<FlowRuleUnitTestSimpleNetwork.Options>();
            selectedOptions.add( FlowRuleUnitTestSimpleNetwork.Options.OPTION_B );
            answerAndTest(FlowRuleUnitTestSimpleNetwork.Questions.QUESTION_2, selectedOptions);
        }

        {
            Integer nextQuestion = FlowRuleServices.getNextQuestionRef(FlowRuleUnitTestSimpleNetwork.USERID, networkId);
            Assert.assertEquals(nextQuestion, null);
        }
    }


    private void answerAndTest(
            FlowRuleUnitTestSimpleNetwork.Questions questionShouldBe,
            List<FlowRuleUnitTestSimpleNetwork.Options> answerWithOptions) throws SQLException {

        // Checking next question
        Integer nextQuestionRef = FlowRuleServices.getNextQuestionRef(FlowRuleUnitTestSimpleNetwork.USERID, networkId);


        if (nextQuestionRef == null)
            return;

        Question question = QuestionServices.getByNetworkIdAndRef(networkId, nextQuestionRef);
        Assert.assertEquals(questionShouldBe.getText(), question.getText());

        // Answering the question with the option references
        List<Integer> selectedOptions = new ArrayList<Integer>();
        for (FlowRuleUnitTestSimpleNetwork.Options option : answerWithOptions) {
            selectedOptions.add( question.findOptionByText(option.getText()).getRef() );
        }

        AnswerServices.answer(
            FlowRuleUnitTestSimpleNetwork.USERID,
            networkId,
            nextQuestionRef,
            selectedOptions,
            AnswerVisibilityEnum.PROTECTED);

    }
}
