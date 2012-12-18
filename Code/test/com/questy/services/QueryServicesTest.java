package com.questy.services;

import com.questy.helpers.UserScores;
import com.questy.utils.DatabaseUtils;
import com.questy.xml.query.QueryXml;
import com.questy.xml.query.QueryXmlReader;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class QueryServicesTest {


    @Test
    public void isSetCriteriaTest() throws SQLException {

        Connection conn = DatabaseUtils.getConnection();
        UserScores userScores = new UserScores();
        
        QueryServices.criteriaIsSet(
            userScores,
            1,
            1,
            1,
            1,
            50
        );

    }

    @Test
    public void createScoresTest() throws Exception {

        String queryXml =
        "<?xml version=\"1.0\"?>" +
        "<query>" +
            "<question ref=\"2\" network=\"2000\" score=\"50\">" +
                "<option ref=\"4\" score=\"50\">" +
                    "<criteria type=\"is_set\"></criteria>" +
                "</option>" +
            "</question>" +
            "<question ref=\"4\" network=\"2002\" score=\"50\">" +
                "<option ref=\"2\" score=\"50\">" +
                    "<criteria type=\"is_set\"></criteria>" +
                "</option>" +
            "</question>" +
            "<question ref=\"1\" network=\"2002\" score=\"50\">" +
                "<option ref=\"2\" score=\"50\">" +
                    "<criteria type=\"is_set\"></criteria>" +
                "</option>" +
                "<option ref=\"1\" score=\"50\">" +
                    "<criteria type=\"is_set\"></criteria>" +
                "</option>" +
                "<option ref=\"3\" score=\"50\">" +
                    "<criteria type=\"is_set\"></criteria>" +
                "</option>" +
            "</question>" +
            "<question ref=\"5\" network=\"2002\" score=\"50\">" +
                "<option ref=\"2\" score=\"50\">" +
                    "<criteria type=\"is_set\"></criteria>" +
                "</option>" +
            "</question>" +
            "<question ref=\"6\" network=\"2002\" score=\"50\">" +
            "</question>" +
        "</query>";

        QueryXml query = QueryXmlReader.parseAndLoad(queryXml);

        UserScores userScores = QueryServices.createScores(2, 2, query);

        System.out.println(userScores);
    }

}
