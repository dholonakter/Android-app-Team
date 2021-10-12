package com.example.myapplication;

import androidx.annotation.NonNull;

public class Person {
    String givenName;
    String surName;
    String initials;
    String mail;
    String telephoneNumber;

    public Person(String givenName, String surName, String initials, String mail, String telephoneNumber) {
        this.givenName = givenName;
        this.surName = surName;
        this.initials = initials;
        this.mail = mail;
        this.telephoneNumber = telephoneNumber;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getSurName() {
        return surName;
    }

    public String getInitials() {
        return initials;
    }

    public String getMail() {
        return mail;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    @NonNull
    @Override
    public String toString() {
        return givenName + " " + surName + " " +
                initials + " " + mail + " " + telephoneNumber;
    }
}

