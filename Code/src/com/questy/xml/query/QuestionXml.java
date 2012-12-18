package com.questy.xml.query;

import java.util.ArrayList;
import java.util.List;

public class QuestionXml {
    private Integer ref;
    private Integer networkId;
    private Integer score;
    private Integer maxSelectedOptions;
    private List<OptionXml> options = new ArrayList<OptionXml>();

    public Integer getRef() {
        return ref;
    }

    public void setRef(Integer ref) {
        this.ref = ref;
    }

    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<OptionXml> getOptions() {
        return options;
    }

    public OptionXml getOption(Integer index) {
        return options.get(index);
    }

    public OptionXml getOptionByRef(Integer value) {

        if (value == null)
            return null;

        for (OptionXml o : options) {
            if (value.equals(o.getRef()))
                return o;

        }

        return null;
    }


    public void setOptions(List<OptionXml> options) {
        this.options = options;
    }

    public void addOption(OptionXml option) {
        options.add(option);
    }

    public boolean hasOptions() {

        if (options.size() > 0)
            return true;
        else
            return false;

    }

    public Integer getMaxSelectedOptions() {
        return maxSelectedOptions;
    }

    public void setMaxSelectedOptions(Integer maxSelectedOptions) {
        this.maxSelectedOptions = maxSelectedOptions;
    }
}
