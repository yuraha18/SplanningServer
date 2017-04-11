package com.himalayapp.splanning.server.entity;



public class Time {
    private long currentTime;

    public long getCurrentTime() {
        return currentTime;
    }

    @Override
    public String toString() {
        return "Time{" +
                "currentTime=" + currentTime +
                '}';
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }
}
