package com.example.cozasocial;

/**
 * Created by SamuelAgbede on 9/6/2015.
 */
public class DevComments {
    private String username, comments, email_address, _time;

    public DevComments(){

    }

    public DevComments(String username, String comments, String email_address, String _time){
        this.username = username;
        this.comments = comments;
        this.email_address = email_address;
        this._time = _time;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String get_time() {
        return _time;
    }

    public void set_time(String _time) {
        this._time = _time;
    }




}
