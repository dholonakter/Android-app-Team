package com.example.myapplication;

public class Schedule{
    String start;
    String end;
    String title;

    public Schedule(String start, String end, String title) {
        this.start = start;
        this.end = end;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
