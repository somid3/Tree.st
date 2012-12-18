package com.questy.xml.query;

import com.questy.utils.DatabaseUtils;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class QueryXmlReaderTest {

    @Test
    public void test() throws Exception {

        Connection conn = DatabaseUtils.getConnection();

        String queryXml =
                "<?xml version=\"1.0\"?>" +
                "<query>" +
                    "<question id=\"2\" score=\"3\">" +
                        "<option value=\"4\" score=\"5\">" +
                            "<criteria type=\"is_set\"></criteria>" +
                        "</option>" +
                    "</question>" +
                    "<question id=\"6\" score=\"7\"></question>" +
                "</query>";

        QueryXml query = QueryXmlReader.parseAndLoad(queryXml);

        Assert.assertEquals((Integer) 2, query.getQuestion(0).getRef());
        Assert.assertEquals((Integer) 3, query.getQuestion(0).getScore());
        Assert.assertEquals((Integer) 4, query.getQuestion(0).getOption(0).getRef());
        Assert.assertEquals((Integer) 5, query.getQuestion(0).getOption(0).getScore());
        Assert.assertEquals(CriteriaXml.Type.IS_SET, query.getQuestion(0).getOption(0).getCriteria().getType());
        Assert.assertEquals((Integer) 6, query.getQuestion(1).getRef());
        Assert.assertEquals((Integer) 7, query.getQuestion(1).getScore());
    }
}
