package com.example.myapplication;

public class People {
    private String id;
    private String displayName;
    private String mail;

    public People(String id, String displayName, String mail) {
        this.id = id;
        this.displayName = displayName;
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getMail() {
        return mail;
    }
}


