package com.example.myapplication;

public class Calendar {
    private String start;
    private String end;
    private String title;
    private boolean allDay;
    public Calendar(String start, String end, String title, boolean allDay) {
        this.start = start;
        this.end = end;
        this.title = title;
        this.allDay = allDay;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
