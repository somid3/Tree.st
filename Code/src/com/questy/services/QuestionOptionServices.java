package com.questy.services;

import com.questy.dao.QuestionDao;
import com.questy.dao.QuestionOptionDao;
import com.questy.dao.UserDao;
import com.questy.domain.Question;
import com.questy.domain.QuestionOption;
import com.questy.domain.User;
import com.questy.domain.UserToNetwork;
import com.questy.utils.ValueComparator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;


public class QuestionOptionServices extends ParentService  {

    public static Integer addOption (Integer userId, Integer networkId, Integer questionRef, String optionText) throws SQLException {

        // Validating
        if (optionText == null || optionText.isEmpty())
            throw new RuntimeException("Option text can not be empty");

        // Currently non-transactional
        Connection conn = null;

        // Retrieving max ref
        Integer maxRef = QuestionOptionDao.getMaxRefByQuestionRef(conn, networkId, questionRef);

        // Creating next ref
        Integer ref = 1;
        if (maxRef != null)
            ref = maxRef + 1;

        // Inserting option
        QuestionOptionDao.insert(conn, networkId, questionRef, ref, userId, optionText);

        return ref;
    }

    public static void addOptions (Integer networkId, Integer questionRef, Integer userId, String[] optionTexts) throws SQLException {

        for (String optionText : optionTexts)
            addOption(userId, networkId, questionRef, optionText);

    }

    public static List<QuestionOption> getFromFlares (String flaresString) throws SQLException {

        String[] flareStrings = flaresString.split(" ");
        String[] flareElements = null;
        Integer flareNetworkId = null;
        Integer flareQuestionRef = null;
        Integer flareOptionRef = null;

        List<QuestionOption> out = new ArrayList<QuestionOption>();
        for (String flareString : flareStrings) {

            flareElements = flareString.split("-");

            // Only analyze flares that belong to options
            if (flareElements.length < 3) continue;

            flareNetworkId = Integer.parseInt(flareElements[0]);
            flareQuestionRef = Integer.parseInt(flareElements[1]);
            flareOptionRef = Integer.parseInt(flareElements[2]);

            out.add(QuestionOptionDao.getByNetworkIdAndQuestionRefAndOptionRef(null, flareNetworkId, flareQuestionRef, flareOptionRef));

        }

        return out;
    }

    /**
     * Sorts question options by its text
     */
    public static List<QuestionOption> sortByText (List<QuestionOption> options) {

        Map<QuestionOption, String> unsortedMap = new HashMap<QuestionOption, String>();
        ValueComparator vc = new ValueComparator(unsortedMap);
        Map<QuestionOption, String> sortedMap = new TreeMap<QuestionOption, String>(vc);

        for (QuestionOption option : options)
            unsortedMap.put(option, option.getText());

        sortedMap.putAll(unsortedMap);
        List<QuestionOption> sortedList = new ArrayList<QuestionOption>();
        sortedList.addAll(sortedMap.keySet());

        return sortedList;
    }

    /**
     * Sorts question options by the total number of answers
     */
    public static List<QuestionOption> sortById(List<QuestionOption> options) {

        Map<QuestionOption, Integer> unsortedMap = new HashMap<QuestionOption, Integer>();
        ValueComparator vc = new ValueComparator(unsortedMap);
        Map<QuestionOption, Integer> sortedMap = new TreeMap<QuestionOption, Integer>(vc);

        for (QuestionOption option : options)
            unsortedMap.put(option, option.getId());

        sortedMap.putAll(unsortedMap);
        List<QuestionOption> sortedList = new ArrayList<QuestionOption>();
        sortedList.addAll(sortedMap.keySet());

        return sortedList;
    }

}
