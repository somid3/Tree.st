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

public class FitArrow {

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
        Integer networkId = NetworkDao.insert(null, "FitArrow");

        // Adding start message
        NetworkAlphaSettingDao.deleteByNetworkIdAndSetting(null, networkId, NetworkAlphaSettingEnum.START_MESSAGE);
        NetworkAlphaSettingDao.insert(null, networkId, NetworkAlphaSettingEnum.START_MESSAGE, "The easiest, fastest way to get personalized, one-on-one training — and start getting fit today!");

        // Adding url path
        NetworkAlphaSettingDao.deleteByValue(null, NetworkAlphaSettingEnum.URL_PATH, "fitarrow");
        NetworkAlphaSettingDao.insert(null, networkId, NetworkAlphaSettingEnum.URL_PATH, "fitarrow");

        // Add question with options and flow rule
        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "What are your current fitness goals?", 10, 2, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                {
                    "Build some serious muscle",
                    "Get toned and trim",
                    "Learn how to eat healthier and still enjoy life",
                    "Learn how to work out more effectively",
                    "Get in shape for an event",
                });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("goal", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "In what location(s) do you prefer to work out?", 10, 2, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "At home",
                        "At the gym",
                        "In the outdoors",
                        "Elsewhere",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("where", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "What days of the week do you prefer to work out?", 10, 7, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Mondays",
                        "Tuesdays",
                        "Wednesdays",
                        "Thursdays",
                        "Fridays",
                        "Saturdays",
                        "Sundays",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("day", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "On a typical day, how much time can you set aside to work out?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "15 minutes",
                        "30 minutes",
                        "45 minutes",
                        "60 minutes (one hour)",
                        "90 minutes (hour and a half)",
                        "120 minutes (two hour)",
                        "Over 120 minutes",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("workout-time", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "How many hours do you sleep on a typical night", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "2 - 3 hours",
                        "4 - 5 hours",
                        "6 - 8 hours",
                        "9 - 11 hours",
                        "Over 12 hours",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("sleep", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "What are your favorite activities?", 10, 3, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Aerobics           ",
                        "Basketball         ",
                        "Bodybuilding       ",
                        "Boxing             ",
                        "Cardio Machines    ",
                        "Cycling            ",
                        "Dancing            ",
                        "Golfing            ",
                        "Hiking             ",
                        "Kickboxing         ",
                        "Martial Arts       ",
                        "Pilates            ",
                        "Running            ",
                        "Skiing             ",
                        "Soccer             ",
                        "Swimming           ",
                        "Tennis             ",
                        "Volleyball         ",
                        "Walking            ",
                        "Yoga               ",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("activities", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "How many hours per day do you drive?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                            "0 - 1 hours  ",
                            "2 - 3 hours  ",
                            "4 - 5 hours  ",
                            "Over 5 hours ",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("driving", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Do you smoke?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                            "Yes",
                            "No",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("smoke", addedQuestion);
        }

            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "How many cigars do you smoke per day?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                            "1 - 5   ",
                            "6 - 10  ",
                            "11 - 15 ",
                            "16 - 20 ",
                            "Over 20 ",
                        });
                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                flowQuestion = questions.get("smoke");
                flowOption = flowQuestion.findOptionByText("Yes");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("smoke cigars", addedQuestion);
            }


            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "What year did you start smoking?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                                "2013",
                                "2012",
                                "2011",
                                "2010",
                                "2009",
                                "2008",
                                "2007",
                                "2006",
                                "2005",
                                "Before 2005",
                        });
                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                flowQuestion = questions.get("smoke");
                flowOption = flowQuestion.findOptionByText("Yes");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("smoke year", addedQuestion);
            }

            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "Is one of your goals to stop smoking?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                                "Yes",
                                "No",
                        });
                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                flowQuestion = questions.get("smoke");
                flowOption = flowQuestion.findOptionByText("Yes");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("smoke stop", addedQuestion);
            }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Do you drink alcohol?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Yes",
                        "No",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("alcohol", addedQuestion);
        }

            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "How many alcoholic drinks to you consume per week?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                                "1 - 5   ",
                                "6 - 10  ",
                                "11 - 15 ",
                                "16 - 20 ",
                                "Over 20 ",
                        });
                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                flowQuestion = questions.get("alcohol");
                flowOption = flowQuestion.findOptionByText("Yes");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("alcohol drinks", addedQuestion);
            }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "In your medical history, select all the conditions you've had or currently have?", 10, 10, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Hypertension                ",
                        "Heart attack(s)             ",
                        "Angina                      ",
                        "claudication                ",
                        "Heart murmur                ",
                        "Stroke(s)                   ",
                        "Epilepsy                    ",
                        "Asthma                      ",
                        "Emphysema                   ",
                        "Chronic bronchitis          ",
                        "Diabetes                    ",
                        "Thyroid disorders           ",
                        "Renal or liver disease      ",
                        "Osteoporosis                ",
                        "Arthritis                   ",
                        "Back pain                   ",
                        "Recent surgery              ",
                        "Previous/current pregnancy  ",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("medical", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Do you have any broken or fractured bones, torn muscles or ligaments, joint sprains, or ongoing pains?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Yes",
                        "No",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("pains", addedQuestion);
        }

            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "Select all the regions in your body where you have broken or fractured bones, torn muscles or ligaments, joint sprains, or ongoing pains.", 10, 10, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                            "Feet/toes     ",
                            "Ankles        ",
                            "Lower leg     ",
                            "Knees         ",
                            "Upper leg     ",
                            "Pelvis/hips   ",
                            "Lower back    ",
                            "Middle back   ",
                            "Neck          ",
                            "Shoulder      ",
                            "Upper arm     ",
                            "Elbow         ",
                            "Forearm       ",
                            "Wrist/fingers ",
                        });
                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                flowQuestion = questions.get("pains");
                flowOption = flowQuestion.findOptionByText("Yes");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("pains location", addedQuestion);
            }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "What is your dietary preferences?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "I eat anything  ",
                        "Vegetarian (no meats)      ",
                        "Pescetarian (only seafood meats)",
                        "Vegan           ",
                        "Gluten-Free     ",
                        "Lactose Free    ",
                        "Raw Food        ",
                        "Kosher Food     ",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("dietary", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "About how often do you eat breakfast at home?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Once a day              ",
                        "Every other day         ",
                        "Every 3 days            ",
                        "Once a week             ",
                        "Every other week        ",
                        "Once a month or longer  ",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("bfast", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "About how often do you eat lunch at home?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                            "Once a day              ",
                            "Every other day         ",
                            "Every 3 days            ",
                            "Once a week             ",
                            "Every other week        ",
                            "Once a month or longer  ",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("lunch", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "About how often do you eat dinner at home?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                            "Once a day              ",
                            "Every other day         ",
                            "Every 3 days            ",
                            "Once a week             ",
                            "Every other week        ",
                            "Once a month or longer  ",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("dinner", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Are you currently working directly with any of our personal trainers, if so, who?", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "None          ",
                        "Bayan Sharafi ",
                        "Lior Paritzky ",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("working with", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Are you a personal trainer", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Yes",
                        "No",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("trainer", addedQuestion);
        }

            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "What are your specialties?", 10, 2, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Bodybuilding & Muscle building  ",
                        "Weight loss & Strength training ",
                        "Nutrition & Diet                ",
                        "Beginners training              ",
                        "Intermediate training           ",
                        "Advanced training               ",
                    });
                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                flowQuestion = questions.get("trainer");
                flowOption = flowQuestion.findOptionByText("Yes");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
                flowQuestion = questions.get("trainer");
                flowOption = flowQuestion.findOptionByText("Yes");
                questions.put("trainer specialties", addedQuestion);
            }

            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "What group of trainees do you specialize in?", 10, 2, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                            "Children             ",
                            "Teenagers            ",
                            "Adults               ",
                            "Pregnant mothers     ",
                            "Post-surgery trainees",
                        });
                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                flowQuestion = questions.get("trainer");
                flowOption = flowQuestion.findOptionByText("Yes");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("trainer groups", addedQuestion);
            }

    }

}
