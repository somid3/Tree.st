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

public class Doctorana {

    public static void main(String[] args) throws Exception {

        // Creating new network
        Integer networkId = NetworkDao.insert(null, "Doctorana");
        String networkDomain = "doctorana.com";

        // Adding domain
        NetworkAlphaSettingDao.deleteByValue(null, NetworkAlphaSettingEnum.URL_DOMAIN, networkDomain);
        NetworkAlphaSettingDao.insert(null, networkId, NetworkAlphaSettingEnum.URL_DOMAIN, "doctorana.com");

        // Adding custom landing
        NetworkAlphaSettingDao.deleteByNetworkIdAndSetting(null, networkId, NetworkAlphaSettingEnum.CUSTOM_LANDING);
        NetworkAlphaSettingDao.insert(null, networkId, NetworkAlphaSettingEnum.CUSTOM_LANDING, "__doctorana.jsp");

        create(networkId);
    }

    public static void create(Integer networkId) throws Exception {

        Integer userId = 1;

        Integer addedQuestionRef = null;
        Question addedQuestion = null;
        QuestionOption option = null;
        Map<String, Question> questions = new HashMap<String, Question>();

        // Add question with options and flow rule
        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "You currently are a...", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Practicing Doctor ",
                        "Medical Student   ",
                        "Retired Doctor    "
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("are", addedQuestion);
        }
        {
             // Adding question
             addedQuestionRef = QuestionServices.insert(userId, networkId, "What are you specialties", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

             // Adding options
             addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
             QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                 {
                     "Allergy and Immunology",
                     "Anesthesiology",
                     "Colon and Rectal Surgery",
                     "Dermatology",
                     "Emergency Medicine",
                     "Family Medicine",
                     "Internal Medicine",
                     "Clinical Genetics",
                     "Neurological Surgery",
                     "Nuclear Medicine",
                     "Obstetrics and Gynecology",
                     "Oncology",
                     "Ophthalmology",
                     "Orthopaedic Surgery",
                     "Otolaryngology",
                     "Pathology",
                     "Pediatrics",
                     "Physical Medicine and Rehabilitation",
                     "Plastic Surgery",
                     "Psychiatry",
                     "Radiology",
                     "Surgery",
                     "Vascular Surgery",
                     "Thoracic and Cardiac Surgery",
                     "Urology",
                 });
             addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

             // Adding flow rules
             FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
             questions.put("specialization", addedQuestion);
        }

        {
             // Adding question
             addedQuestionRef = QuestionServices.insert(userId, networkId, "What country do you currently practice in?", 50, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

             // Adding options
             addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
             QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                 {
                     "Algeria",
                     "Bahrain",
                     "Egypt",
                     "Iran",
                     "Iraq",
                     "Israel",
                     "Jordan",
                     "Kuwait",
                     "Lebanon",
                     "Yemen",
                     "United Arab Emirates",
                     "Libya",
                     "Morocco",
                     "Oman",
                     "Palestine",
                     "Qatar",
                     "Saudi Arabia",
                     "Syria",
                     "Tunisia",
                     "Turkey"
                 });
             addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

             // Adding flow rules
             FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

             // Adding question to list
             questions.put("country", addedQuestion);
        }


        {
             // Adding question
             addedQuestionRef = QuestionServices.insert(userId, networkId, "What are you looking to do?", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

             // Adding options
             addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
             QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                 {
                     "Meet other doctors",
                     "Find referral partners",
                     "Collaborate on patient cases",
                     "Get medical news and research alerts",
                     "Gain medical education",
                     "Help build this community",
                     "Learn of new medicines and therapies",
                     "Other needs...",
                 });
             addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

             // Adding flow rules
             FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

             // Adding question to list
             questions.put("needs", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Do you work in a clinic or a hospital?", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                            "A small clinic - under 10 doctors",
                            "A large clinic - over 10 doctors",
                            "In a small hospital - over 30 doctors",
                            "A large hospital - over 100 doctors",
                            "Other",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("size", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Select all the environments you practice in...", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                            "Military clinic or hospital",
                            "Government-owned clinic or hospital",
                            "Private practice, clinic or hospital",
                            "Other",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("money", addedQuestion);
        }

    }
}
