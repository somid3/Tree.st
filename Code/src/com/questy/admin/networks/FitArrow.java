package com.questy.admin.networks;

import com.questy.dao.NetworkAlphaSettingDao;
import com.questy.dao.NetworkDao;
import com.questy.dao.NetworkIntegerSettingDao;
import com.questy.dao.QuestionDao;
import com.questy.domain.Network;
import com.questy.domain.Question;
import com.questy.domain.QuestionOption;
import com.questy.enums.AnswerVisibilityEnum;
import com.questy.enums.NetworkAlphaSettingEnum;
import com.questy.enums.NetworkIntegerSettingEnum;
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

        // Setting network integer settings
        NetworkIntegerSettingDao.insert(null, networkId, NetworkIntegerSettingEnum.IS_PAYMENT_REQUIRED, 1);
        NetworkIntegerSettingDao.insert(null, networkId, NetworkIntegerSettingEnum.IS_UI_LOGO_SET, 1);
        NetworkIntegerSettingDao.insert(null, networkId, NetworkIntegerSettingEnum.IS_UI_ICON_SET, 1);

        // Setting network alpha settings
        NetworkAlphaSettingDao.deleteByValue(null, NetworkAlphaSettingEnum.URL_PATH, "fitarrow");
        NetworkAlphaSettingDao.insert(null, networkId, NetworkAlphaSettingEnum.URL_PATH, "fitarrow");
        NetworkAlphaSettingDao.insert(null, networkId, NetworkAlphaSettingEnum.UI_HEADER_BACKGROUND_COLOR, "#444");
        NetworkAlphaSettingDao.deleteByNetworkIdAndSetting(null, networkId, NetworkAlphaSettingEnum.START_MESSAGE);
        NetworkAlphaSettingDao.insert(null, networkId, NetworkAlphaSettingEnum.START_MESSAGE, "The easiest, fastest way to get personalized, one-on-one training — and start getting fit today!");

        // Retrieving network to display details
        Network network = NetworkDao.getById(null, networkId);
        System.out.println("Network created. ID: " + network.getId() + " CS: " + network.getChecksum());

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
            addedQuestionRef = QuestionServices.insert(userId, networkId, "What is your gender?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                {
                    "Male",
                    "Female",
                });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("gender", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "How tall are you?", 0, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                {
                        "Under 4 ft",
                        "4 ft 0 in",
                        "4 ft 0.5 in",
                        "4 ft 1 in",
                        "4 ft 1.5 in",
                        "4 ft 2 in",
                        "4 ft 2.5 in",
                        "4 ft 3 in",
                        "4 ft 3.5 in",
                        "4 ft 4 in",
                        "4 ft 4.5 in",
                        "4 ft 5 in",
                        "4 ft 5.5 in",
                        "4 ft 6 in",
                        "4 ft 6.5 in",
                        "4 ft 7 in",
                        "4 ft 7.5 in",
                        "4 ft 8 in",
                        "4 ft 8.5 in",
                        "4 ft 9 in",
                        "4 ft 9.5 in",
                        "4 ft 10 in",
                        "4 ft 10.5 in",
                        "4 ft 11 in",
                        "4 ft 11.5 in",
                        "5 ft 0 in",
                        "5 ft 0.5 in",
                        "5 ft 1 in",
                        "5 ft 1.5 in",
                        "5 ft 2 in",
                        "5 ft 2.5 in",
                        "5 ft 3 in",
                        "5 ft 3.5 in",
                        "5 ft 4 in",
                        "5 ft 4.5 in",
                        "5 ft 5 in",
                        "5 ft 5.5 in",
                        "5 ft 6 in",
                        "5 ft 6.5 in",
                        "5 ft 7 in",
                        "5 ft 7.5 in",
                        "5 ft 8 in",
                        "5 ft 8.5 in",
                        "5 ft 9 in",
                        "5 ft 9.5 in",
                        "5 ft 10 in",
                        "5 ft 10.5 in",
                        "5 ft 11 in",
                        "5 ft 11.5 in",
                        "6 ft 0 in",
                        "6 ft 0.5 in",
                        "6 ft 1 in",
                        "6 ft 1.5 in",
                        "6 ft 2 in",
                        "6 ft 2.5 in",
                        "6 ft 3 in",
                        "6 ft 3.5 in",
                        "6 ft 4 in",
                        "6 ft 4.5 in",
                        "6 ft 5 in",
                        "6 ft 5.5 in",
                        "6 ft 6 in",
                        "6 ft 6.5 in",
                        "6 ft 7 in",
                        "6 ft 7.5 in",
                        "6 ft 8 in",
                        "6 ft 8.5 in",
                        "6 ft 9 in",
                        "6 ft 9.5 in",
                        "6 ft 10 in",
                        "6 ft 10.5 in",
                        "6 ft 11 in",
                        "6 ft 11.5 in",
                        "7 ft 0 in",
                        "7 ft 0.5 in",
                        "7 ft 1 in",
                        "7 ft 1.5 in",
                        "7 ft 2 in",
                        "7 ft 2.5 in",
                        "7 ft 3 in",
                        "7 ft 3.5 in",
                        "7 ft 4 in",
                        "7 ft 4.5 in",
                        "7 ft 5 in",
                        "7 ft 5.5 in",
                        "7 ft 6 in",
                        "7 ft 6.5 in",
                        "7 ft 7 in",
                        "7 ft 7.5 in",
                        "7 ft 8 in",
                        "7 ft 8.5 in",
                        "7 ft 9 in",
                        "7 ft 9.5 in",
                        "7 ft 10 in",
                        "7 ft 10.5 in",
                        "7 ft 11 in",
                        "7 ft 11.5 in",
                        "8 ft 0 in",
                        "8 ft 0.5 in",
                        "8 ft 1 in",
                        "8 ft 1.5 in",
                        "8 ft 2 in",
                        "8 ft 2.5 in",
                        "8 ft 3 in",
                        "8 ft 3.5 in",
                        "8 ft 4 in",
                        "8 ft 4.5 in",
                        "8 ft 5 in",
                        "8 ft 5.5 in",
                        "8 ft 6 in",
                        "8 ft 6.5 in",
                        "8 ft 7 in",
                        "8 ft 7.5 in",
                        "8 ft 8 in",
                        "8 ft 8.5 in",
                        "8 ft 9 in",
                        "8 ft 9.5 in",
                        "8 ft 10 in",
                        "8 ft 10.5 in",
                        "8 ft 11 in",
                        "8 ft 11.5 in",
                        "Over 9 ft",
                });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());


            // Adding question to list
            questions.put("tall", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "How much do you weight?", 0, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                {
                    "Under 100 lbs",
                    "100 lbs",
                    "101 lbs",
                    "102 lbs",
                    "103 lbs",
                    "104 lbs",
                    "105 lbs",
                    "106 lbs",
                    "107 lbs",
                    "108 lbs",
                    "109 lbs",
                    "110 lbs",
                    "111 lbs",
                    "112 lbs",
                    "113 lbs",
                    "114 lbs",
                    "115 lbs",
                    "116 lbs",
                    "117 lbs",
                    "118 lbs",
                    "119 lbs",
                    "120 lbs",
                    "121 lbs",
                    "122 lbs",
                    "123 lbs",
                    "124 lbs",
                    "125 lbs",
                    "126 lbs",
                    "127 lbs",
                    "128 lbs",
                    "129 lbs",
                    "130 lbs",
                    "131 lbs",
                    "132 lbs",
                    "133 lbs",
                    "134 lbs",
                    "135 lbs",
                    "136 lbs",
                    "137 lbs",
                    "138 lbs",
                    "139 lbs",
                    "140 lbs",
                    "141 lbs",
                    "142 lbs",
                    "143 lbs",
                    "144 lbs",
                    "145 lbs",
                    "146 lbs",
                    "147 lbs",
                    "148 lbs",
                    "149 lbs",
                    "150 lbs",
                    "151 lbs",
                    "152 lbs",
                    "153 lbs",
                    "154 lbs",
                    "155 lbs",
                    "156 lbs",
                    "157 lbs",
                    "158 lbs",
                    "159 lbs",
                    "160 lbs",
                    "161 lbs",
                    "162 lbs",
                    "163 lbs",
                    "164 lbs",
                    "165 lbs",
                    "166 lbs",
                    "167 lbs",
                    "168 lbs",
                    "169 lbs",
                    "170 lbs",
                    "171 lbs",
                    "172 lbs",
                    "173 lbs",
                    "174 lbs",
                    "175 lbs",
                    "176 lbs",
                    "177 lbs",
                    "178 lbs",
                    "179 lbs",
                    "180 lbs",
                    "181 lbs",
                    "182 lbs",
                    "183 lbs",
                    "184 lbs",
                    "185 lbs",
                    "186 lbs",
                    "187 lbs",
                    "188 lbs",
                    "189 lbs",
                    "190 lbs",
                    "191 lbs",
                    "192 lbs",
                    "193 lbs",
                    "194 lbs",
                    "195 lbs",
                    "196 lbs",
                    "197 lbs",
                    "198 lbs",
                    "199 lbs",
                    "200 lbs",
                    "201 lbs",
                    "202 lbs",
                    "203 lbs",
                    "204 lbs",
                    "205 lbs",
                    "206 lbs",
                    "207 lbs",
                    "208 lbs",
                    "209 lbs",
                    "210 lbs",
                    "211 lbs",
                    "212 lbs",
                    "213 lbs",
                    "214 lbs",
                    "215 lbs",
                    "216 lbs",
                    "217 lbs",
                    "218 lbs",
                    "219 lbs",
                    "220 lbs",
                    "221 lbs",
                    "222 lbs",
                    "223 lbs",
                    "224 lbs",
                    "225 lbs",
                    "226 lbs",
                    "227 lbs",
                    "228 lbs",
                    "229 lbs",
                    "230 lbs",
                    "231 lbs",
                    "232 lbs",
                    "233 lbs",
                    "234 lbs",
                    "235 lbs",
                    "236 lbs",
                    "237 lbs",
                    "238 lbs",
                    "239 lbs",
                    "240 lbs",
                    "241 lbs",
                    "242 lbs",
                    "243 lbs",
                    "244 lbs",
                    "245 lbs",
                    "246 lbs",
                    "247 lbs",
                    "248 lbs",
                    "249 lbs",
                    "250 lbs",
                    "Over 250 lbs",
                });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("weight", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "What year were you born?", 0, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                {
                    "2012",
                    "2011",
                    "2010",
                    "2009",
                    "2008",
                    "2007",
                    "2006",
                    "2005",
                    "2004",
                    "2003",
                    "2002",
                    "2001",
                    "2000",
                    "1999",
                    "1998",
                    "1997",
                    "1996",
                    "1995",
                    "1994",
                    "1993",
                    "1992",
                    "1991",
                    "1990",
                    "1989",
                    "1988",
                    "1987",
                    "1986",
                    "1985",
                    "1984",
                    "1983",
                    "1982",
                    "1981",
                    "1980",
                    "1979",
                    "1978",
                    "1977",
                    "1976",
                    "1975",
                    "1974",
                    "1973",
                    "1972",
                    "1971",
                    "1970",
                    "1969",
                    "1968",
                    "1967",
                    "1966",
                    "1965",
                    "1964",
                    "1963",
                    "1962",
                    "1961",
                    "1960",
                    "1959",
                    "1958",
                    "1957",
                    "1956",
                    "1955",
                    "1954",
                    "1953",
                    "1952",
                    "1951",
                    "1950",
                    "1949",
                    "1948",
                    "1947",
                    "1946",
                    "1945",
                    "1944",
                    "1943",
                    "1942",
                    "1941",
                    "1940",
                    "1939",
                    "1938",
                    "1937",
                    "1936",
                    "1935",
                    "1934",
                    "1933",
                    "1932",
                    "1931",
                    "1930",
                    "1929",
                    "1928",
                    "1927",
                    "1926",
                    "1925",
                    "1924",
                    "1923",
                    "1922",
                    "1921",
                    "1920",
                    "1919",
                    "1918",
                    "1917",
                    "1916",
                    "1915",
                    "1914",
                    "1913",
                    "1912",
                    "1911",
                    "1910",
                    "1909",
                    "1908",
                });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("birth", addedQuestion);
        }


        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Where do you typically prefer to work out?", 10, 2, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

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
                        "120 minutes (two hours)",
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
                FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

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
                FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

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
                FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

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
                FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("alcohol drinks", addedQuestion);
            }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "In your medical history, select all the conditions you've had or currently have? Click 'Skip' if you haven't had any", 10, 10, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Hypertension                ",
                        "Heart attack(s)             ",
                        "Angina                      ",
                        "Claudication                ",
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
                FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("pains location", addedQuestion);
            }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "What is your dietary preference?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

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
            addedQuestionRef = QuestionServices.insert(userId, networkId, "How often do you eat breakfast at home?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

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
            addedQuestionRef = QuestionServices.insert(userId, networkId, "How often do you eat lunch at home?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

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
            addedQuestionRef = QuestionServices.insert(userId, networkId, "How often do you eat dinner at home?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

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
                FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
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
                FlowRuleServices.insert(addedQuestion.getNetworkId(), flowQuestion.getRef(), flowOption.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("trainer groups", addedQuestion);
            }

    }

}
