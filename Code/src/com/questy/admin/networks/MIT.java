package com.questy.admin.networks;

import com.questy.dao.*;
import com.questy.domain.*;
import com.questy.enums.AnswerVisibilityEnum;
import com.questy.enums.NetworkAlphaSettingEnum;
import com.questy.enums.RoleEnum;
import com.questy.services.FlowRuleServices;
import com.questy.services.NetworkServices;
import com.questy.services.QuestionOptionServices;
import com.questy.services.QuestionServices;

import java.util.*;

public class MIT {

    public static void main(String[] args) throws Exception {


        // Adding start message
        NetworkAlphaSettingDao.deleteByNetworkIdAndSetting(null, 2000, NetworkAlphaSettingEnum.START_MESSAGE);
        NetworkAlphaSettingDao.insert(null, 2000, NetworkAlphaSettingEnum.START_MESSAGE, "Find and talk about science and engineering with the world's best &mdash; MIT");

        NetworkAlphaSettingDao.deleteByNetworkIdAndSetting(null, 2000, NetworkAlphaSettingEnum.START_BODY);
        NetworkAlphaSettingDao.insert(null, 2000, NetworkAlphaSettingEnum.START_BODY, "<div id=\"video\" class=\"glow\">\n" +
                "<iframe src=\"http://player.vimeo.com/video/46590201\" width=\"500\" height=\"281\" frameborder=\"0\" webkitAllowFullScreen mozallowfullscreen allowFullScreen></iframe>\n" +
                "</div>");


//        create();
    }




    public static void create() throws Exception {

        Integer userId = 2;

        Integer addedQuestionRef = null;
        Question addedQuestion = null;
        QuestionOption option = null;
        Map<String, Question> questions = new HashMap<String, Question>();

        // Create network
        Integer networkId = NetworkDao.insert(null, "MIT");

        // Add question with options and flow rule
        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Currently you are a...", 30, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                        "Undergraduate",
                        "Graduate",
                        "Alumni",
                        "PhD. (Doctorate)",
                        "Visiting Scholar",
                        "Post Doctorate",
                        "Professor or Faculty",
                        "Research Assistant",
                        "Administrator",
                        "Family of a MIT member"
                    });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());


            // Adding question to list
            questions.put("a", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "What course(s) are you currently in?", 100, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                {
                    "None, I am undecided",
                    "1: Civil and Environmental Engineering",
                    "2: Mechanical Engineering",
                    "3: Materials Science and Engineering",
                    "4: Architecture",
                    "5: Chemistry",
                    "6: EE & Computer Science",
                    "7: Biology",
                    "8: Physics",
                    "9: Brain and Cognitive Sciences",
                    "10: Chemical Engineering",
                    "11: Urban Studies and Planning",
                    "12: Planetary Sciences",
                    "13: Ocean Engineering",
                    "14: Economics",
                    "15: Business / Management",
                    "16: Aeronautics and Astronautics",
                    "17: Political Science",
                    "18: Mathematics",
                    "20: Biological Engineering",
                    "21: Humanities",
                    "21A: Anthropology",
                    "21F: Foreign Languages and Literatures",
                    "21H: History",
                    "21L: Literature",
                    "21M: Music and Theater Arts",
                    "21WGS: Women's and Gender Studies",
                    "21W: Writing and Humanistic Studies",
                    "22: Nuclear Science and Engineering",
                    "24: Linguistics and Philosophy",
                    "CC: Concourse Program",
                    "CMS: Comparative Media Studies",
                    "CSB: Computational and Systems Biology",
                    "EC: Edgerton Center",
                    "ES: Experimental Study Group",
                    "ESD: Engineering Systems Division",
                    "HST: Health Sciences and Technology",
                    "MAS: Media Arts and Sciences",
                    "ROTC AS: Aerospace Studies",
                    "ROTC MS: Military Science",
                    "ROTC NS: Naval Science",
                    "STS: Science, Technology, and Society",
                    "SWE: Engineering School-Wide Electives"
                });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("b", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "What year did you start your current program?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                {
                    "2014",
                    "2013",
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
                    "1998"
                });
            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("c", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Are you currently doing research at MIT?", 20, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

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
            questions.put("research", addedQuestion);
        }



        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "In what programs or topics are you currently performing research?", 500, 10, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                {
                    "Aeronautics and Astronautics                                           ",
                    "Aerospace Computational Engineering                                    ",
                    "Aerospace Systems                                                      ",
                    "Affective Computing                                                    ",
                    "Aga Khan Program for Islamic Architecture                              ",
                    "Air Transportation Systems                                             ",
                    "Air-Breathing Propulsion                                               ",
                    "Aircraft Systems Engineering                                           ",
                    "American Politics and Public Policy                                    ",
                    "Applied Mechanics                                                      ",
                    "Applied Ocean Science and Engineering                                  ",
                    "Applied Plasma Physics                                                 ",
                    "Aquatic Biology                                                        ",
                    "Aquatic Chemistry                                                      ",
                    "Archaeological MaterialsBiological and Polymeric Materials             ",
                    "Architecture and Urbanism                                              ",
                    "Architecture Design                                                    ",
                    "Artificial Intelligence                                                ",
                    "Assistive Technologies                                                 ",
                    "Astrophysics, Space and Planetary Physics                              ",
                    "Atmospheric Science (dynamics and chemistry)                           ",
                    "Atomic and Optical Physics                                             ",
                    "Automotive & Aircraft Engines                                          ",
                    "Autonomous Systems                                                     ",
                    "Bio-Organic Chemistry                                                  ",
                    "Biochemical, Chemical, and Structural Microbiology                     ",
                    "Biochemistry                                                           ",
                    "Biodegradable Polymers                                                 ",
                    "Bioelectrical Engineering                                              ",
                    "Bioenergy and Metabolic Diversity                                      ",
                    "Bioengineering                                                         ",
                    "Bioinformatics and Computational Microbiology                          ",
                    "Bioinformatics/Computational Biology                                   ",
                    "Biological and Physiological Transport Phenomena                       ",
                    "Biological Chemistry                                                   ",
                    "Biological Design and Synthetic Biology                                ",
                    "Biological Engineering                                                 ",
                    "Biological Imaging and Functional Measurement                          ",
                    "Biological Oceanography                                                ",
                    "Biomaterials                                                           ",
                    "Biomechanics & Neural Control of Movement                              ",
                    "Biomechatronics                                                        ",
                    "Biomedical Engineering                                                 ",
                    "Biomolecular Engineering                                               ",
                    "Biophysics                                                             ",
                    "Biophysics, Medical Physics                                            ",
                    "Biopolymers and Biomaterials                                           ",
                    "Biorobotics                                                            ",
                    "Building Technology                                                    ",
                    "Business Strategy and Entrepreneurship                                 ",
                    "Camera Culture                                                         ",
                    "Cancer Biology                                                         ",
                    "Cell and Tissue Engineering                                            ",
                    "Cell Biology                                                           ",
                    "Cellular and Molecular Neuroscience                                    ",
                    "Changing Places                                                        ",
                    "Chemical Engineering                                                   ",
                    "Chemical Oceanography                                                  ",
                    "Circuit Design                                                         ",
                    "Civil and Environmental Engineering                                    ",
                    "Civil Engineering                                                      ",
                    "Climate Physics and Chemistry                                          ",
                    "Coastal Oceanography                                                   ",
                    "Cognitive Machines                                                     ",
                    "Cognitive Science                                                      ",
                    "Colloids and Surfactants                                               ",
                    "Combustion                                                             ",
                    "Communications                                                         ",
                    "Communications and Networks                                            ",
                    "Comparative Politics                                                   ",
                    "Complex Socio-Technical System Analysis                                ",
                    "Composite Materials and Structures                                     ",
                    "Composites Processing                                                  ",
                    "Computation                                                            ",
                    "Computational Biology                                                  ",
                    "Computational Biology and Bioinformatics                               ",
                    "Computational Fluid Dynamics                                           ",
                    "Computational Materials Science                                        ",
                    "Computational Mechanics                                                ",
                    "Computational Modeling of Biological and Physiological Systems         ",
                    "Computer Aided Engineering                                             ",
                    "Computer Graphics                                                      ",
                    "Computer Networks                                                      ",
                    "Computer Science                                                       ",
                    "Computer Systems and Architecture                                      ",
                    "Computer-Aided Design/Manufacturing                                    ",
                    "Computing Culture                                                      ",
                    "Condensed Matter Physics                                               ",
                    "Construction Engineering and Technology                                ",
                    "Context-Aware Computing                                                ",
                    "Controls                                                               ",
                    "Cryogenics                                                             ",
                    "Design                                                                 ",
                    "Design and Computation                                                 ",
                    "Developmental Biology                                                  ",
                    "Devices and Materials                                                  ",
                    "Discovery and Delivery of Molecular Therapeutics                       ",
                    "Dynamics                                                               ",
                    "Earthquake Engineering                                                 ",
                    "Ecology and Environmental/Geo Microbiology                             ",
                    "Ecology and Evolution                                                  ",
                    "Electrical Engineering and Computer Science                            ",
                    "Electromagnetic Energy, Fields and Waves                               ",
                    "Electronic, Photonic, and Magnetic Materials                           ",
                    "Energy                                                                 ",
                    "Energy and Power                                                       ",
                    "Energy and the Environment                                             ",
                    "Engineering Geology                                                    ",
                    "Engineering Systems                                                    ",
                    "Environmental Chemistry                                                ",
                    "Environmental Engineering                                              ",
                    "Environmental Fluid Mechanics                                          ",
                    "Environmental Geotechnology                                            ",
                    "Environmental Microbiology                                             ",
                    "Evolution                                                              ",
                    "Experimental Plasma Physics                                            ",
                    "Fibers & Polymers                                                      ",
                    "Finite Elements                                                        ",
                    "Fission Reactor Systems Engineering                                    ",
                    "Fluid Mechanics                                                        ",
                    "Functional Genomics                                                    ",
                    "Functional Polymers                                                    ",
                    "Fusion Reactor Technology                                              ",
                    "Future of Learning                                                     ",
                    "Gene and Protein Networks                                              ",
                    "Genetic Toxicology                                                     ",
                    "Genetics                                                               ",
                    "Genetics and Physiology                                                ",
                    "Genomics and Proteomics                                                ",
                    "Genomics and Systems Microbiology                                      ",
                    "Geobiology                                                             ",
                    "Geochemistry                                                           ",
                    "Geoenvironmental Engineering                                           ",
                    "Geology                                                                ",
                    "Geophysics                                                             ",
                    "Geotechnical Engineering                                               ",
                    "Groundwater Hydrology                                                  ",
                    "Health Care, Pharmaceutical, and Service Industries                    ",
                    "Heat and Mass Transfer                                                 ",
                    "High Energy and Nuclear Fusion Research, Relativistic Beam Physics     ",
                    "High Energy and Nuclear Physics                                        ",
                    "High Performance Polymers                                              ",
                    "High Performance Structures                                            ",
                    "High-performance Structural                                            ",
                    "History, Theory, and Criticism of Architecture                         ",
                    "History, Theory, and Criticism of Art                                  ",
                    "Human Genetics                                                         ",
                    "Human-Machine Systems                                                  ",
                    "Human-Systems Engineering                                              ",
                    "Humans in Aerospace                                                    ",
                    "Hydrodynamics                                                          ",
                    "Hydrodynamics of Vehicles                                              ",
                    "Hydrology                                                              ",
                    "Imaging and Image Informatics                                          ",
                    "Immunology                                                             ",
                    "Immunology and Host-Microbe Interactions                               ",
                    "Industrial Relations                                                   ",
                    "Infectious Disease and Immunology                                      ",
                    "Information Technology                                                 ",
                    "Inorganic Chemistry                                                    ",
                    "Instrumentation                                                        ",
                    "Instrumentation Engineering                                            ",
                    "Internal and External Combustion Engines                               ",
                    "International Relations                                                ",
                    "International Relations and Foreign Policy                             ",
                    "Leaders for Global Operations                                          ",
                    "Lifelong Kindergarten                                                  ",
                    "Linguistics                                                            ",
                    "Liquid Crystalline Polymers                                            ",
                    "Logistics and Supply Chain Management                                  ",
                    "Macromolecular Biochemistry & Biophysics                               ",
                    "Management of Technology                                               ",
                    "Manufacturing                                                          ",
                    "Marine Biology                                                         ",
                    "Marine Ecology                                                         ",
                    "Marine Geology and Geophysics                                          ",
                    "Marine Toxicology                                                      ",
                    "Material Engineering                                                   ",
                    "Materials                                                              ",
                    "Materials and Alloys                                                   ",
                    "Materials and Structures                                               ",
                    "Materials Economics and Manufacturing                                  ",
                    "Materials for Energy and the Environment                               ",
                    "Mechanical Behavior of Materials                                       ",
                    "Mechanical Engineering                                                 ",
                    "Mechanics                                                              ",
                    "Mechanics of Materials                                                 ",
                    "Media Fabrics                                                          ",
                    "MEMS and Nanotechnology                                                ",
                    "Metabolic Engineering and Biotechnology                                ",
                    "Metabolism of Drugs and Toxins                                         ",
                    "Micro-Electro-Mechanical Systems                                       ",
                    "Microbial Oceanography                                                 ",
                    "Microbial Pathogenesis                                                 ",
                    "Microbial Systems                                                      ",
                    "Microbiology                                                           ",
                    "Microfluids                                                            ",
                    "Models and Methods                                                     ",
                    "Molecular and Cellular Microbiology                                    ",
                    "Molecular Biophysics                                                   ",
                    "Molecular Ecology                                                      ",
                    "Molecular Epidemiology and Dosimetry                                   ",
                    "Molecular Machines                                                     ",
                    "Molecular Medicine and Human Diseases                                  ",
                    "Molecular Pharmacology                                                 ",
                    "Molecular, Cell and Tissue Biomechanics                                ",
                    "Molecular, Chemical and Environmental Carcinogenesis                   ",
                    "Music, Mind and Machine                                                ",
                    "Nanobiology and Microsystems                                           ",
                    "Nanoscale Engineering of Biological Systems                            ",
                    "Nanotechnology, Nanodevices, and Nanomaterials                         ",
                    "Networks, Distributed Simulation Systems                               ",
                    "Neurobiological Systems                                                ",
                    "Neurobiology                                                           ",
                    "Neuroengineering and Neuromedia                                        ",
                    "Neurosystems Biology                                                   ",
                    "New Tools for Genomics, Functional Genomics, Proteomics and Glycomics  ",
                    "Nondestructive Evaluation                                              ",
                    "Nuclear and Alternative Energy Systems Management & Policy             ",
                    "Nuclear Materials Engineering                                          ",
                    "Nuclear Science and Technology                                         ",
                    "Object-Based Media                                                     ",
                    "Ocean Acoustics                                                        ",
                    "Opera of the Future                                                    ",
                    "Operations Research                                                    ",
                    "Optical Engineering                                                    ",
                    "Optical Measurement                                                    ",
                    "Organic Chemistry                                                      ",
                    "Organizational Learning                                                ",
                    "Paleoceanography                                                       ",
                    "Personal Robots                                                        ",
                    "Philosophy                                                             ",
                    "Physical Chemistry                                                     ",
                    "Physical Language                                                      ",
                    "Physical Oceanography                                                  ",
                    "Physics and Media                                                      ",
                    "Physiology                                                             ",
                    "Planetary Sciences                                                     ",
                    "Plankton Ecology                                                       ",
                    "Plant Molecular Biology                                                ",
                    "Plasma Physics, Nuclear Fusion Research, Plasma Astrophysics           ",
                    "Political Philosophy and Social Theory                                 ",
                    "Polyelectrolytes                                                       ",
                    "Polymer Chemistry                                                      ",
                    "Polymer Mechanics                                                      ",
                    "Polymer Modeling                                                       ",
                    "Polymer Physics                                                        ",
                    "Polymer Processing                                                     ",
                    "Polymer Rheology                                                       ",
                    "Polymer Statistical Mechanics                                          ",
                    "Precision Engineering                                                  ",
                    "Predictive Toxicology and Metabolic Engineering                        ",
                    "Product and Process Design and Development, Technical Innovation       ",
                    "Project Management                                                     ",
                    "Quantum Information Science                                            ",
                    "Reactor Engineering                                                    ",
                    "Remote Sensing                                                         ",
                    "Responsive Environments                                                ",
                    "Risk and Safety Analysis and Decision-Making, Risk Management          ",
                    "Risk Assessment and Management                                         ",
                    "Robots, Manipulators and Teleoperators                                 ",
                    "Rock Mechanics                                                         ",
                    "Science, Space, and Technology Policy                                  ",
                    "Security Studies                                                       ",
                    "Sediment Transport                                                     ",
                    "Signal Processing                                                      ",
                    "Smart Cities                                                           ",
                    "Sociable Media                                                         ",
                    "Social and Organizational Psychology                                   ",
                    "Society of Mind                                                        ",
                    "Software Agents                                                        ",
                    "Soil Mechanics                                                         ",
                    "Space Propulsion                                                       ",
                    "Space Systems                                                          ",
                    "Speech and Mobility                                                    ",
                    "Structural Biology and Biophysics                                      ",
                    "Structural Engineering                                                 ",
                    "Structural Materials                                                   ",
                    "Structural Mechanics                                                   ",
                    "Structure/Property Relationships                                       ",
                    "Supramolecular Assembly                                                ",
                    "Synthetic Biology                                                      ",
                    "System Architecture, Systems Engineering                               ",
                    "System Safety                                                          ",
                    "Systems Biology                                                        ",
                    "Systems Design and Management                                          ",
                    "Systems Neuroscience                                                   ",
                    "Systems, Decision and Control                                          ",
                    "Tangible Media                                                         ",
                    "Technology and Policy                                                  ",
                    "Technology Policy for Socio-Economic Development                       ",
                    "Telepresence                                                           ",
                    "Theoretical Computer Science                                           ",
                    "Theoretical Plasma Physics                                             ",
                    "Thermodynamics                                                         ",
                    "Transportation                                                         ",
                    "Transportation and Information Systems                                 ",
                    "Transportation and Logistics                                           ",
                    "Transportation Economics                                               ",
                    "Transportation Policy                                                  ",
                    "Transportation Systems                                                 ",
                    "Tribology                                                              ",
                    "Underground Construction                                               ",
                    "Underwater Robotics                                                    ",
                    "Urban Transportation                                                   ",
                    "Viral Communications                                                   ",
                    "Virology and Phage Biology                                             ",
                    "Visual Studies                                                         ",
                    "Water Resources                                                        "
                });

            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            option = questions.get("research").findOptionByText("Yes");
            FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());


            // Adding question to list
            questions.put("e", addedQuestion);
        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "If you could solve any challenges, which ones would they be?", 10, 2, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                {
                    "Lack of drinkable water",
                    "Alternative energy",
                    "Sustainability",
                    "Global warming",
                    "Child abuse",
                    "Economic slavery",
                    "Reducing gap between rich and poor",
                    "Gender inequality",
                    "Lack of education",
                    "Causes of poverty",
                    "Racism",
                    "Lack of food and agriculture"
                });

            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("f", addedQuestion);
        }


        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Can others contact you to consult and collaborate with you?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                {
                    "Yes",
                    "Maybe",
                    "No"
                });

            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("i", addedQuestion);
        }
        {
            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "Can others contact you to rent or borrow items from you?", 50, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

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
                questions.put("j", addedQuestion);
            }

            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "What items can others rent or borrow from you?", 100, 10, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                                "SLR Photo Camera",
                                "SLR Video Camera",
                                "Apple iPad",
                                "Bicycle, mountain",
                                "Bicycle, road",
                                "Air conditioner",
                                "Microscope",
                                "Sony PlayStation 3",
                                "Microsoft XBox",
                                "Skiing gear",
                                "Snowboard",
                                "Air mattress",
                        });

                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                option = questions.get("j").findOptionByText("Yes");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());


                // Adding question to list
                questions.put("k", addedQuestion);
            }
        }





        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Where do you live?", 30, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                            "In MIT housing",
                            "Cambridge    ",
                            "Boston       ",
                            "Arlington    ",
                            "Belmont      ",
                            "Brookline    ",
                            "Everett      ",
                            "Malden       ",
                            "Medford      ",
                            "Melrose      ",
                            "Milton       ",
                            "Revere       ",
                            "Somerville   ",
                            "Watertown    ",
                            "Winchester   ",
                            "Winthrop     ",
                            "Chelsea      ",
                            "None of the above"
                    });

            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());


            // Adding question to list
            questions.put("city", addedQuestion);
        }

        {
            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "Where in MIT do you live?", 30, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                                "Alpha Tau Omega                        ",
                                "Ashdown House                          ",
                                "Baker House                            ",
                                "Bexley Hall                            ",
                                "Burton-Conner House                    ",
                                "Delta Kappa Epsilon                    ",
                                "East Campus Alumni Memorial Housing    ",
                                "Eastgate Apartments                    ",
                                "Edgerton House                         ",
                                "Kappa Alpha Theta                      ",
                                "Kappa Sigma                            ",
                                "MacGregor House                        ",
                                "Maseeh Hall                            ",
                                "McCormick Hall                         ",
                                "New House                              ",
                                "Next House                             ",
                                "Phi Beta Epsilon                       ",
                                "Random Hall                            ",
                                "Senior House                           ",
                                "Sidney-Pacific                         ",
                                "Simmons Hall                           ",
                                "Tang Residence Hall                    ",
                                "The Number Six Club (Delta Psi)        ",
                                "The Warehouse                          ",
                                "Theta Delta Chi                        ",
                                "Westgate Apartments                    "
                        });

                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                option = questions.get("city").findOptionByText("In MIT housing");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("mit", addedQuestion);
            }

            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "In which Cambridge neighborhood do you live?", 30, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                                "Alewife/Fresh Pond               ",
                                "Cambridgeport                    ",
                                "Central Square                   ",
                                "East Cambridge                   ",
                                "Harvard Square                   ",
                                "Inman Square                     ",
                                "Kendall Square                   ",
                                "Mid-Cambridge                    ",
                                "Mt. Auburn/Brattle Street        ",
                                "North Cambridge                  ",
                                "Porter Square                    ",
                                "Riverside                        ",
                        });

                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                option = questions.get("city").findOptionByText("Cambridge");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("cambridge", addedQuestion);
            }

            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "In which Boston neighborhood do you live?", 30, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                                "Allston/Brighton                    ",
                                "Back Bay/Bay State Road             ",
                                "Beacon Hill/Bay Village             ",
                                "Charlestown                         ",
                                "Chinatown                           ",
                                "Dorchester/Mattapan                 ",
                                "East Boston                         ",
                                "Fenway/Kenmore                      ",
                                "Hyde Park                           ",
                                "Jamaica Plain                       ",
                                "Mission Hill                        ",
                                "North End                           ",
                                "Roxbury                             ",
                                "South Boston                        ",
                                "South End/St. Botolph               ",
                                "West Roxbury/Roslindale             "
                        });

                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                option = questions.get("city").findOptionByText("Boston");
                FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                // Adding question to list
                questions.put("boston", addedQuestion);
            }


        }

        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Which places at MIT do you spend most of your time?", 100, 10, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Adding options
            addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
            QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                    {
                           "1: Pierce Laboratory                       ",
                           "2: Building 2                              ",
                           "3: Maclaurin Buildings (3)                 ",
                           "4: Maclaurin Buildings (4)                 ",
                           "5: Pratt School                            ",
                           "6: Eastman Laboratories                    ",
                           "6B: Solvent Storage                         ",
                           "6C: Building 6c                             ",
                           "7: Rogers Building                         ",
                           "7A: Rotch Library Extension                 ",
                           "8: Building 8                              ",
                           "9: Building 9                              ",
                           "10: Maclaurin Buildings (10)                ",
                           "11: Homberg Building                        ",
                           "12: Building 12                             ",
                           "12A: Waste Chemical Storage                  ",
                           "13: Bush Building                           ",
                           "14: Hayden Memorial Library                 ",
                           "16: Dorrance Building                       ",
                           "17: Wright Brothers Wind Tunnel             ",
                           "18: Dreyfus Building                        ",
                           "24: Building 24                             ",
                           "26: Compton Laboratories                    ",
                           "31: Sloan Laboratories                      ",
                           "32: Stata Center                            ",
                           "32P: Stata Center Garage                     ",
                           "33: Guggenheim Laboratory                   ",
                           "34: Eg&G Education Center                   ",
                           "35: Sloan Laboratory                        ",
                           "36: Fairchild Building (36)                 ",
                           "37: Mcnair Building                         ",
                           "38: Fairchild Building (38)                 ",
                           "39: Brown Building                          ",
                           "41: Building 41                             ",
                           "42: Cogeneration Plant                      ",
                           "43: Power Plant Annex                       ",
                           "44: Cyclotron                               ",
                           "46: BCSC                                    ",
                           "48: Parsons Laboratory                      ",
                           "50: Walker Memorial                         ",
                           "51: Wood Sailing Pavilion                   ",
                           "54: Green Building                          ",
                           "56: Whitaker Building                       ",
                           "57: Mit Alumni Pool                         ",
                           "62: Alumni Houses: Munroe Hayden Wood       ",
                           "64: Alumni Houses: Walcott Bemis Goodale    ",
                           "66: Landau Building                         ",
                           "68: Koch Biology Building                   ",
                           "76: D H Koch In Ficr                        ",
                           "E1: Gray House                              ",
                           "E2: Senior House                            ",
                           "E14: Building E14                            ",
                           "E15: Wiesner Building                        ",
                           "E17: Mudd Building                           ",
                           "E18: Ford Building (E18)                     ",
                           "E19: Ford Building (E19)                     ",
                           "E23: Health Services                         ",
                           "E25: Whitaker College                        ",
                           "E33: Rinaldi Tile                            ",
                           "E34: Building E34                            ",
                           "E38: Suffolk Building                        ",
                           "E40: Muckley Building                        ",
                           "E51: Tang Center                             ",
                           "E52: Sloan Building                          ",
                           "E53: Hermann Building                        ",
                           "E55: Eastgate                                ",
                           "E60: Arthur D Little Building                ",
                           "E62: Building E62                            ",
                           "N4: Albany Garage                           ",
                           "N9: Superconducting Test Facility           ",
                           "N10: High Voltage Research Lab               ",
                           "N16: Cooling Tower & Oil Reserve             ",
                           "N16A: Building N16a                           ",
                           "N16B: Fire Pump Room                          ",
                           "N16C: Building N16c                           ",
                           "N51: Building N51                            ",
                           "N52: Mit Museum                              ",
                           "N57: Building N57                            ",
                           "NW10: Edgerton House                          ",
                           "NW12: Nuclear Reactor Lab                     ",
                           "NW12A: Nuclear Reactor Labs                    ",
                           "NW13: Building Nw13                           ",
                           "NW14: Francis Bitter Magnet Lab (NW14)        ",
                           "NW15: Francis Bitter Magnet Lab (NW15)        ",
                           "NW16: Plasma Science & Fusion Center (NW16)   ",
                           "NW17: Plasma Science & Fusion Center (NW17)   ",
                           "NW20: Albany St Generator Shelter             ",
                           "NW21: Plasma Science & Fusion Center (NW21)   ",
                           "NW22: Plasma Science & Fusion Center (NW22)   ",
                           "NW30: 224 Albany Street                       ",
                           "NW35: Ashdown House                           ",
                           "NW61: Random Hall                             ",
                           "NW62: Volvo Garage                            ",
                           "NW86: 70 Pacific Street Dormitory             ",
                           "OC1: Endicott House                          ",
                           "OC1A: Edward Pennell Brooks Center            ",
                           "OC19: Bates Linear Accelerator                ",
                           "OC19A: Bates Linac: Administration Bldg        ",
                           "OC19B: Bates Linac: Research Bldg              ",
                           "OC19C: Bates Linac: Engineering Bldg           ",
                           "OC19D: Bates Linac: Lab Service                ",
                           "OC19E: Bates Linac: Warehouse #2               ",
                           "OC19F: Bates Linac: Warehouse #3               ",
                           "OC19G: Bates Linac: Barn                       ",
                           "OC21: Wallace Astrophysical Observatory       ",
                           "OC22: Westford Optics Facility (Haystack)     ",
                           "OC23: Wallace Geophysical Observatory         ",
                           "OC25: Haystack Observatory                    ",
                           "OC26: Haystack Atmospheric Sciences Building  ",
                           "OC31: Katahdin Hill 1                         ",
                           "OC31A: Katahdin Hill 1a                        ",
                           "OC32: Lexington Field House                   ",
                           "OC32A: Balloon House A                         ",
                           "OC32B: Balloon House B                         ",
                           "OC33: Annex 3                                 ",
                           "OC35: Annex V                                 ",
                           "OC36: Annex 6                                 ",
                           "OC36A: Annex 6 Trailer                         ",
                           "W1: Fariborz Maseeh Hall                    ",
                           "W2: Building W2                             ",
                           "W4: Mccormick Hall                          ",
                           "W5: Green Hall                              ",
                           "W7: Baker House                             ",
                           "W8: Pierce Boathouse                        ",
                           "W11: Religious Activities Center             ",
                           "W13: Bexley Hall                             ",
                           "W15: Mit Chapel                              ",
                           "W16: Kresge Auditorium                       ",
                           "W20: Stratton Student Center                 ",
                           "W31: Du Pont Athletic Gymnasium              ",
                           "W32: Du Pont Athletic Center                 ",
                           "W33: Rockwell Cage                           ",
                           "W34: Johnson Athletics Center                ",
                           "W35: Sports & Fitness Center                 ",
                           "W45: West Garage                             ",
                           "W51: Burton-Conner House                     ",
                           "W51C: 405 Memorial Drive                      ",
                           "W53: Carr Indoor Tennis Facility             ",
                           "W53B: Dupont Tennis Courts (Office)           ",
                           "W53C: Building W53c                           ",
                           "W53D: Carr Indoor Tennis Facility(Svc)        ",
                           "W56: Building W56                            ",
                           "W57: Building W57                            ",
                           "W59: Heinz Building                          ",
                           "W61: Macgregor House                         ",
                           "W70: New House                               ",
                           "W71: Next House                              ",
                           "W79: Simmons Hall                            ",
                           "W84: Tang Hall                               ",
                           "W85: Westgate                                ",
                           "W89: MIT Police                              ",
                           "W91: Information Systems Operations          ",
                           "W92: Building W92                            ",
                           "W98: Building W98                            ",
                           "W85DE: Westgate (De)                           ",
                           "W85FG: Westgate (Fg)                           ",
                           "WW15: Building Ww15                              ",
                           "W85ABC: Westgate (Abc)                           ",
                           "W85HJK: Westgate (Hjk)                           "
                    });

            addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

            // Adding flow rules
            FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

            // Adding question to list
            questions.put("mit-loc", addedQuestion);
        }




        {
            // Adding question
            addedQuestionRef = QuestionServices.insert(userId, networkId, "Do you consider yourself a programmer?", 40, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

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
            questions.put("programmer", addedQuestion);
        }
            {
                {
                    // Adding question
                    addedQuestionRef = QuestionServices.insert(userId, networkId, "What projects do you tend to code or program for?", 100, 10, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                    // Adding options
                    addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                    QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                            {
                                "Desktop applications                     ",
                                "Web applications",
                                "Mobile applications",
                                "General Algorithms",
                                "Statistical tools",
                                "Computational models and simulators",
                                "Large data applications",
                                "Database driven tools",
                                "Image analysis tools",
                                "Video analysis tools",
                                "Educational software",
                                "Artificial Intelligence (AI) tools",
                                "Automation tools"
                            });

                    addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                    // Adding flow rules
                    option = questions.get("programmer").findOptionByText("Yes");
                    FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                    // Adding question to list
                    questions.put("soft purpose", addedQuestion);
                }

                {
                    // Adding question
                    addedQuestionRef = QuestionServices.insert(userId, networkId, "Select all the compilable languages you are familiar with:", 100, 10, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                    // Adding options
                    addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                    QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                            {
                                    "Assembly     ",
                                    "Basic        ",
                                    "C            ",
                                    "C#           ",
                                    "C++          ",
                                    "COBOL        ",
                                    "Erlang       ",
                                    "Fortran      ",
                                    "Go           ",
                                    "Groovy       ",
                                    "Java         ",
                                    "Lisp         ",
                                    "Objective-C  ",
                                    "Pascal       ",
                                    "POP-11       ",
                                    "Scheme       ",
                                    "SmallTalk    ",
                                    "Visual Basic "


                            });

                    addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                    // Adding flow rules
                    option = questions.get("programmer").findOptionByText("Yes");
                    FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                    // Adding question to list
                    questions.put("compilable", addedQuestion);
                }

                {
                    // Adding question
                    addedQuestionRef = QuestionServices.insert(userId, networkId, "Select all the interpreted languages you are familiar with:", 100, 10, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                    // Adding options
                    addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                    QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                            {
                                    "Ant           ",
                                    "APL           ",
                                    "Basic         ",
                                    "Groovy        ",
                                    "JavaScript    ",
                                    "Lisp          ",
                                    "Mathematica   ",
                                    "Pascal        ",
                                    "Perl          ",
                                    "PHP           ",
                                    "PostScript    ",
                                    "Python        ",
                                    "R             ",
                                    "Ruby          ",
                                    "S-Lang        ",
                                    "Tcl           ",
                                    "VBScript      ",
                                    "ColdFusion    ",
                                    "Scala         ",
                                    "Node.js       ",
                                    "MATLAB        ",
                                    "Bash          ",
                                    "ProLog        ",
                                    "STRIPS        ",
                                    "Maven         "
                            });

                    addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                    // Adding flow rules
                    option = questions.get("programmer").findOptionByText("Yes");
                    FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                    // Adding question to list
                    questions.put("compilable", addedQuestion);
                }

                {
                    // Adding question
                    addedQuestionRef = QuestionServices.insert(userId, networkId, "Select all the databases you are familiar with:", 100, 10, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                    // Adding options
                    addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                    QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                            "Berkeley DB    ",
                            "CouchDB        ",
                            "M$ SQL Server  ",
                            "MongoDB        ",
                            "MySQL          ",
                            "Oracle         ",
                            "PostgreSQL     ",
                            "SQLite         "
                        });

                    addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                    // Adding flow rules
                    option = questions.get("programmer").findOptionByText("Yes");
                    FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                    // Adding question to list
                    questions.put("db", addedQuestion);
                }

                {
                    // Adding question
                    addedQuestionRef = QuestionServices.insert(userId, networkId, "Select all the structured data languages you are familiar with:", 50, 5, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, true);

                    // Adding options
                    addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                    QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                            "CSS   ",
                            "HTML  ",
                            "JSON  ",
                            "LaTeX ",
                            "XML   ",
                        });

                    addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                    // Adding flow rules
                    option = questions.get("programmer").findOptionByText("Yes");
                    FlowRuleServices.insert(addedQuestion.getNetworkId(), option.getQuestionRef(), option.getRef(), addedQuestion.getRef());

                    // Adding question to list
                    questions.put("markup", addedQuestion);
                }


            }

            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "Are you interested in participating in the MIT $100K Competition?", 20, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                                "What is the $100K?",
                                "Yes",
                                "Maybe",
                                "No"
                        });

                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

                // Adding question to list
                questions.put("g", addedQuestion);
            }

            {
                // Adding question
                addedQuestionRef = QuestionServices.insert(userId, networkId, "Are you interested in working with, launching, or joining startups?", 10, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

                // Adding options
                addedQuestion = QuestionDao.getByNetworkIdAndRef(null, networkId, addedQuestionRef);
                QuestionOptionServices.addOptions(addedQuestion.getNetworkId(), addedQuestion.getRef(), userId, new String[]
                        {
                                "Yes",
                                "Maybe",
                                "No",
                        });

                addedQuestion = QuestionServices.getByNetworkIdAndRef(networkId, addedQuestion.getRef());

                // Adding flow rules
                FlowRuleServices.insert(addedQuestion.getNetworkId(), Question.ROOT_QUESTION_REF, QuestionOption.ANY_OPTION_REF, addedQuestion.getRef());

                // Adding question to list
                questions.put("startups", addedQuestion);
            }

            // Register path
            NetworkServices.addUserToSingleNetwork(networkId, userId, RoleEnum.OWNER);

    }



}
