package com.questy.xml.query;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.questy.dao.QuestionDao;
import com.questy.domain.Question;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class QueryXmlReader {



    public static QueryXml parseAndLoad(String queryString) throws Exception {

        // General query xml
        QueryXml queryXml = generateQuery(queryString);

        // Retrieving current question parameters
        Question question = null;
        for (QuestionXml questionXml : queryXml.getQuestions()) {

            // Retrieving question
            question = QuestionDao.getByNetworkIdAndRef(null, questionXml.getNetworkId(), questionXml.getRef());

            // Passing parameters to question xml object
            questionXml.setMaxSelectedOptions(question.getMaxSelectedOptions());

        }

        return queryXml;
    }

    /**
     * Generates the query object
     *
     */
    private static QueryXml generateQuery(String queryString) throws Exception {

        if (queryString == null || queryString.isEmpty()) {
            return new QueryXml();
        }

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        InputStream is = new ByteArrayInputStream(queryString.getBytes("UTF-8"));
        Document doc = db.parse(is);
        doc.getDocumentElement().normalize();

        // Creating query object
        QueryXml query = new QueryXml();
        NodeList queryList = doc.getElementsByTagName("query");
        Element queryElement = (Element) queryList.item(0);
        query.setQuestions(generateQuestions(queryElement));

        return query;

    }

    /**
     * Generates all the question objects
     *
     */
    private static List<QuestionXml> generateQuestions(Element queryElement) {

        List<QuestionXml> out = new ArrayList<QuestionXml>();

        // Looping over every question
        NodeList questionList = queryElement.getChildNodes();
        for (int i = 0; i < questionList.getLength(); i++) {

            Node questionNode = questionList.item(i);
            if (questionNode.getNodeType() == Node.ELEMENT_NODE) {

                // Creating questionXml objects
                Element questionElement = (Element) questionNode;
                QuestionXml questionXml = new QuestionXml();
                questionXml.setRef(getIntegerAttribute(questionElement, "ref"));
                questionXml.setScore(getIntegerAttribute(questionElement, "score"));
                questionXml.setNetworkId(getIntegerAttribute(questionElement, "network"));
                questionXml.setOptions(generateOptions(questionElement));
                out.add(questionXml);

            }

        }

        return out;

    }

    /**
     * Generates all the option objects
     *
     */
    private static List<OptionXml> generateOptions(Element questionElement) {

        List<OptionXml> out = new ArrayList<OptionXml>();

        // Looping over every option
        NodeList optionList = questionElement.getChildNodes();
        for (int i = 0; i < optionList.getLength(); i++) {

            Node optionNode = optionList.item(i);
            if (optionNode.getNodeType() == Node.ELEMENT_NODE) {

                // Creating option objects
                Element optionElement = (Element) optionNode;
                OptionXml option = new OptionXml();
                option.setScore(getIntegerAttribute(optionElement, "score"));
                option.setRef(getIntegerAttribute(optionElement, "ref"));
                option.setCriteria(generateCriteria(optionElement));
                out.add(option);

            }

        }

        return out;
    }

    /**
     * Generates all the criteria objects
     *
     */
    private static CriteriaXml generateCriteria(Element optionElement) {

        CriteriaXml out = null;

        // Looping over every criteria
        NodeList criteriaList = optionElement.getChildNodes();
        for (int i = 0; i < criteriaList.getLength(); i++) {

            Node criteriaNode = criteriaList.item(i);
            if (criteriaNode.getNodeType() == Node.ELEMENT_NODE) {

                // Creating criteria object
                Element criteriaElement = (Element) criteriaNode;
                out = new CriteriaXml();

                // Finding criteria type
                String typeString = criteriaElement.getAttribute("type");
                CriteriaXml.Type type = CriteriaXml.Type.valueOf(typeString.toUpperCase());
                out.setType(type);

            }
        }

        return out;
    }

    /**
     * Returns the value of an attribute in the form on an integer, if
     * that attribute is defined. If the attribute is not defined null
     * is returned
     *
     */
    private static Integer getIntegerAttribute(Element element, String attributeName) {

        String value = element.getAttribute(attributeName);

        if (value == null)
            return null;

        return Integer.parseInt(value);
    }
}
