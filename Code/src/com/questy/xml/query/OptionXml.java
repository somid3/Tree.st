package com.questy.xml.query;

public class OptionXml {
    private Integer ref;
    private Integer score;
    private CriteriaXml criteria = new CriteriaXml();

    public Integer getRef() {
        return ref;
    }

    public void setRef(Integer ref) {
        this.ref = ref;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public CriteriaXml getCriteria() {
        return criteria;
    }

    public void setCriteria(CriteriaXml criteria) {
        this.criteria = criteria;
    }
    
    public boolean hasCriteria() {

        if (criteria != null)
            return true;
        else
            return false;

    }

    public boolean isCriteriaType(CriteriaXml.Type type) {
        if (criteria != null &&
            criteria.getType() != null &&
            criteria.getType().equals(type))
            return true;
        else
            return false;

    }
}
