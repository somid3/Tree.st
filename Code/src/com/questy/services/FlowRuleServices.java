package com.questy.services;

import com.questy.dao.*;
import com.questy.domain.*;
import com.questy.helpers.SqlLimit;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * WARNING:
 *
 * DO NOT TOUCH THIS CLASS UNLESS YOU ARE AWAKE! THE SLIGHTEST CHANGE TO THIS CLASS CAN LEAD TO CATASTROPHIC DECISION
 * TREE SIDE EFFECTS. BE SURE TO EDIT THIS CLASS IN A MORNING WHEN YOU HAVE YOUR FULL ATTENTION! AS YOU EDIT THIS
 * CLASS ALSO UPDATE ITS UNIT TESTS!
 *
 * This class returns the question reference that a user should answer next, based on the questions the user
 * has already answered. Currently it is a complex algorithm, make sure you are wide awake when you edit this
 * file
 *
 * There are really two decision trees merged into one for a user in a network. The first decision tree stores where
 * the user last left off answering questions *again*. The second decision tree is the location the user stopped
 * answering *new* questions, that is, previously unanswered questions. This algorithm begins first searching for
 * the next available question in the first decision tree, and later in the second one (the new answers decision tree)
 *                            
 * When it searches for the next available question in a decision tree, it first starts looking for the next
 * available question going forward -- if a question reference is available, it returns it. If there are no questions
 * available, the algorithm moves back one step closer to the root from the user's last answer and attempts to see if
 * another questions is available. If a question is available, then the algorithm is at a decision tree fork and
 * returns the first available question reference
 *
 * If multiple questions are available at a particular node this algorithm returns the reference of the question that
 * was created first. It does not randomly pick one. When the algorithm moves backward it will never reach the
 * root question of the decision tree. A network can have many decision trees. As such, if no questions are available
 * moving forward and backwards in both decision trees, then the algorithm searches for root questions that have not
 * been previously answered
 */
public class FlowRuleServices extends ParentService {

    /**
     * Will log all movements across a tree
     */
    private static boolean debug = false;

    public static Integer getNextQuestionRef(Integer userId, Integer networkId) throws SQLException {

        debug("Starting for network " + networkId);

        // Currently non-transactional
        Connection conn = null;

        Integer nextRef = null;

        // Start analyzing decision tree where user left off when a question was being answered "again"
        debug("Starting next question ref with 'again'");
        nextRef = getNextQuestionRef(conn, userId, networkId, true);
        debug("Ending next question ref with 'again': " + nextRef);
        debug("");

        // Start analyzing decision tree where user left off for "new" questions
        if (nextRef == null) {
            debug("Starting next question ref");
            nextRef = getNextQuestionRef(conn, userId, networkId, false);
            debug("Ending next question ref: " + nextRef);
            debug("");
        }

        // Finding a root that has not been answered already
        if (nextRef == null) {
            debug("Starting next question from 'root'");
            nextRef = getNextRoot(conn, userId, networkId);
            debug("Ending next question ref for 'root': " + nextRef);
            debug("");
        }

        /*
         * Ensuring that the "again" and the "new" tree do not return the very last question that
         * the user answered. This can occur if a user enters a new branch of the decision tree
         * of questions by answering the question at the fork "again" but with a different option
         */
        List<Answer> latestAnswers = AnswerDao.getLastByUserIdAndNetworkId(conn, userId, networkId, SqlLimit.FIRST);
        if (!latestAnswers.isEmpty() && nextRef != null &&
            latestAnswers.get(0).getQuestionRef().equals(nextRef))
            nextRef = null;

        debug("Ending for network " + networkId + ", next ref: " + nextRef);
        debug("");

        return nextRef;
    }

    /**
     * Given a user and a network, provides the next question that needs to be answered. If the
     * network has no particular questions left for the user, then a null question is returned
     *
     * This algorithm begins by looking at the last question answered in the network
     */
    private static Integer getNextQuestionRef(Connection conn, Integer userId, Integer networkId, Boolean again) throws SQLException {

        // Get user's latest answer
        List<Answer> latestAnswers = AnswerDao.getLastByUserIdAndNetworkIdAndAgain(conn, userId, networkId, again, SqlLimit.FIRST);

        // Has the user answered any questions?
        Answer answer = null;
        if (latestAnswers.isEmpty()) {

            // No, quit
            return null;

        } else {

            // Getting the most recent answer
            answer = latestAnswers.get(0);
        }

        if (debug) {

            // Display options answered in a nice manner
            List<AnswerOption> options = AnswerOptionDao.getByUserIdAndNetworkIdAndAnswerRef(conn, userId, networkId, answer.getRef());
            List<String> optionsVerbose = new ArrayList<String>();
            for (AnswerOption option : options)
                optionsVerbose.add(option.getOptionRef().toString());

            debug("\tAnalyzing last answer of question ref [" + answer.getQuestionRef() + "] with selected option refs " + optionsVerbose);
        }




        /**************
         * Move forward
         **************/
        debug("\tMoving forward");
        Integer nextQuestionRef = moveForwardOrBackward(conn, answer, again, false);
        if (nextQuestionRef != null) return nextQuestionRef;



        /*************************
         * Move one step backwards
         *************************/
        debug("\tMoving backward");

        // If analyzing questions being answered again, no need to move backwards
        if (again) return null;

        Integer fromQuestionRef = null;
        List<FlowRule> previousFlowRules = FlowRuleDao.getByToQuestionRefAndNetworkId(conn, answer.getQuestionRef(), answer.getNetworkId());
        while (!previousFlowRules.isEmpty()) {

            /**
             * Ensuring all flow rules originate from the same question ref since no question
             * can have flow rules that direct traffic from two different predecessor questions
             */
            fromQuestionRef = previousFlowRules.get(0).getFromQuestionRef();
            for (FlowRule previousFlowRule : previousFlowRules) {

                // Testing whether current flow rule has the same from question reference
                if (!fromQuestionRef.equals(previousFlowRule.getFromQuestionRef()))
                    throw new RuntimeException("One or more flow rules do not contain the same source question reference for the same question");

                fromQuestionRef = previousFlowRule.getFromQuestionRef();
            }

            // Ensuring we stop moving backward once we hit the root question
            if (fromQuestionRef.equals(Question.ROOT_QUESTION_REF))
                break;

            // Retrieving previous answer
            answer = AnswerDao.getLastByUserIdAndNetworkIdAndAnsweredQuestionRefAndAgain(conn, userId, networkId, fromQuestionRef, again);
            debug("\t\tFound last answer, of question ref [" + answer.getQuestionRef() + "]");

            // Move Backward
            debug("\t\tMoving one step back");
            nextQuestionRef = moveForwardOrBackward(conn, answer, again, true);
            if (nextQuestionRef != null) return nextQuestionRef;

            // Getting previous flow rules
            previousFlowRules = FlowRuleDao.getByToQuestionRefAndNetworkId(conn, answer.getQuestionRef(), answer.getNetworkId());
            debug("\t\tAnalyzing now from question ref " + fromQuestionRef);


        }

        return null;
    }

    private static Integer moveForwardOrBackward(Connection conn, Answer answer, Boolean again, boolean movingBackwards) throws SQLException {

        // Get all flow rules where the "from" question ref is that of the question answered coming as input
        List<FlowRule> flowRules = FlowRuleDao.getByFromQuestionRefAndNetworkId(conn, answer.getQuestionRef(), answer.getNetworkId());

        if (debug) {

            // Display flow rules nice manner
            for (FlowRule flowRule : flowRules)
                debug("\t\tGot flow rule, from question ref " + flowRule.getFromQuestionRef() + " and option ref " + flowRule.getFromOptionRef() + " to question ref " + flowRule.getToQuestionRef());
        }

        // If there are no flow rules, then stop
        if (flowRules.isEmpty()) return null;


        /**
         * Are we moving backward?
         *
         * If so, filter out all the flow rules that have already been answered. If this
         * is omitted we will trace paths that have already been covered by previous answers,
         * and will not move to new areas of the decision tree
         */
        if (movingBackwards) {

            // For each flow rule, find those that have not been answered already
            Answer answered = null;
            List<FlowRule> unansweredFlowRules = new ArrayList<FlowRule>();
            for (FlowRule flowRule : flowRules) {

                answered = AnswerDao.getLastByUserIdAndNetworkIdAndAnsweredQuestionRefAndAgain(conn, answer.getUserId(), answer.getNetworkId(), flowRule.getToQuestionRef(), again);

                if (answered == null)
                    unansweredFlowRules.add(flowRule);
            }

            /*
             * Save the safe flow rules as the flow rules to be analyzed
             * to move forward
             */
            flowRules = unansweredFlowRules;
        }

        // If there are no flow rules, then stop
        if (flowRules.isEmpty()) return null;



        if (debug) {

            // Display flow rules nice manner
            for (FlowRule fr : flowRules)
                debug("\t\tUNANSWERED flow rule, from question ref " + fr.getFromQuestionRef() + " and option ref " + fr.getFromOptionRef() + " to question ref " + fr.getToQuestionRef());
        }

        // Is there a flow rule that matches any of the selected option values in the last answer?
        List<FlowRule> noOptionFrs = new ArrayList<FlowRule>();
        List<AnswerOption> answerOptions = null;
        for (FlowRule fr : flowRules) {

            // Does the question flow rule contain an option value?
            if (!fr.getFromOptionRef().equals(QuestionOption.ANY_OPTION_REF)) {

                // Retrieving answer options
                answerOptions = AnswerOptionDao.getByUserIdAndNetworkIdAndAnswerRef(conn, answer.getUserId(), answer.getNetworkId(), answer.getRef());

                // Looping though all answered options
                for (AnswerOption ao : answerOptions) {
                    if (fr.getFromOptionRef().equals(ao.getOptionRef())) {
                        return fr.getToQuestionRef();
                    }
                }

            } else
                noOptionFrs.add(fr);
        }

        // Select the first matching flow rule, if any
        if (!noOptionFrs.isEmpty()) {
            return noOptionFrs.get(0).getToQuestionRef();
        }

        debug("\t\tDid not find a matching flow rule");

        return null;      
    }

    private static Integer getNextRoot (Connection conn, Integer userId, Integer networkId) throws SQLException {

        // Get all the flow rules with a null in the "from" question ref
        List<FlowRule> rootFrs = FlowRuleDao.getByFromQuestionRefAndNetworkId(conn, Question.ROOT_QUESTION_REF, networkId);

        // Filter out the flow rules that have already been answered
        Answer answered = null;
        for (FlowRule rootFr : rootFrs) {

            answered = AnswerDao.getLastByUserIdAndNetworkIdAndAnsweredQuestionRefAndAgain(conn, userId, networkId, rootFr.getToQuestionRef(), false);

            if (answered == null)
                return rootFr.getToQuestionRef();
        }

        return null;
    }

    public static Integer insert (
        Integer networkId,
        Integer fromQuestionRef,
        Integer fromOptionRef,
        Integer toQuestionRef) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        /**
         * To define the order of the new flow rule, we need to find the maximum order number so far in the
         * set of all flow rules that have the same from question reference and option reference
         */
        Integer maxOrder = FlowRuleDao.getMaxOrderByNetworkIdAndFromQuestionRefAndOptionRef(conn, networkId, fromQuestionRef, fromOptionRef);

        // Creating next order
        Integer order = 1;
        if (maxOrder != null)
            order = maxOrder + 1;

        // Inserting flow rule
        return FlowRuleDao.insert(conn, networkId, fromQuestionRef, fromOptionRef, toQuestionRef, order);
    }

    private static void debug (String message) {

        if (debug)
            System.out.println(message);

    }
}
