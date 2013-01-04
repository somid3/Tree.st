package com.questy.admin;

import com.questy.dao.*;
import com.questy.domain.Network;
import com.questy.domain.Question;
import com.questy.domain.QuestionOption;
import com.questy.domain.UserToNetwork;
import com.questy.enums.AnswerVisibilityEnum;
import com.questy.enums.NetworkAlphaSettingEnum;
import com.questy.enums.RoleEnum;
import com.questy.services.FlowRuleServices;
import com.questy.services.QuestionOptionServices;
import com.questy.services.QuestionServices;
import com.questy.utils.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ColorAndFoods {

    public static void create(Integer userId) throws Exception {

        // Creating network
        String checksum = StringUtils.random();
        Date date = new Date();
        Integer networkId = NetworkDao.insert(null, "Colors And Foods! - " + date.getTime(), false, checksum);
        String pathName = "colors";


        // Adding user to network
        UserToNetworkDao.insert(null, userId, networkId, RoleEnum.EDITOR);

        // Adding path parameter
        NetworkAlphaSettingDao.deleteByValue(null, NetworkAlphaSettingEnum.URL_PATH, pathName);
        NetworkAlphaSettingDao.insert(null, networkId, NetworkAlphaSettingEnum.URL_PATH, pathName);

        // Adding main message
        NetworkAlphaSettingDao.insert(null, networkId, NetworkAlphaSettingEnum.START_MESSAGE, "Meet others around the world by their color and cuisine preferences!");
        NetworkAlphaSettingDao.insert(null, networkId, NetworkAlphaSettingEnum.START_BODY, "");

        // Adding manifesto title
        NetworkAlphaSettingDao.insert(null, networkId, NetworkAlphaSettingEnum.MANIFESTO_TITLE, "Purpose");

        // Adding manifesto content
        NetworkAlphaSettingDao.insert(null, networkId, NetworkAlphaSettingEnum.MANIFESTO_CONTENT, "");

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
                        "Left-handed ",
                        "Right-handed ",
                        "Ambidextrous ",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), QuestionDao.ROOT_QUESTION_REF, QuestionOptionDao.ANY_OPTION_REF, addedQuestion.getRef());

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
            FlowRuleServices.insert(addedQuestion.getNetworkId(), QuestionDao.ROOT_QUESTION_REF, QuestionOptionDao.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("color", addedQuestion);
        }

        {
             // Adding question
             addedQuestionRef = QuestionServices.insert(userId, networkId, "What are the top two types cuisine you prefer?", 50, 2, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

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
                     "European",
                     "Chinese",
                     "Brazilian",
                 });
             addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

             // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), QuestionDao.ROOT_QUESTION_REF, QuestionOptionDao.ANY_OPTION_REF, addedQuestion.getRef());

             // Adding question to list
             questions.put("foods", addedQuestion);
        }

            {
                 // Adding question
                 addedQuestionRef = QuestionServices.insert(userId, networkId, "Specifically, what is your favorite 'American' cuisine dish?", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

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
            FlowRuleServices.insert(addedQuestion.getNetworkId(), QuestionDao.ROOT_QUESTION_REF, QuestionOptionDao.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("contact", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "For your next trip, which regions of the world would like to visit?", 100, 3, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

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
            FlowRuleServices.insert(addedQuestion.getNetworkId(), QuestionDao.ROOT_QUESTION_REF, QuestionOptionDao.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("region", addedQuestion);
        }

    }
}
