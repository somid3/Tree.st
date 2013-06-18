package com.questy.admin.networks;

import com.questy.dao.NetworkAlphaSettingDao;
import com.questy.dao.NetworkDao;
import com.questy.dao.QuestionDao;
import com.questy.domain.Question;
import com.questy.domain.QuestionOption;
import com.questy.enums.AnswerVisibilityEnum;
import com.questy.enums.NetworkAlphaSettingEnum;
import com.questy.services.FlowRuleServices;
import com.questy.services.QuestionOptionServices;
import com.questy.services.QuestionServices;

import java.util.HashMap;
import java.util.Map;

public class MIT100K {

    public static void main(String[] args) throws Exception {

        create();
    }

    public static void create() throws Exception {

        Integer userId = 3;

        // Most recently question that was added
        Integer addedQuestionRef = null;
        Question addedQuestion = null;

        // Questions
        Question flowQuestion = null;
        QuestionOption flowOption = null;

        // All questions in the network
        Map<String, Question> questions = new HashMap<String, Question>();

        // Create network
        Integer networkId = NetworkDao.insert(null, "MIT 100K Competition");

        // Adding start message
        NetworkAlphaSettingDao.deleteByNetworkIdAndSetting(null, networkId, NetworkAlphaSettingEnum.START_MESSAGE);
        NetworkAlphaSettingDao.insert(null, networkId, NetworkAlphaSettingEnum.START_MESSAGE, "Meet and collaborate with other entrepreneurs, investors, journalists, and engineers involved in the MIT 100K Competition.");

        // Adding url path
        NetworkAlphaSettingDao.deleteByValue(null, NetworkAlphaSettingEnum.URL_PATH, "100k");
        NetworkAlphaSettingDao.insert(null, networkId, NetworkAlphaSettingEnum.URL_PATH, "100k");

        // Add question with options and flow rule
        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Currently you are a(n)?", 30, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                {
                    "Entrepreneur",
                    "Investor",
                    "Journalist",
                    "Service Provider"
                });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("you", addedQuestion);
        }

            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "As an entrepreneur, what best describes you?", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "I am looking for co-founders",
                        "I am looking for ideas",
                        "I am already working on a venture"
                    });
                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                flowQuestion = questions.get("you");
                flowOption = flowQuestion.findOptionByText("Entrepreneur");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("entre", addedQuestion);
            }

                {
                    // Adding question
                    addedQuestionRef = QuestionServices.insert(userId, networkId, "What is the name of the venture you're working on? (Include a dash and year founded)", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                    // Adding options
                    addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                    QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                            "Tree.st - 2013"
                        });
                    addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                    // Adding flow rules
                    flowQuestion = questions.get("entre");
                    flowOption = flowQuestion.findOptionByText("I am already working on a venture");
                    FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                    // Adding question to list
                    questions.put("venture", addedQuestion);
                }


            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "As a journalist, what news agency do you work with?", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "MIT's The Tech",
                        "MIT Trust Center"
                    });
                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                flowQuestion = questions.get("you");
                flowOption = flowQuestion.findOptionByText("Journalist");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("journal", addedQuestion);
            }

            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "What type of investor are you?", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Angel Investor",
                        "Venture Investor",
                        "Private Equity Investor"
                    });
                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                flowQuestion = questions.get("you");
                flowOption = flowQuestion.findOptionByText("Investor");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("investor1", addedQuestion);
            }


            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "What is the average range of your investments?", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Less than $100K",
                        "$100K - $500K",
                        "$500K - $1M",
                        "$1M - $5M",
                        "$5M - $10M",
                        "$10M - $20M",
                    });
                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                flowQuestion = questions.get("you");
                flowOption = flowQuestion.findOptionByText("Investor");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("investor2", addedQuestion);
            }

            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "How many investments did you do last year?", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "None",
                        "One",
                        "Two",
                        "Three",
                        "Four",
                        "Over five"
                    });
                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                flowQuestion = questions.get("you");
                flowOption = flowQuestion.findOptionByText("Investor");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("investor3", addedQuestion);
            }

            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "What is the name of the investment firm/band you're affiliated with?", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Matrix Partners",
                        "Greylock Partners",
                        "Bain Capital"
                    });
                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                flowQuestion = questions.get("you");
                flowOption = flowQuestion.findOptionByText("Investor");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("investor4", addedQuestion);
            }

            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "What services do you provide?", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Legal",
                        "Marketing",
                        "Sales",
                        "Product Development",
                        "Web Design",
                        "Software Engineering",
                        "Video Services"
                    });
                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                flowQuestion = questions.get("you");
                flowOption = flowQuestion.findOptionByText("Service Provider");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("service1", addedQuestion);
            }

            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "Are you willing to provide your services on a deferred payment plan?", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Yes",
                        "No"
                    });
                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                flowQuestion = questions.get("you");
                flowOption = flowQuestion.findOptionByText("Service Provider");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("service2", addedQuestion);
            }

    }



}
