package com.questy.admin.networks;

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

public class MITSloan {

    public static void main(String[] args) throws Exception {

        // Adding start message
        NetworkAlphaSettingDao.deleteByNetworkIdAndSetting(null, 2002, NetworkAlphaSettingEnum.START_MESSAGE);
        NetworkAlphaSettingDao.insert(null, 2002, NetworkAlphaSettingEnum.START_MESSAGE, "Find and connect with the people that matter to you &mdash; at MIT Sloan");

        NetworkAlphaSettingDao.deleteByNetworkIdAndSetting(null, 2002, NetworkAlphaSettingEnum.START_BODY);
        NetworkAlphaSettingDao.insert(null, 2002, NetworkAlphaSettingEnum.START_BODY, "<div id=\"video\" class=\"glow\">\n" +
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
            addedQuestionRef = QuestionServices.insert(userId, networkId, "What MIT Sloan program are you in?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "MBA",
                        "MBA: Fellow",
                        "MBA: Executive",
                        "Master of Finance",
                        "MSMS (Master of Science in Management Studies)",
                        "SDM (System Design and Management)",
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("program", addedQuestion);
        }

        {
             // Adding question
             addedQuestionRef = QuestionServices.insert(userId, networkId, "Are you in the LGO program?", 50, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

             // Adding options
             addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
             QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                 {
                         "Yes",
                         "No"
                 });
             addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

             // Adding flow rules
             option = questions.get("program").findOptionByText("MBA");
             FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

             // Adding question to list
             questions.put("lgo", addedQuestion);

            {
                {
                     // Adding question
                     addedQuestionRef = QuestionServices.insert(userId, networkId, "Which summer LGO team were you assigned?", 50, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                     // Adding options
                     addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                     QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                         {
                                 "1",
                                 "2",
                                 "3",
                                 "4",
                                 "5",
                                 "6",
                                 "7",
                                 "8",
                                 "9"
                         });
                     addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                     // Adding flow rules
                     option = questions.get("lgo").findOptionByText("Yes");
                     FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                     // Adding question to list
                     questions.put("lgo-summer", addedQuestion);
                }

                {
                     // Adding question
                     addedQuestionRef = QuestionServices.insert(userId, networkId, "Which is your favorite LGO partner company?", 50, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                     // Adding options
                     addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                     QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                         {
                                 "ABB                                       ",
                                 "Amazon                                    ",
                                 "Amgen Inc.                                ",
                                 "Beth Israel Deaconess Medical Center      ",
                                 "The Boeing Company                        ",
                                 "Caterpillar Inc.                          ",
                                 "Cisco Systems Inc.                        ",
                                 "Dell Inc.                                 ",
                                 "General Motors Corporation                ",
                                 "Genzyme Corporation                       ",
                                 "Inditex S.A. (Zara)                       ",
                                 "Intel Corporation                         ",
                                 "Johnson & Johnson                         ",
                                 "Kimberly-Clark Corporation                ",
                                 "Massachusetts General Hospital            ",
                                 "National Grid                             ",
                                 "Nike Inc.                                 ",
                                 "Nokia Corporation                         ",
                                 "Novartis AG                               ",
                                 "Raytheon Company                          ",
                                 "Spirit AeroSystems                        ",
                                 "United Technologies Corporation           ",
                                 "Verizon Wireless                          "
                         });
                     addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                     // Adding flow rules
                     option = questions.get("lgo").findOptionByText("Yes");
                     FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                     // Adding question to list
                     questions.put("lgo-partner", addedQuestion);
                }

                {
                     // Adding question
                     addedQuestionRef = QuestionServices.insert(userId, networkId, "Which LGO engineering program are you in?", 50, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false   );

                     // Adding options
                     addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                     QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                         {
                                 "Aeronautics and Astronautics                ",
                                 "Biological Engineering                      ",
                                 "Chemical Engineering                        ",
                                 "Civil and Environment Engineering           ",
                                 "Electrical Engineering and Computer Science ",
                                 "Engineering Systems                         ",
                                 "Mechanical Engineering                      "
                         });
                     addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                     // Adding flow rules
                     option = questions.get("lgo").findOptionByText("Yes");
                     FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                     // Adding question to list
                     questions.put("lgo-program", addedQuestion);
                }

            }
        }


        {
             // Adding question
             addedQuestionRef = QuestionServices.insert(userId, networkId, "Have you already completed an MBA summer internship?", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

             // Adding options
             addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
             QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                 {
                         "Yes",
                         "No"
                 });
             addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

             // Adding flow rules
             option = questions.get("program").findOptionByText("MBA");
             FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

             option = questions.get("program").findOptionByTextStartsWith("MSMS");
             FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

             option = questions.get("program").findOptionByTextStartsWith("SDM");
             FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

             // Adding question to list
             questions.put("internship", addedQuestion);

             {
                 // Adding question
                 addedQuestionRef = QuestionServices.insert(userId, networkId, "With what company or incubator did you complete your main summer internship?", 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                 // Adding options
                 addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                 QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                     {
                            "360Chestnut                               ",
                            "3M                                        ",
                            "A.T. Kearney                              ",
                            "Accel Partners                            ",
                            "Accenture                                 ",
                            "Acciona Energy                            ",
                            "Adobe Systems                             ",
                            "Advent International                      ",
                            "Advisors                                  ",
                            "Aetna                                     ",
                            "AIG (American International Group, Inc.)  ",
                            "Altman Vilandrie & Company                ",
                            "Amazon                                    ",
                            "American Airlines                         ",
                            "American Express Company                  ",
                            "Amgen                                     ",
                            "Analysis Group                            ",
                            "AOL                                       ",
                            "Apple                                     ",
                            "Arcadia Solutions                         ",
                            "Arthur D. Little                          ",
                            "Asian Development Bank                    ",
                            "athenahealth                              ",
                            "Audible                                   ",
                            "B Lab                                     ",
                            "Bain & Company                            ",
                            "Bain Capital                              ",
                            "Banco Santander                           ",
                            "Bank of America Merrill Lynch             ",
                            "Bank of Tokyo- Mitsubishi UFJ             ",
                            "Barclays Capital                          ",
                            "Barclays Capital Asia Ltd.                ",
                            "Baring Private Equity Asia                ",
                            "Bayer                                     ",
                            "Bechtel Enterprises                       ",
                            "Beehive (E52 Incubator)                   ",
                            "Bessemer Venture Partners                 ",
                            "Biogen Idec                               ",
                            "Black Coral Capital                       ",
                            "BlackRock                                 ",
                            "BNP Paribas                               ",
                            "Boeing                                    ",
                            "Booz & Company                            ",
                            "Boston Company                            ",
                            "Boston Consulting Group                   ",
                            "BP                                        ",
                            "Brattle Group                             ",
                            "Bridgewater Associates                    ",
                            "Burger King                               ",
                            "CA Technologies                           ",
                            "Cambridge Associates                      ",
                            "Capital One                               ",
                            "Carbon Recycling International            ",
                            "Celfin Capital                            ",
                            "Censeo Consulting Group                   ",
                            "Charles River Associates                  ",
                            "Chartis Group                             ",
                            "Cisco Systems                             ",
                            "Citi                                      ",
                            "Coach                                     ",
                            "Company                                   ",
                            "Cornerstone Research                      ",
                            "Corning                                   ",
                            "Credit Suisse                             ",
                            "CSMG Global                               ",
                            "Cummins                                   ",
                            "Dalberg � Global Development              ",
                            "Dell                                      ",
                            "Deloitte                                  ",
                            "Deutsche Bank                             ",
                            "Diageo                                    ",
                            "Duff & Phelps, LLC                        ",
                            "DuPont                                    ",
                            "Eaton Vance Investment Managers           ",
                            "eBay                                      ",
                            "EDF Climate Corps                         ",
                            "Education Pioneers                        ",
                            "Eli Lilly and Company                     ",
                            "EnerNOC                                   ",
                            "Ermenegildo Zegna                         ",
                            "ExxonMobil                                ",
                            "Facebook                                  ",
                            "Far East Organization                     ",
                            "FEIS Trading                              ",
                            "Fidelity International Limited            ",
                            "Fidelity Investments                      ",
                            "FSA (Trust Center Incubator)              ",
                            "FSG Social Impact Advisors                ",
                            "General Electric (GE)                     ",
                            "General Motors (GM)                       ",
                            "Genzyme                                   ",
                            "GETCO                                     ",
                            "Goldman Sachs                             ",
                            "Google                                    ",
                            "Group                                     ",
                            "Groupon                                   ",
                            "GSR Ventures                              ",
                            "Gucci                                     ",
                            "Harris Williams                           ",
                            "Helion Venture Partners                   ",
                            "HSBC                                      ",
                            "HubSpot                                   ",
                            "IBM                                       ",
                            "ICICI Equity Research                     ",
                            "IDEO                                      ",
                            "IDEXX Laboratories                        ",
                            "Infosys Consulting                        ",
                            "Infosys Technologies Limited              ",
                            "Infotrieve                                ",
                            "Innosight                                 ",
                            "Innovation Center for U.S. Dairy          ",
                            "InsightSquared                            ",
                            "Intel                                     ",
                            "International Finance Corporation         ",
                            "ITA Software                              ",
                            "Jane St                                   ",
                            "Jefferies & Company                       ",
                            "Johnson & Johnson                         ",
                            "JP Morgan                                 ",
                            "JPMorgan Chase                            ",
                            "Kettle Cuisine                            ",
                            "Koch Industries                           ",
                            "L.E.K. Consulting (LEK)                   ",
                            "LAN Airlines                              ",
                            "Lark Technologies                         ",
                            "Liberty Mutual                            ",
                            "Macquarie Group                           ",
                            "MasterCard Worldwide                      ",
                            "McKinsey & Company                        ",
                            "MedImmune                                 ",
                            "Medtronic                                 ",
                            "Merck & Co.                               ",
                            "Microsoft                                 ",
                            "Millennium Pharmaceuticals                ",
                            "MocoSpace                                 ",
                            "Monitor Group                             ",
                            "Morgan Stanley                            ",
                            "Movoto, LLC                               ",
                            "Mubadala Development Company              ",
                            "National Basketball Association           ",
                            "National Hockey League (NHL)              ",
                            "Navigant Consulting                       ",
                            "Nike                                      ",
                            "Nokia                                     ",
                            "Oak Investment Partners                   ",
                            "OC&C Strategy Consultants                 ",
                            "OCP Group                                 ",
                            "Oliver Wyman                              ",
                            "OmniGuide                                 ",
                            "Opera Solutions                           ",
                            "PA Consulting Group                       ",
                            "Parametric Technology                     ",
                            "Parthenon Group                           ",
                            "Philips                                   ",
                            "PHOTON Consulting                         ",
                            "PIMCO                                     ",
                            "PricewaterhouseCoopers                    ",
                            "PRTM                                      ",
                            "Putnam Associates                         ",
                            "QGEN                                      ",
                            "Qiming Venture Partners                   ",
                            "Raymond James                             ",
                            "Raytheon*                                 ",
                            "RBS                                       ",
                            "RealNetworks                              ",
                            "Rhythmia Medical                          ",
                            "Robert W. Baird & Co.                     ",
                            "Roland Berger Strategy Consultants        ",
                            "Salesforce.com                            ",
                            "Sambreel Holdings                         ",
                            "Samsung Global Strategy                   ",
                            "Samsung Mobile                            ",
                            "Scout Capital                             ",
                            "Sealed Air                                ",
                            "Sears Holdings                            ",
                            "Siemens AG                                ",
                            "Smith & Nephew                            ",
                            "Sovereign Bank/Banco Santander            ",
                            "Standard Chartered Wholesale Banking      ",
                            "Synthetic Genomics                        ",
                            "T. Rowe Price                             ",
                            "Telsey Advisory Group                     ",
                            "Tencent Technology                        ",
                            "The Blackstone Group                      ",
                            "The World Bank                            ",
                            "Thomson Reuters                           ",
                            "TIAX LLC                                  ",
                            "Transparent Language                      ",
                            "Travelers                                 ",
                            "TripAdvisor                               ",
                            "UBS                                       ",
                            "Union Pacific Railroad Company            ",
                            "United States Department of Energy (USPS) ",
                            "UPS Supply Chain Solutions                ",
                            "Vale                                      ",
                            "Visa                                      ",
                            "VMWare                                    ",
                            "Walt Disney Company                       ",
                            "Wellington Management                     ",
                            "Women�s World Banking                     ",
                            "XL Hybrids                                ",
                            "Zynga                                     "
                     });
                 addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                 // Adding flow rules
                 option = questions.get("internship").findOptionByText("Yes");
                 FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                 // Adding question to list
                 questions.put("companies", addedQuestion);
             }
         }


        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Which 'Ocean' have you been assigned?", 20, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "I dont't know yet   ",
                        "Atlantic            ",
                        "Baltic              ",
                        "Caribbean           ",
                        "Indian              ",
                        "Pacific             ",
                        "Mediterranean       "
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            option = questions.get("program").findOptionByText("MBA");
            FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

            // Adding question to list
            questions.put("ocean", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Which 'Bird' team were you assigned in your 'Ocean'?", 50, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Gull        ",
                        "Pelican     ",
                        "Penguin     "
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            option = questions.get("ocean").findOptionByText("Atlantic");
            FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

            option = questions.get("ocean").findOptionByText("Baltic");
            FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

            option = questions.get("ocean").findOptionByText("Caribbean");
            FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

            option = questions.get("ocean").findOptionByText("Indian");
            FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

            option = questions.get("ocean").findOptionByText("Pacific");
            FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

            option = questions.get("ocean").findOptionByText("Mediterranean");
            FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

            // Adding question to list
            questions.put("ocean-bird", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "In your career, which industries have you worked at?", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                {
                    "Accounting                           ",
                    "Advertising                          ",
                    "Aerospace                            ",
                    "Agriculture                          ",
                    "Airlines                             ",
                    "Apparel & Accessories                ",
                    "Automotive                           ",
                    "Banking                              ",
                    "Biotechnology                        ",
                    "Broadcasting                         ",
                    "Brokerage                            ",
                    "Chemical                             ",
                    "Computer & Hardware                  ",
                    "Construction                         ",
                    "Consulting                           ",
                    "Consumer Products                    ",
                    "Cosmetics                            ",
                    "Defense                              ",
                    "Education                            ",
                    "Electronics                          ",
                    "Energy                               ",
                    "Engineering                          ",
                    "Entertainment & Leisure              ",
                    "Financial Services                   ",
                    "Food, Beverage & Tobacco             ",
                    "Health Care                          ",
                    "Internet (E-Commerce, Web, Services) ",
                    "Investment Banking                   ",
                    "Legal                                ",
                    "Manufacturing                        ",
                    "Music                                ",
                    "Pension Funds                        ",
                    "Pharmaceuticals                      ",
                    "Private Equity                       ",
                    "Publishing                           ",
                    "Real Estate                          ",
                    "Retail & Wholesale                   ",
                    "Securities & Commodity Exchanges     ",
                    "Software                             ",
                    "Sports                               ",
                    "Technology                           ",
                    "Telecommunications                   ",
                    "Television                           ",
                    "Transportation                       ",
                    "Venture Capital                      "
                });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("industries", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "How many years of work experience do you have?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "0 - 2        ",
                        "2 - 4        ",
                        "4 - 6        ",
                        "6 - 8        ",
                        "8 - 10       ",
                        "10 - 15      ",
                        "15 - 20      ",
                        "20 +         "
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("years", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "The organizations you've worked at, they have been...", 50, 4, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                {
                    "Large companies (3000+ employees)",
                    "Medium companies (200+ employees)",
                    "Small companies (20+ employees)",
                    "Startups                  ",
                    "Academic settings         ",
                    "Government agencies        "
                });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("b", addedQuestion);
        }


        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Which sector or industry would like to continue after MIT Sloan?", 50, 2, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                {
                        "Accounting                   ",
                        "Consulting                   ",
                        "Education                    ",
                        "Energy                       ",
                        "Financial Services           ",
                        "Health Care                  ",
                        "Marketing                    ",
                        "Non-profits (NGOs)           ",
                        "Operations                   ",
                        "Private Equity               ",
                        "Startups & Entrepreneurship  ",
                        "Technology                   ",
                        "Venture Capital              "
                });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("goto", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Which regions of the world you would like to work in?", 50, 2, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                {
                        "Africa           ",
                        "Asia             ",
                        "Australia        ",
                        "Central America  ",
                        "Central Asia     ",
                        "Eastern Europe   ",
                        "Middle East      ",
                        "North America    ",
                        "South America    ",
                        "South East Asia  ",
                        "Western Europe   "
                });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("region", addedQuestion);
        }

        // What courses are you taking?
        {

            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Which MIT Sloan courses will you be taking during Fall 2012?", 500, 15, AnswerVisibilityEnum.PROTECTED, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                {
                     "15.011: Economic Analysis for Business Decisions"                                              ,
                     "15.013: Industrial Economics for Strategic Decisions"                                           ,
                     "15.021J: Real Estate Economics"                                                                 ,
                     "15.031J: Energy Decisions, Markets, and Policies"                                              ,
                     "15.054J: The Airline Industry"                                                                  ,
                     "15.062J: Data Mining: Finding the Data and Models that Create Value"                           ,
                     "15.073J: Logistical & Transportation Planning Methods"                                          ,
                     "15.081J: Introduction to Mathematical Programming"                                              ,
                     "15.082J: Network Optimization"                                                                  ,
                     "15.085J: Fundamentals of Probability"                                                           ,
                     "15.093J: Optimization Methods"                                                                  ,
                     "15.094J: Robust Modeling, Optimization and Computation"                                        ,
                     "15.121J: Clinical Trials in Biomedical Enterprise"                                              ,
                     "15.124J: Evaluating a Biomedical Business Concept"                                             ,
                     "15.128J: Neurotechnology Ventures"                                                              ,
                     "15.136J: Principles and Practice of Drug Development"                                           ,
                     "15.231J: Enabling an Energy Efficient Society"                                                  ,
                     "15.269: Literature, Ethics, and Authority"                                                      ,
                     "15.279: Management Communication for Undergraduates"                                            ,
                     "15.301: Managerial Psychology Laboratory (meets w/15.310)"                                      ,
                     "15.305: Special Seminar in Organization Studies: Leadership and Management"                     ,
                     "15.310: Managerial Psychology (meets w/15.301)"                                                 ,
                     "15.342: Organizations and Environments"                                                         ,
                     "15.347: Doctoral Seminar in Research Methods I (meets with with 21A.861)"                       ,
                     "15.348: Doctoral Seminar in Research Methods II"                                               ,
                     "15.366: Energy Ventures"                                                                        ,
                     "15.369: Corporate Entrepreneurship: Strategies for Technology-Based New Business Development"   ,
                     "15.371J: Innovation Teams"                                                                      ,
                     "15.375J: Development Ventures"                                                                  ,
                     "15.383: Entrepreneurship and Sustainable Economic Development: The Case of Puerto Rico"         ,
                     "15.386: Managing in Adversity"                                                                 ,
                     "15.387: Technology Sales and Sales Management"                                                  ,
                     "15.389: Global Entrepreneurship Lab"                                                            ,
                     "15.390: New Enterprises"                                                                        ,
                     "15.391: Early Stage Capital"                                                                    ,
                     "15.399: Entrepreneurship Lab"                                                                  ,
                     "15.401: Finance Theory I"                                                                       ,
                     "15.402: Finance Theory II"                                                                      ,
                     "15.411: Finance Theory I"                                                                       ,
                     "15.412: Finance Theory II"                                                                      ,
                     "15.416J: Introduction to Financial Economics"                                                   ,
                     "15.426J: Real Estate Finance and Investment"                                                    ,
                     "15.433: Investments"                                                                            ,
                     "15.434: Advanced Corporate Finance"                                                             ,
                     "15.437: Options and Futures Markets"                                                            ,
                     "15.440J: Advanced Financial Economics I"                                                        ,
                     "15.450: Analytics of Finance"                                                                  ,
                     "15.451: Proseminar in Capital Markets/Investment Management"                                    ,
                     "15.452: Proseminar in Corporate Finance/Investment Banking"                                     ,
                     "15.492: Foundations of Institutional Portfolio Management"                                      ,
                     "15.493: POF: Perspectives on Investment Management"                                             ,
                     "15.494: POF: Multinational Business Finance"                                                    ,
                     "15.495: POF: Quantitative Investment Management"                                                ,
                     "15.501: Corporate Financial Accounting"                                                         ,
                     "15.516: Corporate Financial Accounting"                                                         ,
                     "15.535: Business Analysis Using Financial Statements"                                           ,
                     "15.539: Doctoral Seminar in Accounting"                                                         ,
                     "15.567: The Economics of Information: Strategy, Structure and Pricing"                         ,
                     "15.569: Leadership Lab: Leading Sustainable Systems (cont. thru IAP)"                           ,
                     "15.615: Basic Business Law for the Entrepreneur and Manager"                                   ,
                     "15.616: Basic Business Law, Tilted Towards Innovation and Strategy"                             ,
                     "15.657J: Sustainability, Trade, and the Environment"                                            ,
                     "15.665: Power & Negotiation"                                                                    ,
                     "15.678J: Political Econ. I: Theories of the State & the Economy"                                ,
                     "15.761: Introduction to Operations Management"                                                 ,
                     "15.767: Intro to Healthcare Delivery in the U.S.: Market & System Challenges"                   ,
                     "15.768: Management of Services: Concepts, Design, and Delivery"                                 ,
                     "15.769: Operations Strategy"                                                                    ,
                     "15.770J: Logistics Systems"                                                                     ,
                     "15.792J: Global Operations Leadership Seminar"                                                  ,
                     "15.795: Seminar in Oper. Mgmt: Behavioral Decision Theories and Applications"                   ,
                     "15.810: Marketing Management"                                                                   ,
                     "15.821: Listening to the Customer"                                                              ,
                     "15.822: Strategic Market Measurement"                                                           ,
                     "15.833: Business to Business Marketing"                                                         ,
                     "15.838: Research Seminar in Marketing"                                                          ,
                     "15.839: Workshop in Marketing"                                                                 ,
                     "15.871: Introduction to System Dynamics"                                                        ,
                     "15.872: System Dynamics II"                                                                     ,
                     "15.877: Professional Seminar in Sustainability"                                                 ,
                     "15.900: Competitive Strategy"                                                                   ,
                     "15.903: Managing the Modern Organization: Organizational Economics and Corporate Strategy"      ,
                     "15.904: Advanced Strategic Management"                                                          ,
                     "15.933: Strategic Opportunities in Energy"                                                     ,
                     "15.941J: Leadership in Real Estate"                                                             ,
                     "15.S02: SSIM: Business Models for Scale and Sustainability in Global Health"                    ,
                     "15.S04: SSIM: POF: Applied Fixed Income Strategies"                                             ,
                     "15.S07: SSIM: Healthcare Ventures"                                                              ,
                     "15.S09: SSIM: Improvisational Leadership: In the Moment Leadership Skills"                      ,
                     "15.S10: SSIM: Global Controversies: Economics, Politics, and Institutions"                      ,
                     "15.S12: SSIM: Data Technologies for Quantitative Finance"                                       ,
                     "15.S16: SSIM: Tax Research Seminar"                                                             ,
                     "15.S17: SSIM: Application of Advanced Entrepreneurial Techniques"                               ,
                     "15.S21: SSIMt: Leadership & Ethics"                                                             ,
                     "15.S22: SSIM: Practical Leadership"                                                             ,
                     "15.S23: U-Lab: Leading Profound Innovation for Sustainability"                                  ,
                     "15.S66: Workshop in IT: Collaborative Innovation Networks"
                });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("mba-courses", addedQuestion);
        }

    }
}
