package com.questy.helpers;

public class SqlLimit {
    
    private int startFrom;
    private int duration;

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
