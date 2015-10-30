package com.example.cozasocial;

/**
 * Created by SamuelAgbede on 9/6/2015.
 */
public class NotificationsModel {
    private String status, title, content, time;
    public NotificationsModel()
    {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public NotificationsModel(String status, String title, String content, String time)
    {
        this.status = status;
        this.title = title;
        this.content = content;
        this.time = time;

    }



}
