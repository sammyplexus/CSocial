package com.example.cozasocial;

/**
 * Created by SamuelAgbede on 9/8/2015.
 */
public class PraiseReportsModel {

    private String full_name, email_address, title, praise_report;
    public PraiseReportsModel()
    {

    }

    public PraiseReportsModel(String full_name, String email_address, String title, String praise_report){
        this.full_name = full_name;
        this.email_address = email_address;
        this.title = title;
        this.praise_report = praise_report;
    }
    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPraise_report() {
        return praise_report;
    }

    public void setPraise_report(String praise_report) {
        this.praise_report = praise_report;
    }




}
