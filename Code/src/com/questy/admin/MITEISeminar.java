package com.questy.admin;

import com.questy.dao.NetworkAlphaSettingDao;
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

public class MITEISeminar {

    public static void main(String[] args) throws Exception {

        // Adding start message
        NetworkAlphaSettingDao.deleteByNetworkIdAndSetting(null, 2003, NetworkAlphaSettingEnum.START_MESSAGE);
        NetworkAlphaSettingDao.insert(null, 2003, NetworkAlphaSettingEnum.START_MESSAGE, "Get to know your classmates and build a well balanced team for your next startup!");

        NetworkAlphaSettingDao.deleteByNetworkIdAndSetting(null, 2003, NetworkAlphaSettingEnum.START_BODY);
        NetworkAlphaSettingDao.insert(null, 2003, NetworkAlphaSettingEnum.START_BODY, "<div id=\"video\" class=\"glow\">\n" +
                "<iframe src=\"http://player.vimeo.com/video/46590201\" width=\"500\" height=\"281\" frameborder=\"0\" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe>\n" +
                "</div>");

//        create();
    }

    public static void create(Integer networkId) throws Exception {

        Integer userId = 2;

        Integer addedQuestionRef = null;
        Question addedQuestion = null;
        QuestionOption option = null;
        Map<String, Question> questions = new HashMap<String, Question>();

        // Add question with options and flow rule
        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Which industry are you most interested for your next startup?", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Consumer          ",
                        "Energy            ",
                        "Financial         ",
                        "Healthcare        ",
                        "Industrial Goods  ",
                        "Other             ",
                        "Services          ",
                        "Software          ",
                        "Technology        ",
                        "Utilities         ",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("industries", addedQuestion);
        }
            {
                 // Adding question
                 addedQuestionRef = QuestionServices.insert(userId, networkId, "What 'Consumer' sectors are you most interested in?", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                 // Adding options
                 addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                 QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                     {
                         "Consumable Products",
                         "Technology & Electronics",
                         "Software & Internet",
                         "Appliances",
                         "Personal Products",
                         "Fashion & Retail",
                     });
                 addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                 // Adding flow rules
                 option = questions.get("industries").findOptionByText("Consumer");
                 FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                 // Adding question to list
                 questions.put("consumer", addedQuestion);
            }

            {
                 // Adding question
                 addedQuestionRef = QuestionServices.insert(userId, networkId, "What 'Energy' sectors are you most interested in?", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                 // Adding options
                 addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                 QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                     {
                         "Alternative Energy",
                         "Renewable Energy",
                         "Energy Storage & Batteries",
                         "Grid Services",
                         "Energy Usage Minimization",
                         "Creating Energy",
                     });
                 addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                 // Adding flow rules
                 option = questions.get("industries").findOptionByText("Energy");
                 FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                 // Adding question to list
                 questions.put("energy", addedQuestion);
            }


            {
                 // Adding question
                 addedQuestionRef = QuestionServices.insert(userId, networkId, "What 'Financial' sectors are you most interested in?", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                 // Adding options
                 addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                 QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                     {
                         "Stock Trading ",
                         "Banking Services",
                         "Insurance",
                         "Options Trading",
                         "Financial Services",
                         "Financial Reports",
                     });
                 addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                 // Adding flow rules
                 option = questions.get("industries").findOptionByText("Financial");
                 FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                 // Adding question to list
                 questions.put("finance", addedQuestion);
            }


            {
                 // Adding question
                 addedQuestionRef = QuestionServices.insert(userId, networkId, "What 'Healthcare' sectors are you most interested in?", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                 // Adding options
                 addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                 QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                     {
                         "Biotechnology      ",
                         "Pharmaceuticals    ",
                         "Medical Devices    ",
                         "Healthcare Services",
                     });
                 addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                 // Adding flow rules
                 option = questions.get("industries").findOptionByText("Healthcare");
                 FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                 // Adding question to list
                 questions.put("healthcare", addedQuestion);
            }

            {
                 // Adding question
                 addedQuestionRef = QuestionServices.insert(userId, networkId, "What 'Industrial Goods' sectors are you most interested in?", 50, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                 // Adding options
                 addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                 QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                     {
                         "Machinery              ",
                         "Aerospace              ",
                         "Defense & Military     ",
                         "Agricultural Machinery ",
                         "Small Tools            ",
                         "Waste Management       ",

                     });
                 addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                 // Adding flow rules
                 option = questions.get("industries").findOptionByText("Industrial Goods");
                 FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                 // Adding question to list
                 questions.put("industrial", addedQuestion);
            }

            {
                 // Adding question
                 addedQuestionRef = QuestionServices.insert(userId, networkId, "What 'Other' sectors are you most interested in? Add your options...", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                 // Adding options
                 addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                 QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                     {
                             "",
                     });
                 addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                 // Adding flow rules
                 option = questions.get("industries").findOptionByText("Other");
                 FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                 // Adding question to list
                 questions.put("other", addedQuestion);
            }

            {
                 // Adding question
                 addedQuestionRef = QuestionServices.insert(userId, networkId, "What 'Services' sectors are you most interested in?", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                 // Adding options
                 addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                 QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                     {
                         "Business Services",
                         "Consumer Services"
                     });
                 addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                 // Adding flow rules
                 option = questions.get("industries").findOptionByText("Services");
                 FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                 // Adding question to list
                 questions.put("services", addedQuestion);
            }

            {
                 // Adding question
                 addedQuestionRef = QuestionServices.insert(userId, networkId, "What 'Software' sectors are you most interested in?", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                 // Adding options
                 addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                 QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                     {
                        "Enterprise Software   ",
                        "Consumer Software     ",
                        "Web & Internet Based  ",
                        "Video Games           ",
                        "Social Tools          ",
                        "Mobile Software       "
                     });
                 addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                 // Adding flow rules
                 option = questions.get("industries").findOptionByText("Software");
                 FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                 // Adding question to list
                 questions.put("software", addedQuestion);
            }

            {
                 // Adding question
                 addedQuestionRef = QuestionServices.insert(userId, networkId, "What 'Technology' sectors are you most interested in?", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                 // Adding options
                 addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                 QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                     {
                         "Electronics    ",
                         "Communications ",
                         "IT             ",
                         "Engineering    ",
                         "Automation     "
                     });
                 addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                 // Adding flow rules
                 option = questions.get("industries").findOptionByText("Technology");
                 FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                 // Adding question to list
                 questions.put("technology", addedQuestion);
            }

            {
                 // Adding question
                 addedQuestionRef = QuestionServices.insert(userId, networkId, "What 'Utilities' sectors are you most interested in?", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                 // Adding options
                 addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                 QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                     {
                         "Diversified Utilities ",
                         "Electric Utilities    ",
                         "Gas Utilities         ",
                         "Water Utilities       ",
                         "Waste Utilities       ",
                         "Industrial Utilities  "
                     });
                 addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                 // Adding flow rules
                 option = questions.get("industries").findOptionByText("Utilities");
                 FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                 // Adding question to list
                 questions.put("utilities", addedQuestion);
            }


        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "What is your top priority in a startup?", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Cash in king, make profits",
                        "Help the world, make the world a better place",
                        "Improve science, help researcher & innovation",
                        "Fun, just wanna have fun",
                        "Practice makes perfection, wish to learn the most"
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("priority", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "In what regions of the world would you like to focus your startup in?", 100, 3, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

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
                        "All emerging markets",
                        "All developed markets",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("priority", addedQuestion);
        }

    }
}
