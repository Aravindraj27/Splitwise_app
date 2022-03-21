package com.gar.machinecoding.model.split;

public class User {
    private String id;
    private String name;
    private String emailId;
    private String mobileNumber;

    public User(String id, String name, String emailId, String mobileNumber) {
        this.id = id;
        this.name = name;
        this.emailId = emailId;
        this.mobileNumber = mobileNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
