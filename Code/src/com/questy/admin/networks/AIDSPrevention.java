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

public class AIDSPrevention {

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
        Integer networkId = NetworkDao.insert(null, "Global Strategies");

        // Adding start message
        NetworkAlphaSettingDao.deleteByNetworkIdAndSetting(null, networkId, NetworkAlphaSettingEnum.START_MESSAGE);
        NetworkAlphaSettingDao.insert(null, networkId, NetworkAlphaSettingEnum.START_MESSAGE, "Meet other physicians, volunteers, and mentors across the globe who are helping in efforts against AIDS");

        // Adding url path
        NetworkAlphaSettingDao.deleteByValue(null, NetworkAlphaSettingEnum.URL_PATH, "globalstrategies");
        NetworkAlphaSettingDao.insert(null, networkId, NetworkAlphaSettingEnum.URL_PATH, "globalstrategies");

        // Add question with options and flow rule
        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Do you live in the San Francisco Bay Area?", 30, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                {
                    "Yes",
                    "No"
                });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("sf", addedQuestion);
        }

            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "What is you medical or healthcare background?", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Physician",
                        "Nurse",
                        "Physical Therapist",
                        "Student"
                    });
                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                flowQuestion = questions.get("sf");
                flowOption = flowQuestion.findOptionByText("Yes");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("health", addedQuestion);
            }

                {
                    // Adding question
                    addedQuestionRef = QuestionServices.insert(userId, networkId, "Physician question:", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                    // Adding options
                    addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                    QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                            "Physician option 1",
                            "Physician option 2",
                            "Physician option 3"
                        });
                    addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                    // Adding flow rules
                    flowQuestion = questions.get("health");
                    flowOption = flowQuestion.findOptionByText("Physician");
                    FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                    // Adding question to list
                    questions.put("physician", addedQuestion);
                }


                {
                    // Adding question
                    addedQuestionRef = QuestionServices.insert(userId, networkId, "Nurse question:", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                    // Adding options
                    addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                    QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                            "Nurse option 1",
                            "Nurse option 2",
                            "Nurse option 3"
                        });
                    addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                    // Adding flow rules
                    flowQuestion = questions.get("health");
                    flowOption = flowQuestion.findOptionByText("Nurse");
                    FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                    // Adding question to list
                    questions.put("nurse", addedQuestion);
                }

                {
                    // Adding question
                    addedQuestionRef = QuestionServices.insert(userId, networkId, "Physical Therapist question:", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                    // Adding options
                    addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                    QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                            "Physical Therapist option 1",
                            "Physical Therapist option 2",
                            "Physical Therapist option 3"
                        });
                    addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                    // Adding flow rules
                    flowQuestion = questions.get("health");
                    flowOption = flowQuestion.findOptionByText("Physical Therapist");
                    FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                    // Adding question to list
                    questions.put("physical therapist", addedQuestion);
                }


                {
                    // Adding question
                    addedQuestionRef = QuestionServices.insert(userId, networkId, "Student question:", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                    // Adding options
                    addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                    QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                            "Student option 1",
                            "Student option 2",
                            "Student option 3"
                        });
                    addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                    // Adding flow rules
                    flowQuestion = questions.get("health");
                    flowOption = flowQuestion.findOptionByText("Student");
                    FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                    // Adding question to list
                    questions.put("student", addedQuestion);
                }

                {
                    // Adding question
                    addedQuestionRef = QuestionServices.insert(userId, networkId, "Where are you currently enrolled?", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                    // Adding options
                    addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                    QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                            "UCSF",
                            "Stanford",
                            "UC Berkeley"
                        });
                    addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                    // Adding flow rules
                    flowQuestion = questions.get("health");
                    flowOption = flowQuestion.findOptionByText("Student");
                    FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                    // Adding question to list
                    questions.put("student enrolled", addedQuestion);
                }

            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "Do you have experience in any of the following:", 100, 6, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Fundraising",
                        "Marketing",
                        "Accounting",
                        "Office Management",
                        "Grahic Design",
                        "Web Design"
                    });
                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                flowQuestion = questions.get("sf");
                flowOption = flowQuestion.findOptionByText("No");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("experiences", addedQuestion);
            }

                {
                    // Adding question
                    addedQuestionRef = QuestionServices.insert(userId, networkId, "Fundraising wise, have you...", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                    // Adding options
                    addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                    QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                                "Written Grants",
                                "Organized Fundraising Events",
                                "Created Targeted Fundraising Campaigns"
                        });
                    addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                    // Adding flow rules
                    flowQuestion = questions.get("experiences");
                    flowOption = flowQuestion.findOptionByText("Fundraising");
                    FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                    // Adding question to list
                    questions.put("fundraising", addedQuestion);
                }


                {
                    // Adding question
                    addedQuestionRef = QuestionServices.insert(userId, networkId, "What do you consider your best Fundraising Skill", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                    // Adding options
                    addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                    QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                            "Writing content",
                            "Creating Interesting Fundraisers",
                            "Designing a process"

                        });
                    addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                    // Adding flow rules
                    flowQuestion = questions.get("experiences");
                    flowOption = flowQuestion.findOptionByText("Fundraising");
                    FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                    // Adding question to list
                    questions.put("fundraising2", addedQuestion);
                }

                {
                    // Adding question
                    addedQuestionRef = QuestionServices.insert(userId, networkId, "Marketing wise, have you...", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                    // Adding options
                    addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                    QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                            "Designed sales or marketing collateral",
                            "Designed or written content for websites"
                        });
                    addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                    // Adding flow rules
                    flowQuestion = questions.get("experiences");
                    flowOption = flowQuestion.findOptionByText("Marketing");
                    FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                    // Adding question to list
                    questions.put("marketing", addedQuestion);
                }


                {
                    // Adding question
                    addedQuestionRef = QuestionServices.insert(userId, networkId, "Select all the accounting accreditations you have received", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                    // Adding options
                    addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                    QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                            "CPA",
                            "GAAP accounting",
                            "IFRS accounting"
                        });
                    addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                    // Adding flow rules
                    flowQuestion = questions.get("experiences");
                    flowOption = flowQuestion.findOptionByText("Accounting");
                    FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                    // Adding question to list
                    questions.put("accounting", addedQuestion);
                }


                    {
                        // Adding question
                        addedQuestionRef = QuestionServices.insert(userId, networkId, "Have you ever conducted an audit?", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                        // Adding options
                        addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                        QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                            {
                                "Yes",
                                "No"
                            });
                        addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                        // Adding flow rules
                        flowQuestion = questions.get("accounting");
                        flowOption = flowQuestion.findOptionByText("CPA");
                        FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                        // Adding question to list
                        questions.put("audit", addedQuestion);
                    }


                {
                    // Adding question
                    addedQuestionRef = QuestionServices.insert(userId, networkId, "Office Management question:", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                    // Adding options
                    addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                    QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                            "Office Management option 1",
                            "Office Management option 2",
                            "Office Management option 3"
                        });
                    addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                    // Adding flow rules
                    flowQuestion = questions.get("experiences");
                    flowOption = flowQuestion.findOptionByText("Office Management");
                    FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                    // Adding question to list
                    questions.put("office management", addedQuestion);
                }

    }



}
