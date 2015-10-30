package com.example.cozasocial;

/**
 * Created by SamuelAgbede on 7/19/2015.
 */
public class DataClass {
    public static String phone_number;
    public static String username;
    public static String email_address;
    public static String displayname;
    public static String Location;

    public DataClass(String username, String phone_number, String email_address, String displayname, String Location){
        this.username = username;
        this.phone_number = phone_number;
        this.email_address = email_address;
        this.displayname = displayname;
        this.Location = Location;
            }

    public static String getUsername(){
        return username;
    }

    public static String getPhone_number(){
        return phone_number;
    }
    public static String getEmail_address(){
        return email_address;
    }
    public static String getLocation(){
        return Location;
    }
    public static String getDisplayname(){
        return displayname;
    }

    public static void setValues(String username){

    }


}
