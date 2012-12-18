package com.questy.services;

import com.questy.dao.QuestionDao;
import com.questy.dao.QuestionOptionDao;
import com.questy.domain.Question;
import com.questy.domain.QuestionOption;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class QuestionOptionServices extends ParentService  {

    public static Integer addOption (Integer networkId, Integer questionRef, Integer userId, String optionText) throws SQLException {

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
            addOption(networkId, questionRef, userId, optionText);

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
}
