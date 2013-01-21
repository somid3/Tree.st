package com.questy.services;

import com.questy.dao.*;
import com.questy.domain.*;
import com.questy.enums.AnswerVisibilityEnum;
import com.questy.enums.NetworkAlphaSettingEnum;
import com.questy.enums.NetworkIntegerSettingEnum;
import com.questy.enums.RoleEnum;
import com.questy.helpers.Tuple;
import com.questy.helpers.UIException;
import com.questy.utils.StringUtils;
import com.questy.web.HtmlUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class NetworkServices extends ParentService {

    public static void addGlobals(Integer userId, RoleEnum role) throws SQLException {

        List<Network> globals = NetworkDao.getAllByGlobal(null, true);
        for (Network global : globals)
            NetworkServices.addUserToSingleNetwork(global.getId(), userId, role);

    }

    public static void addUserToNonGlobalNetworkWithDependencies(Integer networkId, Integer userId, RoleEnum role) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Adding user as member to root network
        addUserToSingleNetwork(networkId, userId, role);

        // Do not add dependencies
        if (role == RoleEnum.VISITOR)
            return;

        // Retrieving all dependencies for network
        List<NetworkDependsOn> dependencies = new ArrayList<NetworkDependsOn>();
        addAllDependencies(networkId, dependencies);

        // Add user to all network dependencies
        for (NetworkDependsOn dependency : dependencies)
            addUserToSingleNetwork(dependency.getDependsOn(), userId, dependency.getRole());
    }

    /**
     * Adds user to the provided network ONLY. It does not add the user to any dependant
     * networks or any other global networks. If the user is already a member of this
     * network, then the user does not get added. The return defines whether the user
     * was added to the network or not
     */
    public static Boolean addUserToSingleNetwork(Integer networkId, Integer userId, RoleEnum role) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving network
        Network network = NetworkDao.getById(conn, networkId);

        // Retrieve user to network mapping
        UserToNetwork utn = UserToNetworkDao.getByUserIdAndNetworkId(conn, userId, networkId);

        Boolean wasUserAdded = false;

        // Is user already part of network?
        if (utn == null) {

            // No, map user to network
            UserToNetworkDao.insert(conn, userId, networkId, role);

            // Retrieving points for joining
            Integer pointsPerJoin = NetworkIntegerSettingEnum.NETWORK_JOIN_POINTS_PER.getValueByNetworkId(networkId);

            // Increment default points for user
            UserToNetworkDao.incrementPointsByUserIdAndNetworkId(conn, userId, networkId, pointsPerJoin);

            // Updating members count for network
            NetworkDao.updateTotalMembers(conn, networkId, network.getTotalMembers() + 1);

            wasUserAdded = true;

        }

        return wasUserAdded;
    }

    public static Tuple<Boolean, String> isEmailEndingGood(Integer networkId, String email) throws SQLException {

        // Retrieving email ending requirements
        List<NetworkEmailEnding> emailEndings = NetworkEmailEndingsDao.getByNetworkId(null, networkId);

        // Email is assumed to end incorrectly
        Boolean isEmailEndingGood = false;

        // Does the network require the email to end in a particular way
        if (emailEndings.isEmpty()) {

            // No, so the email ending requirement is ok
            isEmailEndingGood = true;

        } else {

            // Yes, does the email end at least with one of the correct endings?
            for (NetworkEmailEnding emailEnding : emailEndings) {

                if (email.endsWith(emailEnding.getEmailEndsWith())) {

                    // Yes, so the email is good
                    isEmailEndingGood = true;
                    break;

                }
            }
        }

        // Generating error message if email is not good
        StringBuilder emailMustEndWith = new StringBuilder();
        if (!isEmailEndingGood) {
            String separator = " or ";

            // Looping over all emailEndings and adding to list of requirements
            for (NetworkEmailEnding emailEnding : emailEndings) {
                emailMustEndWith
                        .append('"')
                        .append(emailEnding.getEmailEndsWith())
                        .append('"')
                        .append(separator);
            }

            // Removing the last separator
            emailMustEndWith.delete(
                    emailMustEndWith.lastIndexOf(separator),
                    emailMustEndWith.length());
        }

        // Putting it all together
        Tuple<Boolean, String> out = new Tuple<Boolean, String>(isEmailEndingGood, emailMustEndWith.toString());

        return out;
    }

    public static List<Network> getByUserId(Integer userId, RoleEnum lowestRole) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving user to network mappings
        List<UserToNetwork> utns = UserToNetworkDao.getByUserIdAndLowestRole(null, userId, lowestRole);

        // Retrieving all networks
        List<Network> networks = new ArrayList<Network>();
        for (UserToNetwork utn : utns)
            networks.add(NetworkDao.getById(null, utn.getNetworkId()));

        return networks;
    }

    /**
     * Returns list of networks that are all descendants of the provided network id.
     * The provided network is the first element on the list. If the provided network
     * is a non-global network, then the global networks are appended to the end of
     * returned list
     *
     * @param networkId
     * @return
     * @throws SQLException
     */
    public static List<Network> getNetworkWithAllDependants(Integer networkId) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        List<Network> out = new ArrayList<Network>();

        // Retrieving initial network
        Network network = NetworkDao.getById(conn, networkId);

        // Adding the current network
        out.add(network);

        // Adding dependant networks
        if (!network.isGlobal())
            addAllDependants(networkId, out);

        // Adding all global networks if requested network was a private one
        if (!network.isGlobal())
            out.addAll( NetworkDao.getAllByGlobal(conn, true) );

        return out;
    }

    /**
     * Traverses down the dependency tree of a network and returns the list of networks the
     * user is already mapped to
     *
     * @param networkId
     * @param userId
     * @param lowestRole
     * @return
     * @throws SQLException
     */
    public static List<Network> getNetworkWithAllDependantsMappedToUser (Integer networkId, Integer userId, RoleEnum lowestRole) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving network with dependants
        List<Network> dependants = getNetworkWithAllDependants(networkId);

        // Retrieving networks mapped to user
        List<Network> userNetworks = getByUserId(userId, lowestRole);

        // Finding networks in dependant tree that user is mapped to
        List<Network> out = new ArrayList<Network>();

        // For all networks user is mapped to...
        for (Network userNetwork : userNetworks) {

            // Seeing which dependant networks are mapped to the user
            if (dependants.contains(userNetwork))
                out.add(userNetwork);

        }

        return out;
    }

    /**
     * Returns a list of networks
     *
     * @param networkId
     * @param out
     * @throws SQLException
     */
    public static void addAllDependants(Integer networkId, List<Network> out) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving dependant networks
        List<NetworkDependsOn> dependencies = new ArrayList<NetworkDependsOn>();

        addAllDependencies(networkId, dependencies);

        for (NetworkDependsOn dependency : dependencies) {

            out.add( NetworkDao.getById(conn, dependency.getDependsOn()) );

        };

    }

    public static void addAllDependencies(Integer networkId, List<NetworkDependsOn> out) throws SQLException {

        // Currently non-transactional
        Connection conn = null;

        // Retrieving the one-level-up dependencies of the current network
        List<NetworkDependsOn> dependencies = NetworkDependsDao.getByNetworkId(conn, networkId);

        for (NetworkDependsOn dependency : dependencies) {

            // Adding current dependency
            out.add( dependency );

            // Adding all dependencies that are higher up
            addAllDependencies(dependency.getDependsOn(), out);

        };

    }

    /**
     * Checks whether a path is available for a new network
     * @param path
     */
    public static void testNewPath(String path) throws SQLException {

        if (!HtmlUtils.isPathFriendly(path))
            throw new UIException("Can only contain numbers, letters, dashes, and underscores");

        path = path.toLowerCase();
        Integer networkId = NetworkAlphaSettingEnum.URL_PATH.getNetworkIdByValue(path);

        if (networkId != null)
            throw new UIException("Aww, '" + path + "' not is not available");
    }

    public static Integer createSimpleNetwork(String path, String name, String desc, List<String> qualities) throws SQLException {

        /**
         * Validating
         */

        // Validating path
        if (StringUtils.isEmpty(path) || path.length() <= 3)
            throw new UIException("Web address is too short");

        if (path.length() >= 30)
            throw new UIException("Web address is too long");

        path = path.toLowerCase();
        if (!HtmlUtils.isPathFriendly(path))
            throw new UIException("Web address has the wrong format - it only contain numbers, letters, dashes, and underscores");

        Integer foundNetworkId = NetworkAlphaSettingEnum.URL_PATH.getNetworkIdByValue(path);
        if (foundNetworkId != null)
            throw new UIException("Aww, '" + path + "' not is not available");

        // Validating name
        if (StringUtils.isEmpty(name) || name.length() <= 3)
            throw new UIException("Name is too short");

        if (name.length() >= 60)
            throw new UIException("Name is too long");

        // Validating description
        if (StringUtils.isEmpty(desc) || desc.length() <= 10)
            throw new UIException("Description is too short");

        if (desc.length() >= 100)
            throw new UIException("Description is too long");

        // Validating and digesting each quality
        Integer qualityCount = 1;
        List<Tuple<String, List<String>>> digestedQualities = new ArrayList<Tuple<String, List<String>>>();
        for (String quality : qualities)
            validateAndDigestQuality(quality, qualityCount);


        /**
         * Building the network
         */

        // Creating network
        Integer newNetworkId = NetworkDao.insert(null, name, false);

        // Adding network alpha settings
        NetworkAlphaSettingEnum.URL_PATH.setValueByNetworkId(newNetworkId, path);
        NetworkAlphaSettingEnum.START_MESSAGE.setValueByNetworkId(newNetworkId, desc);

        // Adding questions to network
        Integer previousQuestionRef = Question.ROOT_QUESTION_REF;
        Integer newQuestionRef = null;
        String questionText = null;
        String optionText = null;

        List<String> elements = null;
        for (String quality : qualities) {

            // Cleaning up text
            quality = quality.replaceAll("\r", "");

            // Separating question and options
            elements = new ArrayList<String>(Arrays.asList(quality.split("\n")));
            questionText = elements.get(0).trim();
            elements.remove(0);

            // Validating
            if (StringUtils.isEmpty(questionText))
                continue;

            // Creating question
            newQuestionRef = QuestionServices.insert(User.ANY_USER_ID, newNetworkId, questionText, 100, 1, AnswerVisibilityEnum.PUBLIC, AnswerVisibilityEnum.PROTECTED, false);

            // Creating flow rule
            FlowRuleServices.insert(newNetworkId, previousQuestionRef, QuestionOption.ANY_OPTION_REF,  newQuestionRef);

            // Adding all options
            for (String element : elements) {

                // Cleaning up option text
                optionText = element.trim();

                // Validating
                if (StringUtils.isEmpty(optionText))
                    continue;

                // Creating option
                QuestionOptionServices.addOption(User.ANY_USER_ID, newNetworkId, newQuestionRef, optionText);

            }

        }

        return newNetworkId;
    }

    private static Tuple<String, List<String>> validateAndDigestQuality(String quality, Integer qualityCount) {


        // Cleaning up text
        quality = quality.replaceAll("\r", "");

        // Separating question and options
        ArrayList<String> elements = new ArrayList<String>(Arrays.asList(quality.split("\n")));

        // Extracting question
        String questionText = elements.get(0).trim();

        // Validating question
        if (questionText.length() <= 3)
            throw new UIException("Quality " + qualityCount + " is too short!");

        if (questionText.length() >= 100)
            throw new UIException("Quality " + qualityCount + " is too long!");

        // Extracting question options
        elements.remove(0);
        List<String> questionOptions = elements;

        // Validating options
        for (String questionOption : questionOptions) {

        }

        return new Tuple<String, List<String>>(questionText, questionOptions);
    }
}
