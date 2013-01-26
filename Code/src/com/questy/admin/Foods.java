package com.questy.admin;

import com.questy.dao.*;
import com.questy.domain.Question;
import com.questy.domain.QuestionOption;
import com.questy.enums.AnswerVisibilityEnum;
import com.questy.enums.NetworkAlphaSettingEnum;
import com.questy.enums.RoleEnum;
import com.questy.services.FlowRuleServices;
import com.questy.services.QuestionOptionServices;
import com.questy.services.QuestionServices;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Foods {

    public static void create(Integer userId) throws Exception {

        // Creating network
        Date date = new Date();
        Integer networkId = NetworkDao.insert(null, "Foods");
        String pathName = "foods";

        // Adding user to network
        UserToNetworkDao.insert(null, userId, networkId, RoleEnum.EDITOR);

        // Adding path parameter
        NetworkAlphaSettingDao.deleteByValue(null, NetworkAlphaSettingEnum.URL_PATH, pathName);
        NetworkAlphaSettingDao.insert(null, networkId, NetworkAlphaSettingEnum.URL_PATH, pathName);

        // Adding main message
        NetworkAlphaSettingEnum.START_MESSAGE.setValueByNetworkId(networkId, "Meet others around the world by their favorite food preferences!");
        NetworkAlphaSettingEnum.START_BODY.setValueByNetworkId(networkId, "");

        // Adding manifesto title
        NetworkAlphaSettingEnum.MANIFESTO_TITLE.setValueByNetworkId(networkId, "Purpose & Privacy");
        NetworkAlphaSettingEnum.MANIFESTO_CONTENT.setValueByNetworkId(networkId, "");

        /**
         * Creating questions for network
         */

        Integer addedQuestionRef = null;
        Question addedQuestion = null;
        QuestionOption option = null;
        Map<String, Question> questions = new HashMap<String, Question>();

        // Add question with options and flow rule
        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Are you left handed or right handed?", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Neither ",
                        "Left handed ",
                        "Right handed ",
                        "Both (Ambidextrous)",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("hand", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "What is your favorite color?", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "White  ",
                        "Black  ",
                        "Pink   ",
                        "Red    ",
                        "Orange ",
                        "Brown  ",
                        "Yellow ",
                        "Gray   ",
                        "Green  ",
                        "Cyan   ",
                        "Blue   ",
                        "Violet "
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("color", addedQuestion);
        }

        {
             // Adding question
             addedQuestionRef = QuestionServices.insert(userId, networkId, "What are your two favorite kinds of foods?", 50, 2, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

             // Adding options
             addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
             QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                 {
                     "American",
                     "Thai",
                     "Vietnamese",
                     "French",
                     "Middle Eastern",
                     "Latin",
                     "Spanish",
                     "Chinese",
                 });
             addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

             // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

             // Adding question to list
             questions.put("foods", addedQuestion);
        }

            {
                 // Adding question
                 addedQuestionRef = QuestionServices.insert(userId, networkId, "Specifically, what is your favorite 'American' dish?", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                 // Adding options
                 addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                 QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                     {
                         "BBQ",
                         "Burgers",
                         "Hot Dogs",
                     });
                 addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                 // Adding flow rules
                 option = questions.get("foods").findOptionByText("American");
                 FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                 // Adding question to list
                 questions.put("american-foods", addedQuestion);
            }

            {
                 // Adding question
                 addedQuestionRef = QuestionServices.insert(userId, networkId, "Specifically, what is your favorite 'Thai' dish?", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                 // Adding options
                 addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                 QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                     {
                         "Pad Thai",
                         "Green Curry",
                     });
                 addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                 // Adding flow rules
                 option = questions.get("foods").findOptionByText("Thai");
                 FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                 // Adding question to list
                 questions.put("thai-foods", addedQuestion);
            }

            {
                 // Adding question
                 addedQuestionRef = QuestionServices.insert(userId, networkId, "Specifically, what is your favorite 'Vietnamese' dish?", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                 // Adding options
                 addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                 QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                     {
                         "Pho",
                     });
                 addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                 // Adding flow rules
                 option = questions.get("foods").findOptionByText("Vietnamese");
                 FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                 // Adding question to list
                 questions.put("vietnamese-foods", addedQuestion);
            }

            {
                 // Adding question
                 addedQuestionRef = QuestionServices.insert(userId, networkId, "Specifically, what is your favorite 'French' dish?", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                 // Adding options
                 addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                 QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                     {
                         "Croissant",
                         "Quiche",
                     });
                 addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                 // Adding flow rules
                 option = questions.get("foods").findOptionByText("French");
                 FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                 // Adding question to list
                 questions.put("french-foods", addedQuestion);
            }

            {
                 // Adding question
                 addedQuestionRef = QuestionServices.insert(userId, networkId, "Specifically, what is your favorite 'Middle Eastern' dish?", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                 // Adding options
                 addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                 QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                     {
                         "Falafel",
                         "Tabouleh",
                     });
                 addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                 // Adding flow rules
                 option = questions.get("foods").findOptionByText("Middle Eastern");
                 FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                 // Adding question to list
                 questions.put("eastern-foods", addedQuestion);
            }

            {
                 // Adding question
                 addedQuestionRef = QuestionServices.insert(userId, networkId, "Specifically, what is your favorite 'Latin' dish?", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                 // Adding options
                 addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                 QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                     {
                         "Ceviche",
                         "Fried Plantains",
                     });
                 addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                 // Adding flow rules
                 option = questions.get("foods").findOptionByText("Latin");
                 FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                 // Adding question to list
                 questions.put("latin-foods", addedQuestion);
            }

            {
                 // Adding question
                 addedQuestionRef = QuestionServices.insert(userId, networkId, "Specifically, what is your favorite 'Spanish' dish?", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                 // Adding options
                 addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                 QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                     {
                         "Paella",
                         "Horchata",
                     });
                 addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                 // Adding flow rules
                 option = questions.get("foods").findOptionByText("Spanish");
                 FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                 // Adding question to list
                 questions.put("spanish-foods", addedQuestion);
            }

            {
                 // Adding question
                 addedQuestionRef = QuestionServices.insert(userId, networkId, "Specifically, what is your favorite 'Chinese' dish?", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                 // Adding options
                 addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                 QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                     {
                         "Dumplings",
                     });
                 addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                 // Adding flow rules
                 option = questions.get("foods").findOptionByText("Chinese");
                 FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                 // Adding question to list
                 questions.put("chinese-foods", addedQuestion);
            }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Can others contact you by email to just chat about food?", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Yes",
                        "No",
                        "Maybe",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("contact", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Which regions of the world would you like to visit?", 100, 3, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Africa",
                        "North America",
                        "South America",
                        "Asia",
                        "Australia & Oceania",
                        "Europe",
                        "Any emerging area",
                        "Any developed markets",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("region", addedQuestion);
        }

    }
}
