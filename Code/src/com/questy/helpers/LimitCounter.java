package com.questy.helpers;

public class LimitCounter {

    private int limit = 0;
    private int count = 0;

    public LimitCounter(int limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Integer getCount() {
        return count;
    }

    public void incrementCount() {
        this.count++;
    }

    public Boolean hasReachedMax() {

        if (count >= limit)
            return true;

        return false;
    }
}
