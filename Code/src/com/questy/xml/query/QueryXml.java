package com.questy.xml.query;

import java.util.ArrayList;
import java.util.List;

public class QueryXml {

    private List<QuestionXml> questions = new ArrayList<QuestionXml>();

    public List<QuestionXml> getQuestions() {
        return questions;
    }

    public QuestionXml getQuestion(int index) {
        return questions.get(index);
    }

    public void setQuestions(List<QuestionXml> questionXmls) {
        this.questions = questionXmls;
    }

    public void addQuestion(QuestionXml questionXml) {
        questions.add(questionXml);
    }

    public boolean hasQuestions() {

        if (questions.size() > 0)
            return true;
        else
            return false;

    }
    
}
