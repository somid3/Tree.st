package com.questy.helpers;

public class SqlLimit {
    
    private int startFrom;
    private int duration;

    public static final SqlLimit ALL = new SqlLimit(0, 100000000);
    public static final SqlLimit FIRST = new SqlLimit(0, 1);
    public static final SqlLimit FIRST_TEN = new SqlLimit(0, 10);

    public SqlLimit(int startFrom, int duration) {
        this.startFrom = startFrom;
        this.duration = duration;
    }

    public int getStartFrom() {
        return startFrom;
    }

    public void setStartFrom(int startFrom) {
        this.startFrom = startFrom;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
}
